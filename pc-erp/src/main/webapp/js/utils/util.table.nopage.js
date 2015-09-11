/**
 * Table
 * */
define(		["jquery","template"],
	function( jQuery,  template, pg){
	
		var table={};

		table["init"] = function(config){
			config = config||{};
			this.config=config;
		};
		
		table["search"] = function(searchCond){
			
			searchCond=searchCond||{};
			
			this.searchCond=searchCond;
			
			jQuery.post(this.config.url, searchCond, this.buildHtml, "json");
			
		};
		
		table["buildHtml"]=function(resp){
			
			var p={records:resp};
			
			p = table.preBuildTable(p);
			
			var tableHtml = template(table.config.tpl.table, p);
			
			jQuery("#"+table.config.renderTo).empty();
			
			jQuery("#"+table.config.renderTo).html(tableHtml);
			
			table.afterBuildTable();
		};
		
		table["preBuildTable"] = function(p){
			return p;
		};
		
		table["afterBuildTable"] = function(){
			
		};
		
		table["refresh"]=function(){
			table.search(this.searchCond);
		}
		
		return table;
	}
);