package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import dao.PhongBan_DAO;
import entity.NhanVien;
import entity.PhongBan;

public class NhanVien_GUI1 extends JFrame implements ActionListener, MouseListener   {

	private JPanel pNorth, pCenter, pSouth;
	private JLabel lblTitle, lblmaNV, lblHoTen, lblTuoi, lblPhai, lblPhong, lblLuong;
	private JTextField txtMa, txtHo, txtTen, txtTuoi, txtLuong;
	private JRadioButton radNu;

	JComboBox cbPhong;

	JTable table;
	DefaultTableModel tableModel;

	JSplitPane jSplitPane;
	private JLabel lblNhap;
	JTextField txtNhap;
	JButton btnTim, btnThem, btnXoaTrang, btnSua,btnXoa, btnLuu;

	private NhanVien_DAO nv_dao;
	private PhongBan_DAO pb_dao;

	public NhanVien_GUI1() {
		
		// Khởi tạo kết nối database
		ConnectDB.getInstance().connect();
		nv_dao = new NhanVien_DAO();
		pb_dao = new PhongBan_DAO();
		
		
		
		setTitle("Thông tin nhân viên");

		pNorth = new JPanel();

		Font font = new Font("Times New Roman", Font.BOLD, 30);
		pNorth.add(lblTitle = new JLabel("THÔNG TIN NHÂN VIÊN "));
		lblTitle.setFont(font);
		lblTitle.setForeground(Color.blue);

		add(pNorth, BorderLayout.NORTH);

		//

		pCenter = new JPanel();

		int x = 5, y = 30, with = 100, height = 30;
		pCenter.add(lblmaNV = new JLabel("Mã nhân viên:"));
		lblmaNV.setBounds(x, y, with, height);

		y += 40;

		pCenter.add(lblHoTen = new JLabel("Họ tên:"));
		lblHoTen.setBounds(x, y, with, height);

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
		txtHo.setBounds(x, y, with + 560, height);
	

		y += 40;
		//
		pCenter.add(txtTuoi = new JTextField());
		txtTuoi.setBounds(x, y,with + 560, height);
		//

		y += 40;

//		String[] Phong = { "Phòng đào tạo", "Phòng nhân sự", "Phòng kế hoạch", "Phòng kỹ thuật", "Phòng kế toán" };
	
			

		pCenter.add(cbPhong = new JComboBox<String>());
		cbPhong.setBounds(x, y, with + 180, height);
		
		ArrayList<PhongBan> listPB = pb_dao.getAllPhongBan();
		for (PhongBan p : listPB) {
			cbPhong.addItem(p.getMaPhong());
		}

		pCenter.add(lblLuong = new JLabel("Tiền lương:"));
		lblLuong.setBounds(x + 360, y, with, height);
		;

		pCenter.add(txtLuong = new JTextField());
		txtLuong.setBounds(x + 360 + 100, y, with + 100, height);

		y += 40;
		// table

		String[] col = { "Mã NV", "Họ Tên", "Tuổi", "Phòng", "Tiền lương" };

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
		jRight.add(btnSua = new JButton("Sửa"));
		jRight.add(btnXoa = new JButton("Xóa"));
		jRight.add(btnLuu = new JButton("Lưu"));

		jSplitPane.add(jLeft);
		jSplitPane.add(jRight);

		add(jSplitPane, BorderLayout.SOUTH);
		
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		table.addMouseListener(this);
		//=======
		DocDuLieuDatabaseVaoTable();

		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	private void DocDuLieuDatabaseVaoTable() {
			NhanVien_DAO ds = new NhanVien_DAO();
			List<NhanVien> list = ds.getAlltbNhanVien();
			for(NhanVien s: list) {
				String []rowData = {s.getMaNV(), s.getHoTen(), s.getTuoi()+"", s.getPhong().getMaPhong(), s.getTienLuong()+""};
				tableModel.addRow(rowData);
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
			themAc();
		}
	}
	
	
	private void themAc() {
		//Kiem tra rang buoc truoc khi theem ++++++++++++
		String ma = txtMa.getText();
		String hoTen = txtHo.getText();
		int tuoi = Integer.parseInt(txtTuoi.getText());
		String phongban = cbPhong.getSelectedItem().toString();
		PhongBan phBan = new PhongBan(phongban);
		double tienLuong = Double.parseDouble(txtLuong.getText());
		NhanVien nv = new NhanVien(ma, hoTen, tuoi, phBan, tienLuong);
		try {
			nv_dao.create(nv);
			String []rowData = {nv.getMaNV(), nv.getHoTen(), nv.getTuoi()+"", nv.getPhong().getMaPhong(), nv.getTienLuong()+""};
			tableModel.addRow(rowData);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Trùng mã!!");
		}
		
	}

	private void xoaTrangAc() {
		txtMa.setText("");
		txtHo.setText("");
		txtTuoi.setText("");
		cbPhong.setSelectedIndex(0);
		txtLuong.setText("");
		
		
	}

	public static void main(String[] args) {
		new NhanVien_GUI1();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	