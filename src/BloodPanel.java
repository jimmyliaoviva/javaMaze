//105403517
//¹ùÅV°a
//¸êºÞ3A
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class BloodPanel extends JPanel{
	
	public int blood=500;
	
	public BloodPanel() {
		
	}//end constructor
	
	public void hitRoad() {
		blood-=25;
		repaint();
	}//end hitroad
	
	public void hitWall() {
		blood-=100;
		repaint();
	}//end hitWall
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLUE);
		g2.setStroke(new BasicStroke(25));
		//g2.drawLine(0, 10, Main.mazeFrame.getWidth()-(), 10);
		//g2.drawLine(0, 0,blood, 0);
		g2.drawRect(0, 0, blood, 25);
	}
}//end class BloodPanel
