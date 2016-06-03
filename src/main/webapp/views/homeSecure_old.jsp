<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<title><spring:message code="project.title" text="Project Title"/></title>

<tiles:insertDefinition name="defaultTemplate"/>

<tiles:putAttribute name="header">
    <jsp:include page="layouts/header.jsp"/>
</tiles:putAttribute>

<tiles:putAttribute name="menu">
    <%--<jsp:include page="layouts/menu.jsp"/>--%>
</tiles:putAttribute>

<tiles:putAttribute name="body">

    <h1><spring:message code="welcome.to.oith" text="Welcome to OITH"/></h1>

    <h4><spring:message code="welcome.to.moto.oith" text="An easiest way to java WEB application development!!!"/></h4>

    <h1>It is secure page</h1>
    <span class="floatRight"><a href="<c:url value="/logout"/>">Logout</a></span>
    <p>
        <spring:message code="welcome.to.desc.oith" text="Congratulations, you have successfully started your first OITH application! At the moment
                        this is the default page, feel free to modify it to either redirect to a controller or display whatever
                        content you may choose. Below is a list of controllers that are currently deployed in this application,
                        click on each to execute its default action:"/>
    </p>
    <p>
        <br/>
        <c:forEach var="listValue" items="${lists}">
            <a href="${pageContext.request.contextPath}${listValue}">${listValue}</a><br/>
        </c:forEach>
    </p>

</tiles:putAttribute>

<tiles:putAttribute name="footer">
    <jsp:include page="layouts/footer.jsp"/>
</tiles:putAttribute>

