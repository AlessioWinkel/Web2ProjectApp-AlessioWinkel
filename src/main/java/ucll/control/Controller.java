package ucll.control;

import ucll.DomainException;
import ucll.domain.db.AfsprakenDB;
import ucll.domain.model.Afspraak;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private AfsprakenDB afsprakenDB = new AfsprakenDB();
    ArrayList<String> zoekTermen = new ArrayList<String>();

    public Controller() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination;

        String command = request.getParameter("command");

        if (command == null) {
            destination = getIndex(request);
        } else {
            switch (command) {
                case "VoegToeForm":
                    destination = getVoegToeForm(request, response);
                    break;
                case "pasAan":
                    destination = getPasAan(request, response);
                    break;
                case "aanpassen":
                    destination = aanpassen(request, response);
                    break;
                case "Overzicht":
                    destination = getOverzicht(request,response);
                    break;
                case "VoegToe":
                    destination = getVoegToe(request,response);
                    break;
                case "verwijder":
                    destination = getDelete(request,response);
                    break;
                case "zoek":
                    destination = getZoek(request,response);
                    break;
                case "zoekPagina":
                    destination = getZoekPagina(request,response);
                    break;
                case "zoekConfirmatie":
                    destination = getZoekConfirmatie(request,response);
                    break;
                case "verwijderConfirmatie":
                    destination = getVerwijderConfirmatie(request,response);
                    break;
                case "laatZien":
                    destination = laatBerekendeWaardeZienCookie(request, response, "Ja");
                    break;
                case "laatNietZien":
                    destination = laatBerekendeWaardeZienCookie(request, response, "Nee");
                    break;
                case "resultaat":
                    destination = resultaat(request, response);
                    break;
                default:
                    destination = getIndex(request);
                    break;
            }
        }
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }

    private String aanpassen(HttpServletRequest request, HttpServletResponse response) {
        String naam = request.getParameter("naam");
        Afspraak afspraak = afsprakenDB.vind(naam);
        request.setAttribute("afspraak",afspraak);
        afsprakenDB.remove(naam);

        return "pasAan.jsp";
    }


    private String getZoekConfirmatie(HttpServletRequest request, HttpServletResponse response) {

        return "zoekConfirmatie.jsp";
    }
    private String getVerwijderConfirmatie(HttpServletRequest request, HttpServletResponse response) {

        return "verwijderConfirmatie.jsp";
    }

    private String getZoekPagina(HttpServletRequest request, HttpServletResponse response) {
        return "zoekPagina.jsp";
    }

    private String resultaat(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("afsprakenDB",afsprakenDB);
        String naam = request.getParameter("naam");
        Afspraak afspraak = afsprakenDB.vind(naam);
        request.setAttribute("afspraak",afspraak);
        return "resultaat.jsp";
    }

    private String getVoegToeForm(HttpServletRequest request, HttpServletResponse response) {
        return "VoegToe.jsp";
    }
    private String getZoek(HttpServletRequest request, HttpServletResponse response ) {
        ArrayList<String> errors = new ArrayList<String>();
        Afspraak afspraak = new Afspraak();

        vind(afspraak,request,errors);

        if (errors.size() == 0) {
            try {
                afspraak = afsprakenDB.vind(afspraak.getNaam());
                String naam = request.getParameter("naam");
                HttpSession session = request.getSession();
                session.setAttribute("zoekTermen", zoekTermen);
                zoekTermen.add(naam);
                return getZoekConfirmatie(request,response);
            } catch (DomainException exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);

        return "zoekPagina.jsp";

    }

    private String getOverzicht(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("afsprakenDB",afsprakenDB);
        request.setAttribute("afspraken", afsprakenDB.getAfspraken());

        String meesteMessage = afsprakenDB.getMeestePersonen();
        request.setAttribute("meestePersonen", meesteMessage);

        int aantalAfspraken = afsprakenDB.aantalAfsprakenGemaakt();
        request.setAttribute("aantalAfspraken", aantalAfspraken);

        return "Overzicht.jsp";
    }

    private String getIndex(HttpServletRequest request) {
        request.setAttribute("afsprakenDB", afsprakenDB);
        int aantalAfspraken = afsprakenDB.aantalAfsprakenGemaakt();
        request.setAttribute("aantalAfspraken", aantalAfspraken);
        Afspraak afspraak = (Afspraak) request.getSession().getAttribute("afspraak");
        ArrayList<String> zoekTermen = (ArrayList<String>) request.getSession().getAttribute("zoekTermen");

        String destination;

        Cookie cookie = getCookieWithKey(request, "berekendeWaarde");
        if (cookie == null || cookie.getValue().equals("Ja")) {
            destination = "indexCookie.jsp";
        } else {
            destination = "index.jsp";
        }

        return destination;
    }

    private String getDelete(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("afsprakenDB",afsprakenDB);
        String naam = request.getParameter("naam");
        afsprakenDB.remove(naam);
        return this.getOverzicht(request,response);

    }

    private String getPasAan(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<String>();
        String naam = request.getParameter("naam");

        Afspraak afspraak = new Afspraak();

        setNaam(afspraak,request,errors);
        setTelefoonnummer(afspraak,request,errors);
        setAantalPersonen(afspraak,request,errors);

        request.setAttribute("afspraak",afspraak);


        if (errors.size() == 0) {
            try {
                afsprakenDB.add(afspraak);
                return getOverzicht(request,response);
            } catch (DomainException exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "pasAan.jsp";
    }


    private String getVoegToe(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<String>();

        Afspraak afspraak = new Afspraak();
        setNaam(afspraak,request,errors);
        setTelefoonnummer(afspraak,request,errors);
        setAantalPersonen(afspraak,request,errors);

        if (errors.size() == 0) {
            try {
                afsprakenDB.add(afspraak);
                return getOverzicht(request,response);
            } catch (DomainException exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "VoegToe.jsp";

    }

    private void vind(Afspraak afspraak, HttpServletRequest request, ArrayList<String> errors) {
        String naam = request.getParameter("naam");
        boolean naamHasErrors = false;
        try {
            request.setAttribute("naamPreviousValueZoek", naam);
            afspraak.setNaam(naam);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            naamHasErrors = true;
        } finally {
            request.setAttribute("naamHasErrorsZoek", naamHasErrors);
        }
    }

    private void setNaam(Afspraak afspraak, HttpServletRequest request, ArrayList<String> errors) {
        String naam = request.getParameter("naam");
        boolean naamHasErrors = false;
        try {
            request.setAttribute("naamPreviousValue", naam);
            afspraak.setNaam(naam);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            naamHasErrors = true;
        } finally {
            request.setAttribute("naamHasErrors", naamHasErrors);
        }
    }

    private void setAantalPersonen(Afspraak afspraak, HttpServletRequest request, ArrayList<String> errors) {
        String aantalPersonen = request.getParameter("aantalPersonen");
        boolean aantalPersonenHasErrors = false;
        try {
            request.setAttribute("aantalPersonenPreviousValue", aantalPersonen);
            afspraak.setAantalPersonen(Integer.parseInt(aantalPersonen));
        }
        catch (NumberFormatException exc) {
            errors.add("Vul een nummer in voor het aantal personen.");
            aantalPersonenHasErrors = true;
        }
        catch (DomainException exc) {
            errors.add(exc.getMessage());
            aantalPersonenHasErrors = true;
        }
        finally {
            request.setAttribute("aantalPersonenHasErrors", aantalPersonenHasErrors);
        }
    }

    private void setTelefoonnummer(Afspraak afspraak, HttpServletRequest request, ArrayList<String> errors) {
        String telefoonNummer = request.getParameter("telefoonNummer");
        boolean telefoonNummerHasErrors = false;
        try {
            request.setAttribute("telefoonNummerPreviousValue", telefoonNummer);
            afspraak.setTelefoonNummer(telefoonNummer);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            telefoonNummerHasErrors = true;
        } finally {
            request.setAttribute("telefoonNummerHasErrors", telefoonNummerHasErrors);
        }
    }

    private String laatBerekendeWaardeZienCookie(HttpServletRequest request, HttpServletResponse response,String antwoord) {
        String destination;
        request.setAttribute("afsprakenDB", afsprakenDB);
        int aantalAfspraken = afsprakenDB.aantalAfsprakenGemaakt();
        request.setAttribute("sequence", aantalAfspraken);

        Cookie cookie = new Cookie("berekendeWaarde",antwoord);
        response.addCookie(cookie);
        cookie.setMaxAge(-1);

        if (antwoord == null || antwoord.equals("Ja")) {
            request.setAttribute("requestCookie","Ja");
            destination = "indexCookie.jsp";
        } else {
            request.setAttribute("requestCookie", "Nee");
            destination = "index.jsp";
        }

        return destination;
    }

    private Cookie getCookieWithKey(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies
        ) {
            if (cookie.getName().equals(key))
                return cookie;
        }
        return null;
    }

}