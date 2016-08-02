/**
 * Created by hoshen.mahmud on 12-Jul-16.
 */


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