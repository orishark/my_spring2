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
	
	listReply("1");
	
	$("#reply_btn").click(function(){
		
		var reply_text=$("#reply_text").val();
				
		var bno="${dto.bno}";
		var param={"reply_text":reply_text, "bno":bno};
		$.ajax({
			type: "post",
			url: "${path}/reply/insert.do",
			data: param,
			success: function(){
				alert("댓글이 등록되었습니다.");
				listReply("1");
			}
		});
	});
});

function listReply(num){

	$.ajax({
		type: "post",
		url: "${path}/reply/list.do?bno=${dto.bno}&curPage=" + num,
		success: function(result){
			$("#listReply").html(result);
		}
	});
}
</script>
</head>
<body>
<div class="container" id="main">
    <div class="col-md-12 col-sm-12 col-lg-12">
        <div class="panel panel-default">
          <header class="qna-header">
              <h2 class="qna-title">${dto.title}</h2>
          </header>
          <div class="content-main">
              <article class="article">
                  <div class="article-header">
                      <div class="article-header-thumb">
                          <img src="/upload/displayFile.do?fileName=${dto.image_name}" class="article-author-thumb" alt="">
                      </div>
                      <div class="article-header-text">
                          <a href="/profile.do?writer=${dto.writer}" class="article-author-name">${dto.writer}</a>
                          <span class="article-header-time">
                              <fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                              <i class="icon-link"></i>
                          </span>
                      </div>
                  </div>
                  <div class="article-doc">
                      <p>${dto.content}</p>
                  </div>
                  <div class="article-util">
                      <ul class="article-util-list">
                      	  <li>
                          	  <a href="${path}/board/list.do?curPage=${map.pager.curPage}
								&search_option=${map.search_option}
								&keyword=${map.keyword}" class="link-modify-article">목록</a>
                          </li>
                          <c:if test="${sessionScope.user_id == dto.writer}">
                          	<li>
                              	<a class="link-modify-article" href="${path}/board/edit.do?bno=${dto.bno}">수정</a>
                          	</li>
                          	<li>
                          	  	<a class="link-delete-article" href="${path}/board/delete.do?bno=${dto.bno}">삭제</a>
                          	</li>
                          </c:if>                      
                      </ul>
                  </div>
              </article>

              <div class="qna-comment">
                  <div class="qna-comment-slipp">
                      <div class="qna-comment-slipp-articles">
                      	  
                      	  <!-- 댓글 목록을 출력할 영역 -->
                          <div id="listReply"></div>
                        
                          <c:if test="${sessionScope.user_id != null}">
                          	<form class="submit-write">
                              <div class="form-group" style="padding:14px;">
                                  <textarea class="form-control" placeholder="Update your status" id="reply_text"></textarea>
                              </div>
                              <button class="btn btn-success pull-right" type="button" id="reply_btn">답변하기</button>
                              <div class="clearfix" />
                          	</form>
                          </c:if>
                      </div>
                  </div>
              </div>
          </div>
        </div>
    </div>
</div>
</body>
</html>