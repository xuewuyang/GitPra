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
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <div class="tab">
            <ul>
                <li class="z-sel" ><a href="/network">所有内容</a></li>
                <sec:authorize access="hasRole('BUYER')">
                   <li class="z-sel" ><a href="/network/?type=1">未购买的内容</a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>
    <div class="n-plist">
        <ul class="f-cb" id="plist">
           <c:forEach items="${data}" var="productItem">
             <li id="p-${productItem.id}">
                <a href="/network/show?id=${productItem.id}" class="link">
                    <div class="img"><img src="${productItem.pictureAddress}" alt="SICPP"></div>
                    <h3>${productItem.productName}</h3>
                    <div class="price"><span class="v-unit">¥</span><span class="v-value">${productItem.price}</span></div>
                    <c:if test="${productItem.state == 1}">
                      <sec:authorize access="hasRole('BUYER')">
                         <span class="had"><b>已购买</b></span>
                      </sec:authorize>
                      <sec:authorize access="hasRole('SELLER')">
                         <span class="had"><b>已售出</b></span>
                      </sec:authorize>
                    </c:if>
                </a>
                <c:if test="${productItem.state == 0}">
                    <sec:authorize access="hasRole('SELLER')">
                         <span class="u-btn u-btn-normal u-btn-xs del" data-del="${productItem.id}">删除</span>
                    </sec:authorize>
                </c:if>
            </li>
           </c:forEach>
        </ul>
    </div>
</div>
<div class="n-foot">
    <p>版权所有：网易云课堂<a href="http://mooc.study.163.com/smartSpec/detail/85002.htm">Java开发工程师(Web方向)</a>微专业团队</p>
</div>
<script type="text/javascript" src="./js/global.js"></script>
<script type="text/javascript" src="./js/pageIndex.js"></script>
</body>
</html>