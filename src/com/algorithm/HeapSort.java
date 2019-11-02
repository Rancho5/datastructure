package com.algorithm;

import java.util.Arrays;

public class HeapSort {

    public static void heapSort(int[] arr){
        buildHeap(arr);
        for(int i = arr.length-1; i > 0; i--){
            exchange(arr, 0, i);
            adjustHeap(arr, 0, i);
        }
        System.out.println(Arrays.toString(arr));
    }

    //建立树
    private static void buildHeap(int[] arr) {
        int half = (arr.length-1)/2;
        for(int i = half; i >= 0 ; i--){
            adjustHeap(arr, i, arr.length);
        }
    }

    /**
     * 从某个节点开始调整大根堆的结构
     * @param arr 需要调整的数组
     * @param begin 开始节点
     * @param length 需要调整的数组长度
     */
    private static void adjustHeap(int[] arr, int begin, int length) {
        int left = begin*2+1;
        int right = begin*2+2;
        if(left>length-1 && right>length-1)
            return;
        int large = left;
        if(right<=length-1 && arr[right]>arr[large])  //选出左节点或右节点中较大的
            large = right;
        if(arr[large]>arr[begin]){
            exchange(arr, large, begin);
            adjustHeap(arr, large, length);
        }
    }

    private static void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {49,38,65,97,76,13,27,49,78,34,12,64,1};
        heapSort(array);
    }
}
