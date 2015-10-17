/**
 * Product 模块相关的操作
 * */
define(		["jquery","template","js/app/i18n_zh_CN","product/prop","noty"],
	function(jQuery,  template,  i18n,               prop){
		
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
				{"productId":pid}, function(resp){
					
				var tplData = {"groups":resp};
				var html=template("tpl_group_list", tplData);
				
				jQuery(renderTo).html(html);
				
			}, "json");
			
		}
		
		def["renderItems"]=function(groupId, renderTo){
			
			jQuery.post(CONTEXT_PATH+"/product/group/queryItems.do", 
				{"groupId":groupId},
				function(resp){
					
				jQuery.each(resp, function (idx, obj){
					obj.categoryName=prop.getName("category", obj.categoryCode, "Error Category");
//					obj.picturePath=STATIC_UPLOAD+"/erp/product/"+obj.code;
				});
				
				var html=template("tpl_group_item_list", {records:resp, groupId:groupId});
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
				var groupId=jQuery(this).attr("model-group-id");
				
				var btn=jQuery(this);
				
				def.leave(productId, groupId, function(){
					btn.parent().parent().hide();
				});
			});
			
		}
		
		return def;
	}
);