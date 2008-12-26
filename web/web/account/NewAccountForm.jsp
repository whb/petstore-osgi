<%@ include file="../common/IncludeTop.jsp" %>

<div id="Catalog">

  <html:form action="/shop/newAccount.shtml" method="post">

    <html:hidden name="accountForm" property="validation" value="new"/>

    <h3>User Information</h3>

    <table>
      <tr>
        <td>User ID:</td><td><html:text name="accountForm" property="username"/></td>
      </tr><tr>
      <td>New password:</td><td><html:password name="accountForm" property="password"/></td>
    </tr><tr>
      <td>Repeat password:</td><td><html:password name="accountForm" property="repeatedPassword"/></td>
    </tr>
    </table>

    <%@ include file="IncludeAccountFields.jsp" %>

    <input type="submit" name="submit" value="Create Account"/>

  </html:form>

</div>

<%@ include file="../common/IncludeBottom.jsp" %>