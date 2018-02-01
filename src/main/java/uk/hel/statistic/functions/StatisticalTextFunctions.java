package uk.hel.statistic.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A {@link StatisticalFunctions} sub-interface for declaring statistical text
 * functions.
 * 
 * @author Harvey Lowndes
 *
 */
public interface StatisticalTextFunctions extends StatisticalFunctions {
	
	/**
	 * Calculates and returns the number of lines in the document.
	 * 
	 * @return the line count.
	 */
	public long getLineCount();
	
	/**
	 * Returns a count based on the {@link java.lang.String} array returned from
	 * {@link #extractAllWords}.
	 * 
	 * @return the word count.
	 */
	public default long getWordCount() {
		String[] words = extractAllWords();
		if(words != null) {
			return words.length;
		}
		throw new NullPointerException("extractAllWords() returned null.");
	}
	
	/**
	 * Calculates the average number of letters per word in a
	 * {@link java.lang.String} array deriving from the {@link #extractAllWords}
	 * function.
	 * 
	 * This function has a default procedure as it will work across all
	 * {@link java.lang.String} arrays. However this can be overridden should it
	 * need to be.
	 * 
	 * @throws NullPointerException if extractAllWords() returns null.
	 * @return the average number of letters per word.
	 */
	public default double getAverageNumberOfLettersPerWord() {
		String[] words = extractAllWords();
		if(words != null) {
			double wordLengthTotal = 0;
			for (int i = 0; i < words.length; i++) {
				wordLengthTotal += words[i].length();
			}
			// Result to one decimal place
			return (double) Math.round((wordLengthTotal / words.length) * 10d) / 10d;
		}
		throw new NullPointerException("extractAllWords() returned null.");
	}
	
	/**
	 * Returns a list of characters that occur most in the document.
	 * 
	 * @throws NullPointerException if extractAllWords() returns null.
	 * @return	the most occurring characters
	 */
	public default List<Character> getMostCommonLetter() {
		// Create a map of each character and the times they occur.
		String[] words = extractAllWords();
		if(words != null) {
			Map<Character, Long> map = Arrays
					.stream(words)
					.map(x -> x.toLowerCase())
					.collect(Collectors.joining())
					.chars()
					.mapToObj(c -> (char) c)
					.collect(
							Collectors.groupingBy(Function.identity(),
									Collectors.counting()));
	
			/*
			 * Loop through map and add all those with highest occurrence to a list.
			 * TODO Read more on Streams to find away to pipe this with the above.
			 */
			List<Character> mostOccuring = new ArrayList<Character>();
			Long highestOccurence = (long) 0;
			for (Map.Entry<Character, Long> entry : map.entrySet()) {
				if (entry.getValue() > highestOccurence) {
					highestOccurence = entry.getValue();
					mostOccuring.clear();
					mostOccuring.add(entry.getKey());
				} else if (entry.getValue() == highestOccurence) {
					mostOccuring.add(entry.getKey());
				}
			}
			return mostOccuring;
		}
		throw new NullPointerException("extractAllWords() returned null.");
	}
	
	/**
	 * Extracts all the words from the document. The functionality is defined by
	 * the {@link uk.hel.statistic.activity.Activity} class that wishes to
	 * inherit from this interface.
	 * 
	 * @return the extracted words.
	 */
	public String[] extractAllWords();
	
}
