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

	public static  Main instance = new Main();
	private Stage primaryStage;
	public static GameLogic logic;
	public static GameScreen gameScreen;
	public Menu menu = new Menu();
	private boolean isGameSceneShown = false;

	
//	private Main(){
//		
//	}
	
	public void start(Stage primaryStage) throws Exception {

		instance = this;
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Dodge the bullet");
		this.primaryStage.setResizable(false);
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		toggleScene();
		
		

		this.primaryStage.show();
		
		System.out.println("GAME STARTS!");
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public synchronized void toggleScene() throws Exception{
		if (this.isGameSceneShown){
			logic = new GameLogic();
			gameScreen = new GameScreen(logic);
			this.primaryStage.setScene(new Scene(gameScreen));
			System.out.println("To Config Screen");
			gameScreen.requestFocusForCanvas();
			new GameLoop(gameScreen).start();
		}
		else{
			menu.start(this.primaryStage);
			System.out.println("To Game Screen");
			
		}
		this.isGameSceneShown = !this.isGameSceneShown;
	}

}
