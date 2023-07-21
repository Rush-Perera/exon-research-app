//function submitbtn() {
//    var data = document.getElementById("textinput").value;
//
//    var xhr = new XMLHttpRequest();
//    xhr.open("POST", "./test", true);
//    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//
//    xhr.onreadystatechange = function () {
//        if (xhr.readyState === 4) {
//            if (xhr.status === 200) {
//                var responseData = JSON.parse(xhr.responseText);
//                if (responseData.status === 200) {
//                    alert("Success");
//                } else {
//                    alert("Failed");
//                }
//            } else {
//                alert("Error: " + xhr.status);
//            }
//        }
//    };
//
//    var params = "data=" + encodeURIComponent(data);
//    xhr.send(params);
//
//
//}

function submitbtn() {
    var data = document.getElementById("textinput").value;
    var data1 = document.getElementById("textinput1").value;

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "./test", true);
    xhr.setRequestHeader("Content-Type", "application/json"); // Set content type to JSON

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var responseData = JSON.parse(xhr.responseText);
                if (responseData.status === 200) {
                    alert("Success");
                } else {
                    alert("Failed");
                }
            } else {
                alert("Error: " + xhr.status);
            }
        }
    };

    var jsonData = {
        data: data,
        data1: data1
    };

    var jsonString = JSON.stringify(jsonData); // Convert JS object to JSON string
    xhr.send(jsonString); // Send JSON data as the request payload
}