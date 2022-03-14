<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
    <!-- Estilos Locales -->
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<!-- //// NAVBAR /////////////////////////////////////////// -->
<nav class="navbar navbar-expand-lg navbar-light bg-light d-flex justify-content-end p-2 m-2">
    <a class="navbar-brand flex-grow-1 fs-2" href="/home">Sports</a>
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
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
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
    <h3>${event.getEventName()}</h3>
    <hr>
    <c:choose>
        <c:when test="${event.getCreator() != loggedInUser}">
            <h5>Creador del evento:</h5>
            <div class="w-75 mx-auto">
                <h5>
                    <a href="/user/${event.getCreator().getId()}">${event.getCreator().getUserName()} ${event.getCreator().getUserLastName()}</a>
                </h5>
            </div>
            <hr>
        </c:when>
    </c:choose>
    <h5>Fecha y Hora:</h5>
    <div class="w-75 mx-auto">
        <p><fmt:formatDate pattern="EEEE dd-MM-yyy" value="${event.getEventDate()}"/></p>
        <p><fmt:formatDate pattern="hh:mm aaa" value="${event.getEventDate()}"/></p>
    </div>
    <hr>
    <h5>Participantes:</h5>
    <div class="w-75 mx-auto">
        <table class="table table-bordered border-dark">
            <thead>
            <tr class="text-center">
                <th scope="col">Nombre y Apellido</th>
                <th scope="col">Email</th>
                <th scope="col"># Eventos creados</th>
                <th scope="col"># Eventos en el que Participa</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${event.getEventUsers()}">
                <tr class="text-center">
                    <td>${user.getUserName()} ${user.getUserLastName()}</td>
                    <td>${user.getEmail()}</td>
                    <td>${user.getCreatorEvents().size()}</td>
                    <td>${user.getUserEvents().size()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <hr>
    <c:choose>
        <c:when test="${event.getEventUsers().indexOf(loggedInUser)!= -1}">
            <div class="w-75 mx-auto">
                <form:form action="/create-message" method="post" modelAttribute="message">
                    <div class="mb-3 form-group">
                        <form:label path="content">Dejar un mensaje:</form:label>
                        <form:input path="content" class="form-control mb-3"/>
                        <form:errors path="content" class="text-danger mb-3 d-inline-block"/>

                        <form:input path="event" class="form-control mb-3" type="hidden" value="${event.getId()}"/>
                        <form:errors path="event" class="text-danger mb-3 d-inline-block"/>

                        <form:input path="author" class="form-control mb-3" type="hidden"
                                    value="${loggedInUser.getId()}"/>
                        <form:errors path="author" class="text-danger mb-3 d-inline-block"/>
                    </div>
                    <button class="btn bg-glacierblue text-center" type="submit">mensaje</button>
                </form:form>
            </div>
        </c:when>
    </c:choose>
    <hr>
    <h5>Mensajes:</h5>
    <div class="w-75 mx-auto border border-dark border-2 m-2 p-2">
        <c:forEach var="message" items="${event.getMessages()}">
            <p>${message.getAuthor().getUserName()}: <br> ${message.getContent()} <span
                    class="fs-6 text-black-50"><fmt:formatDate pattern="EEEE dd-MM-yyy hh:mm aaa"
                                                               value="${message.getCreatedAt()}"/></span></p>
        </c:forEach>
    </div>
</main>


<!-- jQuery (No necesario en Bootstrap 5) -->
<script src="/webjars/jquery/jquery.min.js"></script>
<!--Bootstrap -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- Javascript Local -->
<script src="/js/app.js"></script>
</body>
</html>