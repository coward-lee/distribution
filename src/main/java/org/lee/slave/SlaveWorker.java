package org.lee.slave;

import java.io.IOException;

public class SlaveWorker {
    MasterWorker masterWorker;

    public SlaveWorker(String ip, Integer port) throws IOException {
        this.masterWorker = new MasterWorker(ip, port);
    }

    public static SlaveWorker createWorker(String serverAddr) throws IOException {
        String[] split = serverAddr.split(":");
        return new SlaveWorker(split[0], Integer.valueOf(split[1]));
    }
}
