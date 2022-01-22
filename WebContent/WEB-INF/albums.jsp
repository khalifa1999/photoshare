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
                        <div class="d-md-flex justify-content-between">
                            <div>
                                <h5 class="mb-0">Albums</h5>

                                <nav aria-label="breadcrumb" class="d-inline-block mt-1">
                                    <ul class="breadcrumb breadcrumb-muted bg-transparent rounded mb-0 p-0">
                                        <li class="breadcrumb-item text-capitalize"><a href="index.html">App</a></li>
                                        <li class="breadcrumb-item text-capitalize active" aria-current="page">Albums</li>
                                    </ul>
                                </nav>
                            </div>

                            <div class="mt-4 mt-sm-0">
                                <a href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#newblogadd">Add Album</a>
                            </div>
                        </div>
                    
                        <div class="row">
                            <c:choose>
                                <c:when test="${requestScope.dbOut eq true}" ><div class="alert alert-danger" role="alert">Database Offline !</div></c:when>
                                <c:when test="${empty requestScope.albums}" ><h4>No Albums</h4></c:when>
                                <c:when test="${!empty requestScope.albums}"> 
                                    <c:forEach items="${requestScope.albums}" var="album">
                                        <div class="col-xl-3 col-lg-4 col-md-6 mt-4">
                                            <div class="card blog blog-primary rounded border-0 shadow overflow-hidden">
                                                <div class="position-relative">
                                                    <img src="<c:out value="data:;base64,${album.imageOut}"/>" class="card-img-top" alt="...">
                                                    <div class="overlay rounded-top"></div>
                                                </div>
                                                <div class="card-body content">
                                                    <h5><a href="javascript:void(0)" class="card-title title text-dark"><c:out value="${album.title}"/></a></h5>
                                                    <div class="post-meta d-flex justify-content-between mt-3">
                                                        
                                                        <a href="<c:url value="/album-details?id=${album.albumId}"/>" class="text-muted readmore">See Album<i class="uil uil-angle-right-b align-middle"></i></a>
                                                    </div>
                                                </div>
                                                <div class="author">
                                                    <small class="text-light user d-block"><i class="uil uil-user"></i><c:out value="${album.desc}"/></small>
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
                        <h5 class="modal-title" id="exampleModalLabel">Add Album</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <div class="modal-body p-3 pt-4">
                        <form method="post" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="d-grid">
                                    <p class="text-muted">Upload your Album Cover image here !</p>
                                    <div class="preview-box d-block justify-content-center rounded shadow overflow-hidden bg-light p-1"></div>
                                    <input type="file" id="input-file" name="input-file" accept="image/*" onchange={handleChange()} hidden />
                                    <label class="btn-upload btn btn-primary mt-4" for="input-file">Upload Image</label>
                                    <div class="text-danger">
                                        <small><c:out value="${albumForm.errors.image}"/></small>
                                    </div>
                                </div>
                            </div><!--end col-->

                            <div class="col-md-8 mt-4 mt-sm-0">
                                <div class="ms-md-4">
                                    
                                        <div class="row">
                                            <div class="col-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Album Title <span class="text-danger">*</span></label>
                                                    <input name="title" id="name" type="text" class="form-control <c:out value="${albumForm.valids.titleInput}"/> <c:out value="${albumForm.errors.titleInput}"/>" placeholder="Title :" value="<c:out value="${albumForm.album.title}"/>">
                                                    <div class="text-danger">
                                                        <small><c:out value="${albumForm.errors.title}"/></small>
                                                    </div>
                                                </div>
                                            </div><!--end col-->
                                            
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Privacy</label>
                                                    <select class="form-control" name="privacy">
                                                        <option value="1">Private</option>
                                                        <option selected value="2">Public</option>
                                                    </select>
                                                </div>
                                            </div><!--end col-->
    
                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Description <span class="text-danger">*</span></label>
                                                    <textarea name="desc" id="comments" rows="4" class="form-control <c:out value="${albumForm.valids.descInput}"/> <c:out value="${albumForm.errors.descInput}"/>" placeholder="Album description :" value="<c:out value="${albumForm.album.desc}"/>"></textarea>
                                                    <div class="text-danger">
                                                        <small><c:out value="${albumForm.errors.desc}"/></small>
                                                    </div>
                                                </div>
                                            </div><!--end col-->
    
                                            <div class="col-lg-12 text-end">
                                                <button type="submit" class="btn btn-primary">Add Album</button>
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
        <!-- End modal -->
        <c:import url="pages/footer.jsp"/>
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


<!-- Mirrored from shreethemes.in/landrick/dashboard/blog.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 02 Nov 2021 22:45:23 GMT -->
</html>