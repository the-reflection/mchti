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

<div class="box box-primary">
    <div class="box-body">
        <tiles:putAttribute name="body">

            <title><spring:message code="project.title.create" text="Create"/></title>

            <div class="btn-group">
                <a href="${pageContext.request.contextPath}/lookup/index" class="btn btn-primary"><spring:message code="list.link.label"/>&NonBreakingSpace;<spring:message code="lookup" text="Lookup"/></a>
            </div>

            <h1><spring:message code="create.page.title"/></h1>

            <div>
                <div class="box box-primary">
                    <form:form action="${pageContext.request.contextPath}/lookup/save" commandName="lookup" method="POST">
                        <div class="box-body">
                            <jsp:include page="_form.jsp"/>
                        </div>
                        <div class="box-footer">
                            <div class="btn-group">
                                <button type="submit" class="btn btn-primary"><spring:message code="create.page.submit.label"/></button>
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