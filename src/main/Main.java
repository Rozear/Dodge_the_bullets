package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.GameLogic;
import utilities.GameLoop;
import gui.*;

public class Main extends Application{

	public static final Main instance = new Main();
	private Stage primaryStage;
	public static GameLogic logic = new GameLogic();
	public static GameScreen gameScreen = new GameScreen(logic);


	
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
		
		
		this.primaryStage.setScene(new Scene(gameScreen));
		gameScreen.requestFocusForCanvas();
		new GameLoop(gameScreen).start();

		this.primaryStage.show();
		
		System.out.println("GAME STARTS!");
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
