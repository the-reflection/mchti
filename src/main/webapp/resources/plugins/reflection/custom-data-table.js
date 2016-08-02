/**
 * Created by hoshen.mahmud on 12-Jul-16.
 */
//==================================================================================================================================
//  Data Table
//----------------------------------------------------------------------------------------------------------------------------------
//alert('Your Context Path is : ' + contextPath);
//        var data     = contextPath + "/resources/data/data.json"

//var sSwfPath = 'libs/jquery_datatable/TableTools-2.2.4/swf/copy_csv_xls_pdf.swf';
var sSwfPath = "http://localhost:8091/g_t_adminLte" + "/resources/plugins/datatables/extensions/TableTools/swf/copy_csv_xls_pdf.swf";

$("#example").DataTable();
$(".example").DataTable();

$(".dt-default").DataTable({
    lengthMenu      : [
        [5, 10, 15, 25, 50, 100, -1],
        [5, 10, 15, 25, 50, 100, "All"]
    ],
    "iDisplayLength": 10,                   //eg: you want to show 15 records by default
    responsive      : true
});

$(".dt-show").DataTable({
    ordering    : false,
    paging      : false,
    searching   : false,
    info        : false,
    responsive  : true,
    "dom"       : 'T<"clear">lfrtip',           //https://www.datatables.net/extensions/tabletools/

    //tableTools  : {"sSwfPath": sSwfPath}      //For Printing (dataTable) : swf file path

    oTableTools     : {
        sSwfPath    : sSwfPath,
        aButtons    : ["copy", "csv", "print"]          //["copy", "csv", "xls", "pdf", "print"]
    },

    //tableTools      : {
    //    sSwfPath        : sSwfPath,
    //    "oFeatures"     : {
    //        "bCopy"     : true,
    //        "bCsv"      : true,
    //        "bXls"      : true,
    //        "bPdf"      : true,
    //        "bPrint"    : true
    //    },
    //    "iButtonHeight" : 30,
    //    "iButtonWidth"  : 30,
    //    "sTitle"        : "",
    //    "sPrintMessage" : ""
    //},
});

