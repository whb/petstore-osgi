<%@ include file="../common/IncludeTop.jsp" %>

<div id="Catalog">

  <html:form action="/shop/newOrder.shtml" styleId="orderForm" method="post">

    <table>
      <tr><th colspan=2>
        Payment Details
      </th></tr><tr><td>
      Card Type:</td><td>
      <html:select name="orderForm" property="order.cardType">
        <html:options name="orderForm" property="creditCardTypes"/>
      </html:select>
    </td></tr>
      <tr><td>
        Card Number:</td><td><html:text name="orderForm" property="order.creditCard"/>
        * Use a fake number!
      </td></tr>
      <tr><td>
        Expiry Date (MM/YYYY):</td><td><html:text name="orderForm" property="order.expiryDate"/>
      </td></tr>
      <tr><th colspan=2>
        Billing Address
      </th></tr>

      <tr><td>
        First name:</td><td><html:text name="orderForm" property="order.billToFirstName"/>
      </td></tr>
      <tr><td>
        Last name:</td><td><html:text name="orderForm" property="order.billToLastName"/>
      </td></tr>
      <tr><td>
        Address 1:</td><td><html:text size="40" name="orderForm" property="order.billAddress1"/>
      </td></tr>
      <tr><td>
        Address 2:</td><td><html:text size="40" name="orderForm" property="order.billAddress2"/>
      </td></tr>
      <tr><td>
        City: </td><td><html:text name="orderForm" property="order.billCity"/>
      </td></tr>
      <tr><td>
        State:</td><td><html:text size="4" name="orderForm" property="order.billState"/>
      </td></tr>
      <tr><td>
        Zip:</td><td><html:text size="10" name="orderForm" property="order.billZip"/>
      </td></tr>
      <tr><td>
        Country: </td><td><html:text size="15" name="orderForm" property="order.billCountry"/>
      </td></tr>

      <tr><td colspan=2>
        <html:checkbox name="orderForm" property="shippingAddressRequired"/> Ship to different address...
      </td></tr>

    </table>

    <input type="submit" name="submit" value="Continue">

  </html:form>

</div>

<%@ include file="../common/IncludeBottom.jsp" %>