package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	
	
	/** execute query requests from DB **/
	
	private Statement state = null ;
	
	public ResultSet executeQuery( String query ){
		try {
			state = Connect.connect_to_DB().createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE ) ;
			ResultSet r = state.executeQuery(query) ;
			return r ;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public int updateQuery( String query ){
		try {
			state = Connect.connect_to_DB().createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE ) ;
			state.executeUpdate(query) ;
			return 1 ;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1 ;
		}
	}

}
