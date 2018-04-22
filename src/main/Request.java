package main;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Request {
	public mysql db;
	public int checkLoginExistance(String login){
		ResultSet getUSer = db.getRow("SELECT count(*) as counted FROM users WHERE login = '" + login + "'");
		
		try {
			int userId = getUSer.getInt("counted");
			if (userId > 0) {
				System.out.println("Login exists");
				return 0;
			} else {
				System.out.println("Login is Free");
				return 1;
			}
		} catch (SQLException e) {
			System.out.println("Error check it!");
			return 0;
		}
	}
	public int checkCardIDExistance(String CardId){
		ResultSet getUSer = db.getRow("SELECT count(*) as counted FROM users WHERE card_id = '" + CardId + "'");
		
		try {
			int userId = getUSer.getInt("counted");
			if (userId > 0) {
				System.out.println("Card exists");
				return 0;
			} else {
				System.out.println("Card is Free");
				return 1;
			}
		} catch (SQLException e) {
			System.out.println("Error check it!");
			return 0;
		}
	}
	
}
