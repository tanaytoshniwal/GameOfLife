import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameOfLife extends JFrame implements ActionListener{
	static JButton cell[][] = new JButton[69][69];
	JButton start,stop,reset;
	static int grid[][] = new int[69][69];
	static JPanel pan,btn;
	static GameOfLife frame = null;
	static Update thread = null;
	static boolean b = true;
	public GameOfLife() {
		super("Game Of Life");
		setSize(500, 500);
		setLocationRelativeTo(this);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		btn = new JPanel(new FlowLayout());
		pan = new JPanel(new GridLayout(69,69));
		for(int i=0;i<69;i++) {
			for(int j=0;j<69;j++) {
				grid[i][j]=0;
				cell[i][j] = new JButton();
				cell[i][j].setBackground(Color.WHITE);
				cell[i][j].setVisible(true);
				cell[i][j].setBorder(null);
				cell[i][j].setEnabled(false);
				pan.add(cell[i][j]);
				if(i==0||j==0||i==68||j==68)
					cell[i][j].setBackground(Color.RED);
			}
		}
		start=new JButton("START");
		stop=new JButton("STOP");
		reset=new JButton("RESET");
		start.addActionListener(this);
		stop.addActionListener(this);
		reset.addActionListener(this);
		stop.setEnabled(false);
		reset.setEnabled(false);
		set();
		System.out.println(this.getLayout());
		btn.add(start);
		btn.add(stop);
		btn.add(reset);
		add(btn, BorderLayout.SOUTH);
		add(pan);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource();
		if(b==start) {
			thread = frame.new Update();
			thread.start();
			start.setEnabled(false);
			stop.setEnabled(true);
		}
		if(b==stop) {
			try {
				thread.stop();
			}
			catch(Exception ee) {
				
			}
			stop.setEnabled(false);
			reset.setEnabled(true);
		}
		if(b==reset) {
			reset.setEnabled(false);
			start.setEnabled(true);
			set();
		}
	}
	void set() {
		for(int i=0;i<69;i++) {
			for(int j=0;j<69;j++) {
				if(i==0||j==0||i==68||j==68)
					cell[i][j].setBackground(Color.RED);
				else
					cell[i][j].setBackground(Color.WHITE);
				grid[i][j]=0;
			}
		}
		cell[6][2].setBackground(Color.BLACK);
		cell[6][3].setBackground(Color.BLACK);
		cell[7][2].setBackground(Color.BLACK);
		cell[7][3].setBackground(Color.BLACK);
		cell[6][12].setBackground(Color.BLACK);
		cell[7][12].setBackground(Color.BLACK);
		cell[8][12].setBackground(Color.BLACK);
		cell[5][13].setBackground(Color.BLACK);
		cell[9][13].setBackground(Color.BLACK);
		cell[4][14].setBackground(Color.BLACK);
		cell[4][15].setBackground(Color.BLACK);
		cell[10][14].setBackground(Color.BLACK);
		cell[10][15].setBackground(Color.BLACK);
		cell[7][16].setBackground(Color.BLACK);
		cell[5][17].setBackground(Color.BLACK);
		cell[9][17].setBackground(Color.BLACK);
		cell[6][18].setBackground(Color.BLACK);
		cell[7][18].setBackground(Color.BLACK);
		cell[8][18].setBackground(Color.BLACK);
		cell[7][19].setBackground(Color.BLACK);
		cell[6][22].setBackground(Color.BLACK);
		cell[5][22].setBackground(Color.BLACK);
		cell[4][22].setBackground(Color.BLACK);
		cell[4][23].setBackground(Color.BLACK);
		cell[5][23].setBackground(Color.BLACK);
		cell[6][23].setBackground(Color.BLACK);
		cell[3][24].setBackground(Color.BLACK);
		cell[7][24].setBackground(Color.BLACK);
		cell[3][26].setBackground(Color.BLACK);
		cell[7][26].setBackground(Color.BLACK);
		cell[2][26].setBackground(Color.BLACK);
		cell[8][26].setBackground(Color.BLACK);
		cell[4][36].setBackground(Color.BLACK);
		cell[4][37].setBackground(Color.BLACK);
		cell[5][36].setBackground(Color.BLACK);
		cell[5][37].setBackground(Color.BLACK);
		

		grid[6][2]=1;
		grid[6][3]=1;
		grid[7][2]=1;
		grid[7][3]=1;
		grid[6][12]=1;
		grid[7][12]=1;
		grid[8][12]=1;
		grid[5][13]=1;
		grid[9][13]=1;
		grid[4][14]=1;
		grid[4][15]=1;
		grid[10][14]=1;
		grid[10][15]=1;
		grid[7][16]=1;
		grid[5][17]=1;
		grid[9][17]=1;
		grid[6][18]=1;
		grid[7][18]=1;
		grid[8][18]=1;
		grid[7][19]=1;
		grid[6][22]=1;
		grid[5][22]=1;
		grid[4][22]=1;
		grid[4][23]=1;
		grid[5][23]=1;
		grid[6][23]=1;
		grid[3][24]=1;
		grid[7][24]=1;
		grid[3][26]=1;
		grid[7][26]=1;
		grid[2][26]=1;
		grid[8][26]=1;
		grid[4][36]=1;
		grid[4][37]=1;
		grid[5][36]=1;
		grid[5][37]=1;
	}
	int getN(int x,int y) {
		int n=0;
		for(int i=-1;i<=1;i++) {
			for(int j=-1;j<=1;j++) {
				if(cell[x+i][y+j].getBackground().equals(Color.BLACK))
					n++;
			}
		}
		if(cell[x][y].getBackground().equals(Color.BLACK))
			n--;
		return n;
	}
	void update() {
		for(int i=1;i<68;i++) {
			for(int j=1;j<68;j++) {
				if(grid[i][j]==1) cell[i][j].setBackground(Color.BLACK);
				else cell[i][j].setBackground(Color.WHITE);
			}
		}
	}
	void loop() {
		for(int i=1;i<68;i++) {
			for(int j=1;j<68;j++) {
				int neighbours=getN(i,j);
				//System.out.print(grid[i][j]+" ");
				if(neighbours<2 || neighbours>3)
					grid[i][j]=0;
				if(neighbours==3)
					grid[i][j]=1;
			}
			System.out.println();
		}
		update();
		//System.exit(0);
	}
	class Update extends Thread{
		public void run() {
			while(b) {
				//b=false;
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
	}
}