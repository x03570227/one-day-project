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
			var obj={};
			obj["dk"]=id;
			obj["dv"]=this.get(id);
			return obj;
		}
	};
	
	form["radio"]={
		tpl:"tpl_product_form_radio",
		init:function(id, initValue){
			//初始化表单项及具体内容
		},
		get:function(id){
			//获取表单项的值
		},
		getObj:function(id){
			
		}
	};
	
	form["checkbox"]={
		tpl:"tpl_product_form_checkbox",
		init:function(id, initValue){
			//初始化表单项及具体内容
		},
		get:function(id){
			//获取表单项的值
		},
		getObj:function(id){
			
		}
	};
	
	form["textArea"]={
		tpl:"tpl_product_form_text_area",
		init:function(id, initValue){
			//初始化表单项及具体内容
		},
		get:function(id){
			//获取表单项的值
		},
		getObj:function(id){
			
		}
	};
	
	form["select"]={
		tpl:"tpl_product_form_select",
		init:function(id, initValue){
			//初始化表单项及具体内容
		},
		get:function(id){
			//获取表单项的值
		},
		getObj:function(id){
			
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