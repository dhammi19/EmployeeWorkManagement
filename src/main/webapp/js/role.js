// $ đại diện cho thư viện jquery
$(document).ready(function (){ // khi page load xong thì hàm này sẽ thực hiện
    // $.ajax({
    //     method: "GET",
    //     url: "http://localhost:8081/EmployeeWorkManagement/api/role?id=7",
    //     // data: { name: "John", location: "Boston" } => Gửi tham số dạng post
    // }).done(function( data ) {
    //     alert( "Data Saved: " + data );
    // });

    $(".btn-delete").click(function(){ // hàm này sẽ chạy khi ta click vào 1 button có tên là btn-delete
        var id = $(this).attr("roleId")
        var This = $(this)
        // alert("OK Delete"+id)

        $.ajax({
            method: "GET",
            url: "http://localhost:8081/EmployeeWorkManagement/api/role?id=" +id,
            // data: { name: "John", location: "Boston" } => Gửi tham số dạng post
            // method: "POST",
            // url: "http://localhost:8081/EmployeeWorkManagement/api/role",
            // data: { id: "id" }
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