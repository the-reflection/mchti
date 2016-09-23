<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>

    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/functions' prefix='fn'%>
    <%@ taglib uri='http://tiles.apache.org/tags-tiles' prefix='tiles'%>
    <%@ taglib uri='http://www.springframework.org/tags' prefix='spring'%>
    <%@ taglib uri='http://www.springframework.org/tags/form' prefix='form'%>

    <tiles:insertDefinition name='main' >

        <tiles:putAttribute name='body'>

            <div class='content-wrapper'><!-- Content Wrapper. Contains page content -->
                <section class='content-header'><!-- Content Header (Page header) -->
                    <h1><spring:message code='default.button.list.label' text='List'/> Manual Attn Daily</h1>
                    <ul class='top-links'>
                        <sec:access url='/manualAttnDaily/create'>
                            <li>
                                <a href='${pageContext.request.contextPath}/manualAttnDaily/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
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

                                <th class='center bold'><spring:message code='employee' text='Employee'/></th>
                                <th class='center bold'><spring:message code='attnDate' text='Attn Date'/></th>
                                <th class='center bold'><spring:message code='inTime' text='In Time'/></th>
                                <th class='center bold'><spring:message code='outTime' text='Out Time'/></th>
                                <th class='center bold'><spring:message code='remarks' text='Remarks'/></th>
                                <th class='center bold'><spring:message code='entryBy' text='Entry By'/></th>
                                <th class='center bold'><spring:message code='entryDate' text='Entry Date'/></th>
                                <th class='center bold'><spring:message code='editBy' text='Edit By'/></th>
                                <th class='center bold'><spring:message code='editDate' text='Edit Date'/></th>
                                <th class='center bold'><spring:message code='default.button.action.label' text='Action'/></th> 
                                </thead>
                                <tbody>
                                    <c:if test='${not empty manualAttnDailys}'>
                                        <c:forEach items='${manualAttnDailys}' var='manualAttnDaily'  varStatus='loopStatus'>
                                            <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>

                                                <td><c:out value='${manualAttnDaily.employee!=null ? manualAttnDaily.employee :"N/A"}'/></td>
                                                <td><fmt:formatDate value='${manualAttnDaily.attnDate}' type='date' pattern='dd/MM/yyyy'/></td>
                                                <td><fmt:formatDate value='${manualAttnDaily.inTime}' type='time' pattern='HH:mm'/></td>
                                                <td><fmt:formatDate value='${manualAttnDaily.outTime}' type='time' pattern='HH:mm'/></td>
                                                <td><c:out value='${manualAttnDaily.remarks}'/></td>
                                                <td><c:out value='${manualAttnDaily.entryBy}'/></td>
                                                <td><fmt:formatDate value='${manualAttnDaily.entryDate}' type='date' pattern='dd/MM/yyyy HH:mm:ss'/></td>
                                                <td><c:out value='${manualAttnDaily.editBy}'/></td>
                                                <td><fmt:formatDate value='${manualAttnDaily.editDate}' type='date' pattern='dd/MM/yyyy HH:mm:ss'/></td>
                                                <td class='center action'>
                                                    <ul class='top-links'>
                                                        <sec:access url='/manualAttnDaily/show'>
                                                            <li>
                                                                <a class='btn btn-block btn-info btn-xs' href='${pageContext.request.contextPath}/manualAttnDaily/show/${manualAttnDaily.id}'>
                                                                    <i class='fa fa-info-circle'></i> <spring:message code='show.link.label'/>
                                                                </a>
                                                            </li>
                                                        </sec:access>
                                                        <sec:access url='/manualAttnDaily/edit'>
                                                            <li>
                                                                <a class='btn btn-block btn-primary btn-xs' href='${pageContext.request.contextPath}/manualAttnDaily/edit/${manualAttnDaily.id}'>
                                                                    <i class='fa fa-edit'></i> <spring:message code='edit.link.label'/>
                                                                </a>
                                                            </li>
                                                        </sec:access>
                                                        <sec:access url='/manualAttnDaily/copy'>
                                                            <li>
                                                                <a class='btn btn-block btn-warning btn-xs' href='${pageContext.request.contextPath}/manualAttnDaily/copy/${manualAttnDaily.id}'>
                                                                    <i class='fa fa-clone' aria-hidden='true'></i> <spring:message code='default.button.copy.label'/>
                                                                </a>
                                                            </li>
                                                        </sec:access>
                                                        <sec:access url='/manualAttnDaily/delete'>
                                                            <li>
                                                                <a class='btn btn-block btn-danger btn-xs' href='${pageContext.request.contextPath}/manualAttnDaily/delete/${manualAttnDaily.id}' onclick='return confirm('Are you sure to delete?');'>
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
            </div><!-- /.content-wrapper -->
        </tiles:putAttribute>

    </tiles:insertDefinition>