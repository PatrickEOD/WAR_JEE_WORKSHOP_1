package pl.workshop.java;

import java.util.Scanner;

public class GameReversedGessAnumber {

	public static void main(String[] args) {
		int pickedNumber = pickAnumber();
		calculateNumberByComputer(pickedNumber);
	}

	private static void calculateNumberByComputer(int pickedNumber) {
		System.out.println("You've pointed your number within range of 0 - 1000: " + pickedNumber);
		System.out.println("Now I'll try to gess your number in max 10 steps!");
		System.out.println("Help me with answering \"higher\" or \"lower\"");
		Scanner answer = new Scanner(System.in);
		int min = 0;
		int max = 1000;
		int counter = 1;
		int guess = ((max - min) / 2) + min;
		while (guess != pickedNumber) {
			System.out.println("I'm gessing: " + guess + " Counter: " + counter + "\nHelp me:");
			String help = answer.next();
			if (help.equals("lower")) {
				System.out.println("Too high. Of course, don't worry I can do this");
				max = guess;
				counter++;
			} else if (help.equals("higher")) {
				System.out.println("Obviously it is too low, how I'm stupid");
				min = guess;
				counter++;
			}
			guess = ((max - min)/2) + min;
		}
		answer.close();
		System.out.println("I've guessed your number is: " + guess + " in " + counter + " steps.");
	}

	private static int pickAnumber() {
		System.out.println("Pick a number within a range of 0 - 1000");
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			String temp = scan.next();
			if (isDigitWithinRange(temp)) {
//				scan.close();
				return Integer.parseInt(temp);
			}
		}
//		scan.close();
		return 0;
	}

	private static boolean isDigitWithinRange(String number) {
		for (char c : number.toCharArray()) {
			if (!Character.isDigit(c)) {
				System.out.println("Wrong value, pick a number!");
				return false;
			}
		}
		int value = Integer.parseInt(number);
		if (value >= 0 && value <= 1000) {
			return true;
		}
		System.out.println("Finished!\nWrong value pick a number withing range of 0 - 1000!");
		return false;
	}
}
