/**
 * Product 模块相关的操作
 * */
define(		["jquery","template","trade/prop","util/table","messenger"],
	function(jQuery,  template,  prop,        table,      messenger){
		
		var message=Messenger();
	
		var def={table:table, message:message};
		
		
		table["preBuildTable"] = function(p){
			
			if(p.records == null){
				return p;
			}
			
			jQuery.each(p.records, function (idx, obj){
				obj.statusName=prop.getName("status", ""+obj.trade.status, "Error Category");
				obj.gmtModifiedStr=new Date(obj.trade.gmtModified).format('yyyy-MM-dd hh:mm:ss');
				
				if(obj.trade.sourceDomain="koudaitong.com"){
					try{
						obj.detailsJson=JSON.parse(obj.define.details);
					}catch(e){
						obj.detailsJson={};
					}
				}
			});
			
			return p;
		};
		
		table["afterBuildTable"] = function(p){
			
		};
		
		def["bindRemove"]=function(root){
			jQuery(root).on("click", "td button[data-act=remove]", function(){
				
				if(!confirm("are you sure?")){
					return false;
				}
				
				var _btn=jQuery(this);
				
				var id = _btn.attr("model-trade-id");
				
				jQuery.post(CONTEXT_PATH+"/trade/doDelete.do",{"id":id}, function(resp){
					_btn.parent().parent().fadeOut();
    			}, "json");
				
			});
		};
		
		def["bindToggle"]=function(root){
			jQuery(root).on("click", "td[data-act=toggle-details]", function(){
				
				var id=jQuery(this).attr("model-trade-id");
				
				if(jQuery("#tr-details-"+id).hasClass("hide")){
					jQuery("#tr-details-"+id).removeClass("hide");
				}
				
				if(jQuery(this).hasClass("dropup")){
					jQuery("#tr-details-"+id).hide();
					jQuery(this).removeClass("dropup");
					jQuery(this).parent().removeClass("info");
				}else{
					jQuery("#tr-details-"+id).show();
					jQuery(this).addClass("dropup");
					jQuery(this).parent().addClass("info");
				}
			});
		}
		
		return def;
	}
);