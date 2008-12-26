<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.Authentication" %>
<%@ include file="../common/IncludeTop.jsp" %>

<div id="Catalog" align="center">

<body>
<h1>SPRING SECURITY SIGN IN INFORMATION</h1>

<sec:authorize ifAllGranted="ROLE_SUPERVISOR"><font color="#ff0000"> 
	You are a supervisor!</font>  
</sec:authorize>

<table border="1">
<tr><th>Property</th><th>Value</th></tr>
<tr>
<td>principal.username</td><td><sec:authentication property="principal.username"/></td>
</tr>
<tr>
<td>principal.password</td><td><sec:authentication property="principal.password"/></td>
</tr>
<tr>
<td>principal.enabled</td><td><sec:authentication property="principal.enabled"/></td>
</tr>
<tr>
<td>principal.accountNonLocked</td><td><sec:authentication property="principal.accountNonLocked"/></td>
</tr>
</table>

<%		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username="";
        String password="";
		if (auth != null) { 
			
			username=((org.springframework.security.userdetails.User)auth.getPrincipal()).getUsername();
			password=((org.springframework.security.userdetails.User)auth.getPrincipal()).getPassword();
	
     } %>

  <html:form action="/shop/signon.shtml" method="POST">

  <input type="hidden" name="username" value="<%=username%>"/>
  <input type="hidden" name="password" value="<%=password%>"/>
  <input type="submit" name="submit" value="Continue"/>

  </html:form>
</body>

</div>

<%@ include file="../common/IncludeBottom.jsp" %>