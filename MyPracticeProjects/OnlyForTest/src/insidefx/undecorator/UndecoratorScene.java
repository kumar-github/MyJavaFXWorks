package insidefx.undecorator;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author In-SideFX
 */
public class UndecoratorScene extends Scene {

    static public final String DEFAULT_STYLESHEET = "skin/undecorator.css";
    static public final String DEFAULT_STYLESHEET_UTILITY = "skin/undecoratorUtilityStage.css";
    static public final String DEFAULT_STAGEDECORATION = "stagedecoration.fxml";
    static public final String DEFAULT_STAGEDECORATION_UTILITY = "stageUtilityDecoration.fxml";

    static public final String DEFAULT_STYLESHEET_TOUCH = "skin/Touch/undecorator.css";
    static public final String DEFAULT_STYLESHEET_UTILITY_TOUCH = "skin/Touch/undecoratorUtilityStage.css";
    static public final String DEFAULT_STAGEDECORATION_TOUCH = "stagedecorationTouch.fxml";
    static public final String DEFAULT_STAGEDECORATION_UTILITY_TOUCH = "stageUtilityDecorationTouch.fxml";

    static public String STYLESHEET = DEFAULT_STYLESHEET_TOUCH;
    static public String STYLESHEET_UTILITY = DEFAULT_STYLESHEET_UTILITY_TOUCH;
    static public String STAGEDECORATION = DEFAULT_STAGEDECORATION_TOUCH;
    static public String STAGEDECORATION_UTILITY = DEFAULT_STAGEDECORATION_UTILITY_TOUCH;

    Undecorator undecorator;

    static public void setClassicDecoration() {
        UndecoratorScene.STAGEDECORATION = UndecoratorScene.DEFAULT_STAGEDECORATION;
        UndecoratorScene.STAGEDECORATION_UTILITY = UndecoratorScene.DEFAULT_STAGEDECORATION_UTILITY;
        UndecoratorScene.STYLESHEET = UndecoratorScene.DEFAULT_STYLESHEET;
        UndecoratorScene.STYLESHEET_UTILITY = UndecoratorScene.DEFAULT_STYLESHEET_UTILITY;
    }

    /**
     * Basic constructor with built-in behavior
     *
     * @param stage The main stage
     * @param root your UI to be displayed in the Stage
     */
    public UndecoratorScene(Stage stage, Region root) {
        this(stage, StageStyle.TRANSPARENT, root, STAGEDECORATION);
    }

    /**
     * UndecoratorScene constructor
     *
     * @param stage The main stage
     * @param stageStyle could be StageStyle.UTILITY or StageStyle.TRANSPARENT
     * @param root your UI to be displayed in the Stage
     * @param stageDecorationFxml Your own Stage decoration or null to use the built-in one
     */
    public UndecoratorScene(Stage stage, StageStyle stageStyle, Region root, String stageDecorationFxml) {

        super(root);

        /*
         * Fxml
         */
        if (stageDecorationFxml == null) {
            if (stageStyle == StageStyle.UTILITY) {
                stageDecorationFxml = STAGEDECORATION_UTILITY;
            } else {
                stageDecorationFxml = STAGEDECORATION;
            }
        }
        undecorator = new Undecorator(stage, root, stageDecorationFxml, stageStyle);
        super.setRoot(undecorator);

        // Customize it by CSS if needed:
        if (stageStyle == StageStyle.UTILITY) {
            undecorator.getStylesheets().add(STYLESHEET_UTILITY);
        } else {
            undecorator.getStylesheets().add(STYLESHEET);
        }

        // Transparent scene and stage
        if (stage.getStyle() != StageStyle.TRANSPARENT) {
            stage.initStyle(StageStyle.TRANSPARENT);
        }
        super.setFill(Color.TRANSPARENT);

        // Default Accelerators
        undecorator.installAccelerators(this);

        // Forward pref and max size to main stage
        stage.setMinWidth(undecorator.getMinWidth());
        stage.setMinHeight(undecorator.getMinHeight());
        stage.setWidth(undecorator.getPrefWidth());
        stage.setHeight(undecorator.getPrefHeight());
    }

    public void removeDefaultStylesheet() {
        undecorator.getStylesheets().remove(STYLESHEET);
        undecorator.getStylesheets().remove(STYLESHEET_UTILITY);
    }

    public void addStylesheet(String css) {
        undecorator.getStylesheets().add(css);
    }

    public void setAsStageDraggable(Stage stage, Node node) {
        undecorator.setAsStageDraggable(stage, node);
    }

    public void setBackgroundStyle(String style) {
        undecorator.getShadowNode().setStyle(style);
    }

    public void setBackgroundOpacity(double opacity) {
        undecorator.getShadowNode().setOpacity(opacity);
    }

    public void setBackgroundPaint(Paint paint) {
        undecorator.removeDefaultBackgroundStyleClass();
        undecorator.getShadowNode().setFill(paint);
    }

    public Undecorator getUndecorator() {
        return undecorator;
    }

    public void setFadeInTransition() {
        undecorator.setFadeInTransition();
    }

    public void setFadeOutTransition() {
        undecorator.setFadeOutTransition();
    }

}
