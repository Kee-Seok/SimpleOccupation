package sim;

import java.awt.Robot;

public class Sleep extends Robot{

	int sleepTime;
	public Sleep(int sleepTime) throws Exception{
		this.sleepTime = sleepTime;
	}
	
	public void start() {
		try {
			Thread.sleep(sleepTime);
		}catch(Exception e) {
			
		}
	}
	
}
