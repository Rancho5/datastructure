package com.test;

import java.util.EmptyStackException;

public class Stack<E> {
    public Object[] elementData;    //用于保存栈中的数据
    public int elementCount;        //栈中实际的数据数量
    public int capacityIncrement;   //栈的增长系数

    public Stack(){
        this(10);
    }

    public Stack(int initialCapacity){
        this(initialCapacity, 0);
    }

    public Stack(int initialCapacity, int capacityIncrement){
        if(initialCapacity < 0)
            throw new IllegalArgumentException("IllegalArgumentException:" + initialCapacity);      //初始容量<0时抛出异常
        this.elementData = new Object[initialCapacity];
        this.capacityIncrement = capacityIncrement;
    }

    //入栈
    public E push(E item){
        //第一步，先确定栈空间是否足够
        int oldCapacity = elementData.length;
        if(oldCapacity < elementCount + 1){
            int newCapacity = (capacityIncrement > 0) ? oldCapacity+capacityIncrement : oldCapacity*2;      //考虑capacityIncrement是否＞0决定增加多少容量
            if(newCapacity < elementCount + 1)              //考虑oldCapacity＝0时，newCapacity=oldCapacity*2还是0
                newCapacity = elementCount + 1;
        }

        elementData[elementCount++] = item;                 //不要忘记elementCount+1
        return item;
    }

    public E pop(){
        if(elementCount == 0)
            throw new EmptyStackException();

        Object obj = elementData[elementCount - 1];
        elementData[elementCount-- - 1] = null;
        return (E)obj;                              //转型
    }

    //查询栈顶元素，不删除
    public E peak(){
        if(elementCount == 0)
            throw new EmptyStackException();

        Object obj = elementData[elementCount - 1];
        return (E)obj;
    }

    //查询某个元素在栈中的位置，由栈底向栈顶数
    public int search(Object obj){
        if(obj == null){
            for(int i = 0; i < elementCount; i++){
                if(elementData[i] == null)
                    return i;
            }
        }else{
            for(int i = 0; i < elementCount; i++){
                if(elementData[i].equals(obj)){
                    return i;
                }
            }
        }
        return -1;
    }


    public boolean isEmpty(){
        if(elementCount == 0)
            return true;
        return false;
    }

    public String toStr(){
        String str = "";
        for(int i = 0; i < elementCount; i++){
            str += elementData[i];
        }
        return str;
    }

    public void printStack(){
        System.out.println(this.toStr());
    }

    public static void main(String[] args){
        Stack<Integer> s = new Stack();
        for(int i = 0; i < 5; i++){
            s.push(i);
        }
        s.printStack();
        s.pop();
        s.pop();
        s.printStack();
        s.push(10);
        s.push(null);
        s.printStack();
        System.out.println(s.search(2));
        System.out.println(s.search(null));
        System.out.println(s.peak());
    }




}
