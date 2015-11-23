$(function() {
	var oTable1 = $('#orderTable').dataTable({
		bProcessing : false,
		bAutoWidth : true,
		bPaginate : false,
		bSort : true,
		bInfo : false,
		bLengthChange : false,
		bFilter : false,
		aaSorting: [[7, "desc"]],
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
			sWidth : 120
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

	$('.date-picker').datepicker({
		format : 'yyyy-mm-dd',
		autoclose : true,
		todayHighlight : true,
		todayBtn : 'linked',
		weekStart : 1,
		language : 'zh-CN'
	}).next().on(ace.click_event, function() {
		$(this).prev().focus();
	});

	$("#serachorderBtn").click(function() {
		$("#searchcbox").show();
		$(this).after('<button type="button" id="_serachorderBtn" class="btn btn-white btn-info btn-bold"> <span class="glyphicon glyphicon glyphicon glyphicon-search"></span>搜索 </button>');
		$(this).remove();
		$("#firstInit").val("false");
	});

	$("#myButtons").on("click", "#_serachorderBtn", function() {
		$("#orderListForm").submit();
	});

});