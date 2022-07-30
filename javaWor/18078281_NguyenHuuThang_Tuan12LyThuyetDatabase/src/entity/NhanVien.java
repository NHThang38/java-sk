package entity;

public class NhanVien {
	private String maNV;
	private String hoTen;
	private int tuoi;
	
	private PhongBan Phong;
	
	private double tienLuong;
	
	
	public NhanVien() {
		// TODO Auto-generated constructor stub
	}


	public NhanVien(String maNV, String hoTen, int tuoi, PhongBan phong, double tienLuong) {
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.tuoi = tuoi;
		Phong = phong;
		this.tienLuong = tienLuong;
	}


	public String getMaNV() {
		return maNV;
	}


	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}


	public String getHoTen() {
		return hoTen;
	}


	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}


	public int getTuoi() {
		return tuoi;
	}


	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}


	public PhongBan getPhong() {
		return Phong;
	}


	public void setPhong(PhongBan phong) {
		Phong = phong;
	}


	public double getTienLuong() {
		return tienLuong;
	}


	public void setTienLuong(double tienLuong) {
		this.tienLuong = tienLuong;
	}


	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", tuoi=" + tuoi + ", Phong=" + Phong + ", tienLuong="
				+ tienLuong + "]";
	}
	

	
	
	
}
