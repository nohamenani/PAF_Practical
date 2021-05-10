$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {

	// Clear status msges---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation----------------
	var status = validateBuyerForm();

	// If not valid-------------------
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid

	$("#formBuyer").submit();

});

// UPDATE==========================================
$(document).on("click", "#btnUpdate", function(event) {
	$("#hidbIdSave").val($(this).closest("tr").find('#hidbIdUpdate').val());
	$("#fname").val($(this).closest("tr").find('td:eq(0)').text());
	$("#lname").val($(this).closest("tr").find('td:eq(1)').text());
	$("#pnumber").val($(this).closest("tr").find('td:eq(2)').text());
	$("#email").val($(this).closest("tr").find('td:eq(3)').text());
	$("#password").val($(this).closest("tr").find('td:eq(4)').text());
});

/*//REMOVE==========================================
$(document).on("click", ".btnRemove", function(event) {
	$(this).closest(".buyer").btnRemove();

	$("#alertSuccess").text("Removed successfully.");
	$("#alertSuccess").show();
});
*/

// CLIENT-MODEL================================================================
function validateBuyerForm() {
	// first name
	if ($("#fname").val().trim() == "") {
		return "Insert First Name.";
	}
	// last name
	if ($("#lname").val().trim() == "") {
		return "Insert Last Name.";
	}
	// phone number-------------------------------
	if ($("#pnumber").val().trim() == "") {
		return "Insert Phone Number.";
	}
	// email
	if ($("#email").val().trim() == "") {
		return "Insert Email address.";
	}
	
	// password
	if ($("#password").val().trim() == "") {
		return "Insert password.";
	}
	
	return true;
}

