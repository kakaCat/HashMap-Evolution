package com.diy.HashMap.impl;

import com.diy.HashMap.Map;

/**
 * Created by JAVA on 2018/3/11.
 */
public class DiyHashMap<K,V> implements Map<K,V>{

    /**
     * 定义默认数组大小
     */
    private int defaultLenth = 1<<4;

    /**
     * 负载因子，扩容标准    size/数组长度>0.75扩容
     */
    static final float defaultAddSizeFactor =0.75f;

    /**
     * 使用数组位置的总数
     */
    private int size;

    /**
     * 定义Map 骨架之一数组
     */
    private Entry<K, V>[] table;

    public DiyHashMap(int defaultLenth){

        this(defaultLenth,defaultAddSizeFactor);
    }


    public DiyHashMap(int defaultLenth, float defaultAddSizeFactor) {
        if(defaultLenth<0){
            throw new IllegalArgumentException("数组长度为负数"+defaultLenth);
        }
        if(defaultAddSizeFactor<=0 || Double.isNaN(defaultAddSizeFactor)){
            throw new IllegalArgumentException("扩容标准必须大于0的数字"+defaultLenth);
        }

        this.defaultLenth = defaultLenth;
        this.defaultAddSizeFactor = defaultAddSizeFactor;

        table=new Entry[defaultLenth];
    }

    @Override
    public Object put(Object o, Object o2) {
        return null;
    }

    @Override
    public Object get(Object o) {
        return null;
    }
}
