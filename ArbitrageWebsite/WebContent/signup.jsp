<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<c:import url="header.jsp">
	<c:param name="title" value="Sign Up"></c:param>
</c:import>

<form class="form-signin" action="/ArbitrageWebsite/Controller" method="post">
	<input type="hidden" name="action" value="createaccount" />
	
	<h2 class="form-signin-heading">Sign up</h2>
	
	<label for="inputEmail" class="sr-only">Email address</label>
	<input name="email" type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
	
	<label for="inputPassword" class="sr-only">Password</label>
	<input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
	
	<label for="confirmPassword" class="sr-only">Password</label>
	<input name="confirmPassword" type="password" id="inputPassword" class="form-control" placeholder="Confirm Password" required>
	
	<button class="btn btn-lg btn-block all-buttons" type="submit">Sign up</button>
	
	<label class="form-error"><%= request.getAttribute("creationMessage") %></label>
</form>
			
<c:import url="footer.jsp" />
		