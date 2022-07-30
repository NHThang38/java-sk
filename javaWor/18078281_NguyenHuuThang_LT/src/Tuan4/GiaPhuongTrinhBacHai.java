package Tuan4;

import java.awt.BorderLayout;
import java.awt.Button;
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

public class GiaPhuongTrinhBacHai extends JFrame {
	
	
	JButton btnGiai, btnXoaRong, btnThoat;
	JTextField txtnhapA, txtnhapB, txtnhapC, txtKQ;
	JLabel lblnhapA, lblnhapB, lblnhapC, lblKQ;
	
	public GiaPhuongTrinhBacHai() {
		super("GIAI PHUONG TRINH BAC HAI");
	
		JPanel pNor = new JPanel();
	

		
		Font font = new Font("Arial", Font.BOLD, 35);
		
		JLabel lblNor = new JLabel("GIẢI PHƯƠNG TRÌNH BẬC HAI");
		lblNor.setFont(font);
		pNor.add(lblNor);
		pNor.setBackground(Color.cyan);
		add(pNor, BorderLayout.NORTH);
		
		//center==============================
		JPanel pCen = new JPanel();
		
		//lbl
		pCen.add(lblnhapA = new JLabel("Nhập A:"));
		int x = 20, y = 40, with = 100, height = 30;
		lblnhapA.setBounds(x, y, with, height);
		
		y+=60;
		
		pCen.add(lblnhapB = new JLabel("Nhập B:"));
		lblnhapB.setBounds(x, y, with, height);

		y+=60;
		
		pCen.add(lblnhapC = new JLabel("Nhập C:"));
		lblnhapC.setBounds(x, y, with, height);
		
		y+=60;
		
		pCen.add(lblnhapC = new JLabel("Kết quả:"));
		lblnhapC.setBounds(x, y, with, height);
		
		
		add(pCen, BorderLayout.CENTER);
		pCen.setLayout(null);
		
		//txt
		 x+=100; y = 40; with =400;
		 pCen.add(txtnhapA = new JTextField());
		 txtnhapA.setBounds(x, y, with, height);
		 
		 y+=60;
		 pCen.add(txtnhapB = new JTextField());
		 txtnhapB.setBounds(x, y, with, height);
		 
		 y+=60;
		 pCen.add(txtnhapC = new JTextField());
		 txtnhapC.setBounds(x, y, with, height);
		 
		 y+=60;
		 pCen.add(txtKQ = new JTextField());
		 txtKQ.setBounds(x, y, with, height);
		 txtKQ.setEditable(false);
		
		//sou========================================================================
		JPanel pSou = new JPanel();
		pSou.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		
		btnGiai = new JButton("Giải");
		btnGiai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int a, b, c, delta;
				double kq,x1, x2;
				
				DecimalFormat fmt = new DecimalFormat("0.#");
				a = Integer.parseInt(txtnhapA.getText());
				b = Integer.parseInt(txtnhapA.getText());
				c = Integer.parseInt(txtnhapA.getText());
				if(a==0) {
					if(b==0) {
						
					}
				}
				
			}
		});
		pSou.add(btnGiai);
		btnXoaRong = new JButton("Xóa Rỗng");
		pSou.add(btnXoaRong);
		btnThoat = new JButton("Thoát");

		pSou.add(btnThoat);

		pSou.setLayout(new FlowLayout(1,20, 20));
	
		
		add(pSou, BorderLayout.SOUTH);
		
		setSize(600,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	

	public static void main(String[] args) {
		new GiaPhuongTrinhBacHai();
	}
}
