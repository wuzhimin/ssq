package com.ttserver.client.test;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**
 * 本类用的是java_memcached-release-2.5.1.jar包
 *      下载地址： http://github.com/gwhalin/Memcached-Java-Client/downloads
 * 
 * @author Administrator
 * 
 */
public class MemcachedTest {
    String[] servers = { "192.168.1.4:11211" };

    Integer[] weights = { 3 };
    MemCachedClient mcc = new MemCachedClient();
    // 创建一个实例对象SockIOPool
    SockIOPool pool = SockIOPool.getInstance();

    public MemcachedTest() {
        pool.setServers(servers);
        pool.setWeights(weights);

        pool.setInitConn(5);
        pool.setMinConn(5);
        pool.setMaxConn(250);
        pool.setMaxIdle(30);

        pool.setMaintSleep(30);
        pool.initialize();
    }

    public void putObject() {
        for (int i = 1; i < 10; i++) {
            Boolean b = mcc.add("key" + i, "hi, michael" + i); // 如果以前存在，则不去更新
            System.out.println("key" + i + b);
        }
    }

    public void replaceObject() {
        Boolean b = mcc.replace("key2", "hi, tom"); // 如果key不存在，则返回flase，更新失败
        System.out.println("update " + b);
    }

    public void getObject() {
        Object obj = mcc.get("key2");
        System.out.println(obj);
    }

    
    public static void main(String args[]) {
        MemcachedTest tc = new MemcachedTest();
         tc.putObject();
        // tc.replaceObject();
         tc.getObject();

        tc.pool.shutDown();

    }
}