<%@ include file="../common/IncludeTop.jsp" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/head.jsp"%>

<bean:define id="product" name="catalogForm" property="product"/>
<bean:define id="itemList" name="catalogForm" property="itemList"/>

<div id="BackLink">

  <html:link paramId="categoryId" paramName="product" paramProperty="categoryId" page="/shop/viewCategory.shtml?method=viewCategory">
    Return to <bean:write name="product" property="categoryId"/></html:link>

</div>

<div id="Product">

  <%--<h2 align="center"><bean:write name="product" property="name"/></h2>

--%><center>  
	<%--  add next button and prior button --%>
	<ec:table items="itemList" var="item"
		      view="ssview"      title="${product.name}"
			  action=""		  
			  csvFileName="product.csv"
			  xlsFileName="product.xls"
			  pdfFileName="product.pdf"
			  classic="false"
	          resizeColWidth="true"        
	          rowsDisplayed="5"
	          listWidth="" retrieveRowsCallback="process" 
	          toolbarContent="firstpage|prevpage|pagesnum|nextpage|lastpage|pagejump|totalcount|refresh">
		<ec:row>		
			<ec:column property="itemId"  title="Item ID " width="">
				<a	href="${ctx}/shop/viewItem.shtml?method=viewItem&itemId=${item.itemId}">${item.itemId}</a>
			</ec:column>
			<ec:column property="product.productId"  title="Product ID" width="">
			
			</ec:column>
			<ec:column property="attribute1"  title="Description" width="">
			</ec:column>
			<ec:column property="listPrice"  title="List Price" width="">
			</ec:column>
			<ec:column property="listPrice"  title=" " width="">
			<a	href="${ctx}/shop/cart.shtml?method=addItemToCart&workingItemId=${item.itemId}">Add to Cart</a>
			</ec:column>
		</ec:row>
	</ec:table>
</center>
</div>

<%@ include file="../common/IncludeBottom.jsp" %>
