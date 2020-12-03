package com.implemica.digits;

import java.math.BigInteger;

public class DigitsAdder {

	public static void main(String[] args) {
		System.out.println(DigitsAdder.sumOfDigits(DigitsAdder.factorial(100)));
	}

	private static BigInteger factorial(int n) {
		if (n <= 1) {
			return BigInteger.valueOf(1);
		} else {
			return BigInteger.valueOf(n).multiply(factorial(n - 1));
		}

	}

	// for each step method recursively return the sum of last digits by dividing
	// the number by 10, and truncates a number by dividing modulo 10
	private static BigInteger sumOfDigits(BigInteger input) {

		if (input != BigInteger.ZERO) {
			return sumOfDigits(input.divide(BigInteger.valueOf(10))).add(input.mod(BigInteger.valueOf(10)));
		}
		return input;
	}

}
