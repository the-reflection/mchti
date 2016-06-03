<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name="defaultTemplate"/>

<tiles:putAttribute name="header">
    <jsp:include page="../layouts/header.jsp"/>
</tiles:putAttribute>

<tiles:putAttribute name="menu">
    <jsp:include page="../layouts/menu.jsp"/>
</tiles:putAttribute>

<div class="box box-primary">
    <div class="box-body">
        <tiles:putAttribute name="body">

            <title><spring:message code="project.title.edit" text="Edit"/></title>

            <div class="btn-group">
                <a href="${pageContext.request.contextPath}/lookup/index" class="btn btn-info"><spring:message code="list.link.label"/>&NonBreakingSpace;<spring:message code="lookup" text="Lookup"/></a> 
                <a href="${pageContext.request.contextPath}/lookup/create" class="btn btn-primary"><spring:message code="create.link.label"/>&NonBreakingSpace;<spring:message code="lookup" text="Lookup"/></a>
            </div>

            <h1><spring:message code="edit.page.title"/></h1>
            <div class="box box-primary">

                <form:form action="${pageContext.request.contextPath}/lookup/update" commandName="lookup" method="POST">
                    <input type="hidden" name="version" value="${lookup.version}"/>
                    <div class="box-body">
                        <jsp:include page="_form.jsp"/>
                    </div>
                    <div class="box-footer">
                        <!--<div class="form-group">-->
                        <div class="btn-group">
                            <a class="btn btn-info" href="${pageContext.request.contextPath}/lookup/show/<c:out value="${lookup.id}"/>"><spring:message code="show.link.label"/></a>
                            <button type="submit" class="btn btn-primary" ><spring:message code="edit.page.submit.label"/></button>
                        </div>
                    </div>
                </form:form>
            </div>

        </tiles:putAttribute>  

        <div class="box-footer">
            <tiles:putAttribute name="footer">
                <jsp:include page="../layouts/footer.jsp"/>
            </tiles:putAttribute>  
        </div>
    </div>
</div>