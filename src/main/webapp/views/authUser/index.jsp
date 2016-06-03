<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Users List</title>
        <link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
        <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
    </head>

    <body>
        <div class="generic-container">
            <%@include file="../authheader.jsp" %>	
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading"><span class="lead">List of Users </span></div>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Full Name</th>
                            <th>User ID</th>
                                <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                                <th width="100"></th>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ADMIN')">
                                <th width="100"></th>
                                </sec:authorize>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.fullName}</td>
                                <td>${user.username}</td>
                                <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                                    <td><a href="<c:url value='/authUser/edit/${user.username}' />" class="btn btn-success custom-width">edit</a></td>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ADMIN')">
                                    <td><a href="<c:url value='/authUser/delete/${user.username}' />" class="btn btn-danger custom-width">delete</a></td>
                                </sec:authorize>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <sec:authorize access="hasRole('ADMIN')">
                <div class="well">
                    <a href="<c:url value='/authUser/create' />">Add New User</a>
                </div>
            </sec:authorize>
        </div>
    </body>
</html>