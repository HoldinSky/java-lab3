package analyze;

import misc.ArgumentType;
import misc.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Аналіз типів аргументів, що задаються під час запуску програми.
// Якщо аргумент є числовим літералом, тобто починається з цифри, то визначається його тип (“Integer” або “Real”),
// Якщо аргумент укладений в поодинокі апострофи і містить один символ, то його тип — “Character”,
// Якщо аргумент укладений в подвійні апострофи, то його тип — “String”.
// Якщо жодна з умов не виконується, то тип аргументу — “Identifier”.
// Програма виводить кількість заданих аргументів; для кожного аргументу, його тип та значення.

public class ArgumentTypeAnalyzer {
	private static final HashMap<ArgumentType, Pattern> Patterns = new HashMap<>() {{
		put(ArgumentType.Character, Pattern.compile("'\\S{1}'"));
		put(ArgumentType.String, Pattern.compile("\"\\S*\""));
		put(ArgumentType.Number, Pattern.compile("(([+\\-])?([0-9]+)([.,][0-9]+)?)"));
	}};

	public Pair<Integer, ArrayList<Pair<ArgumentType, String>>> parseArgumentArray(String[] args) {
		var argCount = 0;
		var argList = new ArrayList<Pair<ArgumentType, String>>();

		Matcher matcher;
		for (var arg : args) {
			argCount++;

			var hasMatch = false;
			for (var argType : Patterns.keySet()) {
				matcher = Patterns.get(argType).matcher(arg);

				if (matcher.matches()) {
					if (argType == ArgumentType.Number)
						if (arg.contains(".") || arg.contains(","))
							argList.add(new Pair<>(ArgumentType.Real, arg));
						else
							argList.add(new Pair<>(ArgumentType.Integer, arg));
					else
						argList.add(new Pair<>(argType, arg));

					hasMatch = true;
					break;
				}
			}

			if (!hasMatch)
				argList.add(new Pair<>(ArgumentType.Identifier, arg));
		}

		return new Pair<>(argCount, argList);
	}

	public static void main(String[] args) {
		var parser = new ArgumentTypeAnalyzer();

		var result = parser.parseArgumentArray(args);

		System.out.println("You have " + result.first() + " arguments total");

		result.second().forEach((pair) -> System.out.println("Argument " + pair.second() + " - '" + pair.first() + "'"));
	}

}
