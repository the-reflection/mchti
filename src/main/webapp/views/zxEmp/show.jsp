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
                <h1><spring:message code='default.button.show.label' text='Show'/> Zx Emp</h1>
                <ul class='top-links'>
                    <sec:access url='/zxEmp/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/zxEmp/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
                        </li>
                    </sec:access>
                    <sec:access url='/zxEmp/index'>
                        <li>
                            <a href='${pageContext.request.contextPath}/zxEmp/index' class='btn btn-block btn-info btn-xs'><i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/></a>
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

        <c:if test='${zxEmp.code!=null && !zxEmp.code.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='code' text='Code'/>: 
                </span>
                <span class='property-value' aria-labelledby='code'>
                    <c:out value='${zxEmp.code}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmp.picFile!=null && !zxEmp.picFile.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='picFile' text='Pic File'/>: 
                </span>
                <span class='property-value' aria-labelledby='picFile'>
                    <c:url var='picFile' value='/zxEmp/getPhoto/${zxEmp.picFile}'/>
<img height="110px" width="90px" alt='${zxEmp.picFile}' src='${picFile}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmp.fullName!=null && !zxEmp.fullName.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='fullName' text='Full Name'/>: 
                </span>
                <span class='property-value' aria-labelledby='fullName'>
                    <c:out value='${zxEmp.fullName}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmp.zxGender!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='zxGender' text='Zx Gender'/>: 
                </span>
                <span class='property-value' aria-labelledby='zxGender'>
                    <c:out value='${zxEmp.zxGender}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmp.startDate!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='startDate' text='Start Date'/>: 
                </span>
                <span class='property-value' aria-labelledby='startDate'>
                    <fmt:formatDate value='${zxEmp.startDate}' type='date' pattern='dd/MM/yyyy'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmp.dob!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='dob' text='Dob'/>: 
                </span>
                <span class='property-value' aria-labelledby='dob'>
                    <fmt:formatDate value='${zxEmp.dob}' type='date' pattern='dd/MM/yyyy'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmp.sal!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='sal' text='Sal'/>: 
                </span>
                <span class='property-value' aria-labelledby='sal'>
                    <c:out value='${zxEmp.sal}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmp.taxPaid!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='taxPaid' text='Tax Paid'/>: 
                </span>
                <span class='property-value' aria-labelledby='taxPaid'>
                    <c:out value='${zxEmp.taxPaid}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmp.email!=null && !zxEmp.email.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='email' text='Email'/>: 
                </span>
                <span class='property-value' aria-labelledby='email'>
                    <c:out value='${zxEmp.email}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmp.pin!=null && !zxEmp.pin.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='pin' text='Pin'/>: 
                </span>
                <span class='property-value' aria-labelledby='pin'>
                    <c:out value='${zxEmp.pin}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmp.webAddress!=null && !zxEmp.webAddress.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='webAddress' text='Web Address'/>: 
                </span>
                <span class='property-value' aria-labelledby='webAddress'>
                    <c:url var='webAddress' value='/zxEmp/getFile/${zxEmp.webAddress}'/>
<a target='_blank' href='${webAddress}'>${zxEmp.webAddress}</a>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmp.docFile!=null && !zxEmp.docFile.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='docFile' text='Doc File'/>: 
                </span>
                <span class='property-value' aria-labelledby='docFile'>
                    <c:url var='docFile' value='/zxEmp/getFile/${zxEmp.docFile}'/>
<a target='_blank' href='${docFile}'>${zxEmp.docFile}</a>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmp.isActive!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='isActive' text='Is Active'/>: 
                </span>
                <span class='property-value' aria-labelledby='isActive'>
                    <c:if test='${zxEmp.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!zxEmp.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmp.remarks!=null && !zxEmp.remarks.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='remarks' text='Remarks'/>: 
                </span>
                <span class='property-value' aria-labelledby='remarks'>
                    <c:out value='${zxEmp.remarks}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmp.zxDept!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='zxDept' text='Zx Dept'/>: 
                </span>
                <span class='property-value' aria-labelledby='zxDept'>
                    <c:out value='${zxEmp.zxDept}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmp.zxDesg!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='zxDesg' text='Zx Desg'/>: 
                </span>
                <span class='property-value' aria-labelledby='zxDesg'>
                    <c:out value='${zxEmp.zxDesg}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${zxEmp.zxLookupBloodGroup!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='zxLookupBloodGroup' text='Zx Lookup Blood Group'/>: 
                </span>
                <span class='property-value' aria-labelledby='zxLookupBloodGroup'>
                    <c:out value='${zxEmp.zxLookupBloodGroup}'/>
                </span>
            </li>
        </c:if>

        </ol>
    <div><jsp:include page='zxEmpEduDtls.jsp' /></div>

    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class='box-footer'>
                            <a href='${pageContext.request.contextPath}/zxEmp/edit/${zxEmp.id}' class='btn btn-primary'><i class='fa fa-edit'></i> <spring:message code='edit.link.label'/></a> 
                            <a href='${pageContext.request.contextPath}/zxEmp/copy/${zxEmp.id}' class='btn btn-warning'><i class='fa fa-clone'></i> <spring:message code='default.button.copy.label'/></a>             
                            <a href='${pageContext.request.contextPath}/zxEmp/delete/${zxEmp.id}' class='btn btn-danger' onclick='return confirm('Are you sure to delete?');'><i class='fa fa-remove'></i> <spring:message code='delete.link.label'/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>