<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../home/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
body {background-color: white;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="${ctx}/static/styles/zTreeStyle.css" type="text/css" rel="stylesheet" />

<script src="${ctx}/static/jquery/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>

<title>悦迪管理平台</title>
<script type="text/JavaScript">
$(document).ready(function () {
	function set_power_status(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getCheckedNodes(true);
	    var idList = ""; 
        for(var i=0;i<nodes.length;i++){
        	idList += nodes[i].id + ",";
        }
	     idList = idList.substring(0, idList.length-1)
	     var url = "${ctx}/role/addRoleMenu?roleId=${roleId}&idList="+idList;
	     $.ajax({
			type: "post",
			url: url,
			cache: false,
			dataType: "json",
			success: function(data) {
				$('#winAssignMenu').window('close');
			}
		});
		
	}
	
	$("#add").click(function() {
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getCheckedNodes(true);
	    var idList = ""; 
        for(var i=0;i<nodes.length;i++){
        	idList += nodes[i].id + ",";
        }
	     idList = idList.substring(0, idList.length-1)
	     var roleId = $('#roleId').val();
	     //var parentId = $('#parentId').val();
	     var url = "${ctx}/role/assignRoleMenu";
	     var params = {idList:idList,roleId:roleId};
	     $.ajax({
				type: "post",
				url: url,
				data: params,
				cache: false,
				dataType: "json",
				success: function(data) {
					if(data.success) {
						alert(data.message);
					}else {
						alert(data.message);
					}
				}
			});
    });
	
	$("#close").click(function() {
		var parentId = $('#parentId').val();
		$("#close").attr("href",'${ctx}/role/close/'+parentId);
    });
	function consleclick(){
	    var node = $('#test').treegrid('expandAll',2);
	}
	
	var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	
	var code;
	
	function setCheck() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		py = $("#py").attr("checked")? "p":"",
		sy = $("#sy").attr("checked")? "s":"",
		pn = $("#pn").attr("checked")? "p":"",
		sn = $("#sn").attr("checked")? "s":"",
		type = { "Y":py + sy, "N":pn + sn};
		zTree.setting.check.chkboxType = type;
		showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
	}
	function showCode(str) {
		if (!code) code = $("#code");
		code.empty();
		code.append("<li>"+str+"</li>");
	}
	
	function loadUnassignMenuData() {
		var roleId = $('#roleId').val();
		var url = "${ctx}/menu/queryMenuAll/" + roleId;
		$.ajax({
			type: "get",
			url: url,
			cache: false,
			dataType: "json",
			success: function(data) {
				$.fn.zTree.init($("#treeDemo"), setting, data);
			}
		});
	}
	
	loadUnassignMenuData();
	setCheck();
	$("#py").bind("change", setCheck);
	$("#sy").bind("change", setCheck);
	$("#pn").bind("change", setCheck);
	$("#sn").bind("change", setCheck);
});

</script>
</head>
<body>
	<div>
		<div class="breadcrumbs">
	    	<a href="${ctx}/login/index">首页</a>
	        <span>${param.titleName}</span>
	    </div><!-- breadcrumbs -->
	    <!--添加页面-->
	    <div class="form_default" style="margin-left:16px; margin-top:20px;width: ">
			<p>
	    	<a id="add" class="iconlink" href=""><img alt="" class="mgright5" src="${ctx}/demo/images/icons/small/white/save.png"> <span>保存</span></a>
	    	<a class="iconlink" href="javascript:history.go(-1);"><img alt="" class="mgright5" src="${ctx}/demo/images/icons/small/white/close.png"> <span>返回</span></a>
			</p>
			<input type="hidden" value="${roleId}" id="roleId"/>
			<input type="hidden" value="${parentId}" id="parentId"/>
			<div class="content_wrap">
				<div class="zTreeDemoBackground left">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>