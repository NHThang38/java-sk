package Tuan8LyThuyet;

public class Starting {
	public static void main(String[] args) {
		DanhSachNhanVien dao = new DanhSachNhanVien();
		new FormThongTinNhanVien(dao);
	}
}
