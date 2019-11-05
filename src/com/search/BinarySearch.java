package com.search;

import com.sort.BinaryInsertSort;

import java.util.Arrays;

public class BinarySearch {
    public static void binarySearch(int[] arr, int num){
        System.out.println("查找到元素的位置："+binarySearch(arr, num, 0, arr.length-1));
    }

    private static int binarySearch(int[] arr, int num, int begin, int end) {
        int mid = (begin + end) / 2;
        if(begin>end)
            return -1;
        if(arr[mid]==num){
            return mid;
        }else if(arr[mid]<num){
            return binarySearch(arr, num, mid+1, end);
        }else{
            return binarySearch(arr, num, 0, mid-1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {49,38,65,97,76,13,27,49,78,34,12,64,1};
        BinaryInsertSort.binaryInsertSort(arr);
        binarySearch(arr, 97);
    }
}
