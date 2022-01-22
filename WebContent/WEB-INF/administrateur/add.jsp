<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Users Management</title>
	<link href="<c:url value='../assets/css/styles.css'/>" rel="stylesheet">
	<link href="<c:url value='../assets/css/bootstrap.min.css'/>" rel="stylesheet">
	<link href="<c:url value='../assets/css/sweetalert.min.css'/>" rel="stylesheet">
	 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> 
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
	<script src="<c:url value='../assets/js/sweetalert2.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='../assets/js/jquery.min.js'/>"></script>
</head>
<body>
	<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
	  <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="#">${ adminUser.firstName } ${ adminUser.lastName }</a>
	  <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <input id="inputSearch" class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
	  <div class="navbar-nav">
	    <div class="nav-item text-nowrap">
	      <a class="nav-link px-3" href="<c:url value='/logout'/>">Sign out</a>
	    </div>
	  </div>
	</header>
	<div class="container-fluid">
	  <div class="row">
	    <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
	      <div class="position-sticky pt-3">
	        <ul class="nav flex-column">
	          <li class="nav-item">
	            <a class="nav-link ${ activeHome }" aria-current="page" href="<c:url value='/admin'/>">
	              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-users" aria-hidden="true">
		              <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
		              <circle cx="9" cy="7" r="4"></circle>
		              <path d="M23 21v-2a4 4 0 0 0-3-3.87"></path>
		              <path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
	              </svg>
	              Users List
	            </a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link ${ activeAdd }" href="<c:url value='/admin/add'/>">
	            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" class="bi bi-person-plus-fill" viewBox="0 0 24 24" fill="currentColor" aria-hidden="true">
				  <path d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
				  <path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
				</svg>
	              Add User
	            </a>
	          </li>
	        </ul>
	      </div>
	    </nav>
	  </div>
	</div>
	<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 ">
	    <br>
		    <form class="row g-3 needs-validation" method="post" novalidate>
			    <fieldset>
		    	  <legend>Add a New User</legend>
				  <div class="col-md-5">
				    <input type="text" class="form-control <c:out value="${addForm.valids.firstnameInput}"/> <c:out value="${addForm.errors.firstnameInput}"/> " placeholder="Firstname" id="firstname" name="firstname" value="<c:out value="${addForm.user.firstName}"/>">
				   <div class="text-danger">
				   		<small><c:out value="${addForm.errors.firstname}"/></small>
				   </div>
				  </div><br>
				   <div class="col-md-5">
				    <input type="text" class="form-control <c:out value="${addForm.valids.lastnameInput}"/> <c:out value="${addForm.errors.lastnameInput}"/>" placeholder="Lastname" id="lastname" name="lastname" value="<c:out value="${addForm.user.lastName}"/>">
				    <div class="text-danger">
				   		<small><c:out value="${addForm.errors.lastname}"/></small>
				   </div>
				  </div><br>
				  <div class="col-md-5">				 
				    <input type="email" class="form-control <c:out value="${addForm.valids.mailInput}"/> <c:out value="${addForm.errors.mailInput}"/>" placeholder="Mail" id="mail" name="mail" value="<c:out value="${addForm.user.mail}"/>">
				      <div class="text-danger">
				   		<small><c:out value="${addForm.errors.mail}"/></small>
				   </div>
				  </div><br>
				   <div class="col-md-5">				 
				    <input type="number" class="form-control <c:out value="${addForm.valids.loginInput}"/> <c:out value="${addForm.errors.loginInput}"/>" placeholder="Phone" id="login" name="phoneNumber" value="<c:out value="${addForm.user.phoneNumber}"/>">
				      <div class="text-danger">
				   		<small><c:out value="${addForm.errors.login}"/></small>
				   </div>
				  </div><br>
                                  <div class="col-md-5">
                                  <select class="form-select" aria-label="Role" name="role">
                                    <option selected value="2">Simple User</option>
                                    <option value="1">Administrator</option>
                                  </select>
                                      </div><br>
				  <div class="col-md-5">
				    <input type="password" class="form-control <c:out value="${addForm.errors.passwordInput}"/>" placeholder="Password" id="password" name="password">
				     <div class="text-danger">
				   		<small><c:out value="${addForm.errors.password}"/></small>
				   </div>
				  </div><br>
				   <div class="col-md-5">				    
				    <input type="password" class="form-control <c:out value="${addForm.errors.passwordConfInput}"/>" placeholder="Confirm Password" id="passwordConf" name="passwordConf">
				    <div class="text-danger">
				   		<small><c:out value="${addForm.errors.passwordConf}"/></small>
				   </div>
				  </div>
				  <br>			  
				  <button type="submit" class="btn btn-primary">Submit</button>
			    </fieldset>
			</form>
			  <c:if test="${!empty(addForm.statusMessage)}">
			  <br>
			  	<div class="alert alert-danger" role="alert">
  					<c:out value="${addForm.statusMessage}"/>
				</div>
			  </c:if> 
    </main>
</body>
</html>
