package sim;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class Frame extends JFrame{
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 800;
	int gap = 20;
	Panel panel = new Panel();
	int functionLines = 10; //기능 개수 줄
	Point firstP = new Point();
	Point secondP = new Point();//JFrame의 위치 이동시 사용할 포인트 2개
	Point thirdP = new Point();
	JTextField[] tf = {new JTextField(),//텍스트 입력 필드
			new JTextField(), //멈출 시간 지정할 텍스트필드 Thread.sleep(초);
			new JTextField(),
			new JTextField()};
	JButton[] btn = {new JButton("저장"), //텍스트 저장버튼
			new JButton("저장"), //마우스 클릭 저장버튼
			new JButton("설정"),
			new JButton("저장"),//마우스 좌표 저장 버튼
			new JButton("저장")}; //멈춤 저장 버튼 btn[4]
	JLabel[] lb = {new JLabel("자동화 툴",SwingConstants.CENTER), new JLabel("텍스트 입력"), new JLabel("마우스 클릭"), 
			new JLabel("마우스 좌표 설정"), new JLabel("x축 : "), new JLabel("y축 : "), new JLabel("멈춤") };
	String[] title = {"등록된 매크로"};
	DefaultTableModel model = new DefaultTableModel(title,1);
	JTable table = new JTable(model);
	JScrollPane scroll = new JScrollPane(table);
	Font bigFont = new Font("경기천년제목 Bold",Font.BOLD,40);
	Font smallFont = new Font("경기천년제목 Bold",Font.BOLD,20);
	Color fontColor = Color.white;
	Color btnColor = C.brown;
	public Frame() {
		init();
		System.out.println("?");
	}
	
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		addKeyListener(new KeySetting());
		addMouseMotionListener(new MouseSetting());
		addMouseListener(new MouseSetting());
		uiSetting();//gui 세팅 메소드
		setVisible(true);
		requestFocus();
	}
	
	public void uiSetting() {
		panel.setLayout(null);
		scroll.setBounds(SCREEN_WIDTH*2/3,0,SCREEN_WIDTH/3,SCREEN_HEIGHT);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		lb[0].setBounds(0,0,SCREEN_WIDTH*2/3,SCREEN_HEIGHT/functionLines);
		lb[0].setForeground(fontColor);
		lb[0].setFont(bigFont); //제목 라벨 세팅
		
		btn[0].setBounds(gap+SCREEN_WIDTH/6+SCREEN_WIDTH*2/6+10,SCREEN_HEIGHT/functionLines+gap,SCREEN_WIDTH*2/12-gap*2,SCREEN_HEIGHT/functionLines-gap*2);
		btn[0].setBackground(btnColor);
		btn[0].setBorder(BorderFactory.createEmptyBorder());
		btn[0].setForeground(fontColor);
		btn[0].setToolTipText(setText("입력하고자 하는 글자를 입력하고<br> '저장'버튼을 눌러주세요."));
		btn[0].setFont(smallFont); //텍스트 입력 저장버튼 세팅
		panel.add(btn[0]);
		
		btn[1].setBounds(gap+SCREEN_WIDTH/6+SCREEN_WIDTH*2/6+10,SCREEN_HEIGHT/functionLines*2+gap,SCREEN_WIDTH*2/12-gap*2,SCREEN_HEIGHT/functionLines-gap*2);
		btn[1].setBackground(btnColor);
		btn[1].setBorder(BorderFactory.createEmptyBorder());
		btn[1].setForeground(fontColor);
		btn[1].setToolTipText(setText("마우스를 한번 클릭하는 매크로입니다."));
		btn[1].setFont(smallFont); //마우스 클릭 저장버튼 세팅
		panel.add(btn[1]);
		
		btn[2].setBounds(-gap*4+SCREEN_WIDTH/6+SCREEN_WIDTH*2/6+10,SCREEN_HEIGHT/functionLines*3+gap,SCREEN_WIDTH*2/12-gap*2,SCREEN_HEIGHT/functionLines-gap*2);
		btn[2].setBackground(btnColor);
		btn[2].setBorder(BorderFactory.createEmptyBorder());
		btn[2].setForeground(fontColor);
		btn[2].setFont(smallFont); //좌표 설정 버튼
		btn[2].setToolTipText(setText("설정 버튼을 누르면 커서를 움직일때마다<br> 아래 x축과 y축에 화면상 커서 좌표가 <br> 표시됩니다. 원하는 좌표에서 Enter키를<br>눌러주세요."));
		btn[2].addMouseListener(new MouseSetting());
		panel.add(btn[2]);
		
		btn[3].setBounds(gap+SCREEN_WIDTH/6+SCREEN_WIDTH*2/6+10,SCREEN_HEIGHT/functionLines*3+gap,SCREEN_WIDTH*2/12-gap*2,SCREEN_HEIGHT/functionLines-gap*2);
		btn[3].setBackground(btnColor);
		btn[3].setBorder(BorderFactory.createEmptyBorder());
		btn[3].setForeground(fontColor);
		btn[3].setToolTipText(setText("좌표가 지정되었으면 <br> '저장' 버튼을 누르세요."));
		btn[3].setFont(smallFont); //설정된 좌표 저장 버튼
		panel.add(btn[3]);
		
		for(int i = 0; i < btn.length; i++) {
			btn[i].addMouseListener(new MouseSetting());
		}
		
		tf[0].setBounds(gap+SCREEN_WIDTH/6,SCREEN_HEIGHT/functionLines+gap,SCREEN_WIDTH*2/6,SCREEN_HEIGHT/functionLines-gap*2);
		tf[0].setForeground(fontColor);
		tf[0].setFont(smallFont); //텍스트 입력 필드 세팅
		tf[0].setToolTipText(setText("입력하고자 하는 글자를 입력하고<br> '저장'버튼을 눌러주세요.")); //setText(String text) 메소드는 html코드를 입력해놓은 메소드이다.
		panel.add(tf[0]);
		lb[1].setToolTipText(setText("키보드 글자 입력 매크로"));
		lb[2].setToolTipText(setText("마우스 1회 클릭 매크로"));
		lb[3].setToolTipText(setText("마우스 좌표 지정 매크로"));
		lb[4].setToolTipText(setText("커서의 x축 표시"));
		lb[5].setToolTipText(setText("커서의 y축 표시"));
		lb[6].setToolTipText(setText("매크로 중간중간 멈추는 매크로"));
		for(int i = 1; i < 3; i++) {
			lb[i].setBounds(gap,SCREEN_HEIGHT/functionLines+(SCREEN_HEIGHT/functionLines*(i-1)),SCREEN_WIDTH/6,SCREEN_HEIGHT/functionLines);
			lb[i].setForeground(fontColor);
			lb[i].setFont(smallFont); //텍스트 입력 라벨 ~ 마우스 클릭 라벨까지만 세팅
			panel.add(lb[i]);
		}
		lb[3].setBounds(gap,SCREEN_HEIGHT/functionLines*3,SCREEN_WIDTH*2/6,SCREEN_HEIGHT/functionLines);
		lb[3].setForeground(fontColor);
		lb[3].setFont(smallFont); // 마우스 좌표 설정 라벨 세팅
		panel.add(lb[3]);
		for(int i = 4; i <=5; i++) {
			lb[i].setBounds(gap+((SCREEN_WIDTH*2/6-gap)*(i-4)),SCREEN_HEIGHT/functionLines*4-gap,SCREEN_WIDTH/6,SCREEN_HEIGHT/functionLines);
			lb[i].setForeground(fontColor);
			lb[i].setFont(smallFont); //x축, y축 라벨 세팅
			panel.add(lb[i]);
		}
		lb[6].setBounds(gap,SCREEN_HEIGHT/functionLines*5,SCREEN_WIDTH*2/6,SCREEN_HEIGHT/functionLines);
		lb[6].setForeground(fontColor);
		lb[6].setFont(smallFont); //멈춤 라벨 세팅
		lb[6].setToolTipText(setText("멈출 시간을 지정하고 <br>'저장' 버튼을 클릭하세요."));
		panel.add(lb[6]);
		tf[1].setBounds(gap+SCREEN_WIDTH/6,SCREEN_HEIGHT/functionLines*5+gap,SCREEN_WIDTH*2/6,SCREEN_HEIGHT/functionLines-gap*2);
		tf[1].setForeground(fontColor);
		tf[1].setFont(smallFont); //텍스트 입력 필드 세팅
		tf[1].setToolTipText(setText("멈출 시간을 지정하고 <br>'저장' 버튼을 클릭하세요."));
		panel.add(tf[1]);
		
		btn[4].setBounds(gap+SCREEN_WIDTH/6+SCREEN_WIDTH*2/6+10,SCREEN_HEIGHT/functionLines*5+gap,SCREEN_WIDTH*2/12-gap*2,SCREEN_HEIGHT/functionLines-gap*2);
		btn[4].setBackground(btnColor);
		btn[4].setBorder(BorderFactory.createEmptyBorder());
		btn[4].setForeground(fontColor);
		btn[4].setFont(smallFont); //설정된 좌표 저장 버튼
		panel.add(btn[4]);
		
		panel.add(scroll);
		panel.add(lb[0]);
		panel.setBorder(BorderFactory.createLineBorder(C.darkgreen, 5, true));
		add(panel);
	}
	public String setText(String text) { //html코드를 입력해놓았다. text에 html 코드만 넣으면 알아서 작동한다.
		return "<html>"+text+"</html>";
	}
	class Panel extends JPanel{
		public void paintComponent(Graphics g) {
			g.setColor(Color.black);
			g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
			g.setColor(Color.white);
			for(int i = 1; i < 4; i++) {
			g.drawLine(0, SCREEN_HEIGHT/functionLines*i, SCREEN_WIDTH*2/3, SCREEN_HEIGHT/functionLines*i);//가로 하얀 라인 그어주기.
			}
			for(int i = 5; i < functionLines; i++) {
			g.drawLine(0, SCREEN_HEIGHT/functionLines*i, SCREEN_WIDTH*2/3, SCREEN_HEIGHT/functionLines*i);
			}
			
			g.drawLine(SCREEN_WIDTH*2/3, 0, SCREEN_WIDTH*2/3, SCREEN_HEIGHT); //세로 하얀 라인 그어주기.
		}
	}
	
	class KeySetting extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE :
				System.exit(0);
				break;
			}
		}
	}
	
	class MouseSetting extends MouseAdapter implements MouseMotionListener{
		public void mouseMoved(MouseEvent e) {
			
		}
		public void mouseDragged(MouseEvent e) {
			thirdP.x = e.getLocationOnScreen().x;
			thirdP.y = e.getLocationOnScreen().y;
			setLocation(firstP.x+(thirdP.x-secondP.x),firstP.y+(thirdP.y-secondP.y));
			System.out.println((firstP.x+(thirdP.x-secondP.x)) + " " +(firstP.y+(thirdP.y-secondP.y)));
			requestFocus();
		}
		
		public void mousePressed(MouseEvent e) {
			secondP.x = e.getLocationOnScreen().x;
			secondP.y = e.getLocationOnScreen().y;
			firstP.x = e.getLocationOnScreen().x-e.getPoint().x;
			firstP.y = e.getLocationOnScreen().y-e.getPoint().y;
			requestFocus();
		}
		
		public void mouseEntered(MouseEvent e) {
			if(e.getSource() == btn[0]||
					e.getSource() == btn[1]||
					e.getSource() == btn[2]||
					e.getSource() == btn[3]||
					e.getSource() == btn[4]) {
				setCursor(new Cursor(12));
			}
			}
		public void mouseExited(MouseEvent e) {
			 setCursor(new Cursor(0));
			 requestFocus();
		}
	}
	
	public static void main(String[] args) {
		new Frame();
	}
}
