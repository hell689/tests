<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
    <title>Link Finder</title>
</head>
<body>
<h1>Программа по подсчету количества ссылок на WEB-странице</h1>
    <div class="animDiv">
        <h2> Страница не найдена</h2>
        <p>Извините запрашиваемая, страница не найдена</p>
        <hr />
        <a href="<c:url value="/index.html"/>">Перейти на главную страницу приложения</a>
    </div>
</body>