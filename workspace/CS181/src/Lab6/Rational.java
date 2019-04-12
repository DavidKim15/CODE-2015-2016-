/*
 * Lab05.java
 * I pledge my honor that I have abided by the Stevens Honor System.
 * -David Kim
 */
package Lab6;

/**
 * Description: Rational class will deal with rational numbers 
 * (through multiplication, division, addition, subtraction, finding LCM and GCD)
 * 
 * @author davidkim
 *
 */

public class Rational extends Number implements Comparable<Rational> {
	private long numerator;
	private long denominator;
	public static final String NAME = "David Kim";
	
	/**
	 * Constructor when there is no parameter
	 * Sets the numerator to '0' and denominator to '1' of the rational number
	 */
	public Rational () {
		numerator = 0;
		denominator = 1;
	}
	
	/**
	 * Constructor when user wants to deal with whole numbers
	 * @param 'num' becomes the numerator while the denominator is '1'
	 */
	public Rational (long num) {
		numerator = num;
		denominator = 1;
	}
	
	/**
	 * Constructor when user wants to set the numerator and denominator of fraction
	 * @param n: becomes numerator
	 * @param d: becomes denominator
	 */
	public Rational (long n, long d) {
		numerator = n;
		denominator = d;
	}
	
	/**
	 * Getter method for numerator
	 * @return: numerator
	 */
	public long getNumerator() {
		return numerator;
	}
	
	/**
	 * Getter method for the denominator
	 * @return: denominator
	 */
	public long getDenominator() {
		return denominator;
	}
	
	/**
	 * reduces the fraction to simplest form
	 */
	private void reduceTerms() {
		long gcd = computeGCD(numerator, denominator);
		numerator /= gcd;
		denominator /= gcd;
	}
	
	/**
	 * computes the GCD given two numbers of type long
	 * @param x: first number (long)
	 * @param y: second number (long)
	 * @return: the GCD of the two numbers
	 */
	private long computeGCD(long x, long y) {
		x = Math.abs(x);
		y = Math.abs(y);
		long gcd = 0; 				//will hold the GCD
		long oldY = 0;				//will serve as a holder 
		if (x < y){					//want to make sure that we know which number is the larger one
			gcd = computeGCD(y,x);
		}
		else {
			while (y > 0) {			// while y is not a whole-number factor of x, 
				oldY = y;			// oldY will save the current for y
				y = x%y;			// y will be updated as the remainder when diving x by y
				x = oldY;			// x will be the oldY
			}						// ultimately, x will be left as the GCD
		gcd = x;					// set value of x as the gcd
		}
		return gcd;					// returns the gcd
	}
	
	/**
	 * computes the LCM given two numbers of type long
	 * @param x: the first number (long)
	 * @param y: the second number (long)
	 * @return returns the LCM of the two numbers
	 */
	private long computeLCM(long x, long y) {
		return Math.abs((x*y)/(computeGCD(x,y)));	// the LCM can be found by multiplying 
	}												// the two numbers and diving by their GCD
	
	/**
	 * multiplies the current Rational object (fraction) by the number the user inputs
	 * @param r (the number that the fraction will be multiplied by)
	 * @return	returns the Rational object after it is multiplied (however, this does not update the object)
	 */
	public Rational mul (long r) {
		Rational temp = new Rational(numerator*r, denominator);
		temp.reduceTerms();
		return temp;
		
	}
	
	/**
	 * divides the current Rational object (fraction) by the number the user inputs
	 * @param r (the number that the fraction will be divided by)
	 * @return	returns the Rational object after it is divided (however, this does not update the object)
	 */
	public Rational div (long r) {
		Rational temp = new Rational(numerator,denominator*r);
		temp.reduceTerms();
		return temp;
	}
	
	/**
	 * shows the Inverse of the Rational number
	 * @return the inverse of the Rational number (however, this does not update the object)
	 */
	public Rational getInv() {
		Rational temp = new Rational(denominator, numerator);
		return temp;
	}
	
	/**
	 * performs the addition operation between the current Rational object and the inputed Rational object
	 * @param other: this is the object that the user wants to add with
	 * @return the sum of the two Rational numbers (however, this does not update the object)
	 */
	public Rational add (Rational other) {
		long lcm = computeLCM(this.denominator,other.getDenominator());		// find the LCM between the two Rational objects
		long currentNum = (lcm/this.denominator)*this.numerator;		// set the new numerator for each object
		long currentDenom = lcm;											// and set the new denominators with the LCM
		long otherNum = (lcm/other.getDenominator())*other.getNumerator();
																			
		long newNum = currentNum + otherNum;								// add the numerators 
		
		Rational temp = new Rational(newNum, currentDenom);					// create new Rational object that will be the sum
		temp.reduceTerms();
		return temp;
	}
	
	/**
	 * performs the subtraction operation between the current Rational object and the inputed Rational object
	 * @param other: this is the object that the user wants to subtract with
	 * @return the difference of the two Rational numbers (however, this does not update the object)
	 */
	public Rational sub (Rational other) {
		long lcm = computeLCM(this.denominator,other.getDenominator());		// find the LCM between the two Rational objects
		long currentNum = (lcm/this.denominator)*this.numerator;		// set the new numerator for each object
		long currentDenom = lcm;											// and set the new denominators with the LCM
		long otherNum = (lcm/other.getDenominator())*other.getNumerator();
		
		long newNum = currentNum - otherNum;								// subtract the denominators
		
		Rational temp = new Rational(newNum, currentDenom);					// create new Rational object that will be the difference
		temp.reduceTerms();
		return temp;
	}
	/**
	 * performs the multiplication operation between the current Rational object and the inputed Rational object
	 * @param other: this is the object that the user wants to multiply with
	 * @return the product of the two Rational numbers (however, this does not update the object)
	 */
	public Rational mul (Rational other) {
		Rational temp = new Rational(this.numerator * other.getNumerator(), 
									 this.denominator * other.getDenominator());
		temp.reduceTerms();
		return temp;
	}
	/**
	 * performs the division operation between the current Rational object and the inputed Rational object
	 * @param other: this is the object that the user wants to divide with
	 * @return the quotient of the two Rational numbers (however, this does not update the object)
	 */
	public Rational div (Rational other) {
		Rational temp = new Rational(this.numerator * other.getDenominator(), 
									 this.denominator * other.getNumerator());
		temp.reduceTerms();
		return temp;
	}
	/**
	 * compares the current Rational object with the Rational object that the user inputs
	 * returns '1' if current Rational object is greater than given object
	 * returns '-1' if current Rational object is less than given object
	 * returns '0' if current Rational object is equal to given object
	 */
	public int compareTo(Rational other) {
		int output = 0;
		long lcm = computeLCM(this.denominator,other.getDenominator());
		long currentNum = this.numerator * lcm;
		long otherNum = other.getNumerator() * lcm;
		
		if (currentNum > otherNum){
			output++;
		}
		else if (currentNum < otherNum) {
			output --;
		}
		else {
			output = output;
		}
		
		return output;
	}
	
	/**
	 * returns the string representation of the Rational object
	 */
	 public String toString() {
		 return numerator + " / " + denominator;
	 }
	
	/**
	 * returns the int representation of the Rational object
	 */
	 public int intValue() {
		return (int)(numerator/denominator); 
	 }
	 
	/**
	 * returns the long representation of the Rational object
	 */
	 public long longValue() {
		 return numerator/denominator;
	 }
	 
	/**
	 * returns the float representation of the Rational object
	 */
	 public float floatValue() {
		 return (float)(numerator/denominator);
	 }
	 
	/**
	 * returns the double representation of the Rational object
	*/
	 public double doubleValue() {
		 return (double)(numerator/denominator);
	 }
}
