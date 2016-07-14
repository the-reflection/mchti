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
                $('#' + lblCaption).text(d);
            },
            error: function (err) {
                //alert('err mac:'+err)
                $('#' + lblCaption).text(err);
            }
        });
    }
</script>

<form:errors path='*' cssClass='errorblock' element='div' />
<form:hidden path='id'/>
<div>  
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-4'>
        <div class='form-group'>
            <form:label class='required' path='code'><spring:message code='code' text='Code'/></form:label>
            <form:input class='form-control' path='code' type='text' required='true' size='30' maxlength='20'/>
            <form:errors path='code' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-4'>
        <div class='form-group'>
            <form:label class='required' path='employee'><spring:message code='employee' text='Employee'/></form:label>
            <label id='employee_caption' class="pull-right c-gray">(${assignmentHr.employee.fullName})</label>
            <form:input class='form-control' path='employee.code' required='true' onkeyup='getCodableDTOEmployee(this.value,"employee_caption")' type='text' size='8'/>
            <form:errors path='employee' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-4'>
        <div class='form-group'>
            <form:label path='empGroup'><spring:message code='empGroup' text='Emp Group'/></form:label>
            <form:select class='form-control' path='empGroup' name='empGroup' id='empGroup' >
                <form:option value='${null}' label='Select One'/>
                <form:option value='ACTIVE' label='ACTIVE'/>
                <form:option value='EXTERNAL' label='EXTERNAL'/>
                <form:option value='RETIRED' label='RETIRED'/>
                <form:option value='TERMINATED' label='TERMINATED'/>
            </form:select>
            <form:errors path='empGroup' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='department'><spring:message code='department' text='Department'/></form:label>
            <form:select class='form-control selectWithSearch' path='department.id' name='department' id='department' >
                <form:option value='${null}' label='Select One'/>
                <form:options items='${departments}' itemValue='id'/>
            </form:select>
            <form:errors path='department' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='designation'><spring:message code='designation' text='Designation'/></form:label>
            <form:select class='form-control' path='designation.id' name='designation' id='designation' >
                <form:option value='${null}' label='Select One'/>
                <form:options items='${designations}' itemValue='id'/>
            </form:select>
            <form:errors path='designation' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='startDate'><spring:message code='startDate' text='Start Date'/></form:label>
                <div class='input-group'>
                    <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                    <form:input class='form-control dtp-date' path='startDate' required='true'  />
            </div>
            <form:errors path='startDate' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='endDate'><spring:message code='endDate' text='End Date'/></form:label>
                <div class='input-group'>
                    <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                    <form:input class='form-control dtp-date' path='endDate'  />
            </div>
            <form:errors path='endDate' cssClass='error' element='div'/>
        </div>
    </div>
</div>   