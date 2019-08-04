<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>java</title>
    <link rel="stylesheet" href="./css/style.css"/>
</head><body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div><div class="n-head">
    <div class="g-doc f-cb">
        <c:if test="${user == null || (user != 'buyer' && user != 'seller')}">
          <div class="user">
                               请<a href="/network/login">[登录]</a>
          </div>
        </c:if>
        <div class="user">
            <sec:authorize access="hasRole('BUYER')">
                                       买家你好，<span class="name">${user}</span>！<a href="/network/logout">[退出]</a>
            </sec:authorize>
        </div>
        <div class="user">
            <sec:authorize access="hasRole('SELLER')">
                                       卖家你好，<span class="name">${user}</span>！<a href="/network/logout">[退出]</a>
            </sec:authorize>
        </div>
          
        <ul class="nav">
              <li><a href="/network/">首页</a></li>
              <sec:authorize access="hasRole('BUYER')">
              <li>
                <a href="/network/account">账务</a>
              </li>
              </sec:authorize>
              <sec:authorize access="hasRole('BUYER')">
              <li>
                <a href="/network/shoppingcart">购物车</a>
              </li>
              </sec:authorize>
              <sec:authorize access="hasRole('SELLER')">
              <li>
                <a href="/network/public">发布</a>
              </li>
              </sec:authorize>
        </ul>
    </div>
</div><div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>已购买的内容</h2>
    </div>
    <table class="m-table m-table-row n-table g-b3">
        <colgroup><col class="img"/><col/><col class="time"/><col/><col class="num"/><col/><col class="price"/><col/></colgroup>
        <thead>
        <tr><th>内容图片</th><th>内容名称</th><th>购买时间</th><th>购买数量</th><th>购买价格</th></tr>
        </thead>
        <tbody>
        <c:forEach items="${message}" var="productItem">
        <tr>
            <td><a href="/network/show?id=${productItem.id}"><img src="${productItem.pictureAddress}" alt=""></a></td>
            <td><h4><a href="/network/show?id=${productItem.id}">${productItem.productName}</a></h4></td>
            <td><span class="v-time">${productItem.buyTime}</span></td>
            <td><span class="v-num">${productItem.soldCount}</span></td>
            <td><span class="v-unit">¥</span><span class="value" >${productItem.price}</span></td>
        </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4"><div class="total">总计：</div></td>
            <td><span class="v-unit">¥</span><span  id="allPrice">--</span></td>
        </tr>
        </tfoot>
    </table>
</div>
<div class="n-foot">
    <p>版权所有：网易严选商城<a href="http://www.fliplus.com/vp-web-buyer/yanxuan/index.jhtml?sysmoduleid=152">Java开发工程师(Web方向)</a>——杨学武</p>
</div>
<script type="text/javascript " src="./js/pageAccount.js "></script>
</body>
</html>