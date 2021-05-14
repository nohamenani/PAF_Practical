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
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid
	

	var type = ($("#hididSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "BuyersAPI",
		type : type,
		data : $("#formBuyer").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onBuyerSaveComplete(response.responseText, status);
		}
	});

});

function onBuyerSaveComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully saved.");
			
			$("#alertSuccess").show();
			
			$("#divBuyersGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error") {
		
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
		
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hididSave").val("");
	$("#formBuyer")[0].reset();
}


// UPDATE==========================================

$(document).on("click", ".btnUpdate", function(event) {
	
	$("#hididSave").val($(this).data("bId"));
	
	$("#fname").val($(this).closest("tr").find('td:eq(0)').text());
	$("#lname").val($(this).closest("tr").find('td:eq(1)').text());
	$("#pnumber").val($(this).closest("tr").find('td:eq(2)').text());
	$("#email").val($(this).closest("tr").find('td:eq(3)').text());
	$("#password").val($(this).closest("tr").find('td:eq(4)').text());
});

/*
 * //REMOVE========================================== $(document).on("click",
 * ".btnRemove", function(event) { $(this).closest(".buyer").btnRemove();
 * 
 * $("#alertSuccess").text("Removed successfully."); $("#alertSuccess").show();
 * });
 */



$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "BuyersAPI",
		type : "DELETE",
		data : "bId=" + $(this).data("bId"),
		dataType : "text",
		complete : function(response, status) {
			onBuyerDeleteComplete(response.responseText, status);
		}
	});
});


function onBuyerDeleteComplete(response, status)
{
	
	if (status == "success") 
	{
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			
			$("#alertSuccess").text("Successfully deleted the buyer details.");
			
			$("#alertSuccess").show();
			
			$("#divBuyersGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") 
		{
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error") {
		
		$("#alertError").text("Error while deleting buyer details.");
		$("#alertError").show();
		
	} else 
	{
		
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// CLIENT-MODEL================================================================

function validateBuyerForm() {
	
	if ($("#fname").val().trim() == "") {  // first name
		return "Insert First Name.";
	}
	
	
	if ($("#lname").val().trim() == "") {	// last name
		return "Insert Last Name.";
	}

	
	if ($("#pnumber").val().trim() == "") {	// phone number
		return "Insert Phone Number.";
	}
	
	
	if ($("#email").val().trim() == "") {	// email
		return "Insert Email address.";
	}

	
	if ($("#password").val().trim() == "") {	// password
		return "Insert password.";
	}

	return true;
}
