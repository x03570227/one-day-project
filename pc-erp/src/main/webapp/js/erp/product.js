/**
 * Product 模块相关的操作
 * */
define(		["jquery","template","product/prop","util/table","messenger","product/define"],
	function(jQuery,  template,  prop,          table,            messenger,  df){
		
		var message=Messenger();
	
		var product={table:table, message:message};
		
		
		table["preBuildTable"] = function(p){
			
			if(p.records == null){
				return p;
			}
			
			jQuery.each(p.records, function (idx, obj){
				obj.categoryName=prop.getName("category", obj.categoryCode, "Error Category");
			});
			
			return p;
		};
		
		table["afterBuildTable"] = function(){
			jQuery("#"+table.config.renderTo+" .act-delete").click(function(){
				var pid= jQuery(this).attr("model-product-id");
				
				product.remove(pid, jQuery(this));
				
			});
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
		
		product["save"] = function(url, form, cb){
			
			jQuery.post(url, form.serialize(), function(resp){
				cb(resp);
			}, "json");
			
		};
		
		product["fillForm"]=function(url, pid, formId, defineContainer){
			
//			var msg = .post({
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
			//XXX define == null 的时候需要给出错误提示
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
			
			jQuery.post(CONTEXT_PATH+"/product/doDelete.do", data, function(resp){
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