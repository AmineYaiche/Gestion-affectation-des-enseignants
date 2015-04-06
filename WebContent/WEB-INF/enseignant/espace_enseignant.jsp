<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/WEB-INF/head.jsp"></c:import>
</head>
<body>

<c:url value="/enseignant/demandeEnCours" var="URLdemandeEnCours"></c:url>
<c:url value="/enseignant/nouvelleDemande" var="URLNouvelleDemande"></c:url>
<c:url value="/enseignant/demandesAcceptees" var="URLDemandesAcceptees"></c:url>


<h1>Espace enseignant</h1>

<a href="${URLdemandeEnCours}">Les demande en cours</a><br/>
<a href="${URLNouvelleDemande }">Nouvelle demande</a><br/>
<a href="${URLDemandesAcceptees }">Demandes acceptées </a><br/>



</body>
</html>