package com.mxxy.net;

import com.mxxy.protocol.Message;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public interface IClient {

    public abstract void connect(String HostIp, int HostListenningPort);

    public abstract void initialize() throws IOException;

    public abstract void send(Message paramMessage) throws IOException;

    public abstract void send(ByteBuffer bufOut) throws IOException;

    public abstract boolean isThreadStart();

    /**
     * 开启接收线程
     *
     * @throws InterruptedException
     */
    public abstract void openReadThread() throws InterruptedException;

    /**
     * 关闭接收线程
     */
    public abstract void closeReadThread();

    public abstract String getIp();

    public abstract int getPort();

    public abstract InetSocketAddress getInetSocketAddress();
}
