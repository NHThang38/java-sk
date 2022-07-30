package baitap2;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class buttondemo extends JFrame implements ActionListener {
			JPanel jp;
		JButton btnRed, btnBlue, btnWhite;
		public buttondemo() {
			super("Button Demo");
			
			 jp  = new JPanel();
			 jp.setBackground(Color.black);
			JPanel jpbot = new JPanel();
			btnRed = new JButton("RED");
			btnBlue = new JButton("BLUE");
			btnWhite = new JButton("WHITE");
			jpbot.add(btnRed);
			jpbot.add(btnBlue);
			jpbot.add(btnWhite);
			
			btnRed.addActionListener(this);
			btnBlue.addActionListener(this);
			btnWhite.addActionListener(this);
			//cach1 
//			btnRed.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//						jp.setBackground(Color.red);
//					
//				}
//			});
//			
			
			add(jp,BorderLayout.CENTER);
			add(jpbot, BorderLayout.SOUTH);
			setSize(500, 500);
			setVisible(true);
		}

		
		@Override
		public void actionPerformed(ActionEvent e ) {
			Object source = e.getSource();
			
			if(source.equals(btnRed)) {
				jp.setBackground(Color.red);
			}else if(source.equals(btnBlue)) {
				jp.setBackground(Color.blue);
			}else {
				jp.setBackground(Color.white);
			}
			
			
			
		}
		public static void main(String[] args) {
			new buttondemo();
		}

	
}
