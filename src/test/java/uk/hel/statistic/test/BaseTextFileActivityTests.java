package uk.hel.statistic.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import uk.hel.statistic.activity.BaseTextFileActivity;
import uk.hel.statistic.datastore.FileDataStore;

public class BaseTextFileActivityTests {

	private static final String newLine = System.getProperty("line.separator");

	@Test
	public void baseTextFileActivity_getLineCount_TC2() {
		String path = "src/test/resources/data/testLineCount.txt";
		FileDataStore dataStore = new FileDataStore(path);
		BaseTextFileActivity activity = new BaseTextFileActivity(dataStore);
		assertTrue(activity.getLineCount() == 6); // 10 lines w/ empty lines
	}

	@Test
	public void baseTextFileActivity_getLineCount_TC3() {
		String path = "src/test/resources/data/testLineCount.txt";
		FileDataStore dataStore = new FileDataStore(path);
		BaseTextFileActivity activity = new BaseTextFileActivity(dataStore);
		String content = "This is a single line" + newLine + "This is another line.";
		writeToTestDocument(content, path);
		assertTrue(activity.getLineCount() == 2);
		//Revert back
		content = "This is a" + newLine + "Test" + newLine + newLine + "For" + newLine + newLine + "A" + newLine + newLine + "File that has" + newLine + newLine + "10 lines - 4 are empty.";
		writeToTestDocument(content, path);
	}

	@Test
	public void baseTextFileActivity_getLineCount_TC4() {
		String path = "src/test/resources/data/testLineCount.txt";
		FileDataStore dataStore = new FileDataStore(path);
		BaseTextFileActivity activity = new BaseTextFileActivity(dataStore);
		activity.getLineCount();
		assertTrue(activity.getLineCount() == 6);
	}
	
	@Test
	public void baseTextFileActivity_extractAllWords_TC5() {
		String path = "src/test/resources/data/test.txt";
		FileDataStore dataStore = new FileDataStore(path);
		BaseTextFileActivity activity = new BaseTextFileActivity(dataStore);
		assertTrue(activity.isExtractedWordsNull());
		activity.extractAllWords();
		assertFalse(activity.isExtractedWordsNull());
	}
	
	@Test
	public void baseTextFileActivity_extractAllWords_TC6() {
		String path = "src/test/resources/data/test.txt";
		FileDataStore dataStore = new FileDataStore(path);
		BaseTextFileActivity activity = new BaseTextFileActivity(dataStore);
		activity.extractAllWords();
		assertFalse(activity.isExtractedWordsNull());
		String content = "New file content";
		writeToTestDocument(content, path);
		assertTrue(activity.extractAllWords()[0].equalsIgnoreCase("New"));
		//Revert back
		content = "Hi" + newLine + "my" + newLine + "name" + newLine + "is harvey";
		writeToTestDocument(content, path);
	}
	
	public void writeToTestDocument(String contents, String path) {
		String str = contents;
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(path));
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
