package com.es.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.es.websocket.OurFactory;



public class SocketClient {
	private String receiveMessage;  //����Socket���������͹���������
	private String sendMessage;     //��Socket���������͵�����
	private Socket socket;          //Socket
	
	public String getReceiveMessage() {
		return receiveMessage;
	}
	public void setReceiveMessage(String receiveMessage) {
		this.receiveMessage = receiveMessage;
	}
	public String getSendMessage() {
		return sendMessage;
	}
	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	/**
	 * @category ���캯����ʵ������ʱ�򴴽�Socket����ͨ��
	 */
	public SocketClient(String host, int port) {
		try {
			socket = new Socket(host, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (socket.isConnected()) {
			System.out.println("is connected");
		} else {
			return;
		}
	}
	/**
	 * @category �ر�Socket����
	 */
	public void closeSocket() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getData() throws UnknownHostException {
//		sendMessage = msg;
		
		int i = 0;
		
		PrintWriter writer = null;
		BufferedReader reader = null; 
		try {
			writer = new PrintWriter(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			// ���������з�����
			writer.println(sendMessage);
			writer.flush();
			char[] buffer = new char[1024];
			String msg = null;
			//���������ӷ������л�ȡ����
			while (reader.read(buffer) != -1) {
				
				if(null != msg) {
					msg = msg + new String(buffer);
				} else {
					msg = new String(buffer);
				}	
				/**
				 * �������Ƚϴ��ʱ�򣬻�ְ����ͣ������ܵ������ݰ���--������--��ʱ�򣬱�ʾ��һ֡���ݽ�����ɣ����Խ�����һ���Ĳ���
				 * ������յ������ݰ�û�а���--������--����ʾ��һ֡����û�н�����ɣ���������
				 */
				if(msg.contains(";")) {
					i++;
					if(i%3 == 0) {
						sendMessage = "msgOne";
					} else if(i%3 == 1) {
						sendMessage = "msgTwo";
					} else {
						sendMessage = "msgThree";
					}
					/**
					 * �Խ��յ����ݽ��д���
					 */
					receiveMessage = OurFactory.getSocketMsg().updateReceiveMsg(msg.split(";")[0]);
					msg = null;
					writer.println(sendMessage);
					buffer = new char[1024];
					writer.flush();
				} 
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (writer != null) {
				writer.close();
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
