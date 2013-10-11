// 购物车JS 2008.07 刘长炯 beansoft@126.com

// 使用AJAX方式从后台更新商品购买数量
function ajaxAddProduct(product_id) {
		// 异步执行更改购买数量操作
		jQuery.get("cart/ajaxAddProduct.action?ajax=true&product_id=" + product_id + "&timestamp=" + new Date().getTime(), function(data){
			ajaxUpdateCartItemCount();
			if(data) {
				alert(data);
			} else {
				alert("商品添加成功");
			}
		});
}

// 即时更新购物车物品数
function ajaxUpdateCartItemCount() {
	if($("cartItemCount")) {
		window.setTimeout(function () {
             jQuery("#cartItemCount").load("cart/count.action?timestamp=" + new Date().getTime());
         }, 1000);
	}
}

// 缓慢定时更新购物车物品数
function ajaxSlowUpdateCartItemCount() {
	if($("cartItemCount")) {
		window.setInterval(function () {
             jQuery("#cartItemCount").load("cart/count.action?timestamp=" + new Date().getTime());
         }, 10000);
	}
}