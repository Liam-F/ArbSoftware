<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Trades"></c:param>
</c:import>

<c:choose>
	<c:when
		test='${sessionScope.email == null || sessionScope.email == ""}'>
		<h3>Please Log In to view this page</h3>
	</c:when>
	<c:when
		test='${sessionScope.email != null || sessionScope.email != ""}'>

		<div class="row">
			<div class="col-xs-12 col-sm-4 col-md-2">
				<form name="input" action="/ArbitrageWebsite/Controller?action=hiddenArbPage" method="get">
					<input type="hidden" name="action" value="hiddenArbPage" />
					<div class="form-group">
						<label class="sr-only" for="exampleInputAmount">Amount</label>
						<div class="input-group">
							<div class="input-group-addon">&euro;</div>
							<input type="number" id="inputAmount" class="form-control" placeholder="Amount" name="userAmt" value="${inputAmount}">
							<div class="input-group-addon">.00</div>
						</div>
					</div>
					<div>
						<button class="btn btn-lg btn-block all-buttons" type="submit">Submit</button>
					</div>
				</form>
			</div>
			<div class="col-xs-12 col-sm-8 col-md-10">
				<div class="table-responsive table-bordered">
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
						<tbody id="output">
							<c:import url="getResults.jsp">
							</c:import>
						</tbody>
					</table>
					
				</div>
			</div>
		</div>
	</c:when>
</c:choose>

<c:import url="footer.jsp" />
