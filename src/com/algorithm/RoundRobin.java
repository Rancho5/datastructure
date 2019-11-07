package com.algorithm;

//分治法安排循环赛

public class RoundRobin {
    public void roundRobin(int n){       //n支球队
        int[][] table = new int[n][n];
        roundRobin(n, table);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(table[i][j]+" ");
            }
            System.out.println();
        }
    }

    private void roundRobin(int n, int[][] table) {
        if(n==1){
            table[0][0] = 1;
            return;
        }
        roundRobin(n/2, table);
        for(int i = 0; i < n/2; i++){
            for(int j = 0; j < n/2; j++){
                //构造右上部分
                table[i][j+n/2] = table[i][j] + n/2;
                //构造左下
                table[i+n/2][j] = table[i][j] + n/2;
                //构造右下
                table[i+n/2][j+n/2] = table[i][j];
            }
        }
    }

    public static void main(String[] args) {
        RoundRobin rr = new RoundRobin();
        rr.roundRobin(8);
    }
}
