package bc.util.mine;

import java.util.BitSet;
import java.util.Random;

import bc.util.Block;
import bc.util.Util;

public class SHA256Miner {

	private Block block = null;
	private byte[] target = null;
	private long passes = 0;

	public SHA256Miner(Block block) {
		this.block = block;
		this.target = block.getTarget();
	}

	public void start() {
		boolean nounceFound = false;
		byte[] hash = null;

		while (nounceFound != true) {
			setNewNounce();
			hash = Util.hashBlock(block);
			nounceFound = validHash(hash);
			passes++;
		}
		block.hash = hash;
	}

	public long getNumberOfPasses() {
		return passes;
	}

	private boolean validHash(byte[] hash) {
		for (int index = 0; index < hash.length; index++) {
			short h = (short) (hash[index] & 0xFF);
			short t = (short) (target[index] & 0xFF);
			if (h < t)
				return true;
			if (h > t)
				return false;
		}
		return true;
	}

	private void setNewNounce() {
		new Random().nextBytes(block.nounce);
	}

}
