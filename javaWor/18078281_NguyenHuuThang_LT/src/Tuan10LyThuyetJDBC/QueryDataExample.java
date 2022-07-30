package Tuan10LyThuyetJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryDataExample {
	public static void main(String[] args) throws SQLException {
		Connection con = null;

		String url = "jdbc:sqlserver://localhost:1433;databasename=mydb";
		String user = "sa";
		String password = "sapassword";
		
		con = DriverManager.getConnection(url, user, password);
		
		Statement statement = con.createStatement();
		String sql = "select * from hocsinh";
		// Thực thi cậu lệnh SQL trả về đối tượng ResultSet
		ResultSet rs = statement.executeQuery(sql);
		
		while(rs.next()) {
			int Id = rs.getInt(1);
			String ho = rs.getString("ho");
			String ten = rs.getString(3);
			System.out.println("===================================");
			System.out.println("Id là: " + Id);
			System.out.println("Họ là: " + ho);
			System.out.println("Tên là: " + ten);
			
		}
		
		
	}

}
