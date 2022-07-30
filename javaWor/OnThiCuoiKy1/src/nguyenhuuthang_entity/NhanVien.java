package nguyenhuuthang_entity;

public class NhanVien {
	private String maNV;
	private String hoTen;
	private int tuoi;
	
	private PhongBan phong;
	private double tienLuong;

	public NhanVien() {
		// TODO Auto-generated constructor stub
	}

	public NhanVien(String ma) {
		this.maNV = ma;
	}

	public NhanVien(String maNV, String hoTen, int tuoi, PhongBan phong, double tienLuong) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.tuoi = tuoi;
		this.phong = phong;
		this.tienLuong = tienLuong;
	}



	public double getTienLuong() {
		return tienLuong;
	}


	public void setTienLuong(double tienLuong) {
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
		return phong;
	}


	public void setPhong(PhongBan phong) {
		this.phong = phong;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", tuoi=" + tuoi + ", phong=" + phong + ", tienLuong="
				+ tienLuong + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNV == null) ? 0 : maNV.hashCode());
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
		if (maNV == null) {
			if (other.maNV != null)
				return false;
		} else if (!maNV.equals(other.maNV))
			return false;
		return true;
	}
	
	
	
	
	
	
}
