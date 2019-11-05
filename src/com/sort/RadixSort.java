package com.sort;

import java.util.ArrayList;
import java.util.Arrays;

//基数排序
public class RadixSort {
    public static void radixSort(int[] arr){
        int max = arr[0];   //用来存放arr中的最大值，以便知道最大值有多少位
        for(int i = 1; i < arr.length; i++){
            if(max<arr[i])
                max = arr[i];
        }
        int times = 0;      //根据最大值位数决定需要循环几次
        while (max>0){
            max /= 10;
            times++;
        }
        for(int t = 0; t < times; t++){
            //初始化temp
            ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
            for(int i = 0; i < 10; i++){
                ArrayList<Integer> tt = new ArrayList<>();
                temp.add(tt);
            }
            for(int i = 0; i < arr.length; i++){
                int index = ((arr[i] - arr[i]%(int)Math.pow(10, t)) / (int)Math.pow(10,t)) % 10;      //求个位、十位、百位等
                ArrayList<Integer> tt = temp.get(index);
                tt.add(arr[i]);
            }
            //根据temp的值重组arr
            int arrIndex = 0;
            for(int i = 0; i < temp.size(); i++){
                ArrayList<Integer> t3 = temp.get(i);
                for(int j = 0; j < t3.size(); j++){
                    arr[arrIndex++] = t3.get(j);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {49,38,65,97,76,13,27,49,78,34,12,64,1};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
