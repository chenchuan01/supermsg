var websocket;
/*if ('WebSocket' in window) {
    websocket = new WebSocket("ws://localhost:8080/WebSuperMsg/webSocketServer");
} else if ('MozWebSocket' in window) {
    websocket = new MozWebSocket("ws://localhost:8080/WebSuperMsg/webSocketServer");
} else {
    websocket = new SockJS("http://localhost:8080/WebSuperMsg/sockjs/webSocketServer");
}*/
websocket = new SockJS("http://localhost:8080/WebSuperMsg/sockjs/webSocketServer");
websocket.onopen = function (evnt) {
	
};
websocket.onmessage = function (evnt) {
   if(event.isTrusted==true){
	   chatTips(ChatTips.connect);
   }else if(evnt.data){
	   var msg = eval(evnt.data);
	   chatTips(ChatTips.news);
	   callbackShowMsg(ChatRole.he, msg);
   }
   
};
websocket.onerror = function (evnt) {
	
};
websocket.onclose = function (evnt) {
	
}