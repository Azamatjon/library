package main;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class editProfile {

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
	private int userId;
	private boolean isView = false;
	private mysql db;
	private JTextField loginField;
	/**
	 * Launch the application.
	 */
	public editProfile(String frameName, int userId) {
		this.title = frameName;
		this.userId = userId;
		initialize();
	}
	
	public editProfile(String frameName, int userId, boolean isView) {
		this.title = frameName;
		this.userId = userId;
		this.isView = isView;
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
		
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
		
		db = new mysql();
		
		ResultSet userInfo = db.getRow("Select * from users where id = "+userId);
		
		err = new drawRectangle(0, 0, 300,50, new Color(255, 0, 0, 150));
		
		err.setBounds(width - 320, 0 + 20, 280, 40);
		layers.add(err, new Integer(98));
		err.setVisible(false);
		errCode = new JLabel();
		errCode.setForeground(new Color(255,255,255));
		errCode.setFont(new Font("Cochin", Font.PLAIN, 15));
		errCode.setBounds(width - 300, 0 + 20, 270, 40);
		layers.add(errCode, new Integer(100));
		JLabel closeButtonE = new JLabel();
		closeButtonE.setIcon(new ImageIcon("images/close_white.png"));
		closeButtonE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				e.getComponent().getParent().setVisible(false);
				err.setVisible(false);
			}
		});
		errCode.add(closeButtonE, new Integer(2));
		closeButtonE.setBounds(errCode.getWidth() - 20, 0, 30, 30);
		
		errCode.setVisible(false);
		
		succ = new drawRectangle(0, 0, 300,50, new Color(0, 255, 0, 150));
		succ.setBounds(width - 320, 0 + 20, 380, 40);
		
		layers.add(succ, new Integer(99));
		succ.setVisible(false);
		
		succCode = new JLabel("Succesfully saved!");
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
		
		succCode.add(closeButtonS, new Integer(2));
		closeButtonS.setBounds(succCode.getWidth() - 20, 0, 30, 30);
		
		layers.add(succCode, new Integer(100));
		succCode.setVisible(false);
		drawRectangle header = new drawRectangle(0, 0, width, 60, new Color(0, 0, 0, 150));
		header.setBounds(0,0, width, 150);
		layers.add(header, new Integer(95));
		
		
		
		JLabel logo = new JLabel("Profile");
		logo.setFont(new Font("Cochin", Font.PLAIN, 22));
		logo.setForeground(Color.white);
		logo.setBounds(width / 2 - 100,13, 200, 30);
		layers.add(logo, new Integer(100));

		
		
		
		JLayeredPane leftColumn = new JLayeredPane();
		
		
		leftColumn.setBounds((int)Math.round(label.getWidth() / 2 - 330), 60, 660, 30);
	
		
		JSpinner spinner = new JSpinner();
		JComboBox<Object> bd_1_ = new JComboBox<Object>();
		customButton saveNow = new customButton("Save", 140, 40, "images/save.png");
		if (isView) {
			leftColumn.setBounds((int)Math.round(label.getWidth() / 2 - 600), 60, 660, 30);
			JLayeredPane rightColumn = new JLayeredPane();
			
			
			rightColumn.setBounds((int)Math.round(label.getWidth() / 2 + 80), 60, 460, 30);
			
			JLayeredPane lg_ = new JLayeredPane();
			lg_.setSize(600, 40);
			JLabel bd_ = new JLabel("Status:");
			bd_.setFont(new Font("Cochin", Font.PLAIN, 17));
			bd_.setBounds(0, 0, 130, 30);
			lg_.add(bd_, new Integer(2));
			
			
			String[] status = new String[] {"0", "1"};
			
			
			bd_1_.setModel(new DefaultComboBoxModel<Object>(status));
			try {
				bd_1_.setSelectedIndex(userInfo.getInt("status"));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			bd_1_.setFont(new Font("Cochin", Font.PLAIN, 15));
			bd_1_.setBounds(150, 0, 130, 30);
			lg_.add(bd_1_, new Integer(2));
			
			rightColumn.add(lg_, new Integer(3));
			
			
			
			
			
			

			
			
			JLayeredPane lgD = new JLayeredPane();
			lgD.setSize(600, 40);
			
			JLabel bln = new JLabel("Balance:");
			bln.setFont(new Font("Cochin", Font.PLAIN, 17));
			bln.setBounds(0,0, 130, 30);
			lgD.add(bln, new Integer(2));
			
			
			spinner.setFont(new Font("Cochin", Font.PLAIN, 15));
			spinner.setBounds(150, 0, 70, 30);
			lgD.add(spinner, new Integer(2));
			
			try {
				spinner.setValue(userInfo.getInt("balance"));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			rightColumn.add(lgD, new Integer(3));
			
			
			saveNow.setPosition(50, 20);
			
			JLayeredPane lg12_ = new JLayeredPane();
			lg12_.setSize(300, 70);
			lg12_.add(saveNow.getButton(), new Integer(2));
			rightColumn.add(lg12_, new Integer(3));
			
			
			for (Component btn : saveNow.getButton().getComponents()) {
				btn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
				});
				
			}
			
			for (Component lfbr : rightColumn.getComponents()) {
				java.awt.Rectangle comp_b = lfbr.getBounds();
				
				lfbr.setBounds(lfbr.getBounds().x + 40, rightColumn.getBounds().height + 2, comp_b.width,  comp_b.height);
				rightColumn.setSize(rightColumn.getBounds().width, (int)(rightColumn.getBounds().height + comp_b.height + 2));
			
			}
			
			// code
			
			drawRectangle rct_ = new drawRectangle(0, 0, rightColumn.getWidth(), rightColumn.getSize().height, new Color(255, 255, 255, 150));
			rct_.setBounds(0, 0, rightColumn.getWidth(),rightColumn.getSize().height);
		
			rightColumn.setSize(rightColumn.getWidth(), rightColumn.getSize().height);
			
			rightColumn.add(rct_, new Integer(2));
			
			layers.add(rightColumn, new Integer(2));
			
		}
		
		leftColumn.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				java.awt.Rectangle bounds = leftColumn.getBounds();
				if (e.getWheelRotation() < 0) {
					if (bounds.y + bounds.getHeight() > height - 20) leftColumn.setBounds(bounds.x, bounds.y + (e.getWheelRotation() * 10), bounds.width, bounds.height );
				} else if (e.getWheelRotation() > 0) {
					
					if (bounds.y < 60) leftColumn.setBounds(bounds.x, bounds.y + (e.getWheelRotation() * 10), bounds.width, bounds.height );
				}
				
			}
		});
		
		JLayeredPane lg = new JLayeredPane();
		lg.setSize(600, 40);
		
		JLabel loginName = new JLabel("Login:");
		loginName.setFont(new Font("Cochin", Font.PLAIN, 17));
		loginName.setBounds(0,0, 130, 30);
		lg.add(loginName, new Integer(2));
		
		
		
		
		
		loginField = new JTextField();
		loginField.setFont(new Font("Cochin", Font.PLAIN, 15));
		loginField.setBounds(150, 0, 430, 30);
		lg.add(loginField, new Integer(2));
		
		try {
			loginField.setText(userInfo.getString("login"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		leftColumn.add(lg, new Integer(3));
		
		
		
		
		
		JLayeredPane lg2 = new JLayeredPane();
		lg2.setSize(600, 40);
		
		JLabel regName = new JLabel("Name:");
		regName.setFont(new Font("Cochin", Font.PLAIN, 17));
		regName.setBounds(0,0, 130, 30);
		lg2.add(regName, new Integer(2));
		
		JTextField nameField = new JTextField();
		nameField.setFont(new Font("Cochin", Font.PLAIN, 15));
		nameField.setBounds(150, 0, 430, 30);
		lg2.add(nameField, new Integer(2));
		
		try {
			nameField.setText(userInfo.getString("name"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		leftColumn.add(lg2, new Integer(3));
		
		
		JLayeredPane lg3 = new JLayeredPane();
		lg3.setSize(600, 40);
		
		JLabel surName = new JLabel("Surname:");
		surName.setFont(new Font("Cochin", Font.PLAIN, 17));
		surName.setBounds(0, 0, 130, 30);
		lg3.add(surName, new Integer(2));
		
		JTextField surnameField = new JTextField();
		surnameField.setFont(new Font("Cochin", Font.PLAIN, 15));
		surnameField.setBounds(150, 0, 430, 30);
		lg3.add(surnameField, new Integer(2));
		
		try {
			surnameField.setText(userInfo.getString("surname"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		leftColumn.add(lg3, new Integer(3));
		
		
		JLayeredPane lg4 = new JLayeredPane();
		lg4.setSize(600, 40);
		
		JLabel cardIdName = new JLabel("Card ID:");
		cardIdName.setFont(new Font("Cochin", Font.PLAIN, 17));
		cardIdName.setBounds(0, 0, 130, 30);
		lg4.add(cardIdName, new Integer(2));
		
		
		JTextField cardIdTf = new JTextField();
		cardIdTf.setFont(new Font("Cochin", Font.PLAIN, 15));
		cardIdTf.setBounds(150, 0, 230, 30);
		lg4.add(cardIdTf, new Integer(2));
		
		
		try {
			cardIdTf.setText(userInfo.getString("card_id"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		leftColumn.add(lg4, new Integer(3));
		
		JLayeredPane lg5 = new JLayeredPane();
		lg5.setSize(600, 40);
		JLabel telName = new JLabel("Tel. number:");
		telName.setFont(new Font("Cochin", Font.PLAIN, 17));
		telName.setBounds(0, 0, 130, 30);
		lg5.add(telName, new Integer(2));
		
		
		
		JTextField telTf = new JTextField();
		telTf.setFont(new Font("Cochin", Font.PLAIN, 15));
		telTf.setBounds(150, 0, 230, 30);
		lg5.add(telTf, new Integer(2));
		
		
		try {
			telTf.setText(userInfo.getString("tel"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		leftColumn.add(lg5, new Integer(3));
		
		
		JLayeredPane lg6 = new JLayeredPane();
		lg6.setSize(600, 40);
		JLabel gender = new JLabel("Gender:");
		gender.setFont(new Font("Cochin", Font.PLAIN, 17));
		gender.setBounds(0, 0, 130, 30);
		lg6.add(gender, new Integer(2));
		
		
		JComboBox<Object> gender_ = new JComboBox<Object>();
		String[] genderList = new String[] {"male", "female"};
		gender_.setModel(new DefaultComboBoxModel<Object>(genderList));
		gender_.setFont(new Font("Cochin", Font.PLAIN, 15));
		gender_.setBounds(150, 0, 130, 30);
		lg6.add(gender_, new Integer(2));
		
		
		try {
			gender_.setSelectedIndex(userInfo.getInt("gender") - 1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		leftColumn.add(lg6, new Integer(3));
		
		
		JLayeredPane lg7 = new JLayeredPane();
		lg7.setSize(600, 70);
		JLabel addrs = new JLabel("Address:");
		addrs.setFont(new Font("Cochin", Font.PLAIN, 17));
		addrs.setBounds(0, 0, 130, 30);
		lg7.add(addrs, new Integer(2));
		
		JTextArea addrs_ = new JTextArea();
		addrs_.setLineWrap(true);
		addrs_.setWrapStyleWord(true);
		addrs_.setRows(3);
		addrs_.setFont(new Font("Cochin", Font.PLAIN, 15));
		addrs_.setBounds(150, 0, 430, 60);
		lg7.add(addrs_, new Integer(2));
		
		try {
			addrs_.setText(userInfo.getString("address"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		leftColumn.add(lg7, new Integer(3));
		
		
		JLayeredPane lg8 = new JLayeredPane();
		lg8.setSize(600, 40);
		JLabel departm = new JLabel("Department:");
		departm.setFont(new Font("Cochin", Font.PLAIN, 17));
		departm.setBounds(0, 0, 130, 30);
		lg8.add(departm, new Integer(2));
		
		
		JComboBox<comboItem> departm_ = new JComboBox<>();
		
		
		
		ResultSet faculties = db.getAll("Select * from `departments`");
		try {
			departm_.addItem(new comboItem(" ", -1));
			while (faculties.next()) {
				departm_.addItem(new comboItem(faculties.getString("name"), faculties.getInt("id")));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			for (int d = 0; d < departm_.getItemCount(); d++) {
				if (departm_.getItemAt(d).equals(new comboItem("", userInfo.getInt("department_id")))){
					departm_.setSelectedItem(departm_.getItemAt(d));
					
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		departm_.setFont(new Font("Cochin", Font.PLAIN, 15));
		departm_.setBounds(150, 0, 430, 30);
		lg8.add(departm_, new Integer(2));
		
		leftColumn.add(lg8, new Integer(3));
		
		
		JLayeredPane lg9 = new JLayeredPane();
		lg9.setSize(600, 40);
		JLabel bd = new JLabel("Birth Date:");
		bd.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd.setBounds(0, 0, 130, 30);
		lg9.add(bd, new Integer(2));
		
		
		JComboBox<Object> bd_1 = new JComboBox<Object>();
		bd_1.setModel(new DefaultComboBoxModel<Object>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		bd_1.setFont(new Font("Cochin", Font.PLAIN, 15));
		bd_1.setBounds(150, 0, 130, 30);
		lg9.add(bd_1, new Integer(2));
		
		try {
			bd_1.setSelectedItem(userInfo.getString("birth_date").split("-")[2]);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JComboBox<Object> bd_2 = new JComboBox<Object>();
		String[] months = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		bd_2.setModel(new DefaultComboBoxModel<Object>(months));
		bd_2.setFont(new Font("Cochin", Font.PLAIN, 15));
		bd_2.setBounds(300, 0, 130, 30);
		lg9.add(bd_2, new Integer(2));
		
		try {
			bd_2.setSelectedIndex(Integer.parseInt(userInfo.getString("birth_date").split("-")[1]) - 1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JComboBox<Object> bd_3 = new JComboBox<Object>();
		bd_3.setModel(new DefaultComboBoxModel<Object>(new String[] {"2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950"}));
		bd_3.setFont(new Font("Cochin", Font.PLAIN, 15));
		bd_3.setBounds(450, 0, 130, 30);
		lg9.add(bd_3, new Integer(2));
		
		try {
			bd_3.setSelectedItem(userInfo.getString("birth_date").split("-")[0]);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		leftColumn.add(lg9, new Integer(3));
		
		
		JLayeredPane lg10 = new JLayeredPane();
		lg10.setSize(600, 40);
		
		JLabel passName = new JLabel("Password:");
		passName.setFont(new Font("Cochin", Font.PLAIN, 17));
		passName.setBounds(0, 0, 130, 30);
		lg10.add(passName, new Integer(2));
		
		JPasswordField passField = new JPasswordField();
		passField.setFont(new Font("Cochin", Font.PLAIN, 15));
		passField.setBounds(150, 0, 430, 30);
		lg10.add(passField, new Integer(2));
		
		try {
			passField.setText(userInfo.getString("password"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		leftColumn.add(lg10, new Integer(3));
		
		
		
		JLayeredPane lg11 = new JLayeredPane();
		lg11.setSize(600, 40);
		
		JLabel passName2 = new JLabel("Confirm Password:");
		passName2.setFont(new Font("Cochin", Font.PLAIN, 17));
		passName2.setBounds(0, 0, 130, 30);
		lg11.add(passName2, new Integer(2));
		
		JPasswordField passField2 = new JPasswordField();
		passField2.setFont(new Font("Cochin", Font.PLAIN, 15));
		passField2.setBounds(150, 0, 430, 30);
		lg11.add(passField2, new Integer(2));
		
		try {
			passField2.setText(userInfo.getString("password"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		leftColumn.add(lg11, new Integer(3));
		
		
		JLayeredPane lg12 = new JLayeredPane();
		lg12.setSize(600, 40);
		
		JLabel balN = new JLabel("Balance:");
		balN.setFont(new Font("Cochin", Font.PLAIN, 17));
		balN.setBounds(0, 0, 130, 30);
		lg12.add(balN, new Integer(2));
		
		
		JLabel balN2 = new JLabel();
		balN2.setFont(new Font("Cochin", Font.PLAIN, 17));
		balN2.setBounds(150, 0, 430, 30);
		lg12.add(balN2, new Integer(2));
		
		try {
			balN2.setText(userInfo.getString("balance"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		leftColumn.add(lg12, new Integer(3));
		
	
		
		customButton saveNow_ = new customButton("Save", 140, 40, "images/save.png");
		saveNow_.setPosition(50, 20);
		
		JLayeredPane lg12__ = new JLayeredPane();
		lg12__.setSize(300, 70);
		lg12__.add(saveNow_.getButton(), new Integer(2));
		leftColumn.add(lg12__, new Integer(3));
		
		for (Component bbtn : saveNow.getButton().getComponents()) {
			bbtn.addMouseListener(new MouseAdapter() {
				@SuppressWarnings("deprecation")
				@Override
				public void mouseReleased(MouseEvent e) {
					EditProfile_ upd = new EditProfile_(userId);
					if (loginField.getText().length() > 0) {
						int isLogin = upd.checkLoginExistance(loginField.getText());
						try {
							if (userInfo.getString("login").equals(loginField.getText())) isLogin = 1;
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (isLogin == 1) {
							if(nameField.getText().length() > 3) {
								if (surnameField.getText().length() > 3) {
									if (cardIdTf.getText().length() == 11) {
										int isCard = upd.checkCardIDExistance(cardIdTf.getText());
										try {
											if (userInfo.getString("card_id").equals(cardIdTf.getText())) isCard = 1;
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										if (isCard == 1) {
											if (passField.getText().length() > 5) {
												if (passField2.getText().equals(passField.getText())) {
													if (isView) {
														if (upd.updateNowAd(loginField.getText(), nameField.getText(),
																surnameField.getText(), cardIdTf.getText(),
																telTf.getText(), gender_.getSelectedIndex() + 1,
																addrs_.getText(),
																((comboItem) departm_.getSelectedItem()).getId(),
																bd_1.getSelectedIndex() + 1,
																bd_2.getSelectedIndex() + 1,
																bd_3.getSelectedItem().toString(), passField.getText(),
																bd_1_.getSelectedIndex(),
																Integer.parseInt(spinner.getValue().toString())) == 1) {
															showMessage("Success, You have registered successfuly!",
																	true);
														} else
															showMessage("Error, password confirmation is incorrect!",
																	false);

													} else {
														if (upd.updateNowSm(loginField.getText(), nameField.getText(), surnameField.getText(), cardIdTf.getText(), telTf.getText(), gender_.getSelectedIndex() + 1, addrs_.getText(), ((comboItem)departm_.getSelectedItem()).getId(), bd_1.getSelectedIndex() + 1, bd_2.getSelectedIndex() + 1, bd_3.getSelectedItem().toString(), passField.getText()) == 1) {
															showMessage("Success, You have registered successfuly!", true);
														} else showMessage("Error, password confirmation is incorrect!", false);
													}
												} else showMessage("Error, password must be more than 5 characters", false);
											} else showMessage("Error, password must be more than 5 characters", false);
										} else showMessage("Error, CardId exists", false);
									} else showMessage("Error, CardID must be 11 digits", false);
								} else showMessage("Error, surname must be more than 3 characters", false);
							} else showMessage("Error, name must be more than 3 characters", false);
						} else showMessage("Error, this login exists!", false);
					} else showMessage("Error, you havent entered login!", false);
				}
			});
		}
			
		
			
		
		for (Component btn : saveNow_.getButton().getComponents()) {
			btn.addMouseListener(new MouseAdapter() {
				@SuppressWarnings("deprecation")
				@Override
				public void mouseReleased(MouseEvent e) {
					EditProfile_ upd = new EditProfile_(userId);
					
					if (loginField.getText().length() > 0) {
						int isLogin = upd.checkLoginExistance(loginField.getText());
						try {
							if (userInfo.getString("login").equals(loginField.getText())) isLogin = 1;
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (isLogin == 1) {
							if(nameField.getText().length() > 3) {
								if (surnameField.getText().length() > 3) {
									if (cardIdTf.getText().length() == 11) {
										int isCard = upd.checkCardIDExistance(cardIdTf.getText());
										try {
											if (userInfo.getString("card_id").equals(cardIdTf.getText())) isCard = 1;
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										if (isCard == 1) {
											if (passField.getText().length() > 5) {
												if (passField2.getText().equals(passField.getText())) {
													if (isView) {
														if (upd.updateNowAd(loginField.getText(), nameField.getText(), surnameField.getText(), cardIdTf.getText(), telTf.getText(), gender_.getSelectedIndex() + 1, addrs_.getText(), ((comboItem)departm_.getSelectedItem()).getId(), bd_1.getSelectedIndex() + 1, bd_2.getSelectedIndex() + 1, bd_3.getSelectedItem().toString(), passField.getText(), bd_1_.getSelectedIndex(), Integer.parseInt(spinner.getValue().toString())) == 1) {
															showMessage("Success, You have registered successfuly!", true);
														} else showMessage("Error, password confirmation is incorrect!", false);
													
													} else {
														if (upd.updateNowSm(loginField.getText(), nameField.getText(), surnameField.getText(), cardIdTf.getText(), telTf.getText(), gender_.getSelectedIndex() + 1, addrs_.getText(), ((comboItem)departm_.getSelectedItem()).getId(), bd_1.getSelectedIndex() + 1, bd_2.getSelectedIndex() + 1, bd_3.getSelectedItem().toString(), passField.getText()) == 1) {
															showMessage("Success, You have registered successfuly!", true);
														} else showMessage("Error, password confirmation is incorrect!", false);
													}
												} else showMessage("Error, password must be more than 5 characters", false);
											} else showMessage("Error, password must be more than 5 characters", false);
										} else showMessage("Error, CardId exists", false);
									} else showMessage("Error, CardID must be 11 digits", false);
								} else showMessage("Error, surname must be more than 3 characters", false);
							} else showMessage("Error, name must be more than 3 characters", false);
						} else showMessage("Error, this login exists!", false);
					} else showMessage("Error, you havent entered login!", false);
				}
			});
			
		}
		
		for (Component lfbr : leftColumn.getComponents()) {
			java.awt.Rectangle comp_b = lfbr.getBounds();
			
			lfbr.setBounds(lfbr.getBounds().x + 40, leftColumn.getBounds().height + 2, comp_b.width,  comp_b.height);
			leftColumn.setSize(leftColumn.getBounds().width, (int)(leftColumn.getBounds().height + comp_b.height + 2));
		
		}
		
		// code
		
		drawRectangle rct = new drawRectangle(0, 0, leftColumn.getWidth(), (leftColumn.getSize().height < frame.getHeight() - 20)?(frame.getHeight() - 20 - 60):leftColumn.getSize().height, new Color(255, 255, 255, 150));
		rct.setBounds(0, 0, leftColumn.getWidth(),(leftColumn.getSize().height < frame.getHeight() - 20)?(frame.getHeight() - 20 - 60):leftColumn.getSize().height);
		System.out.println(frame.getHeight());
		leftColumn.setSize(leftColumn.getWidth(), (leftColumn.getSize().height < frame.getHeight() - 20)?(frame.getHeight() - 20 - 60):leftColumn.getSize().height);
		
		leftColumn.add(rct, new Integer(2));
		
		layers.add(leftColumn, new Integer(2));
		
		layers.setBounds(0, 0, width, height);
		
		
		panel.add(layers);
		//setBackground();
		
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
		Auth logIN = new Auth(login, password);
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
			
			main ndf = new main("newFrame", userId, isView);
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