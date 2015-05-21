var $border_color = "#efefef";
var $grid_color = "#ddd";
var $default_black = "#666";
var $red = "#eb4343";
var $blue = "#5da4cd";
var $green = "#abd14f";
var $yellow = "#ffaa3a";
var $yellow_one = "#FFF844";
var $grey = "#999999";
var $blue_one = "#74b1d4";
var $blue_two = "#84bad9";
var $blue_three = "#9bc7e0";
var $blue_four = "#afd2e6";
var $blue_five = "#badff2";


//Data Tables
$(document).ready(function () {
	// Modal Window
	$('.modal-large').modal('show');

	// Tooltips
	$('a').tooltip('hide')

	// Dropdown
	$('.dropdown-toggle').dropdown();
	
	// Popovers
	$('.btn').popover('hide');

	// Loading Stage Button
	$('#loading-btn')
	.click(function () {
		var btn = $(this)
		btn.button('loading')
		setTimeout(function () {
			btn.button('reset');
		}, 3000);
	});

	$('#myTab a').click(function (e) {
		e.preventDefault()
		$(this).tab('show')
	})

	$('#myCollapsible').collapse({
  	toggle: false
	});
		
});

// Sliders

$('#sl1').slider({
  formater: function(value) {
    return 'Current value: '+value;
  }
});

$('#sl3').slider({
  formater: function(value) {
    return 'Current value: '+value;
  }
});

$('#sl4').slider({
  formater: function(value) {
    return 'Current value: '+value;
  }
});

$('#sl5').slider({
  formater: function(value) {
    return 'Current value: '+value;
  }
});

$('#sl2').slider();

