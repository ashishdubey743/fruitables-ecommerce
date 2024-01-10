
const showPosition = position => {
    getZipCode(position.coords.latitude, position.coords.longitude);
};

function getZipCode(latitude, longitude) {
    var request = new XMLHttpRequest();
    var method = 'GET';
    var url = 'https://nominatim.openstreetmap.org/reverse?format=json&lat=' + latitude + '&lon=' + longitude;

    request.open(method, url, true);
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            var data = JSON.parse(request.responseText);
            if (data.address && data.address.postcode) {
                document.querySelector(".topbar a").innerHTML = data.address.suburb + ", " + data.address.city
            } else {
                console.error('Unable to retrieve ZIP Code.');
            }
        }
    };
    request.send();
}
navigator.geolocation ? navigator.geolocation.getCurrentPosition(showPosition) : ""