package Tuan8LyThuyet;

import java.io.Serializable;

public class NhanVien implements Serializable {
	private int maNV;
	private String ho;
	private String ten;
	private int tuoi;
	private boolean phai;
	private String phong;
	private double tienLuong;

	public NhanVien(int maNV, String ho, String ten, int tuoi, boolean phai, String phong, double tienLuong) {

		this.maNV = maNV;
		this.ho = ho;
		this.ten = ten;
		this.tuoi = tuoi;
		this.phai = phai;
		this.phong = phong;
		this.tienLuong = tienLuong;
	}

	public NhanVien(int maNV) {

		this(maNV, "", "", 0, true, "", 0);
//		this.maNV = maNV;
	}

	public int getMaNV() {
		return maNV;
	}

	public void setMaNV(int maNV) {
		this.maNV = maNV;
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

	public double getTienLuong() {
		return tienLuong;
	}

	public void setTienLuong(double tienLuong) {
		this.tienLuong = tienLuong;
	}

	public String getPhong() {
		return phong;
	}

	public void setPhong(String phong) {
		this.phong = phong;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maNV;
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
		if (maNV != other.maNV)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", ho=" + ho + ", ten=" + ten + ", tuoi=" + tuoi + ", phai=" + phai
				+ ", phong=" + phong + ", tienLuong=" + tienLuong + "]";
	}


}
