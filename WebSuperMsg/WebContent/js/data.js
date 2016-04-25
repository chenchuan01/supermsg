DATA={
	app:{//桌面1
		'baidu':{
			appid:'1',
			icon:'baidu.png',
			name:'百度一下',
			asc :1
		}
	},
	sApp:{//侧边栏应用
		'chats':{
			appid:'1',
			icon:'chats.png',
			name:'聊天好友',
			asc :1,
			url:'home/selectChat.do?user_id=#id',
			width:500,
			height:570
				
		},
		'friends':{
			appid:'2',
			icon:'friends.png',
			name:'好友列表',
			asc :1,
			url:'home/myFriends.do?user_id=#id',
			width:350,
			height:670
		},
		'plusup':{
			appid:'3',
			icon:'plusup.png',
			name:'添加好友',
			asc :1,
			url :'home/addFriend.do?user_id=#id',
			width:350,
			height:670
		}
	}
};
