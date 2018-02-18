$('.delete_submit').hide();
$('.update_submit').hide();
$('.edit-column').hide();
$('#edit-mode').click(function() {
	$('.edit-column').animate({
		width : 'toggle'
	}, 'slow');
	$('.delete_submit').animate({
		width : 'toggle'
	}, 'slow');
	$('.update_submit').animate({
		width : 'toggle'
	}, 'slow');
});
