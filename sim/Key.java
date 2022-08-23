package sim;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Key {

	
	final static String[] CHO = {"ㄱ","ㄲ","ㄴ","ㄷ","ㄸ","ㄹ","ㅁ","ㅂ","ㅃ",
			"ㅅ","ㅆ","ㅇ","ㅈ","ㅉ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"};
	
	final static String[] JOONG = {"ㅏ","ㅐ","ㅑ","ㅒ","ㅓ","ㅔ","ㅕ","ㅖ","ㅗ","ㅘ",
			"ㅙ","ㅚ","ㅛ","ㅜ","ㅝ","ㅞ","ㅟ","ㅠ","ㅡ","ㅢ","ㅣ"};			
	
	final static String[] JONG = {"","ㄱ","ㄲ","ㄳ","ㄴ","ㄵ","ㄶ","ㄷ","ㄹ","ㄺ","ㄻ","ㄼ",
			"ㄽ","ㄾ","ㄿ","ㅀ","ㅁ","ㅂ","ㅄ","ㅅ","ㅆ","ㅇ","ㅈ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"};
	public static void doMacro(Robot r, String text) {
		for(int i = 0; i < text.length(); i++) {
			try {
				Thread.sleep(50);
			}catch(Exception e) {
				e.printStackTrace();
			}
			char uniVal = text.charAt(i);
			// 한글일 경우만 시작해야 하기 때문에 0xAC00부터 아래의 로직을 실행한다
			if(text.substring(i,i+1).equals(" ")) {
				r.keyPress(KeyEvent.VK_SPACE);
				System.out.println(i+"번째는 빈칸");
			}else if(text.substring(i,i+1).equals("0")||
					text.substring(i,i+1).equals("1")||
					text.substring(i,i+1).equals("2")||
					text.substring(i,i+1).equals("3")||
					text.substring(i,i+1).equals("4")||
					text.substring(i,i+1).equals("5")||
					text.substring(i,i+1).equals("6")||
					text.substring(i,i+1).equals("7")||
					text.substring(i,i+1).equals("8")||
					text.substring(i,i+1).equals("9")){
					numbs(r,text.substring(i,i+1));
			}else if(text.substring(i,i+1).equals("!")){
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_1);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}else if(text.substring(i,i+1).equals("?")){
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_SLASH);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}else if(text.substring(i,i+1).equals(".")){
				r.keyPress(KeyEvent.VK_PERIOD);
				r.keyRelease(KeyEvent.VK_PERIOD);
			}else if(text.substring(i,i+1).equals(",")){
				r.keyPress(KeyEvent.VK_COMMA);
				r.keyRelease(KeyEvent.VK_COMMA);
			}else if(uniVal >= 0xAC00) {
				System.out.print(uniVal + "=>");
				uniVal = (char)(uniVal - 0xAC00);
				
				char cho = (char)(uniVal/28/21);
				char joong = (char) ((uniVal)/28%21);
				char jong = (char) (uniVal % 28);	// 종성의 첫번째는 채움이기 때문에
						
				System.out.println(CHO[cho] + JOONG[joong] + JONG[jong]);
				macro(r,CHO[cho]);
				macro(r,JOONG[joong]);
				if(jong!=0) {
				macro(r,JONG[jong]);
				}
			} else {
				System.out.println(uniVal + "=>" + uniVal);
			}
		}
	}
	
	public static void key(Robot r, int keycode) {
		r.keyPress(keycode);
		r.keyRelease(keycode);
	}
	public static void numbs(Robot r, String numb) {
		int num = Integer.parseInt(numb);
		switch(num) {
		case 0:
			r.keyPress(KeyEvent.VK_0);
			r.keyRelease(KeyEvent.VK_0);
			break;
		case 1:
			r.keyPress(KeyEvent.VK_1);
			r.keyRelease(KeyEvent.VK_1);
			break;
		case 2:
			r.keyPress(KeyEvent.VK_2);
			r.keyRelease(KeyEvent.VK_2);
			break;
		case 3:
			r.keyPress(KeyEvent.VK_3);
			r.keyRelease(KeyEvent.VK_3);
			break;
		case 4:
			r.keyPress(KeyEvent.VK_4);
			r.keyRelease(KeyEvent.VK_4);
			break;
		case 5:
			r.keyPress(KeyEvent.VK_5);
			r.keyRelease(KeyEvent.VK_5);
			break;
		case 6:
			r.keyPress(KeyEvent.VK_6);
			r.keyRelease(KeyEvent.VK_6);
			break;
		case 7:
			r.keyPress(KeyEvent.VK_7);
			r.keyRelease(KeyEvent.VK_7);
			break;
		case 8:
			r.keyPress(KeyEvent.VK_8);
			r.keyRelease(KeyEvent.VK_8);
			break;
		case 9:
			r.keyPress(KeyEvent.VK_9);
			r.keyRelease(KeyEvent.VK_9);
			break;
		}
	}
	public static void macro(Robot r, String str) {
		if(str.equals("ㅂ")||str.equals("q")) {key(r,81);}
		else if(str.equals("ㅈ")||str.equals("w")) {key(r,87);}
		else if(str.equals("ㄷ")||str.equals("e")) {key(r,69);}
		else if(str.equals("ㄱ")||str.equals("r")) {key(r,82);}
		else if(str.equals("ㅅ")||str.equals("t")) {key(r,84);}
		else if(str.equals("ㅛ")||str.equals("y")) {key(r,89);}
		else if(str.equals("ㅕ")||str.equals("u")) {key(r,85);}
		else if(str.equals("ㅑ")||str.equals("i")) {key(r,73);}
		else if(str.equals("ㅐ")||str.equals("o")) {key(r,79);}
		else if(str.equals("ㅔ")||str.equals("p")) {key(r,80);}
		else if(str.equals("ㅁ")||str.equals("a")) {key(r,65);}
		else if(str.equals("ㄴ")||str.equals("s")) {key(r,83);}
		else if(str.equals("ㅇ")||str.equals("d")) {key(r,68);}
		else if(str.equals("ㄹ")||str.equals("f")) {key(r,70);}
		else if(str.equals("ㅎ")||str.equals("g")) {key(r,71);}
		else if(str.equals("ㅗ")||str.equals("h")) {key(r,72);}
		else if(str.equals("ㅓ")||str.equals("j")) {key(r,74);}
		else if(str.equals("ㅏ")||str.equals("k")) {key(r,75);}
		else if(str.equals("ㅣ")||str.equals("l")) {key(r,76);}
		else if(str.equals("ㅋ")||str.equals("z")) {key(r,90);}
		else if(str.equals("ㅌ")||str.equals("x")) {key(r,88);}
		else if(str.equals("ㅊ")||str.equals("c")) {key(r,67);}
		else if(str.equals("ㅍ")||str.equals("v")) {key(r,86);}
		else if(str.equals("ㅠ")||str.equals("b")) {key(r,66);}
		else if(str.equals("ㅜ")||str.equals("n")) {key(r,78);}
		else if(str.equals("ㅡ")||str.equals("m")) {key(r,77);}
		
		else if(str.equals("ㄲ")) {r.keyPress(KeyEvent.VK_SHIFT);key(r,82);r.keyRelease(KeyEvent.VK_SHIFT);}
		else if(str.equals("ㄸ")) {r.keyPress(KeyEvent.VK_SHIFT);key(r,69);r.keyRelease(KeyEvent.VK_SHIFT);}
		else if(str.equals("ㅃ")) {r.keyPress(KeyEvent.VK_SHIFT);key(r,81);r.keyRelease(KeyEvent.VK_SHIFT);}
		else if(str.equals("ㅆ")) {r.keyPress(KeyEvent.VK_SHIFT);key(r,84);r.keyRelease(KeyEvent.VK_SHIFT);}
		else if(str.equals("ㅉ")) {r.keyPress(KeyEvent.VK_SHIFT);key(r,87);r.keyRelease(KeyEvent.VK_SHIFT);}
		
		else if(str.equals("ㅒ")) {r.keyPress(KeyEvent.VK_SHIFT);key(r,79);r.keyRelease(KeyEvent.VK_SHIFT);}
		else if(str.equals("ㅖ")) {r.keyPress(KeyEvent.VK_SHIFT);key(r,80);r.keyRelease(KeyEvent.VK_SHIFT);}
		else if(str.equals("ㅘ")) {key(r,72);key(r,75);}
		else if(str.equals("ㅙ")) {key(r,72);key(r,79);}
		else if(str.equals("ㅚ")) {key(r,72);key(r,76);}
		else if(str.equals("ㅝ")) {key(r,78);key(r,74);}
		else if(str.equals("ㅞ")) {key(r,78);key(r,80);}
		else if(str.equals("ㅟ")) {key(r,78);key(r,76);}
		else if(str.equals("ㅢ")) {key(r,77);key(r,76);}
		
		else if(str.equals("ㄳ")) {key(r,82);key(r,84);}
		else if(str.equals("ㄵ")) {key(r,83);key(r,87);}
		else if(str.equals("ㄶ")) {key(r,83);key(r,71);}
		else if(str.equals("ㄺ")) {key(r,70);key(r,82);}
		else if(str.equals("ㄻ")) {key(r,70);key(r,65);}
		else if(str.equals("ㄼ")) {key(r,70);key(r,81);}
		else if(str.equals("ㄽ")) {key(r,70);key(r,84);}
		else if(str.equals("ㄾ")) {key(r,70);key(r,88);}
		else if(str.equals("ㄿ")) {key(r,70);key(r,86);}
		else if(str.equals("ㅀ")) {key(r,70);key(r,71);}
		else if(str.equals("ㅄ")) {key(r,81);key(r,84);}
	}
}
