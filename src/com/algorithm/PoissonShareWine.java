package com.algorithm;

//todo:理解泊松分酒的原理

public class PoissonShareWine {
    /**
     * 分酒的方法
     * @param volume1 瓶子1初始时酒的体积
     * @param volume2
     * @param volume3
     * @param maxVolume1 瓶子1的最大容量
     * @param maxVolume2
     * @param maxVolume3
     * @param aim
     */
    public void share(int volume1, int volume2, int volume3, int maxVolume1, int maxVolume2, int maxVolume3, int aim){
        int temp1 = volume1;
        int temp2 = volume2;
        int temp3 = volume3;
        while (volume1!=aim && volume2!=aim && volume3!=aim){
            if(volume3!=maxVolume3 && volume2!=0){
                //瓶子2倒入瓶子3
                if(volume2<=(maxVolume3-volume3)){
                    volume3 += volume2;
                    volume2 = 0;
                }else {
                    volume2 = volume2-(maxVolume3-volume3);
                    volume3 = maxVolume3;
                }
            }else if(volume3==maxVolume3){
                //瓶子3满，倒入瓶子1
                if((maxVolume1-volume1)>=volume3){
                    volume1 += volume3;
                    volume3 = 0;
                }else {
                    volume3 = volume3-(maxVolume1-volume1);
                    volume1 = maxVolume1;
                }
            } else if(volume2==0){
                //瓶子1倒入瓶子2
                if(volume1<=maxVolume2){
                    volume2 = volume1;
                    volume1 = 0;
                }else {
                    volume2 = maxVolume2;
                    volume1 -= maxVolume2;
                }
            }

            if(volume1==temp1&&volume2==temp2&&volume3==temp3){
                System.out.println("分酒失败");
                return;
            }
        }
        System.out.println("成功！三个瓶子酒的体积为："+volume1+" "+volume2+" "+volume3);
    }

    public static void main(String[] args) {
        PoissonShareWine psw = new PoissonShareWine();
        psw.share(12, 0, 0, 12, 8, 5, 13);
    }
}
