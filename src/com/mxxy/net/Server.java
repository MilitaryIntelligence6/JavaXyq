package com.mxxy.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 服务器
 * 
 * @author dell
 *
 */
public class Server {

	public static void main(String[] args) {
		Server server = new Server();
		try {
			server.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 超时时间，单位毫秒 */
	private static final int TimeOut = 3000;

	public static int ListenPort = 8888;

	public void start() throws Exception {
		try {

			Selector selector = Selector.open(); // 创建选择器

			ServerSocketChannel listenerChannel = ServerSocketChannel.open(); // 打开监听信道

			listenerChannel.socket().bind(new InetSocketAddress(ListenPort)); // 与本地端口绑定

			listenerChannel.configureBlocking(false);// 设置为非阻塞模式

			listenerChannel.register(selector, SelectionKey.OP_ACCEPT);// operation

			IProtocol iProtocol = new ProtocolHandlerImp(this);

			while (true) {

				if (selector.select(TimeOut) == 0) {
					continue;
				}

				Set<SelectionKey> keys = selector.selectedKeys();

				System.err.println(keys.size());

				Iterator<SelectionKey> keyIter = keys.iterator();

				while (keyIter.hasNext()) {
					SelectionKey key = keyIter.next();
					try {
						if (key.isAcceptable()) {

							System.out.println("accept");
							iProtocol.handleAccept(key);
						}

						if (key.isReadable()) {
							System.out.println("readble");
							iProtocol.handleRead(key);
						}

					} catch (IOException e) {
						keyIter.remove();
					}
					keyIter.remove();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
