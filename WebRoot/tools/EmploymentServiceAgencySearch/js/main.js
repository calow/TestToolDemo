$(function() {
	area(1000, 1);
});
/**
 * 点击"搜索"按钮时触发该函数
 */
function keyWords() {
	var keyWords = $("#keyWords").val();
	$.showIndicator();
	$
			.ajax({
				url : requestUrl + "&toolAction=search&keywords=" + keyWords,
				dataType : 'json',
				success : function(d) {
					$.hideIndicator();
					$("#listOfKeyWord").empty();
					var content = "";
					if (d.length < 1) {
						content = '<div class="content-block-title">非常抱歉没能搜索到内容</div>';
					}
					$
							.each(
									d,
									function() {
										content += '<div class="content-block-title">'
												+ this.name
												+ ':</div>'
												+ '<div class="list-block media-list" style="margin-top: 5px">'
												+ '<ul>'
												+ '<li><a href="#" class="item-content">'
												+ '<div class="item-inner">'
												+ '<div style="font-size:1em">'
												+ '<div>服务地址: '
												+ this.address
												+ '</div>'
												+ '<div>联系电话: '
												+ this.phone
												+ '</div>'
												+ '<div>网址: '
												+ this.website
												+ '</div>'
												+ '</div>'
												+ '</div> </a>'
												+ '</li>'
												+ '</ul>' + '</div>';
									});
					$("#listOfKeyWord").append(content);
				}
			});
}

/**
 * 点击"区域索引"选择框时触发该函数
 */
function area(code, type) {
	$.showIndicator();
	$
			.ajax({
				url : requestUrl + "&toolAction=area&code=" + code + "&type="
						+ type,
				dataType : 'json',
				success : function(d) {
					$.hideIndicator();
					// 将显示内容部分清空
					$("#listOfArea").empty();
					var content = "";
					if (d.length < 1) {
						content = '<div class="content-block-title">非常抱歉未能获取到结果</div>';
					}
					$
							.each(
									d,
									function() {
										content += '<div class="content-block-title">'
												+ this.name
												+ ':</div>'
												+ '<div class="list-block media-list" style="margin-top: 5px">'
												+ '<ul>'
												+ '<li><a href="#" class="item-content">'
												+ '<div class="item-inner">'
												+ '<div style="font-size:1em">'
												+ '<div>服务地址: '
												+ this.address
												+ '</div>'
												+ '<div>联系电话: '
												+ this.phone
												+ '</div>'
												+ '<div>网址: '
												+ this.website
												+ '</div>'
												+ '</div>'
												+ '</div> </a>'
												+ '</li>'
												+ '</ul>' + '</div>';
									});
					$("#listOfArea").append(content);
				}
			});
}
