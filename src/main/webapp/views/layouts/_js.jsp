<%-- 
    Document   : _js
    Created on : May 20, 2016, 5:02:03 PM
    Author     : hoshen.mahmud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- jQuery 2.2.0 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/jQuery/jquery.min.js"></script>

<!-- Bootstrap 3.3.6 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- ChartJS 1.0.1 -->
<!--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/chartjs/Chart.min.js"></script>-->
<!-- Morris.js charts -->
<!--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/raphael/raphael.min.js"></script>-->
<!--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/morris/morris.min.js"></script>-->
<!-- Sparkline -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<!--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>-->
<!--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>-->
<!-- jQuery Knob Chart -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/knob/jquery.knob.js"></script>
<!-- CK Editor -->
<!--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/ckeditor/ckeditor.js"></script>-->
<!-- date-range-picker -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/moment.js/moment.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/fullcalendar/fullcalendar.min.js"></script>
<!--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/daterangepicker/daterangepicker.js"></script>-->
<!-- bootstrap datepicker -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- bootstrap time picker -->
<!--<script type="text/javascript" src="<%=request.getContextPath()%>/resourc //es/plugins/timepicker/bootstrap-timepicker.min.js"></script>-->
<!-- bootstrap color picker -->
<!--<script type="text/javascript" src="<%=request.getContextPath()%>/resources ///plugins/colorpicker/bootstrap-colorpicker.min.js"></script>-->
<!-- Bootstrap WYSIHTML5 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/fastclick/fastclick.js"></script>

<!-- AdminLTE App -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/admin-lte/js/app.js"></script>
<!--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/admin-lte/js/app.min.js"></script>-->
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<!--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/adm //in-lte/js/pages/dashboard.js"></script>-->
<!-- AdminLTE for demo purposes -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/admin-lte/js/demo.js"></script>
<!-- InputMask -->
<!--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/input-mask/jquery.inputmask.js"></script>-->
<!--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>-->
<!--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/input-mask/jquery.inputmask.extensions.js"></script>-->

<!-- DataTables -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/datatables/extensions/Responsive/js/dataTables.responsive.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.js"></script>

<!-- iCheck -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/iCheck/icheck.min.js"></script>
<!-- Select2 -->
<!--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/plugins/select2/select2.full.min.js"/>-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/plugins/select2/select2.min.js"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/jquery-confirm/jquery-confirm.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/reflection/custom-confirm.js"></script>

<!-- Reflection -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/main.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/reflection/reflection.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/reflection/slide-to-top.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/reflection/skin-switcher.js"></script>
<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/reflection/custom-input.js"></script> -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/reflection/custom-data-table.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/reflection/custom-master-details.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/reflection/custom-file-pload.js"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
%{--The HTML5 Shiv enables use of HTML5 sectioning elements in legacy Internet Explorer and provides basic HTML5 styling for Internet Explorer 6-9, Safari 4.x (and iPhone 3.x), and Firefox 3.x.--}%
<!--<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>-->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/html5shiv/html5shiv.min.js"></script>

<!--A fast & lightweight polyfill for min/max-width CSS3 Media Queries (for IE 6-8, and more)-->
<!--<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>-->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/respond/respond.min.js"></script>

<![endif]-->

<!--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins//"></script>-->


<script type="text/javascript">
    var contextPath = "<%=request.getContextPath()%>";
    
    //Date picker
    var fd = "dd/mm/yyyy";
    $('.dtp-date').datepicker({format: fd,autoclose: true}).prop('placeholder', fd);
    
    var AdminLTEOptions = {
        //Add slimscroll to navbar menus
        //This requires you to load the slimscroll plugin
        //in every page before app.js
        navbarMenuSlimscroll: true,
        navbarMenuSlimscrollWidth: "3px", //The width of the scroll bar
        navbarMenuHeight: "200px", //The height of the inner menu
        //General animation speed for JS animated elements such as box collapse/expand and
        //sidebar treeview slide up/down. This options accepts an integer as milliseconds,
        //'fast', 'normal', or 'slow'
        animationSpeed: 500,
        //Sidebar push menu toggle button selector
        sidebarToggleSelector: "[data-toggle='offcanvas']",
        //Activate sidebar push menu
        sidebarPushMenu: true,
        //Activate sidebar slimscroll if the fixed layout is set (requires SlimScroll Plugin)
        sidebarSlimScroll: true,
        //Enable sidebar expand on hover effect for sidebar mini
        //This option is forced to true if both the fixed layout and sidebar mini
        //are used together
        sidebarExpandOnHover: true,
        //BoxRefresh Plugin
        enableBoxRefresh: true,
        //Bootstrap.js tooltip
        enableBSToppltip: true,
        BSTooltipSelector: "[data-toggle='tooltip']",
        //Enable Fast Click. Fastclick.js creates a more
        //native touch experience with touch devices. If you
        //choose to enable the plugin, make sure you load the script
        //before AdminLTE's app.js
        enableFastclick: true,
        //Control Sidebar Options
        enableControlSidebar: true,
        controlSidebarOptions: {
            //Which button should trigger the open/close event
            toggleBtnSelector: "[data-toggle='control-sidebar']",
            //The sidebar selector
            selector: ".control-sidebar",
            //Enable slide over content
            slide: true
        },
        //Box Widget Plugin. Enable this plugin
        //to allow boxes to be collapsed and/or removed
        enableBoxWidget: true,
        //Box Widget plugin options
        boxWidgetOptions: {
            boxWidgetIcons: {
                //Collapse icon
                collapse: 'fa-minus',
                //Open icon
                open: 'fa-plus',
                //Remove icon
                remove: 'fa-times'
            },
            boxWidgetSelectors: {
                //Remove button selector
                remove: '[data-widget="remove"]',
                //Collapse button selector
                collapse: '[data-widget="collapse"]'
            }
        },
        //Direct Chat plugin options
        directChat: {
            //Enable direct chat by default
            enable: true,
            //The button to open and close the chat contacts pane
            contactToggleSelector: '[data-widget="chat-pane-toggle"]'
        }
    };
</script>

