package com.datastructure;

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

    //TODO:将collection变为链表
    public LinkedList(Collection<? extends T> collection){
        //TODO
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

    public void remove(int location){
        if(location >= 0 && location < size){
            Link<T> link = voidLink;
            if(location < size/2){
                for(int i = 0; i <= location; i++){
                    link = link.next;
                }
            }else{
                for(int i = size; i > location; i--){
                    link = link.previous;
                }
            }
            link.previous.next = link.next;
            link.next.previous = link.previous;
            size--;
        }else{
            throw new IndexOutOfBoundsException();
        }

    }

    //return the changed data
    public T set(int location, T data){
        if(location >= 0 && location < size){
            Link<T> link = voidLink;
            if(location < size/2){
                for(int i = 0; i <= location; i++){
                    link = link.next;
                }
            }else{
                for(int i = size; i > location; i--){
                    link = link.previous;
                }
            }
            T temp = link.data;
            link.data = data;
            return temp;
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    public int contains(Object data){           //此处传入object类型而不是泛型T，可能是因为考虑到数据可能为null
        Link link = voidLink.next;
        if(data != null){                       //Don't forget to consider null !!!
            for(int i = 0; i < size; i++){
                if(link.data.equals(data))
                    return i;
                link = link.next;
            }
        }else{
            int location = 0;
            while(link != voidLink){
                if(link.data == null){
                    return location;
                }
                location += 1;
                link = link.next;
            }
        }
        return -1;
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
        ll.add("element5");
        ll.add("element6");
        ll.add("element7");
        ll.add("element8");
        System.out.print("LinkedList: ");
        ll.printData();

        ll.add(0,"insertElement1");
        ll.add(9,"insertElement2");
        System.out.print("datastructure add: ");
        ll.printData();

        ll.remove(9);
        ll.remove(0);
        System.out.print("datastructure remove: ");
        ll.printData();

        ll.set(2, "setElement1");
        System.out.print("datastructure set: ");
        ll.printData();

        System.out.println(ll.contains("element1"));
        System.out.println(ll.contains("element8"));
        System.out.println(ll.contains("element9"));
    }

}
