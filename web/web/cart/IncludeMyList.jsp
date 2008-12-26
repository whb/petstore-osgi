<bean:define id="myList" name="accountForm" property="myList"/>

<logic:present name="myList">
  <p>
    Pet Favorites
    <br/>
    Shop for more of your favorite pets here.
  </p>
  <ul>
    <logic:iterate id="product" name="myList">
      <li><html:link paramId="productId" paramName="product" paramProperty="productId" page="/shop/viewProduct.shtml?method=viewProduct">
        <bean:write name="product" property="name"/></html:link>
      (<bean:write name="product" property="productId"/>)</li>
    </logic:iterate>
  </ul>

  <p>
    
  </p>

</logic:present>




