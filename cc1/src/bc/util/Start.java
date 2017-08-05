package bc.util;

public class Start {

	public static void main(String[] args) {

		Block block = new Block();

		System.out.println(Util.BytesToString(block.GetHeader()));
		
		byte[] hash = Util.SignBlock(block);
		System.out.println(Util.BytesToString(hash));
		
		//String.format("%064x", new java.math.BigInteger(1, digest));
	}

}
