<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>JavaRush Internship</title>
    <link href="data:image/x-icon;base64,AAABAAEAEBAAAAEAIABoBAAAFgAAACgAAAAQAAAAIAAAAAEAIAAAAAAAAAQAABILAAASCwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAuYO8ALmPxAC5h8B4uYfCLLmDw4S5g8OMuYfCPLmHwISxi8QAvYPAAAAAAAAAAAAAAAAAAAAAAAC1f8QAwZfEAMGTxDC9k8FwvY/DLL2Pw/C9i8P8tYfD/LmLw/S9j8M8vZPBhMGTxDjBk8QAvYvEAAAAAADFo8QAxaPEDMWfxPzBm8bEwZvH3MGbx/y9l8f80aPH/Un7z/zls8v8wZfH/MGbx+DBm8bYxZ/FDMWjxBDFo8QA0bvEBMmrxZzFp8ewxafH/MWnx/zFp8f85bvH/o7v4/93m/f9UgvP/L2fx/zFp8f8xafH/MWnx7jJq8W8zbPECM23xJzNs8dozbPH/M2zx/zNs8f8vavH/apPz//v8/v+yyPr/NG3x/zJs8f8zbPH/M2zx/zNs8f8zbPHgM23xLTRv8UY0b/HyNG/x/zRv8f80b/H/MGzx/32i9P//////nLn4/zBs8f80b/H/NG/x/zRv8f80b/H/NG/x9jRv8U01cvJHNXLy8zVy8v81cvL/NXLy/zFw8v+Hq/X//////5a1+P8ycPL/NXLy/zVy8v81cvL/NXLy/zVy8vY1cvJONnXyRzZ18vM2dfL/NnXy/zV08v9TiPP/2+b8/97o/f9YjPT/NXTy/zZ18v82dfL/NnXy/zZ18v82dfL2NnXyTjh48kc4ePLzOHjy/zh48v82d/L/VIvy/9vm+v/e6P3/WY/0/zZ38v84ePL/OHjy/zh48v84ePL/OHjy9jh48k45fPJHOXzy8zl88v85fPL/OXzy/zV58v+JsPT//////5e6+P81efL/OXzy/zl88v85fPL/OXzy/zl88vY5fPJOOn/zRjp/8/I6f/P/On/z/zp/8/83ffP/gaz1//////+fwPn/N3zz/zp/8/86f/P/On/z/zp/8/86f/P2On/zTTuB8yc8gvPaPILz/zyC8/88gvP/OIDz/3Cj9P/7/P7/tc/7/z2D8/87gvP/PILz/zyC8/88gvP/PILz4DuB8y06fvMBPYTzZz2F8+w9hfP/PYXz/zyF8/9EifL/qMf2/9/q/P9dmfX/O4Tz/z2F8/89hfP/PYXz7j2E8288gfMCPYfzAD2G8wM+h/M/PojzsT6I8/c+iPP/PYjz/0GK8/9dm/P/Ro30/z6I8/8+iPP4Pojztj6H80M9hvMEPYbzAAAAAAA/jPMAP4n0AD+J9Aw/ivRcP4v0y0CL9Pw/i/T/Por0/z+L9P0/i/TPP4r0YT+J9A4/ivQAPorzAAAAAAAAAAAAAAAAAAAAAABBjfQAP430AECN9B5AjvSLQY704UGO9ONAjvSPQI30IT6O9ABBjfQAAAAAAAAAAAAAAAAA+B8AAOAHAACAAQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIABAADgBwAA+B8AAA==" rel="icon" type="image/x-icon" />
    <meta id="root" about="${pageContext.request.contextPath}">
    <link href="${pageContext.request.contextPath}/resources/bootstrap-4.3.1-dist/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap-4.3.1-dist/js/jq.js" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-4.3.1-dist/js/jq.js">
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-4.3.1-dist/js/bootstrap.js">
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts.js">
    </script>
</head>
<body>
<h1>List of Ships</h1>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>planet</th>
        <th>shipType</th>
        <th>Production Date</th>
        <th>is Used</th>
        <th>speed</th>
        <th>crew size</th>
        <th>rating</th>
    </tr>
    <c:forEach var="ship" items="${shipList}">
        <tr>
            <td>${ship.id}</td>
            <td>${ship.name}</td>
            <td>${ship.planet}</td>
            <td>${ship.shipType}</td>
            <td>${ship.prodDate}</td>
            <td>${ship.isUsed}</td>
            <td>${ship.speed}</td>
            <td>${ship.crewSize}</td>
            <td>${ship.rating}</td>
            <td>
                <a href="/edit/${ship.id}">edit</a>
                <a href="/delete/${ship.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
