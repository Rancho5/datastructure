package com.algorithm;

//动态规划求最长公共子序列 Longest Common Subsequence
//公共子序列可以不连续，公共子串必须连续

public class LCS {
    public void getLCS(String s1, String s2){
        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        int column = char1.length;
        int row = char2.length;
        int[][] matrixLCS = new int[row][column];

        //初始化第0行和第0列
        for(int i = 0; i < column; i++){
            if(char1[i]==char2[0]){
                matrixLCS[0][i] = 1;
                for(int j = i; j < column; j++)
                    matrixLCS[0][j] = 1;
                break;
            }
        }
        for(int i = 0; i < row; i++){
            if(char1[0]==char2[i]){
                matrixLCS[i][0] = 1;
                for(int j = i; j < row; j++)
                    matrixLCS[j][0] = 1;
                break;
            }
        }

        //开始动态规划
        for(int i = 1; i < row; i++){
            for(int j = 1; j < column; j++){
                if(char2[i]==char1[j]){
                    matrixLCS[i][j] = matrixLCS[i-1][j-1]+1;
                }else{
                    matrixLCS[i][j] = Math.max(matrixLCS[i-1][j], matrixLCS[i][j-1]);
                }
            }
        }

        System.out.println("最长公共子序列长度为："+matrixLCS[row-1][column-1]);
        //打印矩阵
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                System.out.print(matrixLCS[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        LCS lcs = new LCS();
        lcs.getLCS("random", "android");
    }
}
