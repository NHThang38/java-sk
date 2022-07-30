package ThucHanhJDBC;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FormLopHoc extends JFrame{
	JPanel pNorth, pCenter, pSouth;
	JLabel lblTitle, lblMa,lblTen, lblGVCN;
	JTextField txtMa, txtTen, txtGVCN;
	JButton btnThem, btnLuu, btnSua, btnXoa;
	
	JTable table;
	DefaultTableModel tableModel;
	JScrollPane scrollPane;
	
	
	public FormLopHoc() {
		setTitle("THÔNG TIN LỚP HỌC");
		pNorth = new JPanel();
		pNorth.add(lblTitle = new JLabel("THÔNG TIN LỚP HỌC")); 
		Font font = new Font("Times New Roman", Font.BOLD, 30);
		lblTitle.setFont(font);

		add(pNorth, BorderLayout.NORTH);
		
		//
		pCenter = new JPanel();
		
		int x = 20, y = 40, with = 150, hight = 30;
		
		pCenter.add(lblMa = new JLabel("Mã lớp: "));
		lblMa.setBounds(x, y, with, hight);
		
		y+= 40;
		
		pCenter.add(lblTen = new JLabel("Tên lớp: "));
		lblTen.setBounds(x, y, with, hight);
		
		y+= 40;
		
		pCenter.add(lblGVCN = new JLabel("Giáo viên chủ nhiệm: "));
		lblGVCN.setBounds(x-5, y, with, hight);
		
	
		
		
		
		
		
		pCenter.setLayout(null);
		add(pCenter, BorderLayout.CENTER);
		
		
		
		
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new FormLopHoc();
	}

}
