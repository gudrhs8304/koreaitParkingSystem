<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    #header{
        margin: 20px auto;
    }
    #menu {
        display: flex;
        justify-content: space-evenly;
    }
    #menu > li {
        padding: 20px;
        border: 1px solid black;
    }
    #menu > li > a {
        color: black;
    }
</style>

<div id="header">

    <ul id="menu">
        <li><h3>주차관리 시스템</h3></li>
        <li><a href="login.jsp">login</a></li>
        <li><a href="members.jsp">members</a></li>
        <li><a href="parkingIn.jsp">parkingIn</a></li>
        <li><a href="parkingOut.jsp">parkingOut</a></li>
        <li><a href="pricing.jsp">pricing</a></li>
        <li><a href="search.jsp">search</a></li>
        <li><a href="status.jsp">status</a></li>
    </ul>
</div>
