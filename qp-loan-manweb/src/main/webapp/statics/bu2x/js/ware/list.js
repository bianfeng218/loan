$(function() {
	var oTable1 = $('#wareTable').dataTable({
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
	
	$("#addwareBtn").click(function() {
		window.location.href = "publish.do";
	});

	var ids = "";
	$("#deletewareBtn").click(function() {
		ids = "";
		$('table tr > td:first-child input:checkbox').each(function() {
			if ($(this).prop("checked") === true) {
				ids += $.trim($(this).closest("tr").children('td').eq(1).html()) + ",";
			}
		});

		if (ids === "") {
			alert("请选择要删除的商品");
			return;
		}

		ids = ids.substring(0, ids.length - 1);
		var r = confirm("确认要将这些商品放入回收站吗？");
		if (r == true) {
			$.post("toTrash.do", {
				ids : ids
			}, function(result) {
				if (result == "ok") {
					window.location.href = "list.do?firstInit=" + $("#firstInit").val() + "&";
				} else {
					alert("删除出错了");
				}
			});
		}

	});
	
	$("#trashwareBtn").click(function() {
		window.location.href = "trash.do";
	});
	
	$("#myButtons").on("click", "#_serachwareBtn", function() {
		$("#wareListForm").submit();
	});
	
	$("#serachwareBtn").click(function() {
		$("#searchcbox").show();
		$(this).after('<button type="button" id="_serachwareBtn" class="btn btn-white btn-info btn-bold"> <span class="glyphicon glyphicon glyphicon glyphicon-search"></span>搜索 </button>');
		$(this).remove();
		$("#firstInit").val("false");
	});
	$(document).on('click', 'th input:checkbox', function() {
		var that = this;
		$(this).closest('table').find('tr > td:first-child input:checkbox').each(function() {
			// this.checked = that.checked;
			$(this).prop("checked", $(that).prop("checked"));
			var checked = $(this).prop("checked");
			if (checked === true)
				$(this).closest('tr').addClass('selected');
			else
				$(this).closest('tr').removeClass('selected');
		});
	});

	$('table tr > td:first-child input:checkbox').each(function() {
		var checked = $(this).prop("checked");
		if (checked === true)
			$(this).closest('tr').addClass('selected');
		else
			$(this).closest('tr').removeClass('selected');
	});

	$('tr > td:first-child input:checkbox').click(function() {
		var checked = $(this).prop("checked");
		if (checked === true)
			$(this).closest('tr').addClass('selected');
		else
			$(this).closest('tr').removeClass('selected');
	});

	$("#btn_parent_cat").click(function() {
		// Menu
		$("#cat_menu").menu();
		$("#cat_menu").show();
	});

	var eventForSelected = function() {
		var _this = $(this);
		var btn_text = _this.text();
		var parent_a = _this.parents("ul li").eq(1).children("a");
		while (parent_a.length > 0) {
			btn_text = parent_a.text() + " > " + btn_text;
			parent_a = parent_a.parents("ul li").eq(1).children("a");
		}
		$("#btn_parent_cat span").text(btn_text);
		$("#catNameTxt").val(btn_text);
		var _href_text = _this.attr("href");
		var _id = _href_text.substring(1, _href_text.length);
		$("#catIdTxt").val(_id);
		$("#cat_menu").hide();
	};

	$("#cat_menu li:not(.shop-cat-node) a").click(eventForSelected);
	$("#cat_menu li:not(.ui-state-disabled) a").click(eventForSelected);

	$("#cat_menu").mouseleave(function() {
		$(this).hide();
	});

	//每行的编辑
	$("a[tag=editWare]").click(function() {
		var wareId = $(this).attr("wareid");
		window.location.href = "publishNext.do?wareId=" + wareId;
	});
});
