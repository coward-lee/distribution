package org.lee.slave;

import org.lee.TaskType;
import org.lee.slave.router.ReceiveEntity;
import org.lee.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HeartBeat {
    private final Logger log = LoggerFactory.getLogger(HeartBeat.class);
    String pattern = "yyyy-MM-dd hh:mm:ss";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    MasterConnector masterConnector;

    public HeartBeat() {
    }




    public void doBeat() throws IOException, InterruptedException {
        while (true){
            LocalDateTime now = LocalDateTime.now();
            ReceiveEntity receiveEntity = new ReceiveEntity(TaskType.HEAR_BEAT.name(), now.format(formatter));
            log.info("客户端发送心跳信息：{}", receiveEntity);
            masterConnector.sendToMaster(JsonUtil.toJson(receiveEntity));
            Thread.sleep(3000);
        }
    }

    public void setMasterConnector(MasterConnector masterConnector) {
        this.masterConnector = masterConnector;
    }


}
