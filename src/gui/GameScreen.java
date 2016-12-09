package gui;

import graphics.IRenderableObject;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import main.IRenderableHolder;
import utilities.Configuration;
import utilities.GameLoop;
import utilities.InputUtility;

public class GameScreen extends StackPane{

	private Canvas canvas;
	GameLoop loop;
	
	public GameScreen(GameLoop gameLoop){
		super();
		this.setPrefSize(Configuration.SCREEN_WIDTH, Configuration.SCREEN_HEIGHT);
		this.loop = gameLoop;
		this.canvas = new Canvas(Configuration.SCREEN_WIDTH, Configuration.SCREEN_HEIGHT);
		this.getChildren().add(this.canvas);
//		addListener();
		loop.start();
	}
	
	public void paintComponent(){
		
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		gc.setFill(Color.YELLOW);
		gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
//		gc.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
		gc.drawImage(new Image(ClassLoader.getSystemResource("bg/magicka_2.jpg").toString()), 0, 0, 900, 600);
		for(IRenderableObject renderable : IRenderableHolder.getInstance().getEntities()){
			renderable.render(gc);
		}	
	}
	
	public void requestFocusForCanvas(){
		this.requestFocus();
	}
	
	public void applyResize(){
		this.canvas.setWidth(Configuration.SCREEN_WIDTH);
		this.canvas.setHeight(Configuration.SCREEN_HEIGHT);
	}
	
	private void addListener() {
		this.canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("MouseReleased : " + event.getButton().toString());
				if (event.getButton() == MouseButton.PRIMARY)
					InputUtility.setMouseLeftDown(false);
				if (event.getButton() == MouseButton.SECONDARY)
					InputUtility.setMouseRightDown(false);

			}
		});
		this.canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("MousePressed : " + event.getButton().toString());
				if (event.getButton() == MouseButton.PRIMARY) {
					InputUtility.setMouseLeftDown(true);
					InputUtility.setMouseLeftLastDown(true);
				}
				if (event.getButton() == MouseButton.SECONDARY) {
					InputUtility.setMouseRightDown(true);
					InputUtility.setMouseRightLastDown(true);
				}

			}
		});

		this.canvas.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				InputUtility.setMouseOnScreen(false);
			}
		});

		this.canvas.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				InputUtility.setMouseOnScreen(true);
			}
		});

		this.canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (InputUtility.isMouseOnScreen()) {
					InputUtility.setMouseX((int) event.getX());
					InputUtility.setMouseY((int) event.getY());
				}
			}
		});

		this.canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (InputUtility.isMouseOnScreen()) {
					InputUtility.setMouseX((int) event.getX());
					InputUtility.setMouseY((int) event.getY());
				}
			}
		});
		
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("KeyPressed : " + event.getCode().toString());
				if (!InputUtility.getKeyPressed(event.getCode()))
					InputUtility.setKeyTriggered(event.getCode(), true);
				InputUtility.setKeyPressed(event.getCode(), true);
			}
		});

		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("KeyReleased : " + event.getCode().toString());
				InputUtility.setKeyPressed(event.getCode(), false);
			}
		});
	}
}
