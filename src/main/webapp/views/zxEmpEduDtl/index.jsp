<page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>

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
                <h1><spring:message code='default.button.list.label' text='List'/> Zx Emp Edu Dtl</h1>
                <ul class='top-links'>
                    <sec:access url='/zxEmpEduDtl/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/zxEmpEduDtl/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
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
                        <th class='center bold'><spring:message code='exam' text='Exam'/></th>
                    <th class='center bold'><spring:message code='examOrientDate' text='Exam Orient Date'/></th>
                    <th class='center bold'><spring:message code='remarks' text='Remarks'/></th>
                    <th class='center bold'><spring:message code='slNo' text='Sl No'/></th>
                    <th class='center bold'><spring:message code='certificateFile' text='Certificate File'/></th>
                    <th class='center bold'><spring:message code='picFile' text='Pic File'/></th>
                    <th class='center bold'><spring:message code='zxEmp' text='Zx Emp'/></th>
                    <th class='center bold'><spring:message code='zxEmpWhoCheckedBy' text='Zx Emp Who Checked By'/></th>

    <th class='center bold'><spring:message code='default.button.action.label' text='Action'/></th> 
            </thead>
            <tbody>
            <c:if test='${not empty zxEmpEduDtls}'>
                <c:forEach items='${zxEmpEduDtls}' var='zxEmpEduDtl'  varStatus='loopStatus'>
                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><c:out value='${zxEmpEduDtl.exam}'/></td>
                        <td><fmt:formatDate value='${zxEmpEduDtl.examOrientDate}' type='date' pattern='dd/MM/yyyy'/></td>
                        <td><c:out value='${zxEmpEduDtl.remarks}'/></td>
                        <td><c:out value='${zxEmpEduDtl.slNo}'/></td>
                        <td><c:url var='certificateFile' value='/zxEmpEduDtl/getFile/${zxEmpEduDtl.certificateFile}'/>
<a target='_blank' href='${certificateFile}'>${zxEmpEduDtl.certificateFile}</a></td>
                        <td><c:url var='picFile' value='/zxEmpEduDtl/getPhotoTumb/${zxEmpEduDtl.picFile}'/>
<img alt='${zxEmpEduDtl.picFile}' src='${picFile}'/></td>
                        <td><c:out value='${zxEmpEduDtl.zxEmp!=null ? zxEmpEduDtl.zxEmp :"N/A"}'/></td>
                        <td><c:out value='${zxEmpEduDtl.zxEmpWhoCheckedBy!=null ? zxEmpEduDtl.zxEmpWhoCheckedBy :"N/A"}'/></td>

                           <td class='center action'>
                            <ul class='top-links'>
                            <sec:access url='/zxEmpEduDtl/show'>
                                <li>
                                <a class='btn btn-block btn-info btn-xs' href='${pageContext.request.contextPath}/zxEmpEduDtl/show/${zxEmpEduDtl.id}'>
                                    <i class='fa fa-info-circle'></i> <spring:message code='show.link.label'/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url='/zxEmpEduDtl/edit'>
                                <li>
                                <a class='btn btn-block btn-primary btn-xs' href='${pageContext.request.contextPath}/zxEmpEduDtl/edit/${zxEmpEduDtl.id}'>
                                    <i class='fa fa-edit'></i> <spring:message code='edit.link.label'/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url='/zxEmpEduDtl/copy'>
                                <li>
                                <a class='btn btn-block btn-warning btn-xs' href='${pageContext.request.contextPath}/zxEmpEduDtl/copy/${zxEmpEduDtl.id}'>
                                    <i class='fa fa-clone' aria-hidden='true'></i> <spring:message code='default.button.copy.label'/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url='/zxEmpEduDtl/delete'>
                                <li>
                                <a class='btn btn-block btn-danger btn-xs' href='${pageContext.request.contextPath}/zxEmpEduDtl/delete/${zxEmpEduDtl.id}' onclick='return confirm('Are you sure to delete?');'>
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