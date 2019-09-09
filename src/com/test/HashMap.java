package com.test;

/*
学习HashMap和LinkedHashMap, （LinkedHashMap可以用于实现LruCache算法）
HashMap继承AbstractMap类，AbstractMap类实现了Map类
HashMap存的数据是键值对（key-value）,又称实体（entry）。key不可以相同，存入set。value可以相同，存入collection
HashMap的精髓--“散列链表”：每个key获取一个hash值，再通过与操作计算出一个index，对于index相同的key，存放在同一个链表中
LinkedHashMap的精髓--“双向循环链表”：可以根据插入顺序或访问顺序将数据排序，需要移除时先移除队首元素（最老的元素）
*/

public class HashMap {
}
