package uk.hel.statistic.test;

import org.junit.Before;
import org.junit.Test;

import uk.hel.statistic.activity.Activity;
import uk.hel.statistic.activity.BaseTextFileActivity;
import uk.hel.statistic.datastore.FileDataStore;

public class ActivityTests {

	private Activity activity;

	@Test(expected=IllegalArgumentException.class)
	public void activity_constructor_TC1() {
		activity.setDataStore(null);
	}
	
	@Before
	public void setUp() {
		String path = "src/test/resources/data/test.txt";
		FileDataStore dataStore = new FileDataStore(path);
		activity = new BaseTextFileActivity(dataStore);
	}
	
}
