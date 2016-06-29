package application;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.TilePaneBuilder;
import javafx.stage.Stage;

/**
 * @author abi
 */
public class AnimatedButton extends Application {

//  private ImageView icon1 = new ImageView(new Image(getClass().getResourceAsStream("/images/kcmpartitions.png")));
//  private ImageView icon2 = new ImageView(new Image(getClass().getResourceAsStream("/images/kcmpci.png")));
//  private ImageView icon3 = new ImageView(new Image(getClass().getResourceAsStream("/images/personal.png")));
//
//  private ImageView miniIcon1 = new ImageView(new Image(getClass().getResourceAsStream("/images/bell.png")));
//  private ImageView miniIcon2 = new ImageView(new Image(getClass().getResourceAsStream("/images/important.png")));
//  private ImageView miniIcon3 = new ImageView(new Image(getClass().getResourceAsStream("/images/kcmdrkonqi.png")));

  
  private ImageView icon1 = new ImageView(new Image(getClass().getResourceAsStream("kcmpartitions.png")));
  private ImageView icon2 = new ImageView(new Image(getClass().getResourceAsStream("kcmpci.png")));
  private ImageView icon3 = new ImageView(new Image(getClass().getResourceAsStream("personal.png")));

  private ImageView miniIcon1 = new ImageView(new Image(getClass().getResourceAsStream("bell.png")));
  private ImageView miniIcon2 = new ImageView(new Image(getClass().getResourceAsStream("important.png")));
  private ImageView miniIcon3 = new ImageView(new Image(getClass().getResourceAsStream("kcmdrkonqi.png")));


  public static void main(final String[] args) {
    Application.launch(args);
  }

  public void start(final Stage stage) {
    final MiniIconAnimationButton b1 = new MiniIconAnimationButton("1. Button", icon1, miniIcon1, MiniIconAnimationButton.AnimationType.JUMP);
    final MiniIconAnimationButton b2 = new MiniIconAnimationButton("2. Button", icon2, miniIcon2, MiniIconAnimationButton.AnimationType.BLINK);
    final MiniIconAnimationButton b3 = new MiniIconAnimationButton("3. Button", icon3, miniIcon3, MiniIconAnimationButton.AnimationType.NONE);

    final TilePane rootPane = TilePaneBuilder
        .create()
        .children(b1, b2, b3)
        .padding(new Insets(4, 4, 4, 4))
        .hgap(10)
        .build();

    final Scene scene = SceneBuilder
        .create()
        .width(474)
        .height(160)
        .root(rootPane)
        .build();

    stage.setScene(scene);

    stage.show();
  }

}
