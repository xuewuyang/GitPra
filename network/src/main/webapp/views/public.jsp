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
        <h2>内容发布</h2>
    </div>
    <div class="n-public">
        <form class="m-form m-form-ht" id="form" method="post" action="/publicSubmit" onsubmit="return false;" autocomplete="off">
            <div class="fmitem">
                <label class="fmlab">标题：</label>
                <div class="fmipt">
                    <input class="u-ipt ipt" name="title" value="${message.productName}" autofocus placeholder="2-80字符"/>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">摘要：</label>
                <div class="fmipt">
                    <input class="u-ipt ipt" name="summary" value="${message.productSummary}" placeholder="2-140字符"/>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">图片：</label>
                <div class="fmipt" id="uploadType">
                    <input name="pic" type="radio" value="url" checked /> 图片地址
                    <input name="pic" type="radio" value="file" /> 本地上传
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab"></label>
                <div class="fmipt" id="urlUpload">
                    <input class="u-ipt ipt"  name="image" placeholder="图片地址"/>
                </div>
                <div class="fmipt" id="fileUpload"  style="display:none">
                    <input class="u-ipt ipt" name="file" type="file" id="fileUp"/>
                    <button class="u-btn u-btn-primary" id="upload">上传</button>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">正文：</label>
                <div class="fmipt">
                    <textarea class="u-ipt" name="detail" rows="10" placeholder="2-1000个字符">${message.productContext}</textarea>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">价格：</label>
                <div class="fmipt">
                    <input class="u-ipt price" name="price" value="${message.price}"/>元
                </div>
            </div>
            <div class="fmitem fmitem-nolab fmitem-btn">
                <div class="fmipt">
                    <button type="submit" class="u-btn u-btn-primary u-btn-lg">发 布</button>
                </div>
            </div>
        </form>
        <span class="imgpre"><img src="${message.pictureAddress}" alt="" id="imgpre"></span>
    </div>
</div>
<div class="n-foot">
    <p>版权所有：网易云课堂<a href="http://mooc.study.163.com/smartSpec/detail/85002.htm">Java开发工程师(Web方向)</a>微专业团队</p>
</div><script type="text/javascript" src="./js/global.js"></script>
<script type="text/javascript" src="./js/public.js"></script>
</body>
</html>