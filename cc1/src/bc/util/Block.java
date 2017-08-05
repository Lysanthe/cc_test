package bc.util;

import java.nio.ByteBuffer;

public class Block {
	public byte[] version = {0x00, 0x00, 0x00, 0x01};
	public byte[] prevHash = new byte[32];
	public byte[] merkleRoot = new byte[32];
	public byte[] reserved = new byte[32];
	public byte[] timestamp = new byte[8];
	public byte[] bits = new byte[4];
	public byte[] nounce = new byte[32];
	public byte[] hash = new byte[32];
 	
	
	// ------------------------------------------------------------------------
	public Block()
	{
	    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);    
		buffer.putLong(0, System.currentTimeMillis());        
		timestamp =  buffer.array();
	}

	// ------------------------------------------------------------------------
	byte[] GetHeader()
	{
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
	byte[] GetHash()
	{
		byte[] a = {0x00};
		return a;
	}
}
