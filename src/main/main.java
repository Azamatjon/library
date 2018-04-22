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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;





public class main {

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
	private int userId = -1;
	private int userGroup = -1;
	private JLayeredPane rightBar;
	private JLayeredPane rightBar_;
	private drawRectangle hoverBox;
	private drawRectangle rightSidebar;
	private boolean isView = false;
	private ResultSet userInfo;
	private JLayeredPane searchbar;
	private JLayeredPane uSearchbar;
	
	/**
	 * Launch the application.
	 */
	public main(String frameName) {
		this.title = frameName;
		initialize();
	}
	
	public main(String frameName, int userId) {
		this.title = frameName;
		this.userId = userId;
		initialize();
	}
	
	public main(String frameName, int userId, boolean isView) {
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
		height = (int)screenSize.getHeight() - 30;
		System.out.println(width + " " + height);
		frame.setSize(width, height);
		frame.setLocation(0, 0);
		if (isView) frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		else frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(width, height);
		frame.setContentPane(panel);
		
		JLayeredPane layers = new JLayeredPane();
		
		mysql db = new mysql();
		userInfo = db.getRow("SELECT * FROM users WHERE id = " + userId);
		
		try {
			userGroup = userInfo.getInt("group_id");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		label = new JLabel();
		if (picture != null) {
			ImageIcon image = new ImageIcon(picture);
			label.setIcon(image);
		}
		label.setBounds(0,0, width, height);
		layers.add(label, new Integer(1));
		
		
	
		
		/* header */
		
		drawRectangle header = new drawRectangle(0, 0, width, 60, new Color(0, 0, 0, 150));
		header.setBounds(0,0, width, 150);
		layers.add(header, new Integer(99));
		
		if (userId == -1) {
			JLabel auth = new JLabel("SignIn");
			auth.setFont(new Font("Cochin", Font.PLAIN, 17));
			auth.setForeground(Color.white);
			auth.setBounds(width - 100,10, 50, 30);
			layers.add(auth, new Integer(100));
			
			auth.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					Login login_ = new Login("LogIn");
					login_.setBackground("src/images/login-bg.jpg");
					close();
				}
			});
		} else {
			JLabel auth = new JLabel();
			try {
				auth.setText(((isView)?"(view) ":"") + userInfo.getString("name") + " " + userInfo.getString("surname"));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			auth.setFont(new Font("Cochin", Font.PLAIN, 17));
			auth.setForeground(Color.white);
			if (isView) auth.setBounds(width - 360,10, 250, 30);
			else auth.setBounds(width - 310,10, 200, 30);
			layers.add(auth, new Integer(100));
			
			JLabel lgout = new JLabel("(logout)");
			lgout.setFont(new Font("Cochin", Font.PLAIN, 17));
			lgout.setForeground(Color.white);
			lgout.setBounds(width - 80,10, 70, 30);
			layers.add(lgout, new Integer(100));
			lgout.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					Login login_ = new Login("LogIn");
					login_.setBackground("src/images/login-bg.jpg");
					close();
					
				}
			});
		}
		
		
		JLabel logo = new JLabel("Ala-Too International University");
		logo.setFont(new Font("Cochin", Font.PLAIN, 22));
		logo.setForeground(Color.white);
		logo.setBounds(width / 2 - 125,13, 350, 30);
		layers.add(logo, new Integer(100));
		
		JLabel lis = new JLabel("LIS");
		lis.setFont(new Font("Cochin", Font.PLAIN, 20));
		lis.setForeground(Color.white);
		lis.setBounds( 20,13, 350, 30);
		layers.add(lis, new Integer(100));
		
		JLabel logoIcon = new JLabel();
		logoIcon.setIcon(new ImageIcon("images/logo.png"));
		logoIcon.setBounds(width / 2 - 125 - 60, 5, 50, 50);
		logoIcon.setFont(new Font("Cochin", Font.PLAIN, 22));
		logoIcon.setForeground(Color.white);
		layers.add(logoIcon, new Integer(100));
		/* end header */
		
		
		
		/* left sidebar */
		
		JLayeredPane menuPG = new JLayeredPane();
		JLayeredPane facultiesPG = new JLayeredPane();
		JLayeredPane departmentsPG = new JLayeredPane();
		JLayeredPane subjectsPG = new JLayeredPane();
		JLayeredPane categoriesPG = new JLayeredPane();
		
		categoriesPG.setBounds(0, 60, (int)Math.round(width * 0.3) - 5, 20);
		
		JLabel catText = new JLabel("CATEGORIES");
		catText.setBounds((int)Math.round(categoriesPG.getWidth() / 2 - 65), 0, 130, 50);
		catText.setFont(new Font("Cochin", Font.PLAIN, 17));
		categoriesPG.add(catText, new Integer(3));
		
		drawRectangle hoverbx = new drawRectangle(0, 0, categoriesPG.getWidth() - 40, 30, new Color(255, 255, 255, 200)); 
		hoverbx.setSize(categoriesPG.getWidth() - 40, 30);
		hoverbx.setBounds(20, -200, categoriesPG.getWidth(), 30);
		categoriesPG.add(hoverbx,  new Integer(2));
		
		ResultSet categories_ = db.getAll("Select * from `categories`");
		
		try {
			
			while (categories_.next()) {
				JLabel catTemp = new JLabel(categories_.getString("name"));
				catTemp.setToolTipText(categories_.getString("id") + "%" + categories_.getString("parent_id") + "%" + categories_.getString("name"));
				catTemp.setBounds(20, 0, categoriesPG.getSize().width - 40, 30);
				catTemp.setFont(new Font("Cochin", Font.PLAIN, 15));
				catTemp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverbx.setBounds(hoverbx.getBounds().x, e.getComponent().getBounds().y, hoverbx.getWidth(), hoverbx.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverbx.setBounds(hoverbx.getBounds().x, -200, hoverbx.getWidth(), hoverbx.getHeight());
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						rightBar.setBounds(rightBar.getBounds().x, 60, rightBar.getBounds().width, rightBar.getBounds().height);
						ResultSet books = db.getAll("SELECT b.*, (SELECT COUNT(r.id) FROM ratings r WHERE r.book_id = b.id) all_rated, (SELECT SUM(r.points) FROM ratings r WHERE r.book_id = b.id) all_rated_number from books b WHERE b.category_id = " + catTemp.getToolTipText().split("%")[0]);
						updateBookSearchResults(books);
						
					}
				});
				categoriesPG.add(catTemp, new Integer(3));
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		for (Component lfbr : categoriesPG.getComponents()) {
			java.awt.Rectangle comp_b = lfbr.getBounds();
			
			lfbr.setBounds(lfbr.getBounds().x, categoriesPG.getBounds().height + 2, comp_b.width,  comp_b.height);
			categoriesPG.setSize(categoriesPG.getBounds().width, (int)(categoriesPG.getBounds().height + comp_b.height + 2));
		
		}
		
		hoverbx.setBounds(20, -200, categoriesPG.getWidth(), 30);
		
		
		drawRectangle leftSidebar = new drawRectangle(0, 0, categoriesPG.getSize().width, (categoriesPG.getSize().height < panel.getHeight() - 20)?(panel.getHeight() - 20):categoriesPG.getSize().height, new Color(255, 255, 255, 220));
		leftSidebar.setBounds(0, 0, categoriesPG.getSize().width, (categoriesPG.getSize().height < panel.getHeight() - 20)?(panel.getHeight() - 20):categoriesPG.getSize().height);//300
		categoriesPG.setSize(categoriesPG.getSize().width, (categoriesPG.getSize().height < panel.getHeight() - 20)?(panel.getHeight() - 20):categoriesPG.getSize().height);
		categoriesPG.add(leftSidebar, new Integer(2));
		
		
		ImageIcon img = new ImageIcon("images/back.png");
		JLabel imdg_ = new JLabel();
		imdg_.setBounds(40, catText.getBounds().y + 18, 16,16);
		imdg_.setIcon(img);
		imdg_.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				categoriesPG.setVisible(false);
				menuPG.setVisible(true);
			}
		});
		categoriesPG.add(imdg_, new Integer(3));
		
		
		categoriesPG.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				java.awt.Rectangle bounds = categoriesPG.getBounds();
				if (e.getWheelRotation() < 0) {
					if (bounds.y + bounds.getHeight() > height - 10) categoriesPG.setBounds(bounds.x, bounds.y + (e.getWheelRotation() * 10), bounds.width, bounds.height );
				} else if (e.getWheelRotation() > 0) {
					if (bounds.y < 60) categoriesPG.setBounds(bounds.x, bounds.y + (e.getWheelRotation() * 10), bounds.width, bounds.height );
				}
				
			}
		});
		
		layers.add(categoriesPG, new Integer(3));
		categoriesPG.setVisible(false);
		
		
		
		/* faculty */
		facultiesPG.setBounds(0, 60, (int)Math.round(width * 0.3) - 5, 20);
		
		JLabel faculText = new JLabel("FACULTIES");
		faculText.setBounds((int)Math.round(facultiesPG.getWidth() / 2 - 65), 0, 130, 50);
		faculText.setFont(new Font("Cochin", Font.PLAIN, 17));
		facultiesPG.add(faculText, new Integer(3));
		
		drawRectangle hoverbx_facul_ = new drawRectangle(0, 0, facultiesPG.getWidth() - 40, 30, new Color(255, 255, 255, 200)); 
		hoverbx_facul_.setSize(facultiesPG.getWidth() - 40, 30);
		hoverbx_facul_.setBounds(20, -200, facultiesPG.getWidth(), 30);
		facultiesPG.add(hoverbx_facul_,  new Integer(2));
		
		ResultSet faculties_ = db.getAll("Select * from `faculties`");
		
		try {
			
			while (faculties_.next()) {
				JLabel faculTemp = new JLabel(faculties_.getString("name"));
				faculTemp.setToolTipText(faculties_.getString("id") + "%" + "%" + faculties_.getString("name"));
				faculTemp.setBounds(20, 0, facultiesPG.getSize().width - 40, 30);
				faculTemp.setFont(new Font("Cochin", Font.PLAIN, 15));
				faculTemp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverbx_facul_.setBounds(hoverbx_facul_.getBounds().x, e.getComponent().getBounds().y, hoverbx_facul_.getWidth(), hoverbx_facul_.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverbx_facul_.setBounds(hoverbx_facul_.getBounds().x, -200, hoverbx_facul_.getWidth(), hoverbx_facul_.getHeight());
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						rightBar.setBounds(rightBar.getBounds().x, 60, rightBar.getBounds().width, rightBar.getBounds().height);
						ResultSet books = db.getAll("SELECT b.*, (SELECT COUNT(r.id) FROM ratings r WHERE r.book_id = b.id) all_rated, (SELECT SUM(r.points) FROM ratings r WHERE r.book_id = b.id) all_rated_number from books b WHERE b.department_id IN (SELECT id FROM `departments` WHERE faculty_id = " + faculTemp.getToolTipText().split("%")[0] + ") ");
						updateBookSearchResults(books);
						
					}
				});
				facultiesPG.add(faculTemp, new Integer(3));
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		for (Component lfbr : facultiesPG.getComponents()) {
			java.awt.Rectangle comp_b = lfbr.getBounds();
			
			lfbr.setBounds(lfbr.getBounds().x, facultiesPG.getBounds().height + 2, comp_b.width,  comp_b.height);
			facultiesPG.setSize(facultiesPG.getBounds().width, (int)(facultiesPG.getBounds().height + comp_b.height + 2));
		
		}
		
		hoverbx_facul_.setBounds(20, -200, facultiesPG.getWidth(), 30);
		
		
		drawRectangle leftFaculSidebar = new drawRectangle(0, 0, facultiesPG.getSize().width, (facultiesPG.getSize().height < panel.getHeight() - 20)?(panel.getHeight() - 20):facultiesPG.getSize().height, new Color(255, 255, 255, 220));
		leftFaculSidebar.setBounds(0, 0, facultiesPG.getSize().width, (facultiesPG.getSize().height < panel.getHeight() - 20)?(panel.getHeight() - 20):facultiesPG.getSize().height);//300
		facultiesPG.setSize(facultiesPG.getSize().width, (facultiesPG.getSize().height < panel.getHeight() - 20)?(panel.getHeight() - 20):facultiesPG.getSize().height);
		facultiesPG.add(leftFaculSidebar, new Integer(2));
		
		
		ImageIcon img_A = new ImageIcon("images/back.png");
		JLabel imdg__ = new JLabel();
		imdg__.setBounds(40, faculText.getBounds().y + 18, 16,16);
		imdg__.setIcon(img_A);
		imdg__.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				facultiesPG.setVisible(false);
				menuPG.setVisible(true);
			}
		});
		facultiesPG.add(imdg__, new Integer(3));
		
		
		facultiesPG.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				java.awt.Rectangle bounds = facultiesPG.getBounds();
				if (e.getWheelRotation() < 0) {
					if (bounds.y + bounds.getHeight() > height - 10) facultiesPG.setBounds(bounds.x, bounds.y + (e.getWheelRotation() * 10), bounds.width, bounds.height );
				} else if (e.getWheelRotation() > 0) {
					if (bounds.y < 60) facultiesPG.setBounds(bounds.x, bounds.y + (e.getWheelRotation() * 10), bounds.width, bounds.height );
				}
				
			}
		});
		
		layers.add(facultiesPG, new Integer(3));
		facultiesPG.setVisible(false);
		
		/* end faculty */
		
		
		
		
		/* departments */ 
		
		
		departmentsPG.setBounds(0, 60, (int)Math.round(width * 0.3) - 5, 20);
		
		JLabel deparText = new JLabel("DEPARTMENTS");
		deparText.setBounds((int)Math.round(departmentsPG.getWidth() / 2 - 65), 0, 130, 50);
		deparText.setFont(new Font("Cochin", Font.PLAIN, 17));
		departmentsPG.add(deparText, new Integer(3));
		
		drawRectangle hoverbx_depar_ = new drawRectangle(0, 0, departmentsPG.getWidth() - 40, 30, new Color(255, 255, 255, 200)); 
		hoverbx_depar_.setSize(departmentsPG.getWidth() - 40, 30);
		hoverbx_depar_.setBounds(20, -200, departmentsPG.getWidth(), 30);
		departmentsPG.add(hoverbx_depar_,  new Integer(2));
		
		ResultSet departments_ = db.getAll("Select * from `departments`");
		
		try {
			
			while (departments_.next()) {
				JLabel deparTemp = new JLabel(departments_.getString("name"));
				deparTemp.setToolTipText(departments_.getString("id") + "%" + "%" + departments_.getString("name"));
				deparTemp.setBounds(20, 0, departmentsPG.getSize().width - 40, 30);
				deparTemp.setFont(new Font("Cochin", Font.PLAIN, 15));
				deparTemp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverbx_depar_.setBounds(hoverbx_depar_.getBounds().x, e.getComponent().getBounds().y, hoverbx_depar_.getWidth(), hoverbx_depar_.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverbx_depar_.setBounds(hoverbx_depar_.getBounds().x, -200, hoverbx_depar_.getWidth(), hoverbx_depar_.getHeight());
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						rightBar.setBounds(rightBar.getBounds().x, 60, rightBar.getBounds().width, rightBar.getBounds().height);
						ResultSet books = db.getAll("SELECT b.*, (SELECT COUNT(r.id) FROM ratings r WHERE r.book_id = b.id) all_rated, (SELECT SUM(r.points) FROM ratings r WHERE r.book_id = b.id) all_rated_number from books b WHERE b.department_id = " + deparTemp.getToolTipText().split("%")[0]);
						updateBookSearchResults(books);
						
					}
				});
				departmentsPG.add(deparTemp, new Integer(3));
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		for (Component lfbr : departmentsPG.getComponents()) {
			java.awt.Rectangle comp_b = lfbr.getBounds();
			
			lfbr.setBounds(lfbr.getBounds().x, departmentsPG.getBounds().height + 2, comp_b.width,  comp_b.height);
			departmentsPG.setSize(departmentsPG.getBounds().width, (int)(departmentsPG.getBounds().height + comp_b.height + 2));
		
		}
		
		hoverbx_depar_.setBounds(20, -200, departmentsPG.getWidth(), 30);
		
		
		drawRectangle leftDeparSidebar = new drawRectangle(0, 0, departmentsPG.getSize().width, (departmentsPG.getSize().height < panel.getHeight() - 20)?(panel.getHeight() - 20):departmentsPG.getSize().height, new Color(255, 255, 255, 220));
		leftDeparSidebar.setBounds(0, 0, departmentsPG.getSize().width, (departmentsPG.getSize().height < panel.getHeight() - 20)?(panel.getHeight() - 20):departmentsPG.getSize().height);//300
		departmentsPG.setSize(departmentsPG.getSize().width, (departmentsPG.getSize().height < panel.getHeight() - 20)?(panel.getHeight() - 20):departmentsPG.getSize().height);
		departmentsPG.add(leftDeparSidebar, new Integer(2));
		
		
		ImageIcon img_ = new ImageIcon("images/back.png");
		JLabel imdgs_ = new JLabel();
		imdgs_.setBounds(40, deparText.getBounds().y + 18, 16,16);
		imdgs_.setIcon(img_);
		imdgs_.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				departmentsPG.setVisible(false);
				menuPG.setVisible(true);
			}
		});
		departmentsPG.add(imdgs_, new Integer(3));
		
		
		departmentsPG.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				java.awt.Rectangle bounds = departmentsPG.getBounds();
				if (e.getWheelRotation() < 0) {
					if (bounds.y + bounds.getHeight() > height - 10) departmentsPG.setBounds(bounds.x, bounds.y + (e.getWheelRotation() * 10), bounds.width, bounds.height );
				} else if (e.getWheelRotation() > 0) {
					if (bounds.y < 60) departmentsPG.setBounds(bounds.x, bounds.y + (e.getWheelRotation() * 10), bounds.width, bounds.height );
				}
				
			}
		});
		
		layers.add(departmentsPG, new Integer(3));
		departmentsPG.setVisible(false);
		
		
		/* end departments */
		
		/* subjects */
		
		subjectsPG.setBounds(0, 60, (int)Math.round(width * 0.3) - 5, 20);
		
		JLabel subjText = new JLabel("SUBJECTS");
		subjText.setBounds((int)Math.round(subjectsPG.getWidth() / 2 - 65), 0, 130, 50);
		subjText.setFont(new Font("Cochin", Font.PLAIN, 17));
		subjectsPG.add(subjText, new Integer(3));
		
		drawRectangle hoverbx_subj_ = new drawRectangle(0, 0, subjectsPG.getWidth() - 40, 30, new Color(255, 255, 255, 200)); 
		hoverbx_subj_.setSize(subjectsPG.getWidth() - 40, 30);
		hoverbx_subj_.setBounds(20, -200, subjectsPG.getWidth(), 30);
		subjectsPG.add(hoverbx_subj_,  new Integer(2));
		
		ResultSet subjects = db.getAll("Select * from `subjects`");
		
		try {
			
			while (subjects.next()) {
				JLabel subjectTemp = new JLabel(subjects.getString("name"));
				subjectTemp.setToolTipText(subjects.getString("id") + "%" + "%" + subjects.getString("name"));
				subjectTemp.setBounds(20, 0, subjectsPG.getSize().width - 40, 30);
				subjectTemp.setFont(new Font("Cochin", Font.PLAIN, 15));
				subjectTemp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverbx_subj_.setBounds(hoverbx_subj_.getBounds().x, e.getComponent().getBounds().y, hoverbx_subj_.getWidth(), hoverbx_subj_.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverbx_subj_.setBounds(hoverbx_subj_.getBounds().x, -200, hoverbx_subj_.getWidth(), hoverbx_subj_.getHeight());
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						rightBar.setBounds(rightBar.getBounds().x, 60, rightBar.getBounds().width, rightBar.getBounds().height);
						ResultSet books = db.getAll("SELECT b.*, (SELECT COUNT(r.id) FROM ratings r WHERE r.book_id = b.id) all_rated, (SELECT SUM(r.points) FROM ratings r WHERE r.book_id = b.id) all_rated_number from books b WHERE b.subject_id = " + subjectTemp.getToolTipText().split("%")[0]);
						updateBookSearchResults(books);
						
					}
				});
				subjectsPG.add(subjectTemp, new Integer(3));
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		for (Component lfbr : subjectsPG.getComponents()) {
			java.awt.Rectangle comp_b = lfbr.getBounds();
			
			lfbr.setBounds(lfbr.getBounds().x, subjectsPG.getBounds().height + 2, comp_b.width,  comp_b.height);
			subjectsPG.setSize(subjectsPG.getBounds().width, (int)(subjectsPG.getBounds().height + comp_b.height + 2));
		
		}
		
		hoverbx_subj_.setBounds(20, -200, subjectsPG.getWidth(), 30);
		
		
		drawRectangle leftSubjectSidebar = new drawRectangle(0, 0, subjectsPG.getSize().width, (subjectsPG.getSize().height < panel.getHeight() - 20)?(panel.getHeight() - 20):subjectsPG.getSize().height, new Color(255, 255, 255, 220));
		leftSubjectSidebar.setBounds(0, 0, subjectsPG.getSize().width, (subjectsPG.getSize().height < panel.getHeight() - 20)?(panel.getHeight() - 20):subjectsPG.getSize().height);//300
		subjectsPG.setSize(subjectsPG.getSize().width, (subjectsPG.getSize().height < panel.getHeight() - 20)?(panel.getHeight() - 20):subjectsPG.getSize().height);
		subjectsPG.add(leftSubjectSidebar, new Integer(2));
		
		
		ImageIcon imgsss_ = new ImageIcon("images/back.png");
		JLabel imdgsds_ = new JLabel();
		imdgsds_.setBounds(40, subjText.getBounds().y + 18, 16,16);
		imdgsds_.setIcon(imgsss_);
		imdgsds_.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				subjectsPG.setVisible(false);
				menuPG.setVisible(true);
			}
		});
		subjectsPG.add(imdgsds_, new Integer(3));
		
		
		subjectsPG.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				java.awt.Rectangle bounds = subjectsPG.getBounds();
				if (e.getWheelRotation() < 0) {
					if (bounds.y + bounds.getHeight() > height - 10) subjectsPG.setBounds(bounds.x, bounds.y + (e.getWheelRotation() * 10), bounds.width, bounds.height );
				} else if (e.getWheelRotation() > 0) {
					if (bounds.y < 60) subjectsPG.setBounds(bounds.x, bounds.y + (e.getWheelRotation() * 10), bounds.width, bounds.height );
				}
				
			}
		});
		
		layers.add(subjectsPG, new Integer(3));
		subjectsPG.setVisible(false);
		
		
		/* end subjects */
		
		menuPG.setBounds(0, 60, (int)Math.round(width * 0.3) - 5, 20);
		
		JLabel menText = new JLabel("MENU");
		menText.setBounds((int)Math.round(menuPG.getWidth() / 2 - 35), 0, 70, 50);
		menText.setFont(new Font("Cochin", Font.PLAIN, 17));
		menuPG.add(menText, new Integer(3));
		
		drawRectangle hoverbx__ = new drawRectangle(0, 0, menuPG.getWidth() - 40, 30, new Color(255, 255, 255, 200)); 
		hoverbx__.setSize(menuPG.getWidth() - 40, 30);
		
		menuPG.add(hoverbx__,  new Integer(2));
		
		JLabel catTemp = new JLabel("Categories");
		catTemp.setToolTipText("categories");
		catTemp.setBounds(20, 0, menuPG.getSize().width - 40, 30);
		catTemp.setFont(new Font("Cochin", Font.PLAIN, 15));
		catTemp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hoverbx__.setBounds(hoverbx__.getBounds().x, e.getComponent().getBounds().y, hoverbx__.getWidth(), hoverbx__.getHeight());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hoverbx__.setBounds(hoverbx__.getBounds().x, -200, hoverbx__.getWidth(), hoverbx__.getHeight());
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				menuPG.setVisible(false);
				categoriesPG.setVisible(true);
			}
		});
		menuPG.add(catTemp, new Integer(3));
		
		JLabel facultyTemp = new JLabel("Faculties");
		facultyTemp.setToolTipText("categories");
		facultyTemp.setBounds(20, 0, menuPG.getSize().width - 40, 30);
		facultyTemp.setFont(new Font("Cochin", Font.PLAIN, 15));
		facultyTemp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hoverbx__.setBounds(hoverbx__.getBounds().x, e.getComponent().getBounds().y, hoverbx__.getWidth(), hoverbx__.getHeight());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hoverbx__.setBounds(hoverbx__.getBounds().x, -200, hoverbx__.getWidth(), hoverbx__.getHeight());
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				menuPG.setVisible(false);
				facultiesPG.setVisible(true);
			}
		});
		menuPG.add(facultyTemp, new Integer(3));
		
		
		JLabel departTemp = new JLabel("Departments");
		departTemp.setToolTipText("Departments");
		departTemp.setBounds(20, 0, menuPG.getSize().width - 40, 30);
		departTemp.setFont(new Font("Cochin", Font.PLAIN, 15));
		departTemp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hoverbx__.setBounds(hoverbx__.getBounds().x, e.getComponent().getBounds().y, hoverbx__.getWidth(), hoverbx__.getHeight());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hoverbx__.setBounds(hoverbx__.getBounds().x, -200, hoverbx__.getWidth(), hoverbx__.getHeight());
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				menuPG.setVisible(false);
				departmentsPG.setVisible(true);
			}
		});
		menuPG.add(departTemp, new Integer(3));
		
		
		
		JLabel subjectTemp = new JLabel("Subjects");
		subjectTemp.setToolTipText("Subjects");
		subjectTemp.setBounds(20, 0, menuPG.getSize().width - 40, 30);
		subjectTemp.setFont(new Font("Cochin", Font.PLAIN, 15));
		subjectTemp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hoverbx__.setBounds(hoverbx__.getBounds().x, e.getComponent().getBounds().y, hoverbx__.getWidth(), hoverbx__.getHeight());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hoverbx__.setBounds(hoverbx__.getBounds().x, -200, hoverbx__.getWidth(), hoverbx__.getHeight());
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				menuPG.setVisible(false);
				subjectsPG.setVisible(true);
			}
		});
		menuPG.add(subjectTemp, new Integer(3));
		
		
		if (userId != -1) {
			JLabel issTemp = new JLabel("Current Issues");
			issTemp.setToolTipText("Current Issues");
			issTemp.setBounds(20, 0, menuPG.getSize().width - 40, 30);
			issTemp.setFont(new Font("Cochin", Font.PLAIN, 15));
			issTemp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					hoverbx__.setBounds(hoverbx__.getBounds().x, e.getComponent().getBounds().y, hoverbx__.getWidth(), hoverbx__.getHeight());
				}
				@Override
				public void mouseExited(MouseEvent e) {
					hoverbx__.setBounds(hoverbx__.getBounds().x, -200, hoverbx__.getWidth(), hoverbx__.getHeight());
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					rightBar.setBounds(rightBar.getBounds().x, 60, rightBar.getBounds().width, rightBar.getBounds().height);
					updateCurrentIssuesResults(db.getAll("Select i.*, DATEDIFF(i.date_return, NOW()) return_diff, b.book_name FROM issues i LEFT JOIN books b ON i.book_id = b.id WHERE i.date_return IS NOT NULL AND i.returned_date IS NULL AND user_id = "+ userId));
				}
			});
			menuPG.add(issTemp, new Integer(3));
			
			
			
			
			
			JLabel issHisTemp = new JLabel("Issues History");
			issHisTemp.setToolTipText("Issues History");
			issHisTemp.setBounds(20, 0, menuPG.getSize().width - 40, 30);
			issHisTemp.setFont(new Font("Cochin", Font.PLAIN, 15));
			issHisTemp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					hoverbx__.setBounds(hoverbx__.getBounds().x, e.getComponent().getBounds().y, hoverbx__.getWidth(), hoverbx__.getHeight());
				}
				@Override
				public void mouseExited(MouseEvent e) {
					hoverbx__.setBounds(hoverbx__.getBounds().x, -200, hoverbx__.getWidth(), hoverbx__.getHeight());
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						rightBar.setBounds(rightBar.getBounds().x, 60, rightBar.getBounds().width, rightBar.getBounds().height);
						updateIssuesHistoryResults(db.getAll("Select i.*, DATEDIFF(i.date_return, i.returned_date) return_diff, g.fine_amount, b.book_name FROM issues i LEFT JOIN books b ON i.book_id = b.id LEFT JOIN groups g ON g.id = " + userInfo.getInt("group_id") + " WHERE i.date_return IS NOT NULL AND i.returned_date IS NOT NULL AND user_id = "+ userId));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			menuPG.add(issHisTemp, new Integer(3));
			

			JLabel issTempReq = new JLabel("Requests");
			issTempReq.setToolTipText("Requests");
			issTempReq.setBounds(20, 0, menuPG.getSize().width - 40, 30);
			issTempReq.setFont(new Font("Cochin", Font.PLAIN, 15));
			issTempReq.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					hoverbx__.setBounds(hoverbx__.getBounds().x, e.getComponent().getBounds().y, hoverbx__.getWidth(), hoverbx__.getHeight());
				}
				@Override
				public void mouseExited(MouseEvent e) {
					hoverbx__.setBounds(hoverbx__.getBounds().x, -200, hoverbx__.getWidth(), hoverbx__.getHeight());
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					updateRequestedResults(db.getAll("Select i.*, b.book_name, b.`row` `row`, b.`column` `column`, b.id user_id  FROM issues i LEFT JOIN books b ON i.book_id = b.id WHERE i.date_return IS NULL AND i.returned_date IS NULL AND user_id = "+ userId));
				}
			});
			menuPG.add(issTempReq, new Integer(3));
			
			
			if (userGroup == 1 || userGroup == 2) {
				JLabel searBTempReq = new JLabel("Search books");
				searBTempReq.setToolTipText("Search for books");
				searBTempReq.setBounds(20, 0, menuPG.getSize().width - 40, 30);
				searBTempReq.setFont(new Font("Cochin", Font.PLAIN, 15));
				searBTempReq.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverbx__.setBounds(hoverbx__.getBounds().x, e.getComponent().getBounds().y, hoverbx__.getWidth(), hoverbx__.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverbx__.setBounds(hoverbx__.getBounds().x, -200, hoverbx__.getWidth(), hoverbx__.getHeight());
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						
						rightBar.remove(rightBar_);
						rightBar.remove(searchbar);
						rightBar.add(searchbar, new Integer(2));
						rightBar.remove(uSearchbar);
						rightBar.add(rightBar_, new Integer(2));
						rightBar.setBounds(rightBar.getBounds().x, 60, rightBar.getBounds().width, rightBar.getBounds().height);
						updateRightbar();
						//updateRequestedUserResults(db.getAll("Select i.*, b.user_name, b.`row` `row`, b.`column` `column`, b.id user_id  FROM issues i LEFT JOIN books b ON i.user_id = b.id WHERE i.date_return IS NULL AND i.returned_date IS NULL AND user_id = "+ userId));
					}
				});
				menuPG.add(searBTempReq, new Integer(3));
				
				
				JLabel searTempReq = new JLabel("Search users");
				searTempReq.setToolTipText("Search users");
				searTempReq.setBounds(20, 0, menuPG.getSize().width - 40, 30);
				searTempReq.setFont(new Font("Cochin", Font.PLAIN, 15));
				searTempReq.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverbx__.setBounds(hoverbx__.getBounds().x, e.getComponent().getBounds().y, hoverbx__.getWidth(), hoverbx__.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverbx__.setBounds(hoverbx__.getBounds().x, -200, hoverbx__.getWidth(), hoverbx__.getHeight());
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						rightBar.remove(rightBar_);
						rightBar.remove(uSearchbar);
						rightBar.add(uSearchbar, new Integer(2));
						rightBar.add(rightBar_, new Integer(2));
						rightBar.remove(searchbar);
						rightBar.setBounds(rightBar.getBounds().x, 60, rightBar.getBounds().width, rightBar.getBounds().height);
						updateRightbar();
						
						//updateRequestedUserResults(db.getAll("Select i.*, b.user_name, b.`row` `row`, b.`column` `column`, b.id user_id  FROM issues i LEFT JOIN books b ON i.user_id = b.id WHERE i.date_return IS NULL AND i.returned_date IS NULL AND user_id = "+ userId));
					}
				});
				menuPG.add(searTempReq, new Integer(3));
				
			}
			
			JLabel editProfileTemp = new JLabel("Edit Profile");
			editProfileTemp.setToolTipText("Edit Profile");
			editProfileTemp.setBounds(20, 0, menuPG.getSize().width - 40, 30);
			editProfileTemp.setFont(new Font("Cochin", Font.PLAIN, 15));
			editProfileTemp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					hoverbx__.setBounds(hoverbx__.getBounds().x, e.getComponent().getBounds().y, hoverbx__.getWidth(), hoverbx__.getHeight());
				}
				@Override
				public void mouseExited(MouseEvent e) {
					hoverbx__.setBounds(hoverbx__.getBounds().x, -200, hoverbx__.getWidth(), hoverbx__.getHeight());
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					editProfile fr = new editProfile("Editing Profile", userId, isView);
					fr.setBackground("src/images/login-bg.jpg");
				}
			});
			menuPG.add(editProfileTemp, new Integer(3));
			
			if (userGroup == 1 || userGroup == 2) {
				JLabel issTemp_ = new JLabel("All Current Issues");
				issTemp_.setToolTipText("All current Issues");
				issTemp_.setBounds(20, 0, menuPG.getSize().width - 40, 30);
				issTemp_.setFont(new Font("Cochin", Font.PLAIN, 15));
				issTemp_.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverbx__.setBounds(hoverbx__.getBounds().x, e.getComponent().getBounds().y, hoverbx__.getWidth(), hoverbx__.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverbx__.setBounds(hoverbx__.getBounds().x, -200, hoverbx__.getWidth(), hoverbx__.getHeight());
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						rightBar.setBounds(rightBar.getBounds().x, 60, rightBar.getBounds().width, rightBar.getBounds().height);
						updateAllCurrentIssuesResults(db.getAll("Select i.*, DATEDIFF(i.date_return, NOW()) return_diff, b.book_name, u.name user_name, u.surname user_surname FROM issues i LEFT JOIN books b ON i.book_id = b.id LEFT JOIN users u ON u.id = i.user_id WHERE i.date_return IS NOT NULL AND i.returned_date IS NULL"));
					}
				});
				menuPG.add(issTemp_, new Integer(3));
				
				JLabel issHisTemp_ = new JLabel("All issues History");
				issHisTemp_.setToolTipText("All issues History");
				issHisTemp_.setBounds(20, 0, menuPG.getSize().width - 40, 30);
				issHisTemp_.setFont(new Font("Cochin", Font.PLAIN, 15));
				issHisTemp_.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverbx__.setBounds(hoverbx__.getBounds().x, e.getComponent().getBounds().y, hoverbx__.getWidth(), hoverbx__.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverbx__.setBounds(hoverbx__.getBounds().x, -200, hoverbx__.getWidth(), hoverbx__.getHeight());
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						rightBar.setBounds(rightBar.getBounds().x, 60, rightBar.getBounds().width, rightBar.getBounds().height);
						updateAllIssuesHistoryResults(db.getAll("Select i.*, DATEDIFF(i.date_return, i.returned_date) return_diff, g.fine_amount, b.book_name, u.name user_name, u.surname user_surname FROM issues i LEFT JOIN books b ON i.book_id = b.id LEFT JOIN users u ON u.id = i.user_id LEFT JOIN groups g ON g.id = u.group_id WHERE i.date_return IS NOT NULL AND i.returned_date IS NOT NULL"));
					}
				});
				menuPG.add(issHisTemp_, new Integer(3));
				
				

				JLabel issTempReq_ = new JLabel("All requests");
				issTempReq_.setToolTipText("All requests");
				issTempReq_.setBounds(20, 0, menuPG.getSize().width - 40, 30);
				issTempReq_.setFont(new Font("Cochin", Font.PLAIN, 15));
				issTempReq_.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverbx__.setBounds(hoverbx__.getBounds().x, e.getComponent().getBounds().y, hoverbx__.getWidth(), hoverbx__.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverbx__.setBounds(hoverbx__.getBounds().x, -200, hoverbx__.getWidth(), hoverbx__.getHeight());
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						updateAllRequestedResults(db.getAll("Select i.*, b.book_name, b.`row` `row`, b.`column` `column`, u.name user_name, u.surname user_surname  FROM issues i LEFT JOIN books b ON i.book_id = b.id LEFT JOIN users u ON u.id = i.user_id WHERE i.date_return IS NULL AND i.returned_date IS NULL"));
					}
				});
				menuPG.add(issTempReq_, new Integer(3));
				
				JLabel issTempd_ = new JLabel("Add a new book");
				issTempd_.setToolTipText("Adding a new book to the database");
				issTempd_.setBounds(20, 0, menuPG.getSize().width - 40, 30);
				issTempd_.setFont(new Font("Cochin", Font.PLAIN, 15));
				issTempd_.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverbx__.setBounds(hoverbx__.getBounds().x, e.getComponent().getBounds().y, hoverbx__.getWidth(), hoverbx__.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverbx__.setBounds(hoverbx__.getBounds().x, -200, hoverbx__.getWidth(), hoverbx__.getHeight());
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						newBook bookClass = new newBook("Adding a new book");
						bookClass.setBackground("src/images/login-bg.jpg");
					}
				});
				menuPG.add(issTempd_, new Integer(3));
			}
			
		}
		
		
		for (Component lfbr : menuPG.getComponents()) {
			java.awt.Rectangle comp_b = lfbr.getBounds();
			
			lfbr.setBounds(lfbr.getBounds().x, menuPG.getBounds().height + 2, comp_b.width,  comp_b.height);
			menuPG.setSize(menuPG.getBounds().width, (int)(menuPG.getBounds().height + comp_b.height + 2));
		
		}
		
		hoverbx__.setBounds(20, -200, menuPG.getWidth(), 30);
		
		
		System.out.println("menu: " + menuPG.getSize().height + ", panel: " + panel.getHeight());
		drawRectangle leftSidebar_ = new drawRectangle(0, 0, menuPG.getSize().width, (menuPG.getSize().height < panel.getHeight() - 20)?(panel.getHeight() - 20):menuPG.getSize().height, new Color(255, 255, 255, 220));
		leftSidebar_.setBounds(0, 0, menuPG.getSize().width, (menuPG.getSize().height < panel.getHeight() - 20)?(panel.getHeight() - 20):menuPG.getSize().height);//200
		menuPG.setSize(menuPG.getSize().width, (menuPG.getSize().height < panel.getHeight() - 20)?(panel.getHeight() - 20):menuPG.getSize().height);
		menuPG.add(leftSidebar_, new Integer(2));
		
		
		layers.add(menuPG, new Integer(3));
		
		/* end left sidebar */
		
		 rightBar = new JLayeredPane();
		 rightBar.setBounds((int)Math.round(width * 0.3) + 5, 60, (int)Math.round(width * 0.7) - 5, 0);
		 layers.add(rightBar, new Integer(3));
		
		 /* searchbar */
		searchbar = new JLayeredPane();
		searchbar.setSize((int)Math.round(width * 0.7) - 5 - 10, 120);
		drawRectangle searchSidebar = new drawRectangle(0, 0, (int)Math.round(width * 0.7) - 5, 120, new Color(255, 255, 255, 200));
		searchSidebar.setSize(new Dimension((int)Math.round(width * 0.7) - 5 - 10, 120));
		searchbar.add(searchSidebar, new Integer(1));
		rightBar.add(searchbar, new Integer(2));
		
		/*
		JTextField textField = new JTextField();
		textField.setBounds(500, 7, 200, 30);
		searchbar.add(textField, new Integer(2));
		*/
		
		
		/* combo1 */
		ArrayList<comboItem> items_ = new ArrayList<>();
		
		ResultSet faculties = db.getAll("Select * from `faculties`");
		try {
			items_.add(new comboItem("All Faculties", -1));
			while (faculties.next()) {
				items_.add(new comboItem(faculties.getString("name"), faculties.getInt("id")));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		modalCombo combo = new modalCombo("Faculties",items_, 400,300, width, height);
		layers.add(combo.label, new Integer(99));
		JLayeredPane butt = combo.getButton(293, 40);
		combo.setButPosition(10, 10);
		searchbar.add(butt, new Integer(2));
		/* end combo1 */
		
		/* combo2 */
		ArrayList<comboItem> items2 = new ArrayList<>();
		items2.add(new comboItem("All Departments", -1));
		modalCombo combo2 = new modalCombo("Departments",items2, 400,300, width, height);
		layers.add(combo2.label, new Integer(99));
		JLayeredPane butt2 = combo2.getButton(293, 40);
		combo2.setButPosition(318, 10);
		searchbar.add(butt2, new Integer(2));
		/* end combo2 */
		
		/* combo1 listener to combo 2*/
		for (Component compon : butt.getComponents()) {
			compon.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					System.out.println(combo.selectedName);
					System.out.println("changed");
					ArrayList<comboItem> items_5 = new ArrayList<>();

					ResultSet dapartments_2 = db.getAll("Select * from `departments` where `faculty_id` = "+combo.selectedId);
					try {
						items_5.add(new comboItem("All Departments", -1));
						while (dapartments_2.next()) {
							items_5.add(new comboItem(dapartments_2.getString("name"), dapartments_2.getInt("id")));
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					combo2.upd(items_5);
					
				}
			});
		}
		/* end combo1 listener to combo 2*/
		
		
		
		/* combo4 */
		ArrayList<comboItem> combo4_ = new ArrayList<>();
		
		ResultSet categories = db.getAll("Select * from `categories`");
		try {
			combo4_.add(new comboItem("All categories", -1));
			while (categories.next()) {
				combo4_.add(new comboItem(categories.getString("name"), categories.getInt("id"), categories.getInt("parent_id")));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		modalCombo combo4 = new modalCombo("Categories",combo4_, 400,300, width, height);
		layers.add(combo4.label, new Integer(99));
		JLayeredPane butt4 = combo4.getButton(293, 40);
		combo4.setButPosition(626, 10);
		searchbar.add(butt4, new Integer(2));
		/* end combo4 */
		
		/* textfield */
		JTextField textField = new JTextField();
		textField.setBounds(10, 60, 560, 40);
		textField.setFont(new Font("Cochin", Font.PLAIN, 16));
		searchbar.add(textField, new Integer(2));
		
		/* end of textfield */
		
		/* combo5 */
		ArrayList<comboItem> combo5_ = new ArrayList<>();
		combo5_.add(new comboItem("All", -1));
		combo5_.add(new comboItem("Title", 1));
		combo5_.add(new comboItem("Author", 2));
		combo5_.add(new comboItem("Barcode", 3));
		combo5_.add(new comboItem("Publication Year", 4));
		combo5_.add(new comboItem("Publisher Name", 5));
		combo5_.add(new comboItem("Published Place", 6));
		combo5_.add(new comboItem("Contents/Summary", 7));
		
		
		modalCombo combo5 = new modalCombo("Booking",combo5_, 400,300, width, height);
		layers.add(combo5.label, new Integer(99));
		JLayeredPane butt5 = combo5.getButton(200, 40);
		combo5.setButPosition(580, 60);
		searchbar.add(butt5, new Integer(2));
		/* end combo5 */
		
		/* button */
		customButton searchbtt = new customButton("Search", 130, 40, "images/find.png");
		JLayeredPane searchbut = searchbtt.getButton();
		searchbtt.setPosition(790, 60);
		searchbar.add(searchbut, new Integer(2));
		
		for (Component itd : searchbut.getComponents()) {
			itd.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String queryFilter = "";
					if (combo.selectedId != -1) {
						queryFilter += " AND b.department_id IN (SELECT id FROM faculties WHERE id = " + combo.selectedId + ")";
					}
					if (combo2.selectedId != -1) {
						queryFilter += " AND b.department_id = " + combo2.selectedId;
					}
					if (combo4.selectedId != -1) {
						queryFilter += " AND b.category_id = " + combo4.selectedId;
					}
					if (textField.getText().length() >= 3) {
						if (combo5.selectedId != -1) {
							switch (combo5.selectedId) {
							case 1:
								queryFilter += " AND b.name LIKE '%" + textField.getText() + "%'";
								break;
							case 2:
								queryFilter += " AND (a.name LIKE '%" + textField.getText() + "%' OR a.surname LIKE '%" + textField.getText() + "%')";
								break;
							case 3:
								queryFilter += " AND b.barcode LIKE '%" + textField.getText() + "%'";
								break;
							case 4:
								queryFilter += " AND b.published_year LIKE '%" + textField.getText() + "%'";
								break;
							case 5:
								queryFilter += " AND p.name LIKE '%" + textField.getText() + "%'";
								break;
							case 6:
								queryFilter += " AND p.address LIKE '%" + textField.getText() + "%'";
								break;
							case 7:
								queryFilter += " AND b.description LIKE '%" + textField.getText() + "%'";
								break;
								
							default:
								break;
							}
						} else {
							queryFilter += " AND (b.book_name LIKE '%" + textField.getText() + "%' OR b.description LIKE '%" + textField.getText() + "%' OR a.name LIKE '%" + textField.getText() + "%' OR a.surname LIKE '%" + textField.getText() + "%' OR b.barcode LIKE '%" + textField.getText() + "%' OR b.published_year LIKE '%" + textField.getText() + "%' OR p.name LIKE '%" + textField.getText() + "%' OR p.address LIKE '%" + textField.getText() + "%' )";
						}
					}
					
					if (queryFilter.length() > 4) {
						queryFilter = " WHERE " + queryFilter.substring(4);
					} else queryFilter = "";
					System.out.println("SELECT b.*, (SELECT COUNT(r.id) FROM ratings r WHERE r.book_id = b.id) all_rated, (SELECT SUM(r.points) FROM ratings r WHERE r.book_id = b.id) all_rated_number FROM books b LEFT JOIN authors a ON a.id = b.author_id LEFT JOIN publishers p ON p.id = b.publisher_id " + queryFilter);
					ResultSet books = db.getAll("SELECT b.*, (SELECT COUNT(r.id) FROM ratings r WHERE r.book_id = b.id) all_rated, (SELECT SUM(r.points) FROM ratings r WHERE r.book_id = b.id) all_rated_number FROM books b LEFT JOIN authors a ON a.id = b.author_id LEFT JOIN publishers p ON p.id = b.publisher_id " + queryFilter);
					updateBookSearchResults(books);
				}
			});
		}
		
		/* end button */
		/* end searchbar */
		
		 /* user searchbar */
		uSearchbar = new JLayeredPane();
		uSearchbar.setSize((int)Math.round(width * 0.7) - 5 - 10, 60);
		drawRectangle uSearchSidebar = new drawRectangle(0, 0, (int)Math.round(width * 0.7) - 5, 60, new Color(255, 255, 255, 200));
		uSearchSidebar.setSize(new Dimension((int)Math.round(width * 0.7) - 5 - 10, 60));
		uSearchbar.add(uSearchSidebar, new Integer(1));
		/* end combo4 */
		
		/* textfield */
		JTextField textField_ = new JTextField();
		textField_.setBounds(10, 10, 560, 40);
		textField_.setFont(new Font("Cochin", Font.PLAIN, 16));
		uSearchbar.add(textField_, new Integer(2));
		
		/* end of textfield */
		
		/* combo5 */
		ArrayList<comboItem> combo5_3 = new ArrayList<>();
		combo5_3.add(new comboItem("All", -1));
		combo5_3.add(new comboItem("Name", 1));
		combo5_3.add(new comboItem("Surname", 2));
		combo5_3.add(new comboItem("Card ID", 3));
		
		modalCombo combo53 = new modalCombo("Type",combo5_3, 400,300, width, height);
		layers.add(combo53.label, new Integer(99));
		JLayeredPane butt53 = combo53.getButton(200, 40);
		combo53.setButPosition(580, 10);
		uSearchbar.add(butt53, new Integer(2));
		/* end combo5 */
		
		/* button */
		customButton searchbtt_u = new customButton("Search", 130, 40, "images/find.png");
		JLayeredPane searchbtt_ = searchbtt_u.getButton();
		searchbtt_u.setPosition(790, 10);
		uSearchbar.add(searchbtt_, new Integer(2));
		
		for (Component itd : searchbtt_.getComponents()) {
			itd.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String queryFilter = "";
					if (textField_.getText().length() >= 3) {
						if (combo5.selectedId != -1) {
							switch (combo53.selectedId) {
							case 1:
								queryFilter += " AND u.name LIKE '%" + textField_.getText() + "%'";
								break;
							case 2:
								queryFilter += " AND u.surname LIKE '%" + textField_.getText() + "%'";
								break;
							case 3:
								queryFilter += " AND u.card_id LIKE '%" + textField_.getText() + "%'";
								break;
								
							default:
								break;
							}
						} else {
							queryFilter += " AND (u.name LIKE '%" + textField_.getText() + "%' OR u.surname LIKE '%" + textField_.getText() + "%' OR u.card_id LIKE '%" + textField_.getText() + "%')";
						}
					}
					
					if (queryFilter.length() > 4) {
						queryFilter = " WHERE " + queryFilter.substring(4);
					} else queryFilter = "";
					System.out.println("SELECT u.*, (SELECT COUNT(*) FROM issues WHERE user_id = u.id) issues_number FROM users u" + queryFilter);
					ResultSet users = db.getAll("SELECT u.*, (SELECT COUNT(*) FROM issues WHERE user_id = u.id) issues_number FROM users u" + queryFilter);
					updateSearchUsersResults(users);
				}
			});
		}
		
		/* end button */
		/* end user searchbar */
		
	
		
		
		/* main sidebar */
		rightBar_ = new JLayeredPane();
		rightBar_.setSize((int)Math.round(width * 0.7) - 5, 0);
		
		
		
		rightBar.add(rightBar_, new Integer(2));
		
		
		
		hoverBox = new drawRectangle(0, 0, rightBar_.getWidth() - 10, 40, new Color(255, 255, 255, 200));
		hoverBox.setSize(rightBar_.getWidth() - 10, 40);
		hoverBox.setBounds(0, -200, rightBar_.getWidth(), 40);
		rightBar_.add(hoverBox,  new Integer(2));
		
		
		rightSidebar = new drawRectangle(0, 0, rightBar_.getSize().width - 10, rightBar_.getSize().height, new Color(255, 255, 255, 200));
		rightSidebar.setSize(rightBar_.getSize().width, rightBar_.getSize().height);
		rightBar_.add(rightSidebar, new Integer(1));
		/* end main sidebar */
		
		
		/* debug scrollbar */
		
		for (Component comp : rightBar.getComponents()) {
			java.awt.Rectangle comp_b = comp.getBounds();
			
			//System.out.println("comp y: " + comp.getBounds().y + ", width: " + comp_b.width + ", height: " + comp_b.height + ", side: height" + rightBar_.getHeight());
			//System.out.println(rightBar_.height + comp_b.height + 10);

			comp.setBounds(0, rightBar.getBounds().height + 10, comp_b.width,  comp_b.height);
			rightBar.setSize(rightBar.getBounds().width, (int)(rightBar.getBounds().height + comp_b.height + 10));
			rightSidebar.setSize(rightSidebar.getBounds().width, (int)(rightBar.getBounds().height));
		}
		
		/* end debug */
		
		
		
		rightBar.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				java.awt.Rectangle bounds = rightBar.getBounds();
				if (e.getWheelRotation() < 0) {
					if (bounds.y + bounds.getHeight() > height - 10) rightBar.setBounds(bounds.x, bounds.y + (e.getWheelRotation() * 10), bounds.width, bounds.height );
				} else if (e.getWheelRotation() > 0) {
					if (bounds.y < 60) rightBar.setBounds(bounds.x, bounds.y + (e.getWheelRotation() * 10), bounds.width, bounds.height );
				}
				
			}
		});

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
		panel.updateUI();
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
			this.close();
			main ndf = new main("newFrame");
			ndf.setBackground("src/images/login-bg.jpg");
			
		} else {
			err.setVisible(true);
			errCode.setVisible(true);
			errCode.setText(message);
			succCode.setVisible(false);
			succ.setVisible(false);
		}
	}
	public void updateBookSearchResults(ResultSet books) {
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				rightBar_.remove(cmpn);
			}
		}

		try {
			while (books.next()) {
				JLayeredPane user_ = new JLayeredPane();
				user_.setSize(rightBar_.getWidth(),  40);
				
				JLabel book = new JLabel(books.getString("book_name"));
				book.setBounds(20, user_.getHeight() / 2 - 10, 500, 20);
				user_.add(book, new Integer(2));
				
				double ratinged = books.getInt("all_rated_number") / ((books.getInt("all_rated") == 0)?1:(books.getInt("all_rated") + 0.0));
				NumberFormat formatter = new DecimalFormat("#0.00");   
				JLabel rating = new JLabel("Rating: " + ((ratinged == 0)?"*":formatter.format(ratinged)) + "/5");
				rating.setBounds(750, 10, 100, 20);
				user_.add(rating, new Integer(2));
				
				JLabel row = new JLabel("Row: " +  books.getInt("row"));
				row.setBounds(850, 0, 100, 20);
				user_.add(row, new Integer(2));
				
				JLabel column = new JLabel("Column: " +  books.getInt("column"));
				column.setBounds(850, 20, 100, 20);
				user_.add(column, new Integer(2));
				
				
				user_.setToolTipText(books.getString("id"));
				rightBar_.add(user_, new Integer(3));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rightBar_.setBounds(rightBar_.getBounds().x, rightBar_.getBounds().y, rightBar_.getBounds().width, 0);
		
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				cmpn.setBounds(0, rightBar_.getBounds().height + 0, cmpn.getWidth(),  cmpn.getHeight());
				rightBar_.setSize(rightBar_.getBounds().width, (int)(rightBar_.getBounds().height + cmpn.getHeight() + 0));
				cmpn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverBox.setBounds(0, e.getComponent().getBounds().y, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverBox.setBounds(0, -200, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						if (userId == -1) {
							viewBook fr = new viewBook("Book view", Integer.parseInt(((JLayeredPane)cmpn).getToolTipText()));
							fr.setBackground("src/images/login-bg.jpg");
						} else {
							viewBook fr = new viewBook("Book view", Integer.parseInt(((JLayeredPane)cmpn).getToolTipText()), userId, isView);
							fr.setBackground("src/images/login-bg.jpg");
						}
						
					}
				});
			}
		}
		
		rightBar.setBounds(rightBar.getBounds().x, rightBar.getBounds().y, rightBar.getBounds().width, 0);
		for (Component comp : rightBar.getComponents()) {
			java.awt.Rectangle comp_b = comp.getBounds();
			
			//System.out.println("comp y: " + comp.getBounds().y + ", width: " + comp_b.width + ", height: " + comp_b.height + ", side: height" + rightBar_.getHeight());
			//System.out.println(rightBar_.height + comp_b.height + 10);

			comp.setBounds(0, rightBar.getBounds().height + 10, comp_b.width,  comp_b.height);
			rightBar.setSize(rightBar.getBounds().width, (int)(rightBar.getBounds().height + comp_b.height + 10));
		
		}
		System.out.println(rightBar.getBounds().height);
		rightBar_.remove(rightSidebar);
		rightSidebar = new drawRectangle(0, 0, rightBar_.getSize().width - 10, rightBar_.getSize().height, new Color(255, 255, 255, 200));
		rightSidebar.setSize(rightBar_.getSize().width - 10, rightBar_.getSize().height);
		rightBar_.add(rightSidebar, new Integer(1));
		
		rightBar.update(rightBar.getGraphics());
		
	}
	
	
	public void updateRightbar() {
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				rightBar_.remove(cmpn);
			}
		}
		rightBar_.setBounds(rightBar_.getBounds().x, rightBar_.getBounds().y, rightBar_.getBounds().width, 0);
		
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				cmpn.setBounds(0, rightBar_.getBounds().height + 0, cmpn.getWidth(),  cmpn.getHeight());
				rightBar_.setSize(rightBar_.getBounds().width, (int)(rightBar_.getBounds().height + cmpn.getHeight() + 0));
				cmpn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverBox.setBounds(0, e.getComponent().getBounds().y, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverBox.setBounds(0, -200, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						if (userId == -1) {
							viewBook fr = new viewBook("Book view", Integer.parseInt(((JLayeredPane)cmpn).getToolTipText()));
							fr.setBackground("src/images/login-bg.jpg");
						} else {
							viewBook fr = new viewBook("Book view", Integer.parseInt(((JLayeredPane)cmpn).getToolTipText()), userId, isView);
							fr.setBackground("src/images/login-bg.jpg");
						}
						
					}
				});
			}
		}
		
		rightBar.setBounds(rightBar.getBounds().x, rightBar.getBounds().y, rightBar.getBounds().width, 0);
		for (Component comp : rightBar.getComponents()) {
			java.awt.Rectangle comp_b = comp.getBounds();
			
			//System.out.println("comp y: " + comp.getBounds().y + ", width: " + comp_b.width + ", height: " + comp_b.height + ", side: height" + rightBar_.getHeight());
			//System.out.println(rightBar_.height + comp_b.height + 10);

			comp.setBounds(0, rightBar.getBounds().height + 10, comp_b.width,  comp_b.height);
			rightBar.setSize(rightBar.getBounds().width, (int)(rightBar.getBounds().height + comp_b.height + 10));
		
		}
		System.out.println(rightBar.getBounds().height);
		rightBar_.remove(rightSidebar);
		rightSidebar = new drawRectangle(0, 0, rightBar_.getSize().width - 10, rightBar_.getSize().height, new Color(255, 255, 255, 200));
		rightSidebar.setSize(rightBar_.getSize().width - 10, rightBar_.getSize().height);
		rightBar_.add(rightSidebar, new Integer(1));
		
		rightBar.update(rightBar.getGraphics());
		
	}
	
	public void updateRequestedResults(ResultSet books) {
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				rightBar_.remove(cmpn);
			}
		}

		try {
			while (books.next()) {
				JLayeredPane user_ = new JLayeredPane();
				user_.setSize(rightBar_.getWidth(),  40);
				
				JLabel book = new JLabel(books.getString("book_name"));
				book.setBounds(20, user_.getHeight() / 2 - 10, 500, 20);
				user_.add(book, new Integer(2));
				
				JLabel rating = new JLabel("Requested at " + books.getString("date_issue"));
				rating.setBounds(550, 10, 250, 20);
				user_.add(rating, new Integer(2));
				
				JLabel row = new JLabel("Row: " +  books.getInt("row"));
				row.setBounds(850, 0, 100, 20);
				user_.add(row, new Integer(2));
				
				JLabel column = new JLabel("Column: " +  books.getInt("column"));
				column.setBounds(850, 20, 100, 20);
				user_.add(column, new Integer(2));
				
				
				user_.setToolTipText(books.getString("book_id"));
				rightBar_.add(user_, new Integer(3));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rightBar_.setBounds(rightBar_.getBounds().x, rightBar_.getBounds().y, rightBar_.getBounds().width, 0);
		
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				cmpn.setBounds(0, rightBar_.getBounds().height + 0, cmpn.getWidth(),  cmpn.getHeight());
				rightBar_.setSize(rightBar_.getBounds().width, (int)(rightBar_.getBounds().height + cmpn.getHeight() + 0));
				cmpn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverBox.setBounds(0, e.getComponent().getBounds().y, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverBox.setBounds(0, -200, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						if (userId == -1) {
							viewBook fr = new viewBook("Book view", Integer.parseInt(((JLayeredPane)cmpn).getToolTipText()));
							fr.setBackground("src/images/login-bg.jpg");
						} else {
							viewBook fr = new viewBook("Book view", Integer.parseInt(((JLayeredPane)cmpn).getToolTipText()), userId, isView);
							fr.setBackground("src/images/login-bg.jpg");
						}
						
					}
				});
			}
		}
		
		rightBar.setBounds(rightBar.getBounds().x, rightBar.getBounds().y, rightBar.getBounds().width, 0);
		for (Component comp : rightBar.getComponents()) {
			java.awt.Rectangle comp_b = comp.getBounds();
			
			//System.out.println("comp y: " + comp.getBounds().y + ", width: " + comp_b.width + ", height: " + comp_b.height + ", side: height" + rightBar_.getHeight());
			//System.out.println(rightBar_.height + comp_b.height + 10);

			comp.setBounds(0, rightBar.getBounds().height + 10, comp_b.width,  comp_b.height);
			rightBar.setSize(rightBar.getBounds().width, (int)(rightBar.getBounds().height + comp_b.height + 10));
		
		}
		System.out.println(rightBar.getBounds().height);
		rightBar_.remove(rightSidebar);
		rightSidebar = new drawRectangle(0, 0, rightBar_.getSize().width - 10, rightBar_.getSize().height, new Color(255, 255, 255, 200));
		rightSidebar.setSize(rightBar_.getSize().width - 10, rightBar_.getSize().height);
		rightBar_.add(rightSidebar, new Integer(1));
		
		rightBar.update(rightBar.getGraphics());
		
	}
	
	
	public void updateAllRequestedResults(ResultSet books) {
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				rightBar_.remove(cmpn);
			}
		}

		try {
			while (books.next()) {
				JLayeredPane user_ = new JLayeredPane();
				user_.setSize(rightBar_.getWidth(),  40);
				
				JLabel book = new JLabel(books.getString("book_name"));
				book.setBounds(20, user_.getHeight() / 2 - 10, 500, 20);
				user_.add(book, new Integer(2));
				
				JLabel rating = new JLabel("Requested at " + books.getString("date_issue"));
				rating.setBounds(550, 0, 250, 20);
				user_.add(rating, new Integer(2));
				
				JLabel rating_ = new JLabel("User: " + books.getString("user_name") + " " + books.getString("user_surname"));
				rating_.setBounds(550, 20, 250, 20);
				user_.add(rating_, new Integer(2));
				
				JLabel row = new JLabel("Row: " +  books.getInt("row"));
				row.setBounds(850, 0, 100, 20);
				user_.add(row, new Integer(2));
				
				JLabel column = new JLabel("Column: " +  books.getInt("column"));
				column.setBounds(850, 20, 100, 20);
				user_.add(column, new Integer(2));
				
				
				user_.setToolTipText(books.getString("book_id") + "%" + books.getString("user_id"));
				rightBar_.add(user_, new Integer(3));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rightBar_.setBounds(rightBar_.getBounds().x, rightBar_.getBounds().y, rightBar_.getBounds().width, 0);
		
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				cmpn.setBounds(0, rightBar_.getBounds().height + 0, cmpn.getWidth(),  cmpn.getHeight());
				rightBar_.setSize(rightBar_.getBounds().width, (int)(rightBar_.getBounds().height + cmpn.getHeight() + 0));
				cmpn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverBox.setBounds(0, e.getComponent().getBounds().y, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverBox.setBounds(0, -200, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						
						viewBook fr = new viewBook("Book view", Integer.parseInt(((JLayeredPane)cmpn).getToolTipText().split("%")[0]), Integer.parseInt(((JLayeredPane)cmpn).getToolTipText().split("%")[1]), true);
						fr.setBackground("src/images/login-bg.jpg");
						
					}
				});
			}
		}
		
		rightBar.setBounds(rightBar.getBounds().x, rightBar.getBounds().y, rightBar.getBounds().width, 0);
		for (Component comp : rightBar.getComponents()) {
			java.awt.Rectangle comp_b = comp.getBounds();
			
			//System.out.println("comp y: " + comp.getBounds().y + ", width: " + comp_b.width + ", height: " + comp_b.height + ", side: height" + rightBar_.getHeight());
			//System.out.println(rightBar_.height + comp_b.height + 10);

			comp.setBounds(0, rightBar.getBounds().height + 10, comp_b.width,  comp_b.height);
			rightBar.setSize(rightBar.getBounds().width, (int)(rightBar.getBounds().height + comp_b.height + 10));
		
		}
		System.out.println(rightBar.getBounds().height);
		rightBar_.remove(rightSidebar);
		rightSidebar = new drawRectangle(0, 0, rightBar_.getSize().width - 10, rightBar_.getSize().height, new Color(255, 255, 255, 200));
		rightSidebar.setSize(rightBar_.getSize().width - 10, rightBar_.getSize().height);
		rightBar_.add(rightSidebar, new Integer(1));
		
		rightBar.update(rightBar.getGraphics());
		
	}
	
	
	public void updateIssuesHistoryResults(ResultSet books) {
		
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				rightBar_.remove(cmpn);
			}
		}

		try {
			while (books.next()) {
				JLayeredPane user_ = new JLayeredPane();
				user_.setSize(rightBar_.getWidth(),  40);
				
				JLabel book = new JLabel(books.getString("book_name"));
				book.setBounds(20, user_.getHeight() / 2 - 10, 450, 20);
				user_.add(book, new Integer(2));
			
				
				JLabel iop = new JLabel("Opened: " +  books.getTimestamp("date_issue"));
				iop.setBounds(500, 0, 300, 20);
				user_.add(iop, new Integer(2));
				
				JLabel icl = new JLabel("Closed: " +  books.getTimestamp("returned_date"));
				icl.setBounds(500, 20, 300, 20);
				user_.add(icl, new Integer(2));
				
				
				JLabel rd = new JLabel();
				if (books.getInt("return_diff") < 0) {
					rd.setText("<html>Returned: <span style=\"color:red\">" +  books.getInt("return_diff") + "</span> days after</html>");
				} else {
					rd.setText("Returned: " +  books.getInt("return_diff") + " day/s before");
				}
				
				rd.setBounds(750, 0, 300, 20);
				
				user_.add(rd, new Integer(2));
				
				
				NumberFormat formatter = new DecimalFormat("#0.00");  
				
				JLabel fine_ = new JLabel();
				if (books.getInt("return_diff") < 0) {
					fine_.setText("<html>Fine: <span style=\"color:red\">" +  formatter.format(books.getInt("return_diff") * books.getDouble("fine_amount")) + "</span> som</html>");
				} else {
					fine_.setText("Fine: 0 som");
				}
				
				fine_.setBounds(750, 15, 300, 20);
				
				
				user_.add(fine_, new Integer(2));
				
				
				user_.setToolTipText(books.getString("book_id"));
				rightBar_.add(user_, new Integer(3));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rightBar_.setBounds(rightBar_.getBounds().x, rightBar_.getBounds().y, rightBar_.getBounds().width, 0);
		
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				cmpn.setBounds(0, rightBar_.getBounds().height + 0, cmpn.getWidth(),  cmpn.getHeight());
				rightBar_.setSize(rightBar_.getBounds().width, (int)(rightBar_.getBounds().height + cmpn.getHeight() + 0));
				cmpn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverBox.setBounds(0, e.getComponent().getBounds().y, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverBox.setBounds(0, -200, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						if (userId == -1) {
							viewBook fr = new viewBook("Book view", Integer.parseInt(((JLayeredPane)cmpn).getToolTipText()));
							fr.setBackground("src/images/login-bg.jpg");
						} else {
							viewBook fr = new viewBook("Book view", Integer.parseInt(((JLayeredPane)cmpn).getToolTipText()), userId, isView);
							fr.setBackground("src/images/login-bg.jpg");
						}
						
					}
				});
			}
		}
		
		rightBar.setBounds(rightBar.getBounds().x, rightBar.getBounds().y, rightBar.getBounds().width, 0);
		for (Component comp : rightBar.getComponents()) {
			java.awt.Rectangle comp_b = comp.getBounds();
			
			//System.out.println("comp y: " + comp.getBounds().y + ", width: " + comp_b.width + ", height: " + comp_b.height + ", side: height" + rightBar_.getHeight());
			//System.out.println(rightBar_.height + comp_b.height + 10);

			comp.setBounds(0, rightBar.getBounds().height + 10, comp_b.width,  comp_b.height);
			rightBar.setSize(rightBar.getBounds().width, (int)(rightBar.getBounds().height + comp_b.height + 10));
		
		}
		System.out.println(rightBar.getBounds().height);
		rightBar_.remove(rightSidebar);
		rightSidebar = new drawRectangle(0, 0, rightBar_.getSize().width - 10, rightBar_.getSize().height, new Color(255, 255, 255, 200));
		rightSidebar.setSize(rightBar_.getSize().width - 10, rightBar_.getSize().height);
		rightBar_.add(rightSidebar, new Integer(1));
		
		rightBar.update(rightBar.getGraphics());
	}
	
	public void updateAllIssuesHistoryResults(ResultSet books) {
		
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				rightBar_.remove(cmpn);
			}
		}

		try {
			while (books.next()) {
				JLayeredPane user_ = new JLayeredPane();
				user_.setSize(rightBar_.getWidth(),  40);
				
				JLabel book = new JLabel(books.getString("book_name"));
				book.setBounds(20, user_.getHeight() / 2 - 10, 450, 20);
				user_.add(book, new Integer(2));
			
				
				JLabel iop = new JLabel("Opened: " +  books.getTimestamp("date_issue"));
				iop.setBounds(450, 0, 300, 20);
				user_.add(iop, new Integer(2));
				
				JLabel icl = new JLabel("Closed: " +  books.getTimestamp("returned_date"));
				icl.setBounds(450, 20, 300, 20);
				user_.add(icl, new Integer(2));
				
				
				JLabel rd = new JLabel();
				if (books.getInt("return_diff") < 0) {
					rd.setText("<html>Returned: <span style=\"color:red\">" +  books.getInt("return_diff") + "</span> days after</html>");
				} else {
					rd.setText("Returned: " +  books.getInt("return_diff") + " day/s before");
				}
				
				rd.setBounds(670, 0, 300, 20);
				
				user_.add(rd, new Integer(2));
				
				
				NumberFormat formatter = new DecimalFormat("#0.00");  
				
				JLabel fine_ = new JLabel();
				if (books.getInt("return_diff") < 0) {
					fine_.setText("<html>Fine: <span style=\"color:red\">" +  formatter.format(books.getInt("return_diff") * books.getDouble("fine_amount")) + "</span> som</html>");
				} else {
					fine_.setText("Fine: 0 som");
				}
				
				fine_.setBounds(840, 0, 300, 20);
				
				JLabel icl_ = new JLabel("User: " +  books.getString("user_name") + " " + books.getString("user_surname"));
				icl_.setBounds(670, 20, 300, 20);
				user_.add(icl_, new Integer(2));
				
				user_.add(fine_, new Integer(2));
				
				
				user_.setToolTipText(books.getString("book_id") + "%" + books.getString("user_id"));
				rightBar_.add(user_, new Integer(3));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rightBar_.setBounds(rightBar_.getBounds().x, rightBar_.getBounds().y, rightBar_.getBounds().width, 0);
		
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				cmpn.setBounds(0, rightBar_.getBounds().height + 0, cmpn.getWidth(),  cmpn.getHeight());
				rightBar_.setSize(rightBar_.getBounds().width, (int)(rightBar_.getBounds().height + cmpn.getHeight() + 0));
				cmpn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverBox.setBounds(0, e.getComponent().getBounds().y, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverBox.setBounds(0, -200, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						
						viewBook fr = new viewBook("Book view", Integer.parseInt(((JLayeredPane)cmpn).getToolTipText().split("%")[0]), Integer.parseInt(((JLayeredPane)cmpn).getToolTipText().split("%")[1]), true);
						fr.setBackground("src/images/login-bg.jpg");
						
						
					}
				});
			}
		}
		
		rightBar.setBounds(rightBar.getBounds().x, rightBar.getBounds().y, rightBar.getBounds().width, 0);
		for (Component comp : rightBar.getComponents()) {
			java.awt.Rectangle comp_b = comp.getBounds();
			
			//System.out.println("comp y: " + comp.getBounds().y + ", width: " + comp_b.width + ", height: " + comp_b.height + ", side: height" + rightBar_.getHeight());
			//System.out.println(rightBar_.height + comp_b.height + 10);

			comp.setBounds(0, rightBar.getBounds().height + 10, comp_b.width,  comp_b.height);
			rightBar.setSize(rightBar.getBounds().width, (int)(rightBar.getBounds().height + comp_b.height + 10));
		
		}
		System.out.println(rightBar.getBounds().height);
		rightBar_.remove(rightSidebar);
		rightSidebar = new drawRectangle(0, 0, rightBar_.getSize().width - 10, rightBar_.getSize().height, new Color(255, 255, 255, 200));
		rightSidebar.setSize(rightBar_.getSize().width - 10, rightBar_.getSize().height);
		rightBar_.add(rightSidebar, new Integer(1));
		
		rightBar.update(rightBar.getGraphics());
	}
	
	public void updateCurrentIssuesResults(ResultSet books) {
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				rightBar_.remove(cmpn);
			}
		}

		try {
			while (books.next()) {
				JLayeredPane user_ = new JLayeredPane();
				user_.setSize(rightBar_.getWidth(),  40);
				
				JLabel book = new JLabel(books.getString("book_name"));
				book.setBounds(20, user_.getHeight() / 2 - 10, 450, 20);
				user_.add(book, new Integer(2));
				
				JLabel iop = new JLabel("Opened: " +  books.getTimestamp("date_issue"));
				iop.setBounds(500, 0, 300, 20);
				user_.add(iop, new Integer(2));
				
				JLabel icl = new JLabel("Closes/d: " +  books.getTimestamp("date_return"));
				icl.setBounds(500, 20, 300, 20);
				user_.add(icl, new Integer(2));
				
				
				JLabel rd = new JLabel();
				if (books.getInt("return_diff") <= 3) {
					rd.setText("<html>Must return in <span style=\"color:red\">" +  books.getInt("return_diff") + "</span> day/s</html>");
				} else {
					rd.setText("Must return in: " +  books.getInt("return_diff") + " day/s");
				}
				
				rd.setBounds(750, 7, 300, 20);
				
				user_.add(rd, new Integer(2));
				
				
				
			
				
				
				user_.setToolTipText(books.getString("book_id"));
				rightBar_.add(user_, new Integer(3));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rightBar_.setBounds(rightBar_.getBounds().x, rightBar_.getBounds().y, rightBar_.getBounds().width, 0);
		
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				cmpn.setBounds(0, rightBar_.getBounds().height + 0, cmpn.getWidth(),  cmpn.getHeight());
				rightBar_.setSize(rightBar_.getBounds().width, (int)(rightBar_.getBounds().height + cmpn.getHeight() + 0));
				cmpn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverBox.setBounds(0, e.getComponent().getBounds().y, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverBox.setBounds(0, -200, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						if (userId == -1) {
							viewBook fr = new viewBook("Book view", Integer.parseInt(((JLayeredPane)cmpn).getToolTipText()));
							fr.setBackground("src/images/login-bg.jpg");
						} else {
							viewBook fr = new viewBook("Book view", Integer.parseInt(((JLayeredPane)cmpn).getToolTipText()), userId, isView);
							fr.setBackground("src/images/login-bg.jpg");
						}
						
					}
				});
			}
		}
		
		rightBar.setBounds(rightBar.getBounds().x, rightBar.getBounds().y, rightBar.getBounds().width, 0);
		for (Component comp : rightBar.getComponents()) {
			java.awt.Rectangle comp_b = comp.getBounds();
			
			//System.out.println("comp y: " + comp.getBounds().y + ", width: " + comp_b.width + ", height: " + comp_b.height + ", side: height" + rightBar_.getHeight());
			//System.out.println(rightBar_.height + comp_b.height + 10);

			comp.setBounds(0, rightBar.getBounds().height + 10, comp_b.width,  comp_b.height);
			rightBar.setSize(rightBar.getBounds().width, (int)(rightBar.getBounds().height + comp_b.height + 10));
		
		}
		System.out.println(rightBar.getBounds().height);
		rightBar_.remove(rightSidebar);
		rightSidebar = new drawRectangle(0, 0, rightBar_.getSize().width - 10, rightBar_.getSize().height, new Color(255, 255, 255, 200));
		rightSidebar.setSize(rightBar_.getSize().width - 10, rightBar_.getSize().height);
		rightBar_.add(rightSidebar, new Integer(1));
		
		rightBar.update(rightBar.getGraphics());
	}
	
	
	public void updateAllCurrentIssuesResults(ResultSet books) {
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				rightBar_.remove(cmpn);
			}
		}

		try {
			while (books.next()) {
				JLayeredPane user_ = new JLayeredPane();
				user_.setSize(rightBar_.getWidth(),  40);
				
				JLabel book = new JLabel(books.getString("book_name"));
				book.setBounds(20, user_.getHeight() / 2 - 10, 450, 20);
				user_.add(book, new Integer(2));
				
				JLabel iop = new JLabel("Opened: " +  books.getTimestamp("date_issue"));
				iop.setBounds(500, 0, 300, 20);
				user_.add(iop, new Integer(2));
				
				JLabel icl = new JLabel("Closes/d: " +  books.getTimestamp("date_return"));
				icl.setBounds(500, 20, 300, 20);
				user_.add(icl, new Integer(2));
				
				
				JLabel rd = new JLabel();
				if (books.getInt("return_diff") <= 3) {
					rd.setText("<html>Must return in <span style=\"color:red\">" +  books.getInt("return_diff") + "</span> day/s</html>");
				} else {
					rd.setText("Must return in: " +  books.getInt("return_diff") + " day/s");
				}
				
				rd.setBounds(750, 0, 300, 20);
				
				user_.add(rd, new Integer(2));
				
				
				JLabel icl_ = new JLabel("User: " +  books.getString("user_name") + " " +  books.getString("user_surname"));
				icl_.setBounds(750, 20, 300, 20);
				user_.add(icl_, new Integer(2));
				
			
				
				
				user_.setToolTipText(books.getString("book_id") + "%" + books.getString("user_id"));
				rightBar_.add(user_, new Integer(3));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rightBar_.setBounds(rightBar_.getBounds().x, rightBar_.getBounds().y, rightBar_.getBounds().width, 0);
		
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				cmpn.setBounds(0, rightBar_.getBounds().height + 0, cmpn.getWidth(),  cmpn.getHeight());
				rightBar_.setSize(rightBar_.getBounds().width, (int)(rightBar_.getBounds().height + cmpn.getHeight() + 0));
				cmpn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverBox.setBounds(0, e.getComponent().getBounds().y, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverBox.setBounds(0, -200, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						
						viewBook fr = new viewBook("Book view", Integer.parseInt(((JLayeredPane)cmpn).getToolTipText().split("%")[0]), Integer.parseInt(((JLayeredPane)cmpn).getToolTipText().split("%")[1]), true);
						fr.setBackground("src/images/login-bg.jpg");
						
					}
				});
			}
		}
		
		rightBar.setBounds(rightBar.getBounds().x, rightBar.getBounds().y, rightBar.getBounds().width, 0);
		for (Component comp : rightBar.getComponents()) {
			java.awt.Rectangle comp_b = comp.getBounds();
			
			//System.out.println("comp y: " + comp.getBounds().y + ", width: " + comp_b.width + ", height: " + comp_b.height + ", side: height" + rightBar_.getHeight());
			//System.out.println(rightBar_.height + comp_b.height + 10);

			comp.setBounds(0, rightBar.getBounds().height + 10, comp_b.width,  comp_b.height);
			rightBar.setSize(rightBar.getBounds().width, (int)(rightBar.getBounds().height + comp_b.height + 10));
		
		}
		System.out.println(rightBar.getBounds().height);
		rightBar_.remove(rightSidebar);
		rightSidebar = new drawRectangle(0, 0, rightBar_.getSize().width - 10, rightBar_.getSize().height, new Color(255, 255, 255, 200));
		rightSidebar.setSize(rightBar_.getSize().width - 10, rightBar_.getSize().height);
		rightBar_.add(rightSidebar, new Integer(1));
		
		rightBar.update(rightBar.getGraphics());
	}

	public void updateSearchUsersResults(ResultSet users) {
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				rightBar_.remove(cmpn);
			}
		}

		try {
			while (users.next()) {
				JLayeredPane user_ = new JLayeredPane();
				user_.setSize(rightBar_.getWidth(),  40);
				
				JLabel book = new JLabel(users.getString("name") + " " + users.getString("surname"));
				book.setBounds(20, user_.getHeight() / 2 - 10, 450, 20);
				user_.add(book, new Integer(2));
				
				JLabel iop = new JLabel("Birth Date: " +  users.getDate("birth_date"));
				iop.setBounds(500, 0, 300, 20);
				user_.add(iop, new Integer(2));
				
				JLabel icl = new JLabel("Number of issues: " +  users.getInt("issues_number"));
				icl.setBounds(500, 20, 300, 20);
				user_.add(icl, new Integer(2));
				
				
				JLabel rd = new JLabel();
				if (users.getInt("balance") < 0) {
					rd.setText("<html>Balance: <span style=\"color:red\">" +  users.getInt("balance") + "</span> </html>");
				} else {
					rd.setText("Balance: " +  users.getInt("balance"));
				}
				
				rd.setBounds(750, 7, 300, 20);
				
				user_.add(rd, new Integer(2));
				
				
				
			
				
				
				user_.setToolTipText(users.getString("id"));
				rightBar_.add(user_, new Integer(3));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rightBar_.setBounds(rightBar_.getBounds().x, rightBar_.getBounds().y, rightBar_.getBounds().width, 0);
		
		for (Component cmpn : rightBar_.getComponents()) {
			if (cmpn.getClass().getName().equals("javax.swing.JLayeredPane")) {
				cmpn.setBounds(0, rightBar_.getBounds().height + 0, cmpn.getWidth(),  cmpn.getHeight());
				rightBar_.setSize(rightBar_.getBounds().width, (int)(rightBar_.getBounds().height + cmpn.getHeight() + 0));
				cmpn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hoverBox.setBounds(0, e.getComponent().getBounds().y, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hoverBox.setBounds(0, -200, hoverBox.getWidth(), hoverBox.getHeight());
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						main fr = new main("View User", Integer.parseInt(((JLayeredPane)cmpn).getToolTipText()), true);
						fr.setBackground("src/images/login-bg.jpg");
					}
				});
			}
		}
		
		rightBar.setBounds(rightBar.getBounds().x, rightBar.getBounds().y, rightBar.getBounds().width, 0);
		for (Component comp : rightBar.getComponents()) {
			java.awt.Rectangle comp_b = comp.getBounds();
			
			//System.out.println("comp y: " + comp.getBounds().y + ", width: " + comp_b.width + ", height: " + comp_b.height + ", side: height" + rightBar_.getHeight());
			//System.out.println(rightBar_.height + comp_b.height + 10);

			comp.setBounds(0, rightBar.getBounds().height + 10, comp_b.width,  comp_b.height);
			rightBar.setSize(rightBar.getBounds().width, (int)(rightBar.getBounds().height + comp_b.height + 10));
		
		}
		System.out.println(rightBar.getBounds().height);
		rightBar_.remove(rightSidebar);
		rightSidebar = new drawRectangle(0, 0, rightBar_.getSize().width - 10, rightBar_.getSize().height, new Color(255, 255, 255, 200));
		rightSidebar.setSize(rightBar_.getSize().width - 10, rightBar_.getSize().height);
		rightBar_.add(rightSidebar, new Integer(1));
		
		rightBar.update(rightBar.getGraphics());
	}

	
}