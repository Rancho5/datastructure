package com.algorithm;

//背包问题
public class Knapsack {
    public void getMaxValue(int packageVolume, int[] weight, int[] value){
        int row = packageVolume + 1;
        int column = weight.length + 1;
        int[][] matrixValue = new int[row][column];

        //动态规划
        for(int i = 1; i < row; i++){
            for(int j = 1; j < column; j++){
                if(weight[j-1]>i){
                    //如果第j个物品的重量大于动态规划此时包的最大容量
                    matrixValue[i][j] = matrixValue[i][j-1];
                }else{
                    matrixValue[i][j] = Math.max(matrixValue[i][j-1], matrixValue[i-weight[j-1]][j-1]+value[j-1]);
                }
            }
        }

        System.out.println("最大价值为："+matrixValue[row-1][column-1]);
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                System.out.print(matrixValue[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Knapsack k = new Knapsack();
        int[] weight = new int[]{11, 10, 10};
        int[] value = new int[]{22, 19, 3};
        int packageVolumn = 20;
        k.getMaxValue(packageVolumn, weight, value);
    }
}
