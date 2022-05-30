<%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 4/05/2022
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zoek Confirmatie</title>
    <link rel="stylesheet" href="styles/style.css" type="text/css">
    <jsp:include page="navigatie.jspf"/>
</head>
<body>
<main>
    <section class="random-tekst">
        <h2>Bevestiging</h2>
        <p>We hebben de afspraak met naam ${param.naam} voor je opgezocht.</p>
        <p><a href="Controller?command=resultaat&naam=${param.naam}" id="resultaat">
            Bekijk het resultaat.
        </a></p>
        <p><a href="Controller?command=Overzicht">Cancel</a> indien je niet meer geïnteresseerd bent.</p>
    </section>

</main>

</body>
</html>
