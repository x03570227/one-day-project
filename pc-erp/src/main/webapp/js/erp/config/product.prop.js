/**
 * Category Object:
 * {
 * 	code:"",
 * 	name:"",
 * 	child:[{Category Object}],
 * 	parentCode:""
 * }
 * */
//按产品品类分
//按销售范围划分
//按材质划分

define(function(){
	var prop={};
	
	//类别
	prop["category"]=[{
		code:"QT",
		name:"请帖"
//		child:[{
//			code:"HB",
//			name:"子红包",
//			parentCode:"QT"
//		}]
	},{
		code:"HB",
		name:"红包"
	},{
		code:"XT",
		name:"喜糖盒"
	}];
	
	//价格单位
	prop["priceUnit"]=[{
		code:"G",
		name:"个"
	},{
		code:"X",
		name:"箱"
	},{
		code:"H",
		name:"盒"
	}];
	
	//状态
	prop["statusLife"]=[{
		code:"DRAFT",
		name:"草稿"
	},{
		code:"SALING",
		name:"在售"
	},{
		code:"SHELVES",
		name:"下架"
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
		
	}
	
	return prop;
});