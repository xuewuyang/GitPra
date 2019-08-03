<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta name="_csrf" content="${_csrf.token}"/>
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>java</title>
<link rel="stylesheet" href="./css/style.css"/>
</head>
<body>
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
</div><div class="g-doc" id="settleAccount">
    <div class="m-tab m-tab-fw m-tab-simple f-cb" >
        <h2>已添加到购物车的内容</h2>
    </div>
    <table id="newTable" class="m-table m-table-row n-table g-b3">
      <tbody>
        <tr>
          <th>内容名称</th>
          <th>数量</th>
          <th>价格</th>
        </tr>
        <c:forEach items="${data}" var="shoppingCart">
          <tr>
           <td><a href="/network/show?id=${shoppingCart.productId}">${shoppingCart.productName}</a></td>
           <td>
             <span id="plusNum" class="lessNum">-</span>
             <span id="allNum" class="totalNum">${shoppingCart.count}</span>
             <span id="thisId">${shoppingCart.productId}</span>
             <span id="addNum" class="moreNum">+</span>
           </td>
           <td>
             ${shoppingCart.price}
           </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <div id="act-btn">
      <button class="u-btn u-btn-primary" id="back">退出</button>
      <button class="u-btn u-btn-primary" id="Account">购买</button>
    </div>
</div>
<div class="n-foot">
    <p>版权所有：网易云课堂<a href="http://mooc.study.163.com/smartSpec/detail/85002.htm">Java开发工程师(Web方向)</a>微专业团队</p>
</div><script type="text/javascript" src="./js/global.js"></script>
<script type="text/javascript" src="./js/settleAccount.js"></script>
</body>
</html>