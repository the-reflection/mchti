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
                <h1><spring:message code="default.button.list.label" text="List"/> Assignment Prl</h1>
                <ul class="top-links">
                    <sec:access url="/assignmentPrl/create">
                        <li>
                            <a href="${pageContext.request.contextPath}/assignmentPrl/create" class="btn btn-block btn-primary btn-xs"><i class="fa fa-plus-circle"></i> <spring:message code="default.button.create.label" text="New"/></a>
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
                        <th class='center bold'><spring:message code="code" text="Code"/></th>
                    <th class='center bold'><spring:message code="employee" text="Employee"/></th>
                    <th class='center bold'><spring:message code="startDate" text="Start Date"/></th>
                    <th class='center bold'><spring:message code="endDate" text="End Date"/></th>
                    <th class='center bold'><spring:message code="basic" text="Basic"/></th>
                    <th class='center bold'><spring:message code="houseRent" text="House Rent"/></th>
                    <th class='center bold'><spring:message code="medical" text="Medical"/></th>
                    <th class='center bold'><spring:message code="convance" text="Convance"/></th>
                    <th class='center bold'><spring:message code="otherAllowance" text="Other Allowance"/></th>
                    <th class='center bold'><spring:message code="gross" text="Gross"/></th>

    <th class='center bold'><spring:message code="default.button.action.label" text="Action"/></th> 
            </thead>
            <tbody>
            <c:if test="${not empty assignmentPrls}">
                <c:forEach items="${assignmentPrls}" var="assignmentPrl"  varStatus="loopStatus">
                    <tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
                        <td><c:out value="${assignmentPrl.code}"/></td>
                        <td><c:out value="${assignmentPrl.employee!=null ? assignmentPrl.employee :'N/A'}"/></td>
                        <td><fmt:formatDate value="${assignmentPrl.startDate}" type="date" pattern="dd/MM/yyyy"/></td>
                        <td><fmt:formatDate value="${assignmentPrl.endDate}" type="date" pattern="dd/MM/yyyy"/></td>
                        <td><c:out value="${assignmentPrl.basic}"/></td>
                        <td><c:out value="${assignmentPrl.houseRent}"/></td>
                        <td><c:out value="${assignmentPrl.medical}"/></td>
                        <td><c:out value="${assignmentPrl.convance}"/></td>
                        <td><c:out value="${assignmentPrl.otherAllowance}"/></td>
                        <td><c:out value="${assignmentPrl.gross}"/></td>

                           <td class="center action">
                            <ul class="top-links">
                            <sec:access url="/assignmentPrl/show">
                                <li>
                                <a class="btn btn-block btn-info btn-xs" href="${pageContext.request.contextPath}/assignmentPrl/show/${assignmentPrl.id}">
                                    <i class="fa fa-info-circle"></i> <spring:message code="show.link.label"/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url="/assignmentPrl/edit">
                                <li>
                                <a class="btn btn-block btn-primary btn-xs" href="${pageContext.request.contextPath}/assignmentPrl/edit/${assignmentPrl.id}">
                                    <i class="fa fa-edit"></i> <spring:message code="edit.link.label"/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url="/assignmentPrl/copy">
                                <li>
                                <a class="btn btn-block btn-warning btn-xs" href="${pageContext.request.contextPath}/assignmentPrl/copy/${assignmentPrl.id}">
                                    <i class="fa fa-clone" aria-hidden="true"></i> <spring:message code="default.button.copy.label"/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url="/assignmentPrl/delete">
                                <li>
                                <a class="btn btn-block btn-danger btn-xs" href="${pageContext.request.contextPath}/assignmentPrl/delete/${assignmentPrl.id}" onclick="return confirm('Are you sure to delete?');">
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