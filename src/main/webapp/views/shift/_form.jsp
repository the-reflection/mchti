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
            <form:label class='required' path='title'><spring:message code='title' text='Title'/></form:label>
            <form:input class='form-control' path='title' type='text' required='true' size='30' maxlength='100'/>
            <form:errors path='title' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='titleBng'><spring:message code='titleBng' text='Title Bng'/></form:label>
            <form:textarea class='form-control' path='titleBng' type='text' size='30' maxlength='500'/>
            <form:errors path='titleBng' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='startHr'><spring:message code='startHr' text='Start Hr'/></form:label>
            <form:input class='form-control' path='startHr' type='number' required='true' size='15' maxlength='15'/>
            <form:errors path='startHr' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='endHr'><spring:message code='endHr' text='End Hr'/></form:label>
            <form:input class='form-control' path='endHr' type='number' required='true' size='15' maxlength='15'/>
            <form:errors path='endHr' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='startMin'><spring:message code='startMin' text='Start Min'/></form:label>
            <form:input class='form-control' path='startMin' type='number' size='15' maxlength='15'/>
            <form:errors path='startMin' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='endMin'><spring:message code='endMin' text='End Min'/></form:label>
            <form:input class='form-control' path='endMin' type='number' size='15' maxlength='15'/>
            <form:errors path='endMin' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='startBufMin'><spring:message code='startBufMin' text='Start Buf Min'/></form:label>
            <form:input class='form-control' path='startBufMin' type='number' size='15' maxlength='15'/>
            <form:errors path='startBufMin' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='endBufMin'><spring:message code='endBufMin' text='End Buf Min'/></form:label>
            <form:input class='form-control' path='endBufMin' type='number' size='15' maxlength='15'/>
            <form:errors path='endBufMin' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='orientationMin'><spring:message code='orientationMin' text='Orientation Min'/></form:label>
            <form:input class='form-control' path='orientationMin' type='number' size='15' maxlength='15'/>
            <form:errors path='orientationMin' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='shiftType'><spring:message code='shiftType' text='Shift Type'/></form:label>
            <form:select class='form-control' path='shiftType' name='shiftType' id='shiftType' >
                <form:option value='GENERAL' label='GENERAL'/>
                <form:option value='ROSTER' label='ROSTER'/>
            </form:select>
            <form:errors path='shiftType' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='remarks'><spring:message code='remarks' text='Remarks'/></form:label>
            <form:textarea class='form-control' path='remarks' type='text' size='30' maxlength='500'/>
            <form:errors path='remarks' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='isActive'><spring:message code='isActive' text='Is Active'/></form:label>
            <br><form:checkbox class='cb' path='isActive'/>
            <form:errors path='isActive' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='slNo'><spring:message code='slNo' text='Sl No'/></form:label>
            <form:input class='form-control' path='slNo' type='number' size='15' maxlength='15'/>
            <form:errors path='slNo' cssClass='error' element='div'/>
        </div>
    </div>

</div>   