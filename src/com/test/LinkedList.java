package com.test;

import java.util.Collection;

public class LinkedList<T> {

    //The member variable of class LinkedList ：size, voidLink, Link
    private int size = 0;
    private Link<T> voidLink;     //head pointer

    private static final class Link<T>{         //Link is node    问题：为什么用final
        T data;
        Link<T> previous, next;
        public Link(T data, Link<T> previous, Link<T> next){
            this.data = data;
            this.previous = previous;
            this.next = next;
        }
    }

    //Initialization of LinkedList
    public LinkedList(){
        voidLink = new Link<T>(null, null, null);
        voidLink.previous = voidLink;
        voidLink.next = voidLink;
    }

    //Todo:将collection变为链表
    public LinkedList(Collection<? extends T> collection){
        //Todo
    }

    //add, remove, set and contains
    public void add(T data){
        Link<T> link = new Link<>(data, voidLink.previous, voidLink);
        voidLink.previous.next = link;
        voidLink.previous = link;
        size++;
    }

    public void add(int location, T data){
        if(location >= 0 && location <= size){
            Link<T> link = voidLink;    //represent the current node
            //这个思想值得学习：location较小时从头开始迭代，较大时从尾开始迭代
            if(location < size/2){
                for(int i = 0; i < location; i++){
                    link = link.next;
                }
            }else{
                for(int i = size; i >= location; i--){
                    link = link.previous;
                }
            }
            Link<T> newLink = new Link<>(data, link, link.next);
            link.next.previous = newLink;
            link.next = newLink;
            size++;

        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    //print elements in LinkedList
    public void printData(){
        Link<T> link = voidLink.next;
        while (link != voidLink){
            System.out.print(link.data + " ");
            link = link.next;
        }
        System.out.println("");
    }

    public static void main(String[] args){
        LinkedList<String> ll = new LinkedList<>();
        ll.add("element1");
        ll.add("element2");
        ll.add("element3");
        ll.add("element4");
        ll.printData();

        ll.add(1,"newelement1");
        ll.add(4,"newelement2");
        ll.printData();
    }

}
