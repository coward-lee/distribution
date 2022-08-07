package org.lee.slave;

import org.lee.net.Server;

import java.io.IOException;

public class SlaveWorker {
    MasterConnector masterConnector;
    Server server;

    public SlaveWorker() {

    }


    public SlaveWorker(String ip, Integer port) throws IOException {
        this.masterConnector = new MasterConnector(ip, port);
    }

    public void setMasterConnector(MasterConnector masterConnector) {
        this.masterConnector = masterConnector;
    }
    public void bind(Integer port){
        server = new Server();
        server.start(port);
    }


    public static void createWorker(String serverAddr, Integer localPort) throws IOException, InterruptedException {
//        String[] split = serverAddr.split(":");
//        SlaveWorker slaveWorker = new SlaveWorker(split[0], Integer.valueOf(split[1]));
//        slaveWorker.bind(localPort);
        Bootstrap bootstrap = new Bootstrap(serverAddr);
        bootstrap.boot();

    }

}
