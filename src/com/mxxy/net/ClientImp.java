package com.mxxy.net;

import com.mxxy.protocol.Message;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ClientImp implements IClient {

    private SocketChannel channel; // 与服务器通信的通道

    private InetSocketAddress clientAddress;

    private Selector selector;

    private String hostIp; // 要连接的服务器Ip地址

    private int hostListenningPort; // 要连接的远程服务器在监听的端口

    private Receiver mReceiver;

    @Override
    public void send(Message paramMessage) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(paramMessage.toBytes());
        send(buffer);
    }

    @Override
    public void send(ByteBuffer bufOut) throws IOException {
        try {

            if (channel.isOpen()) {
                while (bufOut.hasRemaining()) {
                    channel.write(bufOut);
                }
                bufOut.clear();
                channel.keyFor(selector).interestOps(SelectionKey.OP_READ);
            }
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isThreadStart() {
        return false;
    }

    @Override
    public void openReadThread() throws InterruptedException {
        mReceiver = new Receiver(selector);
        mReceiver.start();
    }

    @Override
    public void closeReadThread() {
        mReceiver.stop();
    }

    @Override
    public String getIp() {
        return hostIp;
    }

    @Override
    public int getPort() {
        return hostListenningPort;
    }

    @Override
    public void connect(String HostIp, int HostListenningPort) {
        this.hostIp = HostIp;
        this.hostListenningPort = HostListenningPort;
        clientAddress = new InetSocketAddress(hostIp, hostListenningPort);
    }

    @Override
    public void initialize() throws IOException {
        channel = SocketChannel.open(new InetSocketAddress(hostIp, hostListenningPort));
        channel.configureBlocking(false);

        // 打开并注册选择器到信道
        selector = Selector.open();

        channel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
    }

    @Override
    public InetSocketAddress getInetSocketAddress() {
        return clientAddress;
    }
}
