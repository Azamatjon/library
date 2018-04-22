package main;

public class Registration extends Request {
	public Registration(){
		super.db = new mysql();
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
