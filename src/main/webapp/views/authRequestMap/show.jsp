<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>

<%@ taglib uri='http://tiles.apache.org/tags-tiles' prefix='tiles'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri='http://www.springframework.org/tags' prefix='spring'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions' prefix='fn'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@ taglib uri='http://www.springframework.org/tags/form' prefix='form'%>

<tiles:insertDefinition name='main' >

    <tiles:putAttribute name='body'>
        
        <div class='content-wrapper'><!-- Content Wrapper. Contains page content -->
            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code='default.button.show.label' text='Show'/> Auth Request Map</h1>
                <ul class='top-links'>
                    <sec:access url='/authRequestMap/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/authRequestMap/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
                        </li>
                    </sec:access>
                    <sec:access url='/authRequestMap/index'>
                        <li>
                            <a href='${pageContext.request.contextPath}/authRequestMap/index' class='btn btn-block btn-info btn-xs'><i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/></a>
                        </li>
                    </sec:access>
                </ul>
            </section><!-- /.content-header -->

            <section class='content-messages'>
                <%--<jsp:include page='../layouts/_flashMessage.jsp'/>--%>
            </section><!-- /.flesh-message -->

            <section class='content'><!-- Main content -->
                <div class='box box-primary'>
                    <div class='box-body'>
                        <fieldset class='show-page'>
                             <form:hidden path='id'/>
        <ol class='property-list'>

        <c:if test='${authRequestMap.configAttribute!=null && !authRequestMap.configAttribute.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='configAttribute' text='Config Attribute'/>: 
                </span>
                <span class='property-value' aria-labelledby='configAttribute'>
                    <c:out value='${authRequestMap.configAttribute}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authRequestMap.httpMethod!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='httpMethod' text='Http Method'/>: 
                </span>
                <span class='property-value' aria-labelledby='httpMethod'>
                    <c:out value='${authRequestMap.httpMethod}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authRequestMap.url!=null && !authRequestMap.url.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='url' text='Url'/>: 
                </span>
                <span class='property-value' aria-labelledby='url'>
                    <c:out value='${authRequestMap.url}'/>
                </span>
            </li>
        </c:if>

        </ol>

    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class='box-footer'>
                            <a href='${pageContext.request.contextPath}/authRequestMap/edit/${authRequestMap.id}' class='btn btn-primary'><i class='fa fa-edit'></i> <spring:message code='edit.link.label'/></a> 
                            <a href='${pageContext.request.contextPath}/authRequestMap/copy/${authRequestMap.id}' class='btn btn-warning' onclick="return alert('Unique field should not contain same value...!');"><i class='fa fa-clone'></i> <spring:message code='default.button.copy.label'/></a>             
                            <a href='${pageContext.request.contextPath}/authRequestMap/delete/${authRequestMap.id}' class='btn btn-danger pull-right' onclick="return confirm('Are you sure...? you want to delete this record...!');"><i class='fa fa-remove'></i> <spring:message code='delete.link.label'/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>