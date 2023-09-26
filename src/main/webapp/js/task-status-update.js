$(document).ready(function (){
    $(document).ready(function () {
        var id = $(this).attr("statusId")
        var selectedStatusId = id; // Giá trị ban đầu từ JSTL
        $("#statusSelect").change(function () {
            selectedStatusId = $(this).val(); // Lấy giá trị ID của trạng thái mà người dùng chọn
        });

        $(".btn-update-task-status").click(function () {
            // Sử dụng biến selectedStatusId khi người dùng nhấn nút "Lưu lại"
            var statusId = selectedStatusId;
            var taskId = $(this).attr("taskId");

            // alert("Status id: "+statusId+", task id: "+taskId)

            $.ajax({
                method: "POST",
                url: "http://localhost:8081/EmployeeWorkManagement/api/task-status-update",
                data: { statusId: statusId, taskId: taskId }
            }).done(function (data) {
                if (data.success) {
                    // Sửa thành công
                    alert("Cập nhật thành công");
                    window.location.href = "http://localhost:8081/EmployeeWorkManagement/profile";
                } else {
                    // Sửa thất bại
                    alert("Cập nhật thất bại");
                }
                console.log(data);
            });
        });
    });

    // $(".btn-update-task-status").click(function(){
    //     var statusId = $(this).attr("statusId")
    //     var taskId = $(this).attr("taskId")
    //     alert(statusId+", "+taskId)
    //
    //     $.ajax({
    //         method: "POST",
    //         url: "http://localhost:8081/EmployeeWorkManagement/api/task-status-update",
    //         data: { statusId: statusId, taskId: taskId}
    //     }).done(function( data ) {
    //         if(data.success) {
    //             // sửa thành công
    //             alert("Cập nhật thành công")
    //         } else {
    //             // sửa thất bại
    //             alert("Xóa thất bại")
    //         }
    //         console.log(data)
    //         // alert( "Data Saved: " + data );
    //     });
    // })
})