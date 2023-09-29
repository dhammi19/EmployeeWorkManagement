$(document).ready(function (){
    $(".btn-delete-user").click(function(){
        var id = $(this).attr("userId")
        var This = $(this)

        $.ajax({
            method: "GET",
            url: "http://localhost:8081/EmployeeWorkManagement/api/delete-user?id="+id,
            //data: {id: id}
        }).done(function( data ) {
            if(data.success) {
                // xóa thành công
                alert("Xóa thành công")
                This.closest("tr").remove()
            } else {
                // xóa thất bại
                alert("Xóa thất bại")
            }
        })
    })
})