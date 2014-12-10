define([      "template","jquery"],
	function(template,   jQuery){
		var table={};
		
		var tpl="tpl_pager";
		
		table["pageBar"]=function(pager){
			var pageConfig = {};
			
			pageConfig.start=pager.start||0;
			pageConfig.limit=pager.limit||20;
			pageConfig.totals=pager.totals||0;
			
			var pre=0;
			var next=0;
			var nav=new Array();
			
			var totalPage=parseInt((pageConfig.totals-1)/pageConfig.limit)+1;
//			console.log("totalPage:"+totalPage);
			pageConfig["totalPage"]=totalPage;
			
			var currentPage=parseInt(pageConfig.start/pageConfig.limit)+1;
//			console.log("currentPage:"+currentPage);
			pageConfig["currentPage"]=currentPage;
			
			next = pageConfig.start+pageConfig.limit;
			next = next >= pageConfig.totals ? (totalPage-1)*pageConfig.limit:next;
//			console.log("next:"+next);
			pageConfig["next"]=next;
			
			pre = pageConfig.start - pageConfig.limit;
			pre = pre < 0 ? 0:pre;
//			console.log("pre:"+pre);
			pageConfig["pre"]=pre;
			
			//
			var bl=13;
			
			var begin = currentPage - (bl-1)/2 ;
			begin = begin <1?1:begin;
			var end = begin+ bl;
			end= end > totalPage ? totalPage :end;
			
//			console.log("begin:"+begin+"  end:"+end);
			
			pageConfig["begin"]=begin;
			pageConfig["end"]=end;
			
			if(begin==end){
				nav.push({
					page:1,
					start:0
				});
			}else{
				for(var i=begin; i<=end; i++){
					nav.push({
						page:i,
						start:(i-1)*pageConfig.limit
					});
				}
			}
			
			
//			console.log(nav);
			pageConfig["nav"]=nav;
			
			var html = template(tpl, pageConfig);
			
			return html;
		};
		
		table["doPage"]=function(renderTo, e){
			jQuery("#"+renderTo+" .pagination li a").click(function(){
				e(jQuery(this));
			});
		}
		
		return table;
	}
);