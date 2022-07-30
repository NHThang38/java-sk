package ThucHanhJDBC;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	public static void main(String[] args) throws SQLException {
		Connection con = null;
		String url = "jdbc:sqlserver://localhost:1433;databasename=qlsv";
		String user = "sa";
		String password = "sapassword";
		
		con = DriverManager.getConnection(url,user,password);
		
		Statement myStmt = con.createStatement();
		String sql = "select * from LopHoc";
		ResultSet rs = myStmt.executeQuery(sql);
		
		while(rs.next()) {
			int maLop = rs.getInt("maLop");
			String tenLop = rs.getString("tenLop");
			
			System.out.println(maLop+tenLop);
		}
		
	}

}
