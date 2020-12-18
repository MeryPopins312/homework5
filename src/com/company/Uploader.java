package com.company;


import java.util.concurrent.CountDownLatch;

public class Uploader extends Thread {
    private CountDownLatch uploader;

    public Uploader(CountDownLatch uploader) {
        this.uploader = uploader;
    }

    @Override
    public void run() {
        try {
            System.out.println("Файл начинает скачиваться из сервера!");
            sleep(1500);
            System.out.println("Файл закончил загрузку из сервера");
            // countDown() - делает -1, исполняет желание
            uploader.countDown();

        } catch (Exception ignore) {

        }
    }
}
