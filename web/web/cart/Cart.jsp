<%@ include file="../common/IncludeTop.jsp" %>
<logic:notPresent name="cartForm" scope="session">
       <br>
       <br>
       <br>
       <H3 align="center"><font color="#ff0000">sorry, you have no item in the cart, please add one!</font></H3>
       <br>
       <br>
       <br>
</logic:notPresent>
<logic:present name="cartForm" scope="session">

<bean:define id="cart" name="cartForm" property="cart"/>

<div id="BackLink">
  <html:link page="/shop/index.shtml?method=index">Return to Main Menu</html:link>
</div>

<div id="Catalog">

  <div id="Cart">

    <h2>Shopping Cart</h2>
    <html:form action="/shop/cart.shtml?method=updateCartQuantities" method="post">
      <table>
        <tr>
          <th><b>Item ID</b></th>  <th><b>Product ID</b></th>  <th><b>Description</b></th> <th><b>In Stock?</b></th>
          <th><b>Quantity</b></th>  <th><b>List Price</b></th> <th><b>Total Cost</b></th>  <th>&nbsp;</th>
        </tr>

        <logic:equal name="cart" property="numberOfItems" value="0">
          <tr><td colspan="8"><b>Your cart is empty.</b></td></tr>
        </logic:equal>

        <logic:iterate id="cartItem" name="cart" property="cartItems">
          <tr>
            <td>
              <html:link paramId="itemId" paramName="cartItem" paramProperty="item.itemId" page="/shop/viewItem.shtml">
                <bean:write name="cartItem" property="item.itemId"/></html:link></td>
            <td><bean:write name="cartItem" property="item.product.productId"/></td>
            <td>
              <bean:write name="cartItem" property="item.attribute1"/>
              <bean:write name="cartItem" property="item.attribute2"/>
              <bean:write name="cartItem" property="item.attribute3"/>
              <bean:write name="cartItem" property="item.attribute4"/>
              <bean:write name="cartItem" property="item.attribute5"/>
              <bean:write name="cartItem" property="item.product.name"/>
            </td>
            <td><bean:write name="cartItem" property="inStock"/></td>
            <td>
              <input type="text" size="3" name="<bean:write name="cartItem" property="item.itemId"/>"
                     value="<bean:write name="cartItem" property="quantity"/>"/>
            </td>
            <td><bean:write name="cartItem" property="item.listPrice" format="$#,##0.00"/></td>
            <td><bean:write name="cartItem" property="total" format="$#,##0.00"/></td>
            <td><html:link styleClass="Button" paramId="workingItemId" paramName="cartItem" paramProperty="item.itemId"
                           page="/shop/cart.shtml?method=removeItemFromCart">
              Remove</html:link></td>
          </tr>
        </logic:iterate>
        <tr>
          <td colspan="7">
            Sub Total: <bean:write name="cart" property="subTotal" format="$#,##0.00"/>
            <input type="submit" name="update" value="Update Cart"/>

          </td>
          <td>&nbsp;</td>
        </tr>
      </table>
      
    </html:form>

    <logic:notEqual name="cart" property="numberOfItems" value="0">
      <html:link styleClass="Button" page="/shop/order.shtml?method=newOrderForm">Proceed to Checkout</html:link>
    </logic:notEqual>
<!--/shop/order.shtml?method=newOrderForm  -->
  </div>

  <logic:present name="accountForm" scope="session">
    <div id="MyList">
      <logic:equal name="accountForm" property="authenticated" value="true">
        <logic:equal name="accountForm" property="account.listOption" value="true">
          <%@ include file="IncludeMyList.jsp" %>
        </logic:equal>
      </logic:equal>
    </div>
  </logic:present>

  <div id="Separator">&nbsp;</div>

</div>
</logic:present>

<%@ include file="../common/IncludeBottom.jsp" %>



