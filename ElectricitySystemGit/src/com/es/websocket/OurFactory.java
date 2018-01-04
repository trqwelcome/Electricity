package com.es.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.es.socket.SocketClient;
import com.es.socket.SocketMsg;



public class OurFactory {
	// socket��Ϣ����
	public static SocketMsg getSocketMsg() {
		return new SocketMsg();
	}
	//��ȡip��ַ�Ͷ˿ں���Ӧ�ķ���
	public static SocketClient getSocketClient(String host, int port) {
		return new SocketClient(host, port);
	}
	//��ȡip��ַ
	public static String getHost() {
		InputStream in1 = OurFactory.class.getClassLoader()
				.getResourceAsStream("com/es/websocket/socket.properties");
		Properties p = new Properties();
		try {
			p.load(in1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return p.getProperty("host");
	}
	//��ȡ�˿ں�
	public static int getPort() {
		InputStream in1 = OurFactory.class.getClassLoader()
				.getResourceAsStream("com/es/websocket/socket.properties");
		Properties p = new Properties();
		try {
			p.load(in1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return Integer.valueOf(p.getProperty("port"));
	}
}
