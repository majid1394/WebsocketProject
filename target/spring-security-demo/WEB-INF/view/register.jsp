<!DOCTYPE html>
<head>



    <title>Register User</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/bootsrap.4.1.1.css">
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/registerCSS.css">


    <script src="${pageContext.request.contextPath}/resources/js/JQuery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootsrap.js"></script>



    <%-- <link type="text/css"
           rel="stylesheet"
           href="${pageContext.request.contextPath}/resources/css/RegisterStyle.css">--%>

</head>

<body>



<div class="container register">
    <div class="row">
        <div class="col-md-3 register-left">
            <img src="https://image.ibb.co/n7oTvU/logo_white.png" alt=""/>
            <h3>Welcome</h3>
            <p>To Question Answer Play!</p>
            <input type="submit" name="" value="Login"/><br/>
        </div>
        <div class="col-md-9 register-right">
            <ul class="nav nav-tabs nav-justified" id="myTab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Player</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Gust</a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <h3 class="register-heading">Apply as a Player</h3>
                    <form >
                        <div class="row register-form">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="firstname" placeholder="First Name *" value="" />
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="family" placeholder="Last Name *" value="" />
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" id="username" placeholder="User Name *" value="" />
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control"  id="password" placeholder="Password *" value="" />
                                </div>
                                <div class="form-group">
                                    <div class="maxl">
                                        <label class="radio inline">
                                            <input type="radio" name="gender" value="male" checked>
                                            <span> Male </span>
                                        </label>
                                        <label class="radio inline">
                                            <input type="radio" name="gender" value="female">
                                            <span>Female </span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">

                                <div class="form-group">
                                    <input type="email" id="email" class="form-control" placeholder="Your Email *" value="" />
                                </div>
                                <div class="form-group">
                                    <input type="text" minlength="10" maxlength="10" name="txtEmpPhone" class="form-control" placeholder="Your Phone *" value="" />
                                </div>
                                <div class="form-group">
                                    <select class="form-control">
                                        <option class="hidden"  selected disabled>Please select your Sequrity Question</option>
                                        <option>What is your Birthdate?</option>
                                        <option>What is Your old Phone Number</option>
                                        <option>What is your Pet Name?</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Enter Your Answer *" value="" />
                                </div>
                                <input type="submit" class="btnRegister"  value="Register" onclick="return AjaxPostData(event)"/>

                            </div>
                        </div>
                    </form>
                </div>


                <div class="tab-pane fade show" id="profile" role="tabpanel" aria-labelledby="profile-tab">

                </div>

            </div>
        </div>
    </div>

</div>

<script type="text/javascript">
    {
        function AjaxPostData(e) {
            e.preventDefault();
            var formdata=
                {
                    name : document.getElementById('firstname').value,
                    family : document.getElementById('family').value,
                    email : document.getElementById('email').value,
                    username:  document.getElementById('username').value,
                    password : document.getElementById('password').value
                };
            $.ajax({
                type: "POST",
                dataType: "text",
                contentType : "application/json",
                url: "${pageContext.request.contextPath}/signUp",
                data: JSON.stringify(formdata),
                success: function(response){
                    window.location.href="${pageContext.request.contextPath}/showMyLoginPage";
                },
                error: function(data){
                    alert("some erros occured");
                    //get the status code
                },
            });
        }
    }
</script>
</body>
</html>

