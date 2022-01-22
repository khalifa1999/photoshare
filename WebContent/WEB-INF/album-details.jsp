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
                            <h5 class="mb-0">Album Content</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-2 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item text-capitalize"><a href="<c:url value="/albums"/>"><c:out value="${album.title}"/></a></li>
                                    <li class="breadcrumb-item text-capitalize active" aria-current="page">Images</li>
                                </ul>
                            </nav>
                            <div class="mt-4 mt-sm-0">
                                <a href="#" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#newblogadd">Add Image</a>
                                <a href="<c:url value="/album-edit?id=${album.albumId}"/>">
                                    <div class="btn btn-outline-success"><i class="ti ti-settings"></i></div>
                                </a>
                            </div>
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
                                            <a 
		            		 	data-href="<c:url value='/albums/delete-image?id=${image.imageId}'/>"
		            		 	class="btn btn-danger btn-sm"
		            		 	data-bs-toggle="modal" 
		            		 	data-bs-target="#exampleModal"
		            		 >-</a>
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
                <!-- End -->
            </main>
            <!--End page-content" -->
        </div>
        <!-- page-wrapper -->

                <!-- Start Modal -->
        <div class="modal fade" id="newblogadd" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel">Add Image</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <div class="modal-body p-3 pt-4">
                        <form method="post" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="d-grid">
                                    <p class="text-muted">Upload your Image here, Please click "Upload Image" Button.</p>
                                    <div class="preview-box d-block justify-content-center rounded shadow overflow-hidden bg-light p-1"></div>
                                    <input type="file" id="input-file" name="input-file" accept="image/*" onchange={handleChange()} hidden />
                                    <label class="btn-upload btn btn-primary mt-4" for="input-file">Upload Image</label>
                                    <div class="text-danger">
                                        <small><c:out value="${imgForm.errors.img}"/></small>
                                    </div>
                                </div>
                            </div><!--end col-->

                            <div class="col-md-8 mt-4 mt-sm-0">
                                <div class="ms-md-4">
                                    
                                        <div class="row">
                                            <div class="col-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Image Title <span class="text-danger">*</span></label>
                                                    <input name="title" id="name" type="text" class="form-control <c:out value="${imgForm.valids.titleInput}"/> <c:out value="${imgForm.errors.titleInput}"/>" placeholder="Title :" value="<c:out value="${imgForm.image.title}"/>">
                                                </div>
                                                  <div class="text-danger">
                                                        <small><c:out value="${imgForm.errors.title}"/></small>
                                                    </div>
                                            </div><!--end col-->
                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Description <span class="text-danger">*</span></label>
                                                    <textarea name="desc" id="comments" rows="4" class="form-control <c:out value="${imgForm.valids.descInput}"/> <c:out value="${imgForm.errors.descInput}"/>" placeholder="Image description :" value="<c:out value="${imgForm.image.desc}"/>"></textarea>
                                                </div>
                                                 <div class="text-danger">
                                                        <small><c:out value="${imgForm.errors.desc}"/></small>
                                                    </div>
                                            </div><!--end col-->
    
                                            <div class="col-lg-12 text-end">
                                                <button type="submit" class="btn btn-primary">Add Image</button>
                                            </div><!--end col-->
                                        </div>
                                </div>
                            </div><!--end col-->
                        </div><!--end row-->
                    </form>

                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content modal-dialog-centered" role="document">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">Confirm Suppression</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  Are you sure you want to delete this Image !
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No !</button>
                  <a class="btn btn-danger btn-ok">Yes, I'm Sure !</a>
                </div>
              </div>
            </div>
        </div>
        <!-- End modal -->
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
        <script src="assets/js/jquery.min.js"></script>
               <script>
            $('#exampleModal').on('show.bs.modal', function(e) {
                $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
            });
	</script>
        <script>
            const handleChange = () => {
            const fileUploader = document.querySelector('#input-file');
                const getFile = fileUploader.files
                if (getFile.length !== 0) {
                    const uploadedFile = getFile[0];
                    readFile(uploadedFile);
                }
            }

            const readFile = (uploadedFile) => {
                if (uploadedFile) {
                    
                    const reader = new FileReader();
                    reader.onload = () => {
                        const parent = document.querySelector('.preview-box');
                        parent.innerHTML = '<img class="preview-content img-fluid rounded" src="' +reader.result+ '"/>';
                    };
                    reader.readAsDataURL(uploadedFile);
                }
            };
        </script>

    </body>


<!-- Mirrored from shreethemes.in/landrick/dashboard/gallery-one.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 02 Nov 2021 22:45:37 GMT -->
</html>