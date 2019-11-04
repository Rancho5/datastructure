package com.algorithm;

import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] arr){
        mergeSort(arr, 0, arr.length-1);
    }

    private static void mergeSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int mid = (begin + end) / 2;
            mergeSort(arr, begin, mid);
            mergeSort(arr, mid+1, end);
            merge(arr, begin, mid,  end);
        }
    }

    /**
     * 合并一个数组中的两个有序序列
     *
     */
    private static void merge(int[] arr, int begin, int mid, int end) {
        int[] temp = new int[end-begin+1];
        int index = 0;
        int leftFlag = begin;
        int rightFlag = mid+1;
        while (leftFlag<=mid && rightFlag<=end){
            if(arr[leftFlag]<arr[rightFlag]){
                temp[index++] = arr[leftFlag++];
            }else {
                temp[index++] = arr[rightFlag++];
            }
        }

        for(;leftFlag<=mid;leftFlag++){
            temp[index++] = arr[leftFlag];
        }
        for(;rightFlag<=end;rightFlag++){
            temp[index++] = arr[rightFlag];
        }

        for(int i = begin;i<=end;i++){
            arr[i] = temp[i-begin];
        }
    }


    public static void main(String[] args) {
        int[] array = {49,38,65,97,76,13,27,49,78,34,12,64,1};
        mergeSort(array);
        System.out.println(Arrays.toString(array));
    }
}
