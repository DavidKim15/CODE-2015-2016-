/*
 * Lab03.java
 *
 * I pledge on my honor that I have abided by the Stevens Honor System.
 * David Kim
 *
 */

package Lab3; /*DO NOT CHANGE!!!!!!*/

/**
 * ENTER A BRIEF DESCRIPTION OF WHAT YOUR PROGRAM DOES HERE
 *
 * IN THE LINES BELOW, FILL IN FIELDS WITH APPROPRIATE RESPONSES. DELETE THIS LINE.
 * @author David Kim
 * @version 1.0
 * @since 20160920
 * 
 */


public class Lab03_DavidKim{
    
    public static final String NAME = "David Kim"; /*<<<insert your name here*/

    /**
     * Given a binary periodic string, return an double value
     * Accepts any input ("._" should return 0)
     * 
     * @param s  the binary string, in the format of D.A_P
     * @return   the String  value of the binary string, in the form of 
     *           INTEGER_VALUE + numerator/denominator (includes period and antiperiod part)
     *           INTEGER_VALUE should not be a decimal
     *           
     *           y = period / (2^(|period| - 1) (2 ^ (|anti-period|))) is the formula
     */
    public static String binaryPeriodic(String s){
    	
    	for (int i = 0; i < s.length(); i ++){		// this is to check for invalid input
    		int counterUnder = 0;					// this will count how many underscores there are
    		int counterPeriod = 0;					// this will count how many periods there are (".")
    		if (!s.substring(i, i+1).equals("0") && !s.substring(i, i+1).equals("1")			// checks if only 0s and 1s are in the input
    				&& !s.substring(i, i+1).equals(".") && !s.substring(i, i+1).equals("_")){
    			return "Invalid Input. Enter a binary number.";
    		}
    		if (s.substring(i, i+1).equals(".")) {		// counts how many periods there are in input
    			counterPeriod++;
    		}
    		if (s.substring(i, i+1).equals("_")) {		// counts how many underscores there are in input
    			counterUnder++;
    		}
    		if (counterPeriod > 1 || counterUnder > 1){	// if there is more than 1 underscore or period, then it's invalid
    			return "Invalid Input.";
    		}
    	} // end checking
    	
    	// splits the input
    	String numberString = s.substring(0, s.indexOf("."));						// (portion left of decimal point)
    	String antiperiodString = s.substring(s.indexOf(".")+1, s.indexOf("_"));	// (portion from decimal point to underscore (noninclusive))
    	String periodString = s.substring(s.indexOf("_")+1);						// (portion from after the underscore and over
    	
    	
    	// convert numberValue (binary) to decimal
    	
    	String numberValue = "" + BtoI(numberString);					// puts the portion left of the decimal and converts to decimal
    	
    	// end conversion from numberValue(decimal) to decimal
    	
    	// convert antiperiodString to fraction
    	int antiNumerator = 0;											// will keep track of binary conversion with fraction (0/1)
    	int antiDenominator = 1;
    	
    	for (int i = 0; i < antiperiodString.length(); i ++){			// for each digit in antiperiod portion
    		antiDenominator *= 2;										// changes the denominator after each shift to the next digit
    		antiNumerator *= 2;											// must multiply numerator too for equivalence
    		if (antiperiodString.substring(i,i+1).equals("1")){			// will essentially be adding (2^-n) when there is a "1"
    			antiNumerator += 1;
    		}
    	}
    	
    	// end convert antiperiodString to fraction
    	
    	// convert periodString to fraction
    	int antiPeriodCount = antiperiodString.length();				// finds how many digits are in the antiperiod
    	int periodCount = periodString.length();						// finds how many digits are in the period
    	 
    	int numerator = (int)BtoI(periodString);						// numerator for formula for period conversion
    	int denominator = (int)(Math.pow(2, antiPeriodCount) * ((Math.pow(2, periodCount))-1));		// denominator for formula for period conversion
    	
    	// end convert periodString to fraction
    	
    	// add the antiperiod and the period if antiperiod has value
    	if (antiNumerator != 0){										// if there is a value for the antiperiod
    	numerator = numerator * antiDenominator + antiNumerator * denominator;	// cross multiply the fractions basically
    	denominator = denominator * antiDenominator;
    	}
    	
    	// end adding the antiperiod and the period
    	
    	return numberValue + " + " + numerator + "/" + denominator;		// concatenates to give proper output
    }
    
    
   /**
    * Given a string, find the longest substring fowards and backwards
    * More details in lab description
    * Accepts any input (including empty strings)
    *
    * @param s1  String 1 to be processed
    * @param s2  String 2 to be processed
    * @return    a SPACE deliminated string with 4 values:
    *            longest substring when both strings forward,
    *            longest substring s1 backward, longest substring s2 backward
    *            longest substring both backward.
    *            Example return is "car car rac rac"
    */
    public static String longestSubstring(String s1, String s2){
    	
    	
    	
    	//reverse s1 
    	String r1 = "";
    	for (int i = s1.length(); i > 0; i --){
    		r1 = r1 + s1.substring(i-1,i);
    	}
    	// end reverse s1
    	
    	//reverse s2
    	String r2 = "";
    	for (int i = s2.length(); i > 0; i --){
    		r2 = r2 + s2.substring(i-1,i);
    	}
    	// end reverse s2
    	String FF = findCommon(s1, s2); //s1 forward order and s2 forward order
    	String RF = findCommon(r1, s2); //s1 reverse order and s2 forward order
    	String FR = findCommon(s1, r2); //s1 forward order and s2 reverse order
    	String RR = findCommon(r1, r2); //s1 reverse order and s2 reverse order
    	
	return FF + " " + RF + " " + FR + " " + RR;
    }

