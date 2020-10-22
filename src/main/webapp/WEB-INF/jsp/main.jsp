<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
<script>
    // 获取你要查看的页码，并且放到表单隐藏域里
    function subPage(page) {
        document.getElementById("pageNum").value=page;
        alert(document.getElementById("pageNum").value)
        subForm();
    }
    // 提交表单
    function subForm() {
        document.getElementById("queryFor").setAttribute("action","/CustomerController/findForSearch");
        document.getElementById("queryFor").submit();
    }
    function add() {
        document.getElementById("queryFor").setAttribute("action","/CustomerController/toAdd");
        document.getElementById("queryFor").submit();

    }

    function qx() {
        var text=document.getElementById("All").innerHTML;
        if (text=='全选'){
            var a=document.getElementsByName("selectCustomerId");
            for (var i=0;i<a.length;i++){
                a[i].checked=true;
            }
            document.getElementById("All").innerHTML="取消";
        }else {
            var a=document.getElementsByName("selectCustomerId");
            for (var i=0;i<a.length;i++){
                a[i].checked=false;
            }
            document.getElementById("All").innerHTML="全选";
        }
    }

    function sc() {
        //获取是选中的多选框
        var b=[];
        //获取所有的多选框
        var a=document.getElementsByName("selectCustomerId");
        for (var i=0;i<a.length;i++){
            if (a[i].checked){
                b.push(a[i]);
            }
        }
        if (b.length<=0){
            alert("请选择你想删除的数据")
        }else {
            if (confirm("你确定要删除嘛？")==true){
                //到controller删除的方法里
                document.getElementById("queryFor").setAttribute("action","/CustomerController/dodelete");
                document.getElementById("queryFor").submit();
            }
        }
    }
    
    function xg() {
        //获取选中的多选框的个数
        //拿到所有多选框
        var count=0;
        var a=document.getElementsByName("selectCustomerId");
        for (var i=0;i<a.length;i++){
            if (a[i].checked){
                count++;
            }
        }
        //判断
        if (count>1){
            alert("只能选中一条修改")
        }else if (count<1){
            alert("请选择你要修改的值")
        }else {
            document.getElementById("queryFor").setAttribute("action","/CustomerController/toupdate");
            document.getElementById("queryFor").submit();
        }

    }
</script>

<body>
成功登录主页面！！
<a href="/tologout">退出</a>
<a href="/togwc">购物车</a>
<a href="/togwzx">个人中心</a><br>
<h1>客户信息列表</h1>
<div class="content">
    <form:form modelAttribute="customer"  id="queryFor">
<%--        传页码--%>
        <input type="hidden" name="pageNum" id="pageNum">
        <div>
            <button id="querybtn" onclick="subForm(1)">查询</button>
            <button type="button" id="addbtn" onclick="add()">新增</button>
            <button type="button" id="updatebtn" onclick="xg()">修改</button>
            <button type="button" id="deletebtn" onclick="sc()">删除</button>
        </div>
        <div>
            <span>客户编号</span>
            <c:if test="${customer.customerId}!=0">
                <form:input path="customerId"/>
            </c:if>
            <input type="text" id="customerId" name="customerId">
            <span>客户名称</span>
            <form:input path="customerName"/><br>
            <span>客户信息来源</span>
            <form:select path="customerSourse">
                <form:option value="">请选择</form:option>
                <form:option value="A">电话营销</form:option>
                <form:option value="B">网络营销</form:option>
            </form:select>
            <span>创建日期</span>
            <input type="text" id="customerDateBegin" name="">
            <input type="text" id="customerDateEnd" name="">
        </div>
        <br>
        <br>
        <table border="1px" cellpadding="5" cellspacing="0">
            <tr>
                <td width="5%" align="center" id="All" onclick="qx()">全选</td>
                <td width="15%" align="center">客户编号</td>
                <td width="15%" align="center">客户名称</td>
                <td width="15%" align="center">客户负责人</td>
                <td width="18%" align="center">客户信息来源</td>
                <td width="18%" align="center">客户所属行业</td>
                <td width="23%" alig n="center">创建日期</td>
            </tr>

            <c:forEach items="${list}" var="c">
                <tr>
                    <td><input type="checkbox" name="selectCustomerId" value="${c.customerId}"></td>
                    <td>${c.customerId}</td>
                    <td>${c.customerName }</td>
                    <td>${c.customerUserName }</td>
                    <td>${c.customerSourseDict }</td>
                    <td>${c.customerIndustryDict }</td>
                    <td>${c.customerDate }</td>
                </tr>
            </c:forEach>
        </table>
