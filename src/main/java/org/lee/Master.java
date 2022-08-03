package org.lee;

import org.lee.master.MasterNode;

import java.io.IOException;

public class Master {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("开始。。。");
        MasterNode masterNode = new MasterNode(8080);

        System.out.println("启动成功");
        while (true){
            System.out.println("服务端发送消息");
            masterNode.sendToSlaves("master 发送的消息 啊哈哈哈哈");
            Thread.sleep(3000);
        }
    }
}
