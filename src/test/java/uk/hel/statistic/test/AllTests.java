package uk.hel.statistic.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AbstractDocumentDataStoreTests.class, ActivityTests.class,
		BaseTextFileActivityTests.class, ChecksumUtilTests.class,
		FileDataStoreTests.class, StatisticalTextFunctionsTests.class })
public class AllTests {

}
