package sim;

import java.awt.Robot;

public class MouseMove {

	int x, y;
	
	public MouseMove(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void start() {
		try {
		new Robot().mouseMove(x, y);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
