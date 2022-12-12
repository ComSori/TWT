
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta name="description" content="" />
  <meta name="author" content="" />
  <title>WorkSpace</title>
  <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
  <!-- Font Awesome icons (free version)-->
  <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
  <!-- Google fonts-->
  <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
  <!-- Core theme CSS (Bootstrap)-->
  <link href="resources/css/navStyles.css" rel="stylesheet" />

  <link href="resources/css/content_workSpace.css" rel="stylesheet" />
  <script type="text/javascript" src="resources/js/navScripts.js"></script>

  <script type="text/javascript">
    window.onload = function() {
      u_name = getCookie("name");
      if(u_name){ // 세션이 있을때 (로그인중일때)
        document.getElementById("login_info").innerText += u_name + "님 환영합니다.";
        document.getElementById("login_btn").style.display = "none";
        document.getElementById("logout_btn").style.display = "block";
      } else { // 세션이 없을때 (로그아웃 상태일때)
        document.getElementById("login_btn").style.display = "block";
        document.getElementById("logout_btn").style.display = "none";
      }



      if(!(getCookie("text") == null)) {
        <% HttpSession sessions = request.getSession(); %>
        let text="<%= sessions.getAttribute("text") %>";
        text = text.split(":");
        console.log(text);
        let x=getCookie("x").split(":");
        let y=getCookie("y").split(":");
        let c=getCookie("c").split(":");
         for(let i = 0; i < text.length - 1; i++) {
           create_memo_data(text[i], x[i], y[i], c[i]);
         }
      } else {
        alert("아무 곳이나 클릭해 글을 작성하세요.");
      }
    }

    function clickSpace(event) {
      if(getComputedStyle(document.getElementById("memo_inputBox")).display == "none"){
        var x = event.offsetX;
        var y = event.offsetY;

        if(event.target.getAttribute("id") == "memo_controlBar") {
          x = parseInt(x) + parseInt(event.target.parentElement.style.left.replace("px", ""));
          y = parseInt(y) + parseInt(event.target.parentElement.style.top.replace("px", ""));
        } else if(event.target.getAttribute("id") == "memo_inputText") {
          x = parseInt(x) + parseInt(event.target.parentElement.style.left.replace("px", ""));
          y = parseInt(y) + parseInt(event.target.parentElement.style.top.replace("px", "")) +25;
        }
        var box = document.getElementById("memo_inputBox");
        box.style.display = "inline-block";
        box.style.left = x + "px";
        box.style.top = y + "px";

        document.getElementById("posX").setAttribute("value", x);
        document.getElementById("posY").setAttribute("value", y);

        event.stopPropagation();
      }
    }

    function memo_cancel() {

      var box = document.getElementById("memo_inputBox");
      document.getElementById("memo_inputText").value = ""; // text reset
      box.style.display = "none";
      event.stopPropagation();
    }

    function memo_submit() {
      /******************
       *  속성 가져오기   *
       *****************/
      document.getElementById("text").setAttribute("value", document.getElementById("memo_inputText").value);
      var text = document.getElementById("memo_inputText").value;
      let memoBox = document.memoBox;
      if(!(text=="")) {
        memoBox.method="post"
        memoBox.action="/memoAdd";
        memoBox.submit();
        /*
        create_memo_data(text, posX, posY);

        var box = document.getElementById("memo_inputBox");
        box.style.display = "none";
*/
        document.getElementById("memo_inputText").value = ""; // text reset


      }
      else {
        alert("텍스트를 입력해주세요");
        //window.history.back();
      }
      event.stopPropagation();
    }

    function create_memo_data(text, posX, posY, c) {
      /******************
       * 새로운 박스 생성  *
       ******************/

      var board = document.getElementById("memo_space");
      var newForm = document.createElement("form");
      newForm.setAttribute("id", c + "form");
      var newMemo = document.createElement("div");
      newMemo.setAttribute("id", "memo_inputBox_updated");
      newMemo.style.top = posY;
      newMemo.style.left = posX;
      var newMemo_controlBox = document.createElement("div");
      newMemo_controlBox.setAttribute("id", "memo_controlBar");
      var newMemo_cancel_button = document.createElement("button");
      newMemo_cancel_button.setAttribute("id", "cancel_button");
      newMemo_cancel_button.setAttribute("type", "button");
      var newhiddeninfo1 = document.createElement("input");
      newhiddeninfo1.setAttribute("type", "hidden");
      newhiddeninfo1.setAttribute("name", "count");
      newhiddeninfo1.setAttribute("value", c);
      newMemo_cancel_button.onclick = function(){
        let memoBox = document.getElementById(c + "form");
        memoBox.method="post";
        memoBox.action="/memoDelete";
        memoBox.submit();
        event.stopPropagation();
      };
      newMemo_controlBox.append(newMemo_cancel_button);
      var newMemo_textBox = document.createElement("textarea");
      newMemo_textBox.setAttribute("readonly","readonly");
      newMemo_textBox.setAttribute("id", "memo_inputText");
      newMemo_textBox.innerText += text;

      newMemo.append(newhiddeninfo1);
      newMemo.append(newMemo_controlBox);
      newMemo.append(newMemo_textBox);

      newForm.append(newMemo);
      board.append(newForm);
      event.stopPropagation();
    }
  </script>
</head>
<body>
  <!-- Page Header-->
  <header class="masthead" style="background-image: url('assets/img/home-bg.jpg')">
    <div class="container position-relative px-4 px-lg-5">
      <div class="row gx-4 gx-lg-5 justify-content-center">
        <div class="col-md-10 col-lg-8 col-xl-7">
          <div class="site-heading">
            <h1>TWT</h1>
            <span class="subheading">Team Work Tool</span>
          </div>
        </div>
      </div>
    </div>
  </header>
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
    <div class="container px-4 px-lg-5">
      <div class="navbar-brand nav-logo">TWT</div>
      <a class="navbar-brand" href="Main.html">Main</a>
      <a class="navbar-brand" href="/Timetable">TimeTable</a>
      <a class="navbar-brand this-page" href="/memoLoad">WorkSpace</a>
      <div class="collapse navbar-collapse" id="navbarResponsive"></div>
      <div id="login_info"></div>
      <button type="button" id="login_btn" class="navbar-brand" onclick="login()">login</button>
      <button type="hidden" id="logout_btn" class="navbar-brand" onclick="logout()">logout</button>
    </div>
  </nav>

  <!-- Main Content-->

  <div class="main_Content gx-4 gx-lg-5 justify-content-center">
    <div class="board_Contents" id="board_Contents"> <!-- memo Contents -->
      <div id="memo_space" onclick="clickSpace(event)" method="POST" action="memo_submit">
        <form name="memoBox" id="memo_inputBox">
          <div id="memo_controlBar">
            <button id="cancel_button" type="button" onclick="memo_cancel()">
            </button>
            <button id="add_button" type="button" onclick="memo_submit()">
            </button>
          </div>
          <textarea type="text" placeholder="input text" name="memo_content" id="memo_inputText"></textarea>
          <input type="hidden" name="text" id="text">
          <input type="hidden" name="posX" id="posX">
          <input type="hidden" name="posY" id="posY">
        </form>
      </div>
    </div>
  </div>

<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
