package bc.util;

import java.nio.ByteBuffer;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Block {
	public byte[] version = { 0x00, 0x00, 0x00, 0x01 };
	public byte[] prevHash = new byte[32];
	public byte[] merkleRoot = new byte[32];
	public byte[] reserved = new byte[32];
	public byte[] timestamp = new byte[8];
	public byte[] bits = { (byte) 0x04, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF };
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
	public boolean isHashValid() {
		byte[] calcHash = Util.hashBlock(this);

		return hash.equals(calcHash);
	}

	// ------------------------------------------------------------------------
	public byte[] getTarget() {

		short exp = (short) (bits[0] & 0xFF);
		byte[] mult = Arrays.copyOfRange(bits, 1, 4);
		byte[] target = new byte[32];

		System.arraycopy(mult, 0, target, exp, 3);

		return target;
	}

	// ------------------------------------------------------------------------
	public JsonObject json() {
		JsonObjectBuilder obj = Json.createObjectBuilder();

		obj.add("version", Util.bytesToString(version));
		obj.add("prevHash", Util.bytesToString(prevHash));
		obj.add("merkleRoot"	, Util.bytesToString(merkleRoot));
		obj.add("reserved", Util.bytesToString(reserved));
		obj.add("timestamp", Util.bytesToString(timestamp));
		obj.add("bits", Util.bytesToString(bits));
		obj.add("nounce", Util.bytesToString(nounce));
		obj.add("hash", Util.bytesToString(hash));
				

		
		return obj.build();
	}

}
