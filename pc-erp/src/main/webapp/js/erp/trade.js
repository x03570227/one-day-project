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
			});
			
			return p;
		};
		
		table["afterBuildTable"] = function(){
			
		};
		
		return def;
	}
);