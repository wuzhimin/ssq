package utils.cache;

public class MemcachedThread extends Thread {

	protected static MemCached memCached = MemCached.getInstance();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MemcachedThread mt = new MemcachedThread();
		boolean flag = true;
		while (flag) {
			mt.run();
		}
	}

	public static void addObject() {

		for (int i = 0; i < 1000; i++) {
			memCached.add("192.168.1.4_" + i, i);
			memCached.add("192.168.1.5_" + i, i);
		}

	}

	public static void getObject() {

		for (int i = 0; i < 1000; i++) {
			String key1 = "192.168.1.4_" + i;
			String key2 = "192.168.1.5_" + i;

			Object o1 = memCached.get(key1);
			if (o1 == null) {
				memCached.add(key1, i);
			}
			System.out.println(key1 + "  get value : " + memCached.get(key1));

			Object o2 = memCached.get(key2);
			if (o2 == null) {
				memCached.add(key2, i);
			}
			System.out.println(key2 + "  get value : " + memCached.get(key2));
		}

	}
}
