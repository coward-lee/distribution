package org.lee;

import org.lee.master.MasterNode;
import org.lee.slave.router.ReceiveEntity;
import org.lee.util.JsonUtil;

import java.io.IOException;

public class Master {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("开始。。。");
        MasterNode masterNode = new MasterNode(8080);

        System.out.println("启动成功");
        while (true){
            System.out.println("服务端发送消息");
            String json = JsonUtil.toJson(new ReceiveEntity("tets_type", "test message"));
            masterNode.sendToSlaves(json);
            Thread.sleep(3000);
        }
    }
}
