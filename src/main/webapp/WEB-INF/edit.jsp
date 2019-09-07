<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<c:url value="/ships/{id}" var="var"/>
<form action="${var}" method="POST">
    <table>
        <td>
            <input type="hidden" name="id" value="${ship.id}">
            <label for="name">Name</label>
            <input type="text" name="name" id="name" value="${ship.name}">
            <label for="planet">Planet</label>
            <input type="text" name="planet" id="planet" value="${ship.planet}">
            <label for="shipType">Ship Type</label>
            <input type="text" name="shipType" id="shipType" value="${ship.shipType}">
        </td>
        <td>
            <label for="prodDate">Prod. Date</label>
            <input type="text" name="prodDate" id="prodDate" value="${ship.prodDate}">
            <label for="isUsed">Used</label>
            <input type="text" name="isUsed" id="isUsed" value="${ship.isUsed}">
            <label for="speed">Speed</label>
            <input type="text" name="speed" id="speed" value="${ship.speed}">
        </td>
        <td>
            <label for="crew">Crew size</label>
            <input type="text" name="crew" id="crew" value="${ship.crewSize}">
            <label for="rating">Rating</label>
            <input type="text" name="rating" id="rating" value="${ship.rating}" disabled>
            <input type="submit" value="Edit film">
        </td>
    </table>
</form>
</body>
</html>
