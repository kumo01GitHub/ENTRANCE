$('.category-header').next().hide();
$('.category-header').click(function() {
	$(this).next().slideToggle();
});