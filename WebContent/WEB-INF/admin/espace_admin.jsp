<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/WEB-INF/head.jsp"></c:import>
</head>
<body>

<c:url value="/admin/demandeEnAttente" var="URLdemandeEnAttente"></c:url>
<c:url value="/admin/demandesAcceptees" var="URLDemandesAcceptees"></c:url>
<c:url value="/admin/archive" var="URLArchive"></c:url>


<h1>Espace d'administration</h1>

<a href="${URLdemandeEnAttente}">Les demande en cours</a><br/>
<a href="${URLDemandesAcceptees }">Demandes acceptées </a><br/>
<a href="${URLArchive }">Affectation des années précédentes</a><br/>


</body>
</html>