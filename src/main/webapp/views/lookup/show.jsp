<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

            <title><spring:message code="project.title.show" text="Show"/></title>


            <div class="btn-group">  
                <a href="${pageContext.request.contextPath}/lookup/index" class="btn btn-block btn-info btn-xs"><i class="fa fa-plus-circle "></i> <spring:message code="list.link.label"/> <spring:message code="lookup" text="Lookup"/></a> 
                <!--<sec:access url="/lookup/create">-->
                <a href="${pageContext.request.contextPath}/lookup/create" class="btn btn-block btn-primary btn-xs"><i class="fa fa-plus-circle "></i> <spring:message code="create.link.label"/> <spring:message code="lookup" text="Lookup"/></a>
                <!--</sec:access>-->
            </div>

            <h1><spring:message code="show.page.title"/></h1>

            <div class="box box-primary">
                <div class="box-body">
                    <form:hidden path="id"/>

                    <div class="property-show">

                        <div class="form-group">
                            <div class="col-xs-12 col-sm-6 col-md-3 col-lg-2">
                                <span class="property-label bold">
                                    <spring:message code="code" text="Code"/> 
                                </span>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-3 col-lg-2">
                                <span class="property-value" aria-labelledby="code">
                                    <c:out value="${lookup.code}"/>
                                </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-xs-12 col-sm-6 col-md-3 col-lg-2">
                                <span class="property-label bold">
                                    <spring:message code="title" text="title"/> 
                                </span>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-3 col-lg-2">
                                <span class="property-value" aria-labelledby="title">
                                    <c:out value="${lookup.title}"/>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-12 col-sm-6 col-md-3 col-lg-2">
                                <span class="property-label bold">
                                    <spring:message code="titleBng" text="titleBng"/> 
                                </span>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-3 col-lg-2">
                                <span class="property-value" aria-labelledby="titleBng">
                                    <c:out value="${lookup.titleBng}"/>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-12 col-sm-6 col-md-3 col-lg-2">
                                <span class="property-label bold">
                                    <spring:message code="keyword" text="keyword"/> 
                                </span>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-3 col-lg-2">
                                <span class="property-value" aria-labelledby="keyword">
                                    <c:out value="${lookup.keyword}"/>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-12 col-sm-6 col-md-3 col-lg-2">
                                <span class="property-label bold">
                                    <spring:message code="remarks" text="remarks"/> 
                                </span>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-3 col-lg-2">
                                <span class="property-value" aria-labelledby="remarks">
                                    <c:out value="${lookup.remarks}"/>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-12 col-sm-6 col-md-3 col-lg-2">
                                <span class="property-label bold">
                                    <spring:message code="isActive" text="isActive"/> 
                                </span>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-3 col-lg-2">
                                <span class="property-value" aria-labelledby="isActive">
                                    <c:out value="${lookup.isActive}"/>
                                </span>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="box-footer">
                    <!--<div class="form-group">-->
                    <div class="btn-group">
                        <a href="${pageContext.request.contextPath}/lookup/edit/${lookup.id}" class="btn btn-block btn-primary btn-xs"><spring:message code="edit.link.label"/></a> 
                        <a href="${pageContext.request.contextPath}/lookup/copy/${lookup.id}" class="btn btn-block btn-warning btn-xs"><spring:message code="copy.link.label"/></a>             
                        <a href="${pageContext.request.contextPath}/lookup/delete/${lookup.id}" class="btn btn-block btn-danger btn-xs" onclick="return confirm('Are you sure to delete?');" ><spring:message code="delete.link.label"/></a>
                    </div>
                </div>
            </div>
        </tiles:putAttribute>  

        <div class="box-footer">
            <tiles:putAttribute name="footer">
                <jsp:include page="../layouts/footer.jsp"/>
            </tiles:putAttribute>  
        </div>
    </div>