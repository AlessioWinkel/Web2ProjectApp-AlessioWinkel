<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 3/05/2022
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pas afspraak aan</title>
    <link rel="stylesheet" href="styles/style.css" type="text/css">
</head>
<body class="container">
<header>
    <h1 class="header1" id="header1">Pas uw afspraak aan</h1>
    <jsp:include page="navigatie.jspf"/>
</header>
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
    <section class="contact-form-box">
        <div class="container-contact">
            <section class="contactform">
                <form method="POST" action="Controller?command=pasAan" novalidate>
                    <h2>Pas afspraak aan</h2>
                    <div class="inputbox">
                        <input type="text" name="naam" id="naamVak"
                               value="${param.naam}" class="${naamHasErrors? 'error' : ''}">
                        <span>Volledige naam</span>
                    </div>
                    <div class="inputbox">
                        <input type="text" name="aantalPersonen" id="aantalPersonenVak"
                               value="${param.aantalPersonen}" class="${aantalPersonenHasErrors? 'error' : ''}">
                        <span>Aantal Personen</span>
                    </div>
                    <div class="inputbox">
                        <input type="text" name="telefoonNummer" id="telefoonNummerVak"
                               value="${param.telefoonNummer}" class="${telefoonNummerHasErrors? 'error' : ''}">

                        <span>Telefoonnummer</span>
                    </div>
                    <div class="inputbox">
                        <input type="submit" name="knop" id="verstuur" value="Verstuur">
                    </div>
                </form>
            </section>
        </div>
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