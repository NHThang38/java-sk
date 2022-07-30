package nhanVien;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class fileDocGhi {
	public static void writeToFile(DanhSachNhanVien dao, String file) 
			throws Exception{
		ObjectOutputStream out = null;
		out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(dao);
		out.close();
	}
	
	public Object readFromFile (String file) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Object list = ois.readObject();
		ois.close();
		return list;
	}
}