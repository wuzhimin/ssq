package com.laodong.test.conn;
/**
 * 类名称
 * 业务描述
 *
 * 2007-1-2713:42:20
 */
public class Haihua {
    // The next serial number to be assigned
    private static int nextSerialNum = 0;

    private static ThreadLocal serialNum = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new Integer(nextSerialNum++);
        }
    };

    public static int get() {
        return ((Integer) (serialNum.get())).intValue();
    }

}
