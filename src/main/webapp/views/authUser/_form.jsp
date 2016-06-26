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
            <form:label class='required' path='username'><spring:message code='username' text='Username'/></form:label>
            <form:input class='form-control' path='username' type='text' required='true' size='30' maxlength='30'/>
            <form:errors path='username' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='password'><spring:message code='password' text='Password'/></form:label>
            <form:textarea class='form-control' path='password' type='text' required='true' size='30' maxlength='128'/>
            <form:errors path='password' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='displayName'><spring:message code='displayName' text='Display Name'/></form:label>
            <form:input class='form-control' path='displayName' type='text' required='true' size='30' maxlength='9'/>
            <form:errors path='displayName' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='fullName'><spring:message code='fullName' text='Full Name'/></form:label>
            <form:input class='form-control' path='fullName' type='text' required='true' size='30' maxlength='50'/>
            <form:errors path='fullName' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='gender'><spring:message code='gender' text='Gender'/></form:label>
            <form:select class='form-control' path='gender' name='gender' id='gender' >
                <form:option value='MALE' label='Male'/>
                <form:option value='FEMALE' label='Female'/>
                <form:option value='OTHER' label='Other'/>
            </form:select>
            <form:errors path='gender' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='dob'><spring:message code='dob' text='Dob'/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-date' path='dob'  />
             </div>
            <form:errors path='dob' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='doj'><spring:message code='doj' text='Doj'/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-date' path='doj'  />
             </div>
            <form:errors path='doj' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='email'><spring:message code='email' text='Email'/></form:label>
            <form:input class='form-control' path='email' type='text' size='30' maxlength='30'/>
            <form:errors path='email' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='picFile'><spring:message code='picFile' text='Pic File'/></form:label>
                <c:url var='picFile' value='/authUser/getPhoto/${authUser.picFile}'/>
                <img height="110px" width="90px" alt='${authUser.picFile}' src='${picFile}'/>
                <form:hidden path='picFile'/>
                <input id='picFileOBJ' name='picFileOBJ' type='file' accept='image/*'/>
            <form:errors path='picFile' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='authRoles'><spring:message code='authRoles' text='Auth Roles'/></form:label>
            <form:select class='form-control' path='authRoles' multiple='true' required='true' >
                <form:options items='${authRoles}' itemValue='id'/>
            </form:select>
            <form:errors path='authRoles' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='enabled'><spring:message code='enabled' text='Enabled'/></form:label>
            <br><form:checkbox class='cb' path='enabled'/>
            <form:errors path='enabled' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='accountNonExpired'><spring:message code='accountNonExpired' text='Account Non Expired'/></form:label>
            <br><form:checkbox class='cb' path='accountNonExpired'/>
            <form:errors path='accountNonExpired' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='accountNonLocked'><spring:message code='accountNonLocked' text='Account Non Locked'/></form:label>
            <br><form:checkbox class='cb' path='accountNonLocked'/>
            <form:errors path='accountNonLocked' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='credentialsNonExpired'><spring:message code='credentialsNonExpired' text='Credentials Non Expired'/></form:label>
            <br><form:checkbox class='cb' path='credentialsNonExpired'/>
            <form:errors path='credentialsNonExpired' cssClass='error' element='div'/>
        </div>
    </div>

</div>   