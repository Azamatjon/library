package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class modalCombo {
	protected JLayeredPane label = new JLayeredPane();
	protected ArrayList<comboItem> items;
	protected JLabel wheelBar;
	protected int width;
	protected int height;
	protected int width_;
	protected int height_;
	protected String selected;
	protected drawRectangle box;
	protected JLayeredPane lbl;
	protected JLabel tetx;
	protected String title_;
	protected String selectedName;
	public int selectedId;
	public modalCombo(String title_, ArrayList<comboItem> items, int width, int height,int width_,int height_) {
		this.width = width;
		this.height = height;
		this.width_ = width_;
		this.height_ = height_;
		this.title_ = title_;
		label.setBounds((int)Math.round(width_ / 2 - width / 2), (int)Math.round(height_ / 2 - height / 2), width, height);
		drawRectangle rect = new drawRectangle(0, 0, width, height, new Color(255, 255, 255, 200));
		 hide();
		label.add(rect, new Integer(1));
		
		rect.setSize(width,height);
		
		System.out.println(this.getClass());
		drawRectangle title = new drawRectangle(0, 0, width, 35, new Color(230, 230, 230, 230));
		title.setBounds(0,0, width, 35);
		label.add(title, new Integer(4));
		
		JLabel ttetx = new JLabel(title_);
		ttetx.setFont(new Font("Cochin", Font.PLAIN, 16));
		ttetx.setForeground(Color.black);
		ttetx.setBounds(10,7,width_ - 30, 18);
		label.add(ttetx, new Integer(5));
		
		ImageIcon img = new ImageIcon("images/close.png");
		JLabel imdg_ = new JLabel();
		imdg_.setBounds(title.getWidth() - 30, 8, 16,16);
		imdg_.setIcon(img);
		label.add(imdg_, new Integer(5));
		imdg_.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				hide();
			}
		});
		this.items = items;
		wheelBar = new JLabel();
		wheelBar.setBounds(0, 40, width, 0);
		
		for (comboItem comboItem_ : items) {
			wheelBar.add(modalItem(comboItem_));
		}
		
		reBuild();
		
		box = new drawRectangle(0, 0, width, 30, new Color(255, 255, 255, 200));
		box.setBounds(0, -50, width, 30);
		label.add(box, new Integer(2));
		
		label.add(wheelBar, new Integer(3));
		
		label.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				java.awt.Rectangle bounds = wheelBar.getBounds();
				if (e.getWheelRotation() < 0) {
					if (bounds.y + bounds.getHeight() > height - 10) wheelBar.setBounds(bounds.x, bounds.y + (e.getWheelRotation() * 10), bounds.width, bounds.height );
				} else if (e.getWheelRotation() > 0) {
					
					if (bounds.y < 40) wheelBar.setBounds(bounds.x, bounds.y + (e.getWheelRotation() * 10), bounds.width, bounds.height );
				}
			}
		});
		if (items.size() == 0) {
			selected = "%";
			selectedId = -1;
			selectedName = "";
			tetx.setText(selectedName);
		}
	}
	public JLabel modalItem(comboItem item) {
		JLabel item_ = new JLabel(item.getName());
		item_.setToolTipText(item.getId() + "%" + item.getName());
		item_.setFont(new Font("Cochin", Font.PLAIN, 15));
		item_.setForeground(Color.black);
		item_.setSize(width - 20, 30);
		return item_;
	}
	public void reBuild() {
		System.out.println("rebuilding");
		for (Component lb : wheelBar.getComponents()) {
			java.awt.Rectangle lb_b = lb.getBounds();
			
			lb.setBounds(10, wheelBar.getBounds().height + 5, lb_b.width,  lb_b.height);
			wheelBar.setSize(wheelBar.getBounds().width, (int)(wheelBar.getBounds().height + lb_b.height + 5));
			lb.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					box.setBounds(0, (lb.getBounds().y + wheelBar.getBounds().y), box.getWidth(), box.getHeight());
					
				}
				@Override
				public void mouseExited(MouseEvent e) {
					box.setBounds(0, -50, box.getWidth(), box.getHeight());
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					selected = (String)(((JLabel) lb).getToolTipText());
					String[] selected2 = selected.split("%");
					selectedName = selected2[1];
					selectedId = Integer.parseInt(selected2[0]);
					tetx.setText(selected2[1]);
					//selected = new comboItem(lb.getName());
					box.setBounds(0, lb.getBounds().y + wheelBar.getBounds().y, box.getWidth(), box.getHeight());
					hide();
				}
			});
		}
		
		
	}
	public JLayeredPane getModal() {
		hide();
		return label;
	}
	public JLayeredPane getButton(int width_, int height_) {
		lbl = new JLayeredPane();
		drawRectangle rect = new drawRectangle(0, 0, width_, height_, new Color(255, 255, 255, 200));
		rect.setBounds(0,0,width_, height_);
		lbl.add(rect, new Integer(1));
		lbl.setSize(width_, height_);
		selected = items.get(0).getId() + "%" + items.get(0).getName();
		System.out.println(selected);
		selectedName = selected.split("%")[1];
		selectedId = Integer.parseInt(selected.split("%")[0]);
		tetx = new JLabel(selected.split("%")[1]);
		tetx.setFont(new Font("Cochin", Font.PLAIN, 16));
		tetx.setForeground(Color.black);
		tetx.setBounds(10,-2,width_ - 40,height_);
		lbl.add(tetx, new Integer(2));
		ImageIcon img = new ImageIcon("images/chevron.png");
		JLabel imdg_ = new JLabel();
		imdg_.setBounds(width_ - 30, (int)Math.round(height_ / 2 - 16 / 2), 16,16);
		imdg_.setIcon(img);
		lbl.add(imdg_, new Integer(3));
		System.out.println("w: " + lbl.getWidth() + ", h: " + lbl.getHeight());
		tetx.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("showed");
				show();
			}
		});
		imdg_.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("showed");
				show();
			}
		});
		return lbl;
	}
	
	
	
	public void upd(ArrayList<comboItem> items) {
		this.items = items;
		label.remove(wheelBar);
		wheelBar = new JLabel();
		wheelBar.setBounds(0, 40, width, 0);
		
		for (comboItem comboItem_ : items) {
			wheelBar.add(modalItem(comboItem_));
		}
		label.add(wheelBar, new Integer(3));
		reBuild();
		if (items.size() == 0) {
			selected = "%";
			selectedId = -1;
			selectedName = "";
			tetx.setText(selectedName);
		}
	}
	public void setPosition(int a,int b) {
		label.setBounds(a, b, label.getWidth(), label.getHeight());
	}
	public void setButPosition(int a,int b) {
		lbl.setBounds(a, b, label.getWidth(), label.getHeight());
	}
	public void hide() {
		label.setVisible(false);
	}
	public void show() {
		label.setVisible(true);
	}
}
