package org.lee.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Connection {
    Socket socket;
    OutputStream outputStream;
    InputStream inputStream;
    public void connection(String ip,Integer port) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(ip, port));
        this.socket = socket;
        this.outputStream = socket.getOutputStream();
        this.inputStream = socket.getInputStream();
    }

    public void connection(String url) throws IOException {
        String[] split = url.split(":");
        connection(split[0], Integer.valueOf(split[1]));
    }

    public void write(String content) throws IOException {
        outputStream.write(content.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Connection connection = new Connection();
        connection.connection("127.0.0.1:8080");
        while (true){
            Thread.sleep(1000 * 10);
            System.out.println("发送消息");
            connection.write("节点注册\n");
        }
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public Integer getLocalPort(){
        return socket.getLocalPort();
    }
}
