package org.lee.slave;

import org.lee.slave.router.Dispatcher;

import java.io.IOException;

public class Bootstrap {
    private MasterConnector masterConnector;
    private final HeartBeat heartBeat;
    private final SlaveWorker slaveWorker;
    private final Dispatcher dispatcher;

    public Bootstrap() {
        heartBeat = new HeartBeat();
        slaveWorker = new SlaveWorker();
        dispatcher = new Dispatcher();
    }

    public Bootstrap(String ip, Integer port) throws IOException {
        this();
        masterConnector = new MasterConnector(ip, port);
        slaveWorker.setMasterConnector(masterConnector);
        heartBeat.setMasterConnector(masterConnector);
        dispatcher.setConnection(masterConnector.getConnection());
    }

    public Bootstrap(String addr) throws IOException {
        this(addr.split(":")[0], Integer.valueOf(addr.split(":")[1]));
    }

    public void boot() throws IOException, InterruptedException {
        masterConnector.listenMaster(dispatcher::listenMaster);
        heartBeat.doBeat();
    }


}
