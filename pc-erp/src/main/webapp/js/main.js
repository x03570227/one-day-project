
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
var STATIC="//s0.caiban.net/";
//var STATIC="";

require.config({
	urlArgs:"v=20150522.12",
	baseUrl:CONTEXT_PATH,
	paths:{
		//基础JS库
//		jquery:"//cdn.bootcss.com/jquery/2.1.1/jquery.min",
		jquery:STATIC+"plugin/jquery/dist/jquery.min",
//		Bootstrap:"//cdn.bootcss.com/bootstrap/3.1.1/js/bootstrap.min",
		Bootstrap:STATIC+"plugin/bootstrap/dist/js/bootstrap.min",
		template:STATIC+"plugin/template",
		menu:STATIC+"plugin/metisMenu/dist/metisMenu.min",
		sbadmin:STATIC+"plugin/startbootstrap-sb-admin-2/dist/js/sb-admin-2",
		//各种插件
		"datetimepicker":STATIC+"plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker",
		"dplocale":STATIC+"plugin/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker."+LOCALE,
		"tagsinput":STATIC+"plugin/bootstrap-tagsinput/bootstrap-tagsinput.min",
		"switch":STATIC+"plugin/bootstrap-switch/js/bootstrap-switch.min",
		"jqValidation":STATIC+"plugin/jquery.extention/jqBootstrapValidation",
		"typeahead":STATIC+"plugin/typeahead/typeahead.jquery.min",
		"hogan":STATIC+"plugin/hogan/hogan-2.0.0.min",
		"messenger":STATIC+"plugin/messenger/build/js/messenger.min",
		"validator":STATIC+"plugin/bootstrap-validator/dist/validator.min",
		"noty":STATIC+"plugin/noty/js/noty/packaged/jquery.noty.packaged.min",
		
		//项目模块
		"util/dt":"js/utils/util.dt",
		"util/form":"js/utils/util.form",
		"util/table/pager":"js/utils/util.table.pager",
		"util/table":"js/utils/util.table",
		"util/cache":"js/utils/util.cache",
		"util/kdtprint":"js/utils/util.kdt.print",
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
		"menu":{deps:["jquery","Bootstrap"]},
		"sbadmin":{deps:["jquery","menu"]},
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
		},
		"noty":{
			deps:["jquery"]
		}
	}
});

require([    "jquery","Bootstrap","js/app/i18n_zh_CN",
             "menu","sbadmin","noty"],
	function(jQuery,  bootstrap,   i18n){
		
		$(document).ajaxError(function(event, request, settings) {
			if(request.status == 500){
//				var errorCode = request.getResponseHeader("CB_ERROR");
				var errorText = request.getResponseHeader("CB_ERROR");
				noty({
					type:"error",
					text: i18n.get("SERVER_ERROR")+":"+decodeURIComponent(errorText),
					timeout:2500
				});
			}else if(request.status > 500){
				noty({
					type:"error",
					text: request.statusText,
					timeout:2500
				});
			}else if(request.status!=200){
				noty({
					type:"error",
					text: "Server Error["+request.status+"]:"+request.statusText,
					timeout:2500
				});
			}
		});
		
	}	
);

Date.prototype.format = function(format) {

	var o = {
		"M+" : this.getMonth() + 1, //month 
		"d+" : this.getDate(), //day 
		"h+" : this.getHours(), //hour 
		"m+" : this.getMinutes(), //minute 
		"s+" : this.getSeconds(), //second 
		"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter 
		"S" : this.getMilliseconds()
	//millisecond 
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}

	return format;
}
