package org.lee.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
    private  ServerSocket serverSocket;
    private  final ReentrantLock lock = new ReentrantLock();

    public  void start(Integer port) {
        try {
            lock.lock();
            if (serverSocket == null) {
                serverSocket = new ServerSocket(port);
            }
            lock.unlock();
        } catch (Throwable t) {
            System.out.println("启动出错");
            t.printStackTrace();
        }
    }

    public  Socket accept() throws IOException {
        return serverSocket.accept();
    }

    public  void printReceive()  {
        try{
            start(8080);
            InputStream inputStream = accept().getInputStream();
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(bytes)) != -1) {
                System.out.print(new String(bytes, 0, len, StandardCharsets.UTF_8));
            }
            System.out.println();
        }catch (Exception e){
            System.out.println("出错还是有客户端断开链接了");
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws IOException {
        System.out.println("开始");
        while (true){
            System.out.println("开始接受消息");
            new Server().printReceive();
        }
    }
}