package BaiNhanVienFile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

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
import javax.swing.table.DefaultTableModel;

public class FormNhanVien extends JFrame implements ActionListener, MouseListener {

	private JPanel pNorth, pCenter, pSouth;
	private JLabel lblTitle, lblmaNV, lblHo, lblTen, lblTuoi, lblPhai, lblPhong, lblLuong;
	private JTextField txtMa, txtHo, txtTen, txtTuoi, txtLuong;
	private JRadioButton radNu;

	JComboBox cbPhong;

	JTable table;
	DefaultTableModel tableModel;

	JSplitPane jSplitPane;
	private JLabel lblNhap;
	JTextField txtNhap;
	JButton btnTim, btnThem, btnXoaTrang, btnXoa, btnLuu;

	DanhSachNhanVien dsnv;

	fileDocGhi fi;
	
	private static final String tenfile = "data//abcd.txt";


	public FormNhanVien(DanhSachNhanVien dao) {
		setTitle("Thông tin nhân viên");

		pNorth = new JPanel();

		Font font = new Font("Times New Roman", Font.BOLD, 30);
		pNorth.add(lblTitle = new JLabel("THÔNG TIN NHÂN VI�?N"));
		lblTitle.setFont(font);
		lblTitle.setForeground(Color.blue);

		add(pNorth, BorderLayout.NORTH);

		//

		pCenter = new JPanel();

		int x = 5, y = 30, with = 100, height = 30;
		pCenter.add(lblmaNV = new JLabel("Mã nhân viên:"));
		lblmaNV.setBounds(x, y, with, height);

		y += 40;

		pCenter.add(lblHo = new JLabel("Họ nhân viên:"));
		lblHo.setBounds(x, y, with, height);

		y += 40;

		pCenter.add(lblTuoi = new JLabel("Tuổi"));
		lblTuoi.setBounds(x, y, with, height);

		y += 40;

		pCenter.add(lblPhong = new JLabel("Phòng:"));
		lblPhong.setBounds(x, y, with, height);

		x = 100;
		y = 30;
		with = 100;
		height = 30;

		pCenter.add(txtMa = new JTextField());
		txtMa.setBounds(x, y, with + 560, height);

		y += 40;

		pCenter.add(txtHo = new JTextField());
		txtHo.setBounds(x, y, with + 250, height);
		//
		pCenter.add(lblTen = new JLabel("Tên nhân viên:"));
		lblTen.setBounds(x + 360, y, with + 260, height);

		pCenter.add(txtTen = new JTextField());
		txtTen.setBounds(x + 360 + 100, y, with + 100, height);

		y += 40;
		//
		pCenter.add(txtTuoi = new JTextField());
		txtTuoi.setBounds(x, y, with + 250, height);
		//
		pCenter.add(lblPhai = new JLabel("Phái:"));
		lblPhai.setBounds(x + 360, y, with, height);

		pCenter.add(radNu = new JRadioButton("Nữ"));
		radNu.setBounds(x + 360 + 100, y, with, height);

		//
		y += 40;

		String[] Phong = { "Phòng đào tạo", "Phòng nhân sự", "Phòng kế hoạch", "Phòng kỹ thuật", "Phòng kế toán" };

		pCenter.add(cbPhong = new JComboBox(Phong));
		cbPhong.setBounds(x, y, with + 180, height);

		pCenter.add(lblLuong = new JLabel("Tiền lương:"));
		lblLuong.setBounds(x + 360, y, with, height);
		;

		pCenter.add(txtLuong = new JTextField());
		txtLuong.setBounds(x + 360 + 100, y, with + 100, height);

		y += 40;
		// table

		String[] col = { "Mã NV", "Họ", "Tên", "Phái", "Tuổi", "Phòng", "Tiền lương" };

		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);

		JScrollPane scroll = new JScrollPane(table);

		pCenter.add(scroll);
		scroll.setBounds(20, y, with + 640, height + 170);

		pCenter.setLayout(null);
		add(pCenter, BorderLayout.CENTER);

		/// South

		jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JPanel jLeft = new JPanel();
		JPanel jRight = new JPanel();

		jLeft.add(lblNhap = new JLabel("Nhập mã cần tìm:"));
		jLeft.add(txtNhap = new JTextField(20));
		jLeft.add(btnTim = new JButton("Tìm"));

