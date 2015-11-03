$("input").each(function(index) {
	if (this.type=="text"){
		$(this).addClass("form-control");
		
		
	}
});

$("select").addClass("form-control");

$('#submit-btn').on('click', function() {
	var $btn = $(this).button('loading')
})


