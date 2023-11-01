package caesar;


// Створіть програму для шифрування\розшифровки тексту методом Цезаря.
// У ньому ключем є ціле число, а шифрування\розшифровка полягає в
// Додаванні\відніманні кодів символів відкритого тексту\криптотексту з ключем

import java.util.Scanner;

public class CaesarCipher {

	private static final String Alphabet = "abcdefghijklmnopqrstuvwxyz";
	private static final byte alphabetLength = (byte) Alphabet.length();

	private final int key;

	public CaesarCipher(int key) {
		this.key = key % alphabetLength;
	}

	public String encrypt(String inputText) {
		return shiftMessage(inputText, key);
	}

	public String decrypt(String inputText) {
		return shiftMessage(inputText, -key);
	}

	private String shiftMessage(String input, int shift) {
		var output = new StringBuilder();

		for (int i = 0; i < input.length(); i++) {
			var original = input.charAt(i);

			if (!Character.isAlphabetic(original)) {
				output.append(original);
				continue;
			}

			var shiftedIndex = getShiftedIndex(original, shift);
			var shifted = Alphabet.charAt(shiftedIndex);

			if (Character.isUpperCase(original))
				output.append(Character.toUpperCase(shifted));
			else
				output.append(shifted);
		}

		return output.toString();
	}

	private int getShiftedIndex(char original, int shift) {
		var firstlyIndex = Alphabet.indexOf(Character.toLowerCase(original)) + shift;

		if (firstlyIndex < 0)
			return alphabetLength + firstlyIndex;

		return firstlyIndex % alphabetLength;
	}

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);

		System.out.print("Enter message you want to cipher: ");
		var msg = scanner.nextLine();

		System.out.print("Enter the key to caesar cipher: ");
		var key = scanner.nextInt();

		var caesarCipher = new CaesarCipher(key);

		var encrypted = caesarCipher.encrypt(msg);
		System.out.println("Encrypted message: " + encrypted);

		System.out.println("Decrypted message: " + caesarCipher.decrypt(encrypted));
	}
}
