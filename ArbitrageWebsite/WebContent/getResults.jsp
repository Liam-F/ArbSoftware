<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<sql:setDataSource var="ds" dataSource="jdbc/ArbitrageTradingSite" />

<sql:query dataSource="${ds}"
	sql="select * from ArbitrageTrades order by profit desc" var="results" />


<div class="row myDiv">
	<div class="col-xs-12 col-sm-4 col-md-2">
		<form>
			<div class="form-group">
				<label class="sr-only" for="exampleInputAmount">Amount (in
					dollars)</label>
				<div class="input-group">
					<div class="input-group-addon">&euro;</div>
					<input type="number" class="form-control" id="inputAmount"
						placeholder="Amount">
					<div class="input-group-addon">.00</div>
				</div>
			</div>
			<div class="checkbox">
				<label> <input type="checkbox" value="tennis">
					Tennis
				</label>
			</div>
			<div class="checkbox">
				<label> <input type="checkbox" value="snooker">
					Snooker
				</label>
			</div>
		</form>
	</div>
	<div class="col-xs-12 col-sm-8 col-md-10">
		<div class="table-responsive">
			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th>Profit</th>
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
		</div>

	</div>

</div>
<!--end of row-->




<!-- 
<div class="row">
	<div class="col-xs-12 col-sm-4 col-md-2">
		<form>
			<div class="form-group">
				<label class="sr-only" for="exampleInputAmount">Amount (in
					dollars)</label>
				<div class="input-group">
					<div class="input-group-addon">&euro;</div>
					<input type="number" class="form-control" id="inputAmount"
						placeholder="Amount">
					<div class="input-group-addon">.00</div>
				</div>
			</div>
			<div class="checkbox">
				<label> <input type="checkbox" value="tennis"> Tennis
				</label>
			</div>
			<div class="checkbox">
				<label> <input type="checkbox" value="snooker">
					Snooker
				</label>
			</div>
		</form>
	</div>

	<div class="col-xs-12 col-sm-8 col-md-10">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th>Profit</th>
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
	</div>
</div> -->



