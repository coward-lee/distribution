package org.lee.master;

import org.lee.net.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class MasterNode {
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
        listen();
    }

    public void listen() {
        new Thread(() -> {
            try {
                while (true){
                    acceptSlave();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "master").start();
    }

    public void acceptSlave() throws IOException {
        System.out.println("等待链接");
        SlaveNode slaveNode = new SlaveNode(server.accept());
        System.out.println("有salve 过来了，"+slaveNode.socket);
        slaveNodes.add(slaveNode);
    }

    public void sendToSlaves(String content) {
        List<SlaveNode> shouldRemoveNode = new ArrayList<>();
        slaveNodes.forEach(slaveNode ->{
            try {
                if (slaveNode.isClosed()){
                    shouldRemoveNode.add(slaveNode);
                }else{
                    slaveNode.write(content);
                }
            }catch (Throwable t){
                System.out.println("发送到从节点失败");
                shouldRemoveNode.add(slaveNode);
                t.printStackTrace();
            }
        });
        shouldRemoveNode.forEach(slaveNodes::remove);
    }
}
