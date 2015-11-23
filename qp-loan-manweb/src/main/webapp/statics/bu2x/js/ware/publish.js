$(function() {
	var lastCatId = "";
	var catIds = "";
	var categoryPath = "";

	$("#cate-container").on("click", ".cat_box li", function() {
		var $this = $(this);
		$this.siblings("li.selected").removeClass("selected");
		$this.addClass("selected");
		$this.parent().nextAll().remove();
		lastCatId = $this.attr("catId");
		catLevel = $this.attr("catLevel");
		if (catLevel == 3) {
			$("#nextBtn").removeAttr("disabled");
		} else {
			$("#nextBtn").attr("disabled","disabled");
		}

		var catBox = '<div class="cat_box">';
		$.ajax({
			url : "getNextCategory.do",
			data : {
				catid : lastCatId
			},
			dataType : 'json',
			type : 'post',
			success : function(result) {
				if (result.length > 0) {
					$.each(result, function(index) {
						catBox += '<li catid="' + this.catId + '" catLevel="' + this.catLevel + '">' + this.catName + '</li>';
					});
					catBox += '</div>';
					$this.parent().after(catBox);
				} else {
					$this.parent().nextAll().remove();
				}
			},
			error : function(result) {
				alert("获取子类目出错");
			}
		});

		categoryPath = "";
		var categoryPathHTML = "";
		var catelength = $("#cate-container .cat_box li.selected").length;
		$("#cate-container .cat_box li.selected").each(function(index) {
			categoryPath += encodeURI(encodeURI(this.innerHTML));
			categoryPathHTML += '<li>' + this.innerHTML + '</li>';
			if (catelength - index != 1) {
				categoryPathHTML += '<li> &nbsp;>&nbsp; </li>';
				categoryPath += " > ";
			}
		});

		$("#cate-path .category-path").html(categoryPathHTML);
	});

	$("#nextBtn").click(function() {
		if (catLevel == 3) {
			window.location.href = "publishNext.do?catId=" + lastCatId;
		} else {
			alert("发布商品必须选择第三级分类");
		}
	});

});