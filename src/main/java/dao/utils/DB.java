package dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	
	private static final String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/dchqgbyd";
	private static final String user = "dchqgbyd";
	private static final String pass = "NhtBV0fJkxswJU_wnGShHGwMDup5cj_6";

	private DB(){
	}
	
//	public static Connection crearConnection(){
//		Connection conn=null;
//		
//		try {
//			Class.forName("org.postgresql.Driver");
//			conn = DriverManager.getConnection(url,user,pass);
//		} catch (ClassNotFoundException ex) {
//		ex.printStackTrace();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//		return conn;
//	}
//	
//	public static Connection getConnection() {
//		return crearConnection();
//	}

}
