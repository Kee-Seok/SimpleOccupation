package sim;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class Main {

	int beginX = 1041;
	int[] pageX = { beginX, beginX + 32 };
	int[] ansysX = { 536, 705, 832, 969, 1112 };
	int[] ansysY = { 433, 433 + (257 / 9 * 1), 433 + (257 / 9 * 2), 433 + (257 / 9 * 3), 433 + (257 / 9 * 4),
			433 + (257 / 9 * 5), 433 + (257 / 9 * 6), 433 + (257 / 9 * 7), 433 + (257 / 9 * 8), 433 + (257 / 9 * 9) };
	int[] excelX = { 2084, 2197, 2271, 2340, 2408 };
	int excelY = 399;
	int excelYGab = 22;
	int howmany = 8; // 총 몇명인지
	int page = 0;
	Vector<Object> arr = new Vector<>();

	public static void main(String[] args) {
		new Main();
		System.out.println("? " + new MouseMove(100, 100).getClass());
//		try {
//		Robot r = new Robot();
//		while(true) {
//		PointerInfo pointerInfo = MouseInfo.getPointerInfo();
//		Color c = r.getPixelColor(pointerInfo.getLocation().x, pointerInfo.getLocation().y);
//		System.out.println("x축 : "+pointerInfo.getLocation().x+" y축 : "+pointerInfo.getLocation().y);
//		}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}

	}

	int x;
	int y;

	public Main() {
		try {
			arr.add(new Sleep(1000)); // sleep
			arr.add(new MouseMove(300, 400)); // 마우스 포인터
			arr.add(new Sleep(1000)); // sleep
			arr.add(new Typing("안녕하세요.")); // 키보드 매크로
			arr.add(new Sleep(1000)); // sleep
			arr.add(new Keys("엔터")); // 키보드 기능을 적어넣으세요.
			arr.add(new Sleep(1000)); // sleep
			arr.add(new Typing("오늘은 굉장히 기분이 안좋습니다.")); // 키보드 매크로
			arr.add(new Keys("엔터")); // 키보드 기능을 적어넣으세요.
			
			for (int j = 0; j < 4; j++) {
				for (int i = 0; i < arr.size(); i++) {
					System.out.println(arr.get(i).getClass());
					if (arr.get(i).getClass().equals(MouseMove.class)) {
						MouseMove move = (MouseMove) arr.get(i);
						move.start();
					} else if (arr.get(i).getClass().equals(Sleep.class)) {
						Sleep sleep = (Sleep) arr.get(i);
						sleep.start();
					} else if (arr.get(i).getClass().equals(Click.class)) {
						Click click = (Click) arr.get(i);
						click.start();
					} else if (arr.get(i).getClass().equals(Typing.class)) {
						Typing type = (Typing) arr.get(i);
						type.start();
					} else if (arr.get(i).getClass().equals(Keys.class)) {
						Keys keys = (Keys) arr.get(i);
						keys.start();
					}
				}
				System.out.println(arr.size());
			}
//			Robot r = new Robot();
//			for(int j = 0; j < howmany; j++) {
//			for(int i = 0; i < ansysX.length; i++) {
//				mouse(r,ansysX[i],ansysY[j]);//세번 클릭
//				copy(r);
//				mouse(r,excelX[i],excelY+j*22);
//				paste(r);
//			}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copy(Robot r) {
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_C);
		sleep(100);
		r.keyRelease(KeyEvent.VK_C);
		r.keyRelease(KeyEvent.VK_CONTROL);
		sleep(100);
	}

	public void paste(Robot r) {
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		sleep(100);
		r.keyRelease(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		sleep(100);
	}

	public void key(Robot r, KeyEvent e) {
		r.keyPress(e.getKeyCode());
		r.keyRelease(e.getKeyCode());
	}

	public void mouse(Robot r, int x, int y) { // 세번클릭
		r.mouseMove(x, y);
		sleep(100);
		for (int i = 0; i < 4; i++) {
			sleep(50);
			r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
		sleep(100);
	}

	public void sleep(int shut) {
		try {
			Thread.sleep(shut);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
