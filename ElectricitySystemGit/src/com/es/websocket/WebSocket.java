package com.es.websocket;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.es.socket.SocketClient;


//webSocket��ȡ����ͻ��˴��������ݣ�ʵʱ�ɼ�������ͨ��websocketʵʱ��ʾ��������ϣ�Ҳ�����ڿͻ���������ʾ
@ServerEndpoint(value = "/websocket")
public class WebSocket {
	//ע�ͣ�2017.11.9 �˴����徲̬�������������������л�ȡ��������
	private static final Set<WebSocket> connections = new CopyOnWriteArraySet<>();
	//ע�ͣ�2017.11.9 
	private Session session;
	private SocketClient socketClient;

	/**
	 * @category ��Websocket����
	 * @param session
	 */
	// ��֤C++��javaServiceֻ����һ�� Ȼ��ͨ��java�㲥 ���������ӣ���������ʱ���Ϊsession
	//ע�ͣ�2017.11.9 ��websocket�ͻ������ӵ���������ʱ�򣬴˷�������ִ�У����Ҵ���һ��session�Ự�������������õ����session���Ϳ�����ͻ��˷�����Ϣ
	@OnOpen
	public void start(Session session) {
		this.session = session;
		connections.add(this);// ���˶�����뼯��������֮��㲥�ã����Ҫʵ��һ��һ���ģ������Ͷ�ӦΪmap������㲥��������
		System.out.println(connections.size());
		System.out.println("websocket����");
		/**
		 * ��c++��Socket������ֻ��һ��ͨ����ͨ��WebSocket�㲥��ʵ�ָ����ͻ������ݵķַ�
		 */
		System.out.println(socketClient);
		if (connections.size() < 2) {
			socketClient = OurFactory.getSocketClient(OurFactory.getHost(), OurFactory.getPort());
			/**
			 * ���������̣߳����ӵ�ǰ��������Socket�������Ƿ�Ͽ�
			 * �����ǰ������Ϊ0����ʾ���еĿͻ��˶���WebSocket�������Ͽ�����ʱ��Socketͨ���Ͽ�
			 * ���Socket�������Ͽ���������пͻ���
			 */
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						/**
						 * �����ǰ������Ϊ0����ʾ���еĿͻ��˶���WebSocket�������Ͽ�����ʱ��Socketͨ���Ͽ�
						 */
						/**
						 * ���Socket�������Ͽ���������пͻ���
						 */
						if (socketClient.getSocket().isClosed()) {
							connections.clear();
							break;
						}
					}
				}
			}).start();
		}
	}

	/**
	 * @category �ر�WebSocket����
	 */
	//�ͻ��˹رյ�ʱ�򣬾ͻ��Զ�ִ��
	@OnClose
	public void end() {
		System.out.println("��ʼ�ر�WebSocket");
		connections.remove(this);//��socket����Ӽ������Ƴ����Ա�㲥ʱ�����ʹ����ӡ�������Ƴ��ͻᱨ��
		System.out.println("websocket�ر�");
		if (connections.size() == 0) {
			socketClient.closeSocket();
		}
	}
	/**
	 * @category ����˫����
	 * @param message
	 *            ���ܿͻ��˴��������� ��
	 */
	//�ͻ��˸�������������Ϣ������������Զ����ã����ҿ����õ����͹���������
	@OnMessage
	public void incoming(String message) {
		/**
		 * ���ͻ��˷����ر��źţ��򽫵�ǰWebSocketͨ���ر�
		 */
		System.out.println("�ҽ�����"+message+"---"+connections.size()+"-------------"+message);
		if (message.equals("clientClosed")) {
			end();
		}
		/**
		 * ��Socket������ֻ��һ��ͨ����ͨ��WebSocket�㲥��ʵ�ָ����ͻ������ݵķַ�
		 */
		else if(connections.size() < 2) {
			/**
			 * ����һ�̣߳�����Socket��������
			 */
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						socketClient.getData();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						for (WebSocket webSocket : connections) {
							connections.remove(webSocket);
						}
						e.printStackTrace();
					}
				}
			}).start();
			/**
			 * ��ͻ���ѭ������Socket���͹���������
			 */
		while (true) {
/*				System.out.println();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				broadcast(str);*/
			//System.out.println();
				if (null != socketClient.getReceiveMessage()) {
					System.out.println("-------------------" + socketClient.getReceiveMessage());
					broadcast(OurFactory.getSocketMsg().updateReceiveMsg(socketClient.getReceiveMessage()));
					// broadcast("0000");
					socketClient.setReceiveMessage(null);
				}
			}
		}
	}

	/**
	 * @category WebSocket�������
	 * @param t
	 * @throws Throwable
	 */
	@OnError
	public void onError(Throwable t) throws Throwable {
		System.out.println("������");
	}

	/**
	 * @category ��ͻ��˷�������
	 * @param msg
	 *            ���͵�����
	 */
	private static void broadcast(String msg) {
		/**
		 * ��ÿһ���ͻ��˶���������
		 */
		//System.out.println("��ͻ��˷ַ�����");
		//�޸�:2017.11.11 �㲥�������ͻ��˼���������Ϣ��ע�ⷢ��Ҫ�õ�session����session.getBasicRemote().sendText(msg)������Ϣ
		for (WebSocket client : connections) { //�޸ģ�2017.11.11 ��������
			try {  //�޸ģ�2017.11.11  ������client�Ѿ�����
				synchronized (client) {
					client.session.getBasicRemote().sendText(msg);
				}
			} catch (IOException e) {
				System.out.println("����ʧ��");
				try {
					client.session.close();
				} catch (IOException e1) {
					// Ignore
				}
				broadcast("websocket�ر�");
			}
		}
	}
}
