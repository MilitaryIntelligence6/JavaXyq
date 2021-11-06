package com.mxxy.net;

import com.mxxy.game.utils.InstanceUtil;
import com.mxxy.protocol.Message;
import com.mxxy.protocol.NetMessage;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ProtocolHandlerImp implements IProtocol {

    private Server server;

    public ProtocolHandlerImp(Server server) {
        this.server = server;
    }

    @Override
    public void handleAccept(SelectionKey key) throws IOException {
        SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();

        sc.configureBlocking(false);
        // 客户端连接一次之后不允许重新连接，以IP为标识

        String hostIP = ((InetSocketAddress) sc.getRemoteAddress()).getHostString();

        sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1024));
    }

    @Override
    public void handleRead(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();

        // 得到并清空缓冲区
        ByteBuffer buffer = (ByteBuffer) key.attachment();

        buffer.clear();

        int len = clientChannel.read(buffer);

        if (len != -1) {
            buffer.flip(); // 将缓冲区准备为数据传出状态

            byte[] buf = buffer.array();

            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(buf));

            int msgOrdinal = dis.readInt();// 读取消息类型

            NetMessage messageState = NetMessage.values()[msgOrdinal];

            dis.close();

            Message message;
            try {
                message = InstanceUtil.getInstance(Class.forName("com.mxxy.protocol." + messageState + "Message"));

                message.parse(buf); // 消息解析

                message.serverHandle(server, clientChannel); // 服务器响应客户端

                buffer.clear();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            clientChannel.close();
        }
    }

    @Override
    public void handleWrite(SelectionKey key) throws IOException {

    }
}
