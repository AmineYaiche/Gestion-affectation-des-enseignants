<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<c:import url="/WEB-INF/head.jsp"></c:import>
</head>
<body>



Espace Ensignant

<form method="POST">
	Login : <input type="text" name="login" /><br/>
	Mot de passe : <input type="password" name="pwd" /> <br/>
	<input type="submit" value="Connecter" />
</form>

<br/><br/><br/><br/><br/><br/><br/>

Espace d'administration

<form method="POST">
	Login : <input type="text" name="login" /><br/>
	Mot de passe : <input type="password" name="pwd" /> <br/>
	<input type="submit" value="Connecter" />
</form>












</body>
</html>