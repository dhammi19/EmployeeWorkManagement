$(document).ready(function (){
    $(".btn-delete-job").click(function() {
        var id = $(this).attr("jobId")
        var This = $(this)

        $.ajax({
            method: "GET",
            url: "http://localhost:8081/EmployeeWorkManagement/api/delete-job?id="+id,
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