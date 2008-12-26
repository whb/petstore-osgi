<%@ include file="../common/IncludeTop.jsp" %>

<div id="Catalog">

  <html:form action="/shop/newOrder.shtml" styleId="orderForm" method="post">

    <table>
      <tr><th colspan=2>
        Shipping Address
      </th></tr>

      <tr><td>
        First name:</td><td><html:text name="orderForm" property="order.shipToFirstName"/>
      </td></tr>
      <tr><td>
        Last name:</td><td><html:text name="orderForm" property="order.shipToLastName"/>
      </td></tr>
      <tr><td>
        Address 1:</td><td><html:text size="40" name="orderForm" property="order.shipAddress1"/>
      </td></tr>
      <tr><td>
        Address 2:</td><td><html:text size="40" name="orderForm" property="order.shipAddress2"/>
      </td></tr>
      <tr><td>
        City: </td><td><html:text name="orderForm" property="order.shipCity"/>
      </td></tr>
      <tr><td>
        State:</td><td><html:text size="4" name="orderForm" property="order.shipState"/>
      </td></tr>
      <tr><td>
        Zip:</td><td><html:text size="10" name="orderForm" property="order.shipZip"/>
      </td></tr>
      <tr><td>
        Country: </td><td><html:text size="15" name="orderForm" property="order.shipCountry"/>
      </td></tr>


    </table>

    <input type="submit" name="submit" value="Continue">

  </html:form>

</div>

<%@ include file="../common/IncludeBottom.jsp" %>