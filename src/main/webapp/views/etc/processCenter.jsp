<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="main" >

    <%--
    <tiles:putAttribute name="header">
        <title><spring:message code="process.processCenter.label" text="Process Center"/></title>
    </tiles:putAttribute>
    --%>

    <tiles:putAttribute name="body">

        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->
            <section class="content-header"><!-- Content Header (Page header) -->
                <h1><spring:message code="default.button.process.label" text="Process"/></h1>
            </section><!-- /.content-header -->

            <section class="content-messages">
                <%--<jsp:include page="../layouts/_flashMessage.jsp"/>--%>
            </section><!-- /.flesh-message -->

            <section class="content"><!-- Main content -->
                <div class="box box-primary">
                    <form:form action="${pageContext.request.contextPath}/processCenter" commandName="rrr" method="POST">
                        <div class="box-body">
                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label><spring:message code="process.processName.label" text="Process Name"/>:</label>
                                    <form:select class="form-control" path="title" name="title" id="title" >
                                        <form:option value="daily" label="Daily Status Process"/>
                                        <form:option value="dailyRange" label="Daily Status Range Process"/>
                                        <form:option value="refresh" label="Employee Refresh Process"/>
                                        <form:option value="delEmp" label="Employee Full info Delete"/>
                                        <form:option value="genCal" label="Generate Calender"/>
                                    </form:select>
                                </div>
                            </div>

                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label><spring:message code="default.date.label" text="Date"/>:</label>
                                    <div class="input-group">
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                        <input name="P_ATTN_DATE" class="form-control dtp-date"/>
                                    </div><!-- /.input group -->
                                </div><!-- /.form-group -->
                            </div><!-- /.col-xs-12 col-sm-6 col-md-6 col-lg-6-->




                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label><spring:message code="default.datew.label" text="From Date"/>:</label>
                                    <div class="input-group">
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                        <input name="P_FROM_DATE" class="form-control dtp-date"/>
                                    </div><!-- /.input group -->
                                </div><!-- /.form-group -->
                            </div><!-- /.col-xs-12 col-sm-6 col-md-6 col-lg-6-->

                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label><spring:message code="default.datew.label" text="To Date"/>:</label>
                                    <div class="input-group">
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                        <input name="P_TO_DATE" class="form-control dtp-date"/>
                                    </div><!-- /.input group -->
                                </div><!-- /.form-group -->
                            </div><!-- /.col-xs-12 col-sm-6 col-md-6 col-lg-6-->
                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label><spring:message code="default.datex.label" text="Emp Code"/>:</label>
                                    <div class="input-group">
                                        <div class="input-group-addon"><i class="fa fa-user"></i></div>
                                        <input name="P_EMP_CODE" class="form-control"/>
                                    </div><!-- /.input group -->
                                </div><!-- /.form-group -->
                            </div>
                            <%--
                                <c:url value="/reportCenter/all" var="downloadXls"/>
                                <a href="${downloadXls}">Download All</a>
                                <br/>
                                <c:url value="/reportCenter/all" var="downloadPdf"/>
                                <a href="${downloadPdf}">Download Monthly</a>
                                <br/>
                                <c:url value="/reportCenter/all" var="downloadCsv"/>
                                <a href="${downloadCsv}">Download Daily</a>
                            --%>

                        </div><!-- /.box-body -->
                        <div class="box-footer">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <!--<sec:access url="/processCenter">-->
                                <button type="submit" class="btn btn-primary"><i class="fa fa-gears"></i> <spring:message code="default.button.process.label" text="Process"/></button>
                                <!--</sec:access>-->
                            </div>
                        </div><!-- /.box-footer -->
                    </form:form>
                </div><!-- /.box box-primary -->
            </section><!-- /.content -->
        </div><!-- /.content-wrapper -->
    </tiles:putAttribute>

</tiles:insertDefinition>