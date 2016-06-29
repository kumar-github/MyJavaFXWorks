package com.almasb.fxwars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.almasb.fxwars.entity.AnimationData;
import com.almasb.fxwars.entity.CollisionHandler;
import com.almasb.fxwars.entity.Entity;
import com.almasb.fxwars.entity.Entity.Type;

public class App extends Application {
    /**
     * A second in nanoseconds
     */
    private static final long SECOND = 1000000000;

    private static Config config = Config.getInstance();

    /* EVENTS */
    private boolean reset = false;
    private long lastTimeFired = 0,
            lastTimeEnemySpawned = 0,
            lastTimePowerupSpawned = 0,
            currentTime = 0;

    /* GAME OBJECTS */
    private Entity player = new Entity(Type.PLAYER);
    private Group animationGroup = new Group();

    // we don't really need these lists
    // but they are useful for direct access
    private ArrayList<Entity> bullets = new ArrayList<Entity>();
    private ArrayList<Entity> enemies = new ArrayList<Entity>();
    private ArrayList<Entity> powerups = new ArrayList<Entity>();

    // instead of polluting GC we reuse objects from the pool
    private ArrayList<Entity> enemyPool = new ArrayList<Entity>();
    private ArrayList<Entity> powerupPool = new ArrayList<Entity>();

    /* INPUT */
    private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
    private MouseState mouse = new MouseState();

    /* GAMEPLAY */
    private SimpleLongProperty highScore = new SimpleLongProperty();
    private SimpleLongProperty score = new SimpleLongProperty();
    private SimpleIntegerProperty multiplier = new SimpleIntegerProperty();
    private SimpleIntegerProperty lives = new SimpleIntegerProperty();

    /* UI */
    @FXML
    private Text textScore;
    @FXML
    private Text textHighScore;
    @FXML
    private Text textMultiplier;
    @FXML
    private Text textLives;
    @FXML
    private Text debug;

    /**
     * Contains all other roots and is THE root of the scene
     */
    private Pane root;

    /**
     * Contains game objects
     */
    private Pane entityRoot;

    /**
     * Contains entityRoot and other misc game related data
     */
    private Pane gameRoot;

    /**
     * Contains UI elements
     */
    private Parent uiRoot;

    /**
     * Main loop update, called every 1/60 second (~60 FPS)
     */
    @SuppressWarnings("incomplete-switch")
    private void onUpdate() {
        if (reset) {
            resetLevel();
            reset = false;
        }

        if (currentTime - lastTimeEnemySpawned >= config.getEnemySpawnDelay() * SECOND) {
            spawnEnemies();
        }

        if (currentTime - lastTimePowerupSpawned >= config.getPowerupSpawnDelay() * SECOND) {
            spawnPowerups();
        }

        // handle collisions
        for (int i = 0; i < entityRoot.getChildren().size(); i++) {
            Entity e = (Entity) entityRoot.getChildren().get(i);
            CollisionHandler.handleScreenCollision(e);

            for (int j = i; j < entityRoot.getChildren().size(); j++) {
                Entity e2 = (Entity) entityRoot.getChildren().get(j);
                if (e == e2)
                    continue;

                CollisionHandler.handle(e, e2);
            }
        }

        // update objects and clean
        for (Iterator<Node> it = entityRoot.getChildren().iterator(); it.hasNext(); ) {
            Entity e = (Entity) it.next();

            if (!e.isAlive()) {
                playAnimation(e.getDeathAnimation());
                // remove from entity root
                it.remove();
                // remove from its own group
                switch (e.getType()) {
                    case BULLET:
                        bullets.remove(e);
                        break;
                    case ENEMY_SEEKER:  // fallthru
                    case ENEMY_WANDERER:
                        enemies.remove(e);
                        enemyPool.add(e);
                        break;
                    case POWERUP:
                        powerups.remove(e);
                        powerupPool.add(e);
                        break;
                }
                continue;
            }

            e.update();
        }
    }

    /**
     * Handles all game related user input
     */
    private void processInput() {
        if (isPressed(KeyCode.W) && player.getTranslateY() >= player.getSpeed()) {
            player.translate(0, -player.getSpeed());
        }

        if (isPressed(KeyCode.S) && player.getTranslateY() + player.getGeometry().height <= config.getAppHeight() - player.getSpeed()) {
            player.translate(0, player.getSpeed());
        }

        if (isPressed(KeyCode.A) && player.getTranslateX() >= player.getSpeed()) {
            player.translate(-player.getSpeed(), 0);
        }

        if (isPressed(KeyCode.D) && player.getTranslateX() + player.getGeometry().width <= config.getAppWidth() - player.getSpeed()) {
            player.translate(player.getSpeed(), 0);
        }

        if (mouse.isPressed && currentTime - lastTimeFired >= config.getFireDelay() * SECOND) {
            fire();
        }
    }

    /**
     * Shoots a bullet in the cursor direction
     */
    private void fire() {
        Entity bullet = new Entity(Type.BULLET);

        bullet.setAlive(true);
        bullet.setTranslateX(player.getTranslateX() + player.getGeometry().width / 2);
        bullet.setTranslateY(player.getTranslateY() + player.getGeometry().height / 2);
        bullet.getProperties().put(Entity.PROP_TARGET_X, mouse.x);
        bullet.getProperties().put(Entity.PROP_TARGET_Y, mouse.y);
        bullet.getProperties().put(Entity.PROP_NEW_TARGET, true);

        bullets.add(bullet);
        entityRoot.getChildren().add(bullet);

        //Audio.SHOOT[(int)(Math.random() * Audio.SHOOT.length)].play();
        //Audio.SHOOT[0].play();
        lastTimeFired = currentTime;
    }

    /**
     * Spawns enemies from the enemy pool
     */
    private void spawnEnemies() {
        if (!enemyPool.isEmpty()) {
            Entity e = enemyPool.remove(enemyPool.size() - 1);

            e.setAlive(true);
            e.setTranslateX(getRandomX());
            e.setTranslateY(getRandomY());
            e.getProperties().put(Entity.PROP_NEW_TARGET, true);
            e.getProperties().put(Entity.PROP_TARGET_X, getRandomX());
            e.getProperties().put(Entity.PROP_TARGET_Y, getRandomY());

            enemies.add(e);
            entityRoot.getChildren().add(e);

            // TODO: play sound
            lastTimeEnemySpawned = currentTime;
        }
    }

    /**
     * Spawns powerups from the powerup pool
     */
    private void spawnPowerups() {
        if (!powerupPool.isEmpty()) {
            Entity e = powerupPool.remove(powerupPool.size() - 1);

            e.setAlive(true);
            e.setTranslateX(getRandomX());
            e.setTranslateY(getRandomY());
            e.getProperties().put(Entity.PROP_NEW_TARGET, true);
            e.getProperties().put(Entity.PROP_TARGET_X, getRandomX());
            e.getProperties().put(Entity.PROP_TARGET_Y, getRandomY());

            powerups.add(e);
            entityRoot.getChildren().add(e);

            // TODO: play sound
            lastTimePowerupSpawned = currentTime;
        }
    }

    /**
     * Plays given animation
     * @param data
     */
    private void playAnimation(AnimationData data) {
        animationGroup.getChildren().add(data.node);
        data.animation.play();
    }

    /**
     * Resets level to its original state
     * but the player scores/lives are intact
     *
     * Also respawns the player
     */
    private void resetLevel() {
        enemyPool.addAll(enemies);
        enemies.clear();

        powerupPool.addAll(powerups);
        powerups.clear();

        bullets.clear();

        entityRoot.getChildren().clear();

        multiplier.set(1);

        entityRoot.getChildren().add(player);
        player.setTranslateX(getRandomX());
        player.setTranslateY(getRandomY());
    }

