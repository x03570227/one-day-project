/**
 * Category Object:
 * {
 * 	code:"",
 * 	name:"",
 * 	child:[{Category Object}],
 * 	parentCode:""
 * }
 * */

define(function(){
	var prop={};
	
	prop["status"]=[{
		code:"0",
		name:"新订单"
	},{
		code:"-1",
		name:"已完成"
	}];
	
	prop["cacheMap"] = {};
	
	
	
	prop["getName"]=function (cat, code, dv){
		cat = cat || "";
		code = code || "";
		
		if(cat=="" || code ==""){
			return dv;
		}
		
		var key=cat+"@"+code;
		var result = this.cacheMap[key]||null;
		
		if(result!=null){
			return result;
		}
		
		result = this.eachCategory(prop[cat], code);
//		result = cat+"@"+result;
		
		this.cacheMap[key]=result;
		
		return result;
	};
	
	prop["eachCategory"] = function (catArr, code){
		var r = null;
		jQuery(catArr).each(function (idx, obj){
			if(obj.code == code){
				r=obj.name;
				return false;
			}
			
			if(typeof obj.child == "object"){
				r=prop.eachCategory(obj.child, code);
				if(r!=null){
					return false;
				}
			}
		});
		return r;
	};
	
	return prop;
});

