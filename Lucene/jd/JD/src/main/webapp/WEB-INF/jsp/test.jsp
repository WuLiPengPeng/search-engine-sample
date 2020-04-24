<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function query() {
		//执行关键词查询时清空过滤条件
		document.getElementById("catalog_name").value="";
		document.getElementById("price").value="";
		//执行查询
		queryList();
	}
	function queryList() {
		//提交表单
		document.getElementById("actionForm").submit();
	}
	function filter(key, value) {
		document.getElementById(key).value=value;
		//执行查询
		queryList();
	}
	function sort() {
		var s = document.getElementById("sort").value; 
		if (s != "1") {
			s = "1";
		} else {
			s = "0";
		}
		document.getElementById("sort").value = s;
		//执行查询
		queryList();
	}
</script>
</head>
<body class="root61">
<%--<div id="shortcut-2013">--%>
	<%--<div class="w">--%>
		<%--<ul class="fl lh">--%>
			<%--<li class="fore1 ld"><b></b><a href="#" rel="nofollow">收藏京东</a></li>--%>
		<%--</ul>--%>
		<%--<ul class="fr lh">--%>
			<%--<li class="fore1" id="loginbar">您好，欢迎来到京东！<span><a href="#">[登录]</a> <a href="#" class="link-regist">[免费注册]</a></span></li>--%>
			<%--<li class="fore2 ld">--%>
				<%--<s></s>--%>
				<%--<a href="#" rel="nofollow">我的订单</a>--%>
			<%--</li>--%>
			<%--<li class="fore2-1 ld" id="jd-vip"><i></i>--%>
				<%--<i></i>--%>
				<%--<s></s>--%>
				<%--<a target="_blank" rel="nofollow" href="http://vip.jd.com/">会员俱乐部</a>--%>
			<%--</li>--%>
			<%--<li class="fore2-2 ld" id="jd-dakehu">        <i></i><s></s>        <a href="http://b.jd.com/" target="_blank" rel="nofollow">企业频道</a>    </li>--%>
			<%--<li class="fore3 ld menu" id="app-jd" data-widget="dropdown" clstag="homepage|keycount|home2013|01d"><s></s>--%>
				<%--<i></i>--%>
				<%--<span class="outline"></span>--%>
				<%--<span class="blank"></span>--%>
				<%--<a href="http://app.jd.com/" target="_blank">手机京东</a>--%>
				<%--<b></b>--%>
			<%--</li>--%>
			<%--<li class="fore4 ld menu" id="biz-service" data-widget="dropdown">--%>
				<%--<s></s>--%>
				<%--<span class="outline"></span>--%>
				<%--<span class="blank"></span>--%>
				<%--客户服务--%>
				<%--<b></b>--%>
			<%--</li>--%>
			<%--<li class="fore5 ld menu" id="site-nav" data-widget="dropdown">--%>
				<%--<s></s>--%>
				<%--<span class="outline"></span>--%>
				<%--<span class="blank"></span>--%>
				<%--网站导航--%>
				<%--<b></b>--%>
			<%--</li>--%>
		<%--</ul>--%>
		<%--<span class="clr"></span>--%>
	<%--</div>--%>
<%--</div><!--shortcut end-->--%>
<div id="o-header-2013">
	<div class="w" id="header-2013">
		<div id="logo-2013" class="ld"><a href="http://www.jd.com/" hidefocus="true"><b></b><img src="<c:url value='/resource'/>/logo-201305.png" width="270" height="60" alt="京东"></a></div>
		<!--logo end-->
		<div id="search-2013">
			<div class="i-search ld">
				<ul id="shelper" class="hide"></ul>
				<form id="actionForm" action="list.action" method="POST">
				<div class="form">
					<input type="text" class="text" accesskey="s" name="queryString" id="key" value="${queryString }"
						autocomplete="off" onkeydown="javascript:if(event.keyCode==13) {query()}">
					<input type="button" value="搜索" class="button" onclick="query()">
				</div>
				<input type="hidden" name="catalog_name" id="catalog_name" value="${catalog_name }"/> 
				<input type="hidden" name="price" id="price" value="${price }"/> 
				<input type="hidden" name="sort" id="sort" value="${sort }"/> 
				</form>
			</div>
		</div>
	</div>
</div>
<div class="a-key">测试商品为帽子</div>
<%--价格--%>
<div data-id="100002" class="prop-attrs">
	<div class="attr">
		<div class="a-key">价格筛选：</div>
		<div class="a-values">
			<div class="v-fold">
				<ul class="f-list">
					<li><a href="javascript:filter('price','0-9')">0-9</a></li>
					<li><a href="javascript:filter('price','10-19')">10-19</a></li>
					<li><a href="javascript:filter('price','20-29')">20-29</a></li>
					<li><a href="javascript:filter('price','30-39')">30-39</a></li>
					<li><a href="javascript:filter('price','40-49')">40-49</a></li>
					<li><a href="javascript:filter('price','50-*')">50以上</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<%--价格排序--%>
<div class="fore1">
	<dl class="order">
		<dt>排序：</dt>
		<dd>
			<a href="javascript:sort()">价格</a><b></b>
		</dd>
	</dl>
</div>
<div>
	<label>搜索结果</label>
	<c:forEach var="item" items="${productModels}">
		<li pid="${item.pid }">
			<div class="lh-wrap">
				<div class="p-img">
					<a target="_blank" href="#">
						<img width="220" height="282" class="err-product" src="<c:url value='/images'/>/${item.picture}">
					</a>
				</div>
				<div class="p-name">
					<a target="_blank" href="#">${item.name }</a>
				</div>
				<div class="p-price">
					<strong><span>￥ ${item.price}</span></strong>
					<%--<strong>￥<fmt:formatNumber value="${item.price}" maxFractionDigits="2"/></strong><span id="p1269191543"></span>--%>
				</div>
			</div>
		</li>
	</c:forEach>
</div>
</body>
</html>