/*package com.trq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

import com.es.websocket.OurFactory;



public class Server {

	   public static void main(String args[]) throws IOException {  
		      //Ϊ�˼���������е��쳣��Ϣ��������  
		      int port = 8080;  
		      //����һ��ServerSocket�����ڶ˿�8899��  
		      ServerSocket server = new ServerSocket(port);  
		      while (true) {  
		         //server���Խ�������Socket����������server��accept����������ʽ��  
		         Socket socket = server.accept();  
		         //ÿ���յ�һ��Socket�ͽ���һ���µ��߳���������  
		         new Thread(new Task(socket)).start();  
		      }  
		   }  
		     
		   *//** 
		    * ��������Socket����� 
		    *//*  
		   static class Task implements Runnable {  
		   
		      private Socket socket;  
		        
		      public Task(Socket socket) {  
		         this.socket = socket;  
		      }  
		        
		      public void run() {  
		         try {  
		            handleSocket();  
		         } catch (Exception e) {  
		            e.printStackTrace();  
		         }  
		      }  
		        
		      *//** 
		       * ���ͻ���Socket����ͨ�� 
		      * @throws Exception 
		       *//*  
		      private void handleSocket() throws Exception {  
		         BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GBK"));  
		         StringBuilder sb = new StringBuilder();  
		         char[] buffer = new char[1024];
					String msg = null;
					//���������ӷ������л�ȡ����
					while (br.read(buffer) != -1) {
						
						if(null != msg) {
							msg = msg + new String(buffer);
						} else {
							msg = new String(buffer);
						}	
						*//**
						 * �������Ƚϴ��ʱ�򣬻�ְ����ͣ������ܵ������ݰ���--������--��ʱ�򣬱�ʾ��һ֡���ݽ�����ɣ����Խ�����һ���Ĳ���
						 * ������յ������ݰ�û�а���--������--����ʾ��һ֡����û�н�����ɣ���������
						 *//*
						if(msg.contains(";")) {
							*//**
							 * �Խ��յ����ݽ��д���
							 *//*
							String receiveMessage = OurFactory.getSocketMsg().updateReceiveMsg(msg.split(";")[0]);

						} 
						
					}
		       Writer writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");  
		         writer.write("��ã��ͻ��ˡ�");  
		         writer.write(";\n");  
		         writer.flush();  
		         writer.close();  
		         br.close();  
		         socket.close();  
		      }  
		   } 
		   
		 
		}

*/