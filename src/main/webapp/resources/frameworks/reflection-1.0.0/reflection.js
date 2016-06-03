/**
 * Created by hoshen.mahmud on 17-Mar-16.
 */
/*======================================================================================================================================================*/
if (typeof jQuery !== 'undefined') {
    (function($) {
        $(document).ajaxStart(function(){
            $('#spinner').fadeIn();
        }).ajaxStop(function(){
            $('#spinner').fadeOut();
        });
    })(jQuery);
}
$(document).ready(function () {
    //$('.date').datepicker({format: DATE_FORMAT, autoclose: true});
    //$('.date').prop('placeholder', DATE_FORMAT);
    //$('.date').prop('placeholder', new Date());
    //$('.date').prop('format', DATE_FORMAT);
    //$('.date').prop('format', dfForm);
    //$('.date').val(new Date($(this).val()).format(dfForm));
    //$(".date").val($.datepicker.formatDate(dfForm, $(this).val()));

    //setTimeout(function() {$('.flashMessage').fadeOut();}, 5000);
    setTimeout(function () {$('.success').fadeOut();}, 5000);
    setTimeout(function () {$('.message').fadeOut();}, 15000);
    setTimeout(function () {$('.warning').fadeOut();}, 10000);
    setTimeout(function () {$('.error').fadeOut();}, 60000);
    //$(".multiSelect").select2();
//    $(".multiSelect").select2({
//        tags: "true",
//        placeholder: "Select One",
//        allowClear: true
//    });
    $('.date').datepicker({format: DATE_FORMAT_UI, autoclose: true});
    $('.dateTime').datetimepicker({format: DATE_FORMAT_UI, autoclose: true});
    $('.date').prop('placeholder', DATE_FORMAT_UI);
    $('.cb, .rb').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green',
    });
    $(".collapse-btn").click(function () {
        $(this).closest("legend").next(".collapse-block").slideToggle("slow", function () {
            $(this).find("i").toggleClass("fa fa-minus fa fa-plus");
        });
    });
    $(document).on('change', '.btn-file :file', function () {
        var input = $(this), label = input.val().replace(/\\/g, '/').replace(/.*\//, ''), output = $(this).parents('.input-group').find(':text');
        output.val(label);
    });
    $(document).on('change', '.file', function () {
        var id = this.id;
        var value = this.value;
        var idSegments = id.split("_");
        var rowId = idSegments[1];
        if (value) {
            $("#requiredDocId_" + rowId).prop("checked", true);
            $("#fileName_" + rowId).val(value);
        }
    });
    var swfPath = 'libs/jquery_datatable/TableTools-2.2.4/swf/copy_csv_xls_pdf.swf';
    $('#example').DataTable({
        sPaginationType: "full_numbers",	//for first/last button in padding
        lengthMenu: [[5, 10, 15, 25, 50, -1], [5, 10, 15, 25, 50, "All"]],
        responsive: true,

        dom: 'T<"clear">lfrtip',
        oTableTools: {
            sSwfPath: swfPath,
            aButtons: ["copy", "csv", "print"]
        },
        oLanguage: {
            sLengthMenu: "Mostrar _MENU_ registros por p�gina",
            sZeroRecords: "Nenhum registro encontrado",
            sInfo: "Mostrando _START_ / _END_ de _TOTAL_ registro(s)",
            sInfoEmpty: "Mostrando 0 / 0 de 0 registros",
            sInfoFiltered: "(filtrado de _MAX_ registros)",
            sSearch: "Pesquisar: ",
            oPaginate: {
                sFirst: "In�cio",
                sPrevious: "Anterior",
                sNext: "Pr�ximo",
                sLast: "�ltimo"
            }
        },
        "aaSorting": [[1, 'desc']],
        //"aoColumnDefs": [
        //	{"sType": "num-html", "aTargets": [0]}
        //]
    });
    $("#btnExport").click(function (e) {
        window.open('data:application/vnd.ms-excel,' + $('#example').jsp());
        //Response.AddHeader("Content-Disposition", "attachment;filename=download.xlsx");
        //x-fast
        //e.preventDefault();
    });
});
function dtlAdd(selectorClass) {
    var selectorIndex = $("." + selectorClass).length, cloneElement = "." + selectorClass + ":last";
    $(cloneElement).clone().insertAfter(cloneElement).show().find("*").each(function () {
        var name = this.name || "";
        this.name = name.replace(/\d+/, selectorIndex)
    });
    var removeBtn = '<div class="box-footer"><div class="pull-right"><a class="btn btn-block btn-danger btn-xs" onclick="dtlRemove(\'' + selectorClass + '\', this);"><i class="fa fa-minus"></i>Remove</a></div></div>';
    if ($(cloneElement + " > .box-footer").length == 0) {
        $(cloneElement + " > .box-body").after(removeBtn);
    }
    /*------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /*var removeBtn = '<div class="pull-right" style="padding-right: 10px"><a class="btn btn-block btn-danger btn-xs" onclick="dtlRemove(\'' + selectorClass + '\', this);"><i class="fa fa-minus">Remove</i></a></div>';if ($(cloneElement + " > .box-footer").length == 0) {alert('dkjasldfj');$(cloneElement).after(removeBtn);}*/
    /*------------------------------------------------------------------------------------------------------------------------------------------------------*/
    $(cloneElement).find('input').val('');
    $(cloneElement).find('textArea').val('');
    $(cloneElement).find('select').val('');
    selectorIndex++;
    return true;
}
function dtlRemove(selectorClass, _this) {
    $(_this).closest("." + selectorClass).remove();
    return true;
}
/*document.onscroll = function () {
    if ($(window).scrollTop() >= headerHeight + 75) {
        $('.navbar').removeClass('navbar-static-top').addClass('navbar-fixed-top');
    }
    else {
        $('.navbar').removeClass('navbar-fixed-top').addClass('navbar-static-top');
    }
}
document.onscroll = function () {
    var header = $('header.banner')
    if ($(window).scrollTop() > headerHeight + 75) {
        header.animate({
            // place your own css styles here
            opacity: 0.5,
        }, 5000, function () {
            header.removeClass('navbar-static-top').addClass('navbar-fixed-top');
        })
    }
    else {
        header.removeClass('navbar-fixed-top').addClass('navbar-static-top');
    }
}*/
/*======================================================================================================================================================*/