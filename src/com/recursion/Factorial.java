package com.recursion;

//求阶乘
public class Factorial {
    public static int fac(int n){
        if(n == 0)
            return 1;
        return n*fac(n-1);
    }

    public static void main(String[] args) {
        System.out.println(fac(15));
    }
}
