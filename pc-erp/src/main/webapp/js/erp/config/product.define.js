define([     "util/form","util/dt"],
	function(form,       dt){
	
	var pdefine={};
	
	pdefine["common"]=[{
		"id":"comm_url_1688",
		"label":"1688地址",
		"placeholder":"1688对应产品地址（完整地址）",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.text,
		"multiValue":false,
		"defaultValue":""
	},{
		"id":"comm_package",
		"label":"包装",
		"placeholder":"包装",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.textArea,
		"multiValue":false
	},{
		"id":"comm_weight",
		"label":"重量",
		"placeholder":"重量",
		"dataType":dt.decimal,
		"searchAble":false,
		"formItem":form.text,
		"multiValue":false,
		"defaultValue":"0"
	},{
		"id":"comm_size",
		"label":"长度",
		"placeholder":"请填写长度",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.textArea,
		"multiValue":false
	},{
		"id":"comm_image",
		"label":"主图",
		"placeholder":"主图URL（完整地址）",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.image,
		"multiValue":false,
		"defaultValue":""
	}
	];
	
	pdefine["GYP-HQ-HB"]=pdefine["common"].concat([
	{
		"id":"hb_description",
		"label":"红包描述",
		"placeholder":"红包描述",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.textArea,
		"multiValue":false
	}]);
	
	pdefine["GYP-HQ-XT"]=pdefine["common"].concat([
		{
			"id":"hb_description",
			"label":"糖盒描述",
			"placeholder":"糖盒描述",
			"dataType":dt.string,
			"searchAble":false,
			"formItem":form.textArea,
			"multiValue":false
		}]);
	
	pdefine["GYP-HQ-QT"] = pdefine["common"].concat([
		{
			"id":"qt_paper_type",
			"label":"纸张类型",
			"placeholder":"纸张类型",
			"dataType":dt.array,
			"searchAble":false,
			"formItem":form.radio,
			"multiValue":false,
			"dataList":[{
				"key":0,
				"value":"软壳"
			},{
				"key":1,
				"value":"硬壳"
			}],
			"defaultValue":0
		},{
			"id":"qt_paper_technics",
			"label":"外壳描述",
			"placeholder":"外壳描述",
			"dataType":dt.string,
			"searchAble":false,
			"formItem":form.textArea,
			"multiValue":false
		},{
			"id":"qt_inner_paper_technics",
			"label":"内页描述",
			"placeholder":"内页描述",
			"dataType":dt.string,
			"searchAble":false,
			"formItem":form.textArea,
			"multiValue":false
		}]);
	
	pdefine["GYP-HQ-QT-HL"]=pdefine["GYP-HQ-QT"];
	pdefine["GYP-HQ-QT-YQH"]=pdefine["GYP-HQ-QT"];
	pdefine["GYP-HQ-QT-ST"]=pdefine["GYP-HQ-QT"];
	pdefine["GYP-HQ-QT-QQ"]=pdefine["GYP-HQ-QT"];
	pdefine["GYP-HQ-QT-HS"]=pdefine["GYP-HQ-QT"];
	pdefine["GYP-HQ-QT-SR"]=pdefine["GYP-HQ-QT"];
	
	pdefine["GYP-HQ-FJ"]=pdefine["common"].concat([{
		"id":"fj_description",
		"label":"产品描述",
		"placeholder":"产品描述",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.textArea,
		"multiValue":false
	}]);
	
	pdefine["GYP-HQ-FJ-XF"]=pdefine["GYP-HQ-FJ"];
	pdefine["GYP-HQ-FJ-XW"]=pdefine["GYP-HQ-FJ"];
	
	pdefine["XN-LY-MP"] = [{
		"id":"qt_intro",
		"label":"景区介绍",
		"placeholder":"景区介绍",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.textArea,
		"multiValue":false
	}];
	
	return pdefine;
});