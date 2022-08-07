package org.lee.slave.router;

public class ReceiveEntity {
    // 要做的任务
    String task;
    String message;

    public ReceiveEntity() {
    }

    public ReceiveEntity(String task, String message) {
        this.task = task;
        this.message = message;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ReceiveEntity{" +
                "task='" + task + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
