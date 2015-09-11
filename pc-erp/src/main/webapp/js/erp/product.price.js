/**
 * Product 模块相关的操作
 * */
define(		[  "jquery","template","util/table/np","product/prop","js/app/i18n_zh_CN","noty"],
	function(jQuery,  template,    table,         prop,                  i18n){
		
		//var message=Messenger();
	
		var def={table:table, form:{}};
		
		table["preBuildTable"] = function(p){
			
			if(p.records == null){
				return p;
			}
			
			p["priceCode"]=prop.priceCode;
			
			return p;
		};
		
		table["afterBuildTable"] = function(){
			
		};
		
		def.form["save"]=function(url, form, cb){
			jQuery.post(url, form.serialize(), function(resp){
				noty({
					text:i18n.get("FORM_SAVE_SUCCESS"),
					type:"success",
					timeout:2500
				});
				cb(resp);
			}, "json");
		}
		
		def["remove"]=function(data, btn){
			
			if(!confirm(i18n.get("ACT_REMOVE_CONFIRM"))){
				return false;
			}
			
			jQuery.post(CONTEXT_PATH+"/product/price/doDelete.do", data, function(resp){
				btn.parent().parent().hide();
			}, "json");
			
		}
		
		return def;
	}
);