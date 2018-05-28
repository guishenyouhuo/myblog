$('#id_a_wechat').hover(function () {
    $('#id_img_wechat_qrcode').fadeIn("fast");
},function () {
    $('#id_img_wechat_qrcode').fadeOut("fast");

});

$(function () {
	$("#navbarResponsive").find("li").each(function () {
        var a = $(this).find("a:first")[0];
        if ($(a).attr("href") === location.pathname) {
            $(this).addClass("active");
        } else {
            $(this).removeClass("active");
        }
    });
});
