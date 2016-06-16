<%-- 
    Document   : _js
    Created on : May 20, 2016, 5:02:03 PM
    Author     : hoshen.mahmud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- jQuery 2.2.0 -->
<script src="<%=request.getContextPath()%>/resources/plugins/jQuery/jQuery-2.2.0.min.js"></script>

<!-- Bootstrap 3.3.6 -->
<script src="<%=request.getContextPath()%>/resources/plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- date-range-picker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<!--<script src="../../resources/plugins/moment.js-2.11.2/moment.min.js"></script>-->
<script src="<%=request.getContextPath()%>/resources/plugins/daterangepicker/daterangepicker.js"></script>
<!-- bootstrap datepicker -->
<script src="<%=request.getContextPath()%>/resources/plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- bootstrap color picker -->
<script src="<%=request.getContextPath()%>/resources/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<!-- bootstrap time picker -->
<script src="<%=request.getContextPath()%>/resources/plugins/timepicker/bootstrap-timepicker.min.js"></script>
<!-- SlimScroll 1.3.0 -->
<script src="<%=request.getContextPath()%>/resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>

<!-- DataTables -->
<script src="<%=request.getContextPath()%>/resources/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/plugins/datatables/extensions/Responsive/js/dataTables.responsive.js"></script>
<script src="<%=request.getContextPath()%>/resources/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.js"></script>

<!-- iCheck -->
<script src="<%=request.getContextPath()%>/resources/plugins/iCheck/icheck.min.js"></script>

<!-- SlimScroll -->
<script src="<%=request.getContextPath()%>/resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>

<!-- FastClick -->
<script src="<%=request.getContextPath()%>/resources/plugins/fastclick/fastclick.js"></script>

<!-- AdminLTE App -->
<script src="<%=request.getContextPath()%>/resources/plugins/admin-lte-2.3.3/js/app.min.js"></script>

<!--<script src="< %=request.getContextPath()%>/resources/plugins/reflection-1.0.0/reflection.js"></script>-->
<script src="<%=request.getContextPath()%>/resources/plugins/reflection-1.0.0/customInput.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
<script src="<%=request.getContextPath()%>/resources/plugins/jquery-confirm/jquery-confirm.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/custom-confirm.js"></script>

<!-- AdminLTE for demo purposes -->
<script src="<%=request.getContextPath()%>/resources/plugins/admin-lte-2.3.3/js/demo.js"></script>

<script type="text/javascript">
    var contextPath = "<%=request.getContextPath()%>";
    var DATE_FORMAT = "dd/mm/yyyy";

    $('.dtp-date').prop('placeholder', DATE_FORMAT);
    
    //Date picker
    $('.dtp-date').datepicker({
        format: DATE_FORMAT,
        autoclose: true
    });
</script>

