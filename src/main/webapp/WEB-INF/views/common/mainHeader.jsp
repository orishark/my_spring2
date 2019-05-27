<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.sign{
	padding: 15px;
	line-height: 50px;
}
</style>
<nav class="navbar navbar-fixed-top header">
 	<div class="col-md-12">
        <div class="navbar-header">
          <a href="/board/list.do" class="navbar-brand">MySpring</a>
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse1">
          <i class="glyphicon glyphicon-search"></i>
          </button>
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse1">
          <ul class="nav navbar-nav navbar-right"> 
          	 <c:if test="${sessionScope.user_id != null}">
          	 	<li>
          	 		<span class="sign">${sessionScope.user_id}님이 로그인 중입니다.</span>
          	 	</li>
          	 </c:if>            
             <li>
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-bell"></i></a>
                <ul class="dropdown-menu">
                  <li><a href="https://slipp.net" target="_blank">MySpring</a></li>
                  <li><a href="https://facebook.com" target="_blank">Facebook</a></li>
                </ul>
             </li>
             <li>
             	<a href="/member_list.do"><i class="glyphicon glyphicon-user"></i></a>
             </li>
           </ul>
        </div>	
     </div>	
</nav>
<div class="navbar navbar-default" id="subnav">
    <div class="col-md-12">
        <div class="navbar-header">
            <a href="#" style="margin-left:15px;" class="navbar-btn btn btn-default btn-plus dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-home" style="color:#dd1111;"></i> Home <small><i class="glyphicon glyphicon-chevron-down"></i></small></a>
            <ul class="nav dropdown-menu">
            	<c:if test="${sessionScope.user_id != null}">
                	<li><a href="/profile.do"><i class="glyphicon glyphicon-user" style="color:#1111dd;"></i> Profile</a></li>
                	<li class="nav-divider"></li>
                </c:if>
                <li><a href="#"><i class="glyphicon glyphicon-cog" style="color:#dd1111;"></i> Settings</a></li>
            </ul>
            
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse2">
            	<span class="sr-only">Toggle navigation</span>
            	<span class="icon-bar"></span>
            	<span class="icon-bar"></span>
            	<span class="icon-bar"></span>
            </button>            
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse2">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="/">Posts</a></li>
                
                <c:if test="${sessionScope.user_id == null}">
                	<li><a href="/sign_in.do" role="button">로그인</a></li>
                </c:if>
                <c:if test="${sessionScope.user_id != null}">
                	<li><a href="/sign_out.do" role="button">로그아웃</a></li>
                	<li><a href="/edit_memberInfo.do" role="button">개인정보수정</a></li>
                </c:if>
                <li><a href="/sign_up.do" role="button">회원가입</a></li>
            </ul>
        </div>
    </div>
</div>

