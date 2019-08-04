<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
   <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <title>Login page</title>
     <link href="<c:url value='/css/style.css' />"  rel="stylesheet"></link>
     <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
   </head>
   <body >
   <div class='image-class'>
     <div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
     <div class="n-title">
       <div class="g-d fcb">
          <a href='/network/' title='首页' class="title-image"></a>
           <div class="title-word">小武的严选小店</div>
       </div>
     </div>
   <c:url var="loginUrl" value="/login" />
   <form class="m-form m-form-ht n-login" id="loginForm" action="${loginUrl}" method="post">
      <c:if test="${param.logout != null}">
         <div class="alert alert-success">
            <p>You have been logged out successfully.</p>
         </div>
      </c:if>
      <div class="fmitem">
        <label class="fmlab">用户名：</label>
        <div class="fmipt">
           <input class="u-ipt" name="username" autofocus/>
        </div>
      </div>
      <div class="fmitem">
        <label class="fmlab">密码：</label>
        <div class="fmipt">
           <input class="u-ipt" type="password" name="password"/>
        </div>

      </div>
       <c:if test="${param.error != null}">
           <div class="alert alert-danger">
               用户名或密码错误
           </div>
       </c:if>
      <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
      <div class="fmitem fmitem-nolab fmitem-btn">
        <div class="fmipt">
           <input type="submit" class="u-btn u-btn-primary u-btn-lg u-btn-block" value="登录">
        </div>
      </div>
    </form>
    
    <div class="n-foot">
       <p>版权所有：网易严选商城<a href="http://www.fliplus.com/vp-web-buyer/yanxuan/index.jhtml?sysmoduleid=152">Java开发工程师(Web方向)</a>——杨学武</p>
    </div>
    </div>
    <script type="text/javascript" src="/js/md5.js"></script>
    <script type="text/javascript" src="/js/global.js"></script>
    <script type="text/javascript" src="/js/pageLogin.js"></script>
  </body>
</html>