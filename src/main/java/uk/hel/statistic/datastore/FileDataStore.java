package uk.hel.statistic.datastore;

import java.io.File;
import java.security.NoSuchAlgorithmException;

import uk.hel.statistic.checksum.util.ChecksumUtil;

/**
 * A data store for File documents.
 * 
 * @author Harvey Lowndes
 *
 */
public class FileDataStore extends AbstractDocumentDataStore<File> {

	/**
	 * Constructor.
	 * 
	 * @param document
	 *            the file.
	 */
	public FileDataStore(File file) {
		setDocumentAndChecksum(file);
	}

	/**
	 * Constructor.
	 * 
	 * @param document
	 *            the file path.
	 */
	public FileDataStore(String filePath) {
		setDocumentAndChecksum(filePath);
	}

	/**
	 * Generates and returns the checksum. This function uses the
	 * {@link com.oracle.statistic.checksum.util.ChecksumUtil#generateFileChecksum}
	 * , passing through the file object and "MD5" as the arguments.
	 * 
	 * @return the generated checksum
	 * @throws NullPointerException
	 *             if the checksum fails to generate.
	 */
	@Override
	public String generateChecksum() {
		// New file object to get up to date checksum.
		File file = new File(getDocument().getPath());
		try {
			return ChecksumUtil.generateFileChecksum(file,
					ChecksumUtil.DEFAULT_CHECKSUM_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		throw new NullPointerException("Could not generate checksum.");
	}

	/**
	 * Sets the file document and generates the checksum.
	 * 
	 * @param file
	 *            the file document to set.
	 * @throws IllegalArgumentException
	 *             if the file cannot be find.
	 */
	@Override
	public void setDocumentAndChecksum(File file) {
		if (file == null || !file.exists()) {
			throw new IllegalArgumentException("File not found.");
		}
		this.document = file;
		setChecksum(generateChecksum());
	}

	/**
	 * Sets the file document
	 * 
	 * @param path
	 *            the file path.
	 * @throws IllegalArgumentException
	 *             if the file cannot be find.
	 */
	@Override
	public void setDocumentAndChecksum(String path) {
		if (path == null || !new File(path).exists()) {
			throw new IllegalArgumentException("File not found.");
		}
		this.document = new File(path);
		setChecksum(generateChecksum());
	}

}
