package uk.hel.statistic.activity;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.stream.Stream;

import uk.hel.statistic.checksum.util.ChecksumUtil;
import uk.hel.statistic.datastore.FileDataStore;
import uk.hel.statistic.functions.StatisticalTextFunctions;

/**
 * An @{link Activity} sub-class for statistics against a
 * {@link uk.hel.statistic.datastore.FileDataStore}. The class uses the
 * {@link Activity} as its superclass, passing through
 * {@link uk.hel.statistic.datastore.FileDataStore} as the parameter.
 * {@link com.oracle.StatisticalTextFunctionsTests.function.StatisticalTextFunctions}
 * is used to provide functionality for text statistics and uses both default
 * functions within the interface.
 * 
 * The naming of this class isn't accidental. Other types of text files could
 * use this class without inhibiting the current functionality (e.g. for numeric
 * text files).
 * 
 * @author Harvey Lowndes
 *
 */
public class BaseTextFileActivity extends Activity<FileDataStore> implements
		StatisticalTextFunctions {

	/*
	 * Store values where we can.
	 */
	protected Long lineCount;
	protected String[] extractedWords;

	/**
	 * Constructor.
	 * 
	 * @param dataStore
	 *            The data store.
	 */
	public BaseTextFileActivity(FileDataStore dataStore) {
		super(dataStore);
	}

	/**
	 * Calculates and returns the line count. This function uses the checksum to
	 * determine whether a file has been modified. If there have been no
	 * modifications then the stored lineCount will be returned, otherwise it
	 * will be recalculated. Empty file lines are excluded.
	 * 
	 * @throws NullPointerException
	 *             if the line count cannot be calculated.
	 * @return the line count
	 */
	@Override
	public long getLineCount() {
		if (lineCount == null || checksumChanged()) {
			try (Stream<String> stream = Files.lines(getDataStore()
					.getDocument().toPath())) {
				long count = stream.filter(line -> !line.isEmpty()).count();
				this.lineCount = count;
				return count;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			return lineCount;
		}
		throw new NullPointerException(
				"Unexpected error. Could not get the line count.");
	}

	/**
	 * Extracts words from the file and splits them into an array of
	 * {@link java.lang.String}s. This function uses the checksum to determine
	 * whether a file has been modified. If there have been no modifications
	 * then the stored extractedWords will be returned, otherwise it will be
	 * re-extracted.
	 * 
	 * @throws NullPointerException
	 *             if the words could not be extracted.
	 * @return the extracted words.
	 */
	@Override
	public String[] extractAllWords() {
		if (extractedWords == null || checksumChanged()) {
			try (Stream<String> stream = Files.lines(getDataStore()
					.getDocument().toPath())) {
				extractedWords = stream
						.flatMap(line -> Arrays.stream(line.trim().split(" ")))
						.map(word -> word.replaceAll("[^a-zA-Z]", "").trim())
						.filter(word -> word.length() > 0)
						.toArray(String[]::new);
				return extractedWords;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			return this.extractedWords;
		}
		throw new NullPointerException("Could not extract words from document.");
	}

	/**
	 * Checks if the checksum has changes using
	 * {@link uk.hel.statistic.checksum.util.ChecksumUtil#compareAndSetChecksum}
	 * .
	 * 
	 * @return boolean
	 */
	protected boolean checksumChanged() {
		return !ChecksumUtil.compareAndSetChecksum(
				getDataStore().getChecksum(),
				getDataStore().generateChecksum(), getDataStore());
	}

	/**
	 * Returns whether extracted words is null for testing.
	 * 
	 * @return boolean
	 */
	public boolean isExtractedWordsNull() {
		return extractedWords == null;
	}
	
	/**
	 * Returns whether line count is null for testing.
	 * 
	 * @return boolean
	 */
	public boolean isLineCountNull() {
		return lineCount == null;
	}
}
