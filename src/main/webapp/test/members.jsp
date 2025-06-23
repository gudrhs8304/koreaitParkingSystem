<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>월정액 회원 관리</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css">
</head>
<body>
<%@include file="inc/header.jsp" %>
<div class="container">
<h2>👤 월정액 회원 관리</h2>
<form method="post" action="AddMemberServlet">
    차량번호: <input type="text" name="car_number"><br><br>
    운전자명: <input type="text" name="driver_name"><br><br>
    연락처: <input type="text" name="phone"><br><br>
    시작일: <input type="date" name="start_date"><br><br>
    종료일: <input type="date" name="end_date"><br><br>
    <button type="submit">회원 등록</button>
</form>
</div>
<%@include file="inc/footer.jsp" %>
</body>
</html>
