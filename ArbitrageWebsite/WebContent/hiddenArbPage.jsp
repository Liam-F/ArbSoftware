<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Trades"></c:param>
</c:import>

<c:choose>
	<c:when test='${sessionScope.email == null || sessionScope.email == ""}'>
		<h3>Please Log In to view this page</h3>
	</c:when>
	<c:when test='${sessionScope.email != null || sessionScope.email != ""}'>
	<div id="output" class="table-responsive table-bordered">
	<c:import url="getResults.jsp">
	</c:import>
	
</div>
	</c:when>
</c:choose>

<c:import url="footer.jsp" />
