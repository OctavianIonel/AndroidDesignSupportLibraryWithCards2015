package it.octavianionel.designsupportlibrarysample;

/**
 * Created by octavian on 7/28/15.
 */
public class CardInfoModel extends CardModel {

    private int status      =1; //1 for "blue", 2 for "red" and 3 for "yellow"
    private String date;
    private String orarioPrenotazione;
    private String aula;
    private String indirizzo1;
    private String indirizzo2;

    public CardInfoModel(int identifier, String title) {
        super(identifier, title);
    }

    public CardInfoModel(int identifier, String title, String date, String orarioPrenotazione, String aula, String indirizzo1, String indirizzo2) {
        this(identifier, title);
        this.date=date;
        this.orarioPrenotazione=orarioPrenotazione;
        this.aula=aula;
        this.indirizzo1=indirizzo1;
        this.indirizzo2=indirizzo2;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrarioPrenotazione() {
        return orarioPrenotazione;
    }

    public void setOrarioPrenotazione(String orarioPrenotazione) {
        this.orarioPrenotazione = orarioPrenotazione;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getIndirizzo1() {
        return indirizzo1;
    }

    public void setIndirizzo1(String indirizzo1) {
        this.indirizzo1 = indirizzo1;
    }

    public String getIndirizzo2() {
        return indirizzo2;
    }

    public void setIndirizzo2(String indirizzo2) {
        this.indirizzo2 = indirizzo2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
