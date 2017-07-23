$(document).ready(function() {
	
	$(window).scrollTop(0);
	
	getProcessId();
		
	$('#datetimepicker').datetimepicker({
		viewMode: 'years',
		format: 'YYYY-MM-DD'
	});
	
	$('.submit-button').click(function() {
		submitApplicationForm();
	});

});


function getProcessId() {
	var url_string = window.location;
	var url = new URL(url_string);
	var processId = url.searchParams.get('processId');
	
	if (processId) return processId;
}

function submitApplicationForm() {
	var jsonFormContent = $('#appform').serializeObject();
	var processId = getProcessId();
	var cvJson = {
		"processInstanceId" : processId,
	    cv: jsonFormContent
	};

    console.log(JSON.stringify(cvJson));

    $.ajax({
    	type: "POST",
    	url: "http://localhost:8080/processJobInquiry/receive-cv",
    	data: JSON.stringify(cvJson),
    	success: function(data) {
	    	console.log(data);
	    	alert('Thank you for your application.');
	    	window.location.reload();
    	},
    	error: function (xhr, ajaxOptions, thrownError) {
	        alert(xhr.status);
	        alert(thrownError);
	    },
    	dataType: "json",
    	contentType : "application/json"
    });
}

(function($){
    $.fn.serializeObject = function(){

        var self = this,
            json = {},
            push_counters = {},
            patterns = {
                "validate": /^[a-zA-Z][a-zA-Z0-9_]*(?:\[(?:\d*|[a-zA-Z0-9_]+)\])*$/,
                "key":      /[a-zA-Z0-9_]+|(?=\[\])/g,
                "push":     /^$/,
                "fixed":    /^\d+$/,
                "named":    /^[a-zA-Z0-9_]+$/
            };


        this.build = function(base, key, value){
            base[key] = value;
            return base;
        };

        this.push_counter = function(key){
            if(push_counters[key] === undefined){
                push_counters[key] = 0;
            }
            return push_counters[key]++;
        };

        $.each($(this).serializeArray(), function(){

            // skip invalid keys
            if(!patterns.validate.test(this.name)){
                return;
            }

            var k,
                keys = this.name.match(patterns.key),
                merge = this.value,
                reverse_key = this.name;

            while((k = keys.pop()) !== undefined){

                // adjust reverse_key
                reverse_key = reverse_key.replace(new RegExp("\\[" + k + "\\]$"), '');

                // push
                if(k.match(patterns.push)){
                    merge = self.build([], self.push_counter(reverse_key), merge);
                }

                // fixed
                else if(k.match(patterns.fixed)){
                    merge = self.build([], k, merge);
                }

                // named
                else if(k.match(patterns.named)){
                    merge = self.build({}, k, merge);
                }
            }

            json = $.extend(true, json, merge);
        });

        return json;
    };
})(jQuery);