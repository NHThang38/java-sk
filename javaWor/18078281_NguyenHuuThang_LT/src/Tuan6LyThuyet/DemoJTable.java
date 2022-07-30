package Tuan6LyThuyet;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DemoJTable extends JFrame implements ActionListener {
	JLabel lblHo, lblTen;
	JPanel  pCenter, pSouth;
	JTextField txtHo, txtTen;
	JButton btnThem, btnXoa, btnThoat;
	DefaultTableModel model;
	JTable table;
	public DemoJTable() {
		setTitle("Edit Table");
		
		pCenter = new JPanel();
		
		int x = 100, y = 40, with = 100, height = 30;
		pCenter.add(lblHo = new JLabel("Họ:"));
		lblHo.setBounds(x, y, with, height);
		
		y+=50;
		pCenter.add(lblTen = new JLabel("Tên:"));
		lblTen.setBounds(x, y, with, height);
		
		x = 130; y = 40; with = 250; height = 30;
		
		pCenter.add(txtHo = new JTextField());
		txtHo.setBounds(x, y, with, height);
		
		y+=50;
		
		pCenter.add(txtTen = new JTextField());
		txtTen.setBounds(x, y, with, height);
		 
		
//		JPanel pButton = new JPanel();
		pCenter.add(btnThem = new JButton("Thêm"));
		x = 130; y+= 40; with = 80; height = 30;
		btnThem.setBounds(x, y, with, height);
		x+=90;
		pCenter.add(btnXoa = new JButton("Xóa"));
		btnXoa.setBounds(x, y, with, height);
		x+=90;
		pCenter.add(btnThoat = new JButton("Thoát"));
		btnThoat.setBounds(x, y, with, height);
	
		pCenter.setLayout(new BorderLayout());
		add(pCenter, BorderLayout.CENTER);
		
//		///South
		pSouth = new JPanel();
		String[] cols = {"Họ", "Tên"};
		
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		
		JScrollPane pane = new JScrollPane(table);
		pSouth.add(pane);
		
		add(pSouth, BorderLayout.SOUTH);
	
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnThoat.addActionListener(this);
//		.addActionListener(this);
	
		setSize(550,680);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new DemoJTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 Object o = e.getSource();
		 
		 if(o.equals(btnThem)) {
			 if(txtHo.equals("") || txtHo.equals("")) {
				 JOptionPane.showMessageDialog(this, "Phải nhập dữ liệu trước");
			 }else {
				 Object[] obj = new Object[2];
				 obj[0] = txtHo.getText();
				 obj[1] = txtTen.getText();
				 model.addRow(obj);
				 
			 }
		 }else if(o.equals(btnXoa)) {
			 if(table.getSelectedRow() ==-1) {
				 JOptionPane.showMessageDialog(this, "Phải chọn dòng cần xóa");
			 }else {
				 if(JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa dòng này không?", "Canh bao", JOptionPane.YES_NO_CANCEL_OPTION)==JOptionPane.YES_OPTION) {
					 model.removeRow(table.getSelectedRow());
				 }
			 }
		 }else if(o.equals(btnThoat)) {
			 System.exit(0);
		 }
		 
		
	}

}
