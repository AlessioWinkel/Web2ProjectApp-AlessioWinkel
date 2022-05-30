<%@ page import="ucll.domain.model.Afspraak" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 3/05/2022
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Overzicht afspraken</title>
    <link rel="stylesheet" href="styles/style.css" type="text/css">
</head>
<body class="container">
<header>
    <h1 class="header1">Overzicht afspraken</h1>
    <jsp:include page="navigatie.jspf"/>
</header>
<br/>

<main>

    <section class="tabel-box">
        <table class="table">
            <thead>
            <tr>
                <th>Naam</th>
                <th>Telefoonnummer</th>
                <th>Aantal personen</th>
                <th>Pas aan</th>
                <th>Verwijder</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="afspraak"  items="${afspraken}">
                <tr>
                    <td>${afspraak.naam}</td>
                    <td>${afspraak.telefoonNummer}</td>
                    <td>${afspraak.aantalPersonen}</td>
                    <td>
                        <a href="Controller?command=aanpassen&naam=${afspraak.naam}&telefoonNummer=${afspraak.telefoonNummer}&aantalPersonen=${afspraak.aantalPersonen}">
                            Pas aan
                        </a>
                    </td>
                    <td>
                        <a href="Controller?command=verwijderConfirmatie&naam=${afspraak.naam}" id="verwijderKnop">X</a>
                    </td>
                </tr>

            </c:forEach>

            </tbody>

        </table>
        <p class="aantalPersonen">Aantal afspraken dat gemaakt zijn: ${afsprakenDB.sequence}</p>
        <p class="meestePersonen">Degene met de meeste personen is: ${afsprakenDB.meestePersonen}</p>
    </section>


</main>


<footer class="footer" role="contentinfo">
    <div class="footercontainer">
        <h2>De haardracht</h2>
        <p>Neerstraat 7, 3980 Tessenderlo</p>
        <p>Email: dehaardracht@hotmail.com</p>

    </div>
</footer>

</body>
</html>