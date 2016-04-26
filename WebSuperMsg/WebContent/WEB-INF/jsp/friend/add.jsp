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
			<form id="search" action="friend/friendPage" method="post" class="form-inline">
				  <div class="form-group">
				    <label class="sr-only" for="frined_name">昵称</label>
				    <input type="text" class="form-control" id="frined_name" name="name" placeholder="好友昵称">
				  </div>
				  <button class="btn btn-primary" type="button" onclick="page(1)"><i class="glyphicon glyphicon-search"></i></button>
			</form>
			<table class="table table-hover">
				<tr id="template" style="display: none;">
					<td item="index"></td>
					<td item="name"></td>
					<td item="userName"></td>
					<td item="oprea" itemFiled="id">
						<a href="javascript:;" title="添加好友" onclick="addFriend('friend/append?host=${sysuser.id}&friend_id=')" class="btn btn-primary btn-sm">
						<i class=" glyphicon glyphicon-plus"></i>
						</a>
					</td>
				</tr>
				<thead>
					<tr>
						<th>#</th>
						<th>昵称</th>
						<th>账号</th>
						<th>操作</th>                                        
					</tr>
				</thead>
				<tbody id="tableData"></tbody>  
			</table>
		</div>
	</div>
</div>
