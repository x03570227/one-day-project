/**
 * Product 模块相关的操作
 * */
define(		["jquery","template","js/app/i18n_zh_CN","noty"],
	function(jQuery,  template,  i18n){
		
		//var message=Messenger();
	
		var def={};
		
		def["join"]=function(pid, data, renderTo){
			
			jQuery.post(CONTEXT_PATH+"/product/group/doJoin.do", data, 
				function(resp){
				
				def.render(pid, renderTo);
				
			}, "json");
		}
		
		def["leave"]=function(pid, groupId, cb){
			
			if(!confirm(i18n.get("ACT_REMOVE_CONFIRM"))){
				return false;
			}
			
			jQuery.post(CONTEXT_PATH+"/product/group/doLeave.do", 
					{"productId":pid, "groupId":groupId}, 
					function(resp){
//				btn.parent().parent().hide();
				cb();
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
		
		def["renderItems"]=function(id, renderTo){
			
			jQuery.post(CONTEXT_PATH+"/product/group/queryItems.do", 
				{"id":id},
				function(resp){
				var html=template("tpl_group_item_list", {records:resp, groupId:id});
				jQuery(renderTo).html(html);
			}, "json");
			
		}
		
		def["bindGroupAct"]=function(container, renderTo){
			
			jQuery(container).on("click", "button[data-act=render_items]", function(){
				var id=jQuery(this).attr("model-id");
				def.renderItems(id, renderTo);
			});
			
			jQuery(container).on("click", "button[data-act=leave_group]", function(){
				var productId=jQuery(this).attr("model-product-id");
				var groupId=jQuery(this).attr("model-id");
				
				var btn=jQuery(this);
				
				def.leave(productId, groupId, function(){
					btn.parent().parent().hide();
				});
				
			});
			
		}
		
		def["bindItemAct"]=function(container){
			
			jQuery(container).on("click", "button[data-act=leave_group]", function(){
				
				var productId=jQuery(this).attr("model-product-id");
				var groupId=jQuery(this).attr("model-id");
				
				var btn=jQuery(this);
				
				def.leave(productId, groupId, function(){
					btn.parent().parent().hide();
				});
			});
			
		}
		
		return def;
	}
);