<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<style type="text/css">
    .content {
        width: 950px;
    }

    #All:hover {
        cursor: pointer;
    }

    .content table input {
        width: 15px;
        height: 15px;
    }
</style>

<body>
成功登录主页面！！
<a href="/tologout">退出</a>
<a href="/togwc">购物车</a>
<a href="/togwzx">个人中心</a><br>
<h1>客户信息列表</h1>
<div class="content">
    <form:form modelAttribute="customer" action="" id="queryFor">
        <table border="1px" cellpadding="5" cellspacing="0">
            <tr>
                <td width="5%" align="center" id="All"> 全选</td>
                <td width="15%" align="center">客户编号</td>
                <td width="15%" align="center">客户名称</td>
                <td width="15%" align="center">客户负责人</td>
                <td width="18%" align="center">客户信息来源</td>
                <td width="18%" align="center">客户所属行业</td>
                <td width="23%" alig n="center">创建日期</td>
            </tr>

            <c:forEach items="${customers}" var="c">
                <tr>
                    <td><input type="checkbox" name="selectCustomerId"></td>
                    <td>${c.customerId}</td>
                    <td>${c.customerName }</td>
                    <td>${c.customerUserName }</td>
                    <td>${c.customerSourseDict }</td>
                    <td>${c.customerIndustryDict }</td>
                    <td>${c.customerDate }</td>
                </tr>
            </c:forEach>
        </table>
    </form:form>
</div>

</body>
</html>
