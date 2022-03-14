<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> Titulo </title>
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
<nav class="navbar navbar-expand-lg navbar-light bg-light d-flex justify-content-end p-2 m-2">
    <a class="navbar-brand flex-grow-1 fs-2" href="/home">Sports</a>
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
<main role="main" class="mx-auto w-75">
    <h2 class="text-center"> Editar evento </h2>
    <form:form action="/create-event" method="post" modelAttribute="newEvent">
        <div class="mb-3 form-group">
            <form:label path="eventName">Nombre del Evento:</form:label>
            <form:input path="eventName" class="form-control mb-3"  />
            <form:errors path="eventName" class="text-danger mb-3 d-inline-block" />
        </div>
        <div class="mb-3 form-group">
            <form:label path="locationName">Lugar:</form:label>
            <form:input path="locationName" class="form-control mb-3"  />
            <form:errors path="locationName" class="text-danger mb-3 d-inline-block" />
        </div>
        <div class="mb-3 form-group">
            <form:label path="attendees">Asistentes:</form:label>
            <form:input path="attendees" class="form-control mb-3" type="number" />
            <form:errors path="attendees" class="text-danger mb-3 d-inline-block" />
        </div>
        <div class="mb-3 form-group">
            <form:label path="eventDate">Fecha de evento:</form:label>
            <form:input path="eventDate" class="form-control mb-3" type="datetime-local" />
            <form:errors path="eventDate" class="text-danger mb-3 d-inline-block" />
        </div>
            <form:input path="id" class="form-control mb-3" type="hidden" value="${newEvent.getId()}"/>
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