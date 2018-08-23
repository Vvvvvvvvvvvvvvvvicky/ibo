<html>
	<head>
		<link rel="stylesheet" href="/css/test.css"></link>
		<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">
		<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
		<title>首页</title>
	</head>
	<body style="text-align:center">
		<#list list as item>
			${item["id"]}---${item["name"]}---${item["isShow"]}---
			<#if item.isShow=="1">显示<#else>不显示</#if><br>
		</#list>
		<br><br><br>
		<#list list as item>
			${item["id"]}---${item["name"]}---${item["isShow"]}---
			<template v-if="item.isShow=='1'">
				显示
			</template>
			<template v-else>
				不显示
			</template>
			<br>
		</#list>
		<br><br><br>
		<button type="button" class="btn" onclick="changShowStatus('001')">提交</button>
		<script type="text/javascript" src="/js/index.js"></script>
		<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
		<script src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
		
	</body>
</html>