   /**
    * Computes the intermediate steps of the 3n+1 conjecture
    * 
    * @requires n to be a positive, non-zero integer 
    *      ^^The requires flag tells you what input conditions
    *        your method requires. So this flag means your method
    *        does not need to handle negative nums or 0. Yay!
    *
    * @param  n  the number to be processed
    * @return    the string of intermediate steps, seperated by SPACES. 
    *            The last character of the string, based on the conjecture
    *            should always be a 1
    */
    public static String threeNPlusOne(int n){
    	
    	String ans = "";
    	
    	while (n != 1){					// this loop will end when n = 1, which is when the conjecture ends
    		if (n % 2 == 1){			// if the n is odd
    			n = 3*n+1;				
        		ans = ans + " " + n;	// concatenates n to the ans variable
    		}
    		if (n % 2 == 0){			// if the n is even
	    		n /= 2;
	    		ans = ans + " " + n;	// concatenates n to the ans variable
    		}
    	}
    	return ans.substring(1);
    }

    
    /**
     * Given a String that represents a binary number, 
     * outputs the number in decimal form (as a long)
     *
     * @param String number		this is the String representation of the binary number in question
     * @return				    A long, changing the binary input as a decimal number
     */
    public static long BtoI (String number){ 
		long ans = 0;												// this will add the binary digit converted to decimal
		long counter = 1;											// this will keep track of the binary base
		for (int i = 0; number.length() > 0; i++){			
			if (number.substring(number.length()-1).equals ("1")){	// if the digit has a 1, then add it to ans
				ans += counter;
			}
			counter *= 2;
			number = number.substring(0,number.length()-1);			// chops off the last "digit" of the string to 
		}
		return ans;
	} 
    // end BtoI function
    
    /**
     * 
     * finds the longest common substring between two given strings
     * 
     * @param s1	the first string that we want to compare
     * @param s2	the second string that we want to compare
     * @return		The longest common substring between the two strings,
     * 				if there is no common substring, then the method will return "None Found"
     */
    public static String findCommon (String s1, String s2){
    	String sub1 = "";    	// this will be the string from s1 that will be used to see if it exists in s2
    	String sub2 = "";		// this will be the substring in s2 that is in common with s1
    	String ans = "";		
    	
    	for (int i = 0; i < s1.length(); i ++){													// the "start" of the substring
    		for (int j = i + 1; j < s1.length()+1; j ++){										// the "end" of the substring
    			sub1 = s1.substring(i, j);														//
    			if (s2.contains(sub1)){															// if sub1 exists in s2
        			sub2 = s2.substring(s2.indexOf(sub1), sub1.length() + s2.indexOf(sub1));	// then find the substring in s2 and set it to sub2

    				if (sub2.length() > ans.length()){	// sub2 will turn into ans if sub2 is longer than the previous ans
    					ans = sub2;
    				}
    			}
    		}
    	}
    	if (ans != ""){																			// if ans has a value
    		return ans;
    	}
    	return "None Found.";																	// if there is no substring in common
    }
    

    public static void main(String[] args){

	/* IMPORTANT (NEW FOR THIS LAB 3):
	 *
	 * PLEASE COMMENT OUT YOU ENTIRE MAIN FUNCTION BEFORE SUBMITTING. 
	 * THERE SHOULD ADDITIONALLY BE NO PRINTLN STATEMENTS IN ANY OF THE ABOVE METHODS
	 *
	 * This main method is for your testing purpose only. There should
	 * be NO code in this method that enables the above methods to properly function
	 *
	 * Feel free to add additional methods
	 * Any new methods you create you  MUST also write javadocs
	 * However, you CANNOT delete or modify any of the given method headers
	 *
	 * If you have any questions, please ask a CA for clarification
	 * 
	 * Remember that you CANNOT use any java libraries to convert bin->int
	 * Doing so will result in a 0 on the assignment
	 *
	 * You may delete this comment block after you have read it.
	 * Good Luck, and enjoy your String Cheese!
	 */
    	if (args[0].equals("1")){
    		System.out.println(binaryPeriodic(args[1]));
    	}
    	
    	if (args[0].equals("2")){
    		System.out.println(longestSubstring(args[1], args[2]));
    	}
    	
    	if (args[0].equals("3")){
    		int n = Integer.parseInt(args[1]);
    		System.out.println(threeNPlusOne(n));
    	}
    }
    
}
