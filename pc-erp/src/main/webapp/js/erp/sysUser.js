define(		["jquery","util/table","messenger"],
	function(jQuery,  table,       messenger){
		
		var message=Messenger();
	
		var def={table:table, message:message, form:{}};
		
		
		table["preBuildTable"] = function(p){
			if(p.records == null){
				return p;
			}
			
			jQuery.each(p.records, function (idx, obj){
				obj.accountWithoutClassify=obj.account.substring(2, obj.account.length);
			});
			
			return p;
		};
		
		table["afterBuildTable"] = function(){
			
		};
		
		def.form["save"]=function(url, form, cb){
			jQuery.post(url, form.serialize(), function(resp){
				cb(resp);
			}, "json");
		};
		
		
		return def;
	}
);