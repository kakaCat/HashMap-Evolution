package com.diy.hashmap;

/**
 * Created by JAVA on 2018/1/18.
 */
public interface DiyMap<K,V> {

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
