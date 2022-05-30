<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ucll.domain.model.Afspraak" %><%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 4/05/2022
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zoekresultaat</title>
    <link rel="stylesheet" href="styles/style.css" type="text/css">
</head>
<header>
    <h1 class="header1">Zoekresultaat</h1>
    <jsp:include page="navigatie.jspf"/>
</header>
<body>
<main>
    <article class="random-tekst">
        <c:choose>
            <c:when test="${afspraak != null}">
                <p>We vonden volgende afspraak met naam ${param.naam}:</p>

                <ul>
                    <li>Naam: ${afspraak.naam}</li>
                    <li>Aantal personen: ${afspraak.aantalPersonen}</li>
                    <li>Telefoonnummer: ${afspraak.telefoonNummer}</li>
                </ul>
            </c:when>
            <c:otherwise>
                <p>Helaas, we konden geen afspraak met naam ${param.naam} vinden. </p>
            </c:otherwise>
        </c:choose>

    </article>
</main>

</body>
</html>
