package com.algorithm;

import java.util.Arrays;

//直接插入排序：依次遍历，依次排序，遍历到后面的元素插入到前面排好的序列里
public class InsertSort {

    public static void insertSort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            int temp = arr[i];
            int j;
            for(j = i-1; j>=0&&arr[j]>temp; j--){
                arr[j+1] = arr[j];
            }
            arr[j+1] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args){
        int[] arr = {49,38,65,97,76,13,27,49,78,34,12,64,1};
        insertSort(arr);
    }

}
