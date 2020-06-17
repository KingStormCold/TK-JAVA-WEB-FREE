<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="sidebar-menu">
	<div class="sidebar-header">
		<div class="logo">
			<a href="index.html"><img src="<c:url value='/template/admin/assets/images/icon/logo.png'/>" alt="logo"></a>
		</div>
	</div>
	<div class="main-menu">
		<div class="menu-inner">
			<nav>
			<ul class="metismenu" id="menu">
				<li class="active">
					<a href='<c:url value= "/admin/category/list"/>' aria-expanded="true">
						<i class="fa fa-bars"></i><span>Quản lý thể loại</span>
					</a>
				</li>
				<li class="active">
					<a href='<c:url value= "/admin/news/list"/>'aria-expanded="true">
						<i class="fa fa-newspaper-o"></i><span>Quản lý bài viết</span>
					</a>
				</li>
				<li class="active">
					<a href='<c:url value= "/admin/users/list"/>' aria-expanded="true">
						<i class="fa fa-users"></i><span>Quản lý user</span>
					</a>
				</li>
				<li class="active">
					<a href='<c:url value= "/admin/role/list"/>' aria-expanded="true">
						<i class="fa fa-users"></i><span>Quản lý role</span>
					</a>
				</li>
			</ul>
			</nav>
		</div>
	</div>
</div>