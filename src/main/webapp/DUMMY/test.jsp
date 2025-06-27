<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>TEST</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div style="display: none">
    <%@ include file="/page/dataTable.jsp"%>
</div>
<div class="container" id="parking_area">
    <div class="row justify-content-center">

        <!-- 왼쪽 주차 공간 -->
        <div class="col-5">
            <div class="d-flex flex-column gap-2">
                <div class="p-3 text-center border border-dark border-1 parkingSpot">P1</div>
                <div class="p-3 text-center border border-dark border-1 parkingSpot">P2</div>
                <div class="p-3 text-center border border-dark border-1 parkingSpot">P3</div>
                <div class="p-3 text-center border border-dark border-1 parkingSpot">P4</div>
                <div class="p-3 text-center border border-dark border-1 parkingSpot">P5</div>
            </div>
        </div>

        <!-- 오른쪽 주차 공간 -->
        <div class="col-5">
            <div class="d-flex flex-column gap-2">
                <div class="p-3 text-center border border-dark border-1 parkingSpot">P6</div>
                <div class="p-3 text-center border border-dark border-1 parkingSpot">P7</div>
                <div class="p-3 text-center border border-dark border-1 parkingSpot">P8</div>
                <div class="p-3 text-center border border-dark border-1 parkingSpot">P9</div>
                <div class="p-3 text-center border border-dark border-1 parkingSpot">P10</div>
            </div>
        </div>
    </div>
</div>
<script>
    // parking area 최하단의 div 모두 추출
    document.addEventListener("DOMContentLoaded", function () {

        const parking_area_list = document.querySelectorAll(".parkingSpot");
        parking_area_list.forEach(parking_area => {
            console.log(parking_area.innerText);
        })
        parking_area_list.forEach(parking_area => {
            parking_area.addEventListener("click", function () {
                console.log(parking_area.innerText);
            })
        })

    })
</script>
</body>
</html>