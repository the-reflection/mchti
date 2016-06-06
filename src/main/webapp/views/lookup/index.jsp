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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/plugins/jquery/dataTables-1.10.10/media/css/jquery.dataTables.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/plugins/jquery/dataTables-1.10.10/media/css/dataTables.bootstrap.css"/>

    <title><spring:message code="project.title.index" text="Index"/></title>

    <div class="content-wrapper">

        <section class="content-header"><!-- Content Header (Page header) -->
            <h1><spring:message code="list.page.title" text="Index"/></h1>

            <ul class="top-links">
                <sec:access url="/lookup/create">
                    <li>
                        <a class="btn btn-block btn-primary btn-xs" href="${pageContext.request.contextPath}/lookup/create">
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

                            <table id="example" class="table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center" cellspacing="0" width="100%">

                                <!--<table class="table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center">-->

                                <thead>
                                    <tr>
                                        <td></td>
                                        <td><spring:message code="code" text="Code"/></td>
                                        <td><spring:message code="isActive" text="Is Active"/></td>
                                        <td><spring:message code="slNo" text="Sl No"/></td>
                                        <td><spring:message code="title" text="Title"/></td>
                                        <td><spring:message code="titleBng" text="Title Bng"/></td>
                                        <td><spring:message code="remarks" text="Remarks"/></td>
                                        <td><spring:message code="keyword" text="Keyword"/></td>
                                        <td></td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:choose>
                                        <c:when test="${lookups.size()!=0}">
                                            <c:forEach items="${lookups}" var="lookup"  varStatus="loopStatus">

                                                <tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
                                                    <td class="center">
                                                        <a class="btn btn-block btn-info btn-xs" href="${pageContext.request.contextPath}/lookup/show/${lookup.id}">
                                                            <i class="fa fa-envelope"></i> <spring:message code="show.link.label"/>
                                                        </a>
                                                    </td>
                                                    <td><c:out value="${lookup.code}"/></td>
                                                    <td><c:if test="${lookup.isActive}"><spring:message code="default.boolean.true" text="YES"/></c:if><c:if test="${!lookup.isActive}"><spring:message code="default.boolean.false" text="NO"/></c:if></td>
                                                    <td><c:out value="${lookup.slNo}"/></td>
                                                    <td><c:out value="${lookup.title}"/></td>
                                                    <td><c:out value="${lookup.titleBng}"/></td>
                                                    <td><c:out value="${lookup.remarks}"/></td>
                                                    <td><c:out value="${lookup.keyword}"/></td>
                                                    <td class="center">
                                                        <div class="btn-group">
                                                            <a class="btn btn-block btn-primary btn-xs" href="${pageContext.request.contextPath}/lookup/edit/${lookup.id}">
                                                                <i class="fa fa-edit"></i> <spring:message code="edit.link.label"/>
                                                            </a>
                                                            <a class="btn btn-block btn-warning btn-xs" href="${pageContext.request.contextPath}/lookup/copy/${lookup.id}">
                                                                <i class="fa fa-copy"></i> <spring:message code="copy.link.label"/>
                                                            </a>
                                                            <a class="btn btn-block btn-danger btn-xs" href="${pageContext.request.contextPath}/lookup/delete/${lookup.id}" onclick="return confirm('Are you sure to delete?');">
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

<script src="<%=request.getContextPath()%>/resources/plugins/jquery/jquery-1.11.3/jquery-1.11.3.js"></script>
<script src="<%=request.getContextPath()%>/resources/plugins/jquery/dataTables-1.10.10/media/js/jquery.dataTables.js"></script>
<script type='text/javascript'>
                                                                $(document).ready(function () {
//        var data = '{resource(dir: 'resource/data/', file: 'data.json')}'
//        var sSwfPath = '/resources/plugins/jquery/dataTables-1.10.10/extensions/TableTools-2.2.4/swf/copy_csv_xls_pdf.swf'
                                                                    $('#example').DataTable({
//--------------------------------------------------------------------------------------------------------------------
                                                                        order: [[1, "asc"]], //"asc" & "desc"
                                                                        ordering: true,
                                                                        paging: true,
                                                                        sPaginationType: "full_numbers", //for first/last button in padding
                                                                        lengthChange: true,
                                                                        lengthMenu: [
                                                                            [5, 10, 15, 25, 50, -1],
                                                                            [5, 10, 15, 25, 50, "All"]
                                                                        ], //or lengthMenu: [ 10, 25, 50, 75, 100 ]
                                                                        searching: true,
                                                                        info: true,
                                                                        scrollY: true, //400 or 100%
                                                                        scrollX: true, //400 or 100%
                                                                        deferRender: true,
//--------------------------------------------------------------------------------------------------------------------
//          For Responsive Data Table
//--------------------------------------------------------------------------------------------------------------------
                                                                        responsive: true, //For Responsive dataTable. (http://www.datatables.net/extensions/responsive/)
//--------------------------------------------------------------------------------------------------------------------
//          To Load Data
//--------------------------------------------------------------------------------------------------------------------
//            ajax: data, //For Generating Data      : data source path.
//--------------------------------------------------------------------------------------------------------------------
//          For Printing (using table tools)...
//--------------------------------------------------------------------------------------------------------------------
                                                                        "dom": 'T<"clear">lfrtip', //https://www.datatables.net/extensions/tabletools/
//            tableTools: {"sSwfPath": sSwfPath}, //For Printing (dataTable) : swf file path
//--------------------------------------------------------------------------------------------------------------------
                                                                        select: true,
                                                                        "dom"               : '<"top"lf>t<"bottom"pi><"clear">',
//--------------------------------------------------------------------------------------------------------------------
//          To force dataTables to keep the column widths
//--------------------------------------------------------------------------------------------------------------------
                                                                                autoWidth: false, //optional
                                                                        "aoColumnDefs": [
                                                                            {"sWidth": "5%", "aTargets": 0, class: 'text-center'}, //<-start from zero, "sWidth": "100px"
                                                                            {"sWidth": "5%", "aTargets": 1, class: 'text-left'}, //if you use sWidth then > aTargets is required
                                                                            {"sWidth": "5%", "aTargets": 2, class: 'text-center'}, //class > optional
                                                                            {"sWidth": "5%", "aTargets": 3, class: 'text-center'},
                                                                            {"sWidth": "20%", "aTargets": 4, class: 'text-left'},
                                                                            {"sWidth": "20%", "aTargets": 5, class: 'text-left'},
                                                                            {"sWidth": "20%", "aTargets": 6, class: 'text-left'},
                                                                            {"sWidth": "10%", "aTargets": 7, class: 'text-center'},
                                                                            {"sWidth": "10%", "aTargets": 8, class: 'text-center'}
                                                                        ]
//--------------------------------------------------------------------------------------------------------------------
                                                                    });
                                                                });
</script>
