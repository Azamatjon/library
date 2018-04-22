
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

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class editBook {

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
	private mysql db;
	private int bookId;
	/**
	 * Launch the application.
	 */
	public editBook(String frameName, int bookId) {
		this.title = frameName;
		this.bookId = bookId;
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
		panel = new JPanel();
		panel.setLayout(null);
	
		frame.setContentPane(panel);
		
		JLayeredPane layers = new JLayeredPane();
		
		db = new mysql();
		ResultSet bookInfo = db.getRow("SELECT b.*, LEFT(b.published_year, 4) as `p_year`, a.name author_name, a.surname author_surname, p.name publisher_name FROM books b LEFT JOIN authors a ON b.author_id = a.id LEFT JOIN publishers p ON b.publisher_id = p.id WHERE b.id = " + bookId + " ");
		label = new JLabel();
		if (picture != null) {
			ImageIcon image = new ImageIcon(picture);
			label.setIcon(image);
		}
		label.setBounds(0,0, width, height);
		layers.add(label, new Integer(1));
		
		
		
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
		
		
		
		JLabel logo = new JLabel("Editing the Book");
		logo.setFont(new Font("Cochin", Font.PLAIN, 22));
		logo.setForeground(Color.white);
		logo.setBounds(width / 2 - 100,13, 200, 30);
		layers.add(logo, new Integer(100));

		
		
		JLayeredPane leftColumn = new JLayeredPane();
		leftColumn.setBounds((int)Math.round(label.getWidth() / 2 - 330), 60, 660, 30);
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
		
		JLabel bkname = new JLabel("Book name:");
		bkname.setFont(new Font("Cochin", Font.PLAIN, 17));
		bkname.setBounds(0,0, 130, 30);
		lg.add(bkname, new Integer(2));
		
		JTextField titleField = new JTextField();
		titleField.setFont(new Font("Cochin", Font.PLAIN, 15));
		titleField.setBounds(150, 0, 430, 30);
		lg.add(titleField, new Integer(2));
		
		try {
			titleField.setText(bookInfo.getString("book_name"));
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		leftColumn.add(lg, new Integer(3));
		
		
		
		
		JLayeredPane lg2 = new JLayeredPane();
		lg2.setSize(600, 40);
		
		JLabel regName = new JLabel("Author");
		regName.setFont(new Font("Cochin", Font.PLAIN, 19));
		regName.setBounds(0,0, 130, 30);
		lg2.add(regName, new Integer(2));
		
		leftColumn.add(lg2, new Integer(3));
		
		
		JLayeredPane lg3 = new JLayeredPane();
		lg3.setSize(600, 40);
		
		JLabel auName = new JLabel("Name:");
		auName.setFont(new Font("Cochin", Font.PLAIN, 17));
		auName.setBounds(30, 0, 130, 30);
		lg3.add(auName, new Integer(2));
		
		
		JTextField auNameField = new JTextField();
		auNameField.setFont(new Font("Cochin", Font.PLAIN, 15));
		auNameField.setBounds(150, 0, 430, 30);
		lg3.add(auNameField, new Integer(2));
		
		try {
			auNameField.setText(bookInfo.getString("author_name"));
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		leftColumn.add(lg3, new Integer(3));
		
		
		JLayeredPane lg4 = new JLayeredPane();
		lg4.setSize(600, 50);
		
		JLabel surName = new JLabel("Surname:");
		surName.setFont(new Font("Cochin", Font.PLAIN, 17));
		surName.setBounds(30, 0, 130, 30);
		lg4.add(surName, new Integer(2));
		
		JTextField surnauTf = new JTextField();
		surnauTf.setFont(new Font("Cochin", Font.PLAIN, 15));
		surnauTf.setBounds(150, 0, 430, 30);
		lg4.add(surnauTf, new Integer(2));
		
		try {
			surnauTf.setText(bookInfo.getString("author_surname"));
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		leftColumn.add(lg4, new Integer(3));
		
		JLayeredPane lg5 = new JLayeredPane();
		lg5.setSize(600, 40);
		JLabel telName = new JLabel("Publisher");
		telName.setFont(new Font("Cochin", Font.PLAIN, 19));
		telName.setBounds(0, 0, 130, 30);
		lg5.add(telName, new Integer(2));
		
		leftColumn.add(lg5, new Integer(3));
		
		
		JLayeredPane lg6 = new JLayeredPane();
		lg6.setSize(600, 60);
		JLabel cpn = new JLabel("Company name:");
		cpn.setFont(new Font("Cochin", Font.PLAIN, 17));
		cpn.setBounds(30, 0, 130, 30);
		lg6.add(cpn, new Integer(2));
		
		
		
		JTextField pubName = new JTextField();
		pubName.setFont(new Font("Cochin", Font.PLAIN, 15));
		pubName.setBounds(150, 0, 430, 30);
		
		try {
			pubName.setText(bookInfo.getString("publisher_name"));
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		lg6.add(pubName, new Integer(2));
		
		leftColumn.add(lg6, new Integer(3));
		
		
		JLayeredPane lg7 = new JLayeredPane();
		lg7.setSize(600, 130);
		JLabel descr = new JLabel("Description:");
		descr.setFont(new Font("Cochin", Font.PLAIN, 17));
		descr.setBounds(0, 0, 130, 120);
		lg7.add(descr, new Integer(2));
		
		JTextArea descr__ = new JTextArea();
		descr__.setLineWrap(true);
		descr__.setWrapStyleWord(true);
		descr__.setRows(3);
		descr__.setFont(new Font("Cochin", Font.PLAIN, 15));
		descr__.setBounds(150, 0, 430, 120);
		
		try {
			descr__.setText(bookInfo.getString("description"));
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		lg7.add(descr__, new Integer(2));
		
		leftColumn.add(lg7, new Integer(3));
		
		
		JLayeredPane lg8 = new JLayeredPane();
		lg8.setSize(600, 40);
		JLabel departm = new JLabel("Subject:");
		departm.setFont(new Font("Cochin", Font.PLAIN, 17));
		departm.setBounds(0, 0, 130, 30);
		lg8.add(departm, new Integer(2));
		
		
		JComboBox<comboItem> subj_ = new JComboBox<>();
		
		
		
		ResultSet subjects = db.getAll("Select * from `subjects`");
		try {
			subj_.addItem(new comboItem(" ", -1));
			while (subjects.next()) {
				subj_.addItem(new comboItem(subjects.getString("name"), subjects.getInt("id")));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			for (int d = 0; d < subj_.getItemCount(); d++) {
				if (subj_.getItemAt(d).equals(new comboItem("", bookInfo.getInt("subject_id")))){
					subj_.setSelectedItem(subj_.getItemAt(d));
					break;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		subj_.setFont(new Font("Cochin", Font.PLAIN, 15));
		subj_.setBounds(150, 0, 430, 30);
		lg8.add(subj_, new Integer(2));
		
		leftColumn.add(lg8, new Integer(3));
		
		
		
		JLayeredPane lg15 = new JLayeredPane();
		lg15.setSize(600, 40);
		JLabel catg_ = new JLabel("Category:");
		catg_.setFont(new Font("Cochin", Font.PLAIN, 17));
		catg_.setBounds(0, 0, 130, 30);
		lg15.add(catg_, new Integer(2));
		
		
		JComboBox<comboItem> catge_ = new JComboBox<>();
		
		
		ResultSet categories_ = db.getAll("Select * from `categories`");
		try {
			catge_.addItem(new comboItem(" ", -1));
			while (categories_.next()) {
				catge_.addItem(new comboItem(categories_.getString("name"), categories_.getInt("id")));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			for (int d = 0; d < catge_.getItemCount(); d++) {
				if (catge_.getItemAt(d).equals(new comboItem("", bookInfo.getInt("category_id")))){
					catge_.setSelectedItem(catge_.getItemAt(d));
					break;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		catge_.setFont(new Font("Cochin", Font.PLAIN, 15));
		catge_.setBounds(150, 0, 430, 30);
		lg15.add(catge_, new Integer(2));
		
		leftColumn.add(lg15, new Integer(3));
		
		
		
		JLayeredPane lg20 = new JLayeredPane();
		lg20.setSize(600, 40);
		JLabel depr_ = new JLabel("Category:");
		depr_.setFont(new Font("Cochin", Font.PLAIN, 17));
		depr_.setBounds(0, 0, 130, 30);
		lg20.add(depr_, new Integer(2));
		
		
		JComboBox<comboItem> dpr_ = new JComboBox<>();
		
		
		ResultSet departments_ = db.getAll("Select * from `departments`");
		try {
			catge_.addItem(new comboItem(" ", -1));
			while (departments_.next()) {
				dpr_.addItem(new comboItem(departments_.getString("name"), departments_.getInt("id")));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			for (int d = 0; d < dpr_.getItemCount(); d++) {
				if (dpr_.getItemAt(d).equals(new comboItem("", bookInfo.getInt("department_id")))){
					dpr_.setSelectedItem(dpr_.getItemAt(d));
					break;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		dpr_.setFont(new Font("Cochin", Font.PLAIN, 15));
		dpr_.setBounds(150, 0, 430, 30);
		lg20.add(dpr_, new Integer(2));
		
		leftColumn.add(lg20, new Integer(3));
		
		
		
		JLayeredPane lg9 = new JLayeredPane();
		lg9.setSize(600, 40);
		JLabel bd = new JLabel("Location:");
		bd.setFont(new Font("Cochin", Font.PLAIN, 19));
		bd.setBounds(0, 0, 130, 30);
		lg9.add(bd, new Integer(2));
		
		leftColumn.add(lg9, new Integer(3));
		
		
		
		JLayeredPane lg10 = new JLayeredPane();
		lg10.setSize(600, 60);
		
		JLabel coln = new JLabel("Column:");
		coln.setFont(new Font("Cochin", Font.PLAIN, 17));
		coln.setBounds(150, 0, 100, 30);
		lg10.add(coln, new Integer(2));
		
		JSpinner columnLoc = new JSpinner();
		columnLoc.setFont(new Font("Cochin", Font.PLAIN, 15));
		columnLoc.setBounds(220, 0, 90, 30);
		lg10.add(columnLoc, new Integer(2));
		
		try {
			columnLoc.setValue(bookInfo.getInt("column"));
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		JLabel rown = new JLabel("Row:");
		rown.setFont(new Font("Cochin", Font.PLAIN, 17));
		rown.setBounds(370, 0, 100, 30);
		lg10.add(rown, new Integer(2));
		
		JSpinner rowLoc = new JSpinner();
		rowLoc.setFont(new Font("Cochin", Font.PLAIN, 15));
		rowLoc.setBounds(420, 0, 90, 30);
		lg10.add(rowLoc, new Integer(2));
		
		try {
			rowLoc.setValue(bookInfo.getInt("row"));
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		leftColumn.add(lg10, new Integer(3));
		
		
		
		JLayeredPane lg11 = new JLayeredPane();
		lg11.setSize(600, 40);
		
		JLabel bkn = new JLabel("Books Number:");
		bkn.setFont(new Font("Cochin", Font.PLAIN, 17));
		bkn.setBounds(0, 0, 130, 30);
		lg11.add(bkn, new Integer(2));
		
		JSpinner bkn__ = new JSpinner();
		bkn__.setFont(new Font("Cochin", Font.PLAIN, 15));
		bkn__.setBounds(150, 0, 90, 30);
		lg11.add(bkn__, new Integer(2));
		
		try {
			bkn__.setValue(bookInfo.getInt("books_number"));
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		leftColumn.add(lg11, new Integer(3));
		
		
		JLayeredPane lg16 = new JLayeredPane();
		lg16.setSize(600, 40);
		
		JLabel prct = new JLabel("Price:");
		prct.setFont(new Font("Cochin", Font.PLAIN, 17));
		prct.setBounds(0, 0, 130, 30);
		lg16.add(prct, new Integer(2));
		
		JSpinner prct_ = new JSpinner();
		prct_.setFont(new Font("Cochin", Font.PLAIN, 15));
		prct_.setBounds(150, 0, 90, 30);
		lg16.add(prct_, new Integer(2));
		
		try {
			prct_.setValue(bookInfo.getInt("price"));
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		leftColumn.add(lg16, new Integer(3));
		
		JLayeredPane lg17 = new JLayeredPane();
		lg17.setSize(600, 40);
		
		JLabel brct = new JLabel("Barcode:");
		brct.setFont(new Font("Cochin", Font.PLAIN, 17));
		brct.setBounds(0, 0, 130, 30);
		lg17.add(brct, new Integer(2));
		
		JTextField brct_ = new JTextField();
		brct_.setFont(new Font("Cochin", Font.PLAIN, 15));
		brct_.setBounds(150, 0, 420, 30);
		lg17.add(brct_, new Integer(2));
		
		try {
			brct_.setText(bookInfo.getString("barcode"));
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		leftColumn.add(lg17, new Integer(3));
		
		
		JLayeredPane lg18 = new JLayeredPane();
		lg18.setSize(600, 40);
		
		JLabel yert = new JLabel("Published Year:");
		yert.setFont(new Font("Cochin", Font.PLAIN, 17));
		yert.setBounds(0, 0, 130, 30);
		lg18.add(yert, new Integer(2));
		
		JTextField yert_ = new JTextField();
		yert_.setFont(new Font("Cochin", Font.PLAIN, 15));
		yert_.setBounds(150, 0, 90, 30);
		
		try {
			yert_.setText(bookInfo.getString("p_year"));
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		lg18.add(yert_, new Integer(2));
		
		leftColumn.add(lg18, new Integer(3));
		
		
		customButton saveNow = new customButton("Save", 100, 40, "images/save.png");
		saveNow.setPosition(100, 20);
		
		
		JLayeredPane lg12 = new JLayeredPane();
		lg12.setSize(600, 90);
		lg12.add(saveNow.getButton(), new Integer(2));
		
		
		customButton removeNow = new customButton("Remove", 120, 40, "images/remove.png", new Color(255, 0, 0, 200), new Color(255, 255, 255));
		removeNow.setPosition(220, 20);
		
	
		lg12.add(removeNow.getButton(), new Integer(2));
		
		for (Component btn : saveNow.getButton().getComponents()) {
			btn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					Book nwbk = new Book();
					
					if (titleField.getText().length() > 0) {
						if (nwbk.edit(titleField.getText(), auNameField.getText(), surnauTf.getText(), Integer.parseInt(prct_.getValue().toString()), brct_.getText(), ((int)((comboItem)subj_.getSelectedItem()).getId() != -1)?(int)((comboItem)subj_.getSelectedItem()).getId():null, Integer.parseInt(columnLoc.getValue().toString()), Integer.parseInt(rowLoc.getValue().toString()), Integer.parseInt(bkn__.getValue().toString()), pubName.getText(), ((int)((comboItem)catge_.getSelectedItem()).getId() != -1)?(int)((comboItem)catge_.getSelectedItem()).getId():null, descr__.getText(), yert_.getText(), bookId, ((int)((comboItem)dpr_.getSelectedItem()).getId() != -1)?(int)((comboItem)dpr_.getSelectedItem()).getId():null) ){
							showMessage("Success, the book was edited successfuly!", true);
						}						
					} else showMessage("Error, couldn't edit!", false);
				}
			});
			
		}
		
		for (Component btn : removeNow.getButton().getComponents()) {
			btn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					Book nwbk = new Book();
					
					if (titleField.getText().length() > 0) {
						if (nwbk.delete(bookId)) {
							showMessage("Success, the book was deleted successfully!", true);
						} else showMessage("Error, couldn't delete book!", false);
					} else showMessage("Error, you havent entered title!", false);
				}
			});
			
		}
		
		leftColumn.add(lg12, new Integer(3));
		
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
			this.close();
			
		} else {
			err.setVisible(true);
			errCode.setVisible(true);
			errCode.setText(message);
			succCode.setVisible(false);
			succ.setVisible(false);
		}
		
		
	}
}