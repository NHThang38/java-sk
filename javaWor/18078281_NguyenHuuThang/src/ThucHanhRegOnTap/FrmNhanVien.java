package ThucHanhRegOnTap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class FrmNhanVien extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtDiaChi;
	private JTextField txtTuoi;
	private JTextField txtMaphong;
	
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnLuu;
	
	private JTable table;
	private JTextField txtMess;
	private JButton btnXoaRong;

	private QuanLy thuvien;
	private DefaultTableModel tableModel;

	private JButton btnLoc;

	public FrmNhanVien() {
		setTitle("Quản lý sách");
		setSize(900, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		buildUI();
		setVisible(true);
	}

	private void buildUI() {

		// Phần North
		JPanel pnlNorth;
		add(pnlNorth = new JPanel(), BorderLayout.NORTH);
		pnlNorth.setPreferredSize(new Dimension(0, 180));
		pnlNorth.setBorder(BorderFactory.createTitledBorder("Records Editor"));
		pnlNorth.setLayout(null); // Absolute layout

		JLabel lblMaSach, lblTuaSach, lblTacGia, lblNamXB, lblNhaXB, lblSoTrang, lblDonGia, lblISBN;
		pnlNorth.add(lblMaSach = new JLabel("Mã Nhân Viên: "));
		pnlNorth.add(lblTuaSach = new JLabel("Họ Tên: "));
		pnlNorth.add(lblTacGia = new JLabel("địa chỉ: "));
		pnlNorth.add(lblNamXB = new JLabel("Tuổi: "));
		pnlNorth.add(lblNhaXB = new JLabel("Mã phòng: "));
		
		pnlNorth.add(txtMa = new JTextField());
		pnlNorth.add(txtTen = new JTextField());
		pnlNorth.add(txtDiaChi = new JTextField());
		pnlNorth.add(txtTuoi = new JTextField());
		pnlNorth.add(txtMaphong = new JTextField());
		
		pnlNorth.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));

		int w1 = 100, w2 = 300, h = 20;
		lblMaSach.setBounds(20, 20, w1, h);
		txtMa.setBounds(120, 20, 200, h);

		lblTuaSach.setBounds(20, 45, w1, h);
		txtTen.setBounds(120, 45, w2, h);
		lblTacGia.setBounds(450, 45, w1, h);
		txtDiaChi.setBounds(570, 45, w2, h);

		lblNamXB.setBounds(20, 70, w1, h);
		txtTuoi.setBounds(120, 70, w2, h);
		lblNhaXB.setBounds(450, 70, w1, h);
		txtMaphong.setBounds(570, 70, w2, h);

	
		txtMess.setBounds(20, 145, 550, 20);

		// Phần Center
		JPanel pnlCenter;
		add(pnlCenter = new JPanel(), BorderLayout.CENTER);
		pnlCenter.add(btnThem = new JButton("Thêm - kiểm tra dữ liệu ..."));
		pnlCenter.add(btnXoaRong = new JButton("Xóa rỗng"));

		// Phần South
		JScrollPane scroll;
		String[] headers = "MaNhanVien;HoTenNV;Tuoi;DiaChi; MaPhong".split(";");

		tableModel = new DefaultTableModel(headers, 0);
		add(scroll = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.SOUTH);
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách"));
		table.setRowHeight(20);
		scroll.setPreferredSize(new Dimension(0, 350));

		// Xử lý

		thuvien = new QuanLy();
	
		updateTableData(); // Cập nhật dữ liệu cho table
		
		btnThem.addActionListener(this);		
		btnXoaRong.addActionListener(this);
		
		
	}

	private void fillForm(int row) {
		if (row != -1) {
			String ma = (String) table.getValueAt(row, 0);
			NhanVien s = new NhanVien(ma);
			ArrayList<NhanVien> dsSach = thuvien.getDs();
			if (thuvien.getDs().contains(s)) {
				s = dsSach.get(dsSach.indexOf(s));
				txtMa.setText(s.getMaNV());
				txtTen.setText(s.getHoTen());
				txtDiaChi.setText(s.getDiaChi());
				txtMaphong.setText(s.getEmail());
				txtTuoi.setText(s.getTuoi() + "");				
				txtMa.setEditable(false);
			}
		}
	}

	private void updateTableData() {
		XoaHetDuLieuTrenTableModel();
		for (NhanVien s : thuvien.getDs()) {
			tableModel.addRow(new Object[] { s.getMaNV(), s.getHoTen(),
					 s.getTuoi(),s.getDiaChi(), s.getEmail(),
					});
		}

		table.setModel(tableModel);

	}

	private void XoaHetDuLieuTrenTableModel() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (validData()) {
				NhanVien s = revertSachFromTextfields();
				if (thuvien.them(s)) {
					txtMess.setText("Thêm thành công..........");
					updateTableData(); // Cập nhật dữ liệu xuống bảng
				} else {
					showMessage("Error: Trùng mã số", txtMa);
				}
			}
		} else if (o.equals(btnXoaRong)) {
			clearTextfields();
		}

	}

	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		txtMess.setText(message);
	}

	private boolean validData() {
		String ma = txtMa.getText().trim();
		String ten = txtTen.getText().trim();
		String diachi = txtDiaChi.getText().trim();
		String tuoi = txtTuoi.getText().trim();
		String phong = txtMaphong.getText().trim();
		
		if ( !(ma.length() > 0 && ma.matches("\\d{3}NV")) ) { //[0-9]{3}NV
			JOptionPane.showMessageDialog(this, "Mã nhân viên phải bắt đầu bằng 3 chữ số theo sau là 2 chữ cái NV");
			txtMa.requestFocus();
			return false;
			
		}
		if ( !(ten.length() > 0 && ten.matches("[a-zA-Z0-9' ]+")) ) { //[0-9]{3}NV
			JOptionPane.showMessageDialog(this, "Tên nhân viên có thể gồm nhiều từ ngăn cách bởi khoảng trắng và có thể chứa số nhưng không chứa ký tự đặc biệt");
			txtTen.requestFocus();
			return false;
			
		}
		if ( !(diachi.length() > 0 && diachi.matches("[a-zA-Z0-9 ]+")) ) { //[0-9]{3}NV
			JOptionPane.showMessageDialog(this, "Địa chỉ nhập theo mẫu a-zA-Z0-9 ]+");
			txtDiaChi.requestFocus();
			return false;	
		}
		if ( !(tuoi.length() > 0 && tuoi.matches("[0-9]+")) ) {
			JOptionPane.showMessageDialog(this, " Tuổi nhập sai định dạng hoặc để trống");
			txtTuoi.requestFocus();
			return false;
		}else {
			int x = Integer.parseInt(tuoi);
			if(!( x >= 18 && x <= 60)) {
				JOptionPane.showMessageDialog(this, " Tuổi nhập từ 18-60 tuổi");
				txtTuoi.requestFocus();
				return false;
			}
		}
		if ( !(phong.length() > 0 && phong.matches("TP[0-9]{2}(CV|TN)")) ) { //[0-9]{3}NV
			JOptionPane.showMessageDialog(this, "Phòng nhập theo mẫu: TP[0-9]{2}[CV||TV] ");
			txtMaphong.requestFocus();
			return false;	
		}
		
		
		return true;
		
	}

	private NhanVien revertSachFromTextfields() {
		String maSach = txtMa.getText().trim();
		String tuaSach = txtTen.getText().trim();
		String tacGia = txtDiaChi.getText().trim();

		String nam = txtTuoi.getText().trim();
		int namXB = nam.length() == 0 ? 0 : Integer.parseInt(nam);
		// Để trống thì coi như là 0

		String nhaXB = txtMaphong.getText().trim();
		
		return new NhanVien(maSach, tuaSach, tacGia, namXB, nhaXB);
	}

	private void clearTextfields() {
		txtMa.setText("");
		txtTen.setText("");
		txtDiaChi.setText("");
		txtTuoi.setText("");
		txtMaphong.setText("");
	
		txtMa.setEditable(true);
		txtMa.requestFocus();
	}
	public static void main(String[] args) {
		new FrmNhanVien();

	}
}
