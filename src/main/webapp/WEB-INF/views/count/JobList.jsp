<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/static/echarts/esl.js"></script>
<script type="text/javascript" src="${ctx}/static/echarts/echarts.js"></script>
<title>课程作业统计</title>
<script type="text/javascript">
$(document).ready(function () {
	$("#status").change();//刷新触发点击事件
});
	var fileLocation = "/yuedi_web_v2.0/static/echarts/";
	require.config({
		 paths:{ 
			 echarts: fileLocation
		}
		
	});
	 require(
	        [
	            'echarts',
	            'echarts/chart/bar',
	            'echarts/chart/line'
	        ],
	        function (ec) {
	        	$("#status").change(function(){
		        	jQuery.getJSON("${ctx}/count/WorkCount",{status:$("#status").val()},function(json){
			        	var data = []; //数据报表
						var work_count = document.getElementById("work_count");
						myChart = ec.init(work_count);
						for(var index in json){
							var item = json[index];
							data.push({value:item.num, name:item.className});
						}
						option.series[0].data = data;
						
						myChart.setOption(option,true);
		    		    
		        	});
	        	});
			}
	)

	option = {
	    title : {
	        text: '',
	        subtext: '',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    toolbox: {
	    	x:'600',
	        show : true,
	        feature : {
	            mark : {show: false},
	            dataView : {show: true, readOnly: false},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    series : [
	        {
	            name:'胎教作业',
	            type:'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:[]
	        }
	    ]
	};
</script>
</head>
<body>
	<div class="xian "style="width:1350px;margin:0 auto;padding:0 200px; border-top:1px solid #73d8dc" ></div>
	<div class="qzsj" style="TEXT-ALIGN: center">
		<select id="status" name='status' class="activity-sel">  
			<option value='0' selected>选择类型</option>
			<option value='1'>文字</option>
			<option value='2'>图片</option>
			<option value='3'>语音</option>
		</select>
	</div>
    <div style="TEXT-ALIGN: center;background-color:#FFF">
		<div id="work_count" style="height:520px;width:900px;margin:auto;"></div>
    </div>
    <div class="xian "style="width:1350px;margin:0 auto;padding:0 200px; border-top:1px solid #73d8dc" ></div>
    <h1>胎教作业完成率</h1>
    <div class="box" style="margin-top:25px;width:120px; height:100px; border:10px solid #73d8dc; background-color:#fff;TEXT-ALIGN: center;line-height:80px;">
    	<h1>${scale}%</h1>
    </div>
</body>
</html>