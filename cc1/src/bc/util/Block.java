package bc.util;

import java.nio.ByteBuffer;
import java.util.BitSet;

public class Block {
	public byte[] version = { 0x00, 0x00, 0x00, 0x01 };
	public byte[] prevHash = new byte[32];
	public byte[] merkleRoot = new byte[32];
	public byte[] reserved = new byte[32];
	public byte[] timestamp = new byte[8];
	public byte[] bits = { 0x00, 0x00, 0x00, 0xFF & 4 };
	public byte[] nounce = new byte[32];
	public byte[] hash = new byte[32];

	// ------------------------------------------------------------------------
	public Block() {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.putLong(0, System.currentTimeMillis());
		timestamp = buffer.array();
	}

	// ------------------------------------------------------------------------
	byte[] GetHeader() {
		byte[] block = new byte[144];
		int index = 0;

		System.arraycopy(version, 0, block, index, version.length);
		index += version.length;
		System.arraycopy(prevHash, 0, block, index, prevHash.length);
		index += prevHash.length;
		System.arraycopy(merkleRoot, 0, block, index, merkleRoot.length);
		index += merkleRoot.length;
		System.arraycopy(reserved, 0, block, index, reserved.length);
		index += reserved.length;
		System.arraycopy(timestamp, 0, block, index, timestamp.length);
		index += timestamp.length;
		System.arraycopy(bits, 0, block, index, bits.length);
		index += bits.length;
		System.arraycopy(nounce, 0, block, index, nounce.length);

		return block;
	}

	// ------------------------------------------------------------------------
	public byte[] GetHash() {
		return hash;
	}

	// ------------------------------------------------------------------------
	public BitSet getBitDifficulty() {
		BitSet bs = new BitSet(hash.length * 8);
		int diff = (bits[3] & 0xFF);
		for (int index = 0; index < diff; index++) {
			bs.set(index);
		}
		byte[] bytes = bs.toByteArray();
		System.out.println(Util.bytesToString(bs.toByteArray()));
		return bs;
	}

}
