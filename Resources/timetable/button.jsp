<%@ page import="Resources.timetable.TimetableVO_list" %>
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
    TimetableVO_list vo_list = (TimetableVO_list) request.getAttribute("vo_list");
    String u_id = new String();
    for(int i = 0;i<vo_list.size();i++){
        u_id = vo_list.get(i).getUid();
%>
        <script>
            create_button(<%=u_id%>);
            <%--document.getElementById(<%=u_id%>).onclick = function(){--%>
            <%--    <%--%>
            <%--        for(int j = 0; j < vo_list.get(i).getList().size();j++){--%>
            <%--            --%>
            <%--        }--%>
            <%--    %>--%>
            <%--    event.stopPropagation();--%>
            <%--};--%>
        </script>
<%
    }
%>