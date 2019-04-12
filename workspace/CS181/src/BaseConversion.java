// I pledge on my honor that I have abided by the Stevens Honor System
// David Kim

// given any base, convert it to the appropriate base
// Java Conversion b, i, x, o -- b, i , x, o

public class BaseConversion {

	public static void main (String[] args) {
		

		if (args[1].equals("b")){
			if (args[0].substring(0,1).equals("i")) {
				Long num = Long.parseLong(args[0].substring(1));
				System.out.println("b" + ItoB(num));
			}
			if (args[0].substring(0,1).equals("o")) {
				Long num = Long.parseLong(args[0].substring(1));
				System.out.println("b" + OtoB(num));
			}
			if (args[0].substring(0,1).equals("x")) {
				System.out.println("b" + XtoB(args[0].substring(1)));
			}
			if (args[0].substring(0,1).equals("b")){
				System.out.println ("b"+args[0].substring(1));
			}		
		
		}
		
		if (args[1].equals("i")){
			if (args[0].substring(0, 1).equals("b")){
				Long num = Long.parseLong(args[0].substring(1));
				System.out.println("i" + BtoI(num));
			}
			if (args[0].substring(0, 1).equals("o")){
				Long num = Long.parseLong(args[0].substring(1));
				System.out.println("i" + OtoI(num));
			}
			if (args[0].substring(0, 1).equals("x")){
				System.out.println("i" + XtoI(args[0].substring(1)));
			}
			if (args[0].substring(0,1).equals("i")){
				System.out.println ("i"+args[0].substring(1));
			}
		}
		
		if (args[1].equals("o")){
			if (args[0].substring(0, 1).equals("b")){
				Long num = Long.parseLong(args[0].substring(1));
				System.out.println("o" + BtoO(num));
			}
			if (args[0].substring(0,1).equals("i")){
				Long num = Long.parseLong(args[0].substring(1));
				System.out.println("o" + ItoO(num));
			}
			if (args[0].substring(0,1).equals("x")){
				System.out.println("o" + XtoO(args[0].substring(1)));
			}
			if (args[0].substring(0,1).equals("o")){
				System.out.println ("o"+args[0].substring(1));
			}
		}
		
		if (args[1].equals("x")){
			if (args[0].substring(0, 1).equals("b")){
				Long num = Long.parseLong(args[0].substring(1));
				System.out.println("x" + BtoX(num));
			}
			if (args[0].substring(0, 1).equals("o")){
				Long num = Long.parseLong(args[0].substring(1));
				System.out.println("x" + OtoX(num));
			}
			if (args[0].substring(0, 1).equals("i")){
				Long num = Long.parseLong(args[0].substring(1));
				System.out.println("x" + ItoX(num));
			}
			if (args[0].substring(0,1).equals("x")){
				System.out.println ("x"+args[0].substring(1));
			}

		}
	}
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
