package baitap1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;




public class Calculator extends JFrame implements ActionListener {

    private JTextField txtResult;


    private double kq = 0;


    private String phep = "=";


    private boolean click = true;
	public Calculator() {

		setTitle("Calculator");

	
		txtResult = new JTextField("0");
		txtResult.setBorder(BorderFactory.createLineBorder(Color.black));
		txtResult.setEditable(false);
		add(txtResult, BorderLayout.NORTH);
		
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(4,4,2,2));
		 // Danh sach 16 nut
	    String[] arr = {"7", "8", "9", "/",
	                	"4", "5", "6", "*",
	                    "1", "2", "3", "-",
	                    "0", ".", "=", "+"};
	    // Dat cac nut vao panel, gan doi tuong listen
	    for(int i = 0; i < arr.length; i++) {
	    	JButton btn = new JButton(arr[i]);
	    	jp.add(btn);
	    	btn.addActionListener(this);
	    }
	       
	    add(jp, BorderLayout.CENTER);
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

        String s = e.getActionCommand();

  
        if ('0' <= s.charAt(0) && s.charAt(0) <= '9'
                || s.equals(".")) {
   
            if (click) {
     
            	txtResult.setText(s);
            } else { 
            
            	txtResult.setText(txtResult.getText() + s);
            }

      
            click = false;
        } else { 
            if (click) {
             
                if (s.equals("-")) {
                	txtResult.setText(s);
                    click = false;
                } else {
                    phep = s;
                }
            } else { 
                double x = Double.parseDouble(txtResult.getText());
                calculator(x);
                phep = s;
                click = true;
            }
        }
    }


    public void calculator(double n) {
        if (phep.equals("+")) {
            kq += n;
        } else if (phep.equals("-")) {
            kq -= n;
        } else if (phep.equals("*")) {
            kq *= n;
        } else if (phep.equals("/")) {
            kq /= n;
        } else if (phep.equals("=")) {
            kq = n;
        }

        txtResult.setText(kq + "");
    }
		
	
	
	
	public static void main(String[] args) {
		new Calculator();
		
		
	}
	


}
