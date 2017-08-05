package bc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

	public static String BytesToString(byte[] input) {
		StringBuilder sb = new StringBuilder();
		for (byte b : input) {
			sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}

	public static byte[] SignBlock(Block block) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

		md.update(block.GetHeader()); 
		byte[] digest = md.digest();

		return digest;
	}

}
