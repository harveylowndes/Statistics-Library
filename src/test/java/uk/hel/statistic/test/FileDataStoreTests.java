package uk.hel.statistic.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import uk.hel.statistic.datastore.FileDataStore;

public class FileDataStoreTests {

	@Test(expected = IllegalArgumentException.class)
	public void fileDataStore_constructor_TC20() {
		File file = null;
		FileDataStore dataStore = new FileDataStore(file);
	}

	@Test(expected = IllegalArgumentException.class)
	public void fileDataStore_constructor_TC21() {
		String path = "src/test/resources/data/c.txt";
		FileDataStore dataStore = new FileDataStore(path);
	}

	@Test
	public void fileDataStore_constructor_TC22() {
		File file = new File("src/test/resources/data/test.txt");
		FileDataStore dataStore = new FileDataStore(file);
		assertTrue(dataStore.getDocument() == file);
		assertTrue(dataStore.getChecksum() != null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void fileDataStore_constructor_TC23() {
		String path = null;
		FileDataStore dataStore = new FileDataStore(path);
	}

	@Test(expected = IllegalArgumentException.class)
	public void fileDataStore_constructor_TC24() {
		String path = "src/test/resources/data/c.txt";
		FileDataStore dataStore = new FileDataStore(path);
	}

	@Test
	public void fileDataStore_constructor_TC25() {
		String path = "src/test/resources/data/test.txt";
		FileDataStore dataStore = new FileDataStore(path);
		assertTrue(dataStore.getDocument().getPath().equals(path));
		assertTrue(dataStore.getChecksum() != null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void fileDataStore_setDocumentAndChecksum_TC26() {
		String path = "src/test/resources/data/c.txt";
		File file = new File(path);
		FileDataStore dataStore = new FileDataStore(path);
		dataStore.setDocumentAndChecksum(file);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void fileDataStore_setDocumentAndChecksum_TC27() {
		String path = "src/test/resources/data/c.txt";
		File file = new File(path);
		FileDataStore dataStore = new FileDataStore(path);
		dataStore.setDocumentAndChecksum(file);
	}
	
	@Test
	public void fileDataStore_setDocumentAndChecksum_TC28() {
		String path = "src/test/resources/data/test.txt";
		File file = new File(path);
		FileDataStore dataStore = new FileDataStore(path);
		String oldChecksum = dataStore.getChecksum();
		
		file = new File("src/test/resources/data/testChecksum.txt");
		dataStore.setDocumentAndChecksum(file);
		assertTrue(dataStore.getDocument().getPath().equals(file.getPath()));
		assertFalse(dataStore.getChecksum().equals(oldChecksum));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void fileDataStore_setDocumentAndChecksum_TC29() {
		String path = "src/test/resources/data/testChecksum.txt";
		FileDataStore dataStore = new FileDataStore(path);
		path = null;
		dataStore.setDocumentAndChecksum(path);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void fileDataStore_setDocumentAndChecksum_TC30() {
		String path = "src/test/resources/data/testChecksum.txt";
		FileDataStore dataStore = new FileDataStore(path);
		path = "src/test/resources/data/c.txt";
		dataStore.setDocumentAndChecksum(path);
	}
	
	@Test
	public void fileDataStore_setDocumentAndChecksum_TC31() {
		String path = "src/test/resources/data/test.txt";
		File file = new File(path);
		FileDataStore dataStore = new FileDataStore(path);
		String oldChecksum = dataStore.getChecksum();
		
		path = "src/test/resources/data/testChecksum.txt";
		dataStore.setDocumentAndChecksum(path);
		assertTrue(dataStore.getDocument().getPath().equals(path));
		assertFalse(dataStore.getChecksum().equals(oldChecksum));
	}
	
	
}
