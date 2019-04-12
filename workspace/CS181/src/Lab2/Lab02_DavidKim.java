/*
 * Lab02.java
 *
 * I pledge my honor that I have abided by the Stevens Honor System.
 * David Kim
 *
 */

package Lab2;

/**
 * There are 3 methods in this class.
 * Method1: entering a date in format yyyymmdd, the method will return the day of the week (Like Thursday)
 * Method2: entering a date and a number will return the new date after the number of days pass (+/- numbers)
 * Method3: entering two dates will give back how many days are between these two dates (+/- numbers)
 * 
 * @author David Kim
 * @version 1.0
 * @since 20160913
 * 
 */


public class Lab02_DavidKim{
    
    public static final String NAME = "David Kim";

    /**
     * Given a string, in the format of yyyymmdd, extracts the day of the 
     * week the date falls on
     *
     * @param s  the string, in the format of yyyymmdd to be processed
     * @return   the day of the week, as a String
     */
    public static String getDay(String s){
    	
    	if (s.length() != 8) {								// exceptional cases
    		return "Must enter a date in the format, yyyymmdd.";
    	}
    	
    	int day = Integer.parseInt(s.substring(6));			// breaking up the string formatted yyyymmdd into 3 integers
    	int month = Integer.parseInt(s.substring(4, 6));
    	int year = Integer.parseInt(s.substring(0,4));
    	
    	if (month > 12) {									// exceptional cases
    		return "Invalid month.";
    	}
    	if (month == 2 && year%4 == 0 && day > 29){
    		return "Invalid date.";
    	}
    	if (month == 2 && year%4 != 0 && day > 28){
    		return "Invalid date.";
    	}
    	
    	String ans = "";									// this variable will hold the day of the week
    	
    	if (month == 1 || month == 2){						// adjusting the month and year as needed, given the conditions
    		month += 12;
    		year -= 1;
    	}
    	
    	int dayOfWeek = (day-1+Math.abs((13*(month+1)/5))+ year + //this is the equation given
    					 Math.abs(year/4) - Math.abs(year/100) + 
    					 Math.abs(year/400))%7;
    	
    	if (dayOfWeek==0){
    		ans = "Sunday";
    	}
    	if (dayOfWeek==1){
    		ans = "Monday";
    	}
    	if (dayOfWeek==2){
    		ans = "Tuesday";
    	}
    	if (dayOfWeek==3){
    		ans = "Wednesday";
    	}
    	if (dayOfWeek==4){
    		ans = "Thursday";
    	}
    	if (dayOfWeek==5){
    		ans = "Friday";
    	}
    	if (dayOfWeek==6){
    		ans = "Saturday";
    	}
    	
    	return ans;
    }

