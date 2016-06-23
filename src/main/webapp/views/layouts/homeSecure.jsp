<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="main" >

    <tiles:putAttribute name="body">
        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">

        </div>
    </tiles:putAttribute>

</tiles:insertDefinition>

<%--     
      <!-- Content Header (Page header) -->
      <section class="content-header">
          <h1>
              <!-- Page Header -->
              Available Controllers
              <!-- Optional description-->
              <small>(Consist's in your project)</small>
          </h1>
          <ol class="breadcrumb">
              <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
              <li class="active">Here</li>
          </ol>
      </section>

            <!-- Main content -->
            <section class="content">

                <!-- Your Page Content Here -->
               <div class="box box-primary">
                    <div class="box-header">
                        <div class="bold center">
                            <!--<h1>Available Controllers</h1>-->
                        </div>
                    </div>
                    <div class="box-body">
                        <!--<div class="box-body no-padding">-->
                        <!--<table class="dt-default table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap">--> 
                        <div class="box-body">
                            <table class="dt-default table table-bordered table-striped table-hover table-condensed">
                                <thead><tr><th class="center">Controller Name</th><th class="center">URL Path</th></tr></thead>
                                <tbody>
                                    <c:forEach items="${controllerList}" var="controllerName">
                                        <tr>
                                            <td class="bold">${controllerName}</td>
                                            <td><a href="${pageContext.request.contextPath}${controllerName}">${controllerName}</a></td>
                                        </tr>
                                        <!--                                        
                                        <div class="row">
                                            <div class="hidden-xs col-sm-6 col-md-3 col-lg-3">
                                                <div class="form-group">
                                                    <a href="${pageContext.request.contextPath}${controllerName}">${controllerName}</a><br/>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-6 col-md-9 col-lg-9">
                                                <div class="form-group">
                                                    <a href="${pageContext.request.contextPath}${controllerName}">${controllerName}</a><br/>
                                                </div>
                                            </div>
                                        </div>
                                        -->
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="box-footer">
                    </div>
                </div>
            </section>
            <!-- /.content -->
--%>