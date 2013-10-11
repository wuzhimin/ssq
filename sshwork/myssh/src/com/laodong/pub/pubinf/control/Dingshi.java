package com.laodong.pub.pubinf.control;
/**
 *  定时业务方法接口
 *  任何定时的业务类都要实现此接口，然后调用需要的任何引擎addTask方法
 * @author lhh
 *
 */
public interface Dingshi {
    abstract void autolaodong(DingshiInfo dsinfo);
}
