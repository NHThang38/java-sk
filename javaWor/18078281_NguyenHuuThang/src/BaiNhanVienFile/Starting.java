package BaiNhanVienFile;



public class Starting {
	public static void main(String[] args) {
		DanhSachNhanVien dao = new DanhSachNhanVien();
		new FormNhanVien(dao);
	}
}
