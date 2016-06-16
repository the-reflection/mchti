<page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="main" >

    <tiles:putAttribute name="body">

        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->
            <section class="content-header"><!-- Content Header (Page header) -->
                <h1><spring:message code="default.button.list.label" text="List"/> ProcOutEmp</h1>
                <ul class="top-links">
                    <sec:access url="/procOutEmp/create">
                        <li>
                            <a href="${pageContext.request.contextPath}/procOutEmp/create" class="btn btn-block btn-primary btn-xs"><i class="fa fa-plus-circle"></i> <spring:message code="default.button.create.label" text="New"/></a>
                        </li>
                    </sec:access>
                </ul>
            </section><!-- /.content-header -->

            <section class="content-messages">
                <%--<jsp:include page="../layouts/_flashMessage.jsp"/>--%>
            </section><!-- /.flesh-message -->

            <section class="content"><!-- Main content -->
                <div class="box box-primary">   
                    <div class="box-body" style="overflow-x: auto">
                        <table class="dt-default table table-bordered table-striped table-hover table-condensed">

                            <!--<table class="table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center">-->
                            <thead>
                        <th><spring:message code="version" text="Version"/></th>
                    <th><spring:message code="code" text="Code"/></th>
                    <th><spring:message code="picFile" text="Pic File"/></th>
                    <th><spring:message code="fullName" text="Full Name"/></th>
                    <th><spring:message code="gender" text="Gender"/></th>
                    <th><spring:message code="doj" text="Doj"/></th>
                    <th><spring:message code="dob" text="Dob"/></th>
                    <th><spring:message code="email" text="Email"/></th>
                    <th><spring:message code="address" text="Address"/></th>
                    <th><spring:message code="department" text="Department"/></th>
                    <th><spring:message code="designation" text="Designation"/></th>
                    <th><spring:message code="empGroup" text="Emp Group"/></th>
                    <th><spring:message code="shiftOffDay" text="Shift Off Day"/></th>
                    <th><spring:message code="shift" text="Shift"/></th>
                    <th><spring:message code="roster" text="Roster"/></th>
                    <th><spring:message code="isOvertime" text="Is Overtime"/></th>

    <th><spring:message code="default.button.action.label" text="Action"/></th> 
            </thead>
            <tbody>
            <c:if test="${not empty procOutEmps}">
                <c:forEach items="${procOutEmps}" var="procOutEmp"  varStatus="loopStatus">
                    <tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
                        <td><c:out value="${procOutEmp.version}"/></td>
                        <td><c:out value="${procOutEmp.code}"/></td>
                        <td><c:url var="picFile" value="/procOutEmp/getPhotoTumb/${procOutEmp.picFile}"/>
<img alt="${procOutEmp.picFile}" src="${picFile}"/></td>
                        <td><c:out value="${procOutEmp.fullName}"/></td>
                        <td><c:out value="${procOutEmp.gender}"/></td>
                        <td><fmt:formatDate value="${procOutEmp.doj}" type="date" pattern="dd/MM/yyyy"/></td>
                        <td><fmt:formatDate value="${procOutEmp.dob}" type="date" pattern="dd/MM/yyyy"/></td>
                        <td><c:out value="${procOutEmp.email}"/></td>
                        <td><c:out value="${procOutEmp.address}"/></td>
                        <td><c:out value="${procOutEmp.department}"/></td>
                        <td><c:out value="${procOutEmp.designation}"/></td>
                        <td><c:out value="${procOutEmp.empGroup}"/></td>
                        <td><c:out value="${procOutEmp.shiftOffDay}"/></td>
                        <td><c:out value="${procOutEmp.shift}"/></td>
                        <td><c:out value="${procOutEmp.roster}"/></td>
                        <td><c:if test="${procOutEmp.isOvertime}"><spring:message code="default.boolean.true" text="YES"/></c:if><c:if test="${!procOutEmp.isOvertime}"><spring:message code="default.boolean.false" text="NO"/></c:if></td>

                           <td class="center action">
                            <ul class="top-links">
                            <sec:access url="/procOutEmp/show">
                                <li>
                                <a class="btn btn-block btn-info btn-xs" href="${pageContext.request.contextPath}/procOutEmp/show/${procOutEmp.id}">
                                    <i class="fa fa-info-circle"></i> <spring:message code="show.link.label"/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url="/procOutEmp/edit">
                                <li>
                                <a class="btn btn-block btn-primary btn-xs" href="${pageContext.request.contextPath}/procOutEmp/edit/${procOutEmp.id}">
                                    <i class="fa fa-edit"></i> <spring:message code="edit.link.label"/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url="/procOutEmp/copy">
                                <li>
                                <a class="btn btn-block btn-warning btn-xs" href="${pageContext.request.contextPath}/procOutEmp/copy/${procOutEmp.id}">
                                    <i class="fa fa-clone" aria-hidden="true"></i> <spring:message code="copy.link.label"/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url="/procOutEmp/delete">
                                <li>
                                <a class="btn btn-block btn-danger btn-xs" href="${pageContext.request.contextPath}/procOutEmp/delete/${procOutEmp.id}" onclick="return confirm('Are you sure to delete?');">
                                    <i class="fa fa-remove"></i> <spring:message code="delete.link.label"/>
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
            <div class="pagination">
                <!--<g:paginate total="{testInstanceCount ?: 0}"/>-->
            </div>
            </div><!-- /.box-body table-responsive no-padding -->
        </div><!-- /.box box-primary -->
        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->
    </tiles:putAttribute>
                
</tiles:insertDefinition>