    /**
     * Called when player has no more lives
     */
    private void gameOver() {
        resetLevel();
        playAnimation(AnimationData.createGameOver());
    }

    /**
     * @return
     *          valid X value within the app
     */
    public static double getRandomX() {
        return Math.random() * (config.getAppWidth() - 100);
    }

    /**
     * @return
     *          valid Y value within the app
     */
    public static double getRandomY() {
        return Math.random() * (config.getAppHeight() - 100);
    }

    /**
     * @param key
     * @return
     *          true iff key is currently pressed
     */
    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    /**
     * Holds mouse data
     * cursor's X and Y
     * and whether any mouse button is pressed
     *
     * Note: only tracks XY when any button is pressed
     */
    private static class MouseState {
        double x, y;
        boolean isPressed;

        void update(double x, double y, boolean isPressed) {
            this.x = x;
            this.y = y;
            this.isPressed = isPressed;
        }
    }

    @Override
    public void init() throws Exception {
        // this is the first method to run in our app
        // and first thing we do ensure app config is loaded
        // and all resources are ready and intact
        Config.init();

        // load UI from fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("app_ui.fxml"));
        fxmlLoader.setController(this);
        uiRoot = fxmlLoader.load();

        // TODO: possibly FX bug, occasionally parts of the screen don't get cleaned
        Rectangle bg = new Rectangle(config.getAppWidth(), config.getAppWidth());

        entityRoot = new Pane(player);
        gameRoot = new Pane(bg, animationGroup, entityRoot);
        root = new Pane(gameRoot, uiRoot);

        multiplier.set(1);
        lives.set(config.getDefaultLives());

        long value = Config.loadScore();
        highScore.bind(Bindings.when(score.greaterThan(value)).then(score).otherwise(value));
        textScore.textProperty().bind(new SimpleStringProperty("Score: ").concat(score));
        textHighScore.textProperty().bind(new SimpleStringProperty("High Score: ").concat(highScore));
        textLives.textProperty().bind(new SimpleStringProperty("Lives: ").concat(lives));
        textMultiplier.textProperty().bind(new SimpleStringProperty("Multiplier: ").concat(multiplier));

        /* GAME OBJECTS INIT */

        player.translate(getRandomX(), getRandomY());

        for (int i = 0; i < config.getMaxEnemies() / 2; i++) {
            Entity e = new Entity(Type.ENEMY_SEEKER);
            SimpleDoubleProperty x = new SimpleDoubleProperty();
            x.bind(player.translateXProperty());
            e.getProperties().put(Entity.PROP_PLAYER_X, x);

            SimpleDoubleProperty y = new SimpleDoubleProperty();
            y.bind(player.translateYProperty());
            e.getProperties().put(Entity.PROP_PLAYER_Y, y);

            enemyPool.add(e);

            e = new Entity(Type.ENEMY_WANDERER);
            e.getProperties().put(Entity.PROP_NEW_TARGET, true);
            e.getProperties().put(Entity.PROP_TARGET_X, getRandomX());
            e.getProperties().put(Entity.PROP_TARGET_Y, getRandomY());

            enemyPool.add(e);
        }

        for (int i = 0; i < config.getMaxPowerups(); i++) {
            Entity e = new Entity(Type.POWERUP);
            e.setTranslateX(getRandomX());
            e.setTranslateY(getRandomY());
            e.getProperties().put(Entity.PROP_NEW_TARGET, true);
            e.getProperties().put(Entity.PROP_TARGET_X, getRandomX());
            e.getProperties().put(Entity.PROP_TARGET_Y, getRandomY());

            powerupPool.add(e);
        }

        /* COLLISION HANDLERS */

        CollisionHandler bulletEnemyHandler = new CollisionHandler() {
            @Override
            protected boolean onCollision(Entity a, Entity b) {
                Entity bullet;
                Entity enemy;

                if (a.getType() == Type.BULLET) {
                    bullet = a;
                    enemy = b;
                }
                else {
                    bullet = b;
                    enemy = a;
                }

                bullet.setAlive(false);
                enemy.setAlive(false);

                if (enemy.getType() == Type.ENEMY_WANDERER) {
                    score.set(score.get() + config.getWandererPoints() * multiplier.get());
                    multiplier.set(multiplier.get() + 1);
                }
                else if (enemy.getType() == Type.ENEMY_SEEKER) {
                    score.set(score.get() + config.getSeekerPoints() * multiplier.get());
                    multiplier.set(1);
                }

                return false;
            }
        };

        CollisionHandler playerEnemyHandler = new CollisionHandler() {
            @Override
            protected boolean onCollision(Entity a, Entity b) {

                lives.set(lives.get() - 1);
                reset = true;

                return false;
            }
        };

        CollisionHandler playerPowerupHandler = new CollisionHandler() {
            @Override
            protected boolean onCollision(Entity a, Entity b) {
                Entity powerup = (a.getType() == Type.POWERUP ? a : b);
                powerup.setAlive(false);

                score.set(score.get() + config.getPowerupPoints() * multiplier.get());

                return false;
            }
        };

        CollisionHandler bulletScreenHandler = new CollisionHandler() {
            @Override
            protected void onScreenCollision(Entity bullet) {
                bullet.setAlive(false);
            }
        };

        CollisionHandler enemyScreenHandler = new CollisionHandler() {
            @Override
            protected void onScreenCollision(Entity enemy) {
                enemy.getProperties().put(Entity.PROP_NEW_TARGET, true);
                enemy.getProperties().put(Entity.PROP_TARGET_X, getRandomX());
                enemy.getProperties().put(Entity.PROP_TARGET_Y, getRandomY());
            }
        };

        CollisionHandler.registerCollision(Type.BULLET, Type.ENEMY_SEEKER, bulletEnemyHandler);
        CollisionHandler.registerCollision(Type.BULLET, Type.ENEMY_WANDERER, bulletEnemyHandler);
        CollisionHandler.registerCollision(Type.PLAYER, Type.ENEMY_SEEKER, playerEnemyHandler);
        CollisionHandler.registerCollision(Type.PLAYER, Type.ENEMY_WANDERER, playerEnemyHandler);
        CollisionHandler.registerCollision(Type.PLAYER, Type.POWERUP, playerPowerupHandler);

        CollisionHandler.registerScreenCollision(Type.BULLET, bulletScreenHandler);
        CollisionHandler.registerScreenCollision(Type.ENEMY_WANDERER, enemyScreenHandler);
        CollisionHandler.registerScreenCollision(Type.ENEMY_SEEKER, enemyScreenHandler);
        CollisionHandler.registerScreenCollision(Type.POWERUP, enemyScreenHandler);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(root, config.getAppWidth(), config.getAppHeight());
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        scene.setOnMousePressed(event -> mouse.update(event.getSceneX(), event.getSceneY(), true));
        scene.setOnMouseReleased(event -> mouse.update(event.getSceneX(), event.getSceneY(), false));
        scene.setOnMouseDragged(event -> mouse.update(event.getSceneX(), event.getSceneY(), true));

        primaryStage.setOnCloseRequest(event -> {
            Config.saveScore(highScore.get());
        });
        primaryStage.setScene(scene);
        primaryStage.setTitle("FX Wars");
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            private AnimationTimer pauseMode = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (isPressed(KeyCode.ESCAPE)) {
                        stop();
                        startInternal();
                    }
                }
            };

            private void startInternal() {
                start();
            }

            @Override
            public void handle(long now) {
                currentTime = now;
                processInput();
                onUpdate();
                debug.setText("");

                if (lives.get() == 0) {
                    gameOver();
                    stop();
                }

                if (isPressed(KeyCode.ESCAPE)) {
                    stop();
                    pauseMode.start();
                }
            }
        };

        timer.start();
        //Audio.BGM.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
