package utilities;

import java.util.ArrayList;
import javafx.scene.input.KeyCode;

public class InputUtility {

	private static int mouseX, mouseY;
	private static boolean mouseLeftDown, mouseRightDown, mouseOnScreen;
	private static boolean mouseLeftLastDown, mouseRightLastDown;
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>();
	private static ArrayList<KeyCode> keyTriggered = new ArrayList<>();

	public static int getMouseX() {
		return InputUtility.mouseX;
	}

	public static void setMouseX(int mouseX) {
		/* fill code */
		InputUtility.mouseX = mouseX;
	}

	public static int getMouseY() {
		return InputUtility.mouseY;
	}

	public static void setMouseY(int mouseY) {
		/* fill code */
		InputUtility.mouseY = mouseY;
	}

	public static boolean isMouseLeftDown() {
		return InputUtility.mouseLeftDown;
	}

	public static void setMouseLeftDown(boolean mouseLeftDown) {
		/* fill code */
		InputUtility.mouseLeftDown = mouseLeftDown;
	}

	public static boolean isMouseRightDown() {
		return InputUtility.mouseRightDown;
	}

	public static void setMouseRightDown(boolean mouseRightDown) {
		/* fill code */
		InputUtility.mouseRightDown = mouseRightDown;
	}

	public static boolean isMouseOnScreen() {
		return InputUtility.mouseOnScreen;
	}

	public static void setMouseOnScreen(boolean mouseOnScreen) {
		/* fill code */
		InputUtility.mouseOnScreen = mouseOnScreen;
	}

	public static boolean isMouseLeftClicked() {
		return InputUtility.mouseLeftDown && InputUtility.mouseLeftLastDown;
	}

	public static void setMouseLeftLastDown(boolean v) {
		/* fill code */
		InputUtility.mouseLeftLastDown = v;
	}

	public static boolean isMouseRightClicked() {
		return InputUtility.mouseRightDown && InputUtility.mouseRightLastDown;
	}

	public static void setMouseRightLastDown(boolean v) {
		/* fill code */
		InputUtility.mouseRightLastDown = v;
	}

	public static boolean getKeyPressed(KeyCode keycode) {
		return InputUtility.keyPressed.contains(keycode);
	}

	public static void setKeyPressed(KeyCode keycode, boolean pressed) {
		/* fill code */
		if(pressed && !InputUtility.keyPressed.contains(keycode))
			InputUtility.keyPressed.add(keycode);
		else if(!pressed)
			InputUtility.keyPressed.remove(keycode);
	}

	public static boolean getKeyTriggered(KeyCode keycode) {
		return InputUtility.keyTriggered.contains(keycode);
	}

	public static void setKeyTriggered(KeyCode keycode, boolean pressed) {
		/* fill code */
		if(pressed && !InputUtility.keyPressed.contains(keycode)){
			InputUtility.keyTriggered.add(keycode);
//			System.out.println("add " + keycode.getName());
		}
		else if(!pressed){
			InputUtility.keyTriggered.remove(keycode);
//			System.out.println("remove " + keycode.getName());
		}
	}

	public static void postUpdate() {
		/* fill code */
		InputUtility.mouseLeftLastDown = false;
		InputUtility.mouseRightLastDown = false;
		keyTriggered.clear();
//		System.out.println("input update");
	}
	
	public static void reset(){
		keyPressed.clear();
		keyTriggered.clear();
	}
}
