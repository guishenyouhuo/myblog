$(function () {
    $("#id_user_login_submit").bind("click", function () {
        var loginForm = document.forms[0];
        loginForm.action = getRootPath() + "/userlogin.f";
        loginForm.method = "post";
        loginForm.submit();
    });
});