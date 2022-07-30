package Bai1Tuan4QuanLySach;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FormSach extends JFrame implements ActionListener, MouseListener {
	JPanel pCenter, pButton, pTable, pSouth;
	JLabel lblMa, lblTua, lblTac, lblNam, lblNha, lblSo, lblDGia, lblISBN;
	JTextField txtMa, txtTua, txtTac, txtNam, txtNha, txtSo, txtGia, txtISBN;
	JButton btnThem, btnXoaRong, btnXoa, btnSua, btnLuu;
	JLabel lblTim;
	JComboBox cbTim;

	JTable table;
	DefaultTableModel tableModel;
	////////////
	DanhSachSach dss;
	filedDocGhi fi;
	private static final String tenfile = "data//dulieu.txt";

	public FormSach() {
		setTitle("Quản lý sách");

		pCenter = new JPanel();

		pCenter.setBorder(BorderFactory.createTitledBorder("Records Editor"));

		int x = 10, y = 20, with = 100, height = 30;

		pCenter.add(lblMa = new JLabel("Mã sách:"));
		lblMa.setBounds(x, y, with, height);
		y += 40;

		pCenter.add(lblTua = new JLabel("Tựa sách:"));
		lblTua.setBounds(x, y, with, height);
		y += 40;

		pCenter.add(lblNam = new JLabel("Năm xuất bản"));
		lblNam.setBounds(x, y, with, height);
		y += 40;

		pCenter.add(lblSo = new JLabel("Số trang:"));
		lblSo.setBounds(x, y, with, height);
		y += 40;

		pCenter.add(lblISBN = new JLabel("International Standard Book Number:"));
		lblISBN.setBounds(x, y, with + 120, height);

		// txt
		x = 100;
		y = 20;
		with = 100;
		height = 30;
		pCenter.add(txtMa = new JTextField());
		txtMa.setBounds(x, y, with + 100, height);

		y += 40;
		pCenter.add(txtTua = new JTextField());
		txtTua.setBounds(x, y, with + 200, height);

		pCenter.add(lblTac = new JLabel("Tác giả:"));
		lblTac.setBounds(x + 320, y, with, height);
		pCenter.add(txtTac = new JTextField());
		txtTac.setBounds(x + 320 + 90, y, with + 200, height);

		y += 40;

		pCenter.add(txtNam = new JTextField());
		txtNam.setBounds(x, y, with + 200, height);

		pCenter.add(lblNha = new JLabel("Nhà xuất bản:"));
		lblNha.setBounds(x + 320, y, with, height);
		pCenter.add(txtNha = new JTextField());
		txtNha.setBounds(x + 320 + 90, y, with + 200, height);

		y += 40;

		pCenter.add(txtSo = new JTextField());
		txtSo.setBounds(x, y, with + 200, height);

		pCenter.add(lblDGia = new JLabel("Đơn gía:"));
		lblDGia.setBounds(x + 320, y, with, height);
		pCenter.add(txtGia = new JTextField());
		txtGia.setBounds(x + 320 + 90, y, with + 200, height);

		y += 40;

		pCenter.add(txtISBN = new JTextField());
		txtISBN.setBounds(x + 130, y, with + 70, height);

		pButton = new JPanel();

		pButton.add(btnThem = new JButton("Thêm"));
		pButton.add(btnXoaRong = new JButton("Xóa Rổng"));
		pButton.add(btnXoa = new JButton("Xóa"));
		pButton.add(btnSua = new JButton("Sửa"));
		pButton.add(btnLuu = new JButton("Lưu"));
		pButton.add(lblTim = new JLabel("Tìm theo mã sách:"));

		Integer maS[] = { 1, 2, 3, 4};

		pButton.add(cbTim = new JComboBox(maS));

		x = 100;
		y = 230;
		with = 600;
		height = 40;

		pCenter.add(pButton);
		pButton.setBounds(x, y, with, height);

		// pTable
		pTable = new JPanel();
		pTable.setLayout(new BorderLayout());
		pTable.setBorder(BorderFactory.createTitledBorder("Danh sách các cuốn sách"));
		x = 20;
		y = 290;
		with = 800;
		height = 260;
		String col[] = { "MaSach", "TuaSach", "TacGia", "NamXuatBan", "NhaXuatBan", "SoTrang", "DonGia", "ISBN" };
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);

		JScrollPane jScroll = new JScrollPane(table);

		pTable.add(jScroll);
		jScroll.setBounds(x, y, with, height);

		pCenter.add(pTable);
		pTable.setBounds(x, y, with, height);

		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnLuu.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnXoa.addActionListener(this);
		cbTim.addActionListener(this);
		table.addMouseListener(this);

		pCenter.setLayout(new BorderLayout());
		add(pCenter, BorderLayout.CENTER);

		dss = new DanhSachSach();
		fi = new filedDocGhi();

		try {
			dss = (DanhSachSach) fi.readFromFile(tenfile);
		} catch (Exception e) {
			System.out.println("Khong tim thay file");
		}
		hienTable();

		setSize(840, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	private void hienTable() {
		for (int i = 0; i < dss.tong(); i++) {
			Sach sach = dss.getSach(i);
			String[] dataRow = { sach.getMaSach() + "", sach.getTuaSach(), sach.getTacGia(), sach.getNamXB() + "",
					sach.getNhaXB(), sach.getSoTrang() + "", sach.getDonGia() + "", sach.getiSBN() };
			tableModel.addRow(dataRow);
		}

	}
//	private void hienTableTIM(int ma) {
//		Sach sachNhap = new Sach(ma);
//		for (int i = 0; i < dss.tong(); i++) {
//			if(sachNhap == dss.getSach(i)) {
//
//				String[] dataRow = { sachNhap.getMaSach() + "", sachNhap.getTuaSach(), sachNhap.getTacGia(), sachNhap.getNamXB() + "",
//						sachNhap.getNhaXB(), sachNhap.getSoTrang() + "", sachNhap.getDonGia() + "", sachNhap.getiSBN() };
//				
//			}
//			
//		
//		}
//
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object.equals(btnXoaRong)) {
			xoaRongAc();
		}
		if (object.equals(btnThem)) {
			themAc();
		}
		if (object.equals(btnLuu)) {
			luuAc();
		}
		if (object.equals(btnXoa)) {
			xoaAc();
		}
		if (object.equals(btnSua)) {
			suaAc();
		}
		if(object.equals(cbTim)) {
			timAc();
		}

	}





	private void luuAc() {
		fi = new filedDocGhi();

		try {
			fi.writeToFile(dss, tenfile);

		} catch (Exception e) {
			System.out.println("ERR");
			e.printStackTrace();
		}

	}

	private void themAc() {
		try {
			Sach sach;
			int maSach = Integer.parseInt(txtMa.getText());
			String tuaSach = txtTua.getText();
			String tacGia = txtTac.getText();
			int namXB = Integer.parseInt(txtNam.getText());
			String nhaXB = txtNha.getText();
			int soTrang = 0;
			soTrang = Integer.parseInt(txtSo.getText());
			double donGia = 0;
			donGia = Double.parseDouble(txtGia.getText());
			String iSBN = txtISBN.getText();

			if (soTrang < 0 || donGia < 0) {
				JOptionPane.showMessageDialog(this, "Số trang và �?ơn giá phải là số dương");

			} else if (tuaSach.equals("") || tacGia.equals("")) {
				JOptionPane.showMessageDialog(this, "Tựa sách và tác giả không được rổng");
			} else {
				sach = new Sach(maSach, tuaSach, tacGia, namXB, nhaXB, soTrang, donGia, iSBN);

				if (dss.themSach(sach)) {
					String[] row = { sach.getMaSach() + "", sach.getTuaSach(), sach.getTacGia(), sach.getNamXB() + "",
							sach.getNhaXB(), sach.getSoTrang() + "", sach.getDonGia() + "", sach.getiSBN() };
					tableModel.addRow(row);
					luuAc();
				} else {
					JOptionPane.showMessageDialog(this, "Trùng mã!");
					txtMa.requestFocus();
				}

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Loi nhap lieu");
			return;
		}

	}
	private void suaAc() {
		try {
			Sach sach;
			int maSach = Integer.parseInt(txtMa.getText());
			String tuaSach = txtTua.getText();
			String tacGia = txtTac.getText();
			int namXB = Integer.parseInt(txtNam.getText());
			String nhaXB = txtNha.getText();
			int soTrang = 0;
			soTrang = Integer.parseInt(txtSo.getText());
			double donGia = 0;
			donGia = Double.parseDouble(txtGia.getText());
			String iSBN = txtISBN.getText();
			
			sach = new Sach(maSach, tuaSach, tacGia, namXB, nhaXB, soTrang, donGia, iSBN);
		
			if(dss.suaSach(maSach, sach)) {
				int row = table.getSelectedRow();
				tableModel.setValueAt(sach.getMaSach()+"", row, 0);
				tableModel.setValueAt(sach.getTuaSach(), row, 1);
				tableModel.setValueAt(sach.getTacGia(), row, 2);
				tableModel.setValueAt(sach.getNamXB()+"", row, 3);
				tableModel.setValueAt(sach.getNhaXB(), row, 4);
				tableModel.setValueAt(sach.getSoTrang()+"", row, 5);
				tableModel.setValueAt(sach.getDonGia()+"", row, 6);
				tableModel.setValueAt(sach.getiSBN(), row, 7);
//				luuAc();
			}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Loi nhap lieu");
			return;
		}
		
	}

	private void xoaRongAc() {
		txtMa.setText("");
		txtTua.setText("");
		txtTac.setText("");
		txtNam.setText("");
		txtNha.setText("");
		txtSo.setText("");
		txtGia.setText("");
		txtISBN.setText("");

	}


	private void xoaAc() {
		int row = table.getSelectedRow();
		if (row != -1) {
			int maSach = Integer.parseInt((String) table.getModel().getValueAt(row, 0));

			int hoiNhac = JOptionPane.showConfirmDialog(this, "Chắc chắn xóa không?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_OPTION) {
				if (dss.xoaSach(maSach)) {
					tableModel.removeRow(row);
					luuAc();
				}
			}
		}

	}
	
	
	private void fillForm(int row) {
		if(row != -1) {

			int maSach = Integer.parseInt(table.getValueAt(row, 0).toString());
			
//			String maSach = (String) table.getValueAt(row, 0);
		
			Sach s = new Sach(maSach);
			ArrayList<Sach> dsSach = dss.getDsSach();
			if(dss.getDsSach().contains(s)) {
				s = dsSach.get(dsSach.indexOf(s));
				txtMa.setText(s.getMaSach()+"");
				txtMa.setEditable(false);
				txtTua.setText(s.getTuaSach());
				txtTac.setText(s.getTacGia());
				txtNam.setText(s.getNamXB()+"");
				txtNha.setText(s.getNhaXB());
				txtSo.setText(s.getSoTrang()+"");
				txtGia.setText(s.getDonGia()+"");
				txtISBN.setText(s.getiSBN());
			}
			
		}
	}

	private void timAc() {
	
			Integer maSach = (Integer) cbTim.getSelectedItem();
			Sach s = dss.timKiem(maSach);
			
			if(s != null) {
				int index = dss.getDsSach().indexOf(s);
				fillForm(index);
				table.getSelectionModel().setSelectionInterval(index, index);
				table.scrollRectToVisible(table.getCellRect(index, index, true));
			}
			
			
			
			
			
//			if(s != null) {
//				JOptionPane.showMessageDialog(this, s);
//				
//				
//			}else {
//				JOptionPane.showMessageDialog(this, "Không tìm thấy");
//			}
	
			
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMa.setText(table.getValueAt(row, 0).toString());
		txtTua.setText(table.getValueAt(row, 1).toString());
		txtTac.setText(table.getValueAt(row, 2).toString());
		txtNam.setText(table.getValueAt(row, 3).toString());
		txtNha.setText(table.getValueAt(row, 4).toString());
		txtSo.setText(table.getValueAt(row, 5).toString());
		txtGia.setText(table.getValueAt(row, 6).toString());
		txtISBN.setText(table.getValueAt(row, 7).toString());

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
