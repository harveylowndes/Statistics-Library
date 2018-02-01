package uk.hel.statistic.checksum.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import uk.hel.statistic.checksum.Checksum;

/**
 * A utility class for all common checksum functionalities.
 * 
 * @author Harvey Lowndes
 *
 */
public class ChecksumUtil {

	public static final String DEFAULT_CHECKSUM_ALGORITHM = "MD5";

	/**
	 * The ChecksumUtil constructor has been made private to ensure
	 * non-instantiability.
	 */
	private ChecksumUtil() {

	}

	/**
	 * Generates the checksum of a given file based on a given algorithm. The
	 * given algorithm must be compatible with
	 * {@link java.security.MessageDigest} (e.g. MD5, SH1, SH2..).
	 * 
	 * Checksum logic from {@link https
	 * ://examples.javacodegeeks.com/core-java/security
	 * /messagedigest/generate-a-file-checksum-value-in-java/}
	 * 
	 * @param file
	 *            File to use checksum against
	 * @param algorithm
	 *            Algorithm to generate checksum
	 * @return the generated checksum.
	 */
	public static final String generateFileChecksum(File file, String algorithm)
			throws NoSuchAlgorithmException {
		boolean algorithmException = false;

		if (file != null) {
			try {
				MessageDigest messageDigest = null;
				messageDigest = MessageDigest.getInstance(algorithm);

				FileInputStream fileInput = new FileInputStream(file.getPath());

				byte[] dataBytes = new byte[1024];
				int bytesRead = 0;

				while ((bytesRead = fileInput.read(dataBytes)) != -1) {
					messageDigest.update(dataBytes, 0, bytesRead);
				}

				byte[] digestBytes = messageDigest.digest();

				StringBuffer sb = new StringBuffer("");

				for (int i = 0; i < digestBytes.length; i++) {
					sb.append(Integer.toString((digestBytes[i] & 0xff) + 0x100,
							16).substring(1));
				}

				fileInput.close();
				return sb.toString();
			} catch (NoSuchAlgorithmException e) {
				algorithmException = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!algorithmException)
			throw new IllegalArgumentException("File cannot be null.");
		else
			throw new IllegalArgumentException("Algorithm is not valid.");
	}

	/**
	 * Compares the current and compare checksum {@link java.lang.String}s and
	 * returns a boolean value. No matter the result, the checksum will be set
	 * to the compare argument value.
	 * 
	 * @param current
	 *            the current checksum.
	 * @param compare
	 *            the new/comparison checksum.
	 * @param checksum
	 *            the implementing class.
	 * @throws IllegalArgumentException
	 *             if checksum is null.
	 * @return boolean
	 */
	public static final boolean compareAndSetChecksum(String current,
			String compare, Checksum checksum) {
		if (checksum != null && current != null && compare != null) {
			checksum.setChecksum(compare);
			return current.equals(compare);
		} else {
			throw new IllegalArgumentException(
					"Cannot pass null to parameters.");
		}
	}

}
