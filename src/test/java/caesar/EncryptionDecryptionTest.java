package caesar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EncryptionDecryptionTest {

	@Test
	public void TestEncryption() {
		var samples = new String[] { "Hello, World!", "This is tested case for encryption", "Hey there! How is it going?" };
		var encrypted = new ArrayList<String>();

		var cipher = new CaesarCipher(5);

		for (var str : samples) {
			encrypted.add(cipher.encrypt(str));
		}

		for (int i = 0; i < samples.length; i++) {
			var decrypted = cipher.decrypt(encrypted.get(i));

			assertEquals(samples[i], decrypted);
		}

		cipher = new CaesarCipher(6);
		assertEquals("ghijklmnopqrstuvwxyzabcdef", cipher.encrypt("abcdefghijklmnopqrstuvwxyz"));
		assertEquals("mnopqrstuvwxyzabcdefghijkl", cipher.encrypt("ghijklmnopqrstuvwxyzabcdef"));
	}
}
