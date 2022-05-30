<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Home</title>
  <link rel="stylesheet" href="styles/style.css" type="text/css">
</head>
<body class="container">
<header>
  <h1 class="header1">Welkom bij de haardracht</h1>
  <jsp:include page="navigatie.jspf"/>
</header>
<br/>
<main>
  <article class="random-tekst">
    <p>Hallo, Welkom bij de proefsite van de kapperszaak de Haardracht voor het vak Web Ontwikkeling 2 die is gemaakt door Alessio Winkel</p>
    <p>Op deze site kunt u uw afspraken maken voor een der welk persoon die u kent voor een kappersbeurt.</p>
    <p>Wilt u het aantal afspraken dat gemaakt zijn zien? <a href="Controller?command=laatZien">Ja</a>
      /<a href="Controller?command=laatNietZien">Nee</a></p>
    <p class="aantalPersonen">Aantal afspraken dat gemaakt zijn: ${afsprakenDB.sequence}</p>

    <c:if test="${!(not empty zoekTermen)}">
      <p>Geen zoekopdrachten</p>
    </c:if>
  </article>

  <section class="tabel-box">
    <table>

      <c:if test="${not empty zoekTermen}">
        <tr>
          <th>Opgezochte namen</th>
        </tr>

        <c:forEach items="${zoekTermen}" var="zoekTerm">
          <tr>
            <td>${zoekTerm}</td>
          </tr>
        </c:forEach>
      </c:if>



    </table>
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
