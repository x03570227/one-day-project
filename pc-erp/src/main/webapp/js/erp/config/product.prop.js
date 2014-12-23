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
	
	var df={"gyp":"gyp",
			"ly":"ly"};
//	//类别
//	prop["category"]=[{
//		code:"GYP",
//		name:"工艺品、礼品",
//		define:df.gyp,
//		child:[{
//			name:"婚庆用品",
//			code:"GYP-HQ",
//			define:df.gyp,
//			child:[{
//				code:"GYP-HQ-QT",
//				name:"请帖",
//				define:df.gyp
//			},{
//				code:"GYP-HQ-HB",
//				name:"红包",
//				define:df.gyp
//			},{
//				code:"GYP-HQ-XT",
//				name:"喜糖盒",
//				define:df.gyp
//			}]
//		}]
//	}];
	
	prop["category"]=[{
		code:"GYP-HQ-QT",
		name:"工艺品、礼品-婚庆用品-请帖",
		define:df.gyp
	},{
		code:"GYP-HQ-HB",
		name:"工艺品、礼品-婚庆用品-红包",
		define:df.gyp
	},{
		code:"GYP-HQ-XT",
		name:"工艺品、礼品-婚庆用品-喜糖盒",
		define:df.gyp
	},{
		code:"XN-LY-MP",
		name:"虚拟-旅游-门票",
		define:df.ly
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
		
	};
	
	return prop;
});

