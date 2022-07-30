package BaiNhanVienFile;

import java.io.Serializable;

public class NhanVien implements Serializable {
	private int ma;
	private String ho;
	private String ten;
	private int tuoi;
	private boolean phai;
	private String phong;
	private double luong;
	
	public NhanVien(int ma) {
		this(ma, "", "",0, true, "",0);
	}
	
	public NhanVien(int ma, String ho, String ten, int tuoi, boolean phai, String phong, double luong) {

		this.ma = ma;
		this.ho = ho;
		this.ten = ten;
		this.tuoi = tuoi;
		this.phai = phai;
		this.phong = phong;
		this.luong = luong;
	}

	public int getMa() {
		return ma;
	}

	public void setMa(int ma) {
		this.ma = ma;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public boolean isPhai() {
		return phai;
	}

	public void setPhai(boolean phai) {
		this.phai = phai;
	}

	public String getPhong() {
		return phong;
	}

	public void setPhong(String phong) {
		this.phong = phong;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ma;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		if (ma != other.ma)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NhanVien [ma=" + ma + ", ho=" + ho + ", ten=" + ten + ", tuoi=" + tuoi + ", phai=" + phai + ", phong="
				+ phong + ", luong=" + luong + "]";
	}
	
	
	
	
	
}
