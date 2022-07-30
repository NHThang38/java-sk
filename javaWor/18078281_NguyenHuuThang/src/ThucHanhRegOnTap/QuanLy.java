package ThucHanhRegOnTap;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class QuanLy implements Serializable{
	private ArrayList<NhanVien> ds;

	public QuanLy() {
		ds = new ArrayList<NhanVien>(10);
	}
		
	public boolean them(NhanVien s) {
		if(ds.contains(s))
			return false;
		return ds.add(s);
	}	
	public boolean xoa_1(int index) {
		if(index >= 0 && index < ds.size()){
			ds.remove(index);
			return true;
		}
		return false;
	}
	public ArrayList<NhanVien> getDs() {
		return ds;
	}
	public NhanVien GetObj(int index) {
		if(index<0||index>ds.size())
		return null;
		return ds.get(index);
	}
	
	public int count() {
		return ds.size();
	}
}
