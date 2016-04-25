<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<sql:setDataSource var="ds" dataSource="jdbc/ArbitrageTradingSite" />

<sql:query dataSource="${ds}" sql="select * from ArbitrageTrades order by profit desc" var="results" />

<c:forEach var="col" items="${results.rows}">
	<tr>	
		<c:choose>
			<c:when test="${ not empty sessionScope.userSetAmt}">
				<td class="round">${(col.profit / 100) * sessionScope.userSetAmt }</td>
			</c:when>
			<c:when test="${ empty sessionScope.userSetAmt }">
				<td class="round">${col.profit}</td>
			</c:when>
		</c:choose>
		<td>${col.playerOne}</td>
		<c:choose>
			<c:when  test="${ not empty sessionScope.userSetAmt}">
				<td class="round">${(col.playerOneBet / 100) * sessionScope.userSetAmt }</td>
			</c:when>
			<c:when test="${ empty sessionScope.userSetAmt }">
				<td class="round">${col.playerOneBet}</td>
			</c:when>
		</c:choose>
		<td>${col.playerOneSite}</td>
		<td>${col.playerOneOdds}</td>
		<td>${col.playerTwo}</td>
		<c:choose>
			<c:when  test="${ not empty sessionScope.userSetAmt}">
				<td class="round">${(col.playerTwoBet / 100) * sessionScope.userSetAmt }</td>
			</c:when>
			<c:when test="${ empty sessionScope.userSetAmt }">
				<td class="round">${col.playerTwoBet}</td>
			</c:when>
		</c:choose>
		<td>${col.playerTwoSite}</td>
		<td>${col.playerTwoOdds}</td>
		<td>${col.sport}</td>
	</tr>
</c:forEach>


