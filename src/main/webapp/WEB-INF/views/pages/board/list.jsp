<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(function(){
	
	$("#write_btn").click(function(){
		location.href="${path}/board/write.do";
	});
});

function list(page){
	location.href="${path}/board/list.do?curPage="+page
			+"&search_option=${map.search_option}"
			+"&keyword=${map.keyword}";
}
</script>
</head>
<body>
<div class="container" id="main">
   <div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
   	  <form name="search" method="post" action="${path}/board/list.do">
              <div class="input-group" style="max-width:470px;">
                <input type="text" class="form-control" placeholder="Search" name="keyword" id="srch-term" value="${map.keyword}">
                <div class="input-group-btn">
                  <button class="btn btn-default btn-primary" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                </div>
              </div>
      </form>
      <div class="panel panel-default qna-list">
          <ul class="list">
              <c:forEach var="row" items="${map.list}">
             	 <li>
                  <div class="wrap">
                      <div class="main">
                          <strong class="subject">
                              <a href="${path}/board/view.do?bno=${row.bno}
								&curPage=${map.pager.curPage}
								&search_option=${map.search_option}
								&keyword=${map.keyword}">${row.title}</a>
                          </strong>
                          <div class="auth-info">
                              <i class="icon-add-comment"></i>
                              <span class="time"><fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                              <a href="/profile.do?writer=${row.writer}" class="author">${row.writer}</a>
                          </div>
                          <div>조회수: ${row.viewcnt}</div>
                          <div class="reply" title="댓글">
                              <i class="icon-reply"></i>
                              <span class="point">${row.bno}</span>
                          </div>
                      </div>
                  </div>
              	</li>
              </c:forEach>                     
          </ul>
          <div class="row">
              <div class="col-md-3"></div>
              <div class="col-md-6 text-center">
                  <ul class="pagination center-block" style="display:inline-block;">
                  	  <c:if test="${map.pager.curBlock > 1}">
                  	 	<li><a href="javascript:list('${map.pager.prevPage}')">«</a></li>
                  	  </c:if>
                  	  
                  	  <c:forEach var="num" begin="${map.pager.blockBegin}" end="${map.pager.blockEnd}">
						<c:choose>
							<c:when test="${num == map.pager.curPage}">
								 <li><a href="#" style="color:red;">${num}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="javascript:list('${num}')">${num}</a></li>
							</c:otherwise>
						 </c:choose>
					   </c:forEach>
                       <c:if test="${map.pager.curBlock <= map.pager.totBlock}">
                      	 <li><a href="javascript:list('${map.pager.nextPage}')">»</a></li>
                       </c:if>
                   </ul>
              </div>
              <div class="col-md-3 qna-write">
                  <button type="button" class="btn btn-primary pull-right" id="write_btn" role="button">질문하기</button>
              </div>
          </div>
        </div>
    </div>
</div>
</body>
</html>