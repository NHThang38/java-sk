package ThuHanhRegNhanVien;

public class NhanVien {
	private String maNV;
	private String ho;
	private String ten;
	private int tuoi;
	private String Phong;
	public NhanVien(String maNV, String ho, String ten, int tuoi,
			String Phong) {
		super();
		this.maNV = maNV;
		this.ho = ho;
		this.ten = ten;
		this.tuoi = tuoi;
		
		this.Phong = Phong;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
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
	
	public String getPhong() {
		return Phong;
	}
	public void setPhong(String phong) {
		Phong = phong;
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
	public String toString() {
		return maNV + ";" + ho + ";"
				+ ten + ";" + tuoi	+ ";" + Phong;
	}
} 

