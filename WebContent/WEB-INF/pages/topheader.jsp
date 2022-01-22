<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <a id="close-sidebar" class="btn btn-icon btn-soft-light" href="javascript:void(0)">
                <i class="ti ti-menu-2"></i>
            </a>
            <div class="search-bar p-0 d-none d-md-block ms-2">
                <div id="search" class="menu-search mb-0">
                    <form role="search" method="get" id="searchform" class="searchform">
                        <div>
                            <input type="text" class="form-control border rounded" name="s" id="s" placeholder="Search Keywords...">
                            <input type="submit" id="searchsubmit" value="Search">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <ul class="list-unstyled mb-0">
            <li class="list-inline-item mb-0">
                <a href="<c:url value="/admin"/>">
                    <div class="btn btn-icon btn-soft-light"><i class="ti ti-settings"></i></div>
                </a>
            </li>
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
                        <c:choose>
                            <c:when test="${empty currentUser}">
                                <a class="dropdown-item text-dark" href="<c:url value="/signup"/>"><span class="mb-0 d-inline-block me-1"><i class="ti ti-home"></i></span> Sign Up</a>
                                <a class="dropdown-item text-dark" href="<c:url value="/login"/>"><span class="mb-0 d-inline-block me-1"><i class="ti ti-settings"></i></span> Sign In</a>
                            </c:when>
                            <c:when test="${not empty currentUser}">
                                <a class="dropdown-item text-dark" href="<c:url value="/home"/>"><span class="mb-0 d-inline-block me-1"><i class="ti ti-home"></i></span> Home</a>
                                <a class="dropdown-item text-dark" href="<c:url value="/albums"/>"><span class="mb-0 d-inline-block me-1"><i class="ti ti-settings"></i></span> Albums</a>
                                <a class="dropdown-item text-dark" href="<c:url value="/logout"/>"><span class="mb-0 d-inline-block me-1"><i class="ti ti-logout"></i></span> Logout</a>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
