package com.almasb.fxwars;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public final class Config {
    /**
     * No outside instances
     */
    private Config() {}

    /**
     * The config singleton for convenient resource
     * management since we don't need to use classloaders
     * and we can retain same code for loading resources
     * within IDE and .jar
     * Also used for config values injection from .fxml
     * and still have static access
     */
    private static final Config instance = new Config();

    /**
     * A relative path from source directory
     * to resources
     */
    public static final String RESOURCES_ROOT = "/res/";
    public static final String IMAGES_ROOT = RESOURCES_ROOT + "images/";
    public static final String AUDIO_ROOT = RESOURCES_ROOT + "audio/";
    public static final String LEVELS_ROOT = RESOURCES_ROOT + "levels/";
    public static final String FONTS_ROOT = RESOURCES_ROOT + "fonts/";

    @FXML
    private SimpleIntegerProperty appWidth;
    @FXML
    private SimpleIntegerProperty appHeight;
    @FXML
    private int maxEnemies;
    @FXML
    private int maxPowerups;
    @FXML
    private double enemySpawnDelay;
    @FXML
    private double powerupSpawnDelay;
    @FXML
    private double fireDelay;
    @FXML
    private int defaultLives;
    @FXML
    private int wandererPoints;
    @FXML
    private int seekerPoints;
    @FXML
    private int powerupPoints;

    public int getAppWidth() {
        return appWidth.get();
    }

    public int getAppHeight() {
        return appHeight.get();
    }

    public int getMaxEnemies() {
        return maxEnemies;
    }

    public int getMaxPowerups() {
        return maxPowerups;
    }

    public double getEnemySpawnDelay() {
        return enemySpawnDelay;
    }

    public double getPowerupSpawnDelay() {
        return powerupSpawnDelay;
    }

    public double getFireDelay() {
        return fireDelay;
    }

    public int getDefaultLives() {
        return defaultLives;
    }

    public int getWandererPoints() {
        return wandererPoints;
    }

    public int getSeekerPoints() {
        return seekerPoints;
    }

    public int getPowerupPoints() {
        return powerupPoints;
    }

    public static final Config getInstance() {
        return instance;
    }

    public static void init() {
        try {
            FXMLLoader loader = new FXMLLoader(instance.getClass().getResource("app_config.fxml"));
            loader.setController(instance);
            loader.load();

            //            Fonts.loadAll();
            //            Images.loadAll();
            Audio.loadAll();
        }
        catch (Exception e) {
            // shouldn't happen unless someone's tampering with the jar
            System.out.println("Couldn't load game resource: " + e.getMessage());
            System.out.println("Game will now exit");
            System.exit(0);
        }
    }

    public static long loadScore() {
        try (BufferedReader br = Files.newBufferedReader(Paths.get("scores.txt"))) {
            return Long.parseLong(br.readLine());
        }
        catch (Exception e) {
            // ignore
        }

        return 0;
    }

    public static void saveScore(long score) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("scores.txt"))) {
            bw.write(String.valueOf(score));
        }
        catch (Exception e) {
            // ignore
        }
    }

    /*public static final class Fonts {
        public static Font LOGO;

        private static Font loadFont(String path, double size) throws Exception {
            try (InputStream is = instance.getClass().getResourceAsStream(FONTS_ROOT + path)) {
                return Font.loadFont(is, size);
            }
        }

        private static void loadAll() throws Exception {
            LOGO = loadFont("spacebar.ttf", 72);
        }
    }

    public static final class Images {
        public static Image PLAYER;
        public static Image ENEMY;
        public static Image PLATFORM;
        public static Image EXPLOSION;
        public static Image SPIKE;
        public static Image COIN;
        public static Image STONE;
        public static Image POWERUP;

        private static Image loadImage(String path) throws Exception {
            try (InputStream is = instance.getClass().getResourceAsStream(IMAGES_ROOT + path)) {
                return new Image(is);
            }
        }

        private static void loadAll() throws Exception {
            PLAYER = loadImage("player1.png");
            ENEMY = loadImage("enemy3.png");
            PLATFORM = loadImage("platform.png");
            EXPLOSION = loadImage("explosion.png");
            SPIKE = loadImage("spike.png");
            COIN = loadImage("coin.png");
            STONE = loadImage("stone.png");
            POWERUP = loadImage("powerup.png");
        }
    }*/

    public static final class Audio {

        public static AudioClip[] SHOOT = new AudioClip[4];

        public static AudioClip EXPLOSION;
        public static AudioClip COIN;
        public static AudioClip POWERUP;

        public static MediaPlayer BGM;

        private static Media loadMusic(String path) throws Exception {
            return new Media(instance.getClass().getResource(AUDIO_ROOT + path).toExternalForm());
        }

        private static AudioClip loadAudio(String path) throws Exception {
            AudioClip clip = new AudioClip(instance.getClass().getResource(AUDIO_ROOT + path).toExternalForm());
            //clip.volumeProperty().bind(volume);
            return clip;
        }

        private static void loadAll() throws Exception {
            for (int i = 0; i < 4; i++) {
                SHOOT[i] = loadAudio("shoot_" + i + ".wav");
            }

            BGM = new MediaPlayer(loadMusic("bgm.mp3"));
            BGM.setCycleCount(MediaPlayer.INDEFINITE);
            //            EXPLOSION = loadAudio("explosion.wav");
            //            COIN = loadAudio("coin.wav");
            //            POWERUP = loadAudio("powerup.wav");
        }
    }
}
