$(function () {

    $("#id_user_register_submit").bind("click", function () {
        var registerForm = document.forms[0];
        registerForm.action = getRootPath() + "/userregister.f";
        registerForm.method = "post";
        registerForm.submit();
    });
});