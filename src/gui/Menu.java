package gui;

import graphics.IRenderableHolder;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Main;

public class Menu extends Application {
	@FXML
	private Text Start;
	@FXML
	private Text Option;
	@FXML
	private Text Exit;
	@FXML
	private Text yeah;
	@FXML
	private Text Back;
	@FXML
	private Text BGM;
	@FXML
	private Text noramlbgm;
	@FXML
	private Text theme2;
	@FXML
	private Text theme3;
	@FXML
	private ImageView bg;

	boolean playing;
	Scene scene, scene2;
	MediaPlayer mediaPlayer, mediaPlayer2;

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub

		FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
//		scene = new Scene(loader.load());
		Canvas bg = new Canvas(1200, 675);
		bg.getGraphicsContext2D().drawImage(IRenderableHolder.menuBG, 0, 0);
//		StackPane root = new StackPane(bg, loader.load());
//		root.setPrefSize(854, 480);
//		scene = new Scene(root);
		scene = new Scene(new StackPane(bg, loader.load()));

		Menu a = loader.getController();

		stage.setScene(scene);
		a.setstage(stage);
		a = loader.getController();

		stage.show();

	}

	private void setstage(Stage stage) {
		TranslateTransition transStart = new TranslateTransition(Duration.millis(1000), Start);
		transStart.toXProperty().bind(stage.widthProperty().add(0));
		TranslateTransition transOption = new TranslateTransition(Duration.millis(1200), Option);
		transOption.toXProperty().bind(stage.widthProperty().add(0));
		TranslateTransition transExit = new TranslateTransition(Duration.millis(1300), Exit);
		transExit.toXProperty().bind(stage.widthProperty().add(0));

		Start.setOnMouseClicked(a -> {
			transStart.play();
			transOption.play();
			transExit.play();
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), ae -> {
				try {
					Main.instance.toggleScene();
					MyMediaPlayer.musicplay();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}));
			timeline.play();
		});

		Option.setOnMouseClicked(a -> {

			BGM.setVisible(true);
			noramlbgm.setVisible(true);
			yeah.setVisible(true);
			Start.setVisible(false);
			Option.setVisible(false);
			Exit.setVisible(false);
			Back.setVisible(true);
		});

		Back.setOnMouseClicked(a -> {
			BGM.setVisible(false);
			noramlbgm.setVisible(false);
			theme2.setVisible(false);
			theme3.setVisible(false);
			yeah.setVisible(false);
			Start.setVisible(true);
			Option.setVisible(true);
			Exit.setVisible(true);
			Back.setVisible(false);
		});

		Exit.setOnMouseClicked(a -> {
			stage.close();
		});
		noramlbgm.setOnMouseClicked(a -> {
			MyMediaPlayer.changemusic();
			noramlbgm.setVisible(false);
			theme2.setVisible(true);
		});
		theme2.setOnMouseClicked(a -> {
			MyMediaPlayer.changemusic();
			theme3.setVisible(true);
			theme2.setVisible(false);
		});
		theme3.setOnMouseClicked(a -> {
			MyMediaPlayer.changemusic();
			noramlbgm.setVisible(true);
			theme3.setVisible(false);
		});
		new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub

			}
			
		}.start();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
