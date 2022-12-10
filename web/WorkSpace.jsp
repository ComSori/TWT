
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

    }

    function clickSpace(event) {
      if(getComputedStyle(document.getElementById("memo_inputBox")).display == "none"){
        var x = event.offsetX;
        var y = event.offsetY;
        console.log(event.target.getAttribute("id"));

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
        document.getElementById("text").setAttribute("value", document.getElementById("memo_inputText").value);
        document.getElementById("posX").setAttribute("value", x);
        document.getElementById("posY").setAttribute("value", y);

        event.stopPropagation();
      }
    }

    function memo_cancel() {
      console.log("cancel");

      var box = document.getElementById("memo_inputBox");
      box.style.display = "none";
      event.stopPropagation();
    }

    function memo_submit() {
      console.log("submit");
      /******************  TODO - DB에 입력
       *  속성 가져오기   *
       *****************/
      var text = document.getElementById("memo_inputText").value;
      var posX = document.getElementById("memo_inputBox").style.left;
      var posY = document.getElementById("memo_inputBox").style.top;
      let memoBox = document.memoBox;
      if(text != null) {
        memoBox.method="post"
        memoBox.action="/MemoAdd";
        memoBox.submit();
        /*
        create_memo_data(text, posX, posY);

        var box = document.getElementById("memo_inputBox");
        box.style.display = "none";

        document.getElementById("memo_inputText").value = ""; // text reset

         */
      }
      else {
        alert("텍스트를 입력해주세요");
        window.history.back();
      }

      event.stopPropagation();

    }

    function create_memo_data(text, posX, posY) {
      /******************   TODO - 반복문으로 DB에서 값 불러와서 출력
       * 새로운 박스 생성  *
       ******************/

      var board = document.getElementById("memo_space");
      var newMemo = document.createElement("div");
      newMemo.setAttribute("id", "memo_inputBox_updated");
      newMemo.style.top = posY;
      newMemo.style.left = posX;
      var newMemo_controlBox = document.createElement("div");
      newMemo_controlBox.setAttribute("id", "memo_controlBar");
      var newMemo_cancel_button = document.createElement("button");
      newMemo_cancel_button.setAttribute("id", "cancel_button");
      newMemo_cancel_button.setAttribute("type", "button");
      newMemo_cancel_button.onclick = function(){
        newMemo.remove();
        event.stopPropagation();
      };
      newMemo_controlBox.append(newMemo_cancel_button);

      var newMemo_textBox = document.createElement("div");
      newMemo_textBox.setAttribute("id", "memo_inputText");
      newMemo_textBox.innerText += text;


      newMemo.append(newMemo_controlBox);
      newMemo.append(newMemo_textBox);

      console.log(newMemo);
      board.append(newMemo);
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
      <a class="navbar-brand" href="TimeTable.html">TimeTable</a>
      <a class="navbar-brand this-page" href="WorkSpace.html">WorkSpace</a>
      <div class="collapse navbar-collapse" id="navbarResponsive"></div>
      <div id="login_info"></div>
      <button type="button" id="login_btn" class="navbar-brand" onclick="login()">login</button>
      <button type="hidden" id="logout_btn" class="navbar-brand" onclick="logout()">logout</button>
    </div>
  </nav>

  <!-- Main Content-->

  <div class="main_Content gx-4 gx-lg-5 justify-content-center">
    <div class="sideBar">

    </div>
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
    <div class="side_Contents">
      <div class="login_info_menu">
        <div id="login_info_logo">로그인 목록</div>
        <div id="login_info_box">

        </div>
      </div>
      <div class="chat_menu">

      </div>
    </div>
  </div>
  <!-- Footer-->
  <footer class="border-top">
    <div class="container px-4 px-lg-5">
      <div class="row gx-4 gx-lg-5 justify-content-center">
        <div class="col-md-10 col-lg-8 col-xl-7">
          <ul class="list-inline text-center">
            <li class="list-inline-item">
              <a href="#!">
                                      <span class="fa-stack fa-lg">
                                          <i class="fas fa-circle fa-stack-2x"></i>
                                          <i class="fab fa-twitter fa-stack-1x fa-inverse"></i>
                                      </span>
              </a>
            </li>
            <li class="list-inline-item">
              <a href="#!">
                                      <span class="fa-stack fa-lg">
                                          <i class="fas fa-circle fa-stack-2x"></i>
                                          <i class="fab fa-facebook-f fa-stack-1x fa-inverse"></i>
                                      </span>
              </a>
            </li>
            <li class="list-inline-item">
              <a href="#!">
                                      <span class="fa-stack fa-lg">
                                          <i class="fas fa-circle fa-stack-2x"></i>
                                          <i class="fab fa-github fa-stack-1x fa-inverse"></i>
                                      </span>
              </a>
            </li>
          </ul>
          <div class="small text-center text-muted fst-italic">Copyright &copy; Your Website 2022</div>
        </div>
      </div>
    </div>
  </footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>