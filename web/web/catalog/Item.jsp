<%@ include file="../common/IncludeTop.jsp"%>

<bean:define id="product" name="catalogForm" property="product" />
<bean:define id="item" name="catalogForm" property="item" />

<div id="BackLink">

	<html:link paramId="productId" paramName="product"
		paramProperty="productId" page="/shop/viewProduct.shtml?method=viewProduct">
    Return to <bean:write name="product" property="name" />
	</html:link>

</div>

<div id="Catalog">

	<table>

		<tr>
			<td>
				<bean:write filter="false" name="product" property="description" />
			</td>
		</tr>
		<tr>
			<td>
				<b><bean:write name="item" property="itemId" /> </b>
			</td>
		</tr>
		<tr>
			<td>
				<b><font size="4"> <bean:write name="item"
							property="attribute1" /> <bean:write name="item"
							property="attribute2" /> <bean:write name="item"
							property="attribute3" /> <bean:write name="item"
							property="attribute4" /> <bean:write name="item"
							property="attribute5" /> <bean:write name="item"
							property="product.name" /> </font> </b>
			</td>
		</tr>
		<tr>
			<td>
				<bean:write name="product" property="name" />
			</td>
		</tr>
		<tr>
			<td>
				<bean:write name="item" property="listPrice" format="$#,##0.00" />
			</td>
		</tr>

		<tr>
			<td>
				<html:link styleClass="Button" paramId="workingItemId"
					paramName="item" paramProperty="itemId"
					page="/shop/cart.shtml?method=addItemToCart">
        Add to Cart</html:link>
			</td>
		</tr>
	</table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>



