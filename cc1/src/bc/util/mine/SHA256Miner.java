package bc.util.mine;

import java.util.BitSet;
import java.util.Random;

import bc.util.Block;
import bc.util.Util;

public class SHA256Miner {
	
	private Block block = null;
	private BitSet bitDiff = null;
	private long passes = 0;
	
	public SHA256Miner(Block block)
	{
		this.block = block;
		this.bitDiff = block.getBitDifficulty();
	}
	
	public void start()
	{
		boolean nounceFound = false;
		
		while(nounceFound != true)
		{
			setNewNounce();
			Util.hashBlock(block);
			nounceFound = validHash();	
			passes++;
		}
	}
	
	public long getNumberOfPasses()
	{
		return passes;
	}
	
	private boolean validHash()
	{
		BitSet bs = BitSet.valueOf(block.hash);
		BitSet save = (BitSet) bs.clone();
		bs.and(bitDiff);
		return bs.isEmpty();
	}
	
	private void setNewNounce()
	{		
		new Random().nextBytes(block.nounce);
	}

}
