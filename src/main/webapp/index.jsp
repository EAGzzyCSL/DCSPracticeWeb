<%@ page contentType="text/html;charset=utf-8"%>
<jsp:useBean id="myTest" class="test.Test" />
<html>
<body>
<%
out.print(myTest.getConnection());
%>
</body>
</html>
