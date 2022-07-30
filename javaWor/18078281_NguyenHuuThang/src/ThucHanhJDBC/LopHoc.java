package ThucHanhJDBC;

public class LopHoc {
	private int	maLop;
	private String tenLop;
	private String giaoVienCN;

	
	public LopHoc(int maLop) {
		this(maLop, "", "");
	}


	public LopHoc(int maLop, String tenLop, String giaoVienCN) {
		this.maLop = maLop;
		this.tenLop = tenLop;
		this.giaoVienCN = giaoVienCN;
	}


	public int getMaLop() {
		return maLop;
	}


	public void setMaLop(int maLop) {
		this.maLop = maLop;
	}


	public String getTenLop() {
		return tenLop;
	}


	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}


	public String getGiaoVienCN() {
		return giaoVienCN;
	}


	public void setGiaoVienCN(String giaoVienCN) {
		this.giaoVienCN = giaoVienCN;
	}


	@Override
	public String toString() {
		return "LopHoc [maLop=" + maLop + ", tenLop=" + tenLop + ", giaoVienCN=" + giaoVienCN + "]";
	}
	
	
	
	
	
}
