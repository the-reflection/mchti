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
                <h1><spring:message code='default.button.show.label' text='Show'/> Zx Emp Edu Dtl</h1>
                <ul class='top-links'>
                    <sec:access url='/zxEmpEduDtl/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/zxEmpEduDtl/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
                        </li>
                    </sec:access>
                    <sec:access url='/zxEmpEduDtl/index'>
                        <li>
                            <a href='${pageContext.request.contextPath}/zxEmpEduDtl/index' class='btn btn-block btn-info btn-xs'><i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/></a>
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

        <c:if test='${zxEmpEduDtl.exam!=null && !zxEmpEduDtl.exam.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='exam' text='Exam'/>: 
                </span>
                <span class='property-value' aria-labelledby='exam'>
                    <c:out value='${zxEmpEduDtl.exam}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmpEduDtl.examOrientDate!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='examOrientDate' text='Exam Orient Date'/>: 
                </span>
                <span class='property-value' aria-labelledby='examOrientDate'>
                    <fmt:formatDate value='${zxEmpEduDtl.examOrientDate}' type='date' pattern='dd/MM/yyyy'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmpEduDtl.remarks!=null && !zxEmpEduDtl.remarks.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='remarks' text='Remarks'/>: 
                </span>
                <span class='property-value' aria-labelledby='remarks'>
                    <c:out value='${zxEmpEduDtl.remarks}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmpEduDtl.slNo!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='slNo' text='Sl No'/>: 
                </span>
                <span class='property-value' aria-labelledby='slNo'>
                    <c:out value='${zxEmpEduDtl.slNo}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmpEduDtl.certificateFile!=null && !zxEmpEduDtl.certificateFile.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='certificateFile' text='Certificate File'/>: 
                </span>
                <span class='property-value' aria-labelledby='certificateFile'>
                    <c:url var='certificateFile' value='/zxEmpEduDtl/getFile/${zxEmpEduDtl.certificateFile}'/>
<a target='_blank' href='${certificateFile}'>${zxEmpEduDtl.certificateFile}</a>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmpEduDtl.picFile!=null && !zxEmpEduDtl.picFile.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='picFile' text='Pic File'/>: 
                </span>
                <span class='property-value' aria-labelledby='picFile'>
                    <c:url var='picFile' value='/zxEmpEduDtl/getPhoto/${zxEmpEduDtl.picFile}'/>
<img height="110px" width="90px" alt='${zxEmpEduDtl.picFile}' src='${picFile}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmpEduDtl.zxEmp!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='zxEmp' text='Zx Emp'/>: 
                </span>
                <span class='property-value' aria-labelledby='zxEmp'>
                    <c:out value='${zxEmpEduDtl.zxEmp!=null ? zxEmpEduDtl.zxEmp :"N/A"}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmpEduDtl.zxEmpWhoCheckedBy!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='zxEmpWhoCheckedBy' text='Zx Emp Who Checked By'/>: 
                </span>
                <span class='property-value' aria-labelledby='zxEmpWhoCheckedBy'>
                    <c:out value='${zxEmpEduDtl.zxEmpWhoCheckedBy!=null ? zxEmpEduDtl.zxEmpWhoCheckedBy :"N/A"}'/>
                </span>
            </li>
        </c:if>

        </ol>

    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class='box-footer'>
                            <a href='${pageContext.request.contextPath}/zxEmpEduDtl/edit/${zxEmpEduDtl.id}' class='btn btn-primary'><i class='fa fa-edit'></i> <spring:message code='edit.link.label'/></a> 
                            <a href='${pageContext.request.contextPath}/zxEmpEduDtl/copy/${zxEmpEduDtl.id}' class='btn btn-warning'><i class='fa fa-clone'></i> <spring:message code='default.button.copy.label'/></a>             
                            <a href='${pageContext.request.contextPath}/zxEmpEduDtl/delete/${zxEmpEduDtl.id}' class='btn btn-danger' onclick='return confirm('Are you sure to delete?');'><i class='fa fa-remove'></i> <spring:message code='delete.link.label'/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>