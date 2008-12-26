<%@ include file="../common/IncludeTop.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div id="Catalog">
  <form action="${ctx}/shop/categoryOperation.shtml?method=doEdit" method="post">
    <h3>Category  Information</h3>
    <table>
	      <tr>
	        <td>CategoryId:</td><td> 
	        	<input id="categoryId" readonly name="categoryId" value="${currentEntity.categoryId}">      	
	        </td>
	      </tr>
	      <tr>
		      <td>Name:</td><td>      	
		      	<input id="name"  name="name" value="${currentEntity.name}">     	
		      </td>
	      </tr>
	      <tr>
		      <td>Description:</td><td>      	
		      	<input id="description"  name="description" value="${currentEntity.description}">    	
		      </td>
	      </tr>
    </table>
    <br>
    <input type="submit" name="submit" value="Update Category"/>
  </form>
</div>

<%@ include file="../common/IncludeBottom.jsp" %>