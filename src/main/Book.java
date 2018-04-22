package main;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Book {
	public mysql db;
	public Book() {
		this.db = new mysql();
	}
	
	public boolean add(String name, String authorName, String authorSurname, Integer price, String barcode, Integer subject_id, Integer column, Integer row, Integer books_number, String publisherName, Integer category_id, String description, String year, int department_id) {
		ResultSet bk =  db.getRow("SELECT id FROM `authors` WHERE `name` = '" + authorName + "' AND `surname` = '" + authorSurname + "'");
		ResultSet pb = db.getRow("SELECT id FROM `publishers` WHERE `name` = '" + publisherName + "'");
		int author_id = -1;
		int publisher_id = -1;
		try {
			author_id = bk.getInt("id");
		} catch (SQLException e) {
			db.insert("INSERT INTO `authors` (name, surname) VALUES ('" + authorName + "', '" + authorSurname + "')");
			ResultSet bk_i =  db.getRow("SELECT id FROM `authors` WHERE `name` = '" + authorName + "' AND `surname` = '" + authorSurname + "'");
			try {
				author_id = bk_i.getInt("id");
			} catch (SQLException e1) {
				return false;
			}
		}
		try {
			publisher_id = pb.getInt("id");
		} catch (SQLException e) {
			db.insert("INSERT INTO `publishers` (name) VALUES ('" + publisherName + "')");
			ResultSet pb_i =  db.getRow("SELECT id FROM `publishers` WHERE `name` = '" + publisherName + "'");
			try {
				publisher_id = pb_i.getInt("id");
			} catch (SQLException e1) {
				return false;
			}
		}
		String buildSQL = "'" + name + "', " + author_id + ", " + price + ", '" + barcode + "', " + subject_id + ", " + column + ", " + row + ", " + books_number + ", " + publisher_id + ", " + category_id + ", '" + description + "', '" + year + "', " + department_id;
		return db.insert("INSERT INTO books (`book_name`, `author_id`, `price`, `barcode`, `subject_id`, `column`, `row`, `books_number`, `publisher_id`, `category_id`, `description`, `published_year`, `department_id`) VALUES (" + buildSQL + ")");
	}
	public boolean edit(String name, String authorName, String authorSurname, int price, String barcode, int subject_id, int column, int row, int books_number, String publisherName, int category_id, String description, String year, int bookId, int department_id) {
		ResultSet bk =  db.getRow("SELECT id FROM `authors` WHERE `name` = '" + authorName + "' AND `surname` = '" + authorSurname + "'");
		ResultSet pb = db.getRow("SELECT id FROM `publishers` WHERE `name` = '" + publisherName + "'");
		int author_id = -1;
		int publisher_id = -1;
		try {
			author_id = bk.getInt("id");
		} catch (SQLException e) {
			db.insert("INSERT INTO `authors` (name, surname) VALUES ('" + authorName + "', '" + authorSurname + "')");
			ResultSet bk_i =  db.getRow("SELECT id FROM `authors` WHERE `name` = '" + authorName + "' AND `surname` = '" + authorSurname + "'");
			try {
				author_id = bk_i.getInt("id");
			} catch (SQLException e1) {
				return false;
			}
		}
		try {
			publisher_id = pb.getInt("id");
		} catch (SQLException e) {
			db.insert("INSERT INTO `publishers` (name) VALUES ('" + publisherName + "')");
			ResultSet pb_i =  db.getRow("SELECT id FROM `publishers` WHERE `name` = '" + publisherName + "'");
			try {
				publisher_id = pb_i.getInt("id");
			} catch (SQLException e1) {
				return false;
			}
		}
		String buildSQL = "book_name = '" + name + "', author_id = " + author_id + ", price = " + price + ", barcode = '" + barcode + "', subject_id = " + subject_id + ", `column` = " + column + ", `row` = " + row + ", books_number = " + books_number + ", publisher_id = " + publisher_id + ", category_id = " + category_id + ", description = '" + description + "', published_year = '" + year + "', department_id = " + department_id;
		if (db.update("UPDATE books SET " + buildSQL + " WHERE id = " + bookId) > 0) {
			return true;
		} else return false;
	}
	
	public boolean rateNow(int bookId, int userId, int grade) {
		ResultSet bk =  db.getRow("SELECT id FROM `ratings` WHERE `user_id` = " + userId + " AND `book_id` = " + bookId);
		
		try {
			if (bk.getInt("id") > 0) {
				if (db.update("UPDATE ratings SET points = " + grade + " WHERE id = " + bk.getInt("id")) > 0){
					return true;
				}
			}
		} catch (SQLException e) {
			boolean rated = db.insert("INSERT INTO `ratings` (user_id, book_id, points, date) VALUES (" + userId + ", " + bookId + ", " + grade + ", NOW())");
			if (rated) {
				return true;
			} else return false;
		}
		return false;
	}
	
	public int getMyRate(int bookId, int userId) {
		ResultSet bk =  db.getRow("SELECT points FROM `ratings` WHERE `user_id` = " + userId + " AND `book_id` = " + bookId);
		
		try {
			if (bk.getInt("points") >= 0) {
				return bk.getInt("points");
			}
		} catch (SQLException e) {
			return 0;
		}
		return 0;
	}
	
	public boolean isIssued(int book_id, int userId) {
		ResultSet bkin = db.getRow("SELECT id FROM issues WHERE user_id = " + userId + " AND book_id = " + book_id + " AND returned_date IS NULL AND date_return IS NOT NULL");
		
		try {
			if (bkin.getInt("id") > 0) {
				return true;
			} else return false;
		} catch (SQLException e) {
			
			System.out.println(e.getMessage() + " id: " + book_id + ", userid: " + userId);
			return false;
		}
	}
	
	public boolean isRequested(int book_id, int userId) {
		ResultSet bkin = db.getRow("SELECT id FROM issues WHERE user_id = " + userId + " AND book_id = " + book_id + " AND date_return IS NULL AND returned_date IS NULL");
		
		try {
			if (bkin.getInt("id") > 0) {
				return true;
			} else return false;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public int numExtend(int book_id, int userId) {
		ResultSet bkin = db.getRow("SELECT i.extended, g.extend_num FROM issues i LEFT JOIN users u ON u.id = i.user_id LEFT JOIN groups g ON g.id = u.group_id WHERE i.user_id = " + userId + " AND i.book_id = " + book_id + " AND i.date_return IS NOT NULL AND i.returned_date IS NULL");
		
		try {
			return (bkin.getInt("extend_num") - bkin.getInt("extended"));
		} catch (SQLException e) {
			return 0;
		}
	}
	
	
	

	
	public boolean acceptIssue(int book_id, int userId) {
		ResultSet diff_d = db.getRow("SELECT DATEDIFF(i.date_return, NOW()) diff_date, g.fine_amount FROM issues i LEFT JOIN users u ON u.id = i.user_id LEFT JOIN groups g ON g.id = u.group_id WHERE i.user_id = " + userId + " AND i.book_id = " + book_id + " AND i.returned_date IS NULL");
		try {
			if (diff_d.getInt("diff_date") < 0) {
				System.out.println("UPDATE users SET balance = balance + " + (diff_d.getDouble("fine_amount") * diff_d.getInt("diff_date") ) + " WHERE user_id = " + userId + " AND book_id = " + book_id + " AND date_return IS NOT NULL AND returned_date IS NULL");
				db.update("UPDATE users SET balance = balance + " + (diff_d.getDouble("fine_amount") * diff_d.getInt("diff_date") ) + " WHERE id = " + userId);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int bkin = db.update("UPDATE issues SET returned_date = NOW() WHERE user_id = " + userId + " AND book_id = " + book_id + " AND date_return IS NOT NULL AND returned_date IS NULL");
		
		if (bkin > 0) return true;
		else return false;
	}
	
	public boolean unRequest(int book_id, int userId) {
		boolean bkin = db.delete("DELETE FROM issues WHERE book_id = " + book_id + " AND user_id = " + userId + " AND date_return IS NULL AND returned_date IS NULL AND extended = 0");
		
		if (bkin) return true;
		else return false;
	}
	
	public boolean acceptRequest(int book_id, int userId) {
		ResultSet group = db.getRow("SELECT g.extend_time FROM users u LEFT JOIN groups g ON u.group_id = g.id WHERE u.id = " + userId);
		
		try {
			int bkin = db.update("UPDATE issues SET date_return = DATE_ADD(NOW(), INTERVAL " + group.getInt("extend_time") + " DAY) WHERE user_id = " + userId + " AND book_id = " + book_id + " AND date_return IS NULL AND returned_date IS NULL");
			if (bkin > 0) return true;
			else return false;
		} catch (SQLException e) {
			return false;
		}
	
	}
	
	public boolean extend(int book_id, int userId) {
		ResultSet group = db.getRow("SELECT g.extend_time FROM users u LEFT JOIN groups g ON u.group_id = g.id WHERE u.id = " + userId);
		
		try {
			int bkin = db.update("UPDATE issues SET date_return = DATE_ADD(NOW(), INTERVAL " + group.getInt("extend_time") + " DAY), extended = extended + 1 WHERE user_id = " + userId + " AND book_id = " + book_id + " AND date_return IS NOT NULL AND returned_date IS NULL");
			if (bkin > 0) return true;
			else return false;
		} catch (SQLException e) {
			return false;
		}
	
	}
	
	public boolean newIssue(int book_id, int userId) {
		ResultSet group = db.getRow("SELECT g.extend_time FROM users u LEFT JOIN groups g ON u.group_id = g.id WHERE u.id = " + userId);
		
		try {
			boolean bkin = db.insert("INSERT INTO issues (user_id, book_id, date_issue, date_return) VALUES (" + userId + ", " + book_id + ", NOW(), DATE_ADD(NOW(), INTERVAL " + group.getInt("extend_time") + " DAY))");
			if (bkin) return true;
			else return false;
		} catch (SQLException e) {
			return false;
		}
	
	}
	
	public boolean newRequest(int book_id, int userId) {
		
		boolean bkin = db.insert("INSERT INTO issues (user_id, book_id, date_issue) VALUES (" + userId + ", " + book_id + ", NOW())");
		if (bkin) return true;
		else return false;
	
	}
	
	public boolean delete(int book_id) {
		if (db.delete("DELETE FROM books WHERE id = " + book_id)) {
			return true;
		} else return false;
	}
}
