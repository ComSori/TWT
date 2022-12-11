<%@ page import="Resources.timetable.TimetableVO_list" %>
<%@ page import="Resources.timetable.TimetableVO" %>
<%@ page import="Resources.timetable.Class_list" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta name="description" content="" />
  <meta name="author" content="" />
  <title>TimeTable</title>
  <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
  <!-- Font Awesome icons (free version)-->
  <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
  <!-- Google fonts-->
  <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
  <!-- Core theme CSS (Bootstrap)-->
  <link href="resources/css/navStyles.css" rel="stylesheet" />
  <link href="resources/css/content_timeTable.css" rel="stylesheet" />
  <script type="text/javascript" src="resources/js/navScripts.js"></script>
  <script type = "text/javascript">
    <%
    String tmp_id = new String();
    String tmp_name = new String();
    int[][] collapse = new int[7][24];
    for(int i = 0;i<7;i++){
      for(int j = 0;j<24;j++){
        collapse[i][j]=0;
      }
    }
    %>
    window.onload = function() {
        u_name = getCookie("name");
       if(u_name){ // 세션이 있을때 (로그인중일때)
         document.getElementById("login_info").innerText += u_name + "님 환영합니다.";
         document.getElementById("login_btn").style.display = "none";
         document.getElementById("logout_btn").style.display = "block";
       } else { // 세션이 없을때 (로그아웃 상태일때)
         document.getElementById("login_btn").style.display = "block";
         document.getElementById("logout_btn").style.display = "none";
         // out.flush();
         // location.href="Main.html";
       }
      <%
        TimetableVO_list vo_list = (TimetableVO_list) request.getAttribute("vo_list");
        for(TimetableVO c:vo_list){
          tmp_id = c.getUid();
          tmp_name = c.getU_name();
      %>
      create_button("<%=tmp_id%>","<%=tmp_name%>");
      <%
        }
      %>
      create_table();
    }
    function create_button(u_id, u_name) {
      var board = document.getElementById("sideBar");
      var newButton = document.createElement("button");
      var text = document.createTextNode(u_name);
      newButton.setAttribute("id", u_id);
      newButton.setAttribute("value", u_id);//value안쓰는거같은데
      newButton.setAttribute("type", "button");
      newButton.setAttribute("onclick","table_display('"+u_id+"')");
      newButton.appendChild(text);
      board.append(newButton);
    }
    function create_table() {
      var board = document.getElementsByClassName("cols");
      var newdiv;
      var text;
      <%
      ArrayList<String> color = new ArrayList<String>();
      int i = 0;
      for(TimetableVO c:vo_list){
        color.add("#"+String.format("%x",Math.round(Math.random()*0xffffff)));
        for(Class_list l : c.getList()){
        //  for(int j =0;j<Integer.parseInt(l.getEnd())-Integer.parseInt(l.getStart())+1;j++){
         //   collapse[l.getWeek()][Integer.parseInt(l.getStart()+j)]+=1;
         // }
      %>
          var divstart = document.querySelector(".grids<%=l.getWeek()%> .grid_<%=l.getStart()%>");
          var divend = document.querySelector(".grids<%=l.getWeek()%> .grid_<%=l.getEnd()%>");
          newdiv = document.createElement("div");
          newdiv.setAttribute("class", "<%=c.getUid()%> col");
          newdiv.setAttribute("style", "line-height : "+(divend.getBoundingClientRect().bottom-divstart.getBoundingClientRect().top)+"px; background : <%=color.get(i)%>; top : "+divstart.offsetTop+"px; "+"left : "+divstart.offsetLeft+"px; "+"width : "+divstart.clientWidth+"px; "+"height : "+(divend.getBoundingClientRect().bottom-divstart.getBoundingClientRect().top)+"px; visibility : visible;");
          newdiv.setAttribute("id", "<%=c.getUid()%>");
          newdiv.setAttribute("onmouseover","showdetails()");
          text = document.createTextNode("<%=c.getU_name()%>");
          newdiv.appendChild(text);
          board[<%=l.getWeek()%>].append(newdiv);
      <%
        }
        i++;
      }
      %>
    }
    function table_display(u_id){
      var tag = document.getElementsByClassName(u_id);
      for(var i = 0;i<tag.length;i++){
        if(tag[i].style.visibility == "visible"){
          tag[i].style.visibility = "hidden";
        }
        else{
          tag[i].style.visibility = "visible";
        }
      }
    }
    function showdetails(){

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
    <a class="navbar-brand this-page" href="/Timetable">TimeTable</a>
    <a class="navbar-brand" href="/memoLoad">WorkSpace</a>
    <div class="collapse navbar-collapse" id="navbarResponsive"></div>
    <div id="login_info"></div>
    <button type="button" id="login_btn" class="navbar-brand" onclick="login()">login</button>
    <button type="hidden" id="logout_btn" class="navbar-brand" onclick="logout()">logout</button>
  </div>
</nav>

<!-- Main Content-->
<div class="main_Content gx-4 gx-lg-5 justify-content-center">
  <div id="sideBar" class = "sideBar">
    <button onclick="location.href='Insert_timetable.html'" class="specialTableBtn">
      시간표 추가
    </button>
    <button onclick="location.href='/deleteTable'" class="specialTableBtn">
      시간표 삭제
    </button>
  </div>
  <div class="timeTable_Contents">
    <table>
      <tr>
        <td class = "hours">
          <div>
            <div class = "day hour"></div>
            <div class = "hour">09:00</div>
            <div class = "hour">09:30</div>
            <div class = "hour">10:00</div>
            <div class = "hour">10:30</div>
            <div class = "hour">11:00</div>
            <div class = "hour">11:30</div>
            <div class = "hour">12:00</div>
            <div class = "hour">12:30</div>
            <div class = "hour">13:00</div>
            <div class = "hour">13:30</div>
            <div class = "hour">14:00</div>
            <div class = "hour">14:30</div>
            <div class = "hour">15:00</div>
            <div class = "hour">15:30</div>
            <div class = "hour">16:00</div>
            <div class = "hour">16:30</div>
            <div class = "hour">17:00</div>
            <div class = "hour">17:30</div>
            <div class = "hour">18:00</div>
            <div class = "hour">18:30</div>
            <div class = "hour">19:00</div>
            <div class = "hour">19:30</div>
            <div class = "hour">20:00</div>
            <div class = "hour">20:30</div>
          </div>
        </td>
        <td>
          <div class = "cols">

          </div>
          <div class = "grids grids0">
            <div class = "day">월</div>
            <div class = "grid grid_0"></div>
            <div class = "grid grid_1"></div>
            <div class = "grid grid_2"></div>
            <div class = "grid grid_3"></div>
            <div class = "grid grid_4"></div>
            <div class = "grid grid_5"></div>
            <div class = "grid grid_6"></div>
            <div class = "grid grid_7"></div>
            <div class = "grid grid_8"></div>
            <div class = "grid grid_9"></div>
            <div class = "grid grid_10"></div>
            <div class = "grid grid_11"></div>
            <div class = "grid grid_12"></div>
            <div class = "grid grid_13"></div>
            <div class = "grid grid_14"></div>
            <div class = "grid grid_15"></div>
            <div class = "grid grid_16"></div>
            <div class = "grid grid_17"></div>
            <div class = "grid grid_18"></div>
            <div class = "grid grid_19"></div>
            <div class = "grid grid_20"></div>
            <div class = "grid grid_21"></div>
            <div class = "grid grid_22"></div>
            <div class = "grid grid_23"></div>
          </div>
        </td>
        <td>
          <div class = "cols">

          </div>
          <div class = "grids grids1">
            <div class = "day">화</div>
            <div class = "grid grid_0"></div>
            <div class = "grid grid_1"></div>
            <div class = "grid grid_2"></div>
            <div class = "grid grid_3"></div>
            <div class = "grid grid_4"></div>
            <div class = "grid grid_5"></div>
            <div class = "grid grid_6"></div>
            <div class = "grid grid_7"></div>
            <div class = "grid grid_8"></div>
            <div class = "grid grid_9"></div>
            <div class = "grid grid_10"></div>
            <div class = "grid grid_11"></div>
            <div class = "grid grid_12"></div>
            <div class = "grid grid_13"></div>
            <div class = "grid grid_14"></div>
            <div class = "grid grid_15"></div>
            <div class = "grid grid_16"></div>
            <div class = "grid grid_17"></div>
            <div class = "grid grid_18"></div>
            <div class = "grid grid_19"></div>
            <div class = "grid grid_20"></div>
            <div class = "grid grid_21"></div>
            <div class = "grid grid_22"></div>
            <div class = "grid grid_23"></div>
          </div>
        </td>
        <td>
          <div class = "cols">

          </div>
          <div class = "grids grids2">
            <div class = "day">수</div>
            <div class = "grid grid_0"></div>
            <div class = "grid grid_1"></div>
            <div class = "grid grid_2"></div>
            <div class = "grid grid_3"></div>
            <div class = "grid grid_4"></div>
            <div class = "grid grid_5"></div>
            <div class = "grid grid_6"></div>
            <div class = "grid grid_7"></div>
            <div class = "grid grid_8"></div>
            <div class = "grid grid_9"></div>
            <div class = "grid grid_10"></div>
            <div class = "grid grid_11"></div>
            <div class = "grid grid_12"></div>
            <div class = "grid grid_13"></div>
            <div class = "grid grid_14"></div>
            <div class = "grid grid_15"></div>
            <div class = "grid grid_16"></div>
            <div class = "grid grid_17"></div>
            <div class = "grid grid_18"></div>
            <div class = "grid grid_19"></div>
            <div class = "grid grid_20"></div>
            <div class = "grid grid_21"></div>
            <div class = "grid grid_22"></div>
            <div class = "grid grid_23"></div>
          </div>
        </td>
        <td>
          <div class = "cols">

          </div>
          <div class = "grids grids3">
            <div class = "day">목</div>
            <div class = "grid grid_0"></div>
            <div class = "grid grid_1"></div>
            <div class = "grid grid_2"></div>
            <div class = "grid grid_3"></div>
            <div class = "grid grid_4"></div>
            <div class = "grid grid_5"></div>
            <div class = "grid grid_6"></div>
            <div class = "grid grid_7"></div>
            <div class = "grid grid_8"></div>
            <div class = "grid grid_9"></div>
            <div class = "grid grid_10"></div>
            <div class = "grid grid_11"></div>
            <div class = "grid grid_12"></div>
            <div class = "grid grid_13"></div>
            <div class = "grid grid_14"></div>
            <div class = "grid grid_15"></div>
            <div class = "grid grid_16"></div>
            <div class = "grid grid_17"></div>
            <div class = "grid grid_18"></div>
            <div class = "grid grid_19"></div>
            <div class = "grid grid_20"></div>
            <div class = "grid grid_21"></div>
            <div class = "grid grid_22"></div>
            <div class = "grid grid_23"></div>
          </div>
        </td>
        <td>
          <div class = "cols">

          </div>
          <div class = "grids grids4">
            <div class = "day">금</div>
            <div class = "grid grid_0"></div>
            <div class = "grid grid_1"></div>
            <div class = "grid grid_2"></div>
            <div class = "grid grid_3"></div>
            <div class = "grid grid_4"></div>
            <div class = "grid grid_5"></div>
            <div class = "grid grid_6"></div>
            <div class = "grid grid_7"></div>
            <div class = "grid grid_8"></div>
            <div class = "grid grid_9"></div>
            <div class = "grid grid_10"></div>
            <div class = "grid grid_11"></div>
            <div class = "grid grid_12"></div>
            <div class = "grid grid_13"></div>
            <div class = "grid grid_14"></div>
            <div class = "grid grid_15"></div>
            <div class = "grid grid_16"></div>
            <div class = "grid grid_17"></div>
            <div class = "grid grid_18"></div>
            <div class = "grid grid_19"></div>
            <div class = "grid grid_20"></div>
            <div class = "grid grid_21"></div>
            <div class = "grid grid_22"></div>
            <div class = "grid grid_23"></div>
          </div>
        </td>
        <td>
          <div class = "cols">

          </div>
          <div class = "grids grids5">
            <div class = "day">토</div>
            <div class = "grid grid_0"></div>
            <div class = "grid grid_1"></div>
            <div class = "grid grid_2"></div>
            <div class = "grid grid_3"></div>
            <div class = "grid grid_4"></div>
            <div class = "grid grid_5"></div>
            <div class = "grid grid_6"></div>
            <div class = "grid grid_7"></div>
            <div class = "grid grid_8"></div>
            <div class = "grid grid_9"></div>
            <div class = "grid grid_10"></div>
            <div class = "grid grid_11"></div>
            <div class = "grid grid_12"></div>
            <div class = "grid grid_13"></div>
            <div class = "grid grid_14"></div>
            <div class = "grid grid_15"></div>
            <div class = "grid grid_16"></div>
            <div class = "grid grid_17"></div>
            <div class = "grid grid_18"></div>
            <div class = "grid grid_19"></div>
            <div class = "grid grid_20"></div>
            <div class = "grid grid_21"></div>
            <div class = "grid grid_22"></div>
            <div class = "grid grid_23"></div>
          </div>
        </td>
        <td>
          <div class = "cols">

          </div>
          <div class = "grids grids6">
            <div class = "day">일</div>
            <div class = "grid grid_0"></div>
            <div class = "grid grid_1"></div>
            <div class = "grid grid_2"></div>
            <div class = "grid grid_3"></div>
            <div class = "grid grid_4"></div>
            <div class = "grid grid_5"></div>
            <div class = "grid grid_6"></div>
            <div class = "grid grid_7"></div>
            <div class = "grid grid_8"></div>
            <div class = "grid grid_9"></div>
            <div class = "grid grid_10"></div>
            <div class = "grid grid_11"></div>
            <div class = "grid grid_12"></div>
            <div class = "grid grid_13"></div>
            <div class = "grid grid_14"></div>
            <div class = "grid grid_15"></div>
            <div class = "grid grid_16"></div>
            <div class = "grid grid_17"></div>
            <div class = "grid grid_18"></div>
            <div class = "grid grid_19"></div>
            <div class = "grid grid_20"></div>
            <div class = "grid grid_21"></div>
            <div class = "grid grid_22"></div>
            <div class = "grid grid_23"></div>
          </div>
        </td>
      </tr>
    </table>
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
        <div class="small text-center text-muted fst-italic">Copyright &copy; TWT 2022</div>
      </div>
    </div>
  </div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<!-- <script src="resources/js/navScripts.js"></script> -->
</body>
</html>