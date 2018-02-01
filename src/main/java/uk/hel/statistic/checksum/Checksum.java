package uk.hel.statistic.checksum;

/**
 * An interface to define a method for generating and setting a checksum. Any
 * {@link com.oracle.statistic.activity.Activity} that wishes to use checksum
 * should implement this interface.
 * 
 * @author Harvey Lowndes
 *
 */
public interface Checksum {

	/**
	 * Defines a function to generate a checksum. Implementation on how is
	 * defined by the {@link com.oracle.statistic.activity.Activity} that uses
	 * it.
	 * 
	 * @return the generated checksum.
	 */
	public String generateChecksum();

	/**
	 * An abstract function to enforce the implementing class to include a
	 * setChecksum.
	 * 
	 * @param checksum
	 *            the new checksum.
	 */
	public void setChecksum(String checksum);

}
