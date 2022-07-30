package Bai1Tuan4QuanLySach;

import java.io.Serializable;

public class Sach implements Serializable{
	private int maSach;
	private String tuaSach;
	private String tacGia;
	private int namXB ;
	private String nhaXB;
	private int soTrang;
	private double donGia;
	private String iSBN;
	
	public Sach(int maSach) {
		this(maSach, "", "", 0, "", 0, 0, "");
	}

	public Sach( String tuaSach, String tacGia, int namXB, String nhaXB, int soTrang, double donGia,
			String iSBN) {
	

		this.tuaSach = tuaSach;
		this.tacGia = tacGia;
		this.namXB = namXB;
		this.nhaXB = nhaXB;
		this.soTrang = soTrang;
		this.donGia = donGia;
		this.iSBN = iSBN;
	}


	public Sach(int maSach, String tuaSach, String tacGia, int namXB, String nhaXB, int soTrang, double donGia,
			String iSBN) {
	
		this.maSach = maSach;
		this.tuaSach = tuaSach;
		this.tacGia = tacGia;
		this.namXB = namXB;
		this.nhaXB = nhaXB;
		this.soTrang = soTrang;
		this.donGia = donGia;
		this.iSBN = iSBN;
	}


	public int getMaSach() {
		return maSach;
	}


	public void setMaSach(int maSach) {
		this.maSach = maSach;
	}


	public String getTuaSach() {
		return tuaSach;
	}


	public void setTuaSach(String tuaSach) {
		this.tuaSach = tuaSach;
	}


	public String getTacGia() {
		return tacGia;
	}


	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}


	public int getNamXB() {
		return namXB;
	}


	public void setNamXB(int namXB) {
		this.namXB = namXB;
	}


	public String getNhaXB() {
		return nhaXB;
	}


	public void setNhaXB(String nhaXB) {
		this.nhaXB = nhaXB;
	}


	public int getSoTrang() {
		return soTrang;
	}


	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}


	public double getDonGia() {
		return donGia;
	}


	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}


	public String getiSBN() {
		return iSBN;
	}


	public void setiSBN(String iSBN) {
		this.iSBN = iSBN;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maSach;
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
		Sach other = (Sach) obj;
		if (maSach != other.maSach)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Sach [maSach=" + maSach + ", tuaSach=" + tuaSach + ", tacGia=" + tacGia + ", namXB=" + namXB
				+ ", nhaXB=" + nhaXB + ", soTrang=" + soTrang + ", donGia=" + donGia + ", iSBN=" + iSBN + "]";
	}
	
	
	

}
