<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/static/js/preview_image.js"></script>
<script>

$(function(){
	
	$("#sign_up_btn").click(function(){
		
		var password=$("#password").val();
		var confirmPassword=$("#confirmPassword").val();
		
		if(password != confirmPassword){
			alert("설정한 비밀번호가 일치하지 않습니다.");
			alert(password);
			alert(confirmPassword);
			$("#password").focus();
			return;
		}
		
		alert("성공적으로 개인정보가 수정되었습니다.");
			
		document.sign_up.action="${path}/update_memberInfo.do";
		document.sign_up.submit();
	});
});

</script>
</head>
<body>
<div class="container" id="main">
   <div class="col-md-6 col-md-offset-3">
      <div class="panel panel-default content-main">
          <form name="sign_up" method="post" enctype="multipart/form-data">
          	  <div class="form-group">
                  <label for="name">이름</label>
                  <input class="form-control" id="name" name="name" value="${member.name }" readonly>
              </div>
              <div class="form-group">
                  <label for="userId">사용자 아이디</label>
                  <input class="form-control" id="userId" name="user_id" value="${member.user_id }" readonly>
              </div>
              <div class="form-group">
                  <label for="password">비밀번호</label>
                  <input type="password" class="form-control" id="password" name="password" value="${member.password }">
              </div>
              <div class="form-group">
                  <label for="password">비밀번호 확인</label>
                  <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" value="${member.password }">
              </div>
              <div class="form-group">
                  <label>프로필 이미지</label>
                  <img src="/upload/displayFile.do?fileName=${member.image_name}" id="preview" width="80" height="80"></img>
                  <input type="file" id="profile_image" name="profile_image" class="profile_image" accept=".jpg,.jpeg,.png,.gif">
                  <label for="profile_image">파일선택</label>
                  <span id="file_name">선택된 파일없음</span>
              </div>
              <div class="form-group">
                  <label for="email">이메일</label>
                  <input type="email" class="form-control" id="email" name="email" value="${member.email }">
              </div>
              <button type="button" class="btn btn-success clearfix pull-right" id="sign_up_btn">정보수정</button>
              <div class="clearfix" />
          </form>
        </div>
    </div>
</div>
</body>
</html>