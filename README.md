# HashMap-Evolution
## hashmap的解析
###<font face="黑体" color=red>hashmap是什么</font>
---
###[百度百科的解释](https://baike.baidu.com/item/Hashmap/1167707?fr=aladdin)
---
#简单的理解hashmap
>#hashmap简单的说是一个装着链表的<font face="黑体" color=red>固定长度的数组</font>
	transient Node<K,V>[] table;
>>#<font face="黑体" color=red>而且这个数组长度一定的2的幂方</font>
>>#默认数组长度为16(是线程安全的)
	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
	默认数组长度这个变量是安全的

>#hashmap也有这自己的长度
>>
	 public int size() {
        return size;
    }
>>
>#<font face="黑体" color=red>如果数据长度固定,如何存储远大于数组的长度的数据那?</font>
>>#hashmap是一个可变的数组,如果存储的数据大于已创建的数组
>>#hashmap会再次创建一个数组,代替旧的数组
>
>>
>
>#<font face="黑体" color=red>什么时候才增加数组的长度那?</font>
>>#hashmap中有一个加载因子为0.75(线程是安全的)
	static final float DEFAULT_LOAD_FACTOR = 0.75f;
>>






#hashmap是怎么创建的

>Map<String,Object> map =new HashMap();
>
>>
	 public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }
>>
	public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }
>>	
	static final int MAXIMUM_CAPACITY = 1 << 30;	
>>
	hashmap最大可以存储的容量
>>
	public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) 判断数组长度
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
>>
        if (initialCapacity > MAXIMUM_CAPACITY) 判断是否超过最大容量
            initialCapacity = MAXIMUM_CAPACITY;
>>
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) 判断加载因子大于0  判断是否为无穷数
            throw new IllegalArgumentException("Illegal load factor: " +
                                               loadFactor);
>>
        this.loadFactor = loadFactor; 设置hashmap的加载因子
        this.threshold = tableSizeFor(initialCapacity);设置
    }
>>
>>


>DiyMap<String,Object> map1 =new DIYHashMap<>(); 自己写的hashmap
>>
	
>>