package sim;

import java.awt.Robot;
import java.awt.event.InputEvent;

public class Macro extends Robot{

	int x, y;
	boolean isActive = false;
	int sleepTime;
	public Macro(int x, int y) throws Exception{
		this.x = x;
		this.y = y;
			this.mouseMove(x,y);
		}
	public Macro(int sleepTime) throws Exception {
		Thread.sleep(sleepTime);
	}
	public Macro(String text) throws Exception{
		Key.doMacro(this, text);
	}
	public Macro() throws Exception{
		this.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		this.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	public void start() {
		
	}

}
