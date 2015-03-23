/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function validateEmail() {
    var x = document.getElementById('email').value;
    if(x.indexOf("@") > -1) {
        alert("E-mail is not correctly formatted.");
        return false;
    }
    return true;
}

function validateZipcode() {
    var x = document.getElementById('zipcode').value;
    if(isNaN(x)) {
        alert("Only numbers are allowed.");
        return false;
    }

    if(x < 1000 || x > 9990) {
        alert("Zipcode must be between 1000 and 9990.");
        return false;
    }
    return true;
}

function validatePhone() {
    var x = document.getElementById('phone').value;
    if(isNaN(x)) {
        alert("Only numbers are allowed.");
        return false;
    }

    if(x < 20000000 || x > 99999999) {
        alert("Phone number format is wrong.");
        return false;
    }
    return true;
}