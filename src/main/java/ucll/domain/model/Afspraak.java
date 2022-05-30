package ucll.domain.model;

import ucll.DomainException;

public class Afspraak {

    private String naam,telefoonNummer;
    private int aantalPersonen,id;

    public Afspraak(String naam, String telefoonNummer, int aantalPersonen) {

        setNaam(naam);
        setTelefoonNummer(telefoonNummer);
        setAantalPersonen(aantalPersonen);
    }

    public Afspraak() {}

    public void setId(int id) {
        if (id <=0) {
            throw new DomainException("ID moet positief zijn");
        } else this.id = id;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.isEmpty()) {
            throw new DomainException("Naam mag niet leeg zijn.");
        }
        this.naam = naam;
    }

    public void setAantalPersonen(int aantalPersonen) {
        if (aantalPersonen <= 0 || aantalPersonen > 4) {
            throw new DomainException("Aantal moet positief zijn en mag niet meer dan 4 personen zijn.");
        }
        this.aantalPersonen = aantalPersonen;

    }

    public void setTelefoonNummer(String telefoonNummer) {
        if (telefoonNummer == null || telefoonNummer.isEmpty()) {
            throw new DomainException("Telefoonnummer mag niet leeg zijn.");
        }
        this.telefoonNummer = telefoonNummer;
    }

    public String getNaam() {return naam;}
    public int getAantalPersonen() {return aantalPersonen;}
    public String getTelefoonNummer() {
        return telefoonNummer;
    }
}