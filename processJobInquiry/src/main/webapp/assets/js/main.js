$(document).ready(function() {

	$('#cV-panels .panel-heading').click(function() {
		
		var nextPanelBody = $(this).next('.panel-body');
		
		$('#cV-panels .panel-heading').removeClass('act');
		$(this).addClass('act');
		nextPanelBody.slideToggle('fast');
		$('#cV-panels .panel-body').not(nextPanelBody).slideUp('fast');
		
	});
	
	$('#current-inquiry .panel-heading').click(function() {
		
		var nextPanelBody = $(this).next('.panel-body');

		$(this).toggleClass('act');
		nextPanelBody.slideToggle('fast');
		
	});

});