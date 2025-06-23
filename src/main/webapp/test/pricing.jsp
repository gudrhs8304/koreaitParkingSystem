<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>요금 정책 관리</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css">

</head>
<body>
<%@include file="inc/header.jsp" %>
<h2>💰 요금 정책 수정</h2>
<form method="post" action="UpdatePricingServlet">
    기본 요금 (1시간): <input type="number" name="base_fee"><br><br>
    추가 30분 요금: <input type="number" name="extra_fee"><br><br>
    일일 최대 요금: <input type="number" name="max_fee"><br><br>
    <button type="submit">수정</button>
</form>
<%@include file="inc/footer.jsp" %>
</body>
</html>
