package bc;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import bc.util.Util;

public class TestSHA {

	public static void main(String[] args) {

		byte[] input = new byte[144];
		byte[] hash = new byte[32];
		long passes = 0;
		long previousTime = System.currentTimeMillis();

		//new Random().nextBytes(input);

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		while (true) {
			// new Random().nextBytes(input);
			md.update(hash);
			hash = md.digest();
			passes++;
			if (passes % 1000000 == 0) {
				long timePassed = System.currentTimeMillis() - previousTime;
				long hashRate = passes / timePassed * 1000;
				System.out.format("HashRate: %,d H/s  HASH: %s \n", hashRate, Util.bytesToString(hash));
				passes = 0;
				previousTime = System.currentTimeMillis();
			}
		}

		// System.out.println(Util.bytesToString(hash));
	}

}
