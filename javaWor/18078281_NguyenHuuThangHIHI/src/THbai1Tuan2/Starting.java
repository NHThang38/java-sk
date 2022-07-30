package THbai1Tuan2;

public class Starting {
	public static void main(String[] args) {
		DanhSachNhanVien dao = new DanhSachNhanVien();
		new FormThongTinNhanVien(dao).setVisible(true);;
	}
}
