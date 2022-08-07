package org.lee.slave;

import org.lee.net.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class SlaveReceiver {
    Connection connection;
    String ip;
    Integer port;

    public SlaveReceiver(String ip, Integer port) throws IOException {
        this.ip = ip;
        this.port = port;
        connection = new Connection();
        connection.connection(ip, port);
        listenMaster();
    }

    private void listenMaster() {
        new Thread(() -> readAndPrint(connection.getInputStream()), getTreadName()).start();
    }

    public void readAndPrint(InputStream inputStream) {
        try {
            System.out.println("从节点启动成功："+getTreadName());
            byte[] bytes = new byte[10240];
            int len = -1;
            while ((len = inputStream.read(bytes)) != -1) {
                System.out.println(getTreadName() + new String(bytes, 0,len, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTreadName() {
        return String.format("local[%s] -- server[%s:%s] --:", connection.getLocalPort(), ip, port);
    }


}
