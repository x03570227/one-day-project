
console.log('Global Config: Context Path-> ',CONTEXT_PATH)
console.log('Global Config: Locale-> ',LOCALE)

//require.config({
//	baseUrl:CONTEXT_PATH,
//	paths:{
//		jquery:"//cdn.bootcss.com/jquery/2.1.1/jquery.min",
//		Bootstrap:"//cdn.bootcss.com/bootstrap/3.1.1/js/bootstrap.min"
//	}
//});
var STATIC_UPLOAD="http://su.caiban.net";
var STATIC="http://s.caiban.net";

require.config({
	urlArgs:"v=20141223.4",
	baseUrl:CONTEXT_PATH,
	paths:{
		//基础JS库
//		jquery:"//cdn.bootcss.com/jquery/2.1.1/jquery.min",
		jquery:"js/jquery.min",
//		Bootstrap:"//cdn.bootcss.com/bootstrap/3.1.1/js/bootstrap.min",
		Bootstrap:"plugin/bootstrap/js/bootstrap.min",
		
		template:"plugin/template",
		
		//各种插件
		"datetimepicker":"plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker",
		"dplocale":"plugin/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker."+LOCALE,
		
		"tagsinput":"plugin/bootstrap-tagsinput/bootstrap-tagsinput.min",
		"switch":"plugin/bootstrap-switch/js/bootstrap-switch.min",
		"jqValidation":"plugin/jquery.extention/jqBootstrapValidation",
		"typeahead":"plugin/typeahead/typeahead.jquery.min",
		"hogan":"plugin/hogan/hogan-2.0.0.min",
		"messenger":"plugin/messenger/build/js/messenger.min",
		"validator":"plugin/bootstrap-validator/dist/validator.min",
		
		//项目模块
		"util/dt":"js/utils/util.dt",
		"util/form":"js/utils/util.form",
		"util/table/pager":"js/utils/util.table.pager",
		"util/table":"js/utils/util.table",
		"util/cache":"js/utils/util.cache",
		"product/prop":"js/erp/config/product.prop",
		"product/define":"js/erp/config/product.define",
		"trade/prop":"js/erp/config/trade.prop",
		
		"trade":"js/erp/trade",
		
		"product":"js/erp/product",
		"sysUser":"js/erp/sysUser",
		
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
		"hogan": {exports: "Hogan"},
		"messenger":{
			exports:"messenger",
			deps:["jquery"]
		},
		"validator":{
			deps:["jquery"]
		}
	}
});

require([    "jquery","Bootstrap", "messenger"],
	function(jQuery,  bootstrap,   messenger){
		
		Messenger.options = {
			extraClasses : 'messenger-fixed messenger-on-top',
			theme : 'block'
		};
		
	}	
);