   /**
    * Given a string date and a number, computes the day of the week n days 
    * after (or before) the date given
    *
    * @param s  the string, in the format of yyyymmdd to be processed
    * @param n  the number of days after (or before if negative) 
    *           the specified date
    * @return   the new date,as a String in yyyymmdd format
    */
    public static String newDate(String s, int n){
    	
    	if (s.length() != 8) {								// exceptional cases
    		return "Must enter a date in the format, yyyymmdd.";
    	}
    	
    	int day = Integer.parseInt(s.substring(6));			// breaking up the string into three integers, day, month, and year
    	int month = Integer.parseInt(s.substring(4, 6));
    	int year = Integer.parseInt(s.substring(0,4));
    	
    	if (month > 12) {									// exceptional cases
    		return "Invalid month.";
    	}
    	if (month == 2 && year%4 == 0 && day > 29){
    		return "Invalid date.";
    	}
    	if (month == 2 && year%4 != 0 && day > 28){
    		return "Invalid date.";
    	}
    	
    	String newDay = "";									// this variable will be needed for adjustments that will be made at the end
    	
    	if (n > 0){											// this is for going forward in time
	    	for (int i = 0; i < n; i ++){					// this loop will run for as many days is added into the parameter
	
	    		day ++;										// adding a day for each run of the loop
	
		    	if (month == 1 || month == 3 || month == 5 ||
		    		month == 7 || month == 8 || month == 10 || month == 12){ //for months with 31 days
		    		
		    		if (day > 31){							// resets the day and adds a month for the new month
		    			day = 1;
		    			month ++;
		    				
		    			if (month > 12) {					// for new years, reset the day and move the month to January
		    				day = 1;
		    				month = 1;
		    				year ++;
		    			}
		    		}
		    	}
		    	if (month == 2 || month == 4 || month == 6 ||
		    		month == 9 || month == 11) { // for months with less than 31 days
		    		if (month == 2 && year % 4 == 0) { // For February + Leap Year
		    			if (day > 29){					// on a leap year
		    				day = 1;					// reset the day after the 29th of February
		    				month ++;					// and move to March
		    			}
		    		}
		    		if (month == 2 && year % 4 != 0) { // Just for February
		    			if (day > 28) {					// on a nonleap year
		    				day = 1;					// reset the day after the 28th of February
		    				month ++;					// and move to March
		    			}
		    		}
		    		else { // For Months with 30 days
		    			if (day > 30) {					// on months with 30 days
		    				day = 1;					// reset the day to 1 after the 30th
		    				month ++;					// and add a month
		    			}
		    		}
		    	}
	    	}
    	}
    	
    	if (n < 0) {										// this is for going back in time
    		for (int i = 0; i < Math.abs(n); i ++){			// loop will run as many times as the absolute value of the integer indicates
    			
	    		day --;										// subtract the day each time the loop runs
	
		    	if (month == 1 || month == 3 || month == 5 ||
		    		month == 7 || month == 8 || month == 10 || month == 12){ //for months with 31 days
		    		
		    		if (day < 1){							// to go back a month
		    			day = 30;							// resets the day to 30 (for the months that end with 31 days)
		    			month --;							// goes to the previous month
		    			
		    			if (month < 1) {					// on new year's day
		    				day = 31;						// move to December 31
		    				month = 12;
		    				year --;						// take a year away
		    			}
		    		}
		    	}
		    	if (month == 2 || month == 4 || month == 6 ||
		    		month == 9 || month == 11) { // for months with less than 31 days
		    		if (month == 3 && year % 4 == 0) { // For February + Leap Year
		    			if (day < 1){					// on March of leap year
		    				day = 29;					// must move the day back to 29
		    				month --;					// and subtract a month
		    			}
		    		}
		    		if (month == 3 && year % 4 != 0) { // Just for February
		    			if (day < 1) {					// on March of a nonleap year
		    				day = 28;					// move the day back to 28
		    				month --;					// and subtract a month
		    			}
		    		}
		    		else { // For Months with 30 days
		    			if (day < 1) {					// on the months with 30 days
		    				day = 31;					// must move to a month with 31 days, so reset to 31
		    				month --;
		    			}
		    		}
		    	}
	    	}
    	}
    	if (year < 10){								// accounting for years with less than 4 digits
    		newDay = "000"+year;
    	}
    	else {
    		newDay = ""+year;
    	}
    	
    	if (year < 100){
    		newDay = "00" + year;
    	}
    	else {
    		newDay = "" + year;
    	}
    	
    	if (year < 1000){
    		newDay = "0" + year;
    	}
    	else {
    		newDay = "" + year;
    	}
    	
    	if (month < 10) { // accounting for single digit months
    		newDay += "0" + month;
    	}
    	else {
    		newDay += "" + month;
    	}
    	
    	if (day < 10) { // accounting for single digit days
    		newDay += "0" + day;
    	}
    	else {
    		newDay += day;
    	}
    	
    	

    	
    	return newDay;
    	
    }

   /**
    * Given two dates, computes the number of days in between them.
    *
    * @param start  the start date, in the format of yyyymmdd 
    * @param end    the end date
    * @return       the number of days between the two dates
    */
    public static int daysBetween(String start, String end){

    	if (start.length() != 8 || end.length() != 8) {								// exceptional cases
    		System.out.println("Error. Formats of input must be yyyymmdd (disregard following number)");
    	}
    	
    	int counter = 0; //will count the number of days between the dates
    	
    	if (Integer.parseInt(start) > Integer.parseInt(end)){
    		while (!newDate(start,counter).equals(end)){ // will run newDate method until the dates match 
    			counter --;
    		}
    	}
    	if (Integer.parseInt(start) < Integer.parseInt(end)){ // will run newDate method until the dates match 
    		while (!newDate(start,counter).equals(end)){
    			counter ++;
    		}
    	}
    	return counter;
    	
    }

    public static void main(String[] args){
    	if (args[0].equals("1")) {
    		System.out.println(getDay(args[1]));
    	}
    	if (args[0].equals("2")) {
    		System.out.println(newDate(args[1],Integer.parseInt(args[2])));
    	}
    	if (args[0].equals("3")) {
    		System.out.println(daysBetween(args[1],args[2]));
    	}

    }
    
}