		//
		jRight.add(btnThem = new JButton("Thêm"));
		jRight.add(btnXoaTrang = new JButton("Xóa Trắng"));
		jRight.add(btnXoa = new JButton("Xóa"));
		jRight.add(btnLuu = new JButton("Lưu"));

		jSplitPane.add(jLeft);
		jSplitPane.add(jRight);

		add(jSplitPane, BorderLayout.SOUTH);

		//
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnTim.addActionListener(this);
		table.addMouseListener(this);

		dsnv = new DanhSachNhanVien();
		
		fi = new fileDocGhi();
		
		try {
			dsnv = (DanhSachNhanVien) fi.readFromFile(tenfile);

		} catch (Exception e) {
			System.out.println("Không tìm thấy file");
			
		}
		hienTable();
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private void hienTable() {
		for(int i = 0; i < dsnv.tong(); i++) {
			NhanVien nv = dsnv.getNhanVien(i);
			String[] datarow = { nv.getMa() + "", nv.getHo(), nv.getTen(), Boolean.toString(nv.isPhai()),
					nv.getTuoi() + "", nv.getPhong(), nv.getLuong() + "" };
			tableModel.addRow(datarow);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();

		if (o.equals(btnXoaTrang)) {
			xoaTrangAc();
		} else if (o.equals(btnThem)) {
			themAc();
		} else if (o.equals(btnXoa)) {
			xoaAc();
		}else if(o.equals(btnLuu)) {
			fi = new fileDocGhi();
			try {
				fi.writeToFile(dsnv, tenfile);
				JOptionPane.showMessageDialog(this, "Lưu thành công");
			} catch (Exception e1) {
				System.out.println("Loi!!!!!!!!");
				e1.printStackTrace();
			}
		}

	}

	private void themAc() {
		try {
			NhanVien nv;
			int ma = Integer.parseInt(txtMa.getText());
			String ho = txtHo.getText();
			String ten = txtTen.getText();
			boolean phai = radNu.isSelected() ? true : false;
			int tuoi = Integer.parseInt(txtTuoi.getText());
			String phong = (String) cbPhong.getSelectedItem();
			double luong = Double.parseDouble(txtLuong.getText());

			nv = new NhanVien(ma, ho, ten, tuoi, phai, phong, luong);

			if (dsnv.themNhanVien(nv)) {
				String[] row = { nv.getMa() + "", nv.getHo(), nv.getTen(), Boolean.toString(nv.isPhai()),
						nv.getTuoi() + "", nv.getPhong(), nv.getLuong() + "" };

//					String[] row = { Integer.toString(ma), ho, ten, Boolean.toString(phai), Integer.toString(tuoi), phong,
//							Double.toString(luong) + "" };
				tableModel.addRow(row);
				System.out.println(nv);
//					xoaTrangAc();

			} else {
				JOptionPane.showMessageDialog(null, "Trùng mã nhân viên");
				txtMa.selectAll();
				txtMa.requestFocus();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Lỗi nhập liệu");

			return;
		}

	}

	private void xoaTrangAc() {
		txtMa.setText("");
		txtHo.setText("");
		txtTen.setText("");
		txtTuoi.setText("");
		txtLuong.setText("");
		radNu.setSelected(false);
		cbPhong.setSelectedIndex(0);

	}

	private void xoaAc() {
		int row = table.getSelectedRow();
		if (row != -1) {
			int maNV = Integer.parseInt((String) table.getModel().getValueAt(row, 0));
			int hoiNhac = JOptionPane.showConfirmDialog(this, "Chắc chắn xóa không?", "Chú ý",
					JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_OPTION) {
				if (dsnv.xoaNhanVien(maNV)) {
					tableModel.removeRow(row);

				}
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow(); // lay dong dang chon
		txtMa.setText(table.getValueAt(row, 0).toString());
		txtHo.setText(table.getValueAt(row, 1).toString());
		txtTen.setText(table.getValueAt(row, 2).toString());

		if (table.getValueAt(row, 3).toString().equalsIgnoreCase("true")) { // tbl.getValueAt(row, 3).toString() =="nữ"
			radNu.setSelected(true);
		}else {
			radNu.setSelected(false);
		}
		
		txtTuoi.setText(table.getValueAt(row, 4).toString());

		cbPhong.setSelectedItem(table.getValueAt(row, 5));
		txtLuong.setText(table.getValueAt(row, 6).toString());

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
