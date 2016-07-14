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
                <h1><spring:message code='default.button.create.label' text='Create'/> Zx Any Object</h1>
                <ul class='top-links'>
                    <sec:access url='/zxAnyObject/index'>
                        <li>
                            <a href='${pageContext.request.contextPath}/zxAnyObject/index' class='btn btn-block btn-vimeo btn-xs'>
                                <i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/>
                            </a>
                        </li>
                    </sec:access>
                </ul>
            </section><!-- /.content-header -->

            <section class='content-messages'>
                <%--<jsp:include page='../layouts/_flashMessage.jsp'/>--%>
            </section><!-- /.flesh-message -->

            <section class='content'><!-- Main content -->
                <div id='create-zxAnyObject' class='box box-primary' role='main'>
                    <form:form action='${pageContext.request.contextPath}/zxAnyObject/create'  commandName='zxAnyObject' method='POST'>
                        <div class='box-body'>
                            <jsp:include page='_form.jsp' />
          </div><!-- /.box-body -->
                                <div class='box-footer'>
                                    <button type='reset' class='btn btn-danger pull-right' onclick="return confirm('Are you sure...? you want to reset all value...!');">
                                        <i class='fa fa-refresh'></i> <spring:message code='default.button.reset.label' text='Reset'/>
                                    </button>
                                    <sec:access url='/zxAnyObject/create'>
                                        <button type='submit' class='btn btn-primary'>
                                            <i class='fa fa-save'></i> <spring:message code='default.button.save.label' text='Save'/>
                                        </button>
                                    </sec:access>
                                </div><!-- /.box-footer -->
                            </form:form>
                        </div><!-- /.create-zxAnyObject -->
                    </section><!-- /.content -->
                </div><!-- /.content-wrapper -->
            </tiles:putAttribute>
        
</tiles:insertDefinition>