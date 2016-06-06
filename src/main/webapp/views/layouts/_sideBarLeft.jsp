<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- Sidebar user panel (optional) -->
        <div class="user-panel">

            <sec:authentication var='displayName' property='principal.displayName'/>
            <sec:authentication var='picFile' property='principal.picFile'/>
            <c:url var='macImage' value='/authUser/getPhoto/${picFile}' />

            <div class="pull-left image">
                <img src="${macImage}" class="img-circle" alt="NO IMAGE">
            </div>

            <div class="pull-left info">

                <p>${displayName}</p>
                <!-- Status -->
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>

        <!-- search form (Optional) -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                    <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                    </button>
                </span>
            </div>
        </form>
        <!-- /.search form -->

        <!-- Sidebar Menu -->
        <ul class="sidebar-menu">
            <li class="header">HEADER</li>
            <!-- Optionally, you can add icons to the links -->
            <li><a href="<%=request.getContextPath()%>/index"><i class="fa fa-home"></i> <span>Home Out</span></a></li>
            <li><a href="<%=request.getContextPath()%>/homeSecure"><i class="fa fa-home"></i> <span>Home Self</span></a></li>
            <li class="active treeview">
                <a href="#">
                    <i class="fa fa-gears"></i> <span>Controllers (Static)</span> <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <!--<li><a href="< %=request.getContextPath()%>/index"><i class="fa fa-circle-o"></i> index</a></li>-->
                    <!--<li class="active"><a href="< %=request.getContextPath()%>/lookupx/index"><i class="fa fa-circle-o"></i> lookupx/index</a></li>-->
                    <li><a href="<%=request.getContextPath()%>/proc-refresh"><i class="fa fa-refresh"></i> Refresh Employee</a></li>
                    <li><a href="<%=request.getContextPath()%>/excelFileUpload"><i class="fa fa-file"></i> Upload Attendance Data</a></li>

                    <!--<li><a href="< %=request.getContextPath()%>/procRunner"><i class="fa fa-circle-o"></i> procRunner</a></li>-->
                    <!--<li><a href="< %=request.getContextPath()%>/queryRunner"><i class="fa fa-circle-o"></i> queryRunner</a></li>-->
                    <li><a href="<%=request.getContextPath()%>/reportRunner"><i class="fa fa-info"></i> Report</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-gears"></i> <span>Controllers (Dynamic)</span> <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <c:forEach items="${controllerList}" begin="0" var="controllerName">
                        <!--<li><a href="${pageContext.request.contextPath}${controllerName}"><i class="fa fa-circle-o"></i> <span>${controllerName}</span></a></li>-->  
                        <li><a href="<%=request.getContextPath()%>${controllerName}"><i class="fa fa-circle-o"></i> <span>${controllerName}</span></a></li>
                        </c:forEach>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>Dashboard</span> <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="<%=request.getContextPath()%>/resources/samplePages/home/index.jsp"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>
                    <li><a href="<%=request.getContextPath()%>/resources/samplePages/home/index2.jsp"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>
                </ul>
            </li>
        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>