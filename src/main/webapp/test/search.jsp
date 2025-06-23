<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>차량 검색</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css">

</head>
<body>
<%@include file="inc/header.jsp" %>
<h2>🔍 차량 검색</h2>
<form method="get" action="SearchServlet">
    차량번호/운전자명/연락처: <input type="text" name="keyword"><br><br>
    <button type="submit">검색</button>
</form>
<%@include file="inc/footer.jsp" %>
</body>
</html>
