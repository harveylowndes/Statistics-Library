package uk.hel.statistic.datastore;

import uk.hel.statistic.checksum.Checksum;

/**
 * This abstract data store is the root for all document data stores. A 
 * document is defined as a store of data held in an object, for example
 * this could be something as simple as a String, a File or a URL to even
 * slightly more complex documents such as NoSQL documents. This class
 * provides very basic default functionality, however it is recommended
 * that sub classes overwrite where possible to suit the needs of the
 * document in its validation.
 * 
 * @author Harvey Lowndes
 *
 * @param <E>
 *            the data object representation
 */
public abstract class AbstractDocumentDataStore<E> implements DataStore,
		Checksum {

	protected E document;
	private String checksum;

	/**
	 * Returns the document.
	 * 
	 * @return E the document.
	 */
	public E getDocument() {
		return this.document;
	}

	/**
	 * Sets the document.
	 * 
	 * @param path
	 * @throws IllegalArgumentException
	 *             if the document is null.
	 */
	/*
	 * protected void setDocument(E document) { if (document == null) { throw
	 * new IllegalArgumentException("Document cannot be null."); } this.document
	 * = document; }
	 */

	/**
	 * Sets the document by path. Functionality differs across object so has
	 * been left abstract.
	 * 
	 * @param path
	 *            the document path.
	 */
	protected abstract void setDocumentAndChecksum(String path);

	/**
	 * Sets the document and then regenerates the checksum.
	 * 
	 * @throws IllegalArgumentException
	 *             if the document is null.
	 * @param document
	 */
	protected void setDocumentAndChecksum(E document) {
		if (document == null) {
			throw new IllegalArgumentException(
					"The document cannot be set to null.");
		}
		this.document = document;
		setChecksum(generateChecksum());
	}

	/**
	 * Returns the checksum.
	 * 
	 * @return checksum the checksum.
	 */
	public String getChecksum() {
		return this.checksum;
	}

	/**
	 * Sets the checksum.
	 * 
	 * @throws IllegalArgumentException
	 *             if the checksum is empty.
	 * @param checksum
	 *            the new checksum.
	 */
	public void setChecksum(String checksum) {
		if (checksum == null || checksum.isEmpty()) {
			throw new IllegalArgumentException(
					"Checksum cannot be empty or null.");
		}
		this.checksum = checksum;
	}
}
