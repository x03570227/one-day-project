
define({
	error:{
		"ACT_REMOVE_CONFIRM":"确定要删除吗？",
		"ACT_REMOVE_FAILURE":"发生错误，没有成功删除！",
		
		"e.login":"账户异常，请核对后再试！！",
		"e.login.failure":"您的账户或密码不正确，请核对后再试！！",
		"e.login.account.not.exist":"账户不存在，请重试！！",
		"e.login.password.not.confirmed":"密码不正确，请重试！！",
		
		"remote.unavailable":"服务器发生错误"
	},
	get:function(k){
		console.log("Error undefined, key is "+k);
		return this.error[k]||this.error["remote.unavailable"];
	}
});