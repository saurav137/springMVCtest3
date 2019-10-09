
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KeepNote</title>
</head>
<body>
	<!-- Create a form which will have text boxes for Note ID, title, content and status along with a Send 
		 button. Handle errors like empty fields -->
<form action="saveNote" method ="POST">
<input type="text" name="noteId">
<input type="text" name="noteTittle">
<input type="text" name="noteContent">
<input type="text" name="noteStatus">
<input type="submit">
</form>


<form action="deleteNote" method ="POST">
<input type="text" name="noteId">
<input type="submit" value="Delete">
</form>
<table>

  <c:forEach items="${Value}" var="item">

    <tr>
      <td><c:out value="${item.getNoteId()}" /></td>
       <td><c:out value="${item.getNoteTittle()}" /></td>
       <td><c:out value="${item.getNoteContent()}" /></td>
        <td><c:out value="${item.getNoteStatus()}" /></td>
           <td><c:out value="${item.getCreatedAt()}" /></td>
    </tr>
  </c:forEach>
</table>

	<!-- display all existing notes in a tabular structure with Id, Title,Content,Status, Created Date and Action -->
</body>
</html>