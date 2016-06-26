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
                <h1><spring:message code='default.button.copy.label' text='Copy'/> Company</h1>
                <ul class='top-links'>
                    <ul class='top-links'>
                        <sec:access url='/company/create'>
                            <li>
                                <a href='${pageContext.request.contextPath}/company/create' class='btn btn-block btn-primary btn-xs'>
                                    <i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='Create'/>
                                </a>
                            </li>
                        </sec:access>
                        <sec:access url='/company/index'>
                            <li>
                                <a href='${pageContext.request.contextPath}/company/index' class='btn btn-block btn-info btn-xs'>
                                    <i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/>
                                </a>
                            </li>
                        </sec:access>
                    </ul>
                </ul>
            </section><!-- /.content-header -->

            <section class='content-messages'>
                <%--<jsp:include page='../layouts/_flashMessage.jsp'/>--%>
            </section><!-- /.flesh-message -->

            <section class='content'><!-- Main content -->
                <div class='box box-primary'>
                    <form:form action='${pageContext.request.contextPath}/company/copy' enctype='multipart/form-data' commandName='company' method='POST'>
                        <div class='box-body'>
                            <jsp:include page='_form.jsp' />
                        </div>
                        <div class='box-footer'>
                            <sec:access url='/company/show'>
                                <a href='${pageContext.request.contextPath}/company/show/${company.id}' class='btn btn-primary'><i class='fa fa-info-circle'></i> <spring:message code='default.button.show.label' text='Show'/></a> 
                            </sec:access>
                            <sec:access url='/company/copy'>
                                <button type='submit' class='btn btn-warning' >
                                    <i class='fa fa-clone' aria-hidden='true'></i> <spring:message code='default.button.copy.label' text='Copy'/>
                                </button>
                            </sec:access>
                        </div>
                    </form:form>
                </div>
            </section><!-- /.content -->
        </div><!-- /.content-wrapper -->
    </tiles:putAttribute>

</tiles:insertDefinition>