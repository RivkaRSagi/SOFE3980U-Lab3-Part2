package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	 * A constructor that generates a binary object.
	 *
	 * @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	 */
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}

		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}

		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}

		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);

		// Ensure empty strings are replaced with "0"
		if (this.number.isEmpty()) {
			this.number = "0";
		}
	}
	/**
	 * Return the binary value of the variable
	 *
	 * @return the binary value in a string format.
	 */
	public String getValue()
	{
		return this.number;
	}
	/**
	 * Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	 *
	 * @param num1 The first addend object
	 * @param num2 The second addend object
	 * @return A binary variable with a value of <i>num1+num2</i>.
	 */
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;

	}
	/**
	 * ORing two binary variables.
	 *
	 * @param num1 The first binary object
	 * @param num2 The second binary object
	 * @return A binary variable with a value of <i>num1 || num2</i>.
	 */
	public static Binary OR(Binary num1, Binary num2){
		// the index of the first digit of each number
		int ind1 = num1.number.length()-1;
		int ind2 = num2.number.length()-1;

		//initialize variables
		String num3=""; //the binary value of the logical OR operation
		char comp1, comp2; //char variables for comparisons

		while(ind1>=0 || ind2>=0) //loop until all digits are processed
		{
			comp1 = (ind1>=0)? num1.number.charAt(ind1): '0'; //if the end of the digits is reached, use a 0 for comparison
			comp2 = (ind2>=0)? num2.number.charAt(ind2): '0';
			// if at least one of the digits in the column are 1, then the digit in the result should be one
			if(comp1=='1' || comp2=='1') {
				num3 = "1" + num3;
			}else{
				num3 = "0" + num3;
			}

			ind1--; // decrement both index counters
			ind2--;

		}
		return new Binary(num3); // create a binary object with the calculated value
	}
	/**
	 * ANDing two binary variables.
	 *
	 * @param num1 The first binary object
	 * @param num2 The second binary object
	 * @return A binary variable with a value of <i>num1 AND num2</i>.
	 */
	public static Binary AND(Binary num1, Binary num2){
		// the index of the first digit of each number
		int ind1 = num1.number.length()-1;
		int ind2 = num2.number.length()-1;

		//initialize variables
		String num3=""; //the binary value of the logical AND operation
		char comp1, comp2; //char variables for comparisons

		while(ind1>=0 || ind2>=0) //loop until all digits are processed
		{
			comp1 = (ind1>=0)? num1.number.charAt(ind1): '0'; //if the end of the digits is reached, use a 0 for comparison
			comp2 = (ind2>=0)? num2.number.charAt(ind2): '0';
			// the result digit should only be 1 if the digits in both of the columns are 1
			if(comp1=='1' && comp2=='1') {
				num3 = "1" + num3;
			}else{
				num3 = "0" + num3;
			}

			ind1--; // decrement both index counters
			ind2--;
		}
		return new Binary(num3); // create a binary object with the calculated value
	}
	/**
	 * multiplying two binary variables.
	 *
	 * @param num1 The first binary object, representing the multiplicand
	 * @param num2 The second binary object, representing the multiplier
	 * @return A binary variable with a value of <i>num1*num2</i>.
	 */
	public static Binary multiply(Binary num1, Binary num2){
		//index of the first digit of the second number specifically
		int ind2 = num2.number.length()-1; // acts as the MULTIPLIER

		//initialize variables
		Binary num3=new Binary("0"); //initialize a binary variable to hold the answer, initialize to 0
		String temp=num1.getValue(); //hold the shifted binary values temporarily before adding them
		Binary tempB;

		//loop through the digits in the MULTIPLIER, shift each time, if there is a 1, add to the MULTIPLICAND
		while(ind2>=0){
			//check last digit of MULTIPLIER
			if(num2.number.charAt(ind2)=='1'){//if 1, add the current shifted value of the MULTIPLICAND
				tempB = new Binary(temp);
				num3 = add(num3, tempB);
			}

			//shift the MULTIPLICAND
			temp = temp + "0";

			//decrement the index
			ind2--;
		}
		return num3;
	}
}