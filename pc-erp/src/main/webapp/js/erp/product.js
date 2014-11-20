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
			tb.doPage(product.config.renderTo, function(item){
				var start = item.attr("page-start");
				start = start||0;
				product.setStart(start);
				product.search(product.searchCond);
			});
			
			jQuery("#"+product.config.renderTo+" .act-delete").click(function(){
				var pid= jQuery(this).attr("model-product-id");
				
				product.remove(pid, jQuery(this));
				
			});
		};
		
		product["preBuildHtml"] = function(p){
			if(p.records == null){
				return p;
			}
			
			jQuery.each(p.records, function (idx, obj){
				obj.categoryName=prop.getName("category", obj.categoryCode, "Error Category");
			});
			
//			console.log(p.totals);
			
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
		
		product["initDefineForm"]=function(type){
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
					
					var r = o.formItem.getObj(o.id);
					if(typeof r !="undefined"){
						result[o.id] = r;
//						console.log("OBJ:"+JSON.stringify(result[o.id]));
					}
				});
				
			});
			
			console.log(JSON.stringify(result));
			return JSON.stringify(result);
		};
		
		product["save"] = function(url, form){
			
			jQuery.post(url, form.serialize(), function(resp){
				//提示消息
				console.log("保存成功："+JSON.stringify(resp));
			}, "json");
			
		};
		
		product["fillForm"]=function(url, pid, formId, defineContainer){
			
//			var msg = Messenger().post({
//				  message: 'There was an explosion while processing your request.',
//				  type: 'info',
//				  showCloseButton: true
//			});
			
			var data = {id:pid};
			jQuery.post(url, data, function(resp){
				
				var prod = resp.product;
				var define = resp.define;
				
				defineContainer.html(product.initDefineForm(prod.categoryCode));
				
				product.fillBaseForm(prod, formId);
				product.fillDefine(prod.categoryCode, define);
				
			}, "json");
		};
		
		product["fillBaseForm"]=function(p, formId){
			var item = null;
			for(var name in p){
				jQuery("#"+formId+" input[name="+name+"]").val(p[name]);
			}
			jQuery("#"+formId+" select[name=categoryCode]").val(p["categoryCode"]);
			jQuery("#"+formId+" textarea[name=remark]").val(p["remark"]);
		};
		
		product["fillDefine"] = function(categoryCode, define){
			var details = define.details||"{}";
			details = JSON.parse(details);
			categoryCode = categoryCode||"";
			
			var types = new Array();
			types.push("common");
			if(categoryCode !=""){
				types.push(categoryCode);
			}
			
			jQuery.each(types, function(idx, category){
				
				jQuery.each(df[category], function(i, o){
					o.formItem.setObj(o.id, details[o.id]);
				});
				
			});
			
		};
		
		product["remove"] = function(pid, btn){
			
			if(!confirm("are you sure?")){
				return false;
			}
			
			var data = {};
			data["id"]=pid;
			
			jQuery.post(product.config["url-d"], data, function(resp){
				if(resp.result){
					btn.parent().parent().hide();
				}else{
					Message().post({
						msg:"Error: information not removed!",
						type: 'error'
					});
				}
			}, "json");
			
		}
		return product;
	}
);