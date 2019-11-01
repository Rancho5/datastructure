package com.algorithm;

import java.util.Arrays;

public class ShellSort {
    public static void shellSort(int[] arr){
        int length = arr.length;
        int increment = length/2;   //增量
        int temp;
        for(;increment>=1;increment/=2){
            for(int i = 0; i < increment; i++){
                for(int j = i+increment; j<length; j+=increment){
                    //下面是直接插入排序
                    temp = arr[j];
                    int k;
                    for(k = j-increment; k>=0 && temp<arr[k]; k-=increment){
                        arr[k+increment] = arr[k];
                    }
                    arr[k+increment]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

    }

    public static void main(String[] args) {
        int[] arr = {49,38,65,97,76,13,27,49,78,34,12,64,1};
        shellSort(arr);
    }
}
