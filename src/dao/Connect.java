package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	
	/** Connect only one time to DB **/
	
	private static String url = "jdbc:postgresql://localhost:5433/DepInfo";
	  private static String user = "postgres";
	  private static String passwd = "root";
	  private static Connection connect;
	   
	  public static Connection connect_to_DB (){
	    if(connect == null){
	      try {
	        connect = DriverManager.getConnection(url, user, passwd);
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }      
	    return connect;
	  }   
}