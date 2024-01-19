
/**
 * Show Location on Home Page
 */
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
                if(document.querySelector(".topbar a")) document.querySelector(".topbar a").innerHTML = data.address.suburb + ", " + data.address.city;
            } else {
                console.error('Unable to retrieve ZIP Code.');
            }
        }
    };
    request.send();
}

if (window.location.pathname == "/") {
    navigator.geolocation ? navigator.geolocation.getCurrentPosition(showPosition) : '';
}

/**
 * Modify status of Category
 */
const modifyStatus = (currentStatus, element, categoryId) => {
    fetch('/update_category_status',{
        method : 'POST',
        body : JSON.stringify({"status":currentStatus, "id":categoryId}),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then((response)=>{
        return response.json();
    }).then((response)=>{
        console.log(response);
        if(response.category.status == "0"){
            element.innerHTML = "Enable";
            element.setAttribute('onclick', 'modifyStatus(\'1\', this, this.getAttribute(`id`))');
            element.classList.remove("btn-secondary");
            element.classList.add("btn-success");
            
        }else{
            element.innerHTML = "Disable";
            element.setAttribute('onclick', 'modifyStatus(\'0\', this, this.getAttribute(`id`))');
            element.classList.remove("btn-success");
            element.classList.add("btn-secondary");
        }
    })
}

/**
 * Delete Category
 */

const deleteCategory = (element, categoryId) => {
    fetch('/delete_category',{
        method : 'POST',
        body : JSON.stringify({"id":categoryId}),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then((response)=>{
        return response.json();
    }).then((response)=>{
        console.log(response);
        if(response.status == 200){
           element.closest("li").remove();
        }
    })
}

const deleteProduct = (element, productId) => {
    fetch('/delete_product',{
        method : 'POST',
        body : JSON.stringify({"id":productId}),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then((response)=>{
        return response.json();
    }).then((response)=>{
        console.log(response);
        if(response.status == 200){
           element.closest("tr").remove();
        }
    })
}