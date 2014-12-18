define(		["jquery","util/table","messenger"],
	function(jQuery,  table,       messenger){
		
		var message=Messenger();
	
		var def={table:table, message:message};
		
		
		table["preBuildTable"] = function(p){
			return p;
		};
		
		table["afterBuildTable"] = function(){
			
		};
		
		
		return def;
	}
);