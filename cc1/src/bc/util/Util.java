package bc.util;

public class Util {

	public static String BytesToString(byte[] input) {
		StringBuilder sb = new StringBuilder();
		for (byte b : input) {
			sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}
}