$(".dt-full").DataTable({
//----------------------------------------------------------------------------------------------------------------------------------
//          General : General Functionalities
//----------------------------------------------------------------------------------------------------------------------------------
    searching   : true,                     //default: true,  optional.
    info        : true,                     //default: true,  optional.
    deferRender : true,                     //default: false, optional.
    "bJQueryUI" : false,                    //default: false, optional.
    //scrollY     : true,                   //400 or 100%
    //scrollX     : true,                   //400 or 100%
//----------------------------------------------------------------------------------------------------------------------------------
//        Ordering or Sorting
//----------------------------------------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------------------------------------
//        Length Menu
//----------------------------------------------------------------------------------------------------------------------------------
    lengthChange    : true,
    lengthMenu      : [
        [5, 10, 15, 25, 50, 100, -1],
        [5, 10, 15, 25, 50, 100, "All"]
    ],                //lengthMenu: [10, 25, 50, 75, 100]
    "iDisplayLength": 10,                   //eg: you want to show 15 records by default
//----------------------------------------------------------------------------------------------------------------------------------
//        Pagination
//----------------------------------------------------------------------------------------------------------------------------------
    paging          : true,
    sPaginationType : "full_numbers",       //for first/last button in padding
//----------------------------------------------------------------------------------------------------------------------------------
//        Ordering or Sorting
//----------------------------------------------------------------------------------------------------------------------------------
    ordering        : true,
    order           : [[0, "asc"]],         //"asc" & "desc"
    //"aaSorting"     : [[1, 'desc']],
    //"aoColumnDefs"  : [
    //    {"sType"    : "num-html", "aTargets": [0]}
    //],
//----------------------------------------------------------------------------------------------------------------------------------
//          Responsive : For Responsive Data Table
//----------------------------------------------------------------------------------------------------------------------------------
    responsive      : true,                 //For Responsive dataTable. (http://www.datatables.net/extensions/responsive/)
//----------------------------------------------------------------------------------------------------------------------------------
//          To Load Data
//----------------------------------------------------------------------------------------------------------------------------------
//        ajax            : data,                 //For Generating Data      : data source path.
//----------------------------------------------------------------------------------------------------------------------------------
//          table Tools : For Printing
//----------------------------------------------------------------------------------------------------------------------------------
    "dom"           : 'T<"clear">lfrtip',       //https://www.datatables.net/extensions/tabletools/

    //tableTools      : {"sSwfPath": sSwfPath},   //For Printing (dataTable) : swf file path

    /*
     oTableTools     : {
     sSwfPath    : sSwfPath,
     aButtons    : ["copy", "csv", "pdf"],          //["copy", "csv", "xls", "pdf", "print"]
     },
     */

    oTableTools     : {
        sSwfPath    : sSwfPath,
        "aButtons"  : [
            "copy",
            "print",
            {
                "sExtends"      : "collection",
                "sButtonText"   : "Save As",
                "aButtons"      : ["csv", "xls", "pdf"]
            }
        ]
    },

    /*
     oTableTools     : {
     sSwfPath    : sSwfPath,

     'aButtons': [
     {
     'sExtends'          : 'copy',
     'sButtonText'       : 'Copy',
     'mColumns'          : 'all'
     },
     {
     'sExtends'          : 'xls',
     'sButtonText'       : 'Excel / CSV',
     'mColumns'          : 'all'
     },
     {
     'sExtends'          : 'pdf',
     'sButtonText'       : 'PDF',
     'mColumns'          : 'all',         //or [0, 2],       //Show/Hide column (while generating pdf)
     "sPdfOrientation"   : "landscape"
     },
     {
     "sExtends"          : "collection",
     "sButtonText"       : "Save As",
     "aButtons"          : [ "csv", "xls", "pdf" ]
     }
     ],
     },
     */

    /*
     tableTools      : {
     sSwfPath        : sSwfPath,
     "oFeatures"     : {
     "bCsv"      : true,
     "bXls"      : true,
     "bCopy"     : true,
     "bPrint"    : true
     },
     "iButtonHeight" : 30,
     "iButtonWidth"  : 30,
     "sTitle"        : "",
     "sPrintMessage" : ""
     },
     */
//----------------------------------------------------------------------------------------------------------------------------------
//        Language
//----------------------------------------------------------------------------------------------------------------------------------
    oLanguage: {
        sLengthMenu     : "Show _MENU_ entries",
        "sEmptyTable"   : "<center>No data available</center>",
        sSearch         : "Search: ",
        "sProcessing"   : "<center><i class='fa fa-refresh fa-spin fa-3x fa-fw'></i><span class='sr-only'>Processing...</span></center>",
        "sUrl"          : "",
        "sInfoPostFix"  : "",
        sZeroRecords    : "<center>No matching records found.</center>",
        sInfo           : "Showing _START_ to _END_ from _TOTAL_ record(s)",
        sInfoEmpty      : "Showing 0 to 0 of 0 records",
        sInfoFiltered   : "(filtered from _MAX_ records)",
        oPaginate       : {
            sFirst      : "First",
            sPrevious   : "Previous",
            sNext       : "Next",
            sLast       : "Last"
        },
    },
//----------------------------------------------------------------------------------------------------------------------------------
//        select  : true,
//        "dom"   : '<"top"lf>t<"bottom"pi><"clear">',
//----------------------------------------------------------------------------------------------------------------------------------
//          Column widths : To force dataTables to keep the column widths
//----------------------------------------------------------------------------------------------------------------------------------
    autoWidth       : true,     //optional
    //"aoColumnDefs"  : [
    //    {"sWidth"   : "5%", "aTargets": 0, class: 'text-center'}, //<-start from zero, "sWidth": "100px"
    //    {"sWidth"   : "5%", "aTargets": 1, class: 'text-left'}, //if you use sWidth then > aTargets is required
    //    {"sWidth"   : "5%", "aTargets": 2, class: 'text-center'}, //class > optional
    //],

    //"aoColumns": [
    //{"mDataProp": "col0"},
    //{"mDataProp": "col1"},
    //{"mDataProp": "col2", "bVisible": false},
    //{"mDataProp": "col3"}
    //],
//----------------------------------------------------------------------------------------------------------------------------------
});
//==================================================================================================================================