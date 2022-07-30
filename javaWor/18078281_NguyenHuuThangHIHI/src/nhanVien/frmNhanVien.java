package nhanVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;



public class frmNhanVien extends JFrame implements ActionListener,MouseListener{

	
	
	private JPanel pNorth, pCen, pSouth;
	private JLabel lbltitle, lblma, lblho, lblten, lbltuoi, lblphai, lbltien, lblphong, lbltim;
	private JTextField txtma, txtho, txtten, txttuoi,txttien,txttim;
	private JButton btnLuu, btnThoat, btnThem, btnXoa, btnXoarong, btnTim, btnSua;
	private JComboBox<String> cbxphong;
	private JRadioButton rdphai;
	private JTable table;
	private DefaultTableModel dft;
	private JScrollPane scr;
	fileDocGhi fi;
	private DanhSachNhanVien dsnv;
	private static final String tenfile = "gk//demo1";
	public frmNhanVien(DanhSachNhanVien dao) {
		setTitle("^=^");
		
		// North
		pNorth = new JPanel();
		lbltitle = new JLabel("THÔNG TIN NHÂN VIÊN");
		Font fp = new Font("Times New Roman", Font.BOLD, 30);
		lbltitle.setFont(fp);
		lbltitle.setForeground(Color.BLUE);
		pNorth.add(lbltitle);
		pNorth.setBackground(Color.WHITE);
		add(pNorth, BorderLayout.NORTH);
		
		// Center
		pCen = new JPanel();
		//set JLabel
		pCen.add(lblma =  new JLabel("Mã nhân viên: "));
		int x = 1, y = 10, width = 100, height =30;
		lblma.setBounds(x, y, width, height);
		
		y+=30;
		pCen.add(lblho = new JLabel("Họ: "));
		lblho.setBounds(x, y, width, height);
		pCen.add(lblten = new JLabel("Tên nhân viên: "));
		lblten.setBounds(400, y, width, height);
		
		y+=35;
		pCen.add(lbltuoi = new JLabel("Tuổi: "));
		lbltuoi.setBounds(x, y, width, height);
		pCen.add(lblphai = new JLabel("Phái: "));
		lblphai.setBounds(720, y, width, height);
		
		y+=35;
		pCen.add(lbltien = new JLabel("Tiền lương: "));
		lbltien.setBounds(x, y, width, height);
		
		
		
		//set JTextField
		pCen.add(txtma = new JTextField());
		x +=85; y = 10; width = 800;
		txtma.setBounds(x, y, width, height);
		
		y+=35;
		pCen.add(txtho = new JTextField());
		txtho.setBounds(x, y, 310, height);
		pCen.add(txtten = new JTextField());
		txtten.setBounds(491, y, 395, height);
		
		y+=35;
		pCen.add(txttuoi = new JTextField());
		txttuoi.setBounds(x, y, 630, height);
		pCen.add(rdphai = new JRadioButton("Nữ"));
		rdphai.setBounds(820, y, width, height);
		
		y+=35;
		pCen.add(txttien = new JTextField());
		txttien.setBounds(x, y, 400, height);
		pCen.add(lblphong = new JLabel("Phòng:"));
		String[] phong = {"Phòng nhân sự","Phòng tài chính","Phòng kinh doanh"};
		lblphong.setBounds(500, y, width, height);
		pCen.add(cbxphong = new JComboBox(phong));
		cbxphong.setBounds(550, y, 330, height);
		
		//set Table
		String[] headers = "Mã NV;Họ;Tên;Phái;Tuổi;Phòng;Tiền lương".split(";");
		
		dft = new DefaultTableModel(headers,0);
		table = new JTable(dft);
		scr = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scr.setBounds(1, 170, 885, 270);
		pCen.add(scr);
		
		pCen.setLayout(null);
		add(pCen, BorderLayout.CENTER);
		
		// North
		pSouth = new JPanel();
		pSouth.setLayout(new BoxLayout(pSouth, BoxLayout.X_AXIS));
		pSouth.add(btnThem = new JButton("Thêm"));
		pSouth.add(btnXoa = new JButton("Xoá"));
		pSouth.add(btnSua = new JButton("Sửa"));
		pSouth.add(btnXoarong = new JButton("Xoá rỗng"));
		pSouth.add(btnLuu = new JButton("Lưu"));
		pSouth.add(btnThoat = new JButton("Thoát"));
		pSouth.add(btnTim = new JButton("Tìm mã nhân viên"));
		pSouth.add(txttim = new JTextField(20));
		
		add(pSouth, BorderLayout.SOUTH);
		
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoarong.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThoat.addActionListener(this);
		btnTim.addActionListener(this);
		table.addMouseListener(this);
		
		dsnv = new DanhSachNhanVien();
		fi = new fileDocGhi();
		try {
			dsnv = (DanhSachNhanVien) fi.readFromFile(tenfile);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Khong tim thay file");
			
		}
		hienTable();
		
		setSize(900,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
	}
	private void hienTable() {
		for (int i = 0; i < dsnv.tong(); i++) {
			NhanVien nv = dsnv.getNhanVien(i);
			String[] dataRow = {nv.getMaNV(), nv.getHoNV(), nv.getTenNV(),
					nv.getTuoi()+"",Boolean.toString(nv.isPhai()),
					nv.getPhongBan(),nv.getTienLuong()+""};
			dft.addRow(dataRow);
		}		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		
		txtma.setText(table.getValueAt(row, 0).toString());
		txtho.setText(table.getValueAt(row, 1).toString());
		txtten.setText(table.getValueAt(row, 2).toString());
		txttuoi.setText(table.getValueAt(row, 3).toString());
		rdphai.setSelected(false);
		if(table.getValueAt(row, 4).toString().equalsIgnoreCase("true"))
			rdphai.setSelected(true);
		
		cbxphong.setSelectedItem(table.getValueAt(row, 5).toString());
		
		txttien.setText(table.getValueAt(row, 6).toString());
		
		
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
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThem)) 
			themAction();
		if(o.equals(btnXoarong))
			xoaTrangAction();
		if(o.equals(btnXoa))
			xoaAction();
		if(o.equals(btnThoat))
			thoatAction();
		if(o.equals(btnTim))
			timAction();
		if(o.equals(btnSua))
			suaAction();
		if(o.equals(btnLuu)) {
			fi = new fileDocGhi();
			try {
				fi.writeToFile(dsnv, tenfile);
				JOptionPane.showMessageDialog(this, "Lưu thành công!!!");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				
				JOptionPane.showConfirmDialog(this, "Lỗi");
	
			}
		
		}
	}
	//tìm kiếm mã
	public void setModel(NhanVien nv) {
		txtma.setText(nv.getMaNV());
		txtho.setText(nv.getHoNV());
		txtten.setText(nv.getTenNV());
		txttuoi.setText(nv.getTuoi()+"");
		rdphai.setSelected(nv.isPhai());
		cbxphong.setSelectedItem(nv.getPhongBan());
		txttien.setText(nv.getTienLuong()+"");
	}
	private void timAction() {
		if(txttim.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã cần tìm!");
		}else {
			NhanVien nv = dsnv.timKiem(txttim.getText().trim());
			if(nv != null) {
				JOptionPane.showMessageDialog(this, "Đã tìm thấy mã!!!");
				setModel(nv);
				txttim.setText("");
				txttim.requestFocus();
				
			}else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy mã!!!");
			}
		}
		
	}
	
	// sửa 

	private void suaAction() {
		int row = table.getSelectedRow();
		if(row != -1) {
			if(btnSua.getText().equals("Sửa")) {
				btnThem.setEnabled(false);
				btnXoa.setEnabled(false);
				btnXoarong.setEnabled(false);
				btnThoat.setEnabled(false);
				btnLuu.setEnabled(false);
				btnTim.setEnabled(false);
				txtma.setEditable(false);
				btnSua.setText("Cập nhật");
			}else if(btnSua.getText().equals("Cập nhật")) {
				btnThem.setEnabled(true);
				btnXoa.setEnabled(true);
				btnXoarong.setEnabled(true);
				btnThoat.setEnabled(true);
				btnLuu.setEnabled(true);
				btnTim.setEnabled(true);
				txtma.setEditable(true);
				btnSua.setText("Sửa");
				
				NhanVien nvSua = new NhanVien(txtma.getText(), txtho.getText(), 
						txtten.getText(), Integer.parseInt(txttuoi.getText()), 
						rdphai.isSelected(), (String) cbxphong.getSelectedItem(),
						Double.parseDouble(txttien.getText()));
				dsnv.suaNV(nvSua);
				dft.setValueAt(txtma.getText(), row, 0);
				dft.setValueAt(txtho.getText(), row, 1);
				dft.setValueAt(txtten.getText(), row, 2);
				dft.setValueAt(txttuoi.getText(), row, 3);
				dft.setValueAt(rdphai.isSelected()? true: false, row, 4);
				dft.setValueAt(cbxphong.getSelectedItem(), row, 5);
				dft.setValueAt(txttien.getText(), row, 6);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn sách!");
		}	
	}
	public void suaText(boolean trangThai) {
		txtho.setEnabled(trangThai);
		txtten.setEnabled(trangThai);
		txttien.setEnabled(trangThai);
		txttuoi.setEnabled(trangThai);
		cbxphong.setEnabled(trangThai);
	}
	
	//THOÁT
	private void thoatAction() {
		System.exit(0);
		
	}
	private void xoaAction() {
		int row = table.getSelectedRow();
		if(row != -1) {
			String maNV = (String)table.getModel().getValueAt(row, 0);
			int hoiNhac = JOptionPane.showConfirmDialog(this, "Chac chan xoa khong? ", "Chú ý",
							JOptionPane.YES_NO_OPTION);
			if(hoiNhac == JOptionPane.YES_OPTION)
				if(dsnv.xoaNV(maNV))
					dft.removeRow(row);
		}
		xoaTrangAction();
	}
	///Xoá trắng
	private void xoaTrangAction() {
		txtma.setText("");
		txtho.setText("");
		txtten.setText("");
		txttuoi.setText("");
		txttien.setText("");
		rdphai.setSelected(false);
		txtma.requestFocus();
	}
	
	
	//THêm
	private void themAction() {
		NhanVien nv = null;
		String maNV = txtma.getText();
		String ho = txtho.getText();
		String ten = txtten.getText();
		int tuoi = Integer.parseInt(txttuoi.getText());
		boolean phai = (rdphai.isSelected()) ? true:false;
		
		String phong = (String)cbxphong.getSelectedItem();
		double tienLuong = Double.parseDouble(txttien.getText());
		nv = new NhanVien(maNV, ho, ten, tuoi, phai, phong, tienLuong);
		
		if(dsnv.themNV(nv)) {
			String[] row = {nv.getMaNV(), nv.getHoNV(), nv.getTenNV(),
							nv.getTuoi()+"",Boolean.toString(nv.isPhai()),nv.getPhongBan(),nv.getTienLuong()+""};
			dft.addRow(row);
			xoaTrangAction();
		}else   {
			JOptionPane.showMessageDialog(this, "Mã không được trùng nhau");
			txtma.selectAll();
			txtma.requestFocus();
		
	}
	}
}
