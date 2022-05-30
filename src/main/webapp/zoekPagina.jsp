<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 4/05/2022
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zoek je persoon</title>
    <link rel="stylesheet" href="styles/style.css" type="text/css">
</head>
<header>
    <h1 class="header1">Zoek uw afspraak</h1>
    <jsp:include page="navigatie.jspf"/>
</header>
<body>
<main>
    <c:if test="${not empty errors}">
        <div id="error" >
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <section class="random-tekst">
        <p>Geef de naam van de persoon waarvan je de afspraak wilt zoeken: </p>
        <form action="Controller?command=zoek" novalidate>
            <p>
                <label for="naamZoek">Naam</label>
                <input type="text" id="naamZoek" name="naam" required value="${naamPreviousValueZoek}"
                       class="${naamHasErrorsZoek? 'error' : ''}">
            </p>
            <input type="hidden" name="command" value="zoek">
            <p>
                <input type="submit" value="zoek" id="zoekKnop">
            </p>
        </form>
    </section>

</main>

<footer class="footer" role="contentinfo">
    <section class="footercontainer">
        <h2>De haardracht</h2>
        <p>Neerstraat 7, 3980 Tessenderlo</p>
        <p>Email: dehaardracht@hotmail.com</p>

    </section>
</footer>

</body>
</html>
