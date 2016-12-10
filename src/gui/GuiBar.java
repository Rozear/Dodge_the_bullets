package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameLogic;
import main.IRenderableHolder;
import utilities.Configuration;

public class GuiBar extends BorderPane {
	
	private Canvas leftCanvas, centerCanvas, rightCanvas;
	private GameLogic logic;
	
	public GuiBar(GameLogic logic){
		leftCanvas = new Canvas(Configuration.SCORE_WIDTH, Configuration.GUI_HEIGHT);
		centerCanvas = new Canvas(Configuration.SKILLS_WIDTH, Configuration.GUI_HEIGHT);
		rightCanvas = new Canvas(Configuration.LIFE_WIDTH, Configuration.GUI_HEIGHT);
		this.setLeft(leftCanvas);
		this.setCenter(centerCanvas);
		this.setRight(rightCanvas);
		this.logic = logic;
	}
	
	public void paintGui(){
		this.paintExp();
		this.paintSkills();
		this.paintLife();
	}
	
	public void paintExp(){
		GraphicsContext gc = this.leftCanvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.clearRect(0, 0, this.leftCanvas.getWidth(), this.leftCanvas.getHeight());
		gc.fillRect(0, 0, this.leftCanvas.getWidth(), this.leftCanvas.getHeight());
		
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font("Tomaha", FontWeight.BOLD, 45));
		gc.fillText("SCORE : ", 10, 50);
		gc.setFont(new Font("Tomaha", 30));
		gc.fillText(Integer.toString(logic.getPlayer().getExp()), 10, Configuration.GUI_HEIGHT - 10);
	}
	
	public void paintSkills(){
		GraphicsContext gc = this.centerCanvas.getGraphicsContext2D();
		gc.setFill(Color.GRAY);
		gc.clearRect(0, 0, this.centerCanvas.getWidth(), this.centerCanvas.getHeight());
		gc.fillRect(0, 0, this.centerCanvas.getWidth(), this.centerCanvas.getHeight());
		
		
	}
	
	public void paintLife(){
		GraphicsContext gc = this.rightCanvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.clearRect(0, 0, this.rightCanvas.getWidth(), this.rightCanvas.getHeight());
		gc.fillRect(0, 0, this.rightCanvas.getWidth(), this.rightCanvas.getHeight());
		
		for(int i = logic.getPlayer().getHp(); i > 0; i--){
			gc.drawImage(IRenderableHolder.heart, (i-1)*100 + 25, 25, 50, 50);
		}
	}
}
