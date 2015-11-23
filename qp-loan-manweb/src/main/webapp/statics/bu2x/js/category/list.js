$(function() {
	var oTable1 = $('#categoryTable').dataTable({
		bProcessing : false,
		bAutoWidth : true,
		bPaginate : false,
		bSort : true,
		bInfo : false,
		bLengthChange : false,
		bFilter : false,
		aoColumns : [ {
			sWidth : 100
		}, {
			sWidth : 100
		}, {
			sWidth : 100
		}, {
			sWidth : 100
		}, {
			sWidth : 100
		}, {
			sWidth : 100
		}, {
			sWidth : 100
		}, {
			sWidth : 100
		}, {
			bSortable : false,
			sWidth : 88
		} ],
		oLanguage : {
			sSearch : "搜索:",
			sLengthMenu : "每页显示 _MENU_ 条记录",
			sZeroRecords : "抱歉， 没有找到",
			sInfo : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
			sInfoEmpty : "没有数据",
			sInfoFiltered : "(从 _MAX_ 条数据中检索)",
			sZeroRecords : "没有检索到数据"
		}
	});

	//弹出窗口的提交方法
	$("#submitCategoryBtn").click(function() {
		var currrntId = $(".tab-content .tab-pane.active").attr("id");
			if ($("#newCategoryForm").valid()) {
				$.ajax({
					url : "save.do",
					data : {
						catId : $("#catId").val(),
						parentId : $("#parentId").val(),
						catLevel : $("#catLevel").val(),
						catName : $("#catName").val(),
						sortOrder : $("#sortOrder").val(),
						status : $("#status").val(),
						notes : $("#notes").val()
					},
					type : 'post',
					success : function(result) {
						var obj = result;
						if (obj != null) {
							$("#catId").val(obj.catId);
							catId = obj.catId;
							$("#parentId").val(obj.parentId),
							$("#catLevel").val(obj.catLevel),
							$("#catName").val(obj.catName);
							$("#sortOrder").val(obj.sortOrder);
							$("#status").val(obj.status);
							$("#notes").val(obj.notes);
							alert("保存类目信息成功");
						} else {
							catId = '0';
							alert("保存类目信息错误");
						}
					},
					error : function(result) {
						catId = '0';
						alert("保存类目信息错误,error");
					}
				});
				
				return false;
			}
	});
	
	//绑定新增出窗口事件
	new PopupLayer({ trigger: ".categoryWin", popupBlk: "#newCategoryWin", closeBtn: "#closeCategoryWin", useFx: true, offsets: { x: -800, y: 0}});
	
	//每行的新增类目
	$("a[tag=newCategory]").click(function() {
		var catLevel=parseInt($(this).attr("catLevel")) + 1;
		if(catLevel > 3){
			alert("不能添加超过3级的类目!!!");
			return false;
		}
		
		$("#parentId").val($(this).attr("catId"));
		$("#catLevel").val(catLevel);
	});
	
	//每行的修改类目
	$("a[tag=modifyCategory]").click(function() {
		$.ajax({
			url : "query.do",
			data : {
				catId : $(this).attr("catId")
			},
			type : 'post',
			success : function(result) {
				var obj = result;
				if (obj != null) {
					$("#catId").val(obj.catId);
					catId = obj.catId;
					$("#parentId").val(obj.parentId),
					$("#catLevel").val(obj.catLevel),
					$("#catName").val(obj.catName);
					$("#sortOrder").val(obj.sortOrder);
					$("#status").val(obj.status);
					$("#notes").val(obj.notes);
				} else {
					catId = '0';
					alert("获取类目信息错误");
				}
			},
			error : function(result) {
				catId = '0';
				alert("获取类目信息错误,error");
			}
		});
	});
	
	//关闭窗口清除数据
	$("#closeCategoryWin").click(function() {
		$("#catId").val("");
		$("#parentId").val("");
		$("#catLevel").val("");
	});
});