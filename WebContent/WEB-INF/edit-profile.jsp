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

            <!-- sidebar-wrapper -->

            <!-- Start Page Content -->
            <main class="page-content bg-light">
                <c:import url="pages/topheader.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between align-items-center">
                            <h5 class="mb-0">Profile Setting</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-2 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item text-capitalize"><a href="index.html">Landrick</a></li>
                                    <li class="breadcrumb-item text-capitalize"><a href="#">Proflie</a></li>
                                    <li class="breadcrumb-item text-capitalize active" aria-current="page">Setting</li>
                                </ul>
                            </nav>
                        </div>
                        <form method="post">
                        <div class="row">
                            <div class="col-lg-4 mt-4">
                                <div class="card border-0 rounded shadow">
                                    <div class="card-body">
                                        <h5 class="text-md-start text-center mb-0">Personal Detail :</h5>
        
                                        <div class="mt-4 text-md-start text-center d-sm-flex">
                                            <img src="assets/images/client/13.jpg" class="avatar float-md-left avatar-medium rounded-circle shadow me-md-4" alt="">
                                            
                                            <div class="mt-md-4 mt-3 mt-sm-0">
                                                <a href="javascript:void(0)" class="btn btn-primary mt-2">Change Picture</a>
                                            </div>
                                        </div>
        
                                            <div class="row mt-4">
                                                <div class="col-md-6">
                                                    <div class="mb-3">
                                                        <label class="form-label">First Name</label>
                                                        <div class="form-icon position-relative">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-user fea icon-sm icons"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg>
                                                            <input name="firstname" id="first" type="text" class="form-control ps-5 <c:out value="${editForm.valids.firstnameInput}"/> <c:out value="${editForm.errors.firstnameInput}"/>" placeholder="First Name :" value="<c:out value="${editForm.user.firstName}"/>">
                                                           <div class="text-danger">
                                                                <small><c:out value="${editForm.errors.firstname}"/></small>
                                                           </div>
                                                        </div>
                                                    </div>
                                                </div><!--end col-->
                                                <div class="col-md-6">
                                                    <div class="mb-3">
                                                        <label class="form-label">Last Name</label>
                                                        <div class="form-icon position-relative">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-user-check fea icon-sm icons"><path d="M16 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="8.5" cy="7" r="4"></circle><polyline points="17 11 19 13 23 9"></polyline></svg>
                                                            <input name="lastname" id="last" type="text" class="form-control ps-5 <c:out value="${editForm.valids.lastnameInput}"/> <c:out value="${editForm.errors.lastnameInput}"/>" placeholder="Last Name :" value="<c:out value="${editForm.user.lastName}"/>">
                                                        <div class="text-danger">
                                                            <small><c:out value="${editForm.errors.lastname}"/></small>
                                                       </div>
                                                        </div>
                                                    </div>
                                                </div><!--end col-->
                                                <div class="col-md-6">
                                                    <div class="mb-3">
                                                        <label class="form-label">Your Email</label>
                                                        <div class="form-icon position-relative">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-mail fea icon-sm icons"><path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"></path><polyline points="22,6 12,13 2,6"></polyline></svg>
                                                            <input name="mail" id="email" type="email" class="form-control ps-5 <c:out value="${editForm.valids.mailInput}"/> <c:out value="${editForm.errors.mailInput}"/>" placeholder="Your email :" value="<c:out value="${editForm.user.mail}"/>">
                                                        <div class="text-danger">
                                                            <small><c:out value="${editForm.errors.mail}"/></small>
                                                       </div>
                                                        </div>
                                                    </div> 
                                                </div><!--end col-->
                                                <div class="col-md-6">
                                                    <div class="mb-3">
                                                        <label class="form-label">Phone Number</label>
                                                        <div class="form-icon position-relative">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-phone fea icon-sm icons"><path d="M19 21l-7-5-7 5V5a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2z"></path></svg>
                                                            <input name="phoneNumber" id="occupation" type="text" class="form-control ps-5 <c:out value="${editForm.valids.loginInput}"/> <c:out value="${editForm.errors.loginInput}"/>" placeholder="Phone Number :" value="<c:out value="${editForm.user.phoneNumber}"/>">
                                                        <div class="text-danger">
                                                            <small><c:out value="${editForm.errors.login}"/></small>
                                                       </div>
                                                        </div>
                                                    </div> 
                                                </div><!--end col-->
                                            </div><!--end row-->
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <input type="submit" id="submit" name="send" class="btn btn-primary" value="Save Changes">
                                                </div><!--end col-->
                                            </div><!--end row-->
                                      
                                    </div>
                                </div>
                            </div><!--end col-->

                            <div class="col-lg-4 mt-4">
                                <div class="card border-0 rounded shadow p-4">
                                    <h5 class="mb-0">Change password :</h5>
                                    
                                        <div class="row mt-4">
                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Password :</label>
                                                    <div class="form-icon position-relative">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-key fea icon-sm icons"><path d="M21 2l-2 2m-7.61 7.61a5.5 5.5 0 1 1-7.778 7.778 5.5 5.5 0 0 1 7.777-7.777zm0 0L15.5 7.5m0 0l3 3L22 7l-3-3m-3.5 3.5L19 4"></path></svg>
                                                        <input name="password" type="password" class="form-control ps-5 <c:out value="${editForm.errors.passwordInput}"/>" placeholder="New password" value="<c:out value="${editForm.user.password}"/>">
                                                    </div>
                                                </div>
                                            </div><!--end col-->
        
                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Re-type Password :</label>
                                                    <div class="form-icon position-relative">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-key fea icon-sm icons"><path d="M21 2l-2 2m-7.61 7.61a5.5 5.5 0 1 1-7.778 7.778 5.5 5.5 0 0 1 7.777-7.777zm0 0L15.5 7.5m0 0l3 3L22 7l-3-3m-3.5 3.5L19 4"></path></svg>
                                                        <input name="passwordConf" type="password" class="form-control ps-5 <c:out value="${editForm.errors.passwordConfInput}"/>" placeholder="Re-type New password" value="<c:out value="${editForm.passwordConf}"/>">
                                                    </div>
                                                </div>
                                            </div><!--end col-->
     
                                        </div><!--end row-->
                                    
                                </div>

                
                            </div><!--end col-->
                        </div><!--end row-->
                        </form>
                    </div>
                </div><!--end container-->
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
        <!-- Main Js -->
        <script src="assets/js/plugins.init.js"></script>
        <script src="assets/js/app.js"></script>
        
    


<!-- Mirrored from shreethemes.in/landrick/dashboard/profile-setting.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 02 Nov 2021 22:45:22 GMT -->
</body>