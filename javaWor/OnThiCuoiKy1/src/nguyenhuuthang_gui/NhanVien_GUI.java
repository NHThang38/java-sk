package nguyenhuuthang_gui;

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
import java.sql.Connection;
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

import nguyenhuuthang_connectDB.ConnectDB;
import nguyenhuuthang_dao.NhanVien_Dao;
import nguyenhuuthang_dao.PhongBan_Dao;
import nguyenhuuthang_entity.NhanVien;
import nguyenhuuthang_entity.PhongBan;

public class NhanVien_GUI extends JFrame implements ActionListener, MouseListener {

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

	PhongBan_Dao pb_dao;
	NhanVien_Dao nv_dao;

	public NhanVien_GUI() {

		// khởi tạo kết nối đến CSDL

		// ********
		ConnectDB.getInstance().connect();
		pb_dao = new PhongBan_Dao();
		nv_dao = new NhanVien_Dao();

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

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
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

		// Tạo và đổ dữ liệu vào comboBox
		b4.add(cboPhong = new JComboBox<String>());
		cboPhong.setEditable(true);

		ArrayList<PhongBan> listPB = pb_dao.getAllPhongBan();
		for (PhongBan p : listPB) {
			cboPhong.addItem(p.getMaPhong());
		}

		b4.add(lblTienLuong = new JLabel("Tiền lương: "));
		b4.add(txtTienLuong = new JTextField());

		lblhoTen.setPreferredSize(lblMaNV.getPreferredSize());
		lblPhong.setPreferredSize(lblMaNV.getPreferredSize());
		lblTuoi.setPreferredSize(lblMaNV.getPreferredSize());

		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		String[] headers = "MaNV;Họ tên;Tuổi;Phòng;Tiền lương".split(";");
		tableModel = new DefaultTableModel(headers, 0);
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
		pnlRight.add(btnXoa = new JButton("Xóa"));
		pnlRight.add(btnLuu = new JButton("Lưu"));
		pnlRight.add(btnXoaTrang = new JButton("Xoá trắng"));

		//
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnTim.addActionListener(this);
		table.addMouseListener(this);
		docDuLieuDBVaoTable();

	}

	public static void main(String[] args) {
		new NhanVien_GUI().setVisible(true);
	}

	private void docDuLieuDBVaoTable() {
		NhanVien_Dao ds = new NhanVien_Dao();
		List<NhanVien> list = ds.getAllNhanVien();

		for (NhanVien s : list) {
			String rowData[] = { s.getMaNV(), s.getHoTen(), s.getTuoi() + "", s.getPhong().getMaPhong(),
					s.getTienLuong() + "" };
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();

		if (object.equals(btnXoaTrang)) {
			xoaTrangAc();
		}
		if (object.equals(btnThem)) {
			themAc();
		}
		if (object.equals(btnXoa)) {
			xoaAc();
		}
		if (object.equals(btnSua)) {
			suaAc();
		}
		if (object.equals(btnTim)) {
			timAc();
		}

	}

	private void xoaTrangAc() {
		txtMaNV.setText("");
		txtMaNV.setEditable(true);
		txthoTen.setText("");
		txtTuoi.setText("");
		cboPhong.setSelectedIndex(0);
		txtTienLuong.setText("");

	}

	private void themAc() {
		// Kiem tra du lieu truoc khi them
		String ma = txtMaNV.getText();
		String hoTen = txthoTen.getText();
		int tuoi = Integer.parseInt(txtTuoi.getText());
		String maPhong = (String) cboPhong.getSelectedItem();
		PhongBan phong = new PhongBan(maPhong);
		double tienLuong = Double.parseDouble(txtTienLuong.getText());
		NhanVien nv = new NhanVien(ma, hoTen, tuoi, phong, tienLuong);
//		if (validData()) {
			try {
				if (nv_dao.create(nv)) {
					String row[] = { nv.getMaNV(), nv.getHoTen(), nv.getTuoi() + "", nv.getPhong().getMaPhong(),
							nv.getTienLuong() + "" };
					tableModel.addRow(row);
				} else {
					JOptionPane.showMessageDialog(this, "Trùng mã !!!");
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Thêm không thành công !!!");
			}
//		}

	}


	private void suaAc() {
		int row = table.getSelectedRow();
		String ma = txtMaNV.getText();
		String hoTen = txthoTen.getText();
		int tuoi = Integer.parseInt(txtTuoi.getText());
		String maPhong = (String) cboPhong.getSelectedItem();
		PhongBan phong = new PhongBan(maPhong);
		double tienLuong = Double.parseDouble(txtTienLuong.getText());
		NhanVien nv = new NhanVien(ma, hoTen, tuoi, phong, tienLuong);
		try {
			nv_dao.update(nv);
			table.setValueAt(txtMaNV.getText(), row, 0);
			table.setValueAt(txthoTen.getText(), row, 1);
			table.setValueAt(txtTuoi.getText(), row, 2);
			table.setValueAt(cboPhong.getSelectedItem(), row, 3);
			table.setValueAt(txtTienLuong.getText(), row, 4);
			JOptionPane.showMessageDialog(this, "Sửa thành công !!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void xoaAc() {
		String ma = txtMaNV.getText();
		int row = table.getSelectedRow();
		if (row >= 0) {
			int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_OPTION) {
				if (nv_dao.delete(ma)) {
					tableModel.removeRow(row);
					JOptionPane.showMessageDialog(this, "Xóa thành công");
				}
			}
		}

	}

	private void timAc() {
		String tim = txtTim.getText().trim();
		if (nv_dao.timKiemNV(tim) == null) {
			xoaDLTableModal();
			docDuLieuDBVaoTable();
			JOptionPane.showMessageDialog(this, "Không tìm thấy");
		} else {
			setModal(nv_dao.timKiemNV(tim));
		}
	}

	private void xoaDLTableModal() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();
	}

	private void setModal(NhanVien nv) {
		xoaDLTableModal();
		String rowData[] = { nv.getMaNV(), nv.getHoTen(), nv.getTuoi() + "", nv.getPhong().getMaPhong(),
				nv.getTienLuong() + "" };
		tableModel.addRow(rowData);
	}
	
	
	private boolean validData() {
		String ma = txtMaNV.getText();
		String ten = txthoTen.getText();
		String tuoi = txtTuoi.getText();
		String phong = (String) cboPhong.getSelectedItem();
		String tienLuong = txtTienLuong.getText();
		if (!(ma.length() > 0 && ma.matches("NV\\d{3}"))) {
			JOptionPane.showMessageDialog(this, "Mã nhân viên phải bắt đầu bằng 2 chữ NV, theo sau là 3 chữ số!");
			txtMaNV.requestFocus();
			return false;
		}
		/// Khong bat dc tieng viet
//		if (!(ten.length() > 0 && ten.matches("[a-zA-Z0-9 ]+"))) {
//			JOptionPane.showMessageDialog(this,
//					"Tên nhân viên gồm nhiều từ ngăn cách bởi khoảng trắng, có thể có số nhưng không chứ ký tự đặc biệt!");
//			txthoTen.requestFocus();
//			return false;
//		}
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMaNV.setText(tableModel.getValueAt(row, 0).toString());
		txtMaNV.setEditable(false);
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
