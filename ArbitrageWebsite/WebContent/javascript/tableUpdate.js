$(document).ready(function() {

	setInterval(function() {

		$.get("getResults.jsp", function(result) {

			$("#output").html(result);

		});

	}, 5000);
	
});