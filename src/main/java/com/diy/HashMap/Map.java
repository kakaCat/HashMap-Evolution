package com.diy.HashMap;

/**
 * Created by JAVA on 2018/3/11.
 */
public interface Map<K,V> {

    /**
     * Map双列集合 基本功能是 添加
     */
    V put(K k,V v);
    /**
     * Map双列集合 基本功能是 快速取
     */
    V get(K k);

    /**
     * 定义一个内部接口
     */
    interface Entry<K,V>{
        K getKey();

        V getValue();
    }

}
