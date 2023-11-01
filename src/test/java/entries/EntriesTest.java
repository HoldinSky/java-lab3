package entries;

import misc.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EntriesTest {

	@Test
	public void TestCountingEntries() {
		var samples = new ArrayList<Pair<String, Pair<Character, Integer>>>();
		fillSamples(samples);

		var counter = new EntryCounter();

		for (var samp : samples) {
			var count = counter.countFromString(samp.first(), samp.second().first());

			assertEquals(samp.second().second(), count);
		}

	}

	private void fillSamples(ArrayList<Pair<String, Pair<Character, Integer>>> list) {
		list.add(new Pair<>("Testing string just for testing purposes", new Pair<>('s', 6)));
		list.add(new Pair<>("Check this string for number of t's", new Pair<>('t', 3)));


		String str = "And now it's time to count characters in really long string. How about giving it even more sentences. Now it seems to be enough";
		list.add(new Pair<>(str, new Pair<>('e', 13)));
		list.add(new Pair<>(str, new Pair<>('o', 10)));
		list.add(new Pair<>(str, new Pair<>('n', 12)));
	}
}
