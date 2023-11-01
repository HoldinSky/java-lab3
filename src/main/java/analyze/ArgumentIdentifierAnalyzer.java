package analyze;

import misc.ArgumentType;
import misc.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;

import static misc.ArgumentType.*;

// Аналіз типів аргументів, що задаються під час запуску програми.
// Якщо аргумент є правильним ідентифікатором Java
// (шаблон: складається з латинських букв, цифр і символів “$” та “_”, що вважаються буквами, і, крім того, перший символ є літерою),
// То його тип “Identifier”, якщо аргумент є ключовим словом Java
// (для прикладу задати кілька ключових слів Java, “if”, “for”, “while”, “do” і “else”),
// Тойого тип “Keyword”, інакше його тип вважається “Illegal”.
// Програма виводить кількість заданих аргументів і для кожного аргументу його тип і значення.

public class ArgumentIdentifierAnalyzer {
	private static final Pattern IdentifierPattern = Pattern.compile("[A-Za-z]{1}[A-Za-z0-9$_]*");

	private final HashSet<String> KeywordsSet = new HashSet<>() {{
		add("if");
		add("else");
		add("for");
		add("while");
		add("do");
		add("private");
		add("public");
		add("protected");
		add("static");
		add("final");
		add("class");
		add("long");
		add("int");
		add("short");
		add("byte");
		add("double");
		add("float");
		add("return");
		add("new");
		add("enum");
		add("import");
		add("package");
	}};


	public Pair<Integer, ArrayList<Pair<ArgumentType, String>>> parseArgumentArray(String[] args) {
		var argCount = 0;

		var argList = new ArrayList<Pair<ArgumentType, String>>();
		for (var arg : args) {
			argCount++;

			var matcher = IdentifierPattern.matcher(arg);

			if (KeywordsSet.contains(arg))
				argList.add(new Pair<>(Keyword, arg));
			else if (matcher.matches())
				argList.add(new Pair<>(Identifier, arg));
			else
				argList.add(new Pair<>(Illegal, arg));
		}

		return new Pair<>(argCount, argList);
	}

	public static void main(String[] args) {
		var parser = new ArgumentIdentifierAnalyzer();

		var result = parser.parseArgumentArray(args);

		System.out.println("You have " + result.first() + " arguments total");

		result.second().forEach((pair) -> System.out.println("Argument " + pair.second() + " is of type '" + pair.first() + "'"));
	}
}


