define(		["jquery","util/table","messenger"],
	function(jQuery,  table,       messenger){
		
		var message=Messenger();
	
		var def={table:table, message:message, form:{}};
		
		
		table["preBuildTable"] = function(p){
			if(p.records == null){
				return p;
			}
			
			jQuery.each(p.records, function (idx, obj){
				obj.accountWithoutClassify=obj.account.substring(2, obj.account.length);
			});
			
			return p;
		};
		
		table["afterBuildTable"] = function(){
			
		};
		
		def.form["save"]=function(url, form, cb){
			jQuery.post(url, form.serialize(), function(resp){
				cb(resp);
			}, "json");
		};
		
		def["resetPwd"]=function(uid, url, cb){
			jQuery.post(url, {"uid":uid}, function(resp){
				if(resp.result){
					var msg = users.message.post({
        				message:resp.data,
        				showCloseButton:true,
        				type:"success",
        				hideAfter:60,
        				actions:{
        					copy:{
        						label:"Copy",
        						action:function(){
        							//TODO copy to browser
									msg.cancel();
        						}
        					}
        				}
        			});
				}
			}, "json");
		};
		
		return def;
	}
);