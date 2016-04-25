<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<form id="pwdForm" action="sys/userModify.do" method="post"
				class="form-horizontal">
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i> 登录账号
						</span> <input id="item_coachName" type="text" value="${user.userName }"
							name="userName" class="form-control" placeholder="UserName"
							readonly="readonly" /> <input type="hidden" name="id"
							value="${user.id }" />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-eye-close"></i> 修改密码
						</span> <input id="item_password  type=" text" value="" name="password"
							class="form-control" placeholder="Password" />
					</div>
				</div>
				<div class="form-group">
					<button type="button" class="btn btn-primary"
						onclick="valid('#pwdForm',save)">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal"
						aria-hidden="true">关闭</button>
				</div>
			</form>
		</div>
	</div>
</div>

