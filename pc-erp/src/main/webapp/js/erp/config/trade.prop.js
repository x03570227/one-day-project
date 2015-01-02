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
	}
	
	return prop;
});

