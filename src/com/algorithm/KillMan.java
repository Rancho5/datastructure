package com.algorithm;

//约瑟芬杀人法
public class KillMan {

    class Person{
        int index;
        Person next;

        public Person(int index) {
            this.index = index;
        }
    }

    /**
     * 主要方法
     * @param totalNum 总人数
     * @param k 每第k个人，杀死
     */
    public void kill(int totalNum, int k){
        //构建totalNum个人组成的环
        Person no1 = new Person(1);
        Person pointer = no1;       //当前迭代到的人
        for(int i = 2; i <= totalNum; i++){
            pointer.next = new Person(i);
            pointer = pointer.next;
        }
        pointer.next = no1;

        //开始杀人
        pointer = no1;
        while (pointer.next!=pointer){
            for(int i = 1; i < k-1; i++){
                pointer = pointer.next;
            }
            System.out.println("杀死了："+pointer.next.index);
            pointer.next = pointer.next.next;
            pointer = pointer.next;

        }

        System.out.println("最后的幸运儿是："+pointer.index);
    }

    public static void main(String[] args) {
        KillMan km = new KillMan();
        km.kill(20, 5);
    }
}
