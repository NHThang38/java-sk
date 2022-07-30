package baitap4CongTruNhanChia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CongTruNhanChia extends JFrame implements ActionListener {
	JPanel pNorth, pWest, pCen, pSouth;
	JLabel lbltitle, lblNhapA, lblNhapB, lblKQ;
	JTextField txtA, txtB, txtKQ;
	JButton btnGiai, btnXoa, btnThoat;
	JRadioButton radCong, radTru, radNhan, radChia;

	public CongTruNhanChia() {
		setTitle("Cộng - Trừ - Nhân - Chia ");

		pNorth = new JPanel();
		Font font = new Font("Times New Roman", Font.BOLD, 30);
		pNorth.add(lbltitle = new JLabel("CỘNG TRỪ NHÂN CHIA"));
		lbltitle.setFont(font);
		lbltitle.setForeground(Color.BLUE);
		add(pNorth, BorderLayout.NORTH);

		//
		pWest = new JPanel();
		pWest.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		pWest.setBackground(Color.LIGHT_GRAY);
		pWest.setLayout(new FlowLayout(2, 10, 10));
		Box boxBtn = new Box(BoxLayout.Y_AXIS);

		boxBtn.add(btnGiai = new JButton("   Giải  "));
//		Tạo khoảng cách
		boxBtn.add(Box.createRigidArea(new Dimension(10, 10)));

		boxBtn.add(btnXoa = new JButton("   Xóa   "));
		boxBtn.add(Box.createRigidArea(new Dimension(10, 10)));

		boxBtn.add(btnThoat = new JButton(" Thoát  "));
		boxBtn.add(Box.createRigidArea(new Dimension(10, 10)));

		pWest.add(boxBtn);
		add(pWest, BorderLayout.WEST);

		//
		pCen = new JPanel();
		pCen.setBorder(BorderFactory.createTitledBorder("Tính toán"));

		int x = 70, y = 30, with = 50, height = 30;

		pCen.add(lblNhapA = new JLabel("Nhập a:"));
		lblNhapA.setBounds(x, y, with, height);

		y += 50;
		pCen.add(lblNhapB = new JLabel("Nhập b:"));
		lblNhapB.setBounds(x, y, with, height);

		x = 120;
		y = 30;
		with = 300;
		height = 30;
		pCen.add(txtA = new JTextField());
		txtA.setBounds(x, y, with, height);

		y += 50;
		pCen.add(txtB = new JTextField());
		txtB.setBounds(x, y, with, height);

		// GroudRadio
		y += 50;
		with = 260;
		height = 140;

		JPanel pRadio = new JPanel();
		pRadio.setLayout(new GridLayout(2, 2));
		radCong = new JRadioButton("Cộng");
		radTru = new JRadioButton("Trừ");
		radNhan = new JRadioButton("Nhân");
		radChia = new JRadioButton("Chia");

		pRadio.setBounds(x, y, with, height);

		pRadio.setBorder(BorderFactory.createTitledBorder("Phép toán"));
		pRadio.add(radCong);
		pRadio.add(radTru);
		pRadio.add(radNhan);
		pRadio.add(radChia);

		ButtonGroup group = new ButtonGroup();
		group.add(radCong);
		group.add(radTru);
		group.add(radNhan);
		group.add(radChia);

		pCen.add(pRadio);
		//
		x = 70;
		y = 310;
		with = 50;
		height = 30;
		pCen.add(lblKQ = new JLabel("Kết quả:"));
		lblKQ.setBounds(x, y, with, height);

		x = 120;
		y = 310;
		with = 300;
		height = 30;
		pCen.add(txtKQ = new JTextField());
		txtKQ.setBounds(x, y, with, height);
		txtKQ.setEditable(false);

		pCen.setLayout(null);
		add(pCen, BorderLayout.CENTER);

		// Sout
		pSouth = new JPanel();
		pSouth.setBackground(Color.PINK);
		pSouth.setPreferredSize(new Dimension(0, 50));

		JPanel pBlu = new JPanel();
		pBlu.setPreferredSize(new Dimension(30, 30));

		pBlu.setBackground(Color.BLUE);
		pBlu.setPreferredSize(new Dimension(30, 30));

		JPanel pRed = new JPanel();
		pRed.setBackground(Color.RED);
		pRed.setPreferredSize(new Dimension(30, 30));

		JPanel pYe = new JPanel();
		pYe.setBackground(Color.YELLOW);
		pYe.setPreferredSize(new Dimension(30, 30));

		pSouth.add(pBlu);
		pSouth.add(pRed);
		pSouth.add(pYe);

		add(pSouth, BorderLayout.SOUTH);
		//
		btnGiai.addActionListener(this);
		btnXoa.addActionListener(this);
		btnThoat.addActionListener(this);

		setSize(600, 580);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	public static void main(String[] args) {
		new CongTruNhanChia();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();
		int a = 0, b = 0;
		String s1 = txtA.getText();
		String s2 = txtB.getText();

		if (s1.equals("")) {
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập A");
			txtA.requestFocus();
		} else if (s2.equals("")) {
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập B");
			txtB.requestFocus();
		} else {
			try {
				a = Integer.parseInt(txtA.getText());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Nhập sai định dạng");
				txtA.selectAll();
				txtA.requestFocus();
			}
			try {
				b = Integer.parseInt(txtB.getText());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Nhập sai định dạng");
				txtB.selectAll();
				txtB.requestFocus();
			}
		}

		if (o.equals(btnGiai)) {
			if (radCong.isSelected()) {
				txtKQ.setText(Integer.toString(a + b));
			} else if (radTru.isSelected()) {
				txtKQ.setText(Integer.toString(a - b));
			} else if (radNhan.isSelected()) {
				txtKQ.setText(Integer.toString(a * b));
			} else if (radChia.isSelected()) {
				txtKQ.setText(Double.toString(1.0 * a / b));
			}
		} else if (o.equals(btnXoa)) {
			txtA.setText("");
			txtB.setText("");
			txtKQ.setText("");
		} else if (o.equals(btnThoat)) {
			System.exit(0);

		}

	}
}
