$(function() {
	var myDropzone;
	var wareId = $("#wareId").val() == "" ? '0' : $("#wareId").val();

	$("#submitBtn").click(function() {
		var currrntId = $(".tab-content .tab-pane.active").attr("id");
		switch (currrntId) {
		case 'baseinfo':
			if ($("#wareForm").valid()) {
				var introduction = editor.getContent();
				if (introduction == "" || introduction == null) {
					alert("产品介绍非空");
					return;
				}
				$.ajax({
					url : "saveWare.do",
					data : {
						wareId : $("#wareId").val(),
						title : $("#title").val(),
						simpleDesc : $("#simpleDesc").val(),
						itemNum : $("#itemNum").val(),
						hasStock : $("input[name='hasStock']:checked").val(),
						wareStatus : $("input[name='wareStatus']:checked").val(),
						preDays : $("#preDays").val(),
						catId : $("#catId").val(),
						wareDesc : introduction
					},
					type : 'post',
					success : function(result) {
						if (result > 0) {
							$('#myTab a[href="#picture"]').tab('show');
							$("#wareId").val(result);
							wareId = result;
						} else {
							wareId = '0';
							alert("保存商品出错");
						}
					},
					error : function(result) {
						wareId = '0';
						alert("保存商品出错");
					}
				});
			}
			break;
		case 'picture':
			if (wareId == '0') {
				alert("请先填写商品基本信息并确认");
				$('#myTab a[href="#baseinfo"]').tab('show');
				return;
			} else {
				for ( var i = 0; i < myDropzone.files.length; i++) {
					myDropzone.getQueuedFiles()
					myDropzone.getQueuedFiles().push(myDropzone.files[i]);
				}

				myDropzone.processQueue();
			}
			break;
		case 'price':
			if (wareId == '0') {
				alert("请先填写商品基本信息并确认");
				$('#myTab a[href="#baseinfo"]').tab('show');
				return;
			} else {
				if ($("#priceForm").valid()) {
					$.ajax({
						url : "saveSku.do",
						data : {
							skuId : $("#skuId").val(),
							costPrice : (Number($("#costPrice").val()) * 100).toFixed(0),
							salePrice : (Number($("#salePrice").val()) * 100).toFixed(0),
							stock : $("#stock").val(),
							wareId : $("#wareId").val()
						},
						type : 'post',
						success : function(result) {
							if (result > 0) {
								alert("保存成功");
								$('#myTab a[href="#area"]').tab('show');
								$("#skuId").val(result);
								skuId = result;
							}else {
								skuId = '0';
								alert("保存失败");
							}
						},
						error : function(result) {
							alert("保存失败");
						}
					});
				}
			}
			break;
		}
	});

	$('#myTab a').click(function(e) {
		e.preventDefault();// 阻止a链接的跳转行为
		$(this).tab('show');// 显示当前选中的链接及关联的content
		if ($(this).attr("href") === "#picture") {
			$.ajax({
				url : "getWareImageUrlsByWareId.do",
				data : {
					wareId : $("#wareId").val()
				},
				type : 'post',
				success : function(result) {
					var obj = result;

					$("#picture .ace-thumbnails").empty();
					if (obj != null || obj != {}) {
						var picHtml = "";
						for (pic in obj) {
							var litxt = '<li><input type="hidden" value="' + pic + '"> <a class="cboxElement" href="' + imgServer + '/' + obj[pic] + '" ' + 'title="Photo Title" data-rel="colorbox"> <img alt="150x150" src="' + imgServer + '/' + obj[pic] + '"  style="width:150px;height:150px;"> </a>' + '<div class="tags"> <span class="label-holder"> <span class="label label-warning arrowed-in"></span> </span> </div>' + '<div class="tools"> <a href="#" onclick="PublishNext.deletePic(this);"> <i class="ace-icon glyphicon glyphicon-remove" title="删除"></i></a></div></li>';

							picHtml += litxt;
						}
						$("#picture .ace-thumbnails").append(picHtml);
					}
				},
				error : function(result) {
				}

			});
		}
	});

	// var ue = UE.getEditor('editor');
	// 富文本编辑器
	var editor = new UE.ui.Editor({
		initialContent : '',
		autoClearinitialContent : false
	});
	editor.render("editor");
	editor.ready(function() {
		$("#edui1").css("width", "100%");
	});

	/**
	 * 图像处理相关
	 */
	var $overflow = '';
	var colorbox_params = {
		rel : 'colorbox',
		reposition : true,
		scalePhotos : true,
		scrolling : false,
		previous : '<i class="ace-icon fa fa-arrow-left"></i>',
		next : '<i class="ace-icon fa fa-arrow-right"></i>',
		close : '&times;',
		current : '{current} of {total}',
		maxWidth : '100%',
		maxHeight : '100%',
		onOpen : function() {
			$overflow = document.body.style.overflow;
			document.body.style.overflow = 'hidden';
		},
		onClosed : function() {
			document.body.style.overflow = $overflow;
		},
		onComplete : function() {
			$.colorbox.resize();
		}
	};

	$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
	$("#cboxLoadingGraphic").append("<i class='ace-icon fa fa-spinner orange'></i>");

	Dropzone.autoDiscover = false;
	try {
		myDropzone = new Dropzone("#dropzone", {
			// $("#dropzone").dropzone({
			// url : "/file/post",
			paramName : "file", // The name that will be used to transfer the
			// file
			maxFiles : 30,
			type : 'image/png',
			maxFilesize : 0.5, // MB
			addRemoveLinks : true,
			thumbnailHeight : 250,
			thumbnailWidth : 250,
			dictDefaultMessage : '<span class="bigger-150 bolder"><i class="ace-icon fa fa-caret-right red"></i> 图片上传 </span> \ <span class="smaller-80 grey">(支持图片拖拽)</span> <br /> \ <i class="upload-icon ace-icon fa fa-cloud-upload blue fa-3x"></i>',
			dictResponseError : '上传文件失败!',
			dictRemoveFile : '移除',
			autoProcessQueue : false,
			// change the previewTemplate to use Bootstrap progress bars
			previewTemplate : "<div class=\"dz-preview dz-file-preview\">\n  <div class=\"dz-details\">\n    <div class=\"dz-filename\"><span data-dz-name></span></div>\n    <div class=\"dz-size\" data-dz-size></div>\n    <img data-dz-thumbnail />\n  </div>\n  <div class=\"progress progress-small progress-striped active\"><div class=\"progress-bar progress-bar-success\" data-dz-uploadprogress></div></div>\n   <div class=\"dz-error-message\"><span data-dz-errormessage></span></div>\n</div>",
			/*
			 * sending : function(xhr, formData) { if ($("#wareId").val() == "") {
			 * alert("请先填写商品基本信息并确认"); // myDropzone.drop(); } },
			 */
			success : function(file, data) {
				/*
				 * if (data.status === 1) { $scope.uploadImage = data.message;
				 * $scope.listUserImages(); }
				 */
				// alert("ss");
			}
		});

		myDropzone.on("queuecomplete", function(file) {
			alert("上传完成");
			$('#myTab a[href="#price"]').tab('show');
		});
	} catch (e) {
		alert('Dropzone.js does not support older browsers!');
	}

});

var PublishNext = (function() {
	return {
		deletePic : function($this) {
			var thisParent = $($this).parents('li');
			var picId = thisParent.children("input").val();
			var imgUrl = thisParent.children("a.cboxElement").attr("href");
			$.post("deletePic.do", {
				wareId : $("#wareId").val(),
				warePicId : picId,
				imgUrl : imgUrl
			}, function(result) {
				if (result == "ok") {
					thisParent.remove();
				} else {
					alert("删除出错了");
				}
			});
		}
	}
})();