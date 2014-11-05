define([     "util/form","util/dt"],
	function(form,       dt){
	
	var pdefine={};
	
	pdefine["common"]=[{
		"id":"c0",
		"label":"文本标签",
		"placeholder":"请填写信息",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.text,
		"multiValue":false
	},{
		"id":"c1",
		"label":"纸质",
		"placeholder":"请填写信息",
		"dataType":dt.array,
		"dataList":[{
			"key":0,
			"value":"普通纸"
		},{
			"key":1,
			"value":"普通纸22"
		}],
		"searchAble":false,
		"formItem":form.radio,
		"multiValue":false
	},{
		"id":"c2",
		"label":"其他信息",
		"placeholder":"请填写信息",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.checkbox,
		"dataList":[{
			"key":0,
			"value":"普通纸"
		},{
			"key":1,
			"value":"普通纸22"
		}],
		"multiValue":false
	},{
		"id":"c3",
		"label":"长文本",
		"placeholder":"请填写信息",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.textArea,
		"multiValue":false
	},{
		"id":"c4",
		"label":"长文本",
		"placeholder":"请填写信息",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.select,
		"dataList":[{
			"key":0,
			"value":"普通纸"
		},{
			"key":1,
			"value":"普通纸22"
		}],
		"multiValue":false
	}];
	
	pdefine["HB"]=[{
		"id":"c5",
		"label":"长文本",
		"placeholder":"请填写信息",
		"dataType":dt.string,
		"searchAble":false,
		"formItem":form.select,
		"dataList":[{
			"key":0,
			"value":"普通纸"
		},{
			"key":1,
			"value":"普通纸22"
		}],
		"multiValue":false
	}];
	
	pdefine["XT"]=[];
	
	pdefine["QT"]=[];
	
	
	return pdefine;
});