$(document).ready(function () {
    $("#example-email").blur(function () {
        var email = $(this).val();
        $.ajax({
            method: "GET",
            url: "http://localhost:8081/EmployeeWorkManagement/api/check-duplicate-email",
            data: { email: email }
        }).done(function (data) {
            if (!data.success) {
                // Email bị trùng lặp, hiển thị thông báo lỗi
                alert(data.description);
                // Xoá giá trị email để người dùng nhập lại
                $("#example-email").val("");
            }
        });
    });
});