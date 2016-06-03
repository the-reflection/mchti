<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name="defaultTemplate"/>

<tiles:putAttribute name="header">
    <%--<jsp:include page="../layouts/header.jsp"/>--%>
</tiles:putAttribute>

<tiles:putAttribute name="menu">
    <%--<jsp:include page="../layouts/menu.jsp"/>--%>
</tiles:putAttribute>

<div class="box box-primary">
    <div class="box-body">
        <tiles:putAttribute name="body">

            <title><spring:message code="project.title.edit" text="Edit"/></title>

            <div class="btn-group">
                <a href="${pageContext.request.contextPath}/zxEmp/index"><spring:message code="list.link.label"/>&NonBreakingSpace;<spring:message code="zxEmp" text="ZxEmp"/></a> |
                <a href="${pageContext.request.contextPath}/zxEmp/create"><spring:message code="create.link.label"/>&NonBreakingSpace;<spring:message code="zxEmp" text="ZxEmp"/></a>
            </div>

            <h1><spring:message code="edit.page.title"/></h1>

            <div>
                <form:form action="${pageContext.request.contextPath}/zxEmp/edit" enctype="multipart/form-data" commandName="zxEmp" method="POST">
                    <jsp:include page="_form.jsp"/>
                    <div>
                        <a href="${pageContext.request.contextPath}/zxEmp/show/${zxEmp.id}"><spring:message code="show.link.label"/></a>
                        <input type="submit" value="<spring:message code="edit.page.submit.label"/>"/>
                    </div>
                </form:form>
            </div>

        </tiles:putAttribute>  

        <div class="box-footer">
            <tiles:putAttribute name="footer">
                <%--<jsp:include page="../layouts/footer.jsp"/>--%>
            </tiles:putAttribute>  
        </div>
    </div>