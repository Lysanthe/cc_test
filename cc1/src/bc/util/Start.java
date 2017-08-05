package bc.util;

public class Start {

	public static void main(String[] args) {

		Block block = new Block();

		System.out.print(Util.BytesToString(block.GetHeader()));

	}

}
