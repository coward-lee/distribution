package org.lee.slave.router;

import org.lee.net.Connection;
import org.lee.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class Dispatcher { 
    private final Logger log = LoggerFactory.getLogger(Dispatcher.class);
    private Connection connection;


    public void dispatch(ReceiveEntity receiveEntity) {
        String task = receiveEntity.getTask();
        log.info("{}",receiveEntity);
    }

    public void listenMaster() {
        dispatch(connection.getInputStream());
    }


    public void dispatch(InputStream inputStream) {
        try {
            log.info("从节点启动成功：" + getTreadName());
            byte[] bytes = new byte[10240];
            int len = -1;
            while ((len = inputStream.read(bytes)) != -1) {
                String json = new String(bytes, 0, len, StandardCharsets.UTF_8);
                log.info(getTreadName() + json);
                ReceiveEntity receiveEntity = JsonUtil.parse(json, ReceiveEntity.class);
                dispatch(receiveEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getTreadName() {
        return String.format("local[%s] -- server[%s:%s] --:", connection.getLocalPort(), connection.getMasterIp(), connection.getMasterPort());
    }
}
