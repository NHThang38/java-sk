package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import javax.swing.AbstractCellEditor;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import dao.PhongBan_DAO;
import entity.NhanVien;
import entity.PhongBan;

public class NhanVien_GUI extends JFrame implements ActionListener, MouseListener     {
	
	private JTable table;
	private JTextField txtMaNV;
	private JTextField txthoTen;
	private JTextField txtTuoi;
	private JTextField txtTienLuong;
	private JTextField txtTim;
	private JButton btnTim;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnLuu;
	private JButton btnXoaTrang;
	private DefaultTableModel tableModel;
	private JLabel lblPhong;
	private JTextField txtPhong;
	private JComboBox cboPhong;
	private JButton btnSua;
	
	PhongBan_DAO pb_dao;
	NhanVien_DAO nv_dao;
	
  public NhanVien_GUI() {
	  
	  	// khởi tạo kết nối đến CSDL
	  	ConnectDB.getInstance().connect();
	  	
	  	pb_dao = new PhongBan_DAO();
	  	nv_dao = new NhanVien_DAO();
	  
	  	
		
		setTitle("^-^");
		setSize(700, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JPanel pnlNorth;
		add(pnlNorth = new JPanel(), BorderLayout.NORTH);
		JLabel lblTieuDe;
		pnlNorth.add(lblTieuDe = new JLabel("THÔNG TIN NHÂN VIÊN"));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.BLUE);

		Box b = Box.createVerticalBox();

		Box b1, b2, b3, b4, b5;
		JLabel lblMaNV, lblhoTen, lblTuoi, lblTienLuong;

		b.add(b1 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10));
		b1.add(lblMaNV = new JLabel("Mã nhân viên: "));
		b1.add(txtMaNV = new JTextField());

		b.add(b2 = Box.createHorizontalBox()); 
		b.add(Box.createVerticalStrut(10));
		b2.add(lblhoTen = new JLabel("Họ tên: "));
		b2.add(txthoTen = new JTextField());
		
		b.add(b3 = Box.createHorizontalBox()); 
		b.add(Box.createVerticalStrut(10));
		b3.add(lblTuoi = new JLabel("Tuổi: ")); 
		b3.add(txtTuoi = new JTextField());
		b.add(b4 = Box.createHorizontalBox()); 
		b.add(Box.createVerticalStrut(10));
		
		b4.add(lblPhong = new JLabel("Mã phòng: ")); 
		
		//Tạo và đổ dữ liệu vào comboBox
		b4.add(cboPhong = new JComboBox<String>());
		cboPhong.setEditable(true);		
		
		ArrayList<PhongBan> listPB = pb_dao.getAllPhongBan();
		for(PhongBan p : listPB) {
			cboPhong.addItem(p.getMaPhong());
		}
		
				
		
		b4.add(lblTienLuong = new JLabel("Tiền lương: ")); 
		b4.add(txtTienLuong = new JTextField());
		
		lblhoTen.setPreferredSize(lblMaNV.getPreferredSize());
		lblPhong.setPreferredSize(lblMaNV.getPreferredSize());
		lblTuoi.setPreferredSize(lblMaNV.getPreferredSize());

