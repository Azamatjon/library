package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class customButton {
	protected JLayeredPane lbl = new JLayeredPane();
	protected JLabel tetx;
	protected String name;
	protected int width;
	protected int height;
	protected String iconPath;
	protected Color backCol;
	protected Color textCol;
	public customButton(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
	}
	public customButton(String name, int width, int height, String iconPath) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.iconPath = iconPath;
	}
	public customButton(String name, int width, int height, String iconPath, Color backCol, Color textCol) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.iconPath = iconPath;
		this.backCol = backCol;
		this.textCol = textCol;
	}
	public JLayeredPane getButton() {
		drawRectangle rect;
		if (backCol != null) rect = new drawRectangle(0, 0, width, height, backCol);
		else rect = new drawRectangle(0, 0, width, height, new Color(255, 255, 255, 200));
		
		rect.setBounds(0,0,width, height);
		lbl.add(rect, new Integer(1));
		lbl.setSize(width, height);
		
		tetx = new JLabel(name);
		tetx.setFont(new Font("Cochin", Font.PLAIN, 16));
		if (textCol != null) tetx.setForeground(textCol);
		else  tetx.setForeground(Color.black);
		tetx.setBounds((int)Math.round(width / 2 - ((width - 40) / 2)),-2,width - 40,height);
		lbl.add(tetx, new Integer(2));
		if (iconPath != null) {
			
			ImageIcon img = new ImageIcon(iconPath);
			JLabel imdg_ = new JLabel();
			imdg_.setBounds(10, (int)Math.round(height / 2 - 16 / 2), 16,16);
			imdg_.setIcon(img);
			lbl.add(imdg_, new Integer(3));
			tetx.setBounds(40,-2,width - 40,height);
			imdg_.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
				
				}
			});
		}
		
		System.out.println("w: " + lbl.getWidth() + ", h: " + lbl.getHeight());
		tetx.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
		});
		
		return lbl;
	}
	public void setPosition(int a,int b) {
		lbl.setBounds(a, b, lbl.getWidth(), lbl.getHeight());
	}
}
