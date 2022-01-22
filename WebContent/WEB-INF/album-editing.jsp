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
           <c:import url="pages/sidebar.jsp"/>

            <!-- Start Page Content -->
            <main class="page-content bg-light">
                <c:import url="pages/topheader.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between align-items-center">
                            <h5 class="mb-0">Album Settings</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-2 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item text-capitalize"><a href="#">Album</a></li>
                                    <li class="breadcrumb-item text-capitalize active" aria-current="page">Setting</li>
                                </ul>
                            </nav>
                        </div>
                        <form method="post" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-lg-4 mt-4">
                                <div class="card border-0 rounded shadow">
                                    <div class="card-body">
                                        <h5 class="text-md-start text-center mb-0">Album Detail</h5>
                                        
                                        <div class="d-grid">
                                            <div class="preview-box d-block justify-content-center rounded shadow overflow-hidden bg-light p-1">
                                                <img src="<c:out value="data:;base64,${editAlbum.album.imageOut}"/>" class="preview-content img-fluid rounded" alt="">
                                            </div>
                                            <input type="file" id="input-file" name="input-file" accept="image/*" onchange={handleChange()} hidden />
                                            <label class="btn-upload btn btn-primary mt-4" for="input-file">Change Image</label>
                                            <div class="text-danger">
                                                <small><c:out value="${editAlbum.errors.image}"/></small>
                                            </div>
                                        </div>
                                            
<!--                                        <div class=cmt-4 text-md-start text-center d-sm-flex">
                                            
                                            <div class="mt-md-4 mt-3 mt-sm-0">
                                                <a href="javascript:void(0)" class="btn btn-upload btn-primary mt-2">Change Picture</a>
                                            </div>
                                        </div>
        -->
                                        
                                            <div class="row mt-4">
                                                <div class="col-md-6">
                                                    <div class="mb-3">
                                                        <label class="form-label">Title</label>
                                                        <input name="title" id="first" type="text" class="form-control <c:out value="${editAlbum.valids.titleInput}"/> <c:out value="${editAlbum.errors.titleInput}"/>" placeholder="Title :" value="<c:out value="${editAlbum.album.title}"/>"/>              
                                                        <div class="text-danger">
                                                            <small><c:out value="${editAlbum.errors.title}"/></small>
                                                        </div>
                                                    </div>
                                                </div><!--end col-->
                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Description</label>
                                                        <div class="form-icon position-relative">
                                                            <textarea name="desc" id="comments" rows="4" class="form-control <c:out value="${editAlbum.valids.descInput}"/> <c:out value="${editAlbum.errors.descInput}"/>" placeholder="Description :" value=""><c:out value="${editAlbum.album.desc}"/></textarea>
                                                            <div class="text-danger">
                                                                <small><c:out value="${editAlbum.errors.desc}"/></small>
                                                            </div>
                                                        </div>
                                                          
                                                    </div>
                                                </div>
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
                                    <h5 class="mb-0">Change Visibility :</h5>
                                    
                                        <div class="row mt-4">
                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Visibility :</label>
                                                    <div class="form-icon position-relative">
                                                        <div class="p-4">
                                                            <div class="custom-control custom-radio custom-control-inline">
                                                                <div class="form-check mb-0">
                                                                    <input class="form-check-input" <c:if test="${editAlbum.album.isPrivate eq 1}">checked</c:if> type="radio" name="privacy" id="flexRadioDefault1" value="1">
                                                                    <label class="form-check-label" for="flexRadioDefault1">Private</label>
                                                                </div>
                                                            </div>
                                                            <div class="custom-control custom-radio custom-control-inline">
                                                                <div class="form-check mb-0">
                                                                    <input class="form-check-input" <c:if test="${editAlbum.album.isPrivate eq 2}">checked</c:if> type="radio" name="privacy" id="flexRadioDefault2" value="2">
                                                                    <label class="form-check-label" for="flexRadioDefault2">Public</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div><!--end col-->
                                        </div><!--end row-->
                                    
                                </div>
                                <div class="card border-0 rounded shadow p-4 mt-4">
                                    <h5 class="mb-0 text-danger">Delete Album :</h5>
        
                                    <div class="mt-4">
                                        <h6 class="mb-0">Do you want to delete the Album? Please press below "Delete" button</h6>
                                        <div class="mt-4">
                                            <a data-href="<c:url value="/albums/delete?id=${album.albumId}"/>" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">Delete Album</a>
                                        </div><!--end col-->
                                    </div>
                                </div>
                            </div><!--end col-->
                        </div><!--end row-->
                        </form><!--end form-->
                    </div>
                </div><!--end container-->
                <!-- End -->
            </main>
            <!--End page-content" -->
        </div>
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content modal-dialog-centered" role="document">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">Confirm Suppression</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  Are you sure you want to delete this Album ?
                  All The Images In the album are gonna be deleted !
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No !</button>
                  <a class="btn btn-danger btn-ok">Yes, I'm Sure !</a>
                </div>
              </div>
            </div>
        </div>
        <!-- page-wrapper -->
        <c:import url="pages/footer.jsp"/>
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
