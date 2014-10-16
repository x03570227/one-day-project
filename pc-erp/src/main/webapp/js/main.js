
console.log('Global Config: Context Path-> ',CONTEXT_PATH)
console.log('Global Config: Locale-> ',LOCALE)

//require.config({
//	baseUrl:CONTEXT_PATH,
//	paths:{
//		jquery:"//cdn.bootcss.com/jquery/2.1.1/jquery.min",
//		Bootstrap:"//cdn.bootcss.com/bootstrap/3.1.1/js/bootstrap.min"
//	}
//});
require.config({
	baseUrl:CONTEXT_PATH,
	paths:{
		//基础JS库
//		jquery:"//cdn.bootcss.com/jquery/2.1.1/jquery.min",
		jquery:"js/jquery.min",
//		Bootstrap:"//cdn.bootcss.com/bootstrap/3.1.1/js/bootstrap.min",
		Bootstrap:"plugin/bootstrap/js/bootstrap.min",
		
		//各种插件
		"datetimepicker":"plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker",
		"dplocale":"plugin/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker."+LOCALE,
		
		"tagsinput":"plugin/bootstrap-tagsinput/bootstrap-tagsinput.min",
		"switch":"plugin/bootstrap-switch/js/bootstrap-switch.min",
		"jqValidation":"plugin/jquery.extention/jqBootstrapValidation",
		"typeahead":"plugin/typeahead/typeahead.jquery.min",
		"hogan":"plugin/hogan/hogan-2.0.0.min",
		
		//项目模块
		
		//项目自定义模块
		"Global":"js/app/globalConfig"
	},
	shim:{
		"Bootstrap":{deps:["jquery"]},
		"jqValidation":{
//			exports:"jqValidation",
			deps:["jquery"]
		},
		"datetimepicker":{
//			exports:"datetimepicker",
			deps:['jquery']
		},
		"dplocale":{
			deps:["jquery","datetimepicker"]
		},
		"typeahead":{deps:['jquery']},
		"tagsinput":{
//			exports:"tagsinput",
			deps:['jquery',"typeahead"]
		},
		"switch":{
//			exports:"switch",
			deps:['jquery']
		},
		"hogan": {exports: "Hogan"}
	}
});

require(["jquery","Bootstrap"]);
