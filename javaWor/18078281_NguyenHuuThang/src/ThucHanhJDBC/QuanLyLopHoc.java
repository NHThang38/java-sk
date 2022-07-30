package ThucHanhJDBC;

import java.util.ArrayList;


public class QuanLyLopHoc {
	private ArrayList<LopHoc> list;

	public QuanLyLopHoc(ArrayList<LopHoc> list) {
		this.list = list;
	}
	
	public boolean themLH(LopHoc lh) {
		if(list.contains(lh)) {
			return false;
		}
		list.add(lh);
		return true;
	}
	
	public boolean xoaLH(int maLH) {
		LopHoc lh = new LopHoc(maLH);
		if(list.contains(lh)) {
			list.remove(lh);
			return true;
		}
		return false;
	}
	
	public boolean suaLH (int ma, LopHoc lhMoi) {
		LopHoc lhCu = new LopHoc(ma);
		if(list.contains(lhCu)) {
			lhCu = list.get(list.indexOf(lhCu));
			lhCu.setTenLop(lhMoi.getTenLop());
			lhCu.setGiaoVienCN(lhMoi.getGiaoVienCN());
			return true;
			
		}
		return false;
	}
	
	public LopHoc getLH(int i) {
		if(i >= 0 && i < list.size()) {
			return list.get(i);
		}
		return null;
	}
	
	public ArrayList<LopHoc> getDsLH() {
		return list;
	}
	
	public int tong() {
		return list.size();
	}
	
	

}

