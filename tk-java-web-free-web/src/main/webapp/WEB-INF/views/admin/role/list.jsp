<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<div class="col-sm-10 main-content">
      <div>
          <div class="add-role">
              <a title="Add Role" class="btn btn-primary btn-add-role" data-toggle="modal" data-target="#myModal" data-keyboard="false" data-backdrop="static">+</a>
          </div>
          <!-- The Modal -->
          <div class="modal" id="myModal">
              <div class="modal-dialog">
                  <div class="modal-content modal-content-role">
                      <form action="/user-role/user" method="GET" enctype="multipart/form-data">
                          <!-- Modal Header -->
                          <div class="modal-header">
                              <h4 class="modal-title">Insert Role</h4>
                              <button type="button" class="close" data-dismiss="modal">&times;</button>
                          </div>

                          <!-- Modal body -->
                          <div class="modal-body modal-body-role">
                              <div class ="form-group">
                                  <label for="usr" class="form-label">Name</label>
                                  <input type="text" class="form-control form-input-role" name="name">
                              </div>
                              <div class ="form-group">
                                  <label for="usr" class="form-label">Description</label>
                                  <input type="text" class="form-control form-input-role" name = "description">
                              </div>
                          </div>

                          <!-- Modal footer -->
                          <div class="modal-footer modal-footer-role">
                              <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                              <button type="submit" class="btn btn-success" >Create</button>
                          </div>
                      </form>
                  </div>
              </div>
          </div>
          <div class="card">
              <div class="card-body">
                  <div class="data-tables">
                      <table id="example" class="hover">
                          <thead class="text-capitalize">
                              <tr>
                                  <th class="table-th">No.</th>
                                  <th class="table-th">Created By</th>
                                  <th class="table-th">Created Date</th>
                                  <th class="table-th">Name</th>
                                  <th class="table-th">Description</th>
                                  <th class="table-th">Action</th>
                              </tr>
                          </thead>
                          <tbody>
                              <tr>
                                  <td class="table-td">123123</td>
                                  <td class="table-td">123123</td>
                                  <td class="table-td">123123</td>
                                  <td class="table-td">123123</td>
                                  <td class="table-td">123123</td>
                                  <td class="table-td"><i class ="fa fa-edit"></i><i class ="fa fa-remove icon-small"></i></td>
                              </tr>
                          </tbody>
                      </table>
                      <ul class = "pagination-sm" id="pagination-user"></ul>
                  </div>
              </div>
          </div>
      </div>
  </div>
  <script src = "<c:url value='../../template1/admin/js/pagination.js'/>"></script>
  <script src = "<c:url value='../../template1/admin/js/js-admin-role.js'/>"></script>
</body>

</html>