package sim;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Keys {

	int function;
	public Keys(String function) {
		if(function.equals("엔터")
				||function.equals("enter")
				||function.equals("Enter")) {
		this.function = KeyEvent.VK_ENTER;	
		}else if(function.equals("esc")||function.equals("Esc")||function.equals("ESC")||function.equals("Escape")
				||function.equals("escape")) {
		this.function = KeyEvent.VK_ESCAPE;	
		}else if(function.equals("ctrl")
				||function.equals("Ctrl")
				||function.equals("Control")
				||function.equals("control")
				||function.equals("컨트롤")) {
		this.function = KeyEvent.VK_CONTROL;	
		}else if(function.equals("alt")
				||function.equals("Alt")
				||function.equals("알트")){
		this.function = KeyEvent.VK_ALT;	
		}else if(function.equals("탭")
				||function.equals("tab")
				||function.equals("Tab")
				||function.equals("TAB")){
		this.function = KeyEvent.VK_TAB;	
		}
	}
	public void start() {
		try {
			Robot r = new Robot();
			r.keyPress(function);
			r.keyRelease(function);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
