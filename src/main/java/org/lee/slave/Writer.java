package org.lee.slave;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Writer {

    private final OutputStream outputStream;

    public Writer(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
        outputStream.write(content.getBytes(StandardCharsets.UTF_8));
    }
}
