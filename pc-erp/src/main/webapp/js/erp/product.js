/**
 * Product 模块相关的操作
 * */
define(		["jquery","template","product/prop","util/table","messenger","product/define"],
	function(jQuery,  template,  prop,          tb,          messenger,  df){
	
		var product={};
	
		product["search"] = function(searchCond){
			
			searchCond=searchCond||{};
			
			this.searchCond=searchCond;
			
			jQuery.post(this.config.url, this.buildSubmit(searchCond), this.buildHtml, "json");
			
		};
		
		product["init"] = function(config){
			config = config||{};
			this.config=config;
			if(this.config.pager==null){
				this.config.pager={};
			}
		};
		
		product["buildSubmit"]=function(searchCond){
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
		
		product["buildHtml"]=function(p){
			
			p.records = product.preBuildHtml(p);
			
			var table = template(product.config.tpl.table, p);
			var pagerBar = tb.pageBar(p);
			
			jQuery("#"+product.config.renderTo).empty();
			
			jQuery("#"+product.config.renderTo).html(table);
			jQuery("#"+product.config.renderTo).append(pagerBar);
			
			//绑定分页导航事件
			tb.doPage(product.config.renderTo, function(){
				var start = jQuery(this).attr("page-start")||0;
				product.setStart(start);
				product.search(product.searchCond);
			});
			
		};
		
		product["preBuildHtml"] = function(p){
			if(p.records == null){
				return p;
			}
			
			jQuery.each(p.records, function (idx, obj){
				obj.categoryName=prop.getName("category", obj.categoryCode, "红包");
			});
			
			console.log(p.records);
			
			return p.records;
		}
		
		product["setOrder"]=function(sort, desc){
			var pager = this.config.pager||{};
			pager["sort"]=sort;
			pager["desc"]=desc;
			this.config.pager = pager;
		};
		
		product["setStart"]=function(start){
			var pager = this.config.pager||{};
			pager["start"]=start;
			this.config.pager = pager;
		};
		
		product["setLimit"]=function(limit){
			var pager = this.config.pager||{};
			pager["limit"]=limit;
			this.config.pager = pager;
		};
		
		product["compileOption"] = function (type, selected){
			var source ="{{each "+type+" as c idx}}"+ 
				"<option value='{{c.code}}' {{if c.code == '"+selected+"'}} selected {{/if}}>{{c.name}}</option>"+
				"{{/each}}";
			var render = template.compile(source);
			return render(prop);
		};
		
		product["initDefineForm"]=function(type, dv){
			var html = "";
			
			jQuery.each(df[type], function (idx, obj){
				
				html += template(obj.formItem.tpl, obj);
			});
			
			return html;
		};
		
		product["buildDefine"] = function(types){
			//build define
			
			var result = {};
			jQuery.each(types, function(idx, category){
				
				jQuery.each(df[category], function(i, o){
					
					if(typeof o.formItem.getObj(o.id) !="undefined"){
						result[o.id] = o.formItem.getObj(o.id);
//						console.log("OBJ:"+JSON.stringify(o.formItem.getObj(o.id)));
					}
				});
				
			});
			
			debugger;
			
			console.log(JSON.stringify(result));
			return JSON.stringify(result);
		}
		
		product["save"] = function(url, form){
			//post form
			
//			jQuery.post(url, form.serialize(), function(result`){
//				
//			}, "json");
		}
		
		return product;
	}
);