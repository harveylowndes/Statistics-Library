package uk.hel.statistic.test;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import uk.hel.statistic.datastore.AbstractDocumentDataStore;

public class AbstractDocumentDataStoreTests {

	@Test(expected = IllegalArgumentException.class)
	public void abstractDocumentDataStoreTests_setDocumentAndChecksum_TC15() {
		TestDocumentDataStore testStore = new TestDocumentDataStore(null);
	}

	@Test
	public void abstractDocumentDataStoreTests_setDocumentAndChecksum_TC16()
			throws MalformedURLException {
		URL url = new URL("http://www.mywebsite.com");
		TestDocumentDataStore testStore = new TestDocumentDataStore(url);
		assertTrue(testStore.getDocument() == url);
	}

	@Test(expected=IllegalArgumentException.class)
	public void abstractDocumentDataStoreTests_setDocumentAndChecksum_TC17()
			throws MalformedURLException {
		URL url = new URL("http://www.mywebsite.com");
		TestDocumentDataStore testStore = new TestDocumentDataStore(url);
		testStore.setChecksum("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void abstractDocumentDataStoreTests_setDocumentAndChecksum_TC18()
			throws MalformedURLException {
		URL url = new URL("http://www.mywebsite.com");
		TestDocumentDataStore testStore = new TestDocumentDataStore(url);
		String checksum = null;
		testStore.setChecksum(checksum);
	}
	
	@Test
	public void abstractDocumentDataStoreTests_setDocumentAndChecksum_TC19()
			throws MalformedURLException {
		URL url = new URL("http://www.mywebsite.com");
		TestDocumentDataStore testStore = new TestDocumentDataStore(url);
		String checksum = testStore.generateChecksum();
		testStore.setChecksum(checksum);
		assertTrue(testStore.generateChecksum().equals(checksum)); 
	}
	
	class TestDocumentDataStore extends AbstractDocumentDataStore<URL> {

		public TestDocumentDataStore(URL url) {
			setDocumentAndChecksum(url);
		}

		@Override
		public String generateChecksum() {
			return "1234";
		}

		@Override
		protected void setDocumentAndChecksum(String path) {
			try {
				this.document = new URL(path);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
