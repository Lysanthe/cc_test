package bc.util;

import bc.util.mine.SHA256Miner;

public class Start {

	public static void main(String[] args) {	
				Block block = new Block();		
		
		SHA256Miner miner = new SHA256Miner(block);
		System.out.println("Target: " + block.getTarget().toString());
		System.out.println("Target: " + Util.bytesToString(block.getTarget()));
		miner.start();	
				
		Util.hashBlock(block);
		System.out.println(Util.bytesToString(block.GetHash()));
		System.out.format("Number of retries: %,d",miner.getNumberOfPasses());
		System.out.println();
		System.out.println(Util.bytesToString(block.GetHeader()));
		//String.format("%064x", new java.math.BigInteger(1, digest));
	}

}
