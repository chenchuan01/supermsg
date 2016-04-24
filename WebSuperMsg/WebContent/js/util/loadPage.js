/**
 * 空值占位符
 */
var NONE_VAL_FLAG = '';
/**
 * 值间占位
 */
var VAL_FLAG='-';
/**
 * 属性中从属属性分割符‘.’
 */
var FILED_SPLIT = '.';
/**
 * 多个属性分割符‘,’
 */
var FILEDS_SPLIT = ',';
/**
 * 配置多个值之间分隔符‘;’
 */
var CONFIG_VAL_SPLIT = ';';
/**
 * 配置映射分割符‘:’
 */
var CONFIG_TO_SPLIT = ':';
/**loadPage*/
$(function(){
	loadPage();
});
var loadPage=function(){
	initOther();
};
var initOther=function(){
	/**清空表单*/
	cleanForm();
    /**bootstrap mual init*/
    bootstrapInit();
    
    navsetActive();
};
var selectUserCars=function(){
	var option = '<option value="#car_id">#car_plate</option>';
	$('#selectUser').change(function(){
		var userId=$(this).val();
		ajaxData('car/myCarInfos.do',{custom_id:userId},function(data){
			var options = '';
			for(var i=0;i<data.length;i++){
				var car = data[i];
				options=options+option.replace("#car_id", car.id).replace("#car_plate", car.platenum);
				$('#selectCar').html(options);
			}
		});
	});
};
var saveInsurTips=function(formId){
	var params = getParams(formId);
	ajaxData(getUrl(formId),params,function(data){
		$('#formModal').modal('hide');
		cfm('保存修改成功!',function(){
			$('#msgModal').modal('hide');
			pageView('home/insurTips.do?admin_id='+sysuerId);
		});
	});
};
var saveCancelPass=function(formId){
	var params = getParams(formId);
	ajaxData(getUrl(formId),params,function(data){
		$('#formModal').modal('hide');
		cfm('保存修改成功!',function(){
			$('#msgModal').modal('hide');
			pageView('home/cancelCheck.do?admin_id='+sysuerId);
		});
	});
};
var saveInsurChange=function(formId){
	var params = getParams(formId);
	ajaxData(getUrl(formId),params,function(data){
		$('#formModal').modal('hide');
		cfm('保存修改成功!',function(){
			$('#msgModal').modal('hide');
			pageView('home/insurChange.do?admin_id='+sysuerId);
		});
	});
};
var saveCarInfo=function(formId){
	var params = getParams(formId);
	ajaxData(getUrl(formId),params,function(data){
		$('#formModal').modal('hide');
		cfm('保存修改成功!',function(){
			$('#msgModal').modal('hide');
			loadMyCarInfos(sysuerId);
		});
	});
};
var saveCancel=function(formId){
	var params = getParams(formId);
	ajaxData(getUrl(formId),params,function(data){
		$('#formModal').modal('hide');
		cfm('保存修改成功!',function(){
			$('#msgModal').modal('hide');
			pageView('cancel/cancelInsurList.do?custom_id='+sysuerId);
		});
	});
};
var payInsur=function(userId,carId,typeId){
	var params={
		user_id:userId,
		car_id:carId,
		type_id:typeId
	};
	ajaxData('insur/payInsur.do',params,function(){
		closeMsg();
		cfm('购买保险成功！',function(){closeInfo();});
	});
};
var handleInsur=function(user_id,insur_id){
	var params={
		custom_id:user_id,
		carInfo_id:$('#myCarInfos').val(),
		type_id:insur_id
	};
	win('购买保险', 'insur/handleInsur.do?custom_id='+params.custom_id
			+'&carInfo_id='+params.carInfo_id
			+'&type_id='+params.type_id);
};
/**
 * 加载查询我的车辆
 */
var loadMyCarInfos=function(user_id){
	ajaxData('car/myCarInfos.do',{custom_id:user_id},
			function(data){
		var option = "<option value='#carId'>#carPlate</option>";
		var optsHtml = "";
		if(data&&data.length>0){
			for(var i=0;i<data.length;i++){
				var car = data[i];
				optsHtml = optsHtml+option.replace('#carId', car.id).replace('#carPlate', car.platenum);
			}
			$('#myCarInfos').html(optsHtml);
		}
	});
};
var navsetActive=function(){
	$('.nav li').click(function(){
		$('.nav li').removeClass('active');
		$(this).addClass('active');
	});
};
var cleanForm=function(){
	$('.cancel').click(function(){
		$("textarea,select").each(
			function(){$(this).val('');});
		$("input[name]").each(function(){
			if($(this).attr('type')=='text'||
					$(this).attr('type')=='hidden'){
				$(this).val('');
			}
			
		});
		refresh();
	});
};
var bootstrapInit=function(){
	 $('[data-toggle="popover"]').popover();
	 $('[data-toggle="tooltip"]').tooltip();
	 $('#formModal').on('hidden.bs.modal', function (e) {
		 $('#formModal'+' .modal-title').text("");
		 $('#formModal'+' .modal-body').html("");
	 });
};

