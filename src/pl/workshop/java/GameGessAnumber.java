package pl.workshop.java;

import java.util.Random;
import java.util.Scanner;

public class GameGessAnumber {

	public static void main(String[] args) {

		int gessThisNumber = choosenNumber();
		compareNumbers(gessThisNumber);
		// System.out.println(gessThisNumber);
	}

	private static int choosenNumber() {
		Random rand = new Random();
		int number = rand.nextInt(99) + 1;
		return number;
	}

	private static void compareNumbers(int gessNumber) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Pick an integer value");
		while (scan.hasNext()) {
			String temp = scan.next();
			if(isInteger(temp)) {
				int number = Integer.parseInt(temp);
				if (number == gessNumber) {
					System.out.println("You've got it");
					scan.close();
					break;
				} else if (number > gessNumber) {
					System.out.println("Too high");
				} else if (number < gessNumber) {
					System.out.println("Too low");
				}
			} else {
				System.out.println("Wrong value, pick an integer!");
			}
		}
	}

	private static boolean isInteger(String number) {
		for(char c : number.toCharArray()) {
			if(!Character.isDigit(c)) {
//				System.out.println("Wrong value, pick an integer!");
				return false;
			}
		}
		return true;
	}
}
