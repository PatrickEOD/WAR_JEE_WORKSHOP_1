package pl.workshop.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameLotto {

	public static void main(String[] args) {
		System.out.println("Computer is drowing numbers...");
		ArrayList<Integer> drawList = (ArrayList<Integer>) drawNumbers();
//		System.out.println(drawList);
		ArrayList<Integer> pickList = (ArrayList<Integer>) pickNumbers();
		Iterator<Integer> itrDraw = drawList.iterator();
		Iterator<Integer> itrPick = pickList.iterator();
		// System.out.println(drawList.size());
		System.out.println("Computer choice:");
		System.out.println(drawList);
		System.out.println("Your choice:");
		System.out.println(pickList);
		int counter = 0;
		for(int i = 1; i <= pickList.size(); i++) {
			if(drawList.contains(pickList.get(i-1))) {
				counter++;
			}
		}
		System.out.println("You've luckily got " + counter);
		if(counter >=4) {
			System.out.println("Congratulations");
		}

	}

	private static List<Integer> drawNumbers() {
		Random rand = new Random();
		List<Integer> drowList = new ArrayList<>();
		int counter = 1;
		while (counter <= 6) {
			int temp = rand.nextInt(48) + 1;
			if (!listHasThisNumber(drowList, temp)) {
				drowList.add(temp);
				counter++;
			}
		}
		Collections.sort(drowList);
		return drowList;
	}

	private static List<Integer> pickNumbers() {
		System.out.println("Choose 6 numbers from within 1 to 49");
		List<Integer> pickList = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		System.out.println("Choose " + (pickList.size() + 1) + " number:");
		while (pickList.size() < 6 && scan.hasNext()) {
			String temp = scan.next();
			if (isDigit(temp)) {
				int number = Integer.parseInt(temp);
				if (!listHasThisNumber(pickList, number)) {
					pickList.add(number);
					System.out.println("Choose " + (pickList.size() + 1) + " number:");
				}
			} else {
				System.out.println("Wrong value picked. Choose an integer from within 1 to 49!");
			}
		}
		Collections.sort(pickList);
		return pickList;
	}

	private static boolean listHasThisNumber(List<Integer> list, int number) {
		if (number > 49 || number <= 0) {
			System.out.println("Number is not in range of 1-49!");
			return true;
		}
		if (list.size() == 0) {
			return false;
		}
		for (int i = 1; i <= list.size(); i++) {
			if (list.contains(number)) {
				System.out.println("You've already picked this number!");
				return true;
			}
		}
		return false;
	}

	private static boolean isDigit(String number) {
		for (char c : number.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
}
