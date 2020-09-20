<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<div class = "col-sm-2 main-menu">
    <div class = "main-auth">
      <h3>Authorization</h3><i class ="fa fa-plus main-auth-icon"></i> 
      <ul class ="main-auth-list">
        <li class = "parent-li">
          <a href='<c:url value= "/admin/role/list"/>' aria-expanded="true">
            <i class ="fa fa-users small-icon"></i>
            <span class="active-span">Role Manager</span>
          </a>
        </li>
        <li class = "parent-li">
          <a href='<c:url value= "/admin/user/list"/>' aria-expanded="true">
            <i class ="fa fa-users small-icon"></i>
            <span class = "active-span">User Manager</span>
          </a>
        </li>
      </ul>
      <hr>
    </div>
    <div class = "menu-manager">
      <h3>Main Menu</h3><i class ="fa fa-plus menu-manager-icon"></i>
      <ul class ="main-menu-manager-list">
        <li class = "parent-li category-manager">
          <a href="" aria-expanded="true">
            <i class ="fa fa-bars small-icon"></i>
            <span class="active-span">Category Manager</span>
          </a>
        </li>
        <li class = "parent-li">
          <a href="" aria-expanded="true">
            <i class ="fa fa-users small-icon"></i>
            <span class = "active-span">News Manager</span>
          </a>
        </li>
      </ul>
      <hr>
    </div>
</div>