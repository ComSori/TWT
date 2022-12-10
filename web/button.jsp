<%@ page import="Resources.timetable.TimetableVO_list" %>
<%@ page import="Resources.timetable.TimetableVO" %>
<%@ page import="Resources.timetable.Class_list" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<link href="resources/css/navStyles.css" rel="stylesheet" />
<link href="resources/css/content_timeTable.css" rel="stylesheet" />
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="TimeTable.html"/>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <script type="text/javascript" src="resources/js/timetable.js"></script>
  <script>
    function create_button(u_id) {
      var board = document.getElementById("sideBar");
      var newButton = document.createElement("button");
      newButton.setAttribute("id", u_id);
      newButton.setAttribute("value", u_id);
      newButton.setAttribute("type", "button");
      board.append(newButton);
      alert("버튼 생성 완료");
    }
  </script>
</head>
<body>
<%--<script>alert(<%=session.getAttribute("u_id_cnt")%>)</script>--%>
<%--<script>--%>
<%--    window.onload = function(){--%>
<%--        var side = document.getElementById("sideBar");--%>
<%--        var button = document.createElement("button");--%>
<%--        button.setAttribute("type","button");--%>
<%--        button.setAttribute("value","test");--%>
<%--        side.append(button);--%>
<%--    }--%>
<%--</script>--%>
<%
//  TimetableVO_list vo_list = (TimetableVO_list) request.getAttribute("vo_list");
//    TimetableVO_list vo_list = new TimetableVO_list();
//  TimetableVO vo = new TimetableVO();
//    ArrayList<Class_list> arr = new ArrayList<Class_list>();
//    Class_list list = new Class_list("song", 1, "start", "end", "sysof", "123");
//    arr.add(list);
//    vo.setUid("1");
//    vo.setTid("test");
//    vo.setList(arr);
//    vo_list.add(vo);
//    String u_id = new String();
  ArrayList<String> u_id_list = (ArrayList) session.getAttribute("u_id_list");
  int u_id_cnt = (int)session.getAttribute("u_id_cnt");%>
<script>alert(<%=u_id_cnt%>)</script>
<%
  for(String c:u_id_list){
    out.println(c);
%>
<script>create_button(<%=c%>);</script>
<%
  }
%>
<%--<%--%>
<%--  for(int i = 0;i<u_id_cnt;i++){--%>
<%--%>--%>
<%--<script>--%>
<%--  alert(<%=u_id_list.get(i)%>);--%>
<%--  create_button(<%=u_id_list.get(i)%>);--%>
<%--</script>--%>
<%--<%--%>
<%--  }--%>
<%--%>--%>
<%--<script>--%>
<%--  location.href="TimeTable.html";--%>
<%--</script>--%>
</body>
</html>