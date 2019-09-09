package com.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class Test{
    public static void main(String[] args){
        HashMap hm = new HashMap();
        hm.put(2, 12);
        hm.put(2, 33);
        hm.put('2', 34);
        System.out.println(hm.entrySet());

        hm.remove(2);
        System.out.println(hm);
    }
}

