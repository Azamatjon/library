package main;


public class EditProfile_ extends Request {
	protected int userId;
	public EditProfile_(int userId){
		super.db = new mysql();
		this.userId = userId;
	}
	
	public int updateNowAd(String login, String name, String surname, String cardId, String telNumber, int gender, String address, int department, int birthDay, int birthMonth, String birthYear, String password, int status, int balance){
		
		int upd = db.update("UPDATE `users` SET `name` = '" + name +"', `surname` = '" + surname +"', `card_id` = '" + cardId +"', `tel` = '" + telNumber +"', `address` = '" + address +"', `birth_date` = '" + birthYear + "-" + birthMonth + "-" + birthDay +"', `department_id` = " + department +", `status` = " + status + ", `password` = '" + password +"', `login` = '" + login +"', `gender` = " + gender + ", `balance` = " + balance + " WHERE id = " + userId);
		
		if (upd > 0) {
			System.out.println("updated Succesfully");
			return 1;
		} else {
			System.out.println("Error");
			return 0;
		}
	}
	
	public int updateNowSm(String login, String name, String surname, String cardId, String telNumber, int gender, String address, int department, int birthDay, int birthMonth, String birthYear, String password){
		
		int upd = db.update("UPDATE `users` SET `name` = '" + name +"', `surname` = '" + surname +"', `card_id` = '" + cardId +"', `tel` = '" + telNumber +"', `address` = '" + address +"', `birth_date` = '" + birthYear + "-" + birthMonth + "-" + birthDay +"', `department_id` = " + department +", `password` = '" + password +"', `login` = '" + login +"', `gender` = " + gender + " WHERE id = " + userId);
		
		if (upd > 0) {
			System.out.println("updated Succesfully");
			return 1;
		} else {
			System.out.println("Error");
			return 0;
		}
	}
}
