package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.PhongBan;

public class PhongBan_DAO {
	
	public ArrayList<PhongBan> getAllPhongBan() {
		ArrayList<PhongBan> dsPhong = new ArrayList<PhongBan>();
		
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "select * from PhongBan";
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				String maPhong = rs.getString(1);
				String tenPhong = rs.getString(2);
				PhongBan p = new PhongBan(maPhong, tenPhong);
				dsPhong.add(p);
				
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsPhong;
		
	}

}
