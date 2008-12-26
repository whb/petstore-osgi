<%@ include file="../common/IncludeTop.jsp" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div id="Catalog">  
	<table width="366" border="1">
			  <tr>
			    <th><div align="center">ProductId</div></th>
			    <th><div align="center">Name</div></th>
			    <th><div align="center">DOperate</div></th>
			    <th><div align="center">UOperate</div></th>
			  </tr>
			  <c:forEach items="${list}" var="categoryVo">
				<tr>
			    	<td><div align="center">${categoryVo.categoryId}</div></td>
			        <td><div align="center">${categoryVo.name}</div></td>
			        <td><div align="center"><a href="${ctx}/shop/categoryOperation.shtml?method=delete&categoryId=${categoryVo.categoryId}">Delete</a></div></td>
			        <td><div align="center"><a href="${ctx}/shop/categoryOperation.shtml?method=update&categoryId=${categoryVo.categoryId}">Update</a></div></td>
			    </tr>
			  </c:forEach>
	</table>
<form action="${ctx}/catalog/NewCategoryForm.jsp" method="get">
		<input type="submit" name="submit" value="Add Category"/>
	</form>
</div>

<%@ include file="../common/IncludeBottom.jsp" %>