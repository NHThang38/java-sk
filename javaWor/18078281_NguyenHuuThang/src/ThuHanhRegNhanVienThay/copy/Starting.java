package ThuHanhRegNhanVienThay.copy;

public class Starting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DanhSachNhanVien dao = new DanhSachNhanVien();
		new frmNhanVien(dao).setVisible(true);

	}

}
