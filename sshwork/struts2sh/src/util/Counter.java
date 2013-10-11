package util;

/**
 * 计数器
 * @author BeanSoft
 *
 */
public class Counter {
	private long start = 0L;// 从一个配置文件加载初始值, 销毁时把值存入文件
	
	private static Counter instance;
	
	// 构造器是private
	private Counter() {
		
	}
	
	// 暴露一个获取实例的静态方法
	public static synchronized Counter getInstance() {
		if(instance == null) {
			instance = new Counter();
		}
		
		return instance;
	}
	
	public synchronized long nextValue() {
		return ++start;
	}
	
	public static void main(String[] args) {
		Counter a = new Counter();
		Counter b = new Counter();
		a.start = b.start;
	}
	
}