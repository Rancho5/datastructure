package com.recursion;

public class HanoiTower {
    int stepNum = 0;
    public  void hanoi(int num, char begin, char end, char third){
        if(num==1){
            move(num, begin, end);
        }else{
            hanoi(num-1, begin, third, end);
            move(num, begin, end);
            hanoi(num-1, third, end, begin);
        }
    }

    private  void move(int num, char begin, char end) {
        stepNum++;
        System.out.println("第"+stepNum+"步："+"将"+num+"从"+begin+"移动到"+end);
    };

    public static void main(String[] args) {
        HanoiTower ht = new HanoiTower();
        ht.hanoi(5, 'A', 'C', 'B');
    }
}
