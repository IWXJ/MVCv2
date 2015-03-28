/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function validatePhone(phoneInput){  
    var pattern = "(?=[0-9]+$)(?=\\S+$).{8,8}";
    if (phoneInput.value.search(pattern) === -1) {
        window.alert("\"" + phoneInput.value + "\" isn't a valid phone number!");
        return false;
    }
    return true;
}  
function validateZipcode(zipcodeInput){  
    var pattern = "(?=[0-9]+$)(?=\\S+$).{4,4}";
    if (zipcodeInput.value.search(pattern) === -1) {
        window.alert("\"" + zipcodeInput.value + "\" isn't a valid zipcode!");
        return false;
    }
    return true;
}  
function validateEmail(emailInput){  
    var pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_])(?=\\S+$).{8,12}";
    if (emailInput.value.search(pattern) === -1) {
        window.alert("\"" + emailInput.value + " isn't a valid e-mail address!");
        return false;
    }
    return true;
}  
