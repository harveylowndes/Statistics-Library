package uk.hel.statistic.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import uk.hel.statistic.activity.BaseTextFileActivity;
import uk.hel.statistic.checksum.util.ChecksumUtil;
import uk.hel.statistic.datastore.FileDataStore;

public class ChecksumUtilTests {

	@Test(expected = IllegalArgumentException.class)
	public void checksumUtil_generateFileChecksum_TC7()
			throws NoSuchAlgorithmException {
		File file = null;
		ChecksumUtil.generateFileChecksum(file, "MD5");
	}

	@Test(expected = IllegalArgumentException.class)
	public void checksumUtil_generateFileChecksum_TC8()
			throws NoSuchAlgorithmException {
		File file = new File("src/test/resources/data/test.txt");
		ChecksumUtil.generateFileChecksum(file, "MDF");
	}

	@Test
	public void checksumUtil_generateFileChecksum_TC9()
			throws NoSuchAlgorithmException {
		File file = new File("src/test/resources/data/test.txt");
		ChecksumUtil.generateFileChecksum(file, "MD5");
	}

	@Test(expected = IllegalArgumentException.class)
	public void checksumUtil_compareAndSetChecksum_TC10() {
		String path = "src/test/resources/data/testChecksum.txt";
		FileDataStore dataStore = new FileDataStore(path);
		BaseTextFileActivity activity = new BaseTextFileActivity(dataStore);

		ChecksumUtil.compareAndSetChecksum(null, activity.getDataStore()
				.getChecksum(), activity.getDataStore());
	}

	@Test(expected = IllegalArgumentException.class)
	public void checksumUtil_compareAndSetChecksum_TC11() {
		String path = "src/test/resources/data/testChecksum.txt";
		FileDataStore dataStore = new FileDataStore(path);
		BaseTextFileActivity activity = new BaseTextFileActivity(dataStore);

		ChecksumUtil.compareAndSetChecksum(activity.getDataStore()
				.getChecksum(), null, activity.getDataStore());
	}

	@Test(expected = IllegalArgumentException.class)
	public void checksumUtil_compareAndSetChecksum_TC12() {
		String path = "src/test/resources/data/testChecksum.txt";
		FileDataStore dataStore = new FileDataStore(path);
		BaseTextFileActivity activity = new BaseTextFileActivity(dataStore);

		ChecksumUtil.compareAndSetChecksum(activity.getDataStore()
				.getChecksum(), activity.getDataStore().getChecksum(), null);
	}

	@Test
	public void checksumUtil_compareAndSetChecksum_TC13() {
		String path = "src/test/resources/data/testChecksum.txt";
		FileDataStore dataStore = new FileDataStore(path);
		BaseTextFileActivity activity = new BaseTextFileActivity(dataStore);

		String str = "New file text";
		writeToTestDocument(str, path);

		String newChecksum = activity.getDataStore().generateChecksum();

		assertFalse(ChecksumUtil.compareAndSetChecksum(activity.getDataStore()
				.getChecksum(), newChecksum, activity.getDataStore()));

		assertTrue(activity.getDataStore().getChecksum().equals(newChecksum));

		// Reset
		str = "Original file text";
		writeToTestDocument(str, path);
	}

	@Test
	public void checksumUtil_compareAndSetChecksum_TC14() {
		String path = "src/test/resources/data/testChecksum.txt";
		FileDataStore dataStore = new FileDataStore(path);
		BaseTextFileActivity activity = new BaseTextFileActivity(dataStore);

		assertTrue(ChecksumUtil.compareAndSetChecksum(activity.getDataStore()
				.getChecksum(), activity.getDataStore().getChecksum(), activity
				.getDataStore()));
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
