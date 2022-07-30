package nhanVien;

import java.io.Serializable;
import java.util.ArrayList;



public class DanhSachNhanVien implements Serializable{
	private ArrayList<NhanVien> list;
	public DanhSachNhanVien() {
		list = new ArrayList<NhanVien>();
	}
	public boolean themNV(NhanVien nv) {
		if(list.contains(nv))
			return false;
		list.add(nv);
		return true;
	}
	public boolean xoaNV(String maNV) {
		NhanVien nv = new NhanVien(maNV);
		if(list.contains(nv)) {
			list.remove(nv);
			return true;
		}
		return false;	
	}
	public boolean suaNV(NhanVien nv) {
//		NhanVien nvOld = new NhanVien(maOld);
//		
//		if(list.contains(nvOld)) {
//			nvOld = list.get(list.indexOf(nvOld));
//			nvOld.setHoNV(nvNew.getHoNV());
//			nvOld.setTenNV(nvNew.getTenNV());
//			nvOld.setTuoi(nvNew.getTuoi());
//			nvOld.setPhai(nvNew.isPhai());
//			nvOld.setPhongBan(nvNew.getPhongBan());
//			nvOld.setTienLuong(nvNew.getTienLuong());	
//			nvOld.setHoNV(maNV);
//			nvOld.setTenNV(ho);
//			nvOld.setTenNV(ten);
//			nvOld.setTuoi(tuoi);
//			nvOld.setPhai(phai);
//			nvOld.setPhongBan(phong);
//			nvOld.setTienLuong(tien);	
//			return true;
//		}else {
//			return false;
//		}
		int vitri = list.indexOf(nv);
		if(vitri >= 0 && vitri < list.size()) {
			list.set(vitri, nv);
			return true;
		}
		return false;
	}
	
	public NhanVien timKiem(String maNV) {
		for (NhanVien nhanVien : list) {
			if(nhanVien.getMaNV().equalsIgnoreCase(maNV)) {
				return nhanVien;
			}
		}
		return null;
	}
	public NhanVien getNhanVien(int i) {
		if(i>=0 && i < list.size())
			return list.get(i);
		return null;
	}
	public ArrayList<NhanVien>getList(){
		return list;
	}
	public int tong() {
		return list.size();
	}
//	public boolean kiemTra(String maNV) {
//		NhanVien nv = new NhanVien(maNV);
//		if(list.contains(nv))
//			return true;
//		return false;
//	}
}







