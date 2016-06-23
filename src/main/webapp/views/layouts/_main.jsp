<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<title>JSP Page</title>-->
        <!--<meta charset="utf-8">-->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Reflection | Home</title>
        <!-- favicon -->
        <!--<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/resources/images/favicon.ico"/>-->
        <link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/resources/images/favicon.ico"/>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    </head>
    <body class="hold-transition skin-blue sidebar-mini sidebar-collapse fixed">
        <div class="wrapper">

            <div class="styleSheet">
                <tiles:insertAttribute name="styleSheet"/>
            </div>

            <div class="header">
                <tiles:insertAttribute name="header"/>
            </div>

            <%--<jsp:include page="layouts/_header.jsp"/>--%>

            <div class="sideBarLeft">
                <tiles:insertAttribute name="sideBarLeft"/>
            </div>




            <!-- Content Wrapper. Contains page content -->


            <div class="body">
                <tiles:insertAttribute name="body"/>
            </div>

            <%-- 
                      <div class="content-wrapper">
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
                           
                              <div class="box-body no-padding">
                            
                                <table class="table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap">
                                    <c:forEach items="${controllerList}" var="controllerName">
                                        <tr>
                                            <th style="width: 30%">${controllerName}</th>
                                            <td style="width: 70%"><a href="${pageContext.request.contextPath}${controllerName}">${controllerName}</a></td>
                                        </tr>

                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                        <div class="box-footer">

                        </div>
                    </div>
                </section>
                <!-- /.content -->
            </div>
            --%>


            <div class="sideBarRight">
                <tiles:insertAttribute name="sideBarRight"/>
            </div>

            <div class="control-sidebar-bg"></div>
        </div>
        <!-- ./wrapper -->

        <div class="script">
            <tiles:insertAttribute name="script"/>
        </div>
        <div class="footer">
            <tiles:insertAttribute name="footer"/>
        </div>
    </body>
</html>
