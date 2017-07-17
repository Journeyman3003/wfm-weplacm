$(document).ready(function() {

	$('#cV-panels .panel-heading').click(function() {
		
		var nextPanelBody = $(this).next('.panel-body');
		
		$('#cV-panels .panel-heading').removeClass('act');
		$(this).addClass('act');
		nextPanelBody.slideToggle(300);
		$('#cV-panels .panel-body').not(nextPanelBody).slideUp(300);
		
	});
	
	$('#current-inquiry .panel-heading').click(function() {
		
		var nextPanelBody = $(this).next('.panel-body');

		$(this).toggleClass('act');
		nextPanelBody.slideToggle(300);
		
	});

});