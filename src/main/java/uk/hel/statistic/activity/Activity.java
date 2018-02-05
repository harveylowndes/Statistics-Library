package uk.hel.statistic.activity;

import uk.hel.statistic.datastore.DataStore;

/**
 * This abstract class is the root of the statistical activity hierarchy. It exists
 * to define a relationship between different statistical activities and to hold
 * common functionality across activities. A statistical activity represents a
 * group of methods and functions that can be used against a defined
 * {@link uk.hel.statistic.datastore} to calculate some output. Activities
 * inherit from zero to many
 * {@link uk.hel.statistic.functions.StatisticalFunctions} and provide their own
 * implementation.
 * 
 * @author Harvey Lowndes
 * @param <E>
 *            the DataStore
 */
public abstract class Activity<E extends DataStore> {

	protected E dataStore;

	/**
	 * Constructor.
	 * 
	 * @param dataStore
	 *            the data store.
	 */
	public Activity(E dataStore) {
		setDataStore(dataStore);
	}

	/**
	 * Returns the data store.
	 * 
	 * @return the data store.
	 */
	public E getDataStore() {
		return dataStore;
	}

	/**
	 * Sets the data store.
	 * 
	 * @param dataStore
	 *            the new data store.
	 * @throws IllegalArgumentException
	 *             if the dataStore is null.
	 */
	public void setDataStore(E dataStore) {
		if (dataStore == null) {
			throw new IllegalArgumentException("DataStore cannot be null.");
		}
		this.dataStore = dataStore;
	}
	

}
