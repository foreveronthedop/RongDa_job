package com.llm.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArrayService {
    /**
     * @Author Liruilong
     * @Description  数组线程池操作
     * @Date 13:21 2020/3/14
     * @Param []
     * @return void
     **/

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Array arrayOperate = new Array();
        while (arrayOperate.index < 16) {
            executorService.execute(arrayOperate);
        }
        arrayOperate.print();
    }
}
