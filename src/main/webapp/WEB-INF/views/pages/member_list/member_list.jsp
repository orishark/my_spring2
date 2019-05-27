<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container" id="main">
   <div class="col-md-10 col-md-offset-1">
      <div class="panel panel-default">
          <table class="table table-hover">
              <thead>
              		<th></th>
                    <th>사용자 아이디</th> 
                    <th>이름</th> 
                    <th>이메일</th>
                </tr>
              </thead>
              <tbody>
    			<c:forEach var="row" items="${list}">
					<tr>
						<td>&nbsp&nbsp&nbsp</td>
						<td>${row.user_id}</td>
						<td>${row.name}</td>
						<td>${row.email}</td>
					</tr>
				</c:forEach>
  			  </tbody>
          </table>
        </div>
    </div>
</div>
</body>
</html>