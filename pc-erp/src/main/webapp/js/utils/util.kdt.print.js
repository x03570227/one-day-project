define(["jquery"],
		function( jQuery){
		
		var LODOP=null;
		
		var def={};
		
		def["print"]=function(details){
			LODOP = getLodop();
			
			LODOP.PRINT_INIT("");
	
			var width="45mm";
	
			LODOP.SET_PRINT_PAGESIZE(3,width,"5mm","");
	
			var marginTop=4;
			var marginLeft=4;
	
			//打印小票标题
			LODOP.ADD_PRINT_TEXT(marginTop,marginLeft,width,50,details.title);
			LODOP.SET_PRINT_STYLEA(1,"FontSize",12);
			marginTop = marginTop + 50;
	
			//横线
			LODOP.ADD_PRINT_TEXT(marginTop,marginLeft,width,16,"--------------------------");
			marginTop = marginTop + 16;
	
			//添加order
			var sellerNick="";
			
			var orders = details.orders;
			for(var i=0;i<orders.length;i++){
				marginTop = def.addOrder(marginTop,marginLeft,width, orders[i]);
				sellerNick=orders[i].seller_nick;
			}
			
			//总计
			var totalFee= details.total_fee;
			LODOP.ADD_PRINT_TEXT(marginTop,marginLeft,width,16,"总	计："+totalFee+"元");
			marginTop = marginTop + 16;
	
			//店铺
			LODOP.ADD_PRINT_TEXT(marginTop,marginLeft,width,16,"店	铺："+sellerNick);
			marginTop = marginTop + 16;
	
			//时间
			var now=new Date();
			LODOP.ADD_PRINT_TEXT(marginTop,marginLeft,width,16,"时	间："+now);
			marginTop = marginTop + 16;
	
//			LODOP.print();
			LODOP.preview();
		}
	
		def["addOrder"]=function(marginTop, marginLeft, width, order){
			//商品名称
			var skuArr = order.sku_properties_name;
			var skus = skuArr.split(";", 1);
			var skuName=skus[0];
			skuName=skuName.replace(":","：");
			LODOP.ADD_PRINT_TEXT(marginTop,marginLeft,width,16, skuName);
			marginTop = marginTop + 16;
	
			//购买数量
			var num = order.num;
			LODOP.ADD_PRINT_TEXT(marginTop,marginLeft,width,16,"购买数量："+num+"张");
			marginTop = marginTop + 16;
	
			//单价
			var price = order.price;
			LODOP.ADD_PRINT_TEXT(marginTop,marginLeft,width,16,"单	价："+price+"元");
			marginTop = marginTop + 16;
	
			//小计
			var totalFee= order.total_fee;
			LODOP.ADD_PRINT_TEXT(marginTop,marginLeft,width,16,"小	计："+totalFee+"元");
			marginTop = marginTop + 16;
	
			//横线
			LODOP.ADD_PRINT_TEXT(marginTop,marginLeft,width,16,"--------------------------");
			marginTop = marginTop + 16;
			
			return marginTop;
		}
		
		return def;
});