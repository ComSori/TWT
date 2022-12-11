var u_name;

window.onload = function() {
    /*
    u_name = getCookie("name");
    if(u_name){
        document.getElementById("login_info").innerText += u_name + "님 환영합니다.";
    }
    else {
        alert('로그인 후 이용해주세요');
        location.href='login_page.html';
    }
    */
}

function getCookie(name) {
    var cookieKey = name + "=";
    var result = "";
    const cookieArr = document.cookie.split(";");

    for(var i = 0; i < cookieArr.length; i++) {
        if(cookieArr[i][0] === " ") {
            cookieArr[i] = cookieArr[i].substring(1);
        }

        if(cookieArr[i].indexOf(cookieKey) === 0) {
            result = cookieArr[i].slice(cookieKey.length, cookieArr[i].length);
            return result;
        }
    }
    return result;
}

function logout() {
    // 세션 초기화
    document.cookie = 'name=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
    // 로그인 페이지로 이동
    location.href="/logout";
}

function login() {
    location.href="login_page.html";
}