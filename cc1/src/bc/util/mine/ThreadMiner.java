package bc.util.mine;

import java.util.HashMap;
import java.util.Map;

import bc.util.Block;

public class ThreadMiner {
	
	private final short numberOfThreads = 2;
	private HashMap<SHA256Miner, Thread> threadMap = new HashMap<SHA256Miner, Thread>();
	private long startTime = 0;
	private long previousTime = 0;
	private long previousPasses = 0;
	
	private Block block = null;
	
	public ThreadMiner(Block block)
	{
		this.block = block;		
	}	
	
	public void start()
	{
		for(int index = 0; index< numberOfThreads; index++)
		{
			SHA256Miner miner = new SHA256Miner(block);
			Thread thread = new Thread(miner);
			thread.start();			
			threadMap.put(miner,thread);
		}
		startTime = System.currentTimeMillis();
		previousTime = startTime;
	}
	
	public long getHashRate()
	{
		long totalPasses = 0;
		long currentTime = 0;
		long hashRate = 0;
		
		for(Map.Entry<SHA256Miner, Thread> kvp : threadMap.entrySet())
		{
			totalPasses += kvp.getKey().getNumberOfPasses();
		}
		currentTime = System.currentTimeMillis();
		
		hashRate = (totalPasses - previousPasses) / (currentTime - previousTime) * 1000;
		
		previousTime = System.currentTimeMillis();
		previousPasses = totalPasses;
		
		return hashRate;
	}
	
	public void writeStatus()
	{
		boolean running = true;
		long totalPasses = 0;
		
		while(running)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.format("HashRate: %,d H/s \n", getHashRate());
			totalPasses = 0;
			for(Map.Entry<SHA256Miner, Thread> kvp : threadMap.entrySet())
			{
				totalPasses += kvp.getKey().getNumberOfPasses();
				if (!kvp.getValue().isAlive())
					running = false;
			}
		}
		for(Map.Entry<SHA256Miner, Thread> kvp : threadMap.entrySet())
		{
			kvp.getKey().running = false;
		}
		System.out.format("Total Hashes: %,d H", totalPasses);
	}
	

}
