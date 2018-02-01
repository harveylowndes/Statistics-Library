package uk.hel.statistic.datastore;

/**
 * The DataStore interface is the root interface of all DataStores. DataStores
 * are classes that works with object representations of data both custom,
 * external (e.g. databases) and those in the Java library such as Files and
 * URLs. DataStores must inherit the {@link DataStore} interface. A data store
 * acts as an intermediary between such objects and the rest of the library and
 * should be used to perform checks such as validation and/or checksums.
 * 
 * @author Harvey Lowndes
 *
 */
public interface DataStore {

}
