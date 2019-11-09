package com.algorithm;

//实现两个大数相乘
public class BigCountMultiplication {
    public void bigCountMultiplication(String num1, String num2){
        char[] char1 = num1.toCharArray();
        char[] char2 = num2.toCharArray();
        int length1 = char1.length;
        int length2 = char2.length;
        int[] result = new int[length1+length2-1];

        //大数相乘的核心思想，最终结果的某一位只与两个乘数中的某几位有关系
        for(int i = 0; i < length1; i++){
            for(int j = 0; j < length2; j++){
                result[i+j] += (char1[i]-'0')*(char2[j]-'0');   //char转int
            }
        }

        //进位
        for(int i = result.length-1; i > 0 ; i--){
            result[i-1] += result[i]/10;
            result[i] = result[i]%10;
        }

        //打印
        System.out.print(num1+"*"+num2+"=");
        for(int i = 0; i < result.length; i++){
            System.out.print(result[i]+"");
        }
    }

    public static void main(String[] args) {
        BigCountMultiplication bcm = new BigCountMultiplication();
        bcm.bigCountMultiplication("3", "4");
    }

}