		b.add(b5 = Box.createHorizontalBox()); 
		b.add(Box.createVerticalStrut(10));
		String [] headers = "MaNV;Họ tên;Tuổi;Phòng;Tiền lương".split(";");
		tableModel=new DefaultTableModel(headers,0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		
		b5.add(scroll);
		add(b, BorderLayout.CENTER);
		
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		add(split, BorderLayout.SOUTH);
		JPanel pnlLeft, pnlRight;
		split.add(pnlLeft = new JPanel());
		split.add(pnlRight = new JPanel());

		pnlLeft.add(new JLabel("Nhập mã số cần tìm: "));
		pnlLeft.add(txtTim = new JTextField(10));
		pnlLeft.add(btnTim = new JButton("Tìm"));

		pnlRight.add(btnThem = new JButton("Thêm"));
		pnlRight.add(btnSua = new JButton("Sửa"));
		pnlRight.add(btnXoaTrang = new JButton("Xóa trắng"));
		pnlRight.add(btnXoa = new JButton("Xóa"));
		pnlRight.add(btnLuu = new JButton("Lưu"));
		
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnTim.addActionListener(this);
		table.addMouseListener(this);
		
		DocDuLieuDatabaseVaoTable();
		
		
		
	}

public static void main(String[] args) {
	  new NhanVien_GUI().setVisible(true);
 }


private void DocDuLieuDatabaseVaoTable() {
		NhanVien_DAO ds = new NhanVien_DAO();
		List<NhanVien> list = ds.getAlltbNhanVien();
		for(NhanVien s : list) {
			String []rowdata = {s.getMaNV(), s.getHoTen(), s.getTuoi()+"", s.getPhong().getMaPhong(), s.getTienLuong()+""};
			tableModel.addRow(rowdata);
		}
		table.setModel(tableModel);
	
}

@Override
public void actionPerformed(ActionEvent e) {
	Object object = e.getSource();
	if(object.equals(btnXoaTrang)) {
		xoaTrangAc();
	}
	if(object.equals(btnThem)) {
		themTrangAc();
	}
	if(object.equals(btnXoa)) {
		xoaAc();
	}
	if(object.equals(btnSua)) {
		suaAc();
	}
	
}




private void themTrangAc() {
	//PhaiKiemTra Rang buoc
	String ma = txtMaNV.getText();
	String hoTen = txthoTen.getText();
	int tuoi = Integer.parseInt(txtTuoi.getText());
	String phong = cboPhong.getSelectedItem().toString();
	PhongBan phBan = new PhongBan(phong);
	double tienLuong = Double.parseDouble(txtTienLuong.getText());
	NhanVien nv = new NhanVien(ma, hoTen, tuoi, phBan, tienLuong);
	try {
		nv_dao.create(nv);
		String []row = {nv.getMaNV(), nv.getHoTen(), nv.getTuoi()+"", nv.getPhong().getMaPhong(), nv.getTienLuong()+""};
		tableModel.addRow(row);
		
	} catch (Exception e) {
		JOptionPane.showMessageDialog(this, "Trung ma !!!");
	}
	
}


private void suaAc() {
	int row = table.getSelectedRow();
	String ma = txtMaNV.getText();
	String hoTen = txthoTen.getText();
	int tuoi = Integer.parseInt(txtTuoi.getText());
	String phong = cboPhong.getSelectedItem().toString();
	PhongBan phBan = new PhongBan(phong);
	double tienLuong = Double.parseDouble(txtTienLuong.getText());
	
	NhanVien nv = new NhanVien(ma, hoTen, tuoi, phBan, tienLuong);
	if(row >= 0) {
		if(nv_dao.update(nv)) {
//			table.setValueAt(txtMaNV.getText(), row, 0);
			table.setValueAt(txthoTen.getText(), row, 1);
//			tableModel.setValueAt(txthoTen.getText(), row, 1);  tableModel hoặc table đều được
			table.setValueAt(txtTuoi.getText(), row, 2);
			table.setValueAt(cboPhong.getSelectedItem().toString(), row, 3);
			table.setValueAt(txtTienLuong.getText(), row, 4);
		}
	}
	
	
}

private void xoaAc() {
	int row = table.getSelectedRow();
	if(row >= 0) {
		String ma = txtMaNV.getText();
		int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc xóa không?", "Cảnh báo", JOptionPane.YES_NO_OPTION);
		if(hoiNhac == JOptionPane.YES_OPTION) {
			if(nv_dao.delete(ma)) {
				tableModel.removeRow(row);
				JOptionPane.showMessageDialog(this, "Xóa thành công");
			}
		}
		
	}
	
	
}

private void xoaTrangAc() {
	txtMaNV.setText("");
	txthoTen.setText("");
	txtTuoi.setText("");
	cboPhong.setSelectedIndex(0);
	txtTienLuong.setText("");
	txtTim.setText("");
	
	
}

@Override
public void mouseClicked(MouseEvent e) {
	int row = table.getSelectedRow();
	txtMaNV.setText(tableModel.getValueAt(row, 0).toString());
	txthoTen.setText(tableModel.getValueAt(row, 1).toString());
	txtTuoi.setText(tableModel.getValueAt(row, 2).toString());
	cboPhong.setSelectedItem(tableModel.getValueAt(row, 3).toString());
	txtTienLuong.setText(tableModel.getValueAt(row, 4).toString());
	
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}



}
