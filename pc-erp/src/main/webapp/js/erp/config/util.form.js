define([ "jquery"],
function(jQuery){
	var form={};
	
	form["text"]={
		tpl:"tpl_product_form_text",
		set:function(id, initValue){
			jQuery("#"+id).val(initValue);
		},
		get:function(id){
			return jQuery("#"+id).val();
		},
		getObj:function(id){
//			var obj={};
//			obj["dk"]=this.get(id);
//			obj["dv"]=this.get(id);
			return this.get(id);
		}
	};
	
	form["radio"]={
		tpl:"tpl_product_form_radio",
		init:function(id, initValue){
			//TODO 待验证 初始化表单项及具体内容
			jQuery("input[name="+id+"][value='"+initValue+"']").prop("checked",true);
		},
		get:function(id){
			//获取表单项的值
			return jQuery("input[name="+id+"]").val();
		},
		getObj:function(id){
			var obj={};
			obj["dk"]=this.get(id);
			obj["dv"]=jQuery("input[name="+id+"]").attr("dv");
			return obj;
		}
	};
	
	form["checkbox"]={
		tpl:"tpl_product_form_checkbox",
		init:function(id, initValue){
			//初始化表单项及具体内容
		},
		get:function(id){
			//获取表单项的值
			var result = new Array();
			
			jQuery("input[name='"+id+"']:checked").each(function(idx, obj){
				result.push(jQuery(this).val());
			});
			
			return result.join(",");
		},
		getObj:function(id){
			var result = new Array();
			
			jQuery("input[name='"+id+"']:checked").each(function(idx, obj){
				var o={};
				o["dk"]=jQuery(this).val();
				o["dv"]=jQuery(this).attr("dv");
				result.push(o);
			});
			return result;
		}
	};
	
	form["textArea"]={
		tpl:"tpl_product_form_text_area",
		init:function(id, initValue){
			//初始化表单项及具体内容
			jQuery("#"+id).val(initValue);
		},
		get:function(id){
			//获取表单项的值
			return jQuery("#"+id).val();
		},
		getObj:function(id){
//			var obj={};
//			obj["dk"]=id;
//			obj["dv"]=this.get(id);
			return this.get(id);
		}
	};
	
	form["select"]={
		tpl:"tpl_product_form_select",
		init:function(id, initValue){
			//初始化表单项及具体内容
			jQuery("#"+id).val(initValue);
		},
		get:function(id){
			//获取表单项的值
			return jQuery("#"+id).val();
		},
		getObj:function(id){
			var obj={};
			obj["dk"]=this.get(id);
			obj["dv"]=jQuery("#"+id).find("option:selected").text();
			return obj;
		}
	};
	
	form["file"]={
		tpl:"tpl_product_form_file",
		init:function(id, initValue){
			//初始化表单项及具体内容
		},
		get:function(id){
			//获取表单项的值
		},
		getObj:function(id){
			
		}
	};
	
	return form;
});