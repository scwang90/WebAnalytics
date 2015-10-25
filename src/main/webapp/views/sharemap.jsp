<%--
&nbsp;Created by IntelliJ IDEA.
&nbsp;User: Administrator
&nbsp;Date: 2015/9/7
&nbsp;Time: 14:55
&nbsp;To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>图像数据</title>
</head>
<body>
<code>
option = {<br/>
&nbsp;&nbsp;title : {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;text: '人物关系：乔布斯',<br/>
&nbsp;&nbsp;&nbsp;&nbsp;subtext: '数据来自人立方',<br/>
&nbsp;&nbsp;&nbsp;&nbsp;x:'right',<br/>
&nbsp;&nbsp;&nbsp;&nbsp;y:'bottom'<br/>
&nbsp;&nbsp;},<br/>
&nbsp;&nbsp;tooltip : {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;trigger: 'item',<br/>
&nbsp;&nbsp;&nbsp;&nbsp;formatter: '{a} : {b}'<br/>
&nbsp;&nbsp;},<br/>
&nbsp;&nbsp;toolbox: {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;show : true,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;feature : {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;restore : {show: true},<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;magicType: {show: true, type: ['force', 'chord']},<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;saveAsImage : {show: true}<br/>
&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
&nbsp;&nbsp;},<br/>
&nbsp;&nbsp;legend: {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;x: 'left',<br/>
&nbsp;&nbsp;&nbsp;&nbsp;data:['起始点','中间点','叶子点']<br/>
&nbsp;&nbsp;},<br/>
&nbsp;&nbsp;series : [<br/>
&nbsp;&nbsp;&nbsp;&nbsp;{<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;type:'force',<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;name : "人物关系",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ribbonType: false,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;categories : [<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;name: '起始点'<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;name: '中间点'<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;name:'叶子点'<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;],<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;itemStyle: {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;normal: {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;label: {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;show: true,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;textStyle: {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;color: '#333'<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;nodeStyle : {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;brushType : 'both',<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;borderColor : 'rgba(255,215,0,0.4)',<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;borderWidth : 1<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;linkStyle: {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;type: 'curve'<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;emphasis: {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;label: {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;show: false<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// textStyle: null&nbsp;&nbsp;&nbsp;// 默认使用全局文本样式，详见TEXTSTYLE<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;nodeStyle : {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;//r: 30<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;linkStyle : {}<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;useWorker: false,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;minRadius : 15,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;maxRadius : 25,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;gravity: 1.1,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;scaling: 1.1,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;roam: 'move',<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;nodes:${points},<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;links : ${lines} <br/>
&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
&nbsp;&nbsp;]<br/>
};<br/>
var ecConfig = require('echarts/config');<br/>
function focus(param) {<br/>
&nbsp;&nbsp;var data = param.data;<br/>
&nbsp;&nbsp;var links = option.series[0].links;<br/>
&nbsp;&nbsp;var nodes = option.series[0].nodes;<br/>
&nbsp;&nbsp;if (<br/>
&nbsp;&nbsp;&nbsp;&nbsp;data.source !== undefined<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&& data.target !== undefined<br/>
&nbsp;&nbsp;) { //点击的是边<br/>
&nbsp;&nbsp;&nbsp;&nbsp;var sourceNode = nodes.filter(function (n) {return n.name == data.source})[0];<br/>
&nbsp;&nbsp;&nbsp;&nbsp;var targetNode = nodes.filter(function (n) {return n.name == data.target})[0];<br/>
&nbsp;&nbsp;&nbsp;&nbsp;console.log("选中了边" + sourceNode.name + ' -> ' + targetNode.name + ' (' + data.weight + ')');<br/>
&nbsp;&nbsp;} else { // 点击的是点<br/>
&nbsp;&nbsp;&nbsp;&nbsp;console.log("选中了"+ data.name + '(' + data.value + ')');<br/>
&nbsp;&nbsp;}<br/>
}<br/>
myChart.on(ecConfig.EVENT.CLICK, focus)<br/>
<br/>
myChart.on(ecConfig.EVENT.FORCE_LAYOUT_END, function () {<br/>
&nbsp;&nbsp;console.log(myChart.chart.force.getPosition());<br/>
});<br/>
<br/>
<br/>
</code>

</body>
</html>
