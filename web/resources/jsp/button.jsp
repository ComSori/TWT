<%@ page import="Resources.timetable.TimetableVO_list" %>
<%@ page import="Resources.timetable.TimetableVO" %>
<%@ page import="Resources.timetable.Class_list" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="timetable.js"></script>
</head>
<body>
</body>
</html>
<%
//    TimetableVO_list vo_list = (TimetableVO_list) request.getAttribute("vo_list");
    TimetableVO_list vo_list = new TimetableVO_list();
    TimetableVO vo = new TimetableVO();
    ArrayList<Class_list> arr = new ArrayList<Class_list>();
    Class_list list = new Class_list("song", 1, "start", "end", "sysof", "123");
    arr.add(list);
    vo.setUid("1");
    vo.setTid("test");
    vo.setList(arr);
    vo_list.add(vo);
    String u_id = new String();
    for(int i = 0;i<vo_list.size();i++){
        u_id = vo_list.get(i).getUid();
%>
        <script>
            create_button(<%=u_id%>);
        </script>
<%
    }
%>