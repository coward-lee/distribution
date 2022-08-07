package org.lee.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Connection {
    private final Logger log = LoggerFactory.getLogger(Connection.class);
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

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * 这个的port是来自连接 master 的客户端端口
     */
    public Integer getLocalPort(){
        return socket.getLocalPort();
    }

    public Integer getMasterPort(){
        return socket.getPort();
    }
    public String getMasterIp(){
        return socket.getInetAddress().getHostAddress();
    }
}
