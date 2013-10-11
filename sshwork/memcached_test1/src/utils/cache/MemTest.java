package utils.cache;

public class MemTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args[0].equals("add")) {
			MemcachedThread.addObject();
		} else if(args[0].equals("get")) {
			MemcachedThread.getObject();
		}

	}

}
