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
   </div><div class="g-doc">
    <div class="n-show f-cb" id="showContent">
        <div class="img"><img src="${message.pictureAddress}" alt="" ></div>
        <div class="cnt">
            <h2>${message.productName}</h2>
            <p class="summary">${message.productSummary}</p>
            <div class="price">
                <span class="v-unit">¥</span><span class="v-value">${message.price}</span>
            </div>
            <div class="num">购买数量：
              <span id="plusNum" class="lessNum"><a>-</a></span>
              <span class="totalNum" id="allNum">${message.soldCount}</span>   
              <span id="addNum" class="moreNum"><a>+</a></span>
            </div>
            <div class="oprt f-cb">
                 <sec:authorize access="hasRole('BUYER')">
                   <c:if test="${message.state == 0}">
                      <button class="u-btn u-btn-primary" id="add" data-id="${message.id}" data-title="${message.productName}" data-price="${message.price}">
                                                                         加入购物车</button>
                   </c:if>
                   <c:if test="${message.state == 1}">
                      <span class="u-btn u-btn-primary z-dis">已购买</span>
                      <span class="buyprice">当时购买价格：¥${message.price}</span>
                   </c:if>
                 </sec:authorize>
                 <sec:authorize access="hasRole('SELLER')">
                    <a href="/network/public?id=${message.id}" class="u-btn u-btn-primary">编 辑</a>
                 </sec:authorize>
            </div>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
         ${message.productContext}
    </div>
</div>
<div class="n-foot">
    <p>版权所有：网易云课堂<a href="http://mooc.study.163.com/smartSpec/detail/85002.htm">Java开发工程师(Web方向)</a>微专业团队</p>
</div><script type="text/javascript" src="./js/global.js"></script>
<script type="text/javascript" src="./js/pageShow.js"></script>
</body>
</html>