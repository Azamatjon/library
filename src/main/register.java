package main;
import java.sql.*;
public class register {
	private mysql db;
	public register(){
		db = new mysql();
	}
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
	public int registerNow(String login, String name, String surname, String cardId, String telNumber, int gender, String address, int department, String birthDay, int birthMonth, String birthYear, String password){
		
		String makeSQL = "('" + name +"', '" + surname +"', '" + cardId +"', '" + telNumber +"', '" + address +"', '" + birthYear + "-" + birthMonth + "-" + birthDay +"', " + department +", " + 3 +", " + 1 +", '" + password +"', '" + login +"', " + gender +" )";
	
		boolean regNow = db.insert("INSERT INTO `users` (`name`, `surname`, `card_id`, `tel`, `address`, `birth_date`, `department_id`, `group_id`, `status`, `password`, `login`, `gender`) VALUES " + makeSQL);
		
		if (regNow) {
			System.out.println("Registered Succesfully");
			return 1;
		} else {
			System.out.println("Error");
			return 0;
		}
	}
}
