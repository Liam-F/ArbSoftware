<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<sql:setDataSource var="ds" dataSource="jdbc/ArbitrageTradingSite" />

<sql:query dataSource="${ds}" sql="select * from ArbitrageTrades order by profit desc" var="results" />


<div class="table-responsive">
	<table class="table">
		<thead>
			<tr>
				<th>Profit</th>
				<th>Arb %</th>
				<th>Player One</th>
				<th>Bet</th>
				<th>Site</th>
				<th>Odds</th>
				<th>Player Two</th>
				<th>Bet</th>
				<th>Site</th>
				<th>Odds</th>
				<th>Sport</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="col" items="${results.rows}">
				<tr>
					<td>${col.profit}</td>
					<td>${col.arbPercentage}</td>
					<td>${col.playerOne}</td>
					<td>${col.playerOneBet}</td>
					<td>${col.playerOneSite}</td>
					<td>${col.playerOneOdds}</td>
					<td>${col.playerTwo}</td>
					<td>${col.playerTwoBet}</td>
					<td>${col.playerTwoSite}</td>
					<td>${col.playerTwoOdds}</td>
					<td>${col.sport}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<!-- 

<table class="table text-right table-hover table-bordered table-striped">
		<thead>
			<tr>
				<th>Profit</th>
				<th>Arb %</th>
				<th>Player One</th>
				<th>Player One Bet</th>
				<th>Player One Site</th>
				<th>Player One Odds</th>
				<th>Player Two</th>
				<th>Player Two Bet</th>
				<th>Player Two Site</th>
				<th>Player Two Odds</th>
				<th>Sport</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="col" items="${results.rows}">
				<tr>
					<td>${col.profit}</td>
					<td>${col.arbPercentage}</td>
					<td>${col.playerOne}</td>
					<td>${col.playerOneBet}</td>
					<td>${col.playerOneSite}</td>
					<td>${col.playerOneOdds}</td>
					<td>${col.playerTwo}</td>
					<td>${col.playerTwoBet}</td>
					<td>${col.playerTwoSite}</td>
					<td>${col.playerTwoOdds}</td>
					<td>${col.sport}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	-->