/**
 * I pledge my honor that I have abided by the Stevens Honor System. - David Kim
 * GUI converts when text field loses focus
 */


package GUIPractice;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.Canvas;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class NumberConversion {

	private JFrame frame;
	private JTextField binaryField;
	private JTextField hexField;
	private JTextField decimalField;
	private JTextField octalField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NumberConversion window = new NumberConversion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NumberConversion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		binaryField = new JTextField();
		binaryField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String input = binaryField.getText();
				
				
				
				for (int i = 0; i < input.length(); i ++){
					if (!input.substring(i, i+1).equals("1") && !input.substring(i, i+1).equals("0")){
						binaryField.setText("INVALID INPUT.");
						octalField.setText("Invalid input in Binary Field");
						hexField.setText("Invalid input in Binary Field");
						decimalField.setText("Invalid input in Binary Field");
						break;
					}
				}
				
				if (!binaryField.getText().equals("INVALID INPUT.")){
					octalField.setText(BtoO(Long.parseLong(input)));
					hexField.setText(BtoX(Long.parseLong(input)));
					decimalField.setText(""+BtoI(Long.parseLong(input)));
				}
				
			}
		});
		binaryField.setBounds(217, 99, 207, 26);
		frame.getContentPane().add(binaryField);
		binaryField.setColumns(10);
		
		hexField = new JTextField();
		hexField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String input = hexField.getText();
				
				for (int i = 0; i < input.length(); i ++){
					if (!input.substring(i, i+1).equals("A") && !input.substring(i, i+1).equals("B")&& 
							!input.substring(i, i+1).equals("C")&& !input.substring(i, i+1).equals("D")&& 
							!input.substring(i, i+1).equals("E")&& !input.substring(i, i+1).equals("F")){
						hexField.setText("INVALID INPUT.");
						octalField.setText("Invalid input in Hex Field");
						binaryField.setText("Invalid input in Hex Field");
						decimalField.setText("Invalid input in Hex Field");
						break;
					}
				}
				
				if (hexField.getText().equals("INVALID INPUT.")){
					octalField.setText(XtoO(input));
					binaryField.setText(XtoB(input));
					decimalField.setText(""+XtoI(input));
				}
				
			}
		});
		hexField.setBounds(217, 175, 207, 26);
		frame.getContentPane().add(hexField);
		hexField.setColumns(10);
		
		decimalField = new JTextField();
		decimalField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String input = decimalField.getText();
				binaryField.setText(ItoB(Long.parseLong(input)));
				octalField.setText(ItoO(Long.parseLong(input)));
				hexField.setText(ItoX(Long.parseLong(input)));
			}
		});
		decimalField.setBounds(217, 218, 207, 26);
		frame.getContentPane().add(decimalField);
		decimalField.setColumns(10);
		
		octalField = new JTextField();
		octalField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String input = octalField.getText();
				binaryField.setText(OtoB(Long.parseLong(input)));
				decimalField.setText(""+OtoI(Long.parseLong(input)));
				hexField.setText(OtoX(Long.parseLong(input)));
			}
		});
		octalField.setBounds(217, 137, 207, 26);
		frame.getContentPane().add(octalField);
		octalField.setColumns(10);
		
		JLabel lblBinary = new JLabel("Binary:");
		lblBinary.setBounds(89, 104, 61, 16);
		frame.getContentPane().add(lblBinary);
		
		JLabel lblHexadecimal = new JLabel("Hexadecimal:");
		lblHexadecimal.setBounds(89, 180, 116, 16);
		frame.getContentPane().add(lblHexadecimal);
		
		JLabel lblDecimal = new JLabel("Decimal:");
		lblDecimal.setBounds(89, 223, 61, 16);
		frame.getContentPane().add(lblDecimal);
		
		JLabel lblOctal = new JLabel("Octal:");
		lblOctal.setBounds(89, 142, 61, 16);
		frame.getContentPane().add(lblOctal);
		
		JLabel lblNewLabel = new JLabel("Base Conversion");
		lblNewLabel.setBounds(24, 21, 321, 35);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblInputBinaryFirst = new JLabel("Input Binary First, then click somewhere else. No negatives");
		lblInputBinaryFirst.setBounds(24, 68, 386, 16);
		frame.getContentPane().add(lblInputBinaryFirst);
		
		

	}
	
	//*************************************************************************************************//
	//Converting base methods are created here from Hw0
	
	public static Long BtoI (Long number){
		long ans = 0;
		long counter = 1;
		while (number > 0){
			ans += ((number%10) * counter);
			counter*=2;
			number /= 10;
		}
		return ans;
	} // end BtoI function
	
	public static Long OtoI (Long number){
		long ans = 0;
		long counter = 1;
		while (number > 0){
			ans += ((number%10) * counter);
			counter*=8;
			number /= 10;
		}
		return ans;
	}
	
	public static Long XtoI (String number){
	long ans = 0;
	long counter = 1;
	long num = 0;
	
	while (number.length() > 0) {
		String piece = number.substring(number.length()-1);
		
		if (piece.equals("A")||piece.equals("B")||piece.equals("C")||piece.equals("D")||piece.equals("E")||piece.equals("F")){
			if (piece.equals("A")){
				ans+=(10*counter);
			}
			if (piece.equals("B")){
				ans+=(11*counter);
			}
			if (piece.equals("C")){
				ans+=(12*counter);
			}
			if (piece.equals("D")){
				ans+=(13*counter);
			}
			if (piece.equals("E")){
				ans+=(14*counter);
			}
			if (piece.equals("F")){
				ans+=(15*counter);
			}
		}
		
		else {
			num = Long.parseLong(piece);
			ans += (num*counter);
		}
		
		number = number.substring(0,number.length()-1);
		counter*=16;
	}
	
	return ans;
}

	public static String OtoB (Long number){
		String ans = "";
		String part = "";
		String zero = "0";
		while (number > 0){
			part += ItoB(number%10);
			if (part.length()<3){
				if (part.length()==2){
					part = zero + part;
				}
				if (part.length()==1){
					part = zero + zero + part;
				}
			}
			ans = part+ans;
			part = "";
			number/=10;
		}
		return ans;
	}
	
	public static String ItoB (Long number){
		if (number == 0) {
	           return "0";
	     }
	     String binary = "";
	     while (number > 0) {
	        long r = number % 2;
	        binary = r + binary;
	        number/=2;
	     }
	       return binary;
	} // end ItoB function
	
	public static String XtoB (String number){
		Long i = XtoI(number);
		String ans = ItoB(i);
		return ans;
	}
	
	public static String BtoO (Long number){
		String ans = "";
		Long num = BtoI(number);
		
		if (num == 0) {
			return "0";
		}
		while (num > 0){
			Long r = num % 8;
			ans = r + ans;
			num/=8;
		}
		return ans;
	}
	
	public static String ItoO (Long number){
		String ans = "";
		if (number == 0){
			return "0";
		}
		while (number > 0){
			Long r = number%8;
			ans = r + ans;
			number/=8;
		}
		return ans;
	}
	
	public static String XtoO (String number){
		Long i = XtoI(number);
		String ans = ItoO(i);
		return ans;
	}
	
	public static String BtoX (Long number){
		String ans = "";
		String m = "";
		Long i = BtoI(number);
		
		while (i > 0) {
			Long r = i % 16;
			
			if (r > 9){
				if (r == 10){
					m = "A";
				}
				if (r == 11){
					m = "B";
				}
				if (r == 12){
					m = "C";
				}
				if (r == 13){
					m = "D";
				}
				if (r == 14) {
					m = "E";
				}
				if (r == 15) {
					m = "F";
				}
			ans = m + ans;
			}

			else {
			ans = r + ans;
			}
			i /= 16;
		}
		return ans;
	}

	public static String OtoX (Long number){
		Long i = OtoI(number);
		String ans = ItoX(i);
		return ans;
	}
	
	public static String ItoX (Long number){
		String ans = "";
		String m = "";
		if (number == 0) {
			return "0";
		}
		while (number > 0) {
			Long r = number % 16;
			
			if (r > 9){
				if (r == 10){
					m = "A";
				}
				if (r == 11){
					m = "B";
				}
				if (r == 12){
					m = "C";
				}
				if (r == 13){
					m = "D";
				}
				if (r == 14) {
					m = "E";
				}
				if (r == 15) {
					m = "F";
				}
			ans = m + ans;
			}

			else {
			ans = r + ans;
			}
			number /= 16;
		}
		return ans;
			
		}
}
