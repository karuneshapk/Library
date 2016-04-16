<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%--@elvariable id="book" type="servlets.Books"--%>  <%--для отображения (посказки) методов параметра "book": ${book.author}--%>

  <head>
    <meta http-equiv="content-type" content="text/html: charset=utf-8" />
    <style>
       
        @import URL("../../css/table.css");
    </style>
    <title>Library</title>
    <link rel="stylesheet" type="text/css" href="../../css/visual.css" />
  </head> 

  <body>
  <%@ include file="../../html/header.html" %>

    <div id="main">
      <h1>All books in library:</h1>
      <p>
        <table border="1" style="width: 700px;max-width: 700px;max-height: 800px;overflow: scroll">
            <tr>
                <th><h2>НАЗВАНИЕ КНИГИ</h2></th>
                <th><h2>АВТОР</h2></th>
                <th><h2>АННОТАЦИЯ</h2></th>
            </tr>

            <c:forEach items="${books}" var="book">
                <tr>
                     <th><h2>${book.name}</h2></th>
                     <th><h2>${book.author}</h2></th>
                     <th><h2>${book.description}</h2></th>
                </tr>
             </c:forEach>

        </table>
        <p></p>

<p><a href="/servlets/addBooks"><img src="../../image/buttonAddBook.png"></a></p>
<p><a href="/servlets/login"><img src="../../image/buttonLogout.png"></a></p>

      </p>
    </div>

    <%@ include file="../../html/footer.html" %>


  </body>
</html>