<%--        分页--%>
        <div>
            共有${totalRows}条数据，共有${totalPage}页，当前为第${pageNum}页<br>
            <ul class="page">

                <c:if test="${pageNum>1}">
                    <a href="#"></a><<
                </c:if>
                <c:if test="${pageNum==1}">
                    <<
                </c:if>
                    <%--总页数小于等于五页的时候--%>
                <c:choose>
                    <c:when test="${totalPage<=5}">
                        <c:set var="begin" value="1"></c:set>
                        <c:set var="end" value="${totalPage}"></c:set>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${totalPage-pageNum>3}">
                            <c:set var="begin" value="${pageNum-1}"></c:set>
                            <c:set var="end" value="${pageNum+3}"></c:set>
                        </c:if>
                        <c:if test="${totalPage-pageNum<3}">
                            <c:set var="begin" value="${pageNum-1}"></c:set>
                            <c:set var="end" value="${totalPage}"></c:set>
                        </c:if>
                        <%--当想为第一页--%>
                        <c:if test="${pageNum==1}">
                            <c:set var="begin" value="1"></c:set>
                            <c:set var="end" value="5"></c:set>
                        </c:if>
                        <%--如果最大页大于--%>
                    </c:otherwise>

                </c:choose>

                <c:forEach var="i" begin="${begin}" end="${end}">
                    <c:choose>
                        <c:when test="${i==pageNum}">
                            ${i}
                        </c:when>

                        <c:otherwise>
                            <a href="javascript:void(0)" onclick="subPage(${i})">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${pageNum<totalPage}">
                    <a href="#">>></a>
                </c:if>
                <c:if test="${pageNum==totalPage}">
                    >>
                </c:if>

            </ul>
<%--            <ul class="pagination">--%>
<%--                <c:if test="${pageNum>1}">--%>
<%--                    <li><a href="#"><<</a> </li>--%>
<%--                </c:if>--%>
<%--                <c:if test="${pageNum==1}">--%>
<%--                    <li><a href="#"><<</a> </li>--%>
<%--                </c:if>--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${totalPage<=5}">--%>
<%--                        <c:set var="begin" value="1"/>--%>
<%--                        <c:set var="end" value="${totalPage}"/>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        <c:set var="begin" value="${pageNum-1}"/>--%>
<%--                        <c:set var="end" value="${pageNum+3}"/>--%>
<%--                        <c:if test="${begin-1<=0}">--%>
<%--                            <c:set var="begin" value="1"/>--%>
<%--                            <c:set var="end" value="5"/>--%>
<%--                        </c:if>--%>

<%--                        <c:if test="${end>totalPage}">--%>
<%--                            <c:set var="begin" value="${totalPage-4}"/>--%>
<%--                            <c:set var="end" value="${totalPage}"/>--%>
<%--                        </c:if>--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>

<%--                <c:forEach var="1" begin="${begin}" end="${end}">--%>
<%--                    <c:choose>--%>
<%--                        <c:when test="${i==pageNum}">--%>
<%--                            <li>${i}</li>--%>
<%--                        </c:when>--%>
<%--                        <c:otherwise>--%>
<%--                            <li><a href="#">${i}</a> </li>--%>
<%--                        </c:otherwise>--%>
<%--                    </c:choose>--%>
<%--                </c:forEach>--%>

<%--                <c:if test="${pageNum<totalPage}">--%>
<%--                    <li><a href="#"> >> </a></li>--%>
<%--                </c:if>--%>
<%--                <c:if test="${pageNum==totalPage}">--%>
<%--                    <li><a href="#"> >> </a> </li>--%>
<%--                </c:if>--%>
<%--            </ul>--%>

        </div>
    </form:form>
</div>

</body>
</html>
