<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Home"></c:param>
</c:import>

<div class="row">
	<div class="col-xs-12 col-sm-6 col-md-8">
		<h3>Are you a gambler?</h3>
		<h3>Would you like to beat the bookmaker and make some money?</h3>
		<h3>Use my technology and watch your profit soar like never before.</h3>
		<h3>Become and arbitrage trader now. Sign up here for free.</h3>
	</div>
	<div style="margin-top: 20px;" class="col-xs-12 col-sm-6 col-md-4" id="logo">
		<img alt="logo" src="${pageContext.request.contextPath}/images/logo.png" class="img-rounded img-responsive">
	</div>
</div>

<div class="row">
	<div class="col-xs-12 col-sm-6 col-md-4 img-height">
		<img alt="luxury house" src="${pageContext.request.contextPath}/images/luxury-house.jpg" class="img-rounded img-responsive"></br>
		<img alt="nice car" src="${pageContext.request.contextPath}/images/luxury-car.jpg" class="img-rounded img-responsive"></br>
	</div>
	
	<div class="col-xs-12 col-sm-6 col-md-4 img-height">
		<img alt="nice holiday" src="${pageContext.request.contextPath}/images/luxury-holiday.jpg" class="img-rounded img-responsive"></br>
		<img alt="rich man" src="${pageContext.request.contextPath}/images/man-with-money-raining.jpg" class="img-rounded img-responsive"></br>
	</div>
	
	
	<div class="col-xs-12 col-sm-6 col-md-4">
		<h3>Arbitrage trading is the only way to make constant profits from
			sports trading. You don't even need to have interest in sports or
			trading to take advantage of an arbitrage trade and make extra money
			online. Arbitrage is a trading technique that uses the difference in
			the price (odds) to make instant profit. It has been used for a long
			time in financial markets, which can be difficult for
			non-professionals to access.</h3>
		
		<a href="<c:url value="/Controller?action=signup"/>"><label class="btn btn-lg btn-block all-buttons" style="height: 75px; border: 1px solid black;">Sign up</label></a>
		
	</div>
	
</div>

<c:import url="footer.jsp" />
