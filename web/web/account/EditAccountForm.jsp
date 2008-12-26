<%@ include file="../common/IncludeTop.jsp" %>

<div id="Catalog">

  <html:form method="post" action="/shop/editAccount.shtml">

    <html:hidden name="accountForm" property="validation" value="edit"/>
    <html:hidden name="accountForm" property="username"/>

    <h3>User Information</h3>

    <table>
      <tr>
        <td>User ID:</td><td><bean:write name="accountForm" property="username"/></td>
      </tr><tr>
      <td>New password:</td><td><html:password name="accountForm" property="password"/></td>
    </tr><tr>
      <td>Repeat password:</td><td><html:password name="accountForm" property="repeatedPassword"/></td>
    </tr>
    </table>
    <%@ include file="IncludeAccountFields.jsp" %>

    <input type="submit" name="submit" value="Save Account Information"/>

  </html:form>

  <html:link page="/shop/order.shtml?method=listOrders">My Orders</html:link>

</div>

<%@ include file="../common/IncludeBottom.jsp" %>


