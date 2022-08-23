package sim;

import java.awt.Robot;

public class Typing{

	String text;
	
	public Typing(String text) {
		this.text = text;
	}
	
	public void start() {
		try {
		Key.doMacro(new Robot(), text);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
