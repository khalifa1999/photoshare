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

        <div class="page-wrapper landrick-theme toggled">
            <c:import url="pages/sidebar.jsp"/>
            <!-- Start Page Content -->
            <main class="page-content bg-light">
                <c:import url="pages/topheader.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between align-items-center">
                            <h5 class="mb-0">Gallery</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-2 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item text-capitalize"><a href="index.html"></a></li>
                                    <li class="breadcrumb-item text-capitalize active" aria-current="page">Gallery</li>
                                </ul>
                            </nav>
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