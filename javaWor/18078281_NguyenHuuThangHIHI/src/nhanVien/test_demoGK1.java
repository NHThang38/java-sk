package nhanVien;

public class test_demoGK1 {

	public static void main(String[] args) {
		DanhSachNhanVien dao = new DanhSachNhanVien();
		new frmNhanVien(dao).setVisible(true);
	}

}
