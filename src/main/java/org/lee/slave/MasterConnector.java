package org.lee.slave;

import org.lee.net.Connection;
import org.lee.slave.router.Dispatcher;
import org.lee.slave.router.ReceiveEntity;
import org.lee.util.JsonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MasterConnector {
    private final Connection connection;
    private final String ip;
    private final Integer port;
    private final Dispatcher dispatcher;

    public MasterConnector(String ip, Integer port) throws IOException {
        this.ip = ip;
        this.port = port;
        connection = new Connection();
        connection.connection(ip, port);
        dispatcher = new Dispatcher();
        listenMaster();
    }

    private void listenMaster() {
        new Thread(() -> dispatch(connection.getInputStream()), getTreadName()).start();
    }

    public void dispatch(InputStream inputStream) {
        try {
            System.out.println("从节点启动成功："+getTreadName());
            byte[] bytes = new byte[10240];
            int len = -1;
            while ((len = inputStream.read(bytes)) != -1) {
                String json = new String(bytes, 0, len, StandardCharsets.UTF_8);
                System.out.println(getTreadName() + json);
                ReceiveEntity receiveEntity = JsonUtil.parse(json, ReceiveEntity.class);
                dispatcher.dispatch(receiveEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTreadName() {
        return String.format("local[%s] -- server[%s:%s] --:", connection.getLocalPort(), ip, port);
    }

    public Connection getConnection() {
        return connection;
    }

    public String getIp() {
        return ip;
    }

    public Integer getPort() {
        return port;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }
}
