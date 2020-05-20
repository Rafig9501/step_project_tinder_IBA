function passcheck() {
    var password = document.getElementById("password").value;
    var password2 = document.getElementById("repeatPassword").value;
    if (password === password2) {
        alert("the password is correct");
    } else {
        alert("the password is not the same");
    }
}