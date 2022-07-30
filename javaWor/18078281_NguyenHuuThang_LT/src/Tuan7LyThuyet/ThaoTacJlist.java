package Tuan7LyThuyet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ThaoTacJlist extends JFrame implements ActionListener {

	JPanel pNorth, pCenter, pWest, pSouth;
	JLabel lblTitle;
	JButton btnChan, btnLe, btnNTo, btnBoTD, btnXoaTD, btnTong;
	JButton btnNhap, btnThoat;
	JTextField txtNhap;
	JCheckBox checkSoAm;
	JList<Integer> jlist;
	DefaultListModel<Integer> listmodel;
	JScrollPane listPane;

	public ThaoTacJlist() {
		setTitle("Thao tác trên Jlist");

		pNorth = new JPanel();

		Font font = new Font("Times New Roman", Font.BOLD, 30);
		pNorth.add(lblTitle = new JLabel("Thao tác trên Jlist - Checkbox"));

		lblTitle.setFont(font);

		add(pNorth, BorderLayout.NORTH);

//		WEAST

		pWest = new JPanel();

		pWest.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "Chọn tác vụ"));

		Box box = new Box(BoxLayout.Y_AXIS);
//		Box box = Box.createVerticalBox();

		box.add(btnChan = new JButton("Tô đen số chẵn"));
		box.add(Box.createRigidArea(new Dimension(10, 10))); /// cach1

		box.add(btnLe = new JButton("Tô đen số lẻ"));
		box.add(Box.createVerticalStrut(10)); // cach2

		box.add(btnNTo = new JButton("Tô đen số nguyên  tố"));
		box.add(Box.createVerticalStrut(10));

		box.add(btnBoTD = new JButton("Bỏ tô đen"));
		box.add(Box.createVerticalStrut(10));

		box.add(btnXoaTD = new JButton("Xóa các giá trị đang tô đen"));
		box.add(Box.createVerticalStrut(10));

		box.add(btnTong = new JButton("Tổng giá trị trong Jlist"));

		pWest.add(box);
		add(pWest, BorderLayout.WEST);
//		Center
		pCenter = new JPanel();
		pCenter.setBorder(BorderFactory.createTitledBorder("Nhập thông tin:"));

		pCenter.add(btnNhap = new JButton("Nhập"));
		int x = 30, y = 30, with = 70, hight = 30;
		btnNhap.setBounds(x, y, with, hight);

		x += 80;
		with = 150;
		pCenter.add(txtNhap = new JTextField());
		txtNhap.setBounds(x, y, with, hight);

		x += 150;
		pCenter.add(checkSoAm = new JCheckBox("Cho nhập số âm"));

		checkSoAm.setBounds(x, y, with, hight);

		listmodel = new DefaultListModel();
		jlist = new JList(listmodel);
		listPane = new JScrollPane(jlist);
		x = 20;
		y = 70;
		with = 340;
		hight = 300;
		pCenter.add(listPane);
//		jlist.setVisibleRowCount(50); cacch 2
		listPane.setBounds(x, y, with, hight);

		pCenter.setLayout(null);
		add(pCenter, BorderLayout.CENTER);

		//
		pSouth = new JPanel();
		pSouth.add(btnThoat = new JButton("Đóng chương trình"));
		pSouth.setBackground(Color.cyan);
		add(pSouth, BorderLayout.SOUTH);

		//
		btnNhap.addActionListener(this);
		btnChan.addActionListener(this);
		btnLe.addActionListener(this);
		btnNTo.addActionListener(this);
		btnBoTD.addActionListener(this);
		btnXoaTD.addActionListener(this);
		btnTong.addActionListener(this);
		btnThoat.addActionListener(this);

		setSize(600, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	public static void main(String[] args) {
		new ThaoTacJlist();
	}

	public boolean KTNT(int n) {
		int i = 2;

		while (n % i != 0) {
			i++;
		}
		if (i == n) {
			return true;
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		String nhap;
		nhap = txtNhap.getText();

		if (o.equals(btnNhap)) {
			if (nhap.equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập liệu");
			} else {

				try {
					if (checkSoAm.isSelected() == false) {
						if (Integer.parseInt(txtNhap.getText()) <= 0) {
							JOptionPane.showMessageDialog(this, "Không được nhập số âm");
							txtNhap.setText("");
							txtNhap.requestFocus();
						}
					}
					listmodel.addElement(Integer.parseInt(txtNhap.getText()));
					txtNhap.setText("");
					txtNhap.requestFocus();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Nhập sai định dạng");
					txtNhap.selectAll();
					txtNhap.requestFocus();
				}
			}

		} else if (o.equals(btnChan)) {
			jlist.clearSelection();
			;
			for (int i = 0; i < listmodel.getSize(); i++) {
				if (listmodel.getElementAt(i) % 2 == 0) {
					jlist.addSelectionInterval(i, i);
				}
			}
		} else if (o.equals(btnLe)) {
			jlist.clearSelection();
			for (int i = 0; i < listmodel.getSize(); i++) {
				if (listmodel.getElementAt(i) % 2 != 0) {
					jlist.addSelectionInterval(i, i);
				}
			}
		} else if (o.equals(btnNTo)) {
			jlist.clearSelection();
			for (int i = 0; i < listmodel.getSize(); i++) {
				if (KTNT(listmodel.getElementAt(i)) == true) {
					jlist.addSelectionInterval(i, i);
				}
			}
		} else if (o.equals(btnBoTD)) {
			jlist.clearSelection();

		} else if (o.equals(btnXoaTD)) {
			Object a[] = jlist.getSelectedValues();
			for (int i = 0; i < a.length; i++) {
				listmodel.removeElement(a[i]);
			}
		} else if (o.equals(btnTong)) {
			int tong = 0;
			for (int i = 0; i < listmodel.getSize(); i++) {
				tong = tong + listmodel.getElementAt(i);
			}
			JOptionPane.showMessageDialog(this, "Tổng là:" + tong);
		}else if(o.equals(btnThoat)){
			System.exit(0);
		}

	}

}
