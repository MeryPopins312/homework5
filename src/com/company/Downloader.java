package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Downloader extends Thread {
    private CountDownLatch uploader;
    private CountDownLatch downloader;
    private Semaphore semaphore;

    public Downloader(String name, CountDownLatch uploader, CountDownLatch downloader, Semaphore semaphore) {
        super(name);
        this.uploader = uploader;
        this.downloader = downloader;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            uploader.await();
            //await-сообщает когда джин(coundDownLatch) исполнил все желания!
            semaphore.acquire();
            //acquire-кассир который принимает 1 клиента .(если два кассира , то 2 клиента),начла обязательств кассира.
            System.out.println(getName()+" Начал скачивать файл!");//getName - уже существует в потоке Thread
            sleep(1000);
            System.out.println(getName()+" Скачал файл!");
            downloader.countDown();
            //countDown -  исполнение желания клиентов
            semaphore.release();
            //release-конец обязательств кассира


        }catch (Exception ignore){

        }
    }
}
