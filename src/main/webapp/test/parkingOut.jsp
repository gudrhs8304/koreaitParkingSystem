<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>출차 처리</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css">

</head>
<%@include file="inc/header.jsp" %>
<body>
<div class="container">
<h2>🚘 차량 출차</h2>
<form method="post" action="ParkingOutServlet">
    차량번호: <input type="text" name="car_number"><br><br>
    <button type="submit">출차 처리</button>
</form>
</div>
<%@include file="inc/footer.jsp" %>
</body>
</html>
