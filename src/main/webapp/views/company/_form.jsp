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
            <form:label path='picFile'><spring:message code='picFile' text='Pic File'/></form:label>
                <c:url var='picFile' value='/company/getPhoto/${company.picFile}'/>
                <img height="110px" width="90px" alt='${company.picFile}' src='${picFile}'/>
                <form:hidden path='picFile'/>
                <input id='picFileOBJ' name='picFileOBJ' type='file' accept='image/*'/>
            <form:errors path='picFile' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='fullName'><spring:message code='fullName' text='Full Name'/></form:label>
            <form:input class='form-control' path='fullName' type='text' required='true' size='30' maxlength='100'/>
            <form:errors path='fullName' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='orientationDate'><spring:message code='orientationDate' text='Orientation Date'/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-date' path='orientationDate'  />
             </div>
            <form:errors path='orientationDate' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='email'><spring:message code='email' text='Email'/></form:label>
            <form:input class='form-control' path='email' type='email' size='30' maxlength='30'/>
            <form:errors path='email' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='companyType'><spring:message code='companyType' text='Company Type'/></form:label>
            <form:select class='form-control' path='companyType' name='companyType' id='companyType' >
                <form:option value='PUBLIC_LIMITED' label='PUBLIC LIMITED'/>
                <form:option value='PRIVATE_LIMITED' label='PRIVATE LIMITED'/>
                <form:option value='NGO' label='NGO'/>
            </form:select>
            <form:errors path='companyType' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='web'><spring:message code='web' text='Web'/></form:label>
            <form:input class='form-control' path='web' type='text' size='30' maxlength='100'/>
            <form:errors path='web' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='regNo'><spring:message code='regNo' text='Reg No'/></form:label>
            <form:input class='form-control' path='regNo' type='text' size='30' maxlength='30'/>
            <form:errors path='regNo' cssClass='error' element='div'/>
        </div>
    </div>

</div>   