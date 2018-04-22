package main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class Login {

	private JFrame frame;
	private JLabel label;
	private JPanel panel;
	private Dimension screenSize;
	private String title;
	private String picture;
	private int width;
	private int height;
	private drawRectangle err;
	private JLabel errCode;
	private drawRectangle succ;
	private JLabel succCode;
	private Auth logIN;
	/**
	 * Launch the application.
	 */
	public Login(String frameName) {
		this.title = frameName;
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(title);
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int)screenSize.getWidth();
		height = (int)screenSize.getHeight();
		System.out.println(width + " " + height);
		frame.setSize(width, height);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
		panel = new JPanel();
		panel.setLayout(null);
	
		frame.setContentPane(panel);
		
		JLayeredPane layers = new JLayeredPane();
		
		
		label = new JLabel();
		if (picture != null) {
			ImageIcon image = new ImageIcon(picture);
			label.setIcon(image);
		}
		label.setBounds(0,0, width, height);
		layers.add(label, new Integer(1));
		
		
		drawRectangle rct = new drawRectangle(0, 0, 400,250, new Color(255, 255, 255, 150));
		rct.setBounds(width/2 - 400/2,height/2 - 300/2, 400, 250);//300
		layers.add(rct, new Integer(2));
		
		
		
		
		err = new drawRectangle(0, 0, 300,50, new Color(255, 0, 0, 150));
		
		err.setBounds(width - 320, 0 + 20, 280, 40);
		layers.add(err, new Integer(2));
		err.setVisible(false);
		errCode = new JLabel();
		errCode.setForeground(new Color(255,255,255));
		errCode.setFont(new Font("Cochin", Font.PLAIN, 15));
		errCode.setBounds(width - 300, 0 + 20, 270, 40);
		layers.add(errCode, new Integer(3));
		JLabel closeButtonE = new JLabel();
		closeButtonE.setIcon(new ImageIcon("images/close_white.png"));
		closeButtonE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				e.getComponent().getParent().setVisible(false);
				err.setVisible(false);
			}
		});
		errCode.add(closeButtonE, new Integer(4));
		closeButtonE.setBounds(errCode.getWidth() - 20, 0, 30, 30);
		
		errCode.setVisible(false);
		
		succ = new drawRectangle(0, 0, 300,50, new Color(0, 255, 0, 150));
		succ.setBounds(width - 320, 0 + 20, 380, 40);
		
		layers.add(succ, new Integer(2));
		succ.setVisible(false);
		
		succCode = new JLabel("Succesfully registered!");
		succCode.setForeground(new Color(255,255,255));
		succCode.setFont(new Font("Cochin", Font.PLAIN, 15));
		succCode.setBounds(width - 300, 0 + 20, 270, 40);

		JLabel closeButtonS = new JLabel();
		closeButtonS.setIcon(new ImageIcon("images/close_white.png"));
		closeButtonS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				e.getComponent().getParent().setVisible(false);
				succ.setVisible(false);
			}
		});
		
		succCode.add(closeButtonS, new Integer(4));
		closeButtonS.setBounds(succCode.getWidth() - 20, 0, 30, 30);
		
		layers.add(succCode, new Integer(3));
		succCode.setVisible(false);
		
		JLabel loginTitle = new JLabel("Authorization");
		loginTitle.setFont(new Font("Cochin", Font.PLAIN, 22));
		loginTitle.setBounds(width/2 - 65,height/2 - 300/2 + 20, 130, 30);
		layers.add(loginTitle, new Integer(3));
		
		JTextField loginField = new JTextField();
		loginField.setFont(new Font("Cochin", Font.PLAIN, 15));
		loginField.setBounds(width/2 - 65,height/2 - 300/2 + 80, 240, 30);
		layers.add(loginField, new Integer(4));
		
		
		JLabel loginName = new JLabel("Login:");
		loginName.setFont(new Font("Cochin", Font.PLAIN, 19));
		loginName.setBounds(width/2 - 65 - 110,height/2 - 300/2 + 80, 130, 30);
		layers.add(loginName, new Integer(5));
		
		JPasswordField passField = new JPasswordField();
		passField.setFont(new Font("Cochin", Font.PLAIN, 15));
		passField.setBounds(width/2 - 65,height/2 - 300/2 + 130, 240, 30);
		layers.add(passField, new Integer(6));
		
		
		
		loginField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
					@SuppressWarnings("deprecation")
					int logCode = logIn(loginField.getText(), passField.getText());
					if (logCode == 1) {
						showMessage("You have logged in successfully!", true);
					} else if (logCode == 2) {
						showMessage("Error, login/pass not right!", false);
					} else {
						showMessage("Error, user doesn't exist!", false);
					}
				 }
			}
		});
		passField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
					@SuppressWarnings("deprecation")
					int logCode = logIn(loginField.getText(), passField.getText());
					if (logCode == 1) {
						showMessage("You have logged in successfully!", true);
					} else if (logCode == 2) {
						showMessage("Error, login/pass not right!", false);
					} else {
						showMessage("Error, user doesn't exist!", false);
					}
				 }
			}
		});
		JLabel passName = new JLabel("Password:");
		passName.setFont(new Font("Cochin", Font.PLAIN, 19));
		passName.setBounds(width/2 - 65 - 110,height/2 - 300/2 + 130, 130, 30);
		layers.add(passName, new Integer(7));
		
		
		
		JButton logIn = new JButton("LogIn");
		logIn.setFont(new Font("Cochin", Font.PLAIN, 19));
		logIn.setBackground(new Color(255, 255, 255, 200));
		logIn.setBounds(width/2 - 65,height/2 - 300/2 + 190, 100, 40);
		layers.add(logIn, new Integer(8));
		logIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				@SuppressWarnings("deprecation")
				int logCode = logIn(loginField.getText(), passField.getText());
				if (logCode == 1) {
					showMessage("You have logged in successfully!", true);
				} else if (logCode == 2) {
					showMessage("Error, login/pass not right!", false);
				} else {
					showMessage("Error, user doen't exist!", false);
				}
			}
		});
		
		JLabel regName = new JLabel("Registration");
		regName.setFont(new Font("Cochin", Font.PLAIN, 16));
		regName.setBounds(width/2 + 70,height/2 - 300/2 + 195, 130, 30);
		layers.add(regName, new Integer(9));
		
		layers.setBounds(0, 0, width, height);
		panel.add(layers);
		//setBackground();
		regName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Reg rg = new Reg("Registration");
				close();
				rg.setBackground("src/images/login-bg.jpg");
				
			}
		});
		
	}
	public void setBackground(String picture) {
		this.picture = picture;
		if (picture != null) {
			ImageIcon image = new ImageIcon(picture);
			label.setIcon(image);
		}
	}
	public void close() {
		frame.dispose();
	}
	public void add(JLabel k) {
		label.add(k);
	}
	public void updateUI() {
		panel.update(panel.getGraphics());
	}
	public boolean isVisible() {
		return frame.isVisible();
	}
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}
	public int logIn(String login, String password) {
		logIN = new Auth(login, password);
		return logIN.check();
	}

	public void showMessage(String message, boolean isSuccess) {
		if (isSuccess) {
			err.setVisible(false);
			errCode.setVisible(false);
			succCode.setText(message);
			succCode.setVisible(true);
			succ.setVisible(true);
			updateUI();
			try {
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			close();
			main ndf = new main("Dashboard", logIN.getId());
			ndf.setBackground("src/images/login-bg.jpg");
		} else {
			err.setVisible(true);
			errCode.setVisible(true);
			errCode.setText(message);
			succCode.setVisible(false);
			succ.setVisible(false);
		}
		
		
	}
}