package main;
import java.sql.*;
public class Auth {
	private String login;
	private String password;
	private mysql db;
	public Auth(String login, String password){
		this.login = login;
		this.password = password;
		db = new mysql();
	}
	public int check(){
		ResultSet getUSer = db.getRow("SELECT * FROM users WHERE login = '" + login + "'");
		
		try {
			int userId = getUSer.getInt("id");
			System.out.println("User exists (id= " + userId + ")");
			if (getUSer.getString("password").equals(password)) {
				System.out.println("Login and password you entered true!");
				return 1;
			} else {
				System.out.println("Password you entered Incorrect!");
				return 2;
			}
			
		} catch (SQLException e) {
			System.out.println("User does NOT exist");
			return 0;
		}
	}
	
	public int getId(){
		ResultSet getUSer = db.getRow("SELECT * FROM users WHERE login = '" + login + "'");
		
		try {
			int userId = getUSer.getInt("id");
			System.out.println("User exists (id= " + userId + ")");
			if (getUSer.getString("password").equals(password)) {
				return userId;
			} else {
				System.out.println("Password you entered Incorrect!");
				return -1;
			}
			
		} catch (SQLException e) {
			System.out.println("User does NOT exist");
			return -1;
		}
	}
}
