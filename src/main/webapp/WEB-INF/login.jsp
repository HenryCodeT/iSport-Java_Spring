<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SportLand</title>
    <!-- Balsamiq -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Balsamiq+Sans&display=swap" rel="stylesheet">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <!-- Estilos Locales -->
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>
<div class="border-form p-3 col-5 mx-auto my-5" style="min-width: 390px;">
    <h1 class="text-glacierblue text-center">Sports</h1>
    <h4 class="text-glacierblue text-center">Buscador y organizador de juegos deportivos gratuitos</h4>
    <h2 class="text-glacierblue text-center">Iniciar sesión</h2>
            <form:form action="/login" method="post" modelAttribute="newLogin">
                <div class="mb-3 form-group text-glacierblue" >
                    <form:label path="email" for="email1">Email:</form:label>
                    <form:input path="email" class="form-control mb-3"  id="email1"/>
                    <form:errors path="email" class="text-danger mb-3 d-inline-block" />
                </div>
                <div class="mb-3 form-group text-glacierblue">
                    <form:label path="password" for="password1">Password:</form:label>
                    <form:input path="password" class="form-control mb-3" id="password1" type="password" />
                    <form:errors path="password" class="text-danger mb-3 d-inline-block" />
                </div>
                <button class="btn bg-glacierblue"  type="submit">Iniciar sesión</button>
            </form:form>
    <div class="d-flex justify-content-between my-3">
        <p class="text-glacierblue">No tengo una cuenta</p>
        <a class="btn bg-glacierblue" href="/register">Registro</a>
    </div>
</div>

<!-- jQuery (No necesario en Bootstrap 5) -->
<script src="/webjars/jquery/jquery.min.js"></script>
<!--Bootstrap -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- Javascript Local -->
<script src="/js/app.js"></script>
</body>
</html>