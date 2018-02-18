$('.bookmark-category_header').next().hide();
$('.bookmark-category_header').click(function() {
	$(this).next().slideToggle();
});