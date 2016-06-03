<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

<tiles:putAttribute name="body">

    <title><spring:message code="project.title.index" text="Index"/></title>

    <div class="content-wrapper">

        <section class="content-header"><!-- Content Header (Page header) -->

            <h1><spring:message code="list.page.title"  text="Index"/></h1>

            <ul class="top-links">
                <sec:access url="/zxEmpEduDtl/create">
                    <li>
                        <a class="btn btn-block btn-primary btn-xs" href="${pageContext.request.contextPath}/zxEmpEduDtl/create">
                            <i class="fa fa-plus-circle "></i> New
                        </a>
                    </li>
                </sec:access>
            </ul>
        </section><!-- /.content-header -->

        <section>
            <%--<jsp:include page="../layouts/_flashMessage.jsp"/>--%>
        </section><!--Flash Message-->

        <section class="content"><!-- Main content -->
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="box box-primary">
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center">

                                <thead>
                                    <tr>
                <td></td>
                        <td><spring:message code="code" text="Code"/></td>
                    <td><spring:message code="exam" text="Exam"/></td>
                    <td><spring:message code="examOrientDate" text="Exam Orient Date"/></td>
                    <td><spring:message code="remarks" text="Remarks"/></td>
                    <td><spring:message code="slNo" text="Sl No"/></td>
                    <td><spring:message code="certificateFile" text="Certificate File"/></td>
                    <td><spring:message code="picFile" text="ZxEmp Who Pic File"/></td>
                    <td><spring:message code="zxEmp" text="ZxEmp Id"/></td>
                    <td><spring:message code="zxEmpWhoCheckedBy" text="ZxEmp Who Checked By Id"/></td>

            </tr>
            </thead>
            <tbody>
                <c:choose>
                        <c:when test="${zxEmpEduDtls.size()!=0}">
            <c:forEach items="${zxEmpEduDtls}" var="zxEmpEduDtl"  varStatus="loopStatus">

                <tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
                <td class="center">
                    <a class="btn btn-block btn-info btn-xs" href="${pageContext.request.contextPath}/zxEmpEduDtl/show/${zxEmpEduDtl.id}">
                        <i class="fa fa-envelope"></i> <spring:message code="show.link.label"/>
                    </a>
                </td>
                            <td><c:out value="${zxEmpEduDtl.code}"/></td>
                        <td><c:out value="${zxEmpEduDtl.exam}"/></td>
                        <td><fmt:formatDate value="${zxEmpEduDtl.examOrientDate}" type="date" pattern="dd/MM/yyyy"/></td>
                        <td><c:out value="${zxEmpEduDtl.remarks}"/></td>
                        <td><c:out value="${zxEmpEduDtl.slNo}"/></td>
                        <td><c:url var="certificateFile" value="/zxEmpEduDtl/getFile/${zxEmpEduDtl.certificateFile}"/>
<a target="_blank" href="${certificateFile}">${zxEmpEduDtl.certificateFile}</a></td>
                        <td><c:url var="picFile" value="/zxEmpEduDtl/getPhotoTumb/${zxEmpEduDtl.picFile}"/><img alt="${zxEmpEduDtl.picFile}" src="${picFile}"/></td>
                        <td><c:out value="${zxEmpEduDtl.zxEmp!=null ? zxEmpEduDtl.zxEmp :'N/A'}"/></td>
                        <td><c:out value="${zxEmpEduDtl.zxEmpWhoCheckedBy!=null ? zxEmpEduDtl.zxEmpWhoCheckedBy :'N/A'}"/></td>

                <td class="center">
                    <div class="btn-group">
                        <a class="btn btn-block btn-primary btn-xs" href="${pageContext.request.contextPath}/zxEmpEduDtl/edit/${zxEmpEduDtl.id}">
                        <i class="fa fa-edit"></i> <spring:message code="edit.link.label"/>
                        </a>
                        <a class="btn btn-block btn-warning btn-xs" href="${pageContext.request.contextPath}/zxEmpEduDtl/copy/${zxEmpEduDtl.id}">
                        <i class="fa fa-copy"></i> <spring:message code="copy.link.label"/>
                        </a>
                        <a class="btn btn-block btn-danger btn-xs" href="${pageContext.request.contextPath}/zxEmpEduDtl/delete/${zxEmpEduDtl.id}" onclick="return confirm('Are you sure to delete?');">
                        <i class="fa fa-remove"></i> <spring:message code="delete.link.label"/>
                        </a>
                    </div>
                </td>
                
                 </tr>
                   </c:forEach>
                   </c:when>
                   <c:otherwise>
                   <tr>
                       <td colspan="9" class="bold center">
                       <spring:message code="default.noRecordFound.label" text="No Record Found...!"/>
                       </td>
                   </tr>
                   </c:otherwise> 
                               </c:choose>
               </tbody>
               </table>
               <div class="pagination">
               <!--<g:paginate total="{testInstanceCount ?: 0}"/>-->
               </div>
           </div><!-- /.box-body table-responsive no-padding -->
           </div><!-- /.box box-primary -->
       </div><!-- /.col-xs-12 -->
       </div><!-- /.row -->
   </section><!-- /.content -->
</div><!-- /.content-wrapper -->

</tiles:putAttribute>  

<div class="box-footer">
<tiles:putAttribute name="footer">
   <jsp:include page="../layouts/footer.jsp"/>
</tiles:putAttribute>  
</div>
