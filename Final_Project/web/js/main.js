function checkUser(val) {
    if (val != '') {
        document.getElementById('user-danger').style.display = "none";
    } else {
        document.getElementById('user-danger').style.display = "inline";
    }
}

function checkPassword(val) {
    if (val != '') {
        document.getElementById('password-danger').style.display = "none";
    } else {
        document.getElementById('password-danger').style.display = "inline";
    }
}

function checkCode(val){
    var authcode = document.getElementById('authcode').value;
    if(val !== authcode){
        document.getElementById('password-danger').style.display = "inline";
        document.getElementById('submit-btn').classList.add("btn-disabled");
    } else {
        document.getElementById('password-danger').style.display = "none";
        document.getElementById('submit-btn').classList.remove("btn-disabled");
    }
}


