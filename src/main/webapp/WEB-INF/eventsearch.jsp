<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> Buscar Evento </title>
    <!-- Balsamiq -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Balsamiq+Sans&display=swap" rel="stylesheet">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
    <!-- Estilos Locales -->
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<!-- //// NAVBAR /////////////////////////////////////////// -->
<nav class="navbar navbar-expand-lg navbar-dark bg-nav d-flex justify-content-end p-2 m-2">
    <a class="navbar-brand flex-grow-1 fs-2 text-warmgray" href="/home">SportLand</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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
                        <li><a class="dropdown-item" href="/user/edit/password">cambiar contrase??a</a></li>
                    </ul>
                </li>
            </ul>
        </form>
    </div>
    </div>
</nav>

<!-- //// MAIN AREA //////////////////////////////////////// -->
<main role="main" class="mx-auto w-75">
    <h2 class="text-center text-overcast">Buscar un Evento</h2>
    <table class="table table-bordered border-info">
        <thead>
        <tr class="text-center text-glacierblue">
            <th scope="col">Nombre de Evento</th>
            <th scope="col">Lugar</th>
            <th scope="col">Participantes</th>
            <th scope="col">Fecha y Hora</th>
            <th scope="col">Creador</th>
            <th scope="col">Accion</th>
        </tr>
        </thead>
        <tbody class="text-glacierblue">
            <c:forEach var="event" items="${events}">
                <c:choose>
                    <c:when test="${event.getEventUsers().indexOf(loggedInUser)== -1}">
                    <tr class="text-center">
                        <td><a class="btn btn-info" href="/event/${event.getId()}">${event.getEventName()}</a></td>
                        <td class="text-overcast">${event.getLocationName()}</td>
                        <td class="text-overcast">${event.getEventUsers().size()} / ${event.getAttendees()}</td>
                        <td class="text-overcast"><fmt:formatDate pattern = "E dd-MM-yyy hh:mm aaa" value = "${event.getEventDate()}" /></td>
                        <td> <a class="btn bg-overcast" href="/user/${event.getCreator().getId()}">${event.getCreator().getUserName()} ${event.getCreator().getUserLastName()}</a></td>
                        <td>
                            <c:choose>
                                <c:when test="${event.getEventUsers().size() == event.getAttendees()}">
                                    <p class="text-light">Lleno</p>
                                </c:when>
                                <c:otherwise>
                                    <a class="btn btn-primary" href="/events/add/${event.getId()}">Unirse</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    </c:when>
                </c:choose>
            </c:forEach>
        </tbody>
    </table>
</main>


<!-- jQuery (No necesario en Bootstrap 5) -->
<script src="/webjars/jquery/jquery.min.js"></script>
<!--Bootstrap -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- Javascript Local -->
<script src="/js/app.js"></script>
</body>
</html>