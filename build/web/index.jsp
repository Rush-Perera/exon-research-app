<%-- 
    Document   : index
    Created on : Jul 7, 2023, 10:24:40 PM
    Author     : Sudeera Perera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login and Signup Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js" type="text/javascript"></script>
        <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
        <script src="js/script.js"></script>
        <!-- Google Fonts -->

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans&display=swap" rel="stylesheet">

    </head>

    <body>
        <div class="container">
            <h1>Research Portal</h1>
            <div class="form-container" id="logincontainer">
                <form id="loginForm">
                    <h2>Login</h2>
                    <div class="form-group">
                        <label for="loginEmail">Email</label>
                        <input type="email" class="form-control" id="loginEmail" required>
                    </div>
                    <div class="form-group">
                        <label for="loginPassword">Password</label>
                        <input type="password" class="form-control" id="loginPassword" required>
                    </div>

                    <div class="alert alert-danger mt-3" role="alert" id="signinalert">
                        JS is not Connected
                    </div>

                    <button type="button" class="btn btn-primary" onclick="signinvalidate()">Login</button>

                    <div class="switch-form">
                        <p>Don't have an account? <a href="#" id="switchToSignup">Sign up</a></p>
                    </div>

                </form>
            </div>
            <div class="form-container" id="signupContainer" style="display: none;">
                <form id="signupForm">
                    <h2>Sign Up</h2>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-6">
                                <label for="signupEmail">First Name</label>
                                <input type="text" class="form-control" id="fname" >
                            </div>
                            <div class="col-md-6">
                                <label for="signupEmail">Last Name</label>
                                <input type="text" class="form-control" id="lname" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="signupPassword">NIC</label>
                            <input type="text" class="form-control" id="nic" >
                        </div>
                        <div class="form-group">
                            <label for="signupPassword">Email</label>
                            <input type="email" class="form-control" id="email" >
                        </div>
                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-6">
                                    <label for="country">Country</label>
                                    <select class="form-select" id="country" disabled>
                                        <option value="lk" selected>Sri Lanka</option>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label for="signupEmail">Mobile No.</label>
                                    <input type="text" class="form-control" id="phone" >
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="signupPassword">Password</label>
                            <input type="password" class="form-control" id="password" >
                        </div>
                        <div class="form-group">
                            <label for="signupPassword">Re type password</label>
                            <input type="password" class="form-control" id="repassword" >
                        </div>

                        <button type="button" class="btn btn-primary" onclick="signupvalidate()">Sign Up</button>

                        <div class="alert alert-danger mt-3" role="alert" id="signupalert">
                            JS is not connected
                        </div>

                        <div class="switch-form">
                            <p>Already have an account? <a href="#" id="switchToLogin">Login</a></p>
                        </div>

                </form>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                            
                            
        </script>
    </body>

</html>
