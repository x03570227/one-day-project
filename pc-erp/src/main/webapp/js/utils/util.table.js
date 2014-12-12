/**
 * Table
 * */
define(		["jquery","template","util/table/pager"],
	function( jQuery,  template, pg){
	
		var table={};

		table["init"] = function(config){
			config = config||{};
			this.config=config;
			if(this.config.pager==null){
				this.config.pager={};
			}
		};
		
		table["search"] = function(searchCond){
			
			searchCond=searchCond||{};
			
			this.searchCond=searchCond;
			
			jQuery.post(this.config.url, this.buildSubmit(searchCond), this.buildHtml, "json");
			
		};
		
		table["buildSubmit"]=function(searchCond){
			var pager = this.config.pager;
			if(typeof searchCond == "string"){
				jQuery.each(pager,function(k, v){
					searchCond = searchCond+"&"+k+"="+encodeURIComponent(v);
				});
				return searchCond;
			}
			
			jQuery.each(pager,function(k, v){
				searchCond[k]=encodeURIComponent(v);
			});
			
			return searchCond;
		};
		
		table["buildHtml"]=function(p){
			
			p = table.preBuildTable(p);
			
			var tableHtml = template(table.config.tpl.table, p);
			var pagerBar = pg.pageBar(p);
			
			jQuery("#"+table.config.renderTo).empty();
			
			jQuery("#"+table.config.renderTo).html(tableHtml);
			jQuery("#"+table.config.renderTo).append(pagerBar);
			
			//绑定分页导航事件
			pg.doPage(table.config.renderTo, function(item){
				var start = item.attr("page-start");
				start = start||0;
				table.setStart(start);
				table.search(table.searchCond);
			});
			
			table.afterBuildTable();
		};
		
		table["preBuildTable"] = function(p){
			return p;
		};
		
		table["afterBuildTable"] = function(){
			
		};
		
		table["setOrder"]=function(sort, desc){
			var pager = this.config.pager||{};
			pager["sort"]=sort;
			pager["desc"]=desc;
			this.config.pager = pager;
		};
		
		table["setStart"]=function(start){
			var pager = this.config.pager||{};
			pager["start"]=start;
			this.config.pager = pager;
		};
		
		table["setLimit"]=function(limit){
			var pager = this.config.pager||{};
			pager["limit"]=limit;
			this.config.pager = pager;
		};
		
		return table;
	}
);