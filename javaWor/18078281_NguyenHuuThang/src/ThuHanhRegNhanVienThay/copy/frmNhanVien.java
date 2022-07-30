package ThuHanhRegNhanVienThay.copy;

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
import java.io.FileReader;
import java.io.FileWriter;
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

public class frmNhanVien extends JFrame implements ActionListener {
	private DanhSachNhanVien dao;
	private List<NhanVien> list;
	private JTable table;
	private JTextField txtMaNV;
	private JTextField txtHo;
	private JTextField txtTen;
	private JTextField txtTuoi;
	private JRadioButton radNu;
	private JButton btnThem;
	private JButton btnXoaTrang;
	private DefaultTableModel tableModel;
	private JTextField txtPhong;
	private JLabel lblPhong;

	public frmNhanVien(DanhSachNhanVien dao) {
		setTitle("^-^");
		setSize(700, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		this.dao = dao;
		list = dao.getList();

		JPanel pnlNorth;
		add(pnlNorth = new JPanel(), BorderLayout.NORTH);
		JLabel lblTieuDe;
		pnlNorth.add(lblTieuDe = new JLabel("Thông tin nhân viên"));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.BLUE);

		Box b = Box.createVerticalBox();

		Box b1, b2, b3, b4, b5;
		JLabel lblMaNV, lblHo, lblTen, lblTuoi, lblPhai, lblTienLuong;

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(lblMaNV = new JLabel("Mã nhân viên: "));
		b1.add(txtMaNV = new JTextField());

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(lblHo = new JLabel("Họ: "));
		b2.add(txtHo = new JTextField());
		b2.add(lblTen = new JLabel("Tên nhân viên: "));
		b2.add(txtTen = new JTextField());

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(lblTuoi = new JLabel("Tuổi: "));
		b3.add(txtTuoi = new JTextField());

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));

		b4.add(lblPhong = new JLabel("Phòng: "));
		b4.add(txtPhong = new JTextField());

		lblHo.setPreferredSize(lblMaNV.getPreferredSize());
		lblPhong.setPreferredSize(lblMaNV.getPreferredSize());
		lblTuoi.setPreferredSize(lblMaNV.getPreferredSize());

		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		String[] headers = "MaNV;Ho;Ten;tuoi;Phong".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		// table.setAutoCreateRowSorter(true);
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		b5.add(scroll);
		add(b, BorderLayout.CENTER);

		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		add(split, BorderLayout.SOUTH);
		JPanel pnlRight;

		split.add(pnlRight = new JPanel());
		pnlRight.add(btnThem = new JButton("Thêm"));
		pnlRight.add(btnXoaTrang = new JButton("Xóa Trắng"));

		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

			if (o.equals(btnThem)) {
				if (validData()) {
					NhanVien nv = revertSachFromTextfields();
					if (dao.themNhanVien(nv)) {
						hienTable(); // Cáº­p nháº­t dá»¯ liá»‡u xuá»‘ng báº£ng

					} else {
						JOptionPane.showMessageDialog(this, "Trùng mã");
					}
				}
			} else if (o.equals(btnXoaTrang)) {
				clearTextfields();
			}
		}

	

	public void hienTable() {
		XoaHetDuLieuTrenTableModel();
		for (NhanVien nv : dao.getList()) {
			tableModel.addRow(new Object[] { nv.getMaNV(), nv.getHo(), nv.getTen(), nv.getTuoi(), nv.getPhong(), });
		}

		table.setModel(tableModel);
	}

	private boolean validData() {
		String ma = txtMaNV.getText().trim();
		String ho = txtHo.getText().trim();
		String ten = txtTen.getText().trim();
		String tuoi = txtTuoi.getText().trim();
		String phong = txtPhong.getText().trim();

//		if (!(ma.length() > 0 && ma.matches("[A-Z]\\d{3}"))) {
//			JOptionPane.showMessageDialog(this, "Error: Mã nhân viên theo mẫu: [A-Z]\\d{3}");
//			txtMaNV.requestFocus();
//			return false;
//		}
		if (!(ho.length() > 0 && ho.matches("^[a-zA-Z_+. ]+$"))) { //ky tu dac biet
			JOptionPane.showMessageDialog(this, "Error: Họ nhân viên theo mẫu: [a-zA-Z' ]");
			txtHo.requestFocus();
			return false;
		}
		if (!(ten.length() > 0 && ten.matches("[a-zA-Z' ]+"))) {
			JOptionPane.showMessageDialog(this, "Error: Họ nhân viên theo mẫu: [a-zA-Z' ]");
			txtTen.requestFocus();
			return false;
		}
		if(tuoi.length() == 0) {
			JOptionPane.showMessageDialog(this, "Error: Tuổi không được để trống");
			txtTuoi.requestFocus();
			return false;
		}else {
			try {
					int x = Integer.parseInt(tuoi);
					if(x <= 0) {
						JOptionPane.showMessageDialog(this, "Error: Tuổi phải nhập số nguyên dương");
						txtTuoi.requestFocus();
						return false;
					}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error: Tuổi phải nhập số");
			}
		}


		return true;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DanhSachNhanVien dao = new DanhSachNhanVien();
		new frmNhanVien(dao).setVisible(true);

	}

	private void XoaHetDuLieuTrenTableModel() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();

	}

	private void clearTextfields() {
		txtMaNV.setText("");
		txtHo.setText("");
		txtTen.setText("");
		txtTuoi.setText("");
		txtPhong.setText("");
		txtMaNV.requestFocus();
	}

	private NhanVien revertSachFromTextfields() {
		String ma = txtMaNV.getText().trim();
		String ho = txtHo.getText().trim();
		String ten = txtTen.getText().trim();
		String tuoi = txtTuoi.getText().trim();
		int tuoiInt = tuoi.length() == 0 ? 0 : Integer.parseInt(tuoi);
		String phong = txtPhong.getText().trim();
		return new NhanVien(ma, ho, ten, tuoiInt, phong);
	}

	private void themActions() {

		String ma = txtMaNV.getText();
		String ho = txtHo.getText();
		String ten = txtTen.getText();
		String tuoi = txtTuoi.getText();
		String phong = txtPhong.getText();
	}
}
