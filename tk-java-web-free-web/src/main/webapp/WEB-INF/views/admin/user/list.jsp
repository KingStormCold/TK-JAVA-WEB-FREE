<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
<div class="col-sm-10 main-content">
   <div>
       <div class="add-user">
           <a title="Add user" class="btn btn-primary btn-add-user" data-toggle="modal" data-target="#myModal" data-keyboard="false" data-backdrop="static">+</a>
       </div>
       <!-- The Modal -->
       <div class="modal" id="myModal">
           <div class="modal-dialog">
               <div class="modal-content modal-content-user">
                   <form action="/user-role/user" method="GET" enctype="multipart/form-data">
                       <!-- Modal Header -->
                       <div class="modal-header">
                           <h4 class="modal-title">Insert User</h4>
                           <button type="button" class="close" data-dismiss="modal">&times;</button>
                       </div>

                       <!-- Modal body -->
                       <div class="modal-body modal-user">
                           
                               <div class ="form-group">
                                   <label for="usr" class="form-label">User name</label>
                                   <input type="text" class="form-control form-input" name="user_name" placeholder="Ex: tuankul">
                               </div>
                               <div class ="form-group">
                                   <label for="usr" class="form-label">Password</label>
                                   <input type="text" class="form-control form-input" name = "password" placeholder="Ex: 123456">
                               </div>
                               <div class ="form-group">
                                   <label for="usr" class="form-label">Full name</label>
                                   <input type="text" class="form-control form-input" name="full_name" placeholder="Ex: Thái Thanh Tuấn">
                               </div>
                               <div class ="form-group">
                                   <label for="usr" class="form-label">Email</label>
                                   <input type="email" class="form-control form-input" name="email" placeholder="Ex: test@gmail.com">
                               </div>
                               <div class ="form-group">
                                   <label for="usr" class="form-label">Phone</label>
                                   <input type="number" class="form-control form-input" name = "phone" placeholder="012332323232">
                               </div>
                               <div class ="form-group">
                                   <label for="usr" class="form-label">Address</label>
                                   <input type="text" class="form-control form-input" name = "address" placeholder="349 Nguyễn Đình Chiểu Quận 1">
                               </div>
                               <div class ="form-group">
                                   <label for="usr" class="form-label">Avatar</label>
                                   <input type="file" style="padding-top: 3px;" class="form-control form-input" id = "form-avatar">
                               </div>
                               <div class ="">
                                   <label class ="form-label-hidden"></label>
                                   <img src="" alt="avatar" class = "form-avatar-profile" id = "avater-review">
                               </div>
                               <div class ="form-group">
                                   <label for="usr" class="form-label">Role</label>
                                   <div style=" display: -webkit-inline-box;" class = "col-sm-4 have-role">
                                       <div class = "role-header">
                                           <p class = "span-role">Have rights</p>
                                       </div>
                                       <div class = "role-have-right">
                                           <c:forEach var ="i" begin="1" end = "10">
                                           
                                               <label><input type="checkbox" value="" class = "checkbox-role"><c:out value="${i}"></c:out>12312321321312</label>
                                           </c:forEach>
                                       </div>
                                   </div>
                                   <div style=" display: -webkit-inline-box;" class = "col-sm-4 not-role"> 
                                       <div class = "role-header">
                                           <p class = "span-role">List Role</p>
                                       </div>
                                       <div class = "list-role">
                                           <c:forEach var ="i" begin="1" end = "10">
                                               <label><input type="checkbox" value="" class = "checkbox-role"><c:out value="${i}"></c:out>123123</label>
                                           </c:forEach>
                                       </div>
                                   </div>
                               </div>
                       </div>

                       <!-- Modal footer -->
                        <div class="modal-footer modal-footer-user">
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
                                <th class="table-th">Title</th>
                                <th class="table-th">Update Date</th>
                                <th class="table-th">Update By</th>
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
                                <td class="table-td">123123</td>
                                <td class="table-td"><i class ="fa fa-edit"></i><i class ="fa fa-remove icon-small"></i></td>
                            </tr>
                            <tr>
                                <td class="table-td">rewrwe</td>
                                <td class="table-td">wer</td>
                                <td class="table-td">werwer</td>
                                <td class="table-td">werwer</td>
                                <td class="table-td">ewrwer</td>
                                <td class="table-td">werwe</td>
                                <td class="table-td"><i class ="fa fa-edit"></i><i class ="fa fa-remove icon-small"></i></td>
                            </tr>
                            <tr>
                                <td class="table-td">gfg</td>
                                <td class="table-td">g</td>
                                <td class="table-td">fgf</td>
                                <td class="table-td">fgfdg</td>
                                <td class="table-td">dfgfd</td>
                                <td class="table-td">dfgdfg</td>
                                <td class="table-td"><i class ="fa fa-edit"></i><i class ="fa fa-remove icon-small"></i></td>
                            </tr>
                            <tr>
                                <td class="table-td">gfg</td>
                                <td class="table-td">g</td>
                                <td class="table-td">fgf</td>
                                <td class="table-td">fgfdg</td>
                                <td class="table-td">dfgfd</td>
                                <td class="table-td">dfgdfg</td>
                                <td class="table-td"><i class ="fa fa-edit"></i><i class ="fa fa-remove icon-small"></i></td>
                            </tr>
                            <tr>
                                <td class="table-td">gfg</td>
                                <td class="table-td">g</td>
                                <td class="table-td">fgf</td>
                                <td class="table-td">fgfdg</td>
                                <td class="table-td">dfgfd</td>
                                <td class="table-td">dfgdfg</td>
                                <td class="table-td"><i class ="fa fa-edit"></i><i class ="fa fa-remove icon-small"></i></td>
                            </tr>
                            <tr>
                                <td class="table-td">gfg</td>
                                <td class="table-td">g</td>
                                <td class="table-td">fgf</td>
                                <td class="table-td">fgfdg</td>
                                <td class="table-td">dfgfd</td>
                                <td class="table-td">dfgdfg</td>
                                <td class="table-td"><i class ="fa fa-edit"></i><i class ="fa fa-remove icon-small"></i></td>
                            </tr>
                            <tr>
                                <td class="table-td">gfg</td>
                                <td class="table-td">g</td>
                                <td class="table-td">fgf</td>
                                <td class="table-td">fgfdg</td>
                                <td class="table-td">dfgfd</td>
                                <td class="table-td">dfgdfg</td>
                                <td class="table-td"><i class ="fa fa-edit"></i><i class ="fa fa-remove icon-small"></i></td>
                            </tr>
                            <tr>
                                <td class="table-td">gfg</td>
                                <td class="table-td">g</td>
                                <td class="table-td">fgf</td>
                                <td class="table-td">fgfdg</td>
                                <td class="table-td">dfgfd</td>
                                <td class="table-td">dfgdfg</td>
                                <td class="table-td"><i class ="fa fa-edit"></i><i class ="fa fa-remove icon-small"></i></td>
                            </tr>
                            <tr>
                                <td class="table-td">gfg</td>
                                <td class="table-td">g</td>
                                <td class="table-td">fgf</td>
                                <td class="table-td">fgfdg</td>
                                <td class="table-td">dfgfd</td>
                                <td class="table-td">dfgdfg</td>
                                <td class="table-td"><i class ="fa fa-edit"></i><i class ="fa fa-remove icon-small"></i></td>
                            </tr>
                            <tr>
                                <td class="table-td">gfg</td>
                                <td class="table-td">g</td>
                                <td class="table-td">fgf</td>
                                <td class="table-td">fgfdg</td>
                                <td class="table-td">dfgfd</td>
                                <td class="table-td">dfgdfg</td>
                                <td class="table-td"><i class ="fa fa-edit"></i><i class ="fa fa-remove icon-small"></i></td>
                            </tr>
                            <tr>
                                <td class="table-td">gfg</td>
                                <td class="table-td">g</td>
                                <td class="table-td">fgf</td>
                                <td class="table-td">fgfdg</td>
                                <td class="table-td">dfgfd</td>
                                <td class="table-td">dfgdfg</td>
                                <td class="table-td"><i class ="fa fa-edit"></i><i class ="fa fa-remove icon-small"></i></td>
                            </tr>
                            <tr>
                                <td class="table-td">gfg</td>
                                <td class="table-td">g</td>
                                <td class="table-td">fgf</td>
                                <td class="table-td">fgfdg</td>
                                <td class="table-td">dfgfd</td>
                                <td class="table-td">dfgdfg</td>
                                <td class="table-td"><i class ="fa fa-edit"></i><i class ="fa fa-remove icon-small"></i></td>
                            </tr>
                            <tr>
                                <td class="table-td">gfg</td>
                                <td class="table-td">g</td>
                                <td class="table-td">fgf</td>
                                <td class="table-td">fgfdg</td>
                                <td class="table-td">dfgfd</td>
                                <td class="table-td">dfgdfg</td>
                                <td class="table-td"><i class ="fa fa-edit"></i><i class ="fa fa-remove icon-small"></i></td>
                            </tr>
                            <tr>
                                <td class="table-td">gfg</td>
                                <td class="table-td">g</td>
                                <td class="table-td">fgf</td>
                                <td class="table-td">fgfdg</td>
                                <td class="table-td">dfgfd</td>
                                <td class="table-td">dfgdfg</td>
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
  <script src = "<c:url value='../../template1/admin/js/js-admin-user.js'/>"></script>
</body>
</html>