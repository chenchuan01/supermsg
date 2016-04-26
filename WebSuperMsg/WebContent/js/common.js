/**common*/
/**
 * import
 */
var $import = function(file) {
	if (file.match(/.js$/)) {
		document.write('<script type="text/javascript" src="' + file
				+ '"></script>');
	} else {
		document.write('<style type="text/css">@import "' + file
				+ '" ;</style>');
	}
};
/**
 * 禁用按钮
 */
var $btnDisable = function(selector) {
	if (!selector) {
		selector = '.submit';
	}
	$(selector).addClass('disabled');
	$(selector).attr('disabled', 'disabled');

};
var $btnEnable = function(selector) {
	if (!selector) {
		selector = '.submit';
	}
	$(selector).removeClass('disabled');
	$(selector).removeAttr('disabled');
};
function cleanFormById(formId) {
	if (formId) {
		var selector = formId + ' input[name],' + formId + ' textare[name],'
				+ formId + ' select[name]';
		$(selector).each(function() {
			$(this).val('');
		});
	}
}
/**
 * jsonObjToMap
 */
var $jsonObjToMap = function(jsonObj) {
	var map = {};
	for ( var item in jsonObj) {
		map[item] = jsonObj[item];
	}
	return map;
};
/**
 * isMapNull
 */
var $isMapNull = function(dataMap, filed) {
	return (dataMap[filed] == null || dataMap[filed] == '' || dataMap[filed] == 'null')
			&& dataMap[filed] != 0;
};
/**
 * 通过处理item属性值，返回映射数据(包含情况xx;xx.xx;xx,xx.xx)
 */
var $getDataFiled = function(dataMap, filed) {
	var fileds = filed.split(FILEDS_SPLIT);
	if (fileds.length <= 1) {
		return $singledData(dataMap, fileds[0]);
	} else {
		return $multipleData(dataMap, fileds);
	}
};
/**
 * 单个属性，返回数据(xx;xx.xx)
 */
var $singledData = function(dataMap, filed) {
	// single filed

	// xx.xx
	if (filed != null && filed.indexOf(FILED_SPLIT) > 0) {
		var fileds = filed.split(FILED_SPLIT);
		var tempMap = dataMap;
		for (var i = 0; i < fileds.length; i++) {
			var tempfiled = fileds[i];
			if ($isMapNull(tempMap, tempfiled)) {
				return NONE_VAL_FLAG;
			}
			tempMap = $getDataVal(tempMap, tempfiled);
		}
		return tempMap;
	}
	// xx
	else {
		return $isMapNull(dataMap, filed) ? NONE_VAL_FLAG : $getDataVal(
				dataMap, filed);
	}

};
/**
 * 获得属性值
 */
var $getDataVal = function(dataMap, filed) {
	// 特殊处理会员卡类型和赠送会籍单位
	/***************************************************************************
	 * if(filed!=null&&filed!=""){ if(specialFileds.type==filed){ var type =
	 * dataMap[filed]; return g_cardType[type]; }else
	 * if(specialFileds.givemetric==filed){ return g_dateMetric[dataMap[filed]];
	 * }else if(specialFileds.timeindex==filed){ var timeArry = dataMap[filed];
	 * var timeIndexs = ""; for (var int = 0; int < timeArry.length; int++) {
	 * if(int==timeArry.length-1){
	 * timeIndexs=timeIndexs+timeIndexMap[timeArry[int]]; }else{
	 * timeIndexs=timeIndexs+timeIndexMap[timeArry[int]]+","; }
	 *  } return timeIndexs; }else if(specialFileds.weekday==filed){ var
	 * weekArry = dataMap[filed]; var weekDays = ""; for (var int = 0; int <
	 * weekArry.length; int++) { if(int==weekArry.length-1){
	 * weekDays=weekDays+weekDayMap[weekArry[int]]; }else{
	 * weekDays=weekDays+weekDayMap[weekArry[int]]+","; } } return weekDays; } }/
	 **************************************************************************/
	return dataMap[filed];
};
/**
 * 多个属性，返回组合数据(xx+xx;xx.xx+xx.xx)
 */
var $multipleData = function(dataMap, fileds) {
	var infoArray = [];
	for (var index = 0; index < fileds.length; index++) {
		infoArray[index] = $singledData(dataMap, fileds[index]);
	}
	var groupInfo = "";
	for (var index = 0; index < infoArray.length; index++) {
		groupInfo = groupInfo + infoArray[index];
	}
	return groupInfo;
};
/**
 * 根据信息类型,回填信息数据
 */
