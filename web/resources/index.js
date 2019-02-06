function toggle(id) {
    $("#"+id).toggleClass("w3-show");
}

$(".File-Show").val(null);
$(document).ready(function(){

	$(".upload").change(function(){
		$(".File-Show").val(this.value);
	});

	$(".tablink").click(function() {

		$(".tab").each(function(){
			$(this).css("display", "none");
		});

		$(".tablink").each(function(){
			$(this).removeClass("w3-border-green");
		});

		$(this).addClass("w3-border-green");
		$($(this).attr("href")).css("display", "block");
	});

});