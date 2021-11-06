package com.mxxy.protocol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.mxxy.net.Server;

public class LoginMessage extends Message {

	public static final NetMessage TYPE = NetMessage.Login;

	@Override
	public byte[] toBytes() {
		ByteArrayOutputStream baos = null;
		DataOutputStream dos = null;
		byte[] bytes = null;
		try {
			baos = new ByteArrayOutputStream();
			dos = new DataOutputStream(baos);
			dos.writeInt(0);
			dos.writeUTF("服务器响应客户端");
			dos.flush();
			bytes = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (dos != null) {
					dos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}

	private int log;

	private String string;

	@Override
	public void parse(byte[] bytes) {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
		try {
			this.log = dis.readInt();
			this.string = dis.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void handle() {
		System.err.println(log);
		System.out.println(string);
	}

	@Override
	public void serverHandle(Server server, SocketChannel sc) {
		LoginMessage loginMessage = new LoginMessage();
		try {
			sc.write((ByteBuffer.wrap(loginMessage.toBytes(), 0, loginMessage.toBytes().length)));
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
