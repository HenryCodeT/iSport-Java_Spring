<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> Cambiar contraseña </title>
    <!-- Balsamiq -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Balsamiq+Sans&display=swap" rel="stylesheet">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <!-- Estilos Locales -->
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<!-- //// NAVBAR /////////////////////////////////////////// -->
<nav class="navbar navbar-expand-lg navbar-dark bg-nav d-flex justify-content-end p-2 m-2">
    <a class="navbar-brand flex-grow-1 fs-2 text-warmgray" href="/home">SportLand</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse flex-grow-0" id="navbarSupportedContent">
        <form class="d-flex">
            <ul class="navbar-nav mb-2 mb-lg-0 align-content-center fs-5">
                <li class="nav-item">
                    <a class="nav-link" href="/home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/create-event">Crear nuevo evento</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/search-event">Buscar evento</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Editar perfil
                    </a>
                    <ul class="dropdown-menu " aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="/user/edit/profile">perfil</a></li>
                        <li><a class="dropdown-item" href="/user/edit/password">cambiar contraseña</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link " href="/logout">Cerrar sesion</a>
                </li>
            </ul>
        </form>
    </div>
    </div>
</nav>

<!-- //// MAIN AREA //////////////////////////////////////// -->
<main role="main" class="mx-auto w-50">
    <h2 class="text-center text-overcast"> Cambiar contraseña </h2>
    <form:form action="/edit/password" method="post" modelAttribute="passwordUser">
        <div class="mb-3 form-group text-glacierblue">
            <form:label path="oldPassword">Contraseña actual:</form:label>
            <form:input path="oldPassword" class="form-control mb-3"  />
            <form:errors path="oldPassword" class="text-danger mb-3 d-inline-block" />
        </div>
        <div class="mb-3 form-group text-glacierblue">
            <form:label path="password">Nueva Contraseña:</form:label>
            <form:input path="password" class="form-control mb-3"  />
            <form:errors path="password" class="text-danger mb-3 d-inline-block" />
        </div>
        <button class="btn bg-glacierblue text-center" type="submit">Editar</button>
    </form:form>
</main>

<!-- jQuery (No necesario en Bootstrap 5) -->
<script src="/webjars/jquery/jquery.min.js"></script>
<!--Bootstrap -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- Javascript Local -->
<script src="/js/app.js"></script>
</body>
</html>