<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>주차 현황</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css">
</head>
<body>
<%@include file="inc/header.jsp" %>
<div class="container">
<h2>📊 실시간 주차 현황</h2>
<table border="1">
    <tr>
        <th>차량번호</th>
        <th>운전자명</th>
        <th>입차시간</th>
        <th>주차공간</th>
    </tr>
    <tr>
        <td>12가3456</td>
        <td>홍길동</td>
        <td>2025-06-23 10:45</td>
        <td>3번</td>
    </tr>
</table>
</div>
<%@include file="inc/footer.jsp" %>
</body>
</html>
