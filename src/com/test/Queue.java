package com.test;

//链式队列	在LinkedList基础上实现
public class Queue<T> {
    LinkedList<T> qList = new LinkedList<>();

    public void add(T obj){
        qList.add(obj);
    }

    public void poll(){
        qList.remove(0);
    }

    public int contains(Object obj){
        return qList.contains(obj);
    }

    public void printData(){
        qList.printData();
    }

    public static void main(String[] args){
        Queue<Integer> q = new Queue<>();
        for(int i = 0; i < 5; i++)
            q.add(i);
        q.printData();
        q.poll();
        q.printData();
        System.out.println(q.contains(3));
        q.add(null);
        q.printData();
    }

}
