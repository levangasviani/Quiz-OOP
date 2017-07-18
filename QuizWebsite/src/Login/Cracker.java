package Login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Cracker {

	/* static variables for this class */
	public static MessageDigest md;
	private static final Random RANDOMSALT = new SecureRandom();

	/**
	 * returns a random salt
	 * 
	 * @return - array of bytes
	 */
	public static byte[] getSalt() {
		byte[] salt = new byte[32];
		RANDOMSALT.nextBytes(salt);
		return salt;
	}

	/**
	 * returns a string value for the given array
	 * 
	 * @param bytes
	 *            - salt array
	 * @return bytes transformed to string
	 */
	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;
			if (val < 16)
				buff.append('0');
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}

	/**
	 * hashes given string with a given salt array
	 * 
	 * @param hash
	 *            - string to hash
	 * @param salt
	 *            - salt array
	 * @return
	 */
	public static String StringToHash(String hash, byte[] salt) {
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(salt);
			byte[] bytes = md.digest(hash.getBytes());
			return hexToString(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * returns array of bytes for the given string
	 * 
	 * @param hex
	 *            - string which should be transformed into an array
	 * @return - array of bytes
	 */
	public static byte[] hexToArray(String hex) {
		byte[] result = new byte[hex.length() / 2];
		for (int i = 0; i < hex.length(); i += 2) {
			result[i / 2] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
		}
		return result;
	}
}
