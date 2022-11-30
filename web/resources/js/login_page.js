
function chk_id_dup() {
    var r_id = document.getElementById("user_ID").value;
    location.href="/id_check?r_id=" + r_id;
}