<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
    <c:url var="mainJsUrl" value="/js/script.js"/>
    <c:url var="jqueryJsUrl" value="/js/jquery.js"/>
    <script type="text/javascript" src="${jqueryJsUrl}"></script>
    <script type="text/javascript" src="${mainJsUrl}"></script>
    <title>Link Finder</title>
</head>
<body >
    <h1>Программа по подсчету количества ссылок на WEB-странице</h1>
    <form>
            <div id="linkLabelDiv"><label for="linkInput" id="inputLabel">Анализируемая страница</label></div>
        <div id="linkInputDiv"><input id="linkInput" name="linkInput" required></div>
        <div class="buttonDiv"><input type="button" id="linkButton" value="Анализировать"></div>
    </form>
    <c:url var="waitGifUrl" value="/img/wait.gif"/>
    <div id="waitDiv"><img src="${waitGifUrl}" /> Идет обработка...</div>
    <div id="linkTableDiv"><label for="linkTable" id="tableLabel">Обнаруженные ссылки</label></div>

    <div id="tableDiv">
        <table id="linkTable">
            <thead id="rowTitle">
                <th style="padding-right:20px">№</th>
                <th>Имя ссылки</th>
                <th>Ссылка</th>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
    <div class="buttonDiv"><input type="button" id="eraseButton" value="Очистить"></div>
 </body>
</html>
