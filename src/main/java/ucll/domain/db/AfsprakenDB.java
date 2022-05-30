package ucll.domain.db;

import ucll.DomainException;
import ucll.domain.model.Afspraak;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class AfsprakenDB {
    private int sequence = 0;
    private static String afspraak;

    private String meestePersonen;
    private final ArrayList<Afspraak> afspraken = new ArrayList<>();


    public AfsprakenDB() {
        this.add(new Afspraak("Alessio", "0460456578", 2));
        this.add(new Afspraak("Willem", "0450956560", 3));
        this.add(new Afspraak("Lies", "0480766543", 4));
        this.add(new Afspraak("Robbe", "0460977260", 3));

    }

    public void add(Afspraak afspraak) {
        if (afspraak == null)
            throw new DomainException("Ongeldige afspraak om toe te voegen");
        if (vind(afspraak.getNaam()) != null)
            throw new DomainException("Je mag een afspraak maar één keer toevoegen");

        this.sequence++;
        afspraak.setId(sequence);
        afspraken.add(afspraak);
    }

    public void replace(String naam,int aantalPersonen, String telefoonNummer) {

        if (naam == null || naam.isEmpty())
            throw new DomainException("Naam mag niet leeg zijn");
        if (telefoonNummer == null || telefoonNummer.isEmpty())
            throw new DomainException("Telefoonnummer mag niet leeg zijn");
        if (aantalPersonen > 4 || aantalPersonen <= 0)
            throw new DomainException("Personen moet positief zijn en mag niet meer dan 4 zijn.");
        Afspraak afspraakreplaced = new Afspraak(naam,telefoonNummer,aantalPersonen);
        if (vind(naam) != null) {
            afspraken.remove(vind(naam));
            afspraken.add(afspraakreplaced);
        }
    }


    public Afspraak vind(String naam) {
        if (naam == null || naam.isEmpty())
            throw new DomainException("Naam mag niet leeg zijn");
        for (Afspraak afspraak : afspraken) {
            if (afspraak.getNaam().contains(naam))
                return afspraak;
        }
        return null;
    }

    public void remove(String naam) {
        Afspraak afspraak = this.vind(naam);
        afspraken.remove(afspraak);
        sequence--;
    }

    public int aantalAfsprakenGemaakt() {
        return sequence;
    }

    public void delete(String naam, String telefoonNummer) {
        for (int i = 0; i < afspraken.size(); i++) {
            if (afspraken.get(i).getNaam().equals(naam) && afspraken.get(i).getTelefoonNummer().equals(telefoonNummer)) {
                afspraken.remove(i);
            }
        }
    }

    public String getMeestePersonen() {
        int aantal = 0;
        for (int i = 0; i < afspraken.size(); i++) {
            if (afspraken.get(i).getAantalPersonen() > aantal) {
                aantal = afspraken.get(i).getAantalPersonen();
                meestePersonen = afspraken.get(i).getNaam();
            }
        }
        return meestePersonen;
    }

    public void setMeestePersonen(String meestePersonen) {
        this.meestePersonen = meestePersonen;
    }

    public String getAfspraak() {
        return this.afspraak;
    }

    public ArrayList<Afspraak> getAfspraken() {
        return afspraken;
    }

    private void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getSequence() {
        return sequence;
    }
}
