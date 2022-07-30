package Bai1Tuan4QuanLySach;

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
	
	public boolean suaSach(int ma, Sach sachMoi) {
		Sach sachCu = new Sach(ma);
		if (list.contains(sachCu)) {
			sachCu = list.get(list.indexOf(sachCu));
			sachCu.setTuaSach(sachMoi.getTuaSach());
			sachCu.setTacGia(sachMoi.getTacGia());
			sachCu.setNamXB(sachMoi.getNamXB());
			sachCu.setNhaXB(sachMoi.getNhaXB());
			sachCu.setSoTrang(sachMoi.getSoTrang());
			sachCu.setDonGia(sachMoi.getDonGia());
			sachCu.setiSBN(sachMoi.getiSBN());
			return true;
		}
		return false;
	}

	public Sach timKiem(int maSach) {
		Sach sach = new Sach(maSach);
		if (list.contains(sach)) {
			return list.get(list.indexOf(sach));
		}
		return null;
	}

	public Sach getSach(int i) {
		if (i >= 0 && i < list.size()) {
			return list.get(i);
		}
		return null;
	}

	public ArrayList<Sach> getDsSach() {
		return list;
	}

	public int tong() {
		return list.size();
	}

}
