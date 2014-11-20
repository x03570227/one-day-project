define([     "util/form","util/dt"],
	function(form,       dt){
	
	var pdefine={};
	
	pdefine["common"]=[{
		"id":"comm_price_image",
		"label":"主图",
		"placeholder":"主图URL（完整地址）",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.image,
		"multiValue":false,
		"defaultValue":""
	},{
		"id":"comm_price_sale",
		"label":"销售价格",
		"placeholder":"销售价格（阿里巴巴）",
		"dataType":dt.decimal,
		"searchAble":false,
		"formItem":form.text,
		"multiValue":false,
		"defaultValue":"0"
	},{
		"id":"comm_stock",
		"label":"库存数量",
		"placeholder":"库存数量",
		"dataType":dt.integer,
		"searchAble":false,
		"formItem":form.text,
		"multiValue":false,
		"defaultValue":"0"
	},{
		"id":"comm_size_length",
		"label":"长度",
		"placeholder":"请填写长度（例：10）",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.text,
		"multiValue":false,
		"defaultValue":"0"
	},{
		"id":"comm_size_width",
		"label":"宽度",
		"placeholder":"请填写长度（例：10）",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.text,
		"multiValue":false,
		"defaultValue":"0"
	},{
		"id":"comm_size_unit",
		"label":"单位",
		"placeholder":"尺寸单位（例：cm）",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.text,
		"multiValue":false,
		"defaultValue":"CM"
	},{
		"id":"comm_size_k",
		"label":"纸张大小（K）",
		"placeholder":"纸张大小（例：8K）",
		"dataType":dt.integer,
		"searchAble":false,
		"formItem":form.text,
		"multiValue":false,
		"defaultValue":"8"
	},{
		"id":"comm_tuan",
		"label":"图案",
		"placeholder":"图案",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.text,
		"multiValue":false
	}
//	,{
//		"id":"c1",
//		"label":"纸质",
//		"placeholder":"请填写信息",
//		"dataType":dt.array,
//		"dataList":[{
//			"key":0,
//			"value":"普通纸"
//		},{
//			"key":1,
//			"value":"普通纸22"
//		}],
//		"searchAble":false,
//		"formItem":form.radio,
//		"multiValue":false
//	}
	
	];
	
	pdefine["HB"]=[];
	
	pdefine["XT"]=[];
	
	pdefine["QT"]=[{
		"id":"qt_paper_name",
		"label":"纸张名称",
		"dataType":dt.array,
		"searchAble":false,
		"formItem":form.radio,
		"multiValue":false,
		"dataList":[{
			"key":0,
			"value":"卡纸"
		}],
		"defaultValue":0
	},{
		"id":"qt_paper_color",
		"label":"纸张颜色",
		"placeholder":"纸张颜色",
		"dataType":dt.array,
		"searchAble":false,
		"formItem":form.radio,
		"multiValue":false,
		"dataList":[{
			"key":0,
			"value":"荧光红"
		}],
		"defaultValue":0
	},{
		"id":"qt_paper_weight",
		"label":"纸张重量",
		"placeholder":"纸张重量",
		"dataType":dt.array,
		"searchAble":false,
		"formItem":form.radio,
		"multiValue":false,
		"dataList":[{
			"key":250,
			"value":"250克"
		}],
		"defaultValue":250
	},{
		"id":"qt_paper_type",
		"label":"纸张类型",
		"placeholder":"纸张类型",
		"dataType":dt.array,
		"searchAble":false,
		"formItem":form.radio,
		"multiValue":false,
		"dataList":[{
			"key":0,
			"value":"普通"
		},{
			"key":1,
			"value":"厚板"
		}],
		"defaultValue":0
	},{
		"id":"qt_paper_technics",
		"label":"特殊工艺",
		"placeholder":"特殊工艺",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.textArea,
		"multiValue":false
	},{
		"id":"qt_with_inner_paper",
		"label":"是否含内页",
		"placeholder":"",
		"dataType":dt.array,
		"searchAble":false,
		"formItem":form.radio,
		"dataList":[{
			"key":"Y",
			"value":"有"
		},{
			"key":"N",
			"value":"无"
		}],
		"multiValue":false,
		"defaultValue":"Y"
	},{
		"id":"qt_inner_paper_name",
		"label":"内页纸张名称",
		"placeholder":"内页纸张名称",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.radio,
		"dataList":[{
			"key":0,
			"value":"道林纸"
		}],
		"multiValue":false,
		"defaultValue":0
	},{
		"id":"qt_inner_paper_color",
		"label":"内页纸张颜色",
		"placeholder":"纸张颜色",
		"dataType":dt.array,
		"searchAble":false,
		"formItem":form.radio,
		"multiValue":false,
		"dataList":[{
			"key":0,
			"value":"金黄"
		},{
			"key":1,
			"value":"荧光红"
		}],
		"defaultValue":0
	},{
		"id":"qt_inner_paper_weight",
		"label":"内页纸张重量",
		"placeholder":"内页纸张重量",
		"dataType":dt.array,
		"searchAble":false,
		"formItem":form.radio,
		"multiValue":false,
		"dataList":[{
			"key":90,
			"value":"90克"
		},{
			"key":120,
			"value":"120克"
		}],
		"defaultValue":90
	},{
		"id":"qt_inner_paper_technics",
		"label":"内页工艺",
		"placeholder":"内页工艺",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.textArea,
		"multiValue":false
	},{
		"id":"qt_addon_liushu",
		"label":"流苏",
		"placeholder":"",
		"dataType":dt.array,
		"searchAble":false,
		"formItem":form.radio,
		"dataList":[{
			"key":"Y",
			"value":"有"
		},{
			"key":"N",
			"value":"无"
		}],
		"multiValue":false,
		"defaultValue":"Y"
	},{
		"id":"qt_addon_diaozhui",
		"label":"吊坠",
		"placeholder":"",
		"dataType":dt.array,
		"searchAble":false,
		"formItem":form.radio,
		"dataList":[{
			"key":"Y",
			"value":"有"
		},{
			"key":"N",
			"value":"无"
		}],
		"multiValue":false,
		"defaultValue":"Y"
	}];
	
	
	return pdefine;
});