package main;
import java.sql.*;

public class mysql {
	private Connection connection_;
	
	public mysql (){
		try {
			String hostName = "";
			String userName = "root";
			String password = "1111";
			String dbName = "library";
			int port = 3306;
			boolean sslVerification = false;
			connection_ = DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + port + "/" + dbName + ((!sslVerification)?"?autoReconnect=true&useSSL=false":""), userName, password);
			System.out.println("connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ResultSet getAll(String query) {
	     try {
	    	 PreparedStatement pstmt = connection_.prepareStatement(query);
	    	 ResultSet result = pstmt.executeQuery();
	    	 return result;
	     } catch (SQLException e) {
	    	 e.printStackTrace();
	     }
	     
	     return null;
	 }
	
	public ResultSet getRow(String query) {
	     
	     try {
	    	 PreparedStatement pstmt = connection_.prepareStatement(query);
	    	 ResultSet result = pstmt.executeQuery();
	    	 result.next();
	    	 return result;
	     } catch (SQLException e) {
	    	 e.printStackTrace();
	     }
	     
	     return null;
	 }
	
	public int update(String query){
		try {
			Statement st = connection_.createStatement();
		    return st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean insert(String query){
		try {
			Statement st = connection_.createStatement();
		    st.execute(query);
		    return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean delete(String query){
		try {
			Statement st = connection_.createStatement();
		    st.execute(query);
		    return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	 
	
	
	public void closeConnection(){
		try {
			connection_.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
