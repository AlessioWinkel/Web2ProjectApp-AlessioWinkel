<%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 4/05/2022
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="styles/style.css" type="text/css">
</head>
<header>
    <h1 class="header1">Bevestiging verwijderen afspraak</h1>
    <jsp:include page="navigatie.jspf"/>
</header>
<body>
<main>
    <article class="random-tekst">
        <p>Bent u zeker dat u de afspraak wilt verwijderen?</p>
        <a href="Controller?command=verwijder&naam=${param.naam}" id="verwijderConfirmatie">
            Ja
        </a></p>
        <p>Cancel</p>

    </article>
</main>

</body>
</html>
