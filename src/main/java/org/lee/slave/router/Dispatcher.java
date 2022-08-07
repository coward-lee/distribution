package org.lee.slave.router;

public class Dispatcher {

    public void dispatch(ReceiveEntity receiveEntity){
        String task = receiveEntity.getTask();
        System.out.println(receiveEntity);
    }
}
