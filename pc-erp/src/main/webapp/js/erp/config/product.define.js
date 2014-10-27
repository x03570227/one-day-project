define(["util/form","util/dt"],function(form, dt){
	var pdefine={};
	
	pdefine["common"]=[{
		"id":"c1",
		"label":"纸质",
		"placeholder":"请填写信息",
		"dataType":dt.array,
		"dataList":[{
			"key":0,
			"value":"普通纸"
		},{
			"key":1,
			"value":"普通纸"
		}],
		"searchAble":false,
		"formItem":form.textInput,
		"multiValue":false
	}];
	
	return pdefine;
});