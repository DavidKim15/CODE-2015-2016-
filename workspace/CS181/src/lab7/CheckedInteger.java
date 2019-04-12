/*
 * I pledge my honor that I have abided by the Stevens Honors System.
 * -David Kim
 */

package lab7;

public class CheckedInteger extends Number implements Comparable<CheckedInteger> {
	protected int value;
	public static final String NAME = "David Kim";
	
	/**ZERO argument constructor
	 * default number for 'value' will be 0
	 */
	public CheckedInteger() {
		value = 0;
	}
	
	/**INT argument constructor
	 * 
	 * @param val: 'value' will be the value of val
	 */
	public CheckedInteger(int val){
			value = 0;
		value = val;
	}
	
	/**CHECKEDINTEGER argument constructor
	 * 
	 * @param val: the value of the object, val, will be the value of the current object
	 */
	public CheckedInteger(CheckedInteger val) {
		if (val == null) {
			this.value = 0;
		}
		this.value = val.value;
	}
	
	/**
	 * abs() will return the absolute value of the CheckedInteger
	 * @return the absolute value of the CheckedInteger
	 * @throws IntegerOverflowException
	 */
	public CheckedInteger abs() throws IntegerOverflowException {

		
		
		//abs() will cause an integer to overflow only if we try to find the absolute value of the minimum Integer that Java has
		if (value == Integer.MIN_VALUE) {					
			throw new IntegerOverflowException(value, true);	//inputs 'true' since 'true' signifies abs() function
		}
		int val = value;										//val is a buffer of a sort so that "value" instance variable will not be changed itself
		if (val < 0) {
			 val *= -1;
		}
		CheckedInteger temp = new CheckedInteger(val);
		return temp;
		
	}
	
	/**
	 * returns the string representation of 'value'
	 * value is an integer so it already has a toString() for itself
	 */
	public String toString() {
		return ""+value;
	}
	
	/**
	 * 
	 * @param r: the number that will be added to the CheckedInteger
	 * @return: the sum of the int r and current CheckedInteger
	 * @throws IntegerOverflowException
	 */
	public CheckedInteger add(int r) throws IntegerOverflowException {

		
		int newVal = value + r;

		//if the two operands are negative and the sum is positive, that means that the sum overflowed
		if (newVal > 0 && value < 0 && r < 0){
			throw new IntegerOverflowException(value, r, true); //'true' signifies it's a sum()
		}
		
		//if the two operands are positive and the sum is negative, that means that the sum overflowed
		else if (newVal < 0 && value > 0 && r > 0) {
			throw new IntegerOverflowException(value, r, true);	//'true' signifies it's a sum()
		}
		
		CheckedInteger temp = new CheckedInteger(newVal);
		return temp;
	}
	
	/**
	 * 
	 * @param other: this is the object that will be added to the current CheckedInteger
	 * @return the sum of the two CheckedInteger
	 * @throws IntegerOverflowException
	 */
	public CheckedInteger add(CheckedInteger other) throws IntegerOverflowException {

		
		try {											//this try-catch block will check if 'other' is something that's not 'CheckedInteger'
		int num = value + other.value;
		}
		catch (IllegalArgumentException e) {			//will throw IllegalArgumentException
			e.printStackTrace();	
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
		int newVal = value + other.value;				//this will actually add the two values of the two objects

		//if the two operands are negative and the sum is positive, that means that the sum overflowed
		if (newVal > 0 && value < 0 && other.value < 0){
			throw new IntegerOverflowException(value, other.value, true);
		}
		
		//if the two operands are positive and the sum is negative, that means that the sum overflowed
		else if (newVal < 0 && value > 0 && other.value > 0) {
			throw new IntegerOverflowException(value, other.value, true);
		}
		
		CheckedInteger temp = new CheckedInteger(newVal);
		return temp;
	}
	
	/**
	 * 
	 * @return the negated CheckedInteger
	 * @throws IntegerOverflowException
	 */
	public CheckedInteger negate() throws IntegerOverflowException{

		
		//negate() will cause an integer to overflow only if we try to find the absolute value of the minimum Integer that Java has
		if (value == Integer.MIN_VALUE) {	
			throw new IntegerOverflowException(value, false);
		}

		CheckedInteger temp = new CheckedInteger(value*-1);
		return temp;
	}
	
	/**
	 * 
	 * @param r: this is the number that will be subtracted from CheckedInteger
	 * @return the difference of the values in respect to the current Object's value
	 * @throws IntegerOverflowException
	 */
	public CheckedInteger sub(int r) throws IntegerOverflowException {

		
		CheckedInteger temp1 = new CheckedInteger(value);			//instantiating two CheckedInteger objects
		CheckedInteger temp2 = new CheckedInteger(r);

		int newValue = value;				//buffer variable 
		
		//this for loop will make sure to catch the buffer variable before it overflows
		if (r > 0) {
			for (int i = 0; i < r; i++) {
				if (newValue == Integer.MIN_VALUE && r - i > 0) {
					throw new IntegerOverflowException(value, r, true);
				}
				newValue --;

			}
		}
		
		//this for loop will make sure to catch the buffer variable before it overflows
		if (r < 0) {
			for (int i = 0; i > r; i--) {
				if (newValue == Integer.MAX_VALUE && i - r > 0) {
					throw new IntegerOverflowException(value, r, true);
				}
				newValue ++;
			}
		}
		
		CheckedInteger temp = new CheckedInteger(newValue);
		return temp;
	}
	
	/**
	 * 
	 * @param other: this is the object that will be subtracted from the current CheckedInteger
	 * @return the difference between the two CheckedInteger objects in respect to the current CheckedInteger
	 * @throws IntegerOverflowException
	 */
	public CheckedInteger sub(CheckedInteger other) throws IntegerOverflowException{

		//try-catch block accounts for IllegalArgumentException for the parameter
		try {														
			CheckedInteger temp2 = new CheckedInteger(other.value);		//this is to check if 'other' is a CheckedInteger object
	
			int newValue = value; //buffer variable
			
			//this for loop will make sure to catch the buffer variable before it overflows
			if (other.value > 0) {
				for (int i = 0; i < other.value; i++) {
					if (newValue == Integer.MIN_VALUE && other.value - i > 0) {
						throw new IntegerOverflowException(value, other.value, true);
					}
					newValue --;

				}
			}
			
			//this for loop will make sure to catch the buffer variable before it overflows
			if (other.value < 0) {
				for (int i = 0; i > other.value; i--) {
					if (newValue == Integer.MAX_VALUE && i - other.value > 0) {
						throw new IntegerOverflowException(value, other.value, true);
					}
					newValue ++;
				}
			}
		}
		catch (IllegalArgumentException e) {	//catches if the object in the argument is not correct
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		int newValue = value - other.value;
		CheckedInteger temp = new CheckedInteger(newValue);
		return temp;
	}
	
	/**
	 * 
	 * @param r: the number that will be multiplied with the current value
	 * @return: the product of the current value and the given argument
	 * @throws IntegerOverflowException
	 */
	public CheckedInteger mul(int r) throws IntegerOverflowException {

		
		CheckedInteger temp1 = new CheckedInteger(value);			//instantiating two CheckedInteger objects
		CheckedInteger temp2 = new CheckedInteger(r);
		
		int newVal = value * r;										//buffer variable so that the actual 'value' does not change
		
		//these cases will account for when trying to multiply the MINIMUM VALUE of Integers for Java with a number that is NOT 1 or 0.
		//Mathematically, Java will overflow and not give you the right number if the MIN_VALUE is multiplied by a number NOT by 1 or 0.
		if (temp1.value == Integer.MIN_VALUE && (temp2.value > 1 || temp2.value <= -1)) {
			throw new IntegerOverflowException(value, r, false);
		}
		if (temp2.value == Integer.MIN_VALUE && (temp1.value > 1 || temp1.value <= -1)) {
			throw new IntegerOverflowException(value, r, false);
		}
		
		//MAINLY checking for IntegerOverflowException
		//this try-catch block will check if there could potentially be a time when this code would divide by zero (this is for ArithmeticException)
		try {
			if (absolute(temp1).value > Integer.MAX_VALUE/(absolute(temp2).abs().value)){
				throw new IntegerOverflowException(value, r, false);
			}
		}
		catch (ArithmeticException e){
			e.printStackTrace();
		}
		
		CheckedInteger temp = new CheckedInteger(newVal);
		return temp;
	}
	
	/**
	 * HELPER METHOD FOR ABSOLUTE VALUE
	 * @param i the CheckedInteger that I want to find the absolute value of
	 * @return the absolute value of the CheckedInteger
	 */
	public static CheckedInteger absolute(CheckedInteger i) {
		if (i.value < 0) {
			i.value *= -1;
		}
		return i;
	}
	
	public CheckedInteger mul(CheckedInteger other) throws IntegerOverflowException{

		
		try {
			CheckedInteger temp1 = new CheckedInteger(value);			//instantiating two CheckedInteger objects
			CheckedInteger temp2 = new CheckedInteger(other.value);
			
			
			//these cases will account for when trying to multiply the MINIMUM VALUE of Integers for Java with a number that is NOT 1 or 0.
			//Mathematically, Java will overflow and not give you the right number if the MIN_VALUE is multiplied by a number NOT by 1 or 0.
			if (temp1.value == Integer.MIN_VALUE && (temp2.value > 1 || temp2.value <= -1)) {
				throw new IntegerOverflowException(value, other.value, false);
			}
			if (temp2.value == Integer.MIN_VALUE && (temp1.value > 1 || temp1.value <= -1)) {
				throw new IntegerOverflowException(value, other.value, false);
			}
			
			//MAINLY checking for IntegerOverflowException
			//this try-catch block will check if there could potentially be a time when this code would divide by zero (this is for ArithmeticException)
			try {
				if (absolute(temp1).value > Integer.MAX_VALUE/(absolute(temp2).abs().value)){
					throw new IntegerOverflowException(value, other.value, false);
				}
			}
			catch (ArithmeticException e){
				e.printStackTrace();
			}
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
		int newVal = value * other.value;										//buffer variable so that the actual 'value' does not change

		CheckedInteger temp = new CheckedInteger(newVal);
		return temp;
	}
	
	public CheckedInteger div(int r){

		
		try {		//this try-catch block will account for how we can't divide by zero
			int num = value / r;
		}
		catch (ArithmeticException e) {
			e.printStackTrace();
		}
		CheckedInteger temp = new CheckedInteger(value/r);
		return temp;
	}
	
	public CheckedInteger div(CheckedInteger other)  {
		
		try {		//this try-catch block will account for how we can't divide by zero and if 'other' is truly a CheckedInteger object
			int num = value / other.value;
		}
		catch (ArithmeticException e) {
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
		CheckedInteger temp = new CheckedInteger(value/other.value);
		return temp;
	}
	
	public int compareTo (CheckedInteger other) {
		return value-other.value;
	}
	@Override
	public int intValue() {
		return (int)value;
	}
	@Override
	public long longValue() {
		return (long)value;
	}
	@Override
	public float floatValue() {
		return (float)value;
	}
	@Override
	public double doubleValue() {
		return (double)value;
	}
}