var commFillInfo = function(dataMap, selector, type) {
	$(selector).each(
			function() {
				var showFiled = $(this);
				var itemFileds = showFiled.attr('item');
				if (type == infoType.val) {
					showFiled.val($getDataFiled(dataMap, itemFileds));
				} else if (type == infoType.text) {
					showFiled.text($getDataFiled(dataMap, itemFileds));
				} else if (type == infoType.src) {
					var orginalSrc = showFiled.attr('src');
					orginalSrc = orginalSrc.replace('#src', $singledData(
							dataMap, itemFileds));
					showFiled.attr('src', orginalSrc);
				}
			});
};
/** 时间日期控件 */
$import(basePath + 'js/dateFormat.js');
/** Ajax 封装 */
$import(basePath + 'js/util/ajax.js');
/** 自定义验证 封装 */
$import(basePath + 'js/util/customValid.js');
/** 对话框、提示框 封装 */
$import(basePath + 'js/util/dialogHintWin.js');
/** 页面初始化 封装 */
$import(basePath + 'js/util/loadPage.js');
/** **********Jump Page************* */
function pageView(url) {
	ajaxHtml(url, {}, setContent, function() {
	});
}
function setContent(html) {
	$('#pageContent').html(html);
	page(1);
	bootstrapInit();
}

function chatWithFriend(url) {
	ajaxData(url, {}, startChat);
}
function startChat(data) {
	closeMsg();
	chat(data[ChatRole.he].name);
	$('#fromName').val(data[ChatRole.me].name);
	$('#toName').val(data[ChatRole.he].name);
}
function send() {
	var chat = {
		msg : $('#talkBox').val(),
		fromName : $('#fromName').val(),
		toName : $('#toName').val(),
		time : new Date().Format("yyyy-MM-dd HH:mm:ss")
	};
	ajaxData(basePath + 'chat/chating', chat, function() {
		callbackShowMsg(ChatRole.me, chat);
		$('#talkBox').val('');
		chatTips(ChatTips.success);
	}, function(e) {
		try {
			var msg = e.msg != 'undifined' ? e.msg : e;
			chatTips(ChatTipes.exp.reolace('#expMsg', msg));
		} catch (e) {
			chatTips(ChatTips.error);
		}

	});

}
function chatTips(tips) {
	$('#chatTips').text(tips);
}
function callbackShowMsg(type, chat) {
	var msg = '<p class="alert alert-#type msg"><b>#user: #time</b><br>#msg</p>';
	var classStyle = 'info';
	var user = '';
	if (type == ChatRole.me) {
		classStyle = 'info';
		user = chat['fromName'];
	} else if (type == ChatRole.he) {
		classStyle = 'success';
		user = chat['toName'];
	}
	msg = msg.replace('#type', classStyle).replace('#time', chat['time'])
			.replace('#msg', chat['msg']).replace('#user', user);
	$('#msgAre').append(msg);
	focusBottom();
}
function focusBottom() {
	var msgAre = document.getElementById('msgAre');
	msgAre.scrollTop = msgAre.scrollHeight;
}
function openChatList() {
	closeChat();
	$('div[type="2"][uid="app_1"]').click();
}
function addFriend(url) {
	ajaxData(url, {}, function() {
		page(1);
	});
}
var friend_del_url = '';
function delFriend(url) {
	friend_del_url = url;
	cfm('有个好友不容易，是不是再考虑考虑？', doDelFrined, closeInfo);
}
function doDelFrined() {
	if ('' != friend_del_url) {
		ajaxData(friend_del_url, {}, function() {
			page(1);
			closeInfo();
			$('div[type="2"][uid="app_2"]').click();
			friend_del_url = '';
		});
	}
}
/** **********Test DATA************* */
var showTest = function() {
	$(".dataTest").toggle();
};
/** **********Parameter DATA************* */
function getParams(formId) {
	var params = {};
	if (formId) {
		$(
				formId + ' input[name],' + formId + ' textarea[name],' + formId
						+ ' select[name]').each(function() {
			var name = $(this).attr('name');
			var value = $(this).val();
			if (name != 'undefined' && value != 'undefined') {
				if ($(this).attr('type') != 'checkbox') {
					params[name] = value;
				}

			}
		});
		$(
				formId + ' input[type="checkbox"]:checked,' + formId
						+ ' input[type="radio"]:checked').each(function() {
			params[$(this).attr('name')] = $(this).val();
		});
	} else {
		$('input[name],textarea[name],select[name]').each(function() {
			var name = $(this).attr('name');
			var value = $(this).val();
			if (name != 'undefined' && value != 'undefined') {
				params[name] = value;
			}
		});
		$('input[type="checkbox"]:checked,input[type="radio"]:checked').each(
				function() {
					params[$(this).attr('name')] = $(this).val();
				});
	}
	return params;
}
/** **********Save DATA************* */
var saveInfo = function(formId, saveFun) {
	valid(formId, saveFun);
};
var doSaveInfo = function() {
	var params = getParams();
	ajaxLoad(getUrl(), params);
};

var save = function(formId) {
	var params = getParams(formId);
	ajaxData(getUrl(formId), params, function() {
		$('#formModal').modal('hide');
		cfm('保存修改成功!', function() {
			$('#msgModal').modal('hide');
			if ($('#search') && $('#search') != null
					&& $('#search') != 'undefined') {
				page(1);
			}
		});
	});
};

/** **********DELETE DATA************* */
var deleteUrl;
var deleteItem = function(url) {
	deleteUrl = url;
	cfm("确认删除此条信息？", doDelete, closeInfo);
};
var doDelete = function() {
	ajaxData(deleteUrl, {}, closeAndRefresh);
};
var closeAndRefresh = function() {
	closeInfo();
	page(1);
};