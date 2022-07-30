package nguyenhuuthang_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import nguyenhuuthang_connectDB.ConnectDB;
import nguyenhuuthang_entity.NhanVien;
import nguyenhuuthang_entity.PhongBan;

public class NhanVien_Dao {
	ArrayList<NhanVien> dsnv;
	NhanVien nv;

	public NhanVien_Dao() {
		dsnv = new ArrayList<NhanVien>();
		nv = new NhanVien();
	}

	public ArrayList<NhanVien> getAllNhanVien() {
		try {

			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from NhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				String maNV = rs.getString(1);
				String hoTen = rs.getString(2);
				int tuoi = rs.getInt(3);
				PhongBan pBan = new PhongBan(rs.getString(4));
				double tienLuong = rs.getDouble(5);

				NhanVien s = new NhanVien(maNV, hoTen, tuoi, pBan, tienLuong);
				dsnv.add(s);
			}

		} catch (SQLException e) {
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

		} catch (SQLException e) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean delete(String maNV) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from NhanVien where maNV =?");
			stmt.setString(1, maNV);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public NhanVien timKiemNV(String ma) {
		NhanVien nv1 = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from NhanVien where maNV = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString(1);
				String hoTen = rs.getString(2);
				int tuoi = rs.getInt(3);
				PhongBan pBan = new PhongBan(rs.getString(4));
				double tienLuong = rs.getDouble(5);

				nv1 = new NhanVien(maNV, hoTen, tuoi, pBan, tienLuong);
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}

		return nv1;
	}

}
