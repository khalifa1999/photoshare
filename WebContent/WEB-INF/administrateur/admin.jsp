<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Users Management</title>
	<link href="<c:url value='assets/css/styles.css'/>" rel="stylesheet">
	<link href="<c:url value='assets/css/bootstrap.min.css'/>" rel="stylesheet">
	<link href="<c:url value='assets/css/sweetalert.min.css'/>" rel="stylesheet">
	<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
	<script src="<c:url value='assets/js/sweetalert2.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='assets/js/jquery.min.js'/>"></script>
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
                    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
		<br>
		<h3>List Of Users</h3>
		<br>
		<c:if test="${sessionScope.logForm.status eq true}">
	  		<script>
		  	    $(document).ready(function(){       
		  	        Swal.fire({        
		  	           type: 'success',
		  	           title: 'Logged In Successfully !',
		  	           showConfirmButton: false,
		  	           timer: 1500
		  	        })
		  	    });
  			</script>
		</c:if>
		
		<c:choose>
			<c:when test="${param.added eq true}">
				<div class="alert alert-success" role="alert">Added New User Successfully !</div>
			</c:when>
			<c:when test="${param.updated eq true}">
				<div class="alert alert-success" role="alert">Updated User Successfully !</div>
			</c:when>
			<c:when test="${param.deleted eq true}">
				<div class="alert alert-success" role="alert">Deleted User Successfully !</div>
			</c:when>
		</c:choose>
  		<c:choose> 
 			<c:when test="${requestScope.dbOut eq true}" ><div class="alert alert-danger" role="alert">Database Offline !</div></c:when>
 			<c:when test="${empty requestScope.users}" ><h4>No Users</h4></c:when>
  			<c:when test="${!empty requestScope.users}"> 
				<table id="dtable" class="table table-striped table-bordered">
				  <thead class="table-dark">
				    <tr>
				      <th scope="col">#</th>
				      <th scope="col">First Name</th>
				      <th scope="col">Last Name</th>
				      <th scope="col">Mail</th>
				      <th scope="col">Role</th>
                                      <th scope="col">Phone</th>
				      <th scope="col">Actions</th>
				    </tr>
				  </thead>
				  <tbody>
				  <c:forEach items="${requestScope.users}" var="user">
					    <tr>
					      <th scope="row"><c:out value="${user.userId}" /></th>
		 			      <td><c:out value="${user.firstName}" /></td>
					      <td><c:out value="${user.lastName}" /></td>
					      <td><c:out value="${user.mail}" /></td>
					      <td>
                                                  <c:choose>
                                                      <c:when test="${user.role eq 1}"><c:out value="Admin"/></c:when>
                                                      <c:when test="${user.role eq 2}"><c:out value="User"/></c:when>
                                                  </c:choose>
                                              </td>
                                              <td><c:out value="${user.phoneNumber}" /></td> 
					      <td>
		              		 <a href="<c:url value='/admin/edit?id=${user.userId}'/>" class="btn btn-success"><i class="fas fa-edit"></i></a>
		            		 <a 
		            		 	data-href="<c:url value='/admin/delete?id=${user.userId}'/>"
		            		 	class="btn btn-danger"
		            		 	data-bs-toggle="modal" 
		            		 	data-bs-target="#exampleModal"
		            		 ><i class="far fa-trash-alt"></i></a>
		                  </td> 
					    </tr> 
	 				</c:forEach> 
				  </tbody>
				</table>
  			</c:when>
  		</c:choose>
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content modal-dialog-centered" role="document">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Confirm Suppression</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        Are you sure you want to delete this user ?
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No !</button>
		        <a class="btn btn-danger btn-ok">Yes, I'm Sure !</a>
		      </div>
		    </div>
		  </div>
		</div>
	</main>
        <script src="<c:url value='assets/js/bootstrap.bundle.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='assets/js/jquery.dataTables.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='assets/js/dataTables.bootstrap5.min.js'/>"></script>
	<script>
            window.setTimeout(function() {
                $(".alert").fadeTo(500, 0).slideUp(500, function(){
                    $(this).remove(); 
                });
            }, 2000);
            $('#exampleModal').on('show.bs.modal', function(e) {
                $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
            });
            $(document).ready(function() {
                    oTable = $('#dtable').DataTable({
            "pagingType": "full_numbers"
                    });
            } );
            $('#inputSearch').keyup(function(){
                  oTable.search($(this).val()).draw() ;
            })
              $('#dtable').removeClass( 'display' ).addClass('table table-striped table-bordered');
	</script>
</body>
</html>

