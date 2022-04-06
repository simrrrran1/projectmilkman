package projectDatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;


public class Connector {
	
	public static Connection doConnect()
	{
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 con=DriverManager.getConnection("jdbc:mysql://localhost/projectmilkman","root","1301");
			System.out.println("Connected....");
		} 
		catch (Exception e) 
		  {
			e.printStackTrace();
		  }
		return con;
	}
	
	public static void main(String[] args) 
	{
		doConnect();

	}

}



