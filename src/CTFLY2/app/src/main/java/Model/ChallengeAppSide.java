package Model;

public class ChallengeAppSide {
    private String titolo;
    private String descrizione;
    private String usernameCreatore;
    private String categoria;
    private String rating;
    private String punteggio;
    private String data;

    public ChallengeAppSide(String titolo, String descrizione, String usernameCreatore, String categoria, String rating, String punteggio, String data) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.usernameCreatore = usernameCreatore;
        this.categoria = categoria;
        this.rating = rating;
        this.punteggio = punteggio;
        this.data = data;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUsernameCreatore() {
        return usernameCreatore;
    }

    public void setUsernameCreatore(String usernameCreatore) {
        this.usernameCreatore = usernameCreatore;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(String punteggio) {
        this.punteggio = punteggio;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
