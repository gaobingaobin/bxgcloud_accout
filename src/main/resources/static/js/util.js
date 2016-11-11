/**
 * Created by gaobin on 2016/11/11.
 */
function checkTip(name,nameTxt,msg) {
    if (name == null||name=="") {
        $("#"+nameTxt).tips({
            side: 3,
            msg: msg,
            bg: '#AE81FF',
            time: 2
        });
        $("#"+nameTxt).focus();
        return false;
    }else {
        return true
    }
}
