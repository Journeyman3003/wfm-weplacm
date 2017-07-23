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

$("form.ajax").on("submit", function(){
	var that = $(this),
		url = that.attr("action"),
		method=that.attr("method"),
		data= {}
	
	that.find("[name]").each(function(index, value){
		var that = $(this),
			name = that.attr("name"),
			value = that.val();
		data[name] = value
	})
	$.ajax({
		url: url,
		type: method,
		data: data,
		success: function(response){
			console.log(response);
		}
	})
	return false;
})