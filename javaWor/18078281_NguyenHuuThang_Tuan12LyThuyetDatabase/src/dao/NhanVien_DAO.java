package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.PhongBan;

public class NhanVien_DAO {
	ArrayList<NhanVien> dsnv;
	NhanVien nv;

	public NhanVien_DAO() {
		dsnv = new ArrayList<NhanVien>();
		nv = new NhanVien();
	}

	public ArrayList<NhanVien> getAlltbNhanVien() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from NhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				String maNV = rs.getString(1);
				String hoTen = rs.getString("hoTen");
				int tuoi = rs.getInt(3);
				PhongBan pBan = new PhongBan(rs.getString(4));
				double tien = rs.getDouble(5);

				NhanVien s = new NhanVien(maNV, hoTen, tuoi, pBan, tien);
				System.out.println(s);
				dsnv.add(s);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsnv;
	}

	public boolean create(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into NhanVien values(?, ?, ?, ?, ?)");
			stmt.setString(1, nv.getMaNV());
			stmt.setString(2, nv.getHoTen());
			stmt.setInt(3, nv.getTuoi());
			stmt.setString(4, nv.getPhong().getMaPhong());
			stmt.setDouble(5, nv.getTienLuong());

			n = stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;

	}
	
	public boolean update(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update NhanVien set hoTen=?, tuoi=?, maPhong=?, tienLuong=? where maNV=?");
			
			stmt.setString(1, nv.getHoTen());
			stmt.setInt(2, nv.getTuoi());
			stmt.setString(3, nv.getPhong().getMaPhong());
			stmt.setDouble(4, nv.getTienLuong());
			
			stmt.setString(5, nv.getMaNV());
			n = stmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return n > 0;
	}
	
	
	public boolean delete(String maNV) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from NhanVien where maNV = ?");
			stmt.setString(1, maNV);
			n = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	

}
