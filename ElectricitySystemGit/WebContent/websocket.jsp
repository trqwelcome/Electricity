<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head>
<title>WebSocket client</title>
<style>
body {
    padding: 40px;
}

#outputPanel {
    margin: 10px;
    padding: 10px;
    background: #eee;
    border: 1px solid #000;
    min-height: 300px;
}
</style>
</head>
<body>
    <input type="text" id="messagebox" size="60" />
    <input type="button" id="buttonSend" value="Send Message" />
    <input type="button" id="buttonConnect" value="Connect to server" />
    <input type="button" id="buttonClose" value="Disconnect" />
    <br>
    <%
        out.println("Session ID = " + session.getId());
    %>
    <div id="outputPanel"></div>
</body>
<script type="text/javascript">
    var infoPanel = document.getElementById('outputPanel'); // 输出结果面板  
    var textBox = document.getElementById('messagebox'); // 消息输入框  
    var sendButton = document.getElementById('buttonSend'); // 发送消息按钮  
    var connButton = document.getElementById('buttonConnect');// 建立连接按钮  
    var discButton = document.getElementById('buttonClose');// 断开连接按钮  
    // 控制台输出对象  
    var console = {
        log : function(text) {
            infoPanel.innerHTML += text + "<br>";
        }
    };
    // WebSocket演示对象  
    var demo = {
        socket : null, // WebSocket连接对象  
        host : '', // WebSocket连接 url  
        connect : function() { // 连接服务器  
            window.WebSocket = window.WebSocket || window.MozWebSocket;
            if (!window.WebSocket) { // 检测浏览器支持  
                console.log('Error: WebSocket is not supported .');
                return;
            }
            this.socket = new WebSocket(this.host); // 创建连接并注册响应函数  
            this.socket.onopen = function() {
                console.log("websocket is opened .");
            };
            this.socket.onmessage = function(message) {
                console.log(message.data);
            };
            this.socket.onclose = function() {
                console.log("websocket is closed .");
                demo.socket = null; // 清理  
            };
        },
        send : function(message) { // 发送消息方法  
            if (this.socket) {
                this.socket.send(message);
                return true;
            }
            console.log('please connect to the server first !!!');
            return false;
        }
    };
    // 初始化WebSocket连接 url  
    demo.host = (window.location.protocol == 'http:') ? 'ws://' : 'wss://';
    demo.host += window.location.host +'/ElectricitySystem/websocket';
    // 初始化按钮点击事件函数  
    sendButton.onclick = function() {
        var message = textBox.value;
        if (!message)
            return;
        if (!demo.send(message))
            return;
        textBox.value = '';
    };
    connButton.onclick = function() {
        if (!demo.socket)
            demo.connect();
        else
            console.log('websocket already exists .');
    };
    discButton.onclick = function() {
        if (demo.socket)
            demo.socket.close();
        else
            console.log('websocket is not found .');
    };
</script>
</html>