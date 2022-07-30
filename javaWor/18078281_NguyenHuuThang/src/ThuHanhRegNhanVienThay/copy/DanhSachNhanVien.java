package ThuHanhRegNhanVienThay.copy;


import java.util.*;
public class DanhSachNhanVien {
	private ArrayList<NhanVien> list;
	
	public DanhSachNhanVien() {
		
		list = new ArrayList<NhanVien>();
}
	public boolean themNhanVien(NhanVien nv) {
		if(list.contains(nv))
			return false;
		  list.add(nv);
		return true;
	}
	
	
	public ArrayList<NhanVien> getList() {
		return list;
	}
}
