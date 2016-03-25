package com.surfs.nas.transport;

import com.autumn.util.BufferPool;
import java.io.IOException;
import java.nio.ByteBuffer;

public class TcpRequestEncoder extends TcpCommandEncoder {

    private final TcpClient client;

    public TcpRequestEncoder(TcpClient client) {
        this.client = client;
    }

    @Override
    public void sendBuffer(ByteBuffer buf) throws IOException {
        try {
            while (buf.remaining() > 0) {
                client.channel.write(buf);
            }
        } catch (IOException r) {
            throw r;
        } finally {
            BufferPool.freeByteBuffer(buf);
        }
    }
}