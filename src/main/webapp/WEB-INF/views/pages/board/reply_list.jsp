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
function deleteReply(rno){

	$.ajax({
		type: "post",
		url: "${path}/reply/delete.do?rno=" + rno,
		success: function(){
		alert("댓글이 삭제되었습니다.");
			listReply("1");
		}
	});
}

function modifyReply(content, rno){
	
	var tempContent = content;
	$("#content").replaceWith(
			
			'<div class="article-doc comment-doc" id="content">' +
			'<p><textarea id="updatedComment" row="2" cols="100">' + tempContent + '</textarea></p></div>' +
			'<div class="article-util">' + 
			'<ul class="article-util-list">' + 
			'<li><button type="button" class="delete-answer-button" onclick="replyUpdate('+rno+')">확인</button>&nbsp</li>' +
			'<li>&nbsp<button type="button" class="delete-answer-button" onclick="cancelUpdate()">취소</button></li></ul></div>'
	);
		
	$("#reply_menu").hide();
}

function replyUpdate(rno){
	
	var reply_text = $("#updatedComment").val();
	
	var param={"reply_text":reply_text, "rno":rno};
	$.ajax({
		type: "post",
		url: "${path}/reply/update.do",
		data: param,
		success: function(){
			alert("댓글이 수정되었습니다.");
			listReply("1");
			$("#reply_menu").show();
		}
	});
}


function cancelUpdate(){
	listReply("1");
	$("#reply_menu").show();
}

</script>
</head>
<body>
<p class="qna-comment-count"><strong>${count}</strong>개의 의견</p>
	<c:forEach var="row" items="${list}">
		<article class="article" id="answer-1405">
			<div class="article-header">
				<div class="article-header-thumb">
					<img src="https://graph.facebook.com/v2.3/1324855987/picture"
						class="article-author-thumb" alt="">
				</div>
				<div class="article-header-text">
					<a href="/profile.do?writer=${row.replyer}" class="article-author-name">${row.replyer}</a>
					<span class="article-header-time"> <fmt:formatDate
							value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss" /> <i
						class="icon-link"></i>
					</span>
				</div>
			</div>
			<div class="article-doc comment-doc" id="content">
				<p>${row.reply_text}</p>
			</div>
			<div class="article-util">
				<ul class="article-util-list" id="reply_menu">
					<li>
					<button type="button" class="delete-answer-button" onclick="modifyReply('${row.reply_text}','${row.rno}')">수정</button>
					</li>
					<li>
					<button type="button" class="delete-answer-button" onclick="deleteReply('${row.rno}')">삭제</button>
					</li>		
				</ul>
			</div>
		</article>

	</c:forEach>

	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6 text-center">
			<ul class="pagination center-block" style="display: inline-block;">
				<c:if test="${pager.curBlock > 1}">
					<li><a href="javascript:listReply('${pager.prevPage}')">«</a></li>
				</c:if>

				<c:forEach var="num" begin="${pager.blockBegin}"
					end="${pager.blockEnd}">
					<c:choose>
						<c:when test="${num == pager.curPage}">
							<li><a href="#" style="color: red;">${num}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="javascript:listReply('${num}')">${num}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pager.curBlock <= pager.totBlock}">
					<li><a href="javascript:listReply('${pager.nextPage}')">»</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</body>
</html>