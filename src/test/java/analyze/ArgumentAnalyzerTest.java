package analyze;

import misc.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static misc.ArgumentType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArgumentAnalyzerTest {

	@Test
	public void testArgumentIdentifier() {
		var argsString = "arg1 vAlId$ArGuMeNt another_valid $illegal still_good for public class MyClass";

		var testClass = new ArgumentIdentifierAnalyzer();

		var expect = new Pair<>(9, new ArrayList<>() {{
			add(new Pair<>(Identifier, "arg1"));
			add(new Pair<>(Identifier, "vAlId$ArGuMeNt"));
			add(new Pair<>(Identifier, "another_valid"));
			add(new Pair<>(Illegal, "$illegal"));
			add(new Pair<>(Identifier, "still_good"));
			add(new Pair<>(Keyword, "for"));
			add(new Pair<>(Keyword, "public"));
			add(new Pair<>(Keyword, "class"));
			add(new Pair<>(Identifier, "MyClass"));
		}});

		var actual = testClass.parseArgumentArray(argsString.split(" "));

		assertEquals(expect.first(), actual.first());

		var expectList = expect.second();
		var actualList = actual.second();

		for (int i = 0; i < expectList.toArray().length; i++) {
			assertEquals(expectList.get(i), actualList.get(i));
		}
	}

	@Test
	public void testArgumentType() {
		var argsString = "\"strArg\" 'c' '9' 95.2 1023 -215 just_identifier \"not_identifier_but_string\" '%'";

		var testClass = new ArgumentTypeAnalyzer();

		var expect = new Pair<>(9, new ArrayList<>() {{
			add(new Pair<>(String, "\"strArg\""));
			add(new Pair<>(Character, "'c'"));
			add(new Pair<>(Character, "'9'"));
			add(new Pair<>(Real, "95.2"));
			add(new Pair<>(Integer, "1023"));
			add(new Pair<>(Integer, "-215"));
			add(new Pair<>(Identifier, "just_identifier"));
			add(new Pair<>(String, "\"not_identifier_but_string\""));
			add(new Pair<>(Character, "'%'"));
		}});

		var actual = testClass.parseArgumentArray(argsString.split(" "));

		assertEquals(expect.first(), actual.first());

		var expectList = expect.second();
		var actualList = actual.second();

		for (int i = 0; i < expectList.toArray().length; i++) {
			assertEquals(expectList.get(i), actualList.get(i));
		}
	}
}
