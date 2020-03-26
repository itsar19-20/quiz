package Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Challenge {
    @SerializedName("titolo")
    private String titolo;
    @SerializedName("descrizione")
    private String descrizione;
    @SerializedName("creatore")
    private Utente creatore;
    @SerializedName("rating")
    private int rating;
    @SerializedName("punteggio")
    private int punteggio;
    @SerializedName("data")
    private String data;
    @SerializedName("flag")
    private String flag;
    @SerializedName("commenti")
    private List<Commento> commenti;

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

    public Utente getCreatore() {
        return creatore;
    }

    public void setCreatore(Utente creatore) {
        this.creatore = creatore;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<Commento> commenti) {
        this.commenti = commenti;
    }
}
