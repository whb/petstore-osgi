<%@ include file="../common/IncludeTop.jsp" %>

<div align="center">
  <body onload="document.f.j_username.focus();">
    <h1>SPRING SECURITY SIGN IN</h1>
	<P align="left">Valid users:
	<br>username <b>j2ee</b>, password <b>j2ee</b>
	<br>username <b>ACID</b>, password <b>ACID</b>
	<p align="center">
    <c:if test="${not empty param.login_error}">
      <font color="red">
        Your login attempt was not successful, try again.<br/><br/>
        Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
      </font>
    </c:if>

    <form name="f" action="<c:url value='../j_spring_security_check'/>" method="POST">
      <table align="center">
        <tr><td>User:</td><td><input type='text' name='j_username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/></td></tr>
        <tr><td>Password:</td><td><input type='password' name='j_password'></td></tr>
        <tr><td><input type="checkbox" name="_acegi_security_remember_me"></td><td>Don't ask for my password for two weeks</td></tr>

        <tr><td colspan='2'><input name="submit" type="submit"></td></tr>
        <tr><td colspan='2'><input name="reset" type="reset"></td></tr>
      </table>

    </form>
    Need a username and password?
    <html:link page="/shop/newAccountForm.shtml">Register Now!</html:link>

  </body>

</div>















<%@ include file="../common/IncludeBottom.jsp" %>

