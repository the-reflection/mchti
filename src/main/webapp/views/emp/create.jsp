<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="defaultTemplate"/>

<tiles:putAttribute name="header">
    <jsp:include page="../layouts/header.jsp"/>
</tiles:putAttribute>

<tiles:putAttribute name="menu">
    <jsp:include page="../layouts/menu.jsp"/>
</tiles:putAttribute>

<tiles:putAttribute name="body">

    <title><spring:message code="project.title.create" text="Create"/></title>

    <div>   
        <a href="${pageContext.request.contextPath}/emp/index"><spring:message code="list.link.label"/>&NonBreakingSpace;<spring:message code="emp" text="Emp"/></a>
    </div>

    <h1><spring:message code="create.page.title"/></h1>
    <div>
        <form:form action="${pageContext.request.contextPath}/emp/create" enctype="multipart/form-data" commandName="emp" method="POST">
        <jsp:include page="_form.jsp"/>
        <div>
            <input type="submit" value="<spring:message code="create.page.submit.label"/>"/>
        </div>
        </form:form>
    </div>

</tiles:putAttribute>  

<tiles:putAttribute name="footer">
    <jsp:include page="../layouts/footer.jsp"/>
</tiles:putAttribute> 