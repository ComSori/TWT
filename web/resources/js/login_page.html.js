
function chk_id_distinct() {

    var id = document.getElementById("user_ID").value;
    var sql = "select id from user_table where id = " + id;


}