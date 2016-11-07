/**
 * Created by gaobin on 2016/8/8.
 */

function doLogin() {
    var username = $("#username").val();
    var password = $("#password").val();
    var userError_html ="<span>请输入用户名!</span>";
    var psdError_html = "<span>请输入登录密码!</span>";
    if(username==null || username==""){
     $("#error").html(userError_html);
        return;
    }
    if(password==null || password==""){
        $("#error").html(psdError_html);
        return;
    }
    $("#loginFrom").submit();
}
$(document).keyup(function(event){
    switch(event.keyCode) {
        case 13:
            $("#loginFrom").submit();
    }
});