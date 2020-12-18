package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        CountDownLatch uploaderCDL = new CountDownLatch(1);
        Uploader uploader = new Uploader(uploaderCDL);
        // start() - это начало, запускает метод run()
        uploader.start();
        CountDownLatch downloaderCDL = new CountDownLatch(10);
        Semaphore semaphore = new Semaphore(3, true);
        // три кассира одновременно обслуживают клиентов, true нужен для того что бы они были дисциплинированны.

        //for each-нужен для распаковки массивов . fori-просто цикл
        for (int i = 1; i < 11; i++) {
            new Downloader("Пользователь " + i, uploaderCDL, downloaderCDL, semaphore).start();//начало
            //+i -это прибавляем чило и в итоге будет 1 2 3 4..и до 10.
        }

        try {
            downloaderCDL.await();
            System.out.println("Файл удален из сервера !");
            //в await мы узнаем все ли загрузили файл , и когда это подтвержается пишем соут.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
