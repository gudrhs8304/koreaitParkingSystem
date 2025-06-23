<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>입차 등록</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css">
</head>
<body>
<%@include file="inc/header.jsp" %>
<div class="container">
<h2>🚗 차량 입차 등록</h2>
<form method="post" action="ParkingInServlet">
    차량번호: <input type="text" name="car_number" required><br><br>
    운전자명: <input type="text" name="driver_name"><br><br>
    연락처: <input type="text" name="phone"><br><br>
    차량유형:
    <select name="car_type">
        <option value="일반">일반</option>
        <option value="장애인">장애인</option>
        <option value="경차">경차</option>
    </select><br><br>
    월정액 회원: <input type="checkbox" name="is_monthly" value="true"><br><br>
    <button type="submit">입차 등록</button>
</form>
</div>
<%@include file="inc/footer.jsp" %>
</body>
</html>
