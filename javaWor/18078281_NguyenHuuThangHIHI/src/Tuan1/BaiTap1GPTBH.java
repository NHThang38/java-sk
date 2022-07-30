package Tuan1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BaiTap1GPTBH extends JFrame implements ActionListener {

	JLabel lblTile, lblNhapA, lblNhapB, lblNhapC, lblKQ;
	JTextField txtA, txtB, txtC, txtKQ;
	JButton btnGiai, btnXoaTrang, btnThoat;

	public BaiTap1GPTBH() {
		super("Giai Phuong Trinh Bac Hai");

		///// North
		JPanel pNorth = new JPanel();
		pNorth.add(lblTile = new JLabel("GIAI PHUONG TRINH BAC HAI"));

		Font font = new Font("Times new Roman", Font.BOLD, 35);
		lblTile.setFont(font);
		pNorth.setBackground(Color.CYAN);

		add(pNorth, BorderLayout.NORTH);

		// Center
		JPanel pCenter = new JPanel();

		pCenter.add(lblNhapA = new JLabel("Nhập A:"));

		int x = 20, y = 40, with = 100, height = 30;

		lblNhapA.setBounds(x, y, with, height);

		y += 70;

		pCenter.add(lblNhapB = new JLabel("Nhập B:"));
		lblNhapB.setBounds(x, y, with, height);

		y += 70;

		pCenter.add(lblNhapC = new JLabel("Nhập C:"));
		lblNhapC.setBounds(x, y, with, height);

		y += 70;

		pCenter.add(lblKQ = new JLabel("Kết quả:"));
		lblKQ.setBounds(x, y, with, height);

		x += 70;
		y = 40;
		with = 250;

		pCenter.add(txtA = new JTextField());
		txtA.setBounds(x, y, with, height);

		y += 70;

		pCenter.add(txtB = new JTextField());
		txtB.setBounds(x, y, with, height);

		y += 70;

		pCenter.add(txtC = new JTextField());
		txtC.setBounds(x, y, with, height);

		y += 70;

		pCenter.add(txtKQ = new JTextField());
		txtKQ.setBounds(x, y, with, height);
		txtKQ.setEditable(false);

		add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(null);

		// south
		JPanel pSouth = new JPanel();
		pSouth.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		pSouth.setLayout(new FlowLayout(1, 25, 25));

		pSouth.add(btnGiai = new JButton("Giải"));
		pSouth.add(btnXoaTrang = new JButton("Xóa Trắng"));
		pSouth.add(btnThoat = new JButton("Thoát"));

		add(pSouth, BorderLayout.SOUTH);

		//
		btnGiai.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnThoat.addActionListener(this);

		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	public static void main(String[] args) {
		new BaiTap1GPTBH();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		int a, b, c, delta;
		double kq, x1, x2;
		DecimalFormat fmt = new DecimalFormat("0.#");
		a = Integer.parseInt(txtA.getText());
		b = Integer.parseInt(txtB.getText());
		c = Integer.parseInt(txtC.getText());

		if(source.equals(btnXoaTrang)) {
			txtA.setText("");
			txtB.setText("");
			txtC.setText("");
			txtKQ.setText("");
		}else if(source.equals(btnGiai)) {
			if (a == 0) {
				if (b == 0) {
					if (c == 0) {
						txtKQ.setText("Phương trình vô số nghiệm");
					} else
						txtKQ.setText("Phương trình vô nghiệm");

				} else {
					kq = -1.0 * c / b;
					txtKQ.setText(Double.toString(kq));
				}

			} else {
				delta = b * b - 4 * a * c;
				if (delta < 0) {
					txtKQ.setText("Phương trình vô nghiệm");
				} else {
					if (delta == 0) {
						x1 = x2 = -1.0 * b / (2 * a);
						txtKQ.setText(Double.toString(x1));

					} else {
						x1 = (-1.0 * b + Math.sqrt(delta) / (2 * a));
						x2 = (-1.0 * b - Math.sqrt(delta) / (2 * a));
						txtKQ.setText("x1=" + Double.toString(x1) + " x2=" + Double.toString(x2));

					}
				}

			}

		}else if(source.equals(btnThoat)) {
			System.exit(0);
		}

	}

}
