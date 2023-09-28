$(document).ready(function () {
    $(".btn-add-user").click(function () {
        var userName = $("input[name='userName']").val();
        var email = $("input[name='example-email']").val();
        var password = $("input[name='password']").val();
        var roleId = $("#roleIdSelect").val(); // Lấy giá trị roleId từ select

        $.ajax({
            method: "POST",
            url: "http://localhost:8081/EmployeeWorkManagement/api/add-user",
            data: { email: email, password: password, fullName: userName, roleId: roleId }
        }).done(function (data) {
            if (data.success) {
                // Thêm thành công
                alert("Thêm thành công");
                window.location.href = "http://localhost:8081/EmployeeWorkManagement/user-table";
            } else {
                // Thêm thất bại
                alert("Thêm thất bại");
            }
            console.log(data);
        });
    })
})

// $(document).ready(function (){
//     var userName = $("input[name='userName']").val();
//     var email = $("input[name='example-email']").val();
//     var password = $("input[name='password']").val();
//     //var roleId = $("select[name='role']").val();
//     var roleId = $(this).attr("roleId")
//
//     $("#roleIdSelect").change(function () {
//         roleId = $(this).val(); // Lấy giá trị ID của trạng thái mà người dùng chọn
//     });
//     alert("userName: "+userName+", email: "+email+", password: "+password+", roleId: "+roleId)
//     $(".btn-add-user").click(function(){
//         $.ajax({
//             method: "POST",
//             url: "http://localhost:8081/EmployeeWorkManagement/api/add-user",
//             data: { email: email, password: password, fullName: userName, roleId: roleId }
//         }).done(function (data) {
//             if (data.success) {
//                 // thêm thành công
//                 alert("Thêm thành công");
//                 window.location.href = "http://localhost:8081/EmployeeWorkManagement/user-table";
//             } else {
//                 // thêm thất bại
//                 alert("Thêm thất bại");
//             }
//             console.log(data);
//         });
//     })
// })