package com.sort;

import java.util.Arrays;

public class QuickSort {
    public static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }

    private static void quickSort(int[] arr, int begin, int end) {
        if(begin<end){
            int index = getIndex(arr, begin, end);
            quickSort(arr, 0, index-1);
            quickSort(arr, index+1, end);
        }
    }

    /**
     * 快排的核心算法，选取基准数据并将基准数据放在正确的位置，并使得数组左边的数都小于基准数据，右边的数都大于基准数据
     * @param arr 待排序的数组
     * @param begin 数组待排序的起始位置
     * @param end 数组待排序的终止位置
     * @return 基准数据的正确位置
     */
    private static int getIndex(int[] arr, int begin, int end) {
        int temp = arr[begin];      //选取基准数据
        int leftFlag = begin;
        int rightFlag = end;
        while (leftFlag<rightFlag){
            while (leftFlag<rightFlag && arr[rightFlag]>=temp){     //此处注意应该先从rightFlag开始，同时判断语句也需要加上leftFlag<rightFlag
                rightFlag--;
            }
            arr[leftFlag] = arr[rightFlag];
            while (leftFlag<rightFlag && arr[leftFlag]<=temp){
                leftFlag++;
            }
            arr[rightFlag] = arr[leftFlag];

        }
        arr[leftFlag] = temp;
        return leftFlag;
    }

    public static void main(String[] args) {
        int[] arr = {49,38,65,97,76,13,27,49,78,34,12,64,1};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
