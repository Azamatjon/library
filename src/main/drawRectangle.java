package main;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
public class drawRectangle extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x, y, width, height;
	private Color color;
	private Graphics2D g2;
	public drawRectangle (int x,int y,int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		
	}
	public void paint(Graphics g) {
		g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fillRect(x, y, width, height);
		this.setSize(width, height);
		/*
		AffineTransform transform = new AffineTransform();
		transform.rotate(2.1, x + width / 2, y + height / 2 );
		AffineTransform old = g2.getTransform();
		g2.transform(transform);*/
		
		/*g2.setTransform(old);*/
		
		
		//g2.draw(new Rectangle2D.Double(x, y, width, height));
		
	
	}
}
