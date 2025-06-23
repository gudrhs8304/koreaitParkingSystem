<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>로그인</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css">
</head>
<body>
<%@include file="inc/header.jsp" %>
<h2>🔐 관리자 로그인</h2>
<form method="post" action="LoginServlet">
    아이디: <input type="text" name="username"><br><br>
    비밀번호: <input type="password" name="password"><br><br>
    <button type="submit">로그인</button>
    <%@include file="inc/footer.jsp" %>
</form>
</body>
</html>
