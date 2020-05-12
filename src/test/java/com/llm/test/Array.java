package com.llm.test;

import java.util.Arrays;
import java.util.Random;

public class Array implements Runnable{
    private volatile int[] ints = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    private volatile int[] getInts = new int[16];
    public  volatile static int index = 0;

    @Override
    public void run() {
        //创建随机数
        Random random = new Random();
        if (ints.length - index > 0) {
            int indexScope = ints.length - index;
            int indexs = random.nextInt(indexScope);
            getInts[index] = ints[indexs];
            ints[indexs] = ints[indexScope - 1];
            index++;
        }
    }
    public void print() {
        System.out.println("生成的数组为：" + Arrays.toString(getInts));
    }


}
