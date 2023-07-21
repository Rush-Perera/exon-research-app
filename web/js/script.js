/* global keywords */


document.addEventListener('DOMContentLoaded', function () {


    const switchToSignup = document.querySelector('#switchToSignup');
    const switchToLogin = document.querySelector('#switchToLogin');
    const logincontainer = document.querySelector('#logincontainer');
    const signupContainer = document.querySelector('#signupContainer');
    const signinalert = document.querySelector('#signinalert');
    const signupalert = document.querySelector('#signupalert');
    signinalert.style.display = 'none';
    signupalert.style.display = 'none';
    switchToSignup.addEventListener('click', function (event) {
        event.preventDefault();
        logincontainer.style.display = 'none';
        signupContainer.style.display = 'block';
    });

    switchToLogin.addEventListener('click', function (event) {
        event.preventDefault();
        logincontainer.style.display = 'block';
        signupContainer.style.display = 'none';
    });


});

function validate_nic(nic) {
    var cnic_no_regex = new RegExp('^[0-9+]{9}[vV|xX]$');
    var new_cnic_no_regex = new RegExp('^[0-9+]{12}$');
    if (nic.length == 10) {
        return true;
    } else if (nic.length == 12 && new_cnic_no_regex.test(nic)) {
        return true;
    } else {
        return false;
    }

}

function validate_phone(phone) {
    var phone_no_regex = new RegExp('^[0-9]+$');
    if (phone.length == 10 && phone_no_regex.test(phone)) {
        return true;
    } else {
        return false;
    }

}

function validateEmail(email) {
    var re = /\S+@\S+\.\S+/;
    if (re.test(email)) {
        return true;
    } else {
        return false;
    }
}

function validatePassword(password) {
    const minLength = 8;
    const uppercaseRegex = /[A-Z]/;
    const lowercaseRegex = /[a-z]/;
    const digitRegex = /[0-9]/;

    let status = true;
    // Check length
    if ((password.length < minLength) || (!uppercaseRegex.test(password)) || (!lowercaseRegex.test(password)) || (!digitRegex.test(password))) {
        status = false;
    }

    return status;


}

function signupvalidate() {
    var nic = document.getElementById("nic").value;
    var fname = document.getElementById("fname").value;
    var lname = document.getElementById("lname").value;
    var phone = document.getElementById("phone").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var repassword = document.getElementById("repassword").value;


    // const signinalert = document.querySelector('#signinalert');
    var signupalert = document.querySelector('#signupalert');
    if (fname === "") {
        signupalert.style.display = 'block';
        signupalert.innerHTML = 'Enter First Name';
    } else if (lname === "") {
        signupalert.style.display = 'block';
        signupalert.innerHTML = 'Enter Last Name';
    } else if (nic === "") {
        signupalert.style.display = 'block';
        signupalert.innerHTML = 'Enter NIC';
    } else if (!validate_nic(nic)) {
        signupalert.style.display = 'block';
        signupalert.innerHTML = 'Invalid NIC';
    } else if (email === "") {
        signupalert.style.display = 'block';
        signupalert.innerHTML = 'Enter Email';
    } else if (!validateEmail(email)) {
        signupalert.style.display = 'block';
        signupalert.innerHTML = 'Invalid Email';
    } else if (phone === "") {
        signupalert.style.display = 'block';
        signupalert.innerHTML = 'Enter Phone';
    } else if (!validate_phone(phone)) {
        signupalert.style.display = 'block';
        signupalert.innerHTML = 'Invalid Phone';
    } else if (password === "") {
        signupalert.style.display = 'block';
        signupalert.innerHTML = 'Enter Password';
    } else if (repassword === "") {
        signupalert.style.display = 'block';
        signupalert.innerHTML = 'Enter Confirm Password';
    } else if (password !== repassword) {
        signupalert.style.display = 'block';
        signupalert.innerHTML = 'Password and Confirm Password are not same';
    } else {
        signupalert.style.display = 'block';
        signupalert.className = 'alert alert-success mt-3';
        signupalert.innerHTML = 'Signing Up';

        // Send data to server
        var data = {
            firstName: fname,
            lastName: lname,
            phone: phone,
            email: email,
            nic: nic,
            password: password
        };

        fetch('./Register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(function (responseData) {
            if (responseData.status === 200) {
                alert("Signup Successful");
                window.location = "index.jsp";
            } else {
                responseData.json().then(function (data) {
                    alert("An Error occurred: " + data.status + " " + data.message);
                });
            }
        }).catch(function (error) {
            console.error('Error:', error);
        });





//        $.post("./Register", {
//            firstName: fname,
//            lastName: lname,
//            phone: phone,
//            email: email,
//            nic: nic,
//            password: password
//        }, function (response) {
//            console.log(response);
//        });
//        $.post({
//            url: "./Register",
//            data: JSON.stringify(data),
//            contentType: "application/json",
//            dataType: "json"
//        })
//                .then(function (responseData) {
//                    // Handle the response data here
//                    console.log(responseData);
//                    // Example: if the server responds with JSON
//                    // you can access properties using responseData.propertyName
//                    alert(responseData.message);
//                })
//                .catch(function (error) {
//                    // Handle errors here
//                    console.error("Ajax error:", error);
//                    alert("Ajax error:", error);
//                });




    }
}

function signinvalidate() {

    const signinalert = document.querySelector('#signinalert');

    var email = document.getElementById("loginEmail").value;
    var password = document.getElementById("loginPassword").value;

    if (!validateEmail(email)) {
        signinalert.style.display = 'block';
        signinalert.innerHTML = 'Invalid Email';
    } else if (password === "") {
        signinalert.style.display = 'block';
        signinalert.innerHTML = 'Enter Password';
    } else {


        var data = {
            email: email,
            password: password
        };


        fetch('./SignIn', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(function (responseData) {
            // Check if the response was successful
            if (!responseData.ok) {
                throw new Error("Network response was not ok");
            }
            // Parse the response data as JSON
            return responseData.json();
        }).then(function (data) {
            // Now 'data' contains the parsed JSON data
            if (data.status === 200) {
                alert("Sign in Successful");
                window.location = "addResearch.jsp";
            } else {
                alert("An Error occurred: " + data.status + " " + data.message);
            }
        }).catch(function (error) {
            console.error('Error:', error);
        });





        signinalert.style.display = 'block';
        signinalert.className = 'alert alert-success mt-3';
        signinalert.style = 'align-items: end; display: flex; justify-content: space-between';
        signinalert.innerHTML = 'Signing in';

        const spinner = document.createElement('div');
        spinner.className = 'spinner-border text-success';
        spinner.setAttribute('role', 'status');

        const text = document.createElement('span');
        text.className = 'visually-hidden';
        text.textContent = 'Loading...';

        spinner.appendChild(text);

        signinalert.appendChild(spinner);
    }


}



function detailsContBtn() {
    const area = document.getElementById("researchArea").value;
    const topic = document.getElementById("topic").value;

    if (area === "0" || topic === "") {
        alert("Enter Details to continue.");
    } else {

        const author_section = document.querySelector('#author_section');
        const details_section = document.querySelector('#details_section');
        const step1 = document.querySelector('#step1');
        step1.className = 'step completed';

        details_section.style.display = 'none';
        author_section.style.display = 'block';
    }
}


function authorSubmit() {
    var anic = document.getElementById('anic').value;
    var afname = document.getElementById('afname').value;
    var alname = document.getElementById('alname').value;
    var ainstitute = document.getElementById('ainstitute').value;
    var isMain = document.getElementById('isMain');
    const table = document.getElementById('atable');

    if (anic === '' || afname === '' || alname === '' || ainstitute === '') {
        alert('Enter all the details.');
    } else if (!validate_nic(anic)) {
        alert("Invalid NIC");
    } else {

        const lastRow = table.rows[table.rows.length - 1];
        const lastNic = lastRow.cells[0].textContent;

        if (lastNic === anic) {
            alert("Author added already.")
        } else {
            table.style.display = '';
            const newRow = table.insertRow(-1);
            const nic = newRow.insertCell(0);
            const fname = newRow.insertCell(1);
            const lname = newRow.insertCell(2);
            const institute = newRow.insertCell(3);
            const ismain = newRow.insertCell(4);
            nic.innerHTML = anic;
            fname.innerHTML = afname;
            lname.innerHTML = alname;
            institute.innerHTML = ainstitute;
            if (isMain.checked) {
                ismain.innerHTML = "Main Author";
            } else {
                ismain.innerHTML = "Sub Author";
            }

        }

    }

}

function authorContBtn() {

    const table = document.getElementById('atable');
    const rowCount = table.rows.length;

    if (rowCount === 1) {
        alert("Enter at least one author to continue");
    } else {

        //add data



        const author_section = document.querySelector('#author_section');
        const abstract_section = document.querySelector('#abstract_section');
        const step2 = document.querySelector('#step2');
        step2.className = 'step completed';

        abstract_section.style.display = 'block';
        author_section.style.display = 'none';
    }

}

function authorBackBtn() {
    const author_section = document.querySelector('#author_section');
    const details_section = document.querySelector('#details_section');
    const step1 = document.querySelector('#step1');

    step1.className = 'step';

    details_section.style.display = 'block';
    author_section.style.display = 'none';
}

function abstractContBtn() {

    const abstract = document.getElementById('abstract').value;

    if (abstract === '') {
        alert("Enter research abstract to continue");
    } else {

        // add abstract

        const abstract_section = document.querySelector('#abstract_section');
        const document_section = document.querySelector('#document_section');
        const step3 = document.querySelector('#step3');
        const step2 = document.querySelector('#step2');
        step3.className = 'step completed';

        document_section.style.display = 'block';
        abstract_section.style.display = 'none';
    }


}

function abstractBackBtn() {
    const abstract_section = document.querySelector('#abstract_section');
    const author_section = document.querySelector('#author_section');
    const step2 = document.querySelector('#step2');
    const step1 = document.querySelector('#step1');
    step2.className = 'step';
    step1.className = 'step completed';

    abstract_section.style.display = 'none';
    author_section.style.display = 'block';
}

function documentContBtn() {

    const fileInput = document.getElementById("file");
    const file = fileInput.files[0];
    const mimeType = file.type;
    const requiredMimeType = "application/pdf";

    if (file === undefined) {
        // No file was uploaded
        alert("Please select a file to upload.");
    } else if (mimeType !== requiredMimeType) {
        // The file is not of the required type
        alert("The file you uploaded is not a pdf.");
    } else {
        // The file is of the required type
        const document_section = document.querySelector('#document_section');
        const photograph_section = document.querySelector('#photograph_section');
        const step4 = document.querySelector('#step4');

        step4.className = 'step completed';

        document_section.style.display = 'none';
        photograph_section.style.display = 'block';
    }


}

function documentBackBtn() {
    const document_section = document.querySelector('#document_section');
    const abstract_section = document.querySelector('#abstract_section');
    const step3 = document.querySelector('#step3');
    const step2 = document.querySelector('#step2');
    step3.className = 'step';
    step2.className = 'step completed';

    document_section.style.display = 'none';
    abstract_section.style.display = 'block';
}

//var filePreview = document.getElementById("filePreview");
//const fileInput = document.getElementById("file");
//const file = fileInput.files[0];
//
//fileInput.addEventListener("change", function () {
//    var file = fileInput.files[0];
//    var reader = new FileReader();
//
//    reader.onload = function () {
//        filePreview.innerHTML = "<img src=\"" + reader.result + "\" />";
//    };
//
//    reader.readAsDataURL(file);
//});

function photoBackBtn() {
    const photograph_section = document.querySelector('#photograph_section');
    const document_section = document.querySelector('#document_section');
    const step4 = document.querySelector('#step4');
    const step3 = document.querySelector('#step3');
    step4.className = 'step';
    step3.className = 'step completed';

    photograph_section.style.display = 'none';
    document_section.style.display = 'block';
}

function photoContBtn() {

    const fileInputPhoto = document.getElementById("filePhoto");
    const filePhoto = fileInputPhoto.files[0];
    const mimeType = filePhoto.type;
    const requiredMimeTypes = ["image/jpeg", "image/png", "image/jpg"];

    if (filePhoto === undefined) {
        // No file was uploaded
        alert("Please select a photo to upload.");
    }
//    else if (mimeType !== requiredMimeTypes) {
//        // The file is not of the required type
//        alert("The file you uploaded is not a png/jpeg/jpg");
//    } 
    else {
        // The file is of the required type



        const photograph_section = document.querySelector('#photograph_section');
        const keywords_section = document.querySelector('#keywords_section');
        const step5 = document.querySelector('#step5');
        const step4 = document.querySelector('#step4');
        step5.className = 'step completed';
        step4.className = 'step completed';

        photograph_section.style.display = 'none';
        keywords_section.style.display = 'block';
    }


}

function keywordsBackBtn() {
    const keywords_section = document.querySelector('#keywords_section');
    const photograph_section = document.querySelector('#photograph_section');
    const step5 = document.querySelector('#step5');
    const step4 = document.querySelector('#step4');
    step5.className = 'step';
    step4.className = 'step completed';

    keywords_section.style.display = 'none';
    photograph_section.style.display = 'block';
}

keywords = [];

function addKeyword() {
    const keyword = document.getElementById('keyword').value;
    const keywordsDiv = document.getElementById('keywordsDiv');

    const newButton = document.createElement('button');
    newButton.setAttribute('type', 'button');
    newButton.setAttribute('class', 'btn btn-outline-success');
    newButton.textContent = keyword;

    keywordsDiv.appendChild(newButton);

    keywords.push(keyword);

    document.getElementById('keyword').value = '';
}

function submitResearch() {
    
//    alert("Button clicked");

    const area = document.getElementById("researchArea").value;
    const topic = document.getElementById("topic").value;

    const table = document.getElementById('atable'); // get data from table

    const abstract = document.getElementById('abstract').value;

    const fileInput = document.getElementById("file");
    const file = fileInput.files[0];

    const fileInputPhoto = document.getElementById("filePhoto");
    const filePhoto = fileInputPhoto.files[0];

    //  keywords[]

    const agree = document.getElementById('agree');
    if (!agree.checked) {
        alert('Agree to terms before submitting the research.');
    } else if (keywords.length === 0) {


        alert('Enter atleast one keyword to continue');



    } else {
        const table = document.getElementById('atable');
        const dataArray = []; // Array to store the table data

// Iterate over the rows of the table starting from the second row
        for (let i = 1; i < table.rows.length; i++) {
            const row = table.rows[i];
            const rowData = []; // Array to store data for each row

            // Iterate over the cells of the row
            for (let j = 0; j < row.cells.length; j++) {
                const cell = row.cells[j];
                rowData.push(cell.textContent.trim()); // Add cell data to the row data array
            }

            dataArray.push(rowData); // Add the row data array to the main data array
        }

        console.log(dataArray); // Output the table data array
        
        const data = {
            area: area,
            topic: topic,
            abstract: abstract,
            keywords: keywords,
            authors: dataArray
        }
        
        const formData = new FormData();
        formData.append('file', file);
        formData.append('filePhoto', filePhoto);
        formData.append('data', JSON.stringify(data));
     
        console.log(formData);

        fetch('./NewResearch', {
            method: 'POST',
            body: formData
        }).then(function (responseData) {
            // Check if the response was successful
            if (!responseData.ok) {
                throw new Error("Network response was not ok");
            }
            // Parse the response data as JSON
            return responseData.json();
        }).then(function (data) {
            // Now 'data' contains the parsed JSON data
            if (data.status === 200) {
                alert(data.message);
//                window.location = "addResearch.jsp";
            } else {
                alert("An Error occurred: " + data.status + " " + data.message);
            }
        }).catch(function (error) {
            console.error('Error:', error);
        });
        
    }
}
