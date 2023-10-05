$(document).ready(function (){


    $(".btn-delete").click(function(){
        var id = $(this).attr("taskId")
        var This = $(this)

        $.ajax({
            method: "GET",
            url: "http://localhost:8081/EmployeeWorkManagement/api/delete-task?id=" +id,
        }).done(function( data ) {
            if(data.success) {
                // xóa thành công
                This.closest("tr").remove()
            } else {
                // xóa thất bại
                alert("Xóa thất bại")
            }
            console.log(data)
            // alert( "Data Saved: " + data );
        });
    })
})