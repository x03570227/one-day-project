define(		["jquery", "template"],
	function(jQuery,    template){
		
		var def={};
		
		def["target"]=function(formid){

            jQuery("#"+formid).on("blur", "input[data-validate]", function(){
                var ruleStr = jQuery(this).attr("data-validate");
                var rule = JSON.parse(ruleStr);
                var value = jQuery(this).val();

                if(typeof rule["require"] != "undefined" && rule["require"]){
                    if(value==""){
                        def.addCellWarn(jQuery(this).parent().parent());
                        def.addCellFt(jQuery(this).parent().parent());
                    }
                }
//            weui_btn_disabled
            });
        }

        def["addCellWarn"]=function(obj){
            if(obj.hasClass("weui_cell_warn")){
                return ;
            }
            obj.addClass("weui_cell_warn");
        }

        def["addCellFt"]=function(obj){
            var children = obj.children(".weui_cell_ft");

            if(children.length>0){
                return ;
            }

            var source ="<div class=\"weui_cell_ft\">"+
                "<i class=\"weui_icon_warn\"></i>"+
                "</div>";
            var render = template.compile(source);

            obj.append(render);
        }

		return def;
	}
);