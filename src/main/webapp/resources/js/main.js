/*
 * Document   : main
 * Created on : May 24, 2016, 9:55:17 AM
 * Author     : hoshen.mahmud
*/
//----------------------------------------------------------------------------------------------------------------------------
jQuery(document).ready(function () {
//        alert('Your Context Path is : ' + contextPath);
//        var data     = contextPath + "/resources/data/data.json"        
    var sSwfPath = contextPath + "/resources/plugins/datatables/extensions/TableTools/swf/copy_csv_xls_pdf.swf";

    $("#example").DataTable();

    $(".dt-default").DataTable({
        lengthMenu: [
            [5, 10, 15, 25, 50, 100, -1],
            [5, 10, 15, 25, 50, 100, "All"]
        ],
        "iDisplayLength": 10,	//eg: you want to show 15 records by default
        responsive: true
    });

    $(".dt-show").DataTable({
        ordering: false,
        paging: false,
        searching: false,
        info: false,
        responsive: true,
        "dom": 'T<"clear">lfrtip', //https://www.datatables.net/extensions/tabletools/
        tableTools: {"sSwfPath": sSwfPath} //For Printing (dataTable) : swf file path
    });

    $(".dt-full").DataTable({
        order: [[0, "asc"]], //"asc" & "desc"
        ordering: true,
        paging: true,
        sPaginationType: "full_numbers", //for first/last button in padding
        lengthChange: true,
        lengthMenu: [
            [5, 10, 15, 25, 50, 100, -1],
            [5, 10, 15, 25, 50, 100, "All"]
        ], //or lengthMenu: [ 10, 25, 50, 75, 100 ]
        "iDisplayLength": 10,	//eg: you want to show 15 records by default
        searching: true,
        info: true,
//            scrollY: true, //400 or 100%
//            scrollX: true, //400 or 100%
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
        tableTools: {"sSwfPath": sSwfPath}, //For Printing (dataTable) : swf file path
//            tableTools: {
//                "oFeatures": {
//                    "bCsv": true,
//                    "bXls": true,
//                    "bCopy": true,
//                    "bPrint": true
//                },
//                "sPrintMessage": "",
//                "sTitle": "",
//                sSwfPath: sSwfPath,
//                "iButtonHeight": 30,
//                "iButtonWidth": 30
//            };
//--------------------------------------------------------------------------------------------------------------------
//            select: true,
//            "dom"               : '<"top"lf>t<"bottom"pi><"clear">',
//--------------------------------------------------------------------------------------------------------------------
//          To force dataTables to keep the column widths
//--------------------------------------------------------------------------------------------------------------------
        autoWidth: true //optional
//            "aoColumnDefs": [
//                {"sWidth": "5%", "aTargets": 0, class: 'text-center'}, //<-start from zero, "sWidth": "100px"
//                {"sWidth": "5%", "aTargets": 1, class: 'text-left'}, //if you use sWidth then > aTargets is required
//                {"sWidth": "5%", "aTargets": 2, class: 'text-center'}, //class > optional
//            ]
    });
});