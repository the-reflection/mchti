<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="main" >

    <tiles:putAttribute name="header">
        <title><spring:message code="report.reportCenter.label" text="Report Center"/></title>
    </tiles:putAttribute>

    <tiles:putAttribute name="body">

        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->
            <section class="content-header"><!-- Content Header (Page header) -->
                <h1><spring:message code="default.button.report.label" text="Report"/></h1>
            </section><!-- /.content-header -->

            <section class="content-messages">
                <%--<jsp:include page="../layouts/_flashMessage.jsp"/>--%>
            </section><!-- /.flesh-message -->

            <section class="content"><!-- Main content -->
                <div id="create-zxLookup" class="box box-primary" role="main">
                    <form:form action="${pageContext.request.contextPath}/reportCenter" commandName="rrr" method="POST">
                        <div class="box-body">

                            <div class="box-body">


                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        Report Name:
                                        <form:select class="form-control" path="title" name="title" id="title" >
                                            <form:option value="tl_daily_all_emp_dt_status" label="Daily All Emp Dt Status"/>
                                            <form:option value="tl_daily_all_emp_punch"     label="Daily All Emp Punch"/>
                                            <form:option value="tl_month_in_out_all_emp"     label="Daily All Emp Punch (Cross Tab)"/>
                                            <form:option value="tl_monthly_all_emp"         label="Monthly All Emp"/>
                                        </form:select>

                                    </div>
                                </div>

                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                    Date:
                                    <div class="input-group date" data-provide="datepicker">
                                        <input name="P_ATTN_DATE" id="P_ATTN_DATE"
                                               value="" class="form-control dtp-date"/>
                                        <div class="input-group-addon">
                                            <span class="glyphicon glyphicon-th"></span>
                                        </div>
                                    </div>

                                </div><!-- /.form-group -->



                                <%--                                <c:url value="/reportCenter/all" var="downloadXls"/>
                                                                <a href="${downloadXls}">Download All</a>
                                                                <br/>
                                                                <c:url value="/reportCenter/all" var="downloadPdf"/>
                                                                <a href="${downloadPdf}">Download Monthly</a>
                                                                <br/>
                                                                <c:url value="/reportCenter/all" var="downloadCsv"/>
                                                                <a href="${downloadCsv}">Download Daily</a>--%>

                            </div><!-- /.box-body -->
                            <div class="box-footer">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <!--<sec:access url="/reportCenter">-->
                                    <button type="submit" class="btn btn-primary"><i class="fa fa-save"></i> <spring:message code="default.button.report.label" text="Report"/></button>
                                    <!--</sec:access>-->
                                </div>
                            </div><!-- /.box-footer -->
                        </form:form>
                    </div><!-- /.create-zxLookup -->
            </section><!-- /.content -->
        </div><!-- /.content-wrapper -->
    </tiles:putAttribute>

</tiles:insertDefinition>