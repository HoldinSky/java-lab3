package entries;

// Створіть програму для визначення кількості входжень зазначеного символу в заданому тексті.
// Робота програми припиняється після введення символу “-”.

import java.util.Scanner;

public class EntryCounter {

	public static void main(String[] args) {
		var counter = new EntryCounter();
		var count = counter.countFromInput();

		System.out.println("Given character occurs " + count + " times in your input");
	}

	public int countFromInput() {
		var scanner = new Scanner(System.in);

		var str = new StringBuilder();

		System.out.print("Input the string against which to count characters: ");
		while (true) {
			var token = scanner.next();
			if (token.equals("-"))
				break;

			str.append(token);
		}

		System.out.print("Input the character to count in the string: ");
		var targetChar = scanner.next();

		scanner.close();
		return countFromString(str.toString().trim(), targetChar.charAt(0));
	}

	public int countFromString(String input, char targetChar) {
		int count = 0;

		targetChar = Character.toLowerCase(targetChar);
		for (int i = 0; i < input.length(); i++) {
			if (Character.toLowerCase(input.charAt(i)) == targetChar) {
				count++;
			}
		}

		return count;
	}
}
