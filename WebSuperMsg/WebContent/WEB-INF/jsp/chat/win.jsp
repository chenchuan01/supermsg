<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="container-fluid" style="height: 100%">
	<div class="row" style="height: 100%">
		<div class="col-md-12" style="height: 100%">
				<div class="panel panel-info"style="height: 100%;padding: 2px;background: #d9edf7">
				  <div class="panel-body" style="height: 90%;">
				    <div class="col-md-12" style="height: 100%;">
				    	<!-- 消息区 -->
				    	<div class="panel"style="height: 75%;">
				    		 <div class="panel-body" style="height:100%">
				    		 	<div id="msgAre" class="form-control"  style="height:100%;overflow-y:auto;word-break:break-all;font-size: 14px;">
				    		 	</div>
				    		 </div>
				    	</div>
				    	<!-- 编辑区 -->
				    	<div class="panel"style="height: 30%;">
				    		 <div class="panel-body" style="height: 100%;">
				    		 	<div class="col-md-12"  style="height: 80%;padding: 0;">
				    		 		<input id="fromName" type="hidden" name="fromName" value=""/>
				    		 		<input id="toName" type="hidden" name="toName" value=""/>
				    		 		<textarea id="talkBox" style="height: 100%;width: 100%;overflow-y:auto;word-break:break-all; resize:none;" 
				    		 			placeholder="" class="form-control"></textarea>
				    		 	</div>
				    		 	<div class="col-md-12" style="padding: 3px;">
				    		 		<div class="col-md-3" style="float: left;padding: 6px;">
				    		 			<p><i class="glyphicon glyphicon-info-sign"></i><b id="chatTips"></b></p>
				    		 		</div>
					    		 	<div class="col-md-3" style="float: right;padding: 1px;">
					    		 		<button id="close" class="btn btn-warning btn-sm  btn-block" type="button" onclick="openChatList()" >关闭</button>
					    		 	</div>
					    		 	<div class="col-md-3" style="float: right;padding: 1px;">
					    		 		<button id="send" class="btn btn-primary btn-sm btn-block"  type="button" onclick="send()">发送</button>
					    		 	</div>
				    		 	</div>
				    		 </div>
				    	</div>
				    </div>
				  </div>
				</div>
		</div>
	</div>
</div>