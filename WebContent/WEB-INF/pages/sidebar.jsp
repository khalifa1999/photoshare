<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav id="sidebar" class="sidebar-wrapper">
    <div class="sidebar-content" data-simplebar style="height: calc(100% - 60px);">
        <div class="sidebar-brand">
<!--            <a href="index.html">
                <img src="assets/images/logo-dark.png" height="24" class="logo-light-mode" alt="">
                <img src="assets/images/logo-light.png" height="24" class="logo-dark-mode" alt="">
                <span class="sidebar-colored">
                    <img src="assets/images/logo-light.png" height="24" alt="">
                </span>
            </a>-->
        </div>         
        <ul class="sidebar-menu">
            <li class="sidebar-dropdown">
                <a href="<c:url value="/profile"/>"><i class="ti ti-user me-2"></i>Profile Settings</a>
            </li>
            <li class="sidebar-dropdown">
                <a href="<c:url value="/albums"/>"><i class="ti ti-brand-gravatar me-2"></i>Album</a>
            </li>
            <li class="sidebar-dropdown">
                <a href="<c:url value="/gallery"/>"><i class="ti ti-camera me-2"></i>Gallery</a>
            </li>
        </ul>
        <!-- sidebar-menu  -->
</div>
</nav>
            <!-- sidebar-wrapper  -->
