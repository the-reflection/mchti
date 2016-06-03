<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Reflection - Access Denied</title>
    </head>
    <body>
        <div class="generic-container">
            <div class="authbar">
                <span>
                    <spring:message code="default.message.notAuthorised.label" text="Sorry, You are not authorized to access that page...!"/>
                </span> 
                <span class="floatRight">
                    <a href="<c:url value="/logout"/>"><spring:message code="default.message.logout.label" text="Logout"/></a>
                </span>
            </div>
        </div>
    </body>
</html>