package baitap3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LayoutCongTru extends JFrame {
	JLabel l1, l2, l3, ltitle;
	JTextField textSo1, textSo2, textKQ;
	JRadioButton jRaCong, JRaTru;
	JButton btnCong, btnTru, btnXoa, btnThoat;
	ButtonGroup btn;

	public LayoutCongTru() {
		super("Layout Cong Tru");

		JPanel jp = new JPanel();
		
		
		JPanel jpBot = new JPanel();
	

		
		
		ltitle = new JLabel("Bài tập cộng trừ", SwingConstants.CENTER);
		Font fo = new Font("Times New Roman", Font.BOLD, 18);
		ltitle.setFont(fo);
		add(ltitle, BorderLayout.NORTH);	
		
		jp.add(l1 = new JLabel("Số 1:"));
		jp.add(textSo1 = new JTextField(10));

		l2 = new JLabel("Số 2:");
		l2.setBounds(20, 30, 100, 100);
		jp.add(l2);

		jp.add(textSo2 = new JTextField(10));

		jp.add(l3 = new JLabel("Kết quả:"));
		textKQ = new JTextField(10);
		textKQ.setEditable(false);
		jp.add(textKQ);

	
		
		
		btnCong = new JButton("Cộng");
		btnCong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				textSo1.getText().toString();
				double so1 = Double.parseDouble(textSo1.getText());
				double so2 = Double.parseDouble(textSo2.getText());
		
					textKQ.setText(so1 + so2 + "");
		

			}
		});

		btnTru = new JButton("Trừ");
		btnTru.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				textSo1.getText().toString();
				double so1 = Double.parseDouble(textSo1.getText());
				double so2 = Double.parseDouble(textSo2.getText());
		
					textKQ.setText(so1 - so2 + "");
		

			}
		});

		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textSo1.setText("");
				textSo2.setText("");
				textKQ.setText("");
			}
		});

		btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		jpBot.add(btnCong);
		jpBot.add(btnTru);
		jpBot.add(btnXoa);
		jpBot.add(btnThoat);

		add(jp, BorderLayout.CENTER);
		add(jpBot, BorderLayout.SOUTH);

		setSize(500, 500);
		setVisible(true);
	}

	public static void main(String[] args) {
		new LayoutCongTru();
	}
}
