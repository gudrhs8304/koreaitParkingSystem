<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<!-- Vehicle In Page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>코리아 IT 주차관리 시스템</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="/assets/js/scripts.js"></script>
    <!-- Bootstrap 5 CSS (CDN) -->
    <link href="/assets/css/styles.css" rel="stylesheet">

    <!-- SB Admin 스타일 또는 사용자 정의 CSS -->
    <%--    <link href="/assets/css/add_style.css" rel="stylesheet">--%>
    <!--폰트 어썸 임폿 -->
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

</head>

<body class="sb-nav-fixed">
<!-- topbar 영역-->
<%@ include file="/layout/topbar.jsp" %>
<div id="layoutSidenav">
    <!-- sidebar 영역 -->
    <%@ include file="/layout/sidebar.jsp" %>
    <div id="layoutSidenav_content">
        <!-- 여기서 부터 메인 작업 시작. -->
        <h1>입차 페이지 테스트</h1>
        <hr>
        <h2>입차관리</h2>
        <hr>
        <div class="container">
            <div class="text-center">
                <h6>
                    <%
                        //                        response.setIntHeader("Refresh", 5); // 페이지 새로고침 설정 (5초 후)
//                        Date day = new java.util.Date();
                        String am_pm;
                        Calendar calendar = Calendar.getInstance();

                        int hour = calendar.get(Calendar.HOUR); //12시간제 (0~11)
                        int minute = calendar.get(Calendar.MINUTE);
                        int second = calendar.get(Calendar.SECOND);

                        //오전 오후 판단
                        am_pm = calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";

                        // 0시는 12시로 표기 (예: AM 0:00:00 -> AM 12:00:00)
                        if (hour == 0) {
                            hour = 12;
                        }

                        // 두 자리수 포맷으로 보기 좋게 출력
                        String currentTime = String.format("%02d:%02d:%02d %s", hour, minute, second, am_pm);
                        out.println("현재 입차 시간 기록 : " + currentTime + "<br>");

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
                        String currentDate = simpleDateFormat.format(new Date());
                        out.println("현재 입차 일시 기록 : " + currentDate + "<br>");
                    %>
                </h6>
            </div>
        </div>
        <%--        &lt;%&ndash;%> --%>
        <%--        <%            String carNum = request.getParameter("carNum");--%>
        <%--                if (carNum != null && carNum.length() > 9) {%>--%>


        <%--        <%
        --%>
        <%-- 원래는 차량번호를 입력받을때 9자리 이상 입력시 차량번호를 다시 입력해달라는 문구를넣고싶었는데 위에 로직이랑
        합쳐지지 않아서 일단... 이렇게 두었어요 --%>
        <%--        <h2 class='alert alert-danger'>차량번호를 다시 입력해 주세요!</h2>--%>

        <%
            int spot_number = 10;
            int usedSpace = 0;
            int availableSpace = spot_number - usedSpace;
        %>

        //
        // boolean[] occupiedStatus = {true, true, true, false, true, false, true, false, true, true}; // db의 샘플 값들.
        //
        // for (boolean spot : occupiedStatus) {
        // if (spot) usedSpace++;
        // }
        // boolean is_occupied = false;
        // //새로운 차량이 입차된 경우, 상황에 따라 is_occupied 값을 여기서 업데이트 할 수 있음.
        //
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <form name="Entry" action="/main.jsp" class="form-horizontal" method="post">
                        <div class="form-group row">
                            <label for="parkingSpaceNum" class="col-sm-5">주차 공간 수</label>
                            <div class="col-sm-6">
                                <input type="text" id="parkingSpaceNum" name="parkingSpaceNum"
                                       class="form-control" value="<%=spot_number%>" readonly>
                            </div>
                            <div class="form-group row">
                                <label for="psnAvailable" class="col-sm-5">주차 가능 수</label>
                                <div class="col-sm-6">
                                    <input type="text" id="psnAvailable" name="psnAvailable"
                                           class="form-control" value="<%=availableSpace%>" readonly>
                                </div>
                                <div class="form-group row">
                                    <label for="carNum" class="col-sm-6">차량번호</label>
                                    <div class="col-sm-6">
                                        <input type="text" id="carNum" name="carNum" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="carType" class="col-sm-6">차량 유형</label>
                                    <div class="col-sm-20">
                                        <p><input type="radio" id="carType" name="carType" value="일반" checked>일반 |
                                            <input type="radio" id="carType" name="carType" value="장애인">장애인 |
                                            <input type="radio" id="carType" name="carType" value="전기차">전기차 |
                                            <input type="radio" id="carType" name="carType" value="경차">경차 | </p>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="name" class="col-sm-6">운전자명</label>
                                    <div class="col-sm-5">
                                        <input type="text" id="name" name="name" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="month_yn" class="col-sm-6">월 정액 회원 여부</label>
                                    <div class="col-sm-15">
                                        <p><input type="radio" id="month_yn" name="month_yn" value="예" checked>예
                                            <input type="radio" id="month_yn" name="month_yn" value="아니오">아니오</p>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="phone" class="col-sm-6">연락처</label>
                                    <div class="col-sm-6">
                                        <input type="text" id="phone" name="phone" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" id="rgt" class="btn btn-primary">등록</button>
                                        <%-- <input type="submit" class="btn btn-primary" value="등록">--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                </div>
                </form>
            </div>
        </div>
        <hr>
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            document.getElementById('rgt').addEventListener('click', function (e) {
                e.preventDefault();

                const carNum = document.getElementById('carNum').value.trim();
                const name = document.getElementById('name').value.trim();
                const phone = document.getElementById('phone').value.trim();

                // 등록 버튼을 눌렀을시 재대로 입력이 되었다면 성공 아니면 실패
                if (carNum === "" || carNum.length() > 9 || name === "" || phone === "") {
                    alert("등록이 실패 되었습니다. 다시 입력해 주세요!")
                } else {
                    alert("등록이 완료 되었습니다.");
                    // document.forms["Entry"].submit();
                }
            })
        })
    </script>


    <!-- footer 영역 -->
    <%@ include file="/layout/footer.jsp" %>
</div>
</div>


<!-- Bootstrap JS (Popper + Bootstrap Bundle) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>


<!-- 선택: Chart.js, jQuery 등 추가 가능 -->
<%--<script src="/assets/js/scripts.js"></script>--%>

<!-- 부트스트랩 기본 js 임포트 -->
<!-- 차트는 추후 삭제 가능성 있음. -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="/assets/demo/chart-area-demo.js"></script>
<script src="/assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>
<script src="/assets/js/datatables-simple-demo.js"></script>

</body>
</html>
