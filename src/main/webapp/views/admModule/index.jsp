<%@ page contentType='text/html;charset=utf-8' language='java' %>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions' prefix='fn'%>
<%@ taglib uri='http://www.springframework.org/tags' prefix='spring'%>
<%@ taglib uri='http://www.springframework.org/tags/form' prefix='form'%>


<section class='content-header'><!-- Content Header (Page header) -->
    <h1><spring:message code='default.button.list.label' text='List'/> Adm Module</h1>
    <ul class='top-links'>
        <sec:access url='/admModule/create'>
            <li>
                <a href='${pageContext.request.contextPath}/admModule/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
            </li>
        </sec:access>
    </ul>
</section><!-- /.content-header -->

<section class='content-messages'>
    <%--<jsp:include page='../layouts/_flashMessage.jsp'/>--%>
</section><!-- /.flesh-message -->

<section class='content'><!-- Main content -->
    <div class='box box-primary'>   
        <div class='box-body' style='overflow-x: auto'>
            <table class='dt-default table table-bordered table-striped table-hover table-condensed'>

                <!--<table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>-->
                <thead>
                <th class='center bold'><spring:message code='code' text='Code'/></th>
                <th class='center bold'><spring:message code='fullName' text='Full Name'/></th>
                <th class='center bold'><spring:message code='isActive' text='Is Active'/></th>
                <th class='center bold'><spring:message code='slNo' text='Sl No'/></th>
                <th class='center bold'><spring:message code='remarks' text='Remarks'/></th>

                <th class='center bold'><spring:message code='default.button.action.label' text='Action'/></th> 
                </thead>
                <tbody>
                    <c:if test='${not empty admModules}'>
                        <c:forEach items='${admModules}' var='admModule'  varStatus='loopStatus'>
                            <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                                <td><c:out value='${admModule.code}'/></td>
                                <td><c:out value='${admModule.fullName}'/></td>
                                <td><c:if test='${admModule.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admModule.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                                <td><c:out value='${admModule.slNo}'/></td>
                                <td><c:out value='${admModule.remarks}'/></td>

                                <td class='center action'>
                                    <ul class='top-links'>
                                        <sec:access url='/admModule/show'>
                                            <li>
                                                <a class='btn btn-block btn-info btn-xs' href='${pageContext.request.contextPath}/admModule/show/${admModule.id}'>
                                                    <i class='fa fa-info-circle'></i> <spring:message code='show.link.label'/>
                                                </a>
                                            </li>
                                        </sec:access>
                                        <sec:access url='/admModule/edit'>
                                            <li>
                                                <a class='btn btn-block btn-primary btn-xs' href='${pageContext.request.contextPath}/admModule/edit/${admModule.id}'>
                                                    <i class='fa fa-edit'></i> <spring:message code='edit.link.label'/>
                                                </a>
                                            </li>
                                        </sec:access>
                                        <sec:access url='/admModule/copy'>
                                            <li>
                                                <a class='btn btn-block btn-warning btn-xs' href='${pageContext.request.contextPath}/admModule/copy/${admModule.id}'>
                                                    <i class='fa fa-clone' aria-hidden='true'></i> <spring:message code='default.button.copy.label'/>
                                                </a>
                                            </li>
                                        </sec:access>
                                        <sec:access url='/admModule/delete'>
                                            <li>
                                                <a class='btn btn-block btn-danger btn-xs' href='${pageContext.request.contextPath}/admModule/delete/${admModule.id}' onclick='return confirm('Are you sure to delete?');'>
                                                   <i class='fa fa-remove'></i> <spring:message code='delete.link.label'/>
                                                </a>
                                            </li>
                                        </sec:access>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if> 
                </tbody>
            </table>
            <div class='pagination'>
                <!--<g:paginate total='{testInstanceCount ?: 0}'/>-->
            </div>
        </div><!-- /.box-body table-responsive no-padding -->
    </div><!-- /.box box-primary -->
</section><!-- /.content -->



