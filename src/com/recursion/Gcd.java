package com.recursion;

//欧几里得算法求最大公约数
public class Gcd {
    public static int gcd(int n, int m){
        if(m == 0){
            return n;
        }else{
            return gcd(m, n%m);
        }
    }

    public static void main(String[] args) {
        System.out.println(gcd(33,55));
    }
}
