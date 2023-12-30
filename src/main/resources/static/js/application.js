// const showPosition = position => {
//     console.log("long"+ position.coords.longitude)
//     console.log("latitude"+ position.coords.latitude)
//     displayLocation(position.coords.longitude, position.coords.latitude)
// }

// const displayLocation = (latitude,longitude) => {
//     var request = new XMLHttpRequest();

//     var method = 'GET';
//     var url = 'http://maps.googleapis.com/maps/api/geocode/json?latlng='+latitude+','+longitude+'&sensor=true';
//     var async = true;

//     request.open(method, url, async);
//     request.onreadystatechange = function(){
//       if(request.readyState == 4 && request.status == 200){
//         var data = JSON.parse(request.responseText);
//         var address = data.results[0];
//         document.write(address.formatted_address);
//       }
//     };
//     request.send();
//   };

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