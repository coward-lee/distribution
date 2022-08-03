package org.lee.master;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SlaveNode {
    Socket socket;

    protected SlaveNode(Socket socket) {
        this.socket = socket;
    }

    public void write(String content) throws IOException {
        socket.getOutputStream().write(content.getBytes(StandardCharsets.UTF_8));
    }

    public boolean isClosed(){
        return socket.isClosed();
    }


}
