package org.lee;

import org.lee.master.MasterNode;
import org.lee.slave.router.ReceiveEntity;
import org.lee.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Master {


    private static final Logger log = LoggerFactory.getLogger(Master.class);
    public static void main(String[] args) throws InterruptedException {
        log.info("开始。。。");
        MasterNode masterNode = new MasterNode(8080);

        log.info("启动成功");
        while (true){
            log.info("服务端发送消息");
            String json = JsonUtil.toJson(new ReceiveEntity("tets_type", "test message"));
            masterNode.sendToSlaves(json);
            Thread.sleep(3000);
        }
    }
}
