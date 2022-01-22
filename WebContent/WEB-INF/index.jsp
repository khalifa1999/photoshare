<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import url="pages/head.jsp"/>
    <body>
        <!-- Loader -->
        <!-- <div id="preloader">
            <div id="status">
                <div class="spinner">
                    <div class="double-bounce1"></div>
                    <div class="double-bounce2"></div>
                </div>
            </div>
        </div> -->
        <!-- Loader -->

        <div class="page-wrapper landrick-theme">
            <!-- Start Page Content -->
            <main class="page-content bg-light">
                <div class="top-header">
    <div class="header-bar d-flex justify-content-between">
        <div class="d-flex align-items-center">
            <a href="#" class="logo-icon me-3">
                <img src="assets/images/logo-icon.png" height="30" class="small" alt="">
                <span class="big">
                    <img src="assets/images/logo-dark.png" height="24" class="logo-light-mode" alt="">
                    <img src="assets/images/logo-light.png" height="24" class="logo-dark-mode" alt="">
                </span>
            </a>
        </div>

        <ul class="list-unstyled mb-0">
            <li class="list-inline-item mb-0">
                <a href="<c:url value="/admin"/>">
                    <div class="btn btn-icon btn-soft-light"><i class="ti ti-settings"></i></div>
                </a>
            </li>
            <c:choose>
                <c:when test="${empty currentUser}">
                    <li class="list-inline-item mb-0">
                        <a class="btn btn-soft-light" href="<c:url value="/signup"/>"><span class="mb-0 d-inline-block me-1"><i class="ti ti-user-plus"></i></span> Sign Up</a>
                    </li>
                    <li class="list-inline-item mb-0">
                        <a class="btn btn-soft-success" href="<c:url value="/login"/>"><span class="mb-0 d-inline-block me-1"><i class="ti ti-user-check"></i></span> Sign In</a>
                    </li>
                </c:when>
                <c:when test="${not empty currentUser}">
                <li class="list-inline-item mb-0 ms-1">
                <div class="dropdown dropdown-primary">
                    <button type="button" class="btn btn-soft-light dropdown-toggle p-0" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="assets/images/client/13.jpg" class="avatar avatar-ex-small rounded" alt=""></button>
                    <div class="dropdown-menu dd-menu dropdown-menu-end bg-white shadow border-0 mt-3 py-3" style="min-width: 200px;">
                        <a class="dropdown-item d-flex align-items-center text-dark pb-3" href="/albums">
                            <img src="assets/images/client/13.jpg" class="avatar avatar-md-sm rounded-circle border shadow" alt="">
                            <div class="flex-1 ms-2">
                                <span class="d-block"><c:out value="${currentUser.firstName}"/> <c:out value="${currentUser.lastName}"/></span>
                            </div>
                        </a>
                        <a class="dropdown-item text-dark" href="<c:url value="/home"/>"><span class="mb-0 d-inline-block me-1"><i class="ti ti-home"></i></span> Home</a>
                        <a class="dropdown-item text-dark" href="<c:url value="/albums"/>"><span class="mb-0 d-inline-block me-1"><i class="ti ti-settings"></i></span> Albums</a>
                        <a class="dropdown-item text-dark" href="<c:url value="/logout"/>"><span class="mb-0 d-inline-block me-1"><i class="ti ti-logout"></i></span> Logout</a>
                    </div>
                    </div>
                </li>
                </c:when>
            </c:choose>
             
        </ul>
    </div>
</div>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between align-items-center">
                            <h5 class="mb-0">Gallery</h5>
                        </div>
                    
                        <div class="row row-cols-md-2 row-cols-lg-4 row-cols-xl-5 row-cols-1">
                               <c:choose>
                                <c:when test="${requestScope.dbOut eq true}" ><div class="alert alert-danger" role="alert">Database Offline !</div></c:when>
                                <c:when test="${empty requestScope.images}" ><h4>No Images</h4></c:when>
                                <c:when test="${!empty requestScope.images}"> 
                                    <c:forEach items="${requestScope.images}" var="image">
                                        <div class="col mt-4">
                                            <div class="card work-container work-modern position-relative overflow-hidden shadow rounded border-0">
                                                <div class="card-body p-0">
                                                    <a href="<c:out value="data:;base64,${image.imageOut}"/>" class="lightbox d-inline-block" title="">
                                                        <img src="<c:out value="data:;base64,${image.imageOut}"/>" class="img-fluid" alt="<c:out value="${image.title}"/>">
                                                    </a>
                                                </div>
                                            </div>
                                        </div><!--end col-->
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                        </div><!--end row-->
        
                        <div class="row">
                            <!-- PAGINATION START -->
                            <div class="col-12 mt-4">
                                <ul class="pagination justify-content-center mb-0">
                                    <li class="page-item"><a class="page-link" href="javascript:void(0)" aria-label="Previous">Prev</a></li>
                                    <li class="page-item active"><a class="page-link" href="javascript:void(0)">1</a></li>
                                    <li class="page-item"><a class="page-link" href="javascript:void(0)" aria-label="Next">Next</a></li>
                                </ul>
                            </div><!--end col-->
                            <!-- PAGINATION END -->
                        </div><!--end row-->
                    </div>
                </div><!--end container-->
                <!-- Footer Start -->
                <!-- End -->
            </main>
            <!--End page-content" -->
        </div>
        <!-- page-wrapper -->
        <!-- javascript -->
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <!-- simplebar -->
        <script src="assets/js/simplebar.min.js"></script>
        <!-- Icons -->
        <script src="assets/js/feather.min.js"></script>
        <!-- Lightbox -->
        <script src="assets/js/tobii.min.js"></script>
        <!-- Main Js -->
        <script src="assets/js/plugins.init.js"></script>
        <script src="assets/js/app.js"></script>
    </body>
<!-- Mirrored from shreethemes.in/landrick/dashboard/gallery-one.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 02 Nov 2021 22:45:37 GMT -->
</html>