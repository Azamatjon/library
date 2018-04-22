package main;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class viewBook {

	private JFrame frame;
	private JLabel label;
	private JPanel panel;
	//private Dimension screenSize;
	private String title;
	private String picture;
	private int width;
	private int height;
	private drawRectangle err;
	private JLabel errCode;
	private drawRectangle succ;
	private JLabel succCode;
	private int userId = -1;
	private int bookId;
	private boolean isView = false;
	private mysql db;
	/**
	 * Launch the application.
	 */
	public viewBook(String frameName, int bookId) {
		this.title = frameName;
		this.bookId = bookId;
		initialize();
	}
	
	public viewBook(String frameName, int bookId, int userId, boolean isView) {
		this.title = frameName;
		this.userId = userId;
		this.bookId = bookId;
		this.isView = isView;
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(title);
		
		width = 1000;
		if (userId != -1) height = 650;
		else height = 580;
		
		System.out.println(width + " " + height);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.setVisible(true);
		
		panel = new JPanel();
		panel.setLayout(null);
	
		frame.setContentPane(panel);
		
		JLayeredPane layers = new JLayeredPane();
		db = new mysql();
		
		
		ResultSet bookInfo = db.getRow("SELECT b.*, LEFT(b.published_year, 4) as `p_year`, a.name author_name, a.surname author_surname, p.name publisher_name, s.name subject_name, c.name category_name, (b.books_number - (SELECT COUNT(*) FROM issues WHERE book_id = b.id AND returned_date IS NULL)) available_books, i.date_return, i.returned_date, DATEDIFF(i.date_return, NOW()) issueDateDifference, d.name department_name, (SELECT COUNT(r.id) FROM ratings r WHERE r.book_id = b.id) all_rated, (SELECT SUM(r.points) FROM ratings r WHERE r.book_id = b.id) all_rated_number FROM books b LEFT JOIN authors a ON b.author_id = a.id LEFT JOIN publishers p ON b.publisher_id = p.id LEFT JOIN subjects s ON s.id = b.subject_id LEFT JOIN categories c ON c.id = b.category_id LEFT JOIN issues i ON i.book_id = b.id AND i.date_return IS NOT NULL AND i.returned_date IS NULL LEFT JOIN departments d ON d.id = b.department_id WHERE b.id = " + bookId);
		ResultSet userInfo;
		
		double ratinged = 0.0;
		try {
			ratinged = bookInfo.getInt("all_rated_number") / ((bookInfo.getInt("all_rated") == 0)?1:(bookInfo.getInt("all_rated") + 0.0));
		} catch (SQLException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		NumberFormat formatter = new DecimalFormat("#0.00");   
		
		
		
		label = new JLabel();
		
		if (picture != null) {
			ImageIcon image = new ImageIcon(picture);
			label.setIcon(image);
		}
		
		label.setBounds(0,0, width, height);
		layers.add(label, new Integer(1));
		
		
		drawRectangle rct = new drawRectangle(0, 0, width, height - 60, new Color(255, 255, 255, 150));
		rct.setBounds(0,60, width, height - 60);//300
		layers.add(rct, new Integer(2));
		
		
		
		err = new drawRectangle(0, 0, 300,50, new Color(255, 0, 0, 150));
		
		err.setBounds(width - 320, 0 + 20, 280, 40);
		layers.add(err, new Integer(98));
		err.setVisible(false);
		errCode = new JLabel();
		errCode.setForeground(new Color(255,255,255));
		errCode.setFont(new Font("Cochin", Font.PLAIN, 15));
		errCode.setBounds(width - 300, 0 + 20, 270, 40);
		layers.add(errCode, new Integer(99));
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
		
		layers.add(succ, new Integer(98));
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
		
		layers.add(succCode, new Integer(99));
		succCode.setVisible(false);
		
		
		
		/* header */
		
		drawRectangle header = new drawRectangle(0, 0, width, 60, new Color(0, 0, 0, 150));
		header.setBounds(0,0, width, 150);
		layers.add(header, new Integer(90));
		

		JLabel logo = new JLabel("Book view");
		logo.setFont(new Font("Cochin", Font.PLAIN, 19));
		logo.setForeground(Color.white);
		logo.setBounds(width / 2 - 75,13, 150, 30);
		layers.add(logo, new Integer(91));
		
		
		

		JLabel rating = new JLabel("Rating");
		rating.setFont(new Font("Cochin", Font.PLAIN, 14));
		rating.setForeground(Color.white);
		rating.setBounds(30,13, 100, 30);
		layers.add(rating, new Integer(91));
		
		JLabel rating_ = new JLabel( ((ratinged == 0)?"*":formatter.format(ratinged)) + "/5");
		rating_.setFont(new Font("Cochin", Font.PLAIN, 14));
		rating_.setForeground(Color.white);
		rating_.setBounds(100,13, 70, 30);
		layers.add(rating_, new Integer(91));
		if (userId != -1 && !isView) {
			JComboBox<Object> bd_1_ = new JComboBox<Object>();
			String[] points = new String[] {"1", "2", "3", "4", "5"};
			
			bd_1_.setModel(new DefaultComboBoxModel<Object>(points));
			Book rt = new Book();
		
			bd_1_.setSelectedIndex(rt.getMyRate(bookId, userId) - 1);
			bd_1_.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					rt.rateNow(bookId, userId, bd_1_.getSelectedIndex() + 1);
				}
			});
			bd_1_.setFont(new Font("Cochin", Font.PLAIN, 15));
			bd_1_.setBounds(170,13, 70, 30);
			layers.add(bd_1_, new Integer(91));
		}
		/* end header */
		
		
		
		
		
		JLayeredPane lg_ = new JLayeredPane();
		lg_.setSize(600, 40);
		JLabel bd_ = new JLabel("Book name:");
		bd_.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_.setBounds(0, 0, 148, 30);
		lg_.add(bd_, new Integer(2));
		
	
		JLabel bd_n = new JLabel();
		try {
			bd_n.setText(bookInfo.getString("book_name"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bd_n.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_n.setBounds(190, 0, 280, 30);
		lg_.add(bd_n, new Integer(2));
		
		lg_.setBounds(20, 80, width, 30);
		
		layers.add(lg_, new Integer(3));
		
		
		
		JLayeredPane lg_2 = new JLayeredPane();
		lg_2.setSize(600, 40);
		JLabel bd_a = new JLabel("Book author:");
		bd_a.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_a.setBounds(0, 0, 148, 30);
		lg_2.add(bd_a, new Integer(2));
		
	
		JLabel bd_3 = new JLabel();
		try {
			bd_3.setText(bookInfo.getString("author_name") + " " + bookInfo.getString("author_surname"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bd_3.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_3.setBounds(190, 0, 280, 30);
		lg_2.add(bd_3, new Integer(2));
		
		lg_2.setBounds(20, 120, width, 30);
		
		layers.add(lg_2, new Integer(3));
		
		
		
		
		JLayeredPane lg_3 = new JLayeredPane();
		lg_3.setSize(600, 40);
		JLabel bd_s = new JLabel("Subject:");
		bd_s.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_s.setBounds(0, 0, 148, 30);
		lg_3.add(bd_s, new Integer(2));
		
	
		JLabel bd_4 = new JLabel();
		try {
			bd_4.setText(bookInfo.getString("subject_name"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bd_4.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_4.setBounds(190, 0, 280, 30);
		lg_3.add(bd_4, new Integer(2));
		
		
		
		lg_3.setBounds(20, 160, width, 30);
		layers.add(lg_3, new Integer(3));
		
		
		

		JLayeredPane lg_4 = new JLayeredPane();
		JLabel bd_c = new JLabel("Category:");
		bd_c.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_c.setBounds(0, 0, 148, 30);
		lg_4.add(bd_c, new Integer(2));
		
	
		JLabel bd_5 = new JLabel();
		try {
			bd_5.setText(bookInfo.getString("category_name"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bd_5.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_5.setBounds(190, 0, 280, 30);
		lg_4.add(bd_5, new Integer(2));
		
		
		
		lg_4.setBounds(20, 200, width, 30);
		layers.add(lg_4, new Integer(3));
		
		
		
		JLayeredPane lg_20 = new JLayeredPane();
		JLabel bd_dp = new JLabel("Department:");
		bd_dp.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_dp.setBounds(0, 0, 148, 30);
		lg_20.add(bd_dp, new Integer(2));
		
	
		JLabel bd_25 = new JLabel();
		try {
			bd_25.setText(bookInfo.getString("department_name"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bd_25.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_25.setBounds(190, 0, 280, 30);
		lg_20.add(bd_25, new Integer(2));
		
		
		
		lg_20.setBounds(20, 240, width, 30);
		layers.add(lg_20, new Integer(3));
		
		
		
		
		JLayeredPane lg_5 = new JLayeredPane();
		JLabel bd_pb = new JLabel("Publisher:");
		bd_pb.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_pb.setBounds(0, 0, 148, 30);
		lg_5.add(bd_pb, new Integer(2));
		
	
		JLabel bd_6 = new JLabel();
		try {
			bd_6.setText(bookInfo.getString("publisher_name"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bd_6.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_6.setBounds(190, 0, 280, 30);
		lg_5.add(bd_6, new Integer(2));
		
		
		
		lg_5.setBounds(20, 280, width, 30);
		layers.add(lg_5, new Integer(3));
		
		
		
		JLayeredPane lg_6 = new JLayeredPane();
		JLabel bd_ps = new JLabel("Position:");
		bd_ps.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_ps.setBounds(0, 0, 148, 30);
		lg_6.add(bd_ps, new Integer(2));
		
		
		JLabel bd_psr = new JLabel("Row:");
		bd_psr.setFont(new Font("Cochin", Font.PLAIN, 16));
		bd_psr.setBounds(190, 0, 40, 30);
		lg_6.add(bd_psr, new Integer(2));
		
	
		JLabel bd_7 = new JLabel();
		try {
			bd_7.setText(bookInfo.getString("row"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bd_7.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_7.setBounds(230, 0, 80, 30);
		lg_6.add(bd_7, new Integer(2));
		
		
		
		JLabel bd_psc = new JLabel("Column:");
		bd_psc.setFont(new Font("Cochin", Font.PLAIN, 16));
		bd_psc.setBounds(270, 0, 80, 30);
		lg_6.add(bd_psc, new Integer(2));
		
	
		JLabel bd_8 = new JLabel();
		try {
			bd_8.setText(bookInfo.getString("column"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bd_8.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_8.setBounds(330, 0, 80, 30);
		lg_6.add(bd_8, new Integer(2));
		
		
		
		lg_6.setBounds(20, 320, width, 30);
		layers.add(lg_6, new Integer(3));
		
		JLayeredPane lg_7 = new JLayeredPane();
		JLabel bd_bn = new JLabel("Books number/available:");
		bd_bn.setToolTipText("Books number/available");
		bd_bn.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_bn.setBounds(0, 0, 188, 30);
		lg_7.add(bd_bn, new Integer(2));
		
	
		JLabel bd_9 = new JLabel();
		try {
			bd_9.setText(bookInfo.getString("books_number") + "/" + bookInfo.getString("available_books"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bd_9.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_9.setBounds(190, 0, 280, 30);
		lg_7.add(bd_9, new Integer(2));
		
		
		
		lg_7.setBounds(20, 360, width, 30);
		layers.add(lg_7, new Integer(3));
		
		
		JLayeredPane lg_8 = new JLayeredPane();
		JLabel bd_py = new JLabel("Published year:");
		bd_py.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_py.setBounds(0, 0, 148, 30);
		lg_8.add(bd_py, new Integer(2));
		
	
		JLabel bd_10 = new JLabel();
		try {
			bd_10.setText(bookInfo.getString("p_year"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bd_10.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_10.setBounds(190, 0, 280, 30);
		lg_8.add(bd_10, new Integer(2));
		
		
		
		lg_8.setBounds(20, 400, width, 30);
		layers.add(lg_8, new Integer(3));
		
		
		
		JLayeredPane lg_9 = new JLayeredPane();
		JLabel bd_bc = new JLabel("Barcode:");
		bd_bc.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_bc.setBounds(0, 0, 148, 30);
		lg_9.add(bd_bc, new Integer(2));
		
	
		JLabel bd_11 = new JLabel();
		try {
			bd_11.setText(bookInfo.getString("barcode"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bd_11.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_11.setBounds(190, 0, 280, 30);
		lg_9.add(bd_11, new Integer(2));
		
		
		
		lg_9.setBounds(20, 440, width, 30);
		layers.add(lg_9, new Integer(3));
		
		
		JLayeredPane lg_10 = new JLayeredPane();
		JLabel bd_prc = new JLabel("Price:");
		bd_prc.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_prc.setBounds(0, 0, 148, 30);
		lg_10.add(bd_prc, new Integer(2));
		
	
		JLabel bd_12 = new JLabel();
		try {
			bd_12.setText(bookInfo.getString("price"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bd_12.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_12.setBounds(190, 0, 280, 30);
		lg_10.add(bd_12, new Integer(2));
		
		
		
		lg_10.setBounds(20, 480, width, 30);
		layers.add(lg_10, new Integer(3));
		
		Book bk = new Book();
		boolean isIssued = bk.isIssued(bookId, userId);
		if (isIssued) {
			JLayeredPane lg_12 = new JLayeredPane();
			JLabel bd_rtn = new JLabel("Must return until:");
			bd_rtn.setFont(new Font("Cochin", Font.PLAIN, 17));
			bd_rtn.setBounds(0, 0, 148, 30);
			lg_12.add(bd_rtn, new Integer(2));
			
		
			
			JLabel bd_13 = new JLabel();
			try {
				String dtTmp;
				if (bookInfo.getInt("issueDateDifference") < 0) dtTmp = "<html>" + bookInfo.getTimestamp("date_return") + " (<span style=\"color: #ff0000; font-size: 10px;\">" + bookInfo.getInt("issueDateDifference") + " " + ((bookInfo.getInt("issueDateDifference") < -1)?"days":"day") + "</span>)</html>";
				else dtTmp = "<html>" + bookInfo.getTimestamp("date_return") + " (<span style=\"font-size: 10px;\">" + bookInfo.getInt("issueDateDifference") + " " + ((bookInfo.getInt("issueDateDifference") > 1)?"days":"day") + " remained</span>)</html>";
				bd_13.setText(dtTmp);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			bd_13.setFont(new Font("Cochin", Font.PLAIN, 17));
			bd_13.setBounds(190, 0, 480, 30);
			lg_12.add(bd_13, new Integer(2));
			
			lg_12.setBounds(20, 520, 480, 30);
			layers.add(lg_12, new Integer(3));
		}

		
		
		
	
		JLayeredPane lg_11 = new JLayeredPane();
		
		JLabel bd_13 = new JLabel();
		try {
			bd_13.setText("<html> <span style=\"max-width: 130px; margin-top: 0;\">" + bookInfo.getString("description") + "</span></html>");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bd_13.setFont(new Font("Cochin", Font.PLAIN, 17));
		bd_13.setBounds(190, 0, 380, 400);
		lg_11.add(bd_13, new Integer(2));
		
		lg_11.setBounds(350, 80, width, 400);
		layers.add(lg_11, new Integer(3));
		
		
		
		if (userId != -1) {
		
		JLayeredPane buttons = new JLayeredPane();
		buttons.setSize(0, 90);
		
		userInfo = db.getRow("SELECT * FROM users WHERE id = " + userId);
		try {
			JLabel usrInf = new JLabel(((isView)?"(view) ":"") + userInfo.getString("name") + " " + userInfo.getString("surname"));
			usrInf.setFont(new Font("Cochin", Font.PLAIN, 14));
			usrInf.setForeground(Color.white);
			usrInf.setBounds(width - 225,13, 250, 30);
			layers.add(usrInf, new Integer(91));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			if (userInfo.getInt("group_id") == 1 || userInfo.getInt("group_id") == 2) {
				customButton accReq = new customButton("Edit", 100, 40, "images/save.png");
			accReq.setPosition(0, 0);
			buttons.add(accReq.getButton(), new Integer(2));
			for (Component cmp : accReq.getButton().getComponents()) {
				cmp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						close();
						editBook eb = new editBook("Book editing", bookId);
						eb.setBackground("src/images/login-bg.jpg");
					}
				});
			}
			}
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		
		if (isView) {
			
			if (bk.isRequested(bookId, userId)) {
				customButton accReq = new customButton("ACCEPT REQUEST", 200, 40, "images/reg.png");
				accReq.setPosition(0, 0);
				buttons.add(accReq.getButton(), new Integer(2));
				for (Component cmp : accReq.getButton().getComponents()) {
					cmp.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (bk.acceptRequest(bookId, userId)) {
								accReq.getButton().setVisible(false);
								showMessage("Success, the request was accepted successfuly!", true);
							} else showMessage("Error, couldn't accept request!", false);
						}
					});
				}
			} else
				try {
					if (bookInfo.getInt("available_books") > 0 && !isIssued) {
						customButton accReq = new customButton("NEW ISSUE", 150, 40, "images/plus.png");
						accReq.setPosition(0, 0);
						buttons.add(accReq.getButton(), new Integer(2));
						for (Component cmp : accReq.getButton().getComponents()) {
							cmp.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									if (bk.newIssue(bookId, userId)) {
										accReq.getButton().setVisible(false);
										showMessage("Success, the new issue was created successfuly!", true);
									} else showMessage("Error, couldn't create new issue!", false);
								}
							});
						}
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			
			if (isIssued) {
				customButton accReq = new customButton("CLOSE ISSUE", 180, 40, "images/reg.png");
				accReq.setPosition(0, 0);
				buttons.add(accReq.getButton(), new Integer(2));
				for (Component cmp : accReq.getButton().getComponents()) {
					cmp.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (bk.acceptIssue(bookId, userId)) {
								accReq.getButton().setVisible(false);
								showMessage("Success, the issue was closed successfuly!", true);
							} else showMessage("Error, couldn't close issue!", false);
						}
					});
				}
				
				customButton accReq_ = new customButton("EXTEND ", 140, 40, "images/reload.png");
				accReq_.setPosition(0, 0);
				buttons.add(accReq_.getButton(), new Integer(2));
				for (Component cmp : accReq_.getButton().getComponents()) {
					cmp.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (bk.extend(bookId, userId)) {
								accReq_.getButton().setVisible(false);
								showMessage("Success, the return date was extended successfuly!", true);
							} else showMessage("Error, couldn't extend request!", false);
						}
					});
				}
				
			} 
			
			
		} else {
			if (isIssued) {
				int extN = bk.numExtend(bookId, userId);
				try {
					if (extN > 0 && bookInfo.getInt("issueDateDifference") <= 3 && bookInfo.getInt("issueDateDifference") >= 0) {
						customButton accReq = new customButton("EXTEND (" + extN + ")", 140, 40, "images/reload.png");
						accReq.setPosition(0, 0);
						buttons.add(accReq.getButton(), new Integer(2));
						for (Component cmp : accReq.getButton().getComponents()) {
							cmp.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									if (bk.extend(bookId, userId)) {
										accReq.getButton().setVisible(false);
										showMessage("Success, the new return date was extended successfuly!", true);
									} else showMessage("Error, couldn't extend return date!", false);
								}
							});
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (bk.isRequested(bookId, userId)) {
				customButton accReq = new customButton("UNREQUEST", 170, 40, "images/reg.png");
				accReq.setPosition(0, 0);
				buttons.add(accReq.getButton(), new Integer(2));
				
				for (Component cmp : accReq.getButton().getComponents()) {
					cmp.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (bk.unRequest(bookId, userId)) {
								accReq.getButton().setVisible(false);
								showMessage("Success, request deleted successfuly!", true);
							} else showMessage("Error, couldn't delete request!", false);
						}
					});
				}
				
				
			} else {
				
				customButton accReq = new customButton("Request NOW", 175, 40, "images/plus.png");
				accReq.setPosition(0, 0);
				buttons.add(accReq.getButton(), new Integer(2));
				for (Component cmp : accReq.getButton().getComponents()) {
					cmp.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (bk.newRequest(bookId, userId)) {
								accReq.getButton().setVisible(false);
								showMessage("Success, request sent successfuly!", true);
							} else showMessage("Error, couldn't send request!", false);
						}
					});
				}
			}
		}
		
		for (Component butt: buttons.getComponents()) {
			java.awt.Rectangle comp_b = butt.getBounds();
			butt.setBounds(buttons.getBounds().width + 10, 0, comp_b.width,  comp_b.height);
			buttons.setSize(buttons.getBounds().width + comp_b.width + 10, (int)(buttons.getBounds().height));
			System.out.println( " - " + butt.getBounds().x + ", " + butt.getBounds().y);
		}
		
		buttons.setBounds((int)Math.round(width / 2 - buttons.getWidth() / 2), 547, buttons.getWidth(), buttons.getHeight());
		layers.add(buttons, new Integer(3));
		
	}
		
		
		
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
			
			viewBook vb_ = new viewBook(title, bookId, userId, isView);
			vb_.setBackground("src/images/login-bg.jpg");
			close();
		} else {
			err.setVisible(true);
			errCode.setVisible(true);
			errCode.setText(message);
			succCode.setVisible(false);
			succ.setVisible(false);
		}
		
		
	}
}