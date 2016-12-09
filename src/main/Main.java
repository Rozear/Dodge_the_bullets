package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utilities.GameLoop;
import gui.*;

public class Main extends Application{

	public static final Main instance = new Main();
	private Stage primaryStage;
	private GameScreen gameScreen = new GameScreen(new GameLoop());
	
//	private Main(){
//		
//	}
	
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Dodge the bullet");
		this.primaryStage.setResizable(false);
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		this.primaryStage.setScene(new Scene(Main.instance.gameScreen));

		this.primaryStage.show();
		
		System.out.println("GAME STARTS!");
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	public GameScreen getGameScreen(){
		return this.gameScreen;
	}
}
