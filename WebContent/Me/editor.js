$('[name=account_email_select]').change(function() {
	var value = $(this).val();
	if($(this).val() == "other") {
		$('[name=account_email_text]').removeAttr( "disabled" );
	} else {
		$('[name=account_email_text]').attr("disabled", "disabled");
	}
});
$('[name=account_registration]').click(function() {
	if($('[name=account_email_select]').val() == "other") {
		$('[name=account_email]').val($('[name=account_email_text]').val());
	} else {
		$('[name=account_email]').val($('[name=account_email_select]').val());
	}
});