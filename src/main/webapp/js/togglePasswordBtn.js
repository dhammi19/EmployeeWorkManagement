$(document).ready(function () {
    $("#togglePasswordBtn").click(function () {
        var passwordField = $("#passwordField");
        var passwordFieldType = passwordField.attr("type");

        if (passwordFieldType === "password") {
            passwordField.attr("type", "text");
            $(this).text("Ẩn Mật Khẩu");
        } else {
            passwordField.attr("type", "password");
            $(this).text("Hiện Mật Khẩu");
        }
    });
});