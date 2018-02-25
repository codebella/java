//check if browser supports file api and filereader features
if (window.File && window.FileReader && window.FileList && window.Blob) {

    //To Upload a file using rest service
    function uploadFile() {
        var file = document.getElementById('myFileUploader').files[0];
        var data1 = new FormData();
        data1.append("file", file);

        $.ajax({
            url: "http://localhost:8080/upload",
            type: "POST",
            data: data1,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            success: function (data) {
                console.log("File available at: ", data);
            },
            error: function (data) {
                var obj = data;
                alert(obj.error);
            }
        });
    }
} else {
    alert('The browser does not support File api fully.');
}
