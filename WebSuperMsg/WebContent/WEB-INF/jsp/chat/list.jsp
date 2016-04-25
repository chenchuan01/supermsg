<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script>
$(function(){
	page(1);
});
</script>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<form id="search" action="friend/friendPage.do" method="post" class="form-inline">
				  <div class="form-group">
				    <label class="sr-only" for="frined_name">好友昵称</label>
				    <input type="hidden" name="myFlag" value="1"/>
				    <input type="text" class="form-control" id="frined_name" name="name" placeholder="好友昵称">
				  </div>
				  <button class="btn btn-info" type="button" onclick="page(1)"><i class="glyphicon glyphicon-search"></i></button>
			</form>
			<table class="table table-hover">
				<tr id="template" style="display: none;">
					<td item="index"></td>
					<td item="name"></td>
					<td item="userName"></td>
					<td item="oprea" itemFiled="id">
						<a href="javascript:;" title="来聊两句"  onclick="chatWithFriend('chat/startChat.do?host=${sysuser.id}&friend_id=')" class="btn btn-info btn-sm">
						<i class="glyphicon glyphicon-comment"></i>
						</a>
					</td>
				</tr>
				<thead>
					<tr>
						<th>#</th>
						<th>好友昵称</th>
						<th>好友账号</th>
						<th>操作</th>                                        
					</tr>
				</thead>
				<tbody id="tableData"></tbody>  
			</table>
		</div>
	</div>
</div>
