<%-- 
    Document   : addResearch
    Created on : Jul 12, 2023, 1:58:01 PM
    Author     : Sudeera Perera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Research</title>
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

        <!-- Google Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans&display=swap" rel="stylesheet">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/script.js"></script>

    </head>

    <body>
        <div class="container-lg">
            <nav class="navbar bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand">Research Portal</a>
                    <form class="d-flex" role="search">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </nav>
        </div>
        <div class="container">
            <div class="container padding-bottom-3x mb-1">
                <div class="card mb-3">
                    <div class="p-4 text-center text-white text-lg bg-dark rounded-top"><span class="text-uppercase">Upload
                            New Research</span></div>

                    <div class="card-body">
                        <div
                            class="steps d-flex flex-wrap flex-sm-nowrap justify-content-between padding-top-2x padding-bottom-1x">
                            <div class="step completed" id="step0">
                                <div class="step-icon-wrap">
                                    <div class="step-icon"><i class="pe-7s-note"></i></div>
                                </div>
                                <h4 class="step-title">Research Details</h4>
                            </div>
                            <div class="step" id="step1">
                                <div class="step-icon-wrap">
                                    <div class="step-icon"><i class="pe-7s-add-user"></i></div>
                                </div>
                                <h4 class="step-title">Author Details</h4>
                            </div>
                            <div class="step" id="step2">
                                <div class="step-icon-wrap">
                                    <div class="step-icon"><i class="pe-7s-medal"></i></div>
                                </div>
                                <h4 class="step-title">Abstract</h4>
                            </div>
                            <div class="step" id="step3">
                                <div class="step-icon-wrap">
                                    <div class="step-icon"><i class="pe-7s-notebook"></i></div>
                                </div>
                                <h4 class="step-title">Research Document</h4>
                            </div>
                            <div class="step" id="step4">
                                <div class="step-icon-wrap">
                                    <div class="step-icon"><i class="pe-7s-photo"></i></div>
                                </div>
                                <h4 class="step-title">Research Photograph</h4>
                            </div>
                            <div class="step" id="step5">
                                <div class="step-icon-wrap">
                                    <div class="step-icon"><i class="pe-7s-key"></i></div>
                                </div>
                                <h4 class="step-title">Research Keywords</h4>
                            </div>
                        </div>

                        <section id="details_section">

                            <div class="row">
                                <div class="col-md-12 d-flex flex-wrap justify-content-center p-4">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="country">Research Area :</label>
                                            <select class="form-select" id="researchArea">
                                                <option value="0">Select Area</option>
                                                <option value="1">Information Technology</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="topic">Research Topic :</label>
                                            <input type="text" class="form-control" id="topic">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">

                                <div class="col-12 d-flex flex-wrap justify-content-end">
                                    <button type="button" class="btn btn-outline-success" id="detailsContBtn" onclick="detailsContBtn()">Continue</button>
                                </div>
                            </div>

                        </section>
                        <section id="author_section" style="display: none;">

                            <div class="row">
                                <form action="submit"></form>
                                <div class="col-md-12 d-flex flex-wrap justify-content-center p-4">

                                    <div class="row">
                                        <div class="col-md-2">
                                            <label for="anic">NIC</label>
                                            <input type="text" id="anic" class="afields">
                                        </div>
                                        <div class="col-md-2">
                                            <label for="afname">First Name</label>
                                            <input type="text" id="afname" class="afields">
                                        </div>
                                        <div class="col-md-2">
                                            <label for="alname">Last Name</label>
                                            <input type="text" id="alname" class="afields">
                                        </div>
                                        <div class="col-md-2">
                                            <label for="ainstitution">Institution</label>
                                            <input type="text" id="ainstitute" class="afields">
                                        </div>
                                        <div class="col-md-2">
                                            <label for="mainauthor">Main author</label>
                                            <input type="checkbox" id="isMain">
                                        </div>
                                        <div class="col-md-2">
                                            <button type="button" class="btn btn-primary" id="authorSubmit" onclick="authorSubmit()">Submit</button>
                                        </div>
                                    </div>
                                </div>
                                </form>
                            </div>
                            <div class="row">
                                <div class="col-md-12 d-flex flex-wrap justify-content-center p-4">
                                    <table class="table" id="atable" style="display: none">
                                        <thead>
                                            <tr>
                                                <th scope="col">NIC</th>
                                                <th scope="col">First Name</th>
                                                <th scope="col">Last Name</th>
                                                <th scope="col">Institution</th>
                                                <th scope="col">Author Type</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-6 d-flex flex-wrap">
                                    <button type="button" class="btn btn-outline-warning" id="authorBackBtn" onclick="authorBackBtn()">Previous</button>
                                </div>
                                <div class="col-6 d-flex flex-wrap justify-content-end">
                                    <button type="button" class="btn btn-outline-success" onclick="authorContBtn()">Continue</button>
                                </div>
                            </div>

                        </section>
                        <section id="abstract_section" style="display: none;">

                            <div class="row">
                                <div class="col-md-12 d-flex flex-wrap justify-content-center p-4">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="abstract">Reseach Abstract</label>
                                            <textarea class="form-control" rows="7" id="abstract"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-6 d-flex flex-wrap">
                                    <button type="button" class="btn btn-outline-warning" id="authorBackBtn" onclick="abstractBackBtn()">Previous</button>
                                </div>
                                <div class="col-6 d-flex flex-wrap justify-content-end">
                                    <button type="button" class="btn btn-outline-success" onclick="abstractContBtn()">Continue</button>
                                </div>
                            </div>

                        </section>
                        <section id="document_section" style="display: none;">

                            <div class="row">
                                <div class="col-md-12 d-flex flex-wrap justify-content-center p-4">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="file">Document</label>
                                            <input type="file" class="form-control-file" id="file" accept="application/pdf">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-6 d-flex flex-wrap">
                                    <button type="button" class="btn btn-outline-warning" id="authorBackBtn" onclick="documentBackBtn()">Previous</button>
                                </div>
                                <div class="col-6 d-flex flex-wrap justify-content-end">
                                    <button type="button" class="btn btn-outline-success" onclick="documentContBtn()">Continue</button>
                                </div>
                            </div>

                        </section>
                        <section id="photograph_section" style="display: none;">

                            <div class="row">
                                <div class="col-md-12 d-flex flex-wrap justify-content-center p-4">
                                    <div class="col-md-6">
                                        <div class="photocontainer" id="filePreview"></div>
                                        <div class="form-group">
                                            <label for="file">Photograph</label>
                                            <input type="file" class="form-control-file" id="filePhoto" accept="image/jpeg, image/png, image/jpg">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-6 d-flex flex-wrap">
                                    <button type="button" class="btn btn-outline-warning" id="authorBackBtn" onclick="photoBackBtn()">Previous</button>
                                </div>
                                <div class="col-6 d-flex flex-wrap justify-content-end">
                                    <button type="button" class="btn btn-outline-success" onclick="photoContBtn()">Continue</button>
                                </div>
                            </div>

                        </section>
                        <section id="keywords_section" style="display: none;">

                            <div class="row">
                                <div class="col-md-12 d-flex flex-wrap justify-content-start p-4">
                                    <div class="col-md-12">

                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="topic">Add keywords :</label>
                                                <input type="text" class="form-control" id="keyword">
                                                <button type="button" class="btn btn-primary" id="addKeywords" onclick="addKeyword()">Add</button>
                                            </div>

                                            <div class="row-cols-12" id="keywordsDiv">
                                            </div>
                                            <div class="row">
                                                <p>You have to pay the research submission fees after pressing the submit button</p>
                                                <div class="col-md-6">

                                                    <input type="checkbox" id="agree"> 
                                                    <label for="agree">Agree to terms and conditions</label>

                                                </div>
                                            </div>
                                        </div></div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-6 d-flex flex-wrap">
                                    <button type="button" class="btn btn-outline-warning" id="authorBackBtn" onclick="keywordsBackBtn()">Previous</button>
                                </div>
                                <div class="col-6 d-flex flex-wrap justify-content-end">
                                    <button type="button" class="btn btn-outline-success" onclick="submitResearch()">Continue</button>
                                </div>
                            </div>

                        </section>

                    </div>
                </div>
            </div>
        </div>




    </body>

</html>
