var geocoder;
var map;
var marker;

function initialize() {
	geocoder = new google.maps.Geocoder();
	var mapOptions = {
		center : new google.maps.LatLng(51.1078852, 17.03853760000004),
		zoom : 5,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById("googleMap"), mapOptions);
	marker = new google.maps.Marker({
		draggable : true,
		map : map,
		position : new google.maps.LatLng(51.1078852, 17.03853760000004)
	});

	google.maps.event.addListener(marker, 'dragend', function() {
		document.getElementById("latId").value = marker.getPosition().lat();
		document.getElementById("lngId").value = marker.getPosition().lng();
	});
}

google.maps.event.addDomListener(window, 'load', initialize);

function codeAddress() {
	var address = document.getElementById("addressId").value + ", "
			+ document.getElementById("cityId").value;
	// alert("Address: " + address);
	geocoder
			.geocode(
					{
						'address' : address
					},
					function(results, status) {
						if (status == google.maps.GeocoderStatus.OK) {
							map.setCenter(results[0].geometry.location);
							marker.setMap(map);
							marker.setPosition(results[0].geometry.location);
							map.setZoom(15);
							document.getElementById("latId").value = results[0].geometry.location
									.lat();
							document.getElementById("lngId").value = results[0].geometry.location
									.lng();
						} else if (status == google.maps.GeocoderStatus.ZERO_RESULTS) {
							alert("No results");
						} else if (status == google.maps.GeocoderStatus.OVER_QUERY_LIMIT) {
							alert("Usage limit");
						} else if (status == google.maps.GeocoderStatus.REQUEST_DENIED) {
							alert("Request denied");
						} else if (status == google.maps.GeocoderStatus.INVALID_REQUEST) {
							alert("Invalid address");
						}
					});
}
