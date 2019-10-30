package com.algorithm;

import java.util.Arrays;

//二分法插入排序:直接插入排序的改进
public class BinaryInsertSort {

    public static void binaryInsertSort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            int left = 0;
            int right = i-1;
            int temp = arr[i];
            int index;
            while(left<right){
                int mid = (left+right)/2;
                if(arr[mid]>temp){
                    right = mid-1;
                }else{
                    left = mid+1;
                }
            }
            if(arr[left]<temp){
                index = left+1;
            }else{
                index = left;
            }
            for(int j=i-1; j>=index; j--){
                arr[j+1] = arr[j];
            }
            arr[index] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int[] arr = {49,38,65,97,176,213,227,49,78,34,12,164,11,18,1};
        binaryInsertSort(arr);
    }

}
