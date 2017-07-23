$(document).ready(function() {
	
	getProcessId();
	
	$('.submit-button').click(function() {
		submitApplicationForm();
	});

	/*$("#appform.ajax").on("submit", function(){
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
	});*/
	

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