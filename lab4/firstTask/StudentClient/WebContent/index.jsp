<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.List" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
		<tr><th>Лабораторна робота №4</th></tr>
		<tr><th>
			<ol>
				<%
					List studens = (List)request.getAttribute("students"); 
					if(studens!=null)
					{
						for(int i=0;i<studens.size();i++)
						{
							out.println("<li>"+studens.get(i)+"</li>");
						}
					}
				%>
			</ol>  
		</th></tr>
	</table>
<form action="" method="GET">
	<input type="Submit" name="Submit">
</form>
</body>
</html>