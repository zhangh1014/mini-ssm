<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<title>${param.title}</title>
<!--  与EasyUI有冲突
<base href="${pageContext.request.contextPath}/" />
-->

<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,user-scalable=no">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.5.4.1/jquery.min.js"></script>
	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/wdm.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/wdm.js"></script>

<!-- Easy UI -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.5.4.1/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.5.4.1/themes/icon.css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
	
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.5.4.1/locale/easyui-lang-zh_CN.js"></script>

<script>
	$.extend($.messager.defaults, {
		ok : "确定",
		cancel : "取消"
	});

	if ('${ requestScope.message }' != "") {
		$.messager.alert('${ requestScope.message.msgType }',
				'${ requestScope.message.msg }');
	}
</script>