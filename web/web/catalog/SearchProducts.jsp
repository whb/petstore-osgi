<%@ include file="../common/IncludeTop.jsp"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/head.jsp"%>
<%--<bean:define id="productList" name="catalogBean" property="productList"/>--%>
<bean:define id="category" name="catalogForm" property="category"/>
<bean:define id="productList" name="catalogForm" property="productList" />

<div id="BackLink">

	<html:link page="/shop/index.shtml?method=index">Return to Main Menu</html:link>

</div>

<div id="Catalog">
	<center>
			<ec:table items="productList" var="product" view="ssview"
				title="List"
				action=""
				csvFileName="product.csv"
				xlsFileName="product.xls"
				pdfFileName="product.pdf"
				classic="false"
				resizeColWidth="true"
				rowsDisplayed="3"
				listWidth="" 
				retrieveRowsCallback="process"
				toolbarContent="firstpage|prevpage|pagesnum|nextpage|lastpage|pagejump|totalcount|refresh">
				<ec:row>
					<ec:column property="productId" title="Product ID " width="">
						<a
							href="${ctx}/shop/viewProduct.shtml?method=viewProduct&productId=${product.productId}">${product.productId}</a>
					</ec:column>
					<ec:column property="name" title="Name" width="">
					</ec:column>
				</ec:row>
			</ec:table>
	</center>

	
</div>
<%@ include file="../common/IncludeBottom.jsp" %>




