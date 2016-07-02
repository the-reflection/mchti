<%@ page contentType='text/html; charset=UTF-8' language='java' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>

<style>
    .error {
        color: #ff0000;
    }
    .errorblock {
        color: #000;
        background-color: #ffEEEE;
        border: 3px solid #ff0000;
        padding: 8px;
        margin: 16px;
    }
</style>

<script type='text/javascript'>
    function getCodableDTOEmployee(code, lblCaption) {
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/employee/getCodableDTO',
            data: {code: code},
            success: function (d) {
                //alert('ok:'+d)
                $('#' + lblCaption).text(d)
            },
            error: function (err) {
                //alert('err mac:'+err)
                $('#' + lblCaption).text(err)
            }
        });
    }
</script>

<form:errors path='*' cssClass='errorblock' element='div' />
<form:hidden path='id'/>
<div>  

    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='employee'><spring:message code='employee' text='Employee'/></form:label>
            <form:input class='form-control' path='employee.code' required='true' onkeyup='getCodableDTOEmployee(this.value,"employee_caption")' type='text' size='8'/>
            <label id='employee_caption'>${manualAttnDaily.employee.fullName}</label>
            <form:errors path='employee' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='attnDate'><spring:message code='attnDate' text='Attn Date'/></form:label>
                <div class='input-group'>
                    <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                    <form:input class='form-control dtp-date' path='attnDate' type="date" required='true'  />
            </div>
            <form:errors path='attnDate' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='inTime'><spring:message code='inTime' text='In Time'/></form:label>
            <form:input class='form-control' path='inTime' type='time' size='5' maxlength='5'/>
            <form:errors path='inTime' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='outTime'><spring:message code='outTime' text='Out Time'/></form:label>
            <form:input class='form-control time' path='outTime' placeholder="HH:mm" type="time" size='5' maxlength='5'/>
            <form:errors path='outTime' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='remarks'><spring:message code='remarks' text='Remarks'/></form:label>
            <form:textarea class='form-control' path='remarks' type='text' size='30' maxlength='500'/>
            <form:errors path='remarks' cssClass='error' element='div'/>
        </div>
    </div>

</div>   