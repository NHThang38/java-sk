package Bai1Tuan4QuanLySach;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class filedDocGhi {
	public static void writeToFile(DanhSachSach dss, String file) throws Exception {
		ObjectOutputStream out = null;
		out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(dss); /// la dsnv dss
		out.close();
		
	}
	
	public Object readFromFile(String file) throws Exception{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Object list = ois.readObject();
		ois.close();
		return list;
	}

}
