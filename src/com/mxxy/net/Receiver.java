package com.mxxy.net;

import com.mxxy.game.utils.InstanceUtil;
import com.mxxy.protocol.Message;
import com.mxxy.protocol.NetMessage;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Receiver extends Thread {

    private Selector selectors;

    public Receiver(Selector selector) {
        this.selectors = selector;
    }

    @Override
    public void run() {
        try {
            while (selectors.select() > 0) {
                // 遍历每个有可用IO操作Channel对应的SelectionKey
                for (SelectionKey sk : selectors.selectedKeys()) {

                    // 如果该SelectionKey对应的Channel中有可读的数据
                    if (sk.isReadable()) {
                        // 使用NIO读取Channel中的数据
                        SocketChannel sc = (SocketChannel) sk.channel();

                        ByteBuffer buffer = (ByteBuffer) sk.attachment();

                        buffer.clear();

                        int len = sc.read(buffer);

                        if (len != -1) {

                            buffer.flip();

                            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(buffer.array()));

                            NetMessage messageState = NetMessage.values()[dis.readInt()];

                            Message msg;
                            try {
                                msg = InstanceUtil.getInstance(Class.forName("com.mxxy.protocol." + messageState +
                                        "Message"));

                                msg.parse(buffer.array());

                                msg.handle();

                                buffer.clear();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            sk.interestOps(SelectionKey.OP_READ);
                        } else {
                            sc.close();
                        }
                    }
                    // 删除正在处理的SelectionKey
                    selectors.selectedKeys().remove(sk);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
