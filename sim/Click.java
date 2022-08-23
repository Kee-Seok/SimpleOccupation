package sim;

import java.awt.Robot;
import java.awt.event.InputEvent;

public class Click {

	public void start() {
		try {
			Robot r = new Robot();
			r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
