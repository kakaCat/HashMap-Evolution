package com.diy.hashmap.impl;

import com.diy.hashmap.DiyMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAVA on 2018/1/18.
 */
public class DIYHashMap<K, V> implements DiyMap<K, V> {

    //定义默认数组大小
    private  int defaultLenth=16;
    //负载因子，扩容标准    useSize/数组长度>0.75扩容
    private double defaultAddSizeFactor=0.75;
    //使用数组位置的总数
    private double useSize;
    //定义Map 骨架之一数组
    private Entry<K, V>[] table;


    public DIYHashMap(int defaultLenth, double defaultAddSizeFactor) {
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
    public DIYHashMap(){
        this.defaultLenth = defaultLenth;
    }


    @Override
    public V put(K k, V v) {
        if(useSize>defaultAddSizeFactor*defaultLenth){
            //扩容
            up2Size();
        }
        //通过key来计算出 存储的位置
        int index=getIndex(k,table.length);

        Entry<K, V> entry=table[index];
        Entry<K, V> newEntry=new Entry<K, V>(k, v, null);
        if(entry==null){
            table[index]=newEntry;
            useSize++;
        }else{//维护数组相同位置队列
            Entry<K, V> tmp;
            while((tmp=table[index])!=null){
                tmp=tmp.next;
            }
            tmp.next=newEntry;
        }
        return newEntry.getValue();
    }


    private int getIndex(K k, int length) {
        //通常hashCode 取膜法
        int m=length-1;
        int index=hash(k.hashCode()) & m;
        return index >= 0 ? index : -index;
    }

    //创建自己的hash算法，保证计算出的位置 在数组中均匀分布
    private int hash(int hashCode) {
        hashCode=hashCode^((hashCode>>>20)^(hashCode>>>12));
        return hashCode^((hashCode>>>7)^(hashCode>>>4));
    }


    //扩容数组
    private void up2Size() {
        Entry<K, V>[] newTable=new Entry[defaultLenth*2];
        //将原table中的entry重新，散列到新的table中
        againHash(newTable);
    }

    //将原table中的entry重新，散列到新的table中
    private void againHash(Entry<K, V>[] newTable) {
        //数组里面对象 封装到list中,包括同一位置 有列表结构的都解析出来
        List<Entry<K,V>> entryList=new ArrayList<Entry<K,V>>();
        for(int i=0;i<table.length;i++){
            if(table[i]==null){
                continue;
            }
            findEntryByNext(table[i],entryList);
        }
        if(entryList.size()>0){
            useSize=0;
            defaultLenth=defaultLenth*2;
            table=newTable;
            for (Entry<K, V> entry : entryList) {
                if(entry.next!=null){
                    entry.next=null;
                }
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    private void findEntryByNext(Entry<K, V> entry, List<Entry<K, V>> entryList) {
        if(entry!=null && entry.next!=null){
            //这个entry对象已经形成链表结构
            entryList.add(entry);
            //递归 将链表中的entry实体 都一次封装到entryList链表中
            findEntryByNext(entry.next, entryList);
        }else{
            entryList.add(entry);
        }
    }


    @Override
    public V get(K k) {
        //通过key来计算出 存储的位置
        int index=getIndex(k,table.length);

        Entry<K, V> entry=table[index];

        if(entry==null){
            throw new NullPointerException();
        }

        return findValueByKey(k,entry);



    }


    private V findValueByKey(K k, Entry<K, V> entry) {

        if(k == entry.getKey() || k.equals(entry.getKey())){
            return entry.v;
        }else if(entry.next!=null){
            return findValueByKey(k,entry.next);
        }
        return null;
    }


    class Entry<K, V> implements DiyMap.Entry<K, V>{

        K k;
        V v;
        //指向被this挤压下去的entry
        Entry<K, V> next;

        public Entry(K k, V v, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

    }
}
