package THbai1Tuan2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
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

public class FormThongTinNhanVien extends JFrame implements ActionListener, MouseListener {
	JPanel pNorth, pCenter, pSouth;
	JLabel lblTitle, lblMa, lblHo, lblTen, lblTuoi, lblPhai, lblLuong;
	JTextField txtMa, txtHo, txtTen, txtTuoi, txtLuong;
	JRadioButton radNu;

	DefaultTableModel tblModel;
	JTable tbl;
	JScrollPane scrollPane;
	private JSplitPane split;
	private JTextField txtNhap;
	private JButton btnTim, btnThem, btnXoaTrang, btnXoa, btnLuu;
	//
	private DanhSachNhanVien dao;
	private List<NhanVien> list;

	public FormThongTinNhanVien(DanhSachNhanVien dao) {
		setTitle("THÔNG TIN NHÂN VIÊN");

		//
		this.dao = dao;
		list = dao.getList();
		//

		pNorth = new JPanel();

		Font font = new Font("Times New Roma", Font.BOLD, 30);
		pNorth.add(lblTitle = new JLabel("THÔNG TIN NHÂN VIÊN"));
		lblTitle.setFont(font);
		lblTitle.setForeground(Color.BLUE);
		add(pNorth, BorderLayout.NORTH);

		// Center

		pCenter = new JPanel();

		int x = 5, y = 20, with = 90, height = 30;
		pCenter.add(lblMa = new JLabel("Mã nhân viên: "));
		lblMa.setBounds(x, y, with, height);

		y += 40;

		pCenter.add(lblHo = new JLabel("Họ: "));
		lblHo.setBounds(x, y, with, height);

		y += 40;

		pCenter.add(lblTuoi = new JLabel("Tuổi: "));
		lblTuoi.setBounds(x, y, with, height);

		y += 40;

		pCenter.add(lblLuong = new JLabel("Tiền lương: "));
		lblLuong.setBounds(x, y, with, height);

		//
		x = 90;
		y = 20;
		with = 90;
		height = 30;

		pCenter.add(txtMa = new JTextField());
		txtMa.setBounds(x, y, with + 600, height);

		y += 40;
		pCenter.add(txtHo = new JTextField());
		txtHo.setBounds(x, y, with + 200, height);

		pCenter.add(lblTen = new JLabel("Tên nhân viên: "));
		lblTen.setBounds(x + 300, y, with, height);

		pCenter.add(txtTen = new JTextField());
		txtTen.setBounds(x + 400, y, with + 200, height);

		y += 40;
		pCenter.add(txtTuoi = new JTextField());
		txtTuoi.setBounds(x, y, with + 400, height);

		pCenter.add(lblPhai = new JLabel("Phái: "));
		lblPhai.setBounds(x + 500, y, with, height);

		pCenter.add(radNu = new JRadioButton("Nữ "));
		radNu.setBounds(x + 580, y, with, height);

		y += 40;
		pCenter.add(txtLuong = new JTextField());
		txtLuong.setBounds(x, y, with + 600, height);

		x = 50;
		y += 40;
		with = 700;
		height = 300;

//			String[] cols= {"Mã NV;Họ;Tên;Phái;Tuổi;Tiền lương"}.split(";");

		String[] cols = { "Mã NV", "Họ", "Tên", "Phái", "Tuổi", "Tiền lương" };

		tblModel = new DefaultTableModel(cols, 0);
		tbl = new JTable(tblModel);
		scrollPane = new JScrollPane(tbl);

		pCenter.add(scrollPane);
		scrollPane.setBounds(x, y, with, height);
		;

		pCenter.setLayout(null);
		add(pCenter, BorderLayout.CENTER);

		// South
		pSouth = new JPanel();

		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		JPanel pSLeft = new JPanel();
		JPanel pRight = new JPanel();

		pSLeft.add(new JLabel("Nhập mã số cần tìm:"));
		pSLeft.add(txtNhap = new JTextField(20));
		pSLeft.add(btnTim = new JButton("Tìm"));

		//
		pRight.add(btnThem = new JButton("Thêm"));
		pRight.add(btnXoaTrang = new JButton("Xóa trắng"));
		pRight.add(btnXoa = new JButton("Xóa"));
		pRight.add(btnLuu = new JButton("Lưu"));

		split.add(pSLeft);
		split.add(pRight);

		add(split, BorderLayout.SOUTH);

		//
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnTim.addActionListener(this);
		tbl.addMouseListener(this);

		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			themActions();
		} else if (o.equals(btnXoaTrang)) {
			xoaTrangActions();
		} else if (o.equals(btnXoa)) {
			xoaActions();
		}

	}





	private void xoaTrangActions() {
		txtMa.setText("");
		txtHo.setText("");
		txtTen.setText("");
		txtTuoi.setText("");
		txtLuong.setText("");
		radNu.setSelected(false);
		txtNhap.setText("");
		txtMa.requestFocus();

	}

	private void themActions() {
		try {
			int maNV = Integer.parseInt(txtMa.getText());
			String ho = txtHo.getText();
			String ten = txtTen.getText();
			boolean phai = (radNu.isSelected()) ? true : false;
			String phaiHT = "";
//			if(phai == true) {
//				phaiHT = "nữ";
//			}else {
//				phaiHT = "nam";
//			}
			int tuoi = Integer.parseInt(txtTuoi.getText());
			double tienLuong = Double.parseDouble(txtLuong.getText());

			NhanVien nv = new NhanVien(maNV, ho, ten, tuoi, phai, tienLuong);

			if (dao.themNhanVien(nv)) {
				String[] row = { Integer.toString(maNV), ho, ten, Boolean.toString(phai), Integer.toString(tuoi),
						Double.toString(tienLuong) + "" };

				tblModel.addRow(row);
				xoaTrangActions();

			} else {
				JOptionPane.showMessageDialog(null, "Trùng mã nhân viên.");
				txtMa.selectAll();
				txtMa.requestFocus();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi nhập liệu.");
			return;
		}

	}

	private void xoaActions() {
		int row = tbl.getSelectedRow();
		if (row != -1) {
			int maNV = Integer.parseInt((String) tbl.getModel().getValueAt(row, 0));
			int hoiNhac = JOptionPane.showConfirmDialog(this, "Chắc chắn xóa không?", "Chú ý",
					JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_OPTION) {
				if (dao.xoaNhanVien(maNV)) {
					tblModel.removeRow(row);
				}
			}
		}

	}
	


	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tbl.getSelectedRow(); // lay dong chon tren table
		txtMa.setText(tbl.getValueAt(row, 0).toString());
		txtHo.setText(tbl.getValueAt(row, 1).toString());
		txtTen.setText(tbl.getValueAt(row, 2).toString());
		radNu.setSelected(false);
		if (tbl.getValueAt(row, 3).toString().equalsIgnoreCase("true")) { // tbl.getValueAt(row, 3).toString() =="nữ"
			radNu.setSelected(true);
		}
		txtTuoi.setText(tbl.getValueAt(row, 4).toString());
		txtLuong.setText(tbl.getValueAt(row, 5).toString());
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
