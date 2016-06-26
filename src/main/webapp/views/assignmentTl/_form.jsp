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
    function getCodableDTOEmployee(code, lblCaption){
        $.ajax({
            type : 'GET',
            url: '${pageContext.request.contextPath}/employee/getCodableDTO',
            data: {code: code},
            success: function(d) {
                //alert('ok:'+d)
                $('#'+lblCaption).text(d)
            },
            error: function(err) {
                //alert('err mac:'+err)
                $('#'+lblCaption).text(err)
            }
        });
    }
</script>

<form:errors path='*' cssClass='errorblock' element='div' />
<form:hidden path='id'/>
<div>  
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='code'><spring:message code='code' text='Code'/></form:label>
            <form:input class='form-control' path='code' type='text' required='true' size='30' maxlength='20'/>
            <form:errors path='code' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='employee'><spring:message code='employee' text='Employee'/></form:label>
            <form:input class='form-control' path='employee.code' required='true' onkeyup='getCodableDTOEmployee(this.value,"employee_caption")' type='text' size='8'/>
            <label id='employee_caption'>${assignmentTl.employee.fullName}</label>
            <form:errors path='employee' cssClass='error' element='div'/>
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
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='weekendShiftOffDay'><spring:message code='weekendShiftOffDay' text='Weekend Shift Off Day'/></form:label>
            <form:select class='form-control' path='weekendShiftOffDay' name='weekendShiftOffDay' id='weekendShiftOffDay' >
                <form:option value='FRIDAY' label='FRIDAY'/>
                <form:option value='SATURDAY' label='SATURDAY'/>
                <form:option value='SUNDAY' label='SUNDAY'/>
                <form:option value='MONDAY' label='MONDAY'/>
                <form:option value='TUESDAY' label='TUESDAY'/>
                <form:option value='WEDNESDAY' label='WEDNESDAY'/>
                <form:option value='THURSDAY' label='THURSDAY'/>
            </form:select>
            <form:errors path='weekendShiftOffDay' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='shift'><spring:message code='shift' text='Shift'/></form:label>
            <form:select class='form-control' path='shift.id' name='shift' id='shift' >
                <form:option value='${null}' label='--- Select ---'/>
                <form:options items='${shifts}' itemValue='id'/>
            </form:select>
            <form:errors path='shift' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='roster'><spring:message code='roster' text='Roster'/></form:label>
            <form:select class='form-control' path='roster.id' name='roster' id='roster' >
                <form:option value='${null}' label='--- Select ---'/>
                <form:options items='${rosters}' itemValue='id'/>
            </form:select>
            <form:errors path='roster' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='isOvertime'><spring:message code='isOvertime' text='Is Overtime'/></form:label>
            <br><form:checkbox class='cb' path='isOvertime'/>
            <form:errors path='isOvertime' cssClass='error' element='div'/>
        </div>
    </div>

</div>   