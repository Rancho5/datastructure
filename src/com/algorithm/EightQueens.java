package com.algorithm;

//回溯法解决八皇后问题
public class EightQueens {

    final int queenNum = 8;
    int[] location = new int[queenNum];    //表示每一列的“皇后”放在了哪个位置，如location[0] = 0就表示第一列皇后放在了第一行，即坐标（1, 1）
    int planNum = 0;                         //表示可行方案的个数

    public void putQueens(){
            putQueens(0);

    }

    private void putQueens(int i) {         //放置第i列的皇后
        boolean[] canQueen = new boolean[queenNum];      //表示当前列每个位置是否可以放置“皇后”，true表示不可以，false表示可以
        //判断第i列哪些位置能放“皇后”
        for(int j = 0; j < i; j++){
            //同一行不能有
            canQueen[location[j]] = true;
            //正斜线不能有
            if(location[j]-(i-j)>=0)
                canQueen[location[j]-(i-j)] = true;
            //反斜线不能有
            if(location[j]+(i-j)<queenNum)
                canQueen[location[j]+(i-j)] = true;
        }

        //开始放“皇后”
        for(int j = 0; j < queenNum; j++){
            if(!canQueen[j]){       //如果该位置可以放“皇后”
                location[i] = j;
                if(i == queenNum-1){
                    planNum++;
                    //打印该方法的结果
                    System.out.println("第"+planNum+"种方法为：");
                    for(int m = 0; m < queenNum; m++){
                        for(int n = 0; n < queenNum; n++){
                            if(location[n] == m){
                                System.out.print("Q ");
                            }else {
                                System.out.print("+ ");
                            }
                        }
                        System.out.println();
                    }
                }else{
                    putQueens(i+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        EightQueens eq = new EightQueens();
        eq.putQueens();
    }

}
