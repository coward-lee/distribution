package org.lee;

import org.lee.slave.SlaveWorker;

import java.io.IOException;

public class Slave {

    public static void main(String[] args) throws InterruptedException, IOException {
        SlaveWorker.createWorker("127.0.0.1:8080", 8081);
//        SlaveWorker.createWorker("127.0.0.1:8080", 8082);
//        SlaveWorker.createWorker("127.0.0.1:8080", 8083);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
