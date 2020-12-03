package com.implemica.parentheses;

import java.util.Scanner;

public class CorrectParenthesisSequences {

	public static void main(String[] args) {
		System.out.println(CorrectParenthesisSequences.countOfSequences());
	}
	//method use Catalan number formula
	private static long countOfSequences() {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.close();
		long[] countOfSequences = new long[n + 1];
		countOfSequences[0] = 1;
		for (int i = 0; i < n; i++) {
			countOfSequences[i + 1] = countOfSequences[i] * 2 * (2 * i + 1) / (i + 2);
		}
		return countOfSequences[n];
	}

}
