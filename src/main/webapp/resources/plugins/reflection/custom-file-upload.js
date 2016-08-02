/**
 * Created by hoshen.mahmud on 12-Jul-16.
 */


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