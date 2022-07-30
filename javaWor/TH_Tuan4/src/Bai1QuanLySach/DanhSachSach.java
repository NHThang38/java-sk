package Bai1QuanLySach;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhSachSach implements Serializable {
	private ArrayList<Sach> list;

	public DanhSachSach() {
		list = new ArrayList<Sach>();
	}

	public boolean themSach(Sach sach) {
		if (list.contains(sach)) {
			return false;
		}
		list.add(sach);
		return true;
	}

	public boolean xoaSach(int maSach) {
		Sach sach = new Sach(maSach);
		if (list.contains(sach)) {
			list.remove(sach);
			return true;
		}
		return false;

	}

	public Sach timKiem(int maSach) {
		Sach sach = new Sach(maSach);
		if(list.contains(sach)) {
			return list.get(list.indexOf(sach));
		}
		return null;
	}
	
	public Sach getSach(int i) {
		if(i>=0 && i < list.size()) {
			return list.get(i);
		}
		return null;
	}
	
//	public ArrayList<Sach> getList(){
//		return list;
//	}
	public int tong() {
		return list.size();
	}

}
