import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameOfLife extends JFrame{
	static JButton cell[][] = new JButton[69][69];
	static JPanel pan;
	static GameOfLife frame = null;
	static boolean b = true;
	public GameOfLife() {
		super("Game Of Life");
		setSize(500, 500);
		setLocationRelativeTo(this);
		setResizable(false);
		pan = new JPanel(new GridLayout(69,69));
		for(int i=0;i<69;i++) {
			for(int j=0;j<69;j++) {
				cell[i][j] = new JButton();
				cell[i][j].setBackground(Color.WHITE);
				cell[i][j].setVisible(true);
				cell[i][j].setBorder(null);
				cell[i][j].setEnabled(false);
				set(i,j);
				pan.add(cell[i][j]);
			}
		}
		add(pan);
	}
	void set(int i, int j) {
		if(i==0 && j==4)
			cell[i][j].setBackground(Color.BLACK);
		if(i==1 && j==4)
			cell[i][j].setBackground(Color.BLACK);
		if(i==0 && j==5)
			cell[i][j].setBackground(Color.BLACK);
		if(i==1 && j==5)
			cell[i][j].setBackground(Color.BLACK);
		if(i==12 && j==2)
			cell[i][j].setBackground(Color.BLACK);
		if(i==13 && j==2)
			cell[i][j].setBackground(Color.BLACK);
		if(i==3 && j==11)
			cell[i][j].setBackground(Color.BLACK);
		if(i==4 && j==10)
			cell[i][j].setBackground(Color.BLACK);
		if(i==5 && j==10)
			cell[i][j].setBackground(Color.BLACK);
		if(i==6 && j==10)
			cell[i][j].setBackground(Color.BLACK);
		if(i==7 && j==11)
			cell[i][j].setBackground(Color.BLACK);
		if(i==8 && j==12)
			cell[i][j].setBackground(Color.BLACK);
		if(i==8 && j==13)
			cell[i][j].setBackground(Color.BLACK);
		if(i==5 && j==14)
			cell[i][j].setBackground(Color.BLACK);
		if(i==3 && j==15)
			cell[i][j].setBackground(Color.BLACK);
		if(i==4 && j==16)
			cell[i][j].setBackground(Color.BLACK);
		if(i==5 && j==16)
			cell[i][j].setBackground(Color.BLACK);
		if(i==6 && j==16)
			cell[i][j].setBackground(Color.BLACK);
		if(i==5 && j==17)
			cell[i][j].setBackground(Color.BLACK);
		if(i==2 && j==20)
			cell[i][j].setBackground(Color.BLACK);
		if(i==3 && j==20)
			cell[i][j].setBackground(Color.BLACK);
		if(i==4 && j==20)
			cell[i][j].setBackground(Color.BLACK);
		if(i==2 && j==21)
			cell[i][j].setBackground(Color.BLACK);
		if(i==3 && j==21)
			cell[i][j].setBackground(Color.BLACK);
		if(i==4 && j==21)
			cell[i][j].setBackground(Color.BLACK);
		if(i==1 && j==22)
			cell[i][j].setBackground(Color.BLACK);
		if(i==5 && j==22)
			cell[i][j].setBackground(Color.BLACK);
		if(i==0 && j==24)
			cell[i][j].setBackground(Color.BLACK);
		if(i==1 && j==24)
			cell[i][j].setBackground(Color.BLACK);
		if(i==5 && j==24)
			cell[i][j].setBackground(Color.BLACK);
		if(i==6 && j==24)
			cell[i][j].setBackground(Color.BLACK);
		if(i==2 && j==34)
			cell[i][j].setBackground(Color.BLACK);
		if(i==3 && j==34)
			cell[i][j].setBackground(Color.BLACK);
		if(i==2 && j==35)
			cell[i][j].setBackground(Color.BLACK);
		if(i==3 && j==35)
			cell[i][j].setBackground(Color.BLACK);
		if(i==7 && j==15)
			cell[i][j].setBackground(Color.BLACK);
		if(i==60 && j==60)
			cell[i][j].setBackground(Color.BLACK);
		if(i==60 && j==61)
			cell[i][j].setBackground(Color.BLACK);
		if(i==60 && j==62)
			cell[i][j].setBackground(Color.BLACK);
		pan.add(cell[i][j]);
	}
	int getN(int x,int y) {
		int n = 0;

		int startPosX = (x - 1 < 0) ? x : x-1;
		int startPosY = (y - 1 < 0) ? y : y-1;
		int endPosX = (x + 1 > 68) ? x : x+1;
		int endPosY = (y + 1 > 68) ? y : y+1;

		for (int i = startPosX; i<=endPosX; i++)
			for (int j = startPosY; j<=endPosY; j++)
				if (cell[i][j].getBackground()==Color.BLACK)
					n++;
		return n;
	}
	void loop() {
		for(int i=0;i<69;i++) {
			for(int j=0;j<69;j++) {
				int neighbours=getN(i,j);
				//System.out.println(neighbours);
				if(neighbours<2 || neighbours>3)
					cell[i][j].setBackground(Color.WHITE);
				else if(neighbours==3)
					cell[i][j].setBackground(Color.BLACK);
			}
		}
	}
	class Update extends Thread{
		public void run() {
			while(b) {
				loop();
				try {
					Thread.sleep(69);
				}
				catch(InterruptedException e) {
					
				}
			}
		}
	}
	public static void main(String[] args){
		frame=new GameOfLife();
		frame.setVisible(true);
		frame.new Update().start();
	}
}