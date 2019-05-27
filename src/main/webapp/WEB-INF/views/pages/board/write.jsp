<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(function(){

	$("#insert_btn").click(function(){
		
		document.question.action="${path}/board/insert.do";
		document.question.submit();
	});
});
</script>
</head>
<body>
<div class="container" id="main">
   <div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
      <div class="panel panel-default content-main">
          <form name="question" method="post" action="">
              <div class="form-group">
                  <label for="writer">글쓴이</label>
                  <div class="form-control" id="writer" name="writer">${sessionScope.user_id}</div>
              </div>
              <div class="form-group">
                  <label for="title">제목</label>
                  <input type="text" class="form-control" id="title" name="title" placeholder="제목"/>
              </div>
              <div class="form-group">
                  <label for="content">내용</label>
                  <textarea name="content" id="content" rows="5" class="form-control"></textarea>
              </div>
              <button type="submit" class="btn btn-success clearfix pull-right" id="insert_btn">확인</button>
              <div class="clearfix" />
          </form>
        </div>
    </div>
</div>
</body>
</html>