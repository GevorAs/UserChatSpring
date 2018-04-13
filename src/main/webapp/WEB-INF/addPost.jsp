<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">


<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>MGGM</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">
    <style>
        .disclaimer {
            width: 480px;
            color: #646464;
            margin: 20px auto;
            padding: 0 16px;
            text-align: center;
            font: 400 12px Roboto, Helvetica, Arial, sans-serif
        }

        .disclaimer a {
            color: #009688
        }

        #credit {
            display: none
        }
    </style>
</head>
<body>

<div class="row">
    <div class="input-field col s6">
        <a href="/userPage"> <button class="waves-effect waves-light btn submit-btn" type="submit">
            Home
        </button>
        </a>
    </div>
<spring:form class="main" id="form" action="/savePost" novalidate="novalidate"
             style="max-width: 480px;margin: 40px auto;" enctype="multipart/form-data" modelAttribute="addPost"
             method="post">
    <div id="forminner">
        <div class="row">
            <div class="col s12">
                <h5 class="center-align teal-text">Add new Post</h5>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <spring:textarea id="name" type="text" path="text" class="validate" required=""
                                 aria-required="true"></spring:textarea>
                <label for="name">Description</label>
            </div>
        </div>


        <div class="row">
            <div class="file-field input-field col s12">
                <div class="btn">
                    <span>File</span>
                    <input id="files" type="file" name="postFile">
                </div>
                <div class="file-path-wrapper">
                    <input class="file-path validate" type="text" placeholder="Select a file on your computer">
                </div>
            </div>
        </div>
        <h5> &nbsp;&nbsp;Or...</h5>
        <div class="row">
            <div class="file-field input-field col s12">
                <div class="btn">
                    <span>Image</span>
                    <input id="image" type="file" name="postImage">
                </div>
                <div class="file-path-wrapper">
                    <input class="file-path validate" type="text" placeholder="Select a iamge on your computer">
                </div>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s6">
                <button class="waves-effect waves-light btn submit-btn" type="submit">
                    Submit
                </button>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12" id="progress">
            </div>
        </div>
    </div>
    <!--<div id="success" style="display:block">-->
    <!--<h5 class="left-align teal-text">File Uploaded</h5>-->
    <!--<p>Your file has been successfully uploaded.</p>-->
    <!--<p>The <a href="http://www.labnol.org/internet/file-upload-google-forms/29170/">pro version</a> (see <a href="http://j.mp/GoogleFormsDemo">demo form</a>) includes a visual drag-n-drop form builder, CAPTCHAs, the form responses are saved in a Google Spreadsheet and respondents can upload multiple files of any size.</p> -->
    <!--<p class="center-align"><a class="btn btn-large" href="https://gum.co/GA14?wanted=true" target="_blank">Upgrade to Pro</a></p>-->
    <!--</div>-->
</spring:form>


<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
<!--     <script src="https://gumroad.com/js/gumroad.js"></script> -->

<script>

    var file,
        reader = new FileReader();

    reader.onloadend = function (e) {
        if (e.target.error != null) {
            showError("File " + file.name + " could not be read.");
            return;
        } else {
            google.script.run
                .withSuccessHandler(showSuccess)
                .uploadFileToGoogleDrive(e.target.result, file.name, $('input#name').val(), $('input#email').val());
        }
    };

    function showSuccess(e) {
        if (e === "OK") {
            $('#forminner').hide();
            $('#success').show();
        } else {
            showError(e);
        }
    }

    function submitForm() {

        var files = $('#files')[0].files;

        if (files.length === 0) {
            showError("Please select a file to upload");
            return;
        }

        file = files[0];

        if (file.size > 1024 * 1024 * 5) {
            showError("The file size should be < 5 MB. Please <a href='http://www.labnol.org/internet/file-upload-google-forms/29170/' target='_blank'>upgrade to premium</a> for receiving larger files in Google Drive");
            return;
        }

        showMessage("Uploading file..");


    }

    function showError(e) {
        $('#progress').addClass('red-text').html(e);
    }

    function showMessage(e) {
        $('#progress').removeClass('red-text').html(e);
    }


</script>

</body>

</html>


<script src="../front/past_form/js/index.js"></script>


</body>

</html>
