package uk.hel.statistic.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import uk.hel.statistic.activity.BaseTextFileActivity;
import uk.hel.statistic.datastore.FileDataStore;

public class StatisticalTextFunctionsTests {

	@Test(expected = NullPointerException.class)
	public void statisticalTextFunctions_getWordCount_TC32() {
		String path = "src/test/resources/data/testWordCount.txt";
		FileDataStore dataStore = new FileDataStore(path);
		BaseTextFileActivity activity = new BaseTextFileActivity(dataStore) {
			@Override
			public String[] extractAllWords() {
				return null;
			}
		};
		activity.getWordCount();
	}
	
	@Test
	public void statisticalTextFunctions_getWordCount_TC33() {
		String path = "src/test/resources/data/testWordCount.txt";
		FileDataStore dataStore = new FileDataStore(path);
		BaseTextFileActivity activity = new BaseTextFileActivity(dataStore);
		assertTrue(activity.getWordCount() == 18); // 20 words - 2 numbers
	}
	
	@Test(expected = NullPointerException.class)
	public void statisticalTextFunctions_getAvgNumLettersPerWord_TC34() {
		String path = "src/test/resources/data/testAverageNumberOfLettersPerWord.txt";
		FileDataStore dataStore = new FileDataStore(path);
		BaseTextFileActivity activity = new BaseTextFileActivity(dataStore) {
			@Override
			public String[] extractAllWords() {
				return null;
			}
		};
		activity.getAverageNumberOfLettersPerWord();
	}
	
	@Test
	public void statisticalTextFunctions_getAvgNumLettersPerWord_TC35() {
		String path = "src/test/resources/data/testAverageNumberOfLettersPerWord.txt";
		FileDataStore dataStore = new FileDataStore(path);
		BaseTextFileActivity activity = new BaseTextFileActivity(dataStore);
		assertTrue(activity.getAverageNumberOfLettersPerWord() == 3.9);
	}
	
	@Test(expected = NullPointerException.class)
	public void statisticalTextFunctions_getMostCommonLetters_TC36() {
		String path = "src/test/resources/data/testMostCommonLetters.txt";
		FileDataStore dataStore = new FileDataStore(path);
		BaseTextFileActivity activity = new BaseTextFileActivity(dataStore) {
			@Override
			public String[] extractAllWords() {
				return null;
			}
		};
		activity.getMostCommonLetter();
	}

	@Test
	public void statisticalTextFunctions_getMostCommonLetters_TC37() {
		String path = "src/test/resources/data/testMostCommonLetters.txt";
		FileDataStore dataStore = new FileDataStore(path);
		BaseTextFileActivity activity = new BaseTextFileActivity(dataStore);

		List<Character> mostCommonLetters = activity.getMostCommonLetter();

		boolean valid = true;
		if (mostCommonLetters.isEmpty()) {
			valid = false;
		} else {
			for (Character letter : activity.getMostCommonLetter()) {
				if (!letter.equals('j') && !letter.equals('h')) {
					valid = false;
					break;
				}
			}
		}
		assertTrue(valid);
	}
	
}