/*************分页封装***************/
var pageHtml='<li  id="page_%page%" class="pages"><a href="javascript:;" onclick="page(%page%)">%page%</a></li>';
function getPageUrl(){
	return $('#search').attr('action');
}
function getTotal(){
	return parseInt($('#totalPages').text());
};
function setTotal(pages){
	$('#totalPages').text("");
	$('#totalPages').text(pages);
};
function getPage(){
	return parseInt($('#page').text());
};
function setPage(page){
	$('.pages').removeClass('active');
	$('#page').text(page);
	var pageId="#page_"+page;
	$(pageId).addClass('active');
};
var setPageItems=function(itemHtml){
	$('#pagesItems').html(itemHtml);
};
var setTableData=function(dataHtml){
	$('#tableData').html(dataHtml);
	
};
var page=function(num){
	if(getPageUrl()=='undefined'||getParams()=='undefined'){
		return;
	}
	var params = getParams('#search');
	params['page']=num;
	var url = getPageUrl();
	ajaxData(url,params,pageFun);
};
var pageFun=function(data){
	if(data){
		jspLoadPageDatas(data.content);
		loadPagesItem(data.totalPages);
		setTotal(data.totalPages);
		setPage(data.page);
	}else{
		loadPagesItem(0);
		setTotal(0);
		setPage(0);
	}
};
var loadPagesItem=function(total){
	total = total=='undefined'?0:total;
	if(total>0){
		var itemHtml='<li><a href="javascript:;" onclick="prePage()">&lt;&lt;</a></li>';
		for(var i =1;i<=total;i++){
			itemHtml = itemHtml+pageHtml.replace('%page%', i).replace('%page%', i).replace('%page%', i);
		}
		itemHtml = itemHtml+'<li><a href="javascript:;" onclick="nextPage()">&gt;&gt;</a></li>';
		setPageItems(itemHtml);
	};
};
var jspLoadPageDatas=function(data){
	var trStr='<tr class="">%content%</tr>';
	var tdStr='<td item="%item%">%content%</td>';
	var trObj=$('#template');
	var tdObjs=trObj.children('td');
	var trStrs = "";
	//行列遍历生成html字符串
	for(var row=0;row<data.length;row++){
		var jsonData = data[row];
		var tempTr=trStr;
		var tdStrs="";
		for(var col=0;col<tdObjs.length;col++){
			var dataMap = $jsonObjToMap(jsonData);
			var tempTd = tdStr;
			var tdObj = $(tdObjs[col]);
			var item = tdObj.attr('item');
			var content = "";
			if('index'==item){
				content = ''+(row+1);
			}else if('oprea'==item){
				var itemFiled = tdObj.attr('itemFiled');
				var itemId = $getDataFiled(dataMap, itemFiled);
				content = genOpreaHtml($(tdObjs[col]).html(),itemId);
			}else{
				content = $getDataFiled(dataMap,item);
			}
			tempTd = tempTd.replace("%item%", item).replace("%content%", content);
			tdStrs = tdStrs+tempTd;
		}
		tempTr = tempTr.replace('%content%',tdStrs);
		trStrs=trStrs+tempTr;
	}
		
	if(trStrs.indexOf('%content%')<=-1){
		clearTable();
		setTable(trStrs);
	}
	
};

var genOpreaHtml = function(aStrs,itemId){
	var opStrs = aStrs.split('id=');
	var newOpStrs = opStrs[0];
	for(var i=1;i<opStrs.length;i++){
		newOpStrs=newOpStrs+"id="+itemId+opStrs[i].substring(opStrs[i].indexOf('\''),opStrs[i].length);
	}
	return newOpStrs;
};

var clearTable=function(){
	$("#tableData").html('');
};
var setTable=function(trStrs){
	$("#tableData").html(trStrs);
};
var nextPage=function(){
	var total = getTotal();
	var pageNum = getPage()+1;
	if(pageNum>total){
		return;
	}
	page(pageNum);
};

var prePage=function(){
	var pageNum = getPage()-1;
	if(pageNum<1){
		return;
	}
	page(pageNum);
};
var fristPage=function(){
	page(1);
};
var lastPage=function(){
	page(getTotal());
};
var refresh=function(){
	var formFileds = $('#search input[name],#search select[name]');
	if(formFileds){
		formFileds.each(function(){$(this).val('');});
		page(1);
	}
};