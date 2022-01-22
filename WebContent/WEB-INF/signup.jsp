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

        <div class="back-to-home rounded d-none d-sm-block">
            <a href="<c:url value="/home"/>" class="btn btn-icon btn-primary"><i data-feather="home" class="icons"></i></a>
        </div>
        <!-- Hero Start -->
        <section class="bg-home bg-circle-gradiant d-flex align-items-center">
            <div class="bg-overlay bg-overlay-white"></div>
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="form-signin p-4 bg-white rounded shadow">
                            <form class="login-form mt-4" method="post">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">First name <span class="text-danger">*</span></label>
                                                <div class="form-icon position-relative">
                                                    <i data-feather="user" class="fea icon-sm icons"></i>
                                                    <input type="text" class="form-control ps-5 <c:out value="${signUp.valids.firstnameInput}"/> <c:out value="${signUp.errors.firstnameInput}"/>" placeholder="First Name" name="firstname" value="<c:out value="${signUp.user.firstName}"/>">
                                                </div>
                                                <div class="text-danger">
                                                    <small><c:out value="${signUp.errors.firstname}"/></small>
                                                </div>
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-md-6">
                                            <div class="mb-3"> 
                                                <label class="form-label">Last name <span class="text-danger">*</span></label>
                                                <div class="form-icon position-relative">
                                                    <i data-feather="user-check" class="fea icon-sm icons"></i>
                                                    <input type="text" class="form-control ps-5 <c:out value="${signUp.valids.lastnameInput}"/> <c:out value="${signUp.errors.lastnameInput}"/>" placeholder="Last Name" name="lastname" value="<c:out value="${signUp.user.lastName}"/>">
                                                </div>
                                                <div class="text-danger">
                                                    <small><c:out value="${signUp.errors.lastname}"/></small>
                                                </div>
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-md-12">
                                            <div class="mb-3">
                                                <label class="form-label">Your Email <span class="text-danger">*</span></label>
                                                <div class="form-icon position-relative">
                                                    <i data-feather="mail" class="fea icon-sm icons"></i>
                                                    <input type="email" class="form-control ps-5 <c:out value="${signUp.valids.mailInput}"/> <c:out value="${signUp.errors.mailInput}"/>" placeholder="Email" name="mail" value="<c:out value="${signUp.user.mail}"/>">
                                                </div>
                                                <div class="text-danger">
                                                    <small><c:out value="${signUp.errors.mail}"/></small>
                                                </div>
                                            </div>
                                        </div><!--end col-->
                                        <div class="col-md-12">
                                            <div class="mb-3">
                                                <label class="form-label">Phone Number <span class="text-danger">*</span></label>
                                                <div class="form-icon position-relative">
                                                    <i data-feather="phone" class="fea icon-sm icons"></i>
                                                    <input type="number" class="form-control ps-5 <c:out value="${signUp.valids.phoneInput}"/> <c:out value="${signUp.errors.phoneInput}"/>" placeholder="Phone" name="phoneNumber" value="<c:out value="${signUp.user.phoneNumber}"/>">
                                                </div>
                                                <div class="text-danger">
                                                    <small><c:out value="${signUp.errors.phoneNumber}"/></small>
                                                </div>
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-md-12">
                                            <div class="mb-3">
                                                <label class="form-label">Password <span class="text-danger">*</span></label>
                                                <div class="form-icon position-relative">
                                                    <i data-feather="key" class="fea icon-sm icons"></i>
                                                    <input type="password" class="form-control ps-5 <c:out value="${signUp.valids.passwordInput}"/> <c:out value="${signUp.errors.passwordInput}"/>" placeholder="password" name="password">
                                                </div>
                                                <div class="text-danger">
                                                    <small><c:out value="${signUp.errors.password}"/></small>
                                                </div>
                                            </div>
                                        </div><!--end col-->
                                                  <div class="col-md-12">
                                            <div class="mb-3">
                                                <label class="form-label">Confirm Password <span class="text-danger">*</span></label>
                                                <div class="form-icon position-relative">
                                                    <i data-feather="key" class="fea icon-sm icons"></i>
                                                    <input type="password" class="form-control ps-5 <c:out value="${signUp.valids.passwordConfInput}"/> <c:out value="${signUp.errors.passwordConfInput}"/>" placeholder="Confirm Password" name="confPass" >
                                                </div>
                                                <div class="text-danger">
                                                    <small><c:out value="${signUp.errors.confPass}"/></small>
                                                </div>
                                            </div>
                                        </div><!--end col-->
                                        <div class="col-md-12">
                                            <div class="d-grid">
                                                <button type="submit" class="btn btn-primary">Register</button>
                                            </div>
                                        </div><!--end col-->

                                        <div class="mx-auto">
                                            <p class="mb-0 mt-3"><small class="text-dark me-2">Already have an account ?</small> <a href="<c:url value="/login"/>" class="text-dark fw-bold">Sign in</a></p>
                                        </div>
                                    </div><!--end row-->
                                </form>
                            <c:if test="${!empty(signUp.statusMessage)}">
			  <br>
			  	<div class="alert alert-danger" role="alert">
  					<c:out value="${signUp.statusMessage}"/>
				</div>
			  </c:if> 
                        </div>
                    </div>
                </div>
            </div> <!--end container-->
        </section><!--end section-->
        <!-- Hero End -->
        
    <c:import url="pages/footer.jsp"/>
    </body>


<!-- Mirrored from shreethemes.in/landrick/dashboard/signup.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 02 Nov 2021 22:45:41 GMT -->
</html>