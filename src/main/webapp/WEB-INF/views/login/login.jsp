<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>코리아 IT 주차관리 시스템 - 로그인</title>

    <!-- CSS -->
    <link href="/assets/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>

<body class="bg-gradient">
<div id="layoutAuthentication">
    <div id="layoutAuthentication_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header bg-primary text-white text-center">
                                <h3 class="fw-light my-3">로그인</h3>
                            </div>
                            <div class="card-body">
                                <c:if test="${not empty error}">
                                    <div class="alert alert-danger text-center">${error}</div>
                                </c:if>

                                <form action="${pageContext.request.contextPath}/login.do" method="post">
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="username" name="username" type="email" required />
                                        <label for="username">이메일</label>
                                    </div>

                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="password" name="password" type="password" required />
                                        <label for="password">비밀번호</label>
                                    </div>

                                    <div class="form-check mb-3">
                                        <input class="form-check-input" id="remember" name="remember" type="checkbox" />
                                        <label class="form-check-label" for="remember">자동 로그인</label>
                                    </div>

                                    <div class="d-flex justify-content-end mt-4">
                                        <button type="submit" class="btn btn-primary w-100">로그인</button>
                                    </div>
                                </form>
                            </div>
                            <div class="card-footer text-center py-3 small text-muted">
                                코리아 IT 주차관리 시스템
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>

    <div id="layoutAuthentication_footer">
        <footer class="py-4 bg-light mt-auto">
            <div class="container-fluid px-4">
                <div class="d-flex justify-content-between small">
                    <div class="text-muted">© Korea IT Parking System</div>
                    <div><a href="#">Privacy Policy</a></div>
                </div>
            </div>
        </footer>
    </div>
</div>

<!-- JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="/assets/js/scripts.js"></script>
</body>
</html>