package pl.workshop.java;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameRollOfADice {

	private static Pattern ROLLANDDICE = Pattern.compile("([1-9]?\\d*)D([3468]|([1][02])|(20)|(100))(\\+|\\-)([1-9]*)");

	public static void main(String[] args) {

		System.out.println(rollTheDice());
	}

	private static int rollTheDice() {
		System.out.println("Roll the dice...");
		Scanner scan = new Scanner(System.in);
		System.out.println(
				"Insert data about dice with format xDyvz where, x = number of dices, y = kind of a dice, v = - or + operator, z - value to add or substract.");
		String dice = scan.next();
		while (!ROLLANDDICE.matcher(dice).matches()) {
			System.out.println("Wrong format, correct the dice type and throws");
			dice = scan.next();
		}
		scan.close();
		System.out.println("Data of the dice correct");
		Matcher matcher = ROLLANDDICE.matcher(dice);
		int result = 0;
		if (matcher.matches()) {
			int quantifier = Integer.parseInt(matcher.group(1));
			int diceType = Integer.parseInt(matcher.group(2));
			String operator = matcher.group(6);
			int value = Integer.parseInt(matcher.group(7));
			Random rand = new Random();
			for (int i = 1; i <= quantifier; i++) {
				int temp = rand.nextInt(diceType - 1) +1; //for double-check
				result += temp; // for double-check
				System.out.println("Dice step "+i+ ": " + result + " temp is: " + temp);
			}
			if (operator.equals("-")) {
				result -= value;
			} else if (operator.equals("+")) {
				result += value;
			}
		}
		return result;
	}
}
