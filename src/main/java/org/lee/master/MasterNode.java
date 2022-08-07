package org.lee.master;

import org.lee.Master;
import org.lee.net.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MasterNode {
    private static final Logger log = LoggerFactory.getLogger(Master.class);

    Server server;
    Integer port;
    List<SlaveNode> slaveNodes = new CopyOnWriteArrayList<>();

    public MasterNode(Integer port) {
        this.server = new Server();
        this.port = port;
        start();
    }

    public void start() {
        server.start(port);
        listenAccept();
    }

    public void listenAccept() {
        new Thread(() -> {
            try {
                while (true) {
                    acceptSlave();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "master").start();
    }

    public void acceptSlave() throws IOException {
        log.info("等待新的链接");
        SlaveNode slaveNode = new SlaveNode(server.accept());
        log.info("有salve 过来了，" + slaveNode.socket);
        slaveNodes.add(slaveNode);
        new Thread(() -> listenSlaveMessage(slaveNode)).start();
    }

    public void listenSlaveMessage(SlaveNode slaveNode) {
        Socket socket = slaveNode.socket;
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[10240];
            int len = -1;
            while ((len = inputStream.read(bytes)) != -1) {
                log.info("来自slave 的消息：{}", new String(bytes, 0, len, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
            slaveNodes.remove(slaveNode);
        }
    }

    public void sendToSlaves(String content) {
        List<SlaveNode> shouldRemoveNode = new ArrayList<>();
        slaveNodes.forEach(slaveNode -> {
            try {
                if (slaveNode.isClosed()) {
                    shouldRemoveNode.add(slaveNode);
                } else {
                    slaveNode.write(content);
                }
            } catch (Throwable t) {
                log.info("发送到从节点失败");
                shouldRemoveNode.add(slaveNode);
                t.printStackTrace();
            }
        });
        shouldRemoveNode.forEach(slaveNodes::remove);
    }
}
