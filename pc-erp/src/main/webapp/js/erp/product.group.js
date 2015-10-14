/**
 * Product 模块相关的操作
 * */
define(		["jquery","template","js/app/i18n_zh_CN","noty"],
	function(jQuery,  template,  i18n){
		
		//var message=Messenger();
	
		var def={};
		
		def["join"]=function(){
			
		}
		
		def["leave"]=function(data, btn){
			
			if(!confirm(i18n.get("ACT_REMOVE_CONFIRM"))){
				return false;
			}
			
			jQuery.post(CONTEXT_PATH+"/product/group/doLeave.do", data, function(resp){
				btn.parent().parent().hide();
			}, "json");
			
		}
		
		def["render"]=function(pid, renderTo){
			
			jQuery.post(CONTEXT_PATH+"/product/group/queryByPid.do", 
				{"productId":pid}, 
				function(resp){
				var html=template("tpl_group_list", resp);
				jQuery(renderTo).html(html);
			}, "json");
		}
		
		def["renderItem"]=function(groupId, renderTo){
			
			jQuery.post(CONTEXT_PATH+"/product/group/queryItems.do", 
				{"groupId":groupId}, 
				function(resp){
				var html=template("tpl_group_item_list", resp);
				jQuery(renderTo).html(html);
			}, "json");
		}
		
		def["bindGoupAct"]=function(){
			//TODO 绑定群组操作
		}
		
		def["bindItemAct"]=function(){
			//TODO 绑定产品操作
		}
		
		return def;
	}
);