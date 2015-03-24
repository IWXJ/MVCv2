/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function validateEmail(elem) {
    var x = elem.value;
    if(x.length > 0 && x.indexOf("@") === -1) {
        alert("E-mail is not correctly formatted.");
        return false;
    }
    return true;
}

function validateZipcode(elem) {
    var x = elem.value;
    if(x.length > 0 && isNaN(x)) {
        alert("Zipcode: Only numbers are allowed.");
        return false;
    }

    if(x.length > 0 && (x < 1000 || x > 9990)) {
        alert("Zipcode must be between 1000 and 9990.");
        return false;
    }
    return true;
}

function validatePhone(elem) {
    var x = elem.value;
    if(x.length > 0 && isNaN(x)) {
        alert("Phone: Only numbers are allowed.");
        return false;
    }

    if(x.length > 0 && x < (20000000 || x > 99999999)) {
        alert("Phone number format is wrong.");
        return false;
    }
    return true;
}
