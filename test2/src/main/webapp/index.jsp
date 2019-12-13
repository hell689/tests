<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
    <title>Link Finder</title>
</head>
<body >
    <h1>Программа по подсчету количества ссылок на WEB-странице</h1>
    <form method="post">
        <input name="linkInput" required>
        <button type="submit">Анализировать</button>
    </form>
    <table>
        <thead>
            <th>№</th>
            <th>Имя ссылки</th>
            <th>Ссылка</th>
        </thead>
        <tbody>
            <c:forEach var="weblink" items="${links}" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>${weblink.name}</td>
                    <td>${weblink.link}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
 </body>
</html>
