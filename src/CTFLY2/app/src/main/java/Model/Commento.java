package Model;

import com.google.gson.annotations.SerializedName;

public class Commento {
    @SerializedName("id")
    private int id;
    @SerializedName("spoiler")
    private Boolean spoiler;
    @SerializedName("data")
    private String data;
    @SerializedName("testo")
    private String testo;
    @SerializedName("segnalato")
    private Boolean segnalato;
    @SerializedName("autore")
    private Utente autore;
    @SerializedName("challenge")
    private Challenge challenge;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getSpoiler() {
        return spoiler;
    }

    public void setSpoiler(Boolean spoiler) {
        this.spoiler = spoiler;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Boolean getSegnalato() {
        return segnalato;
    }

    public void setSegnalato(Boolean segnalato) {
        this.segnalato = segnalato;
    }

    public Utente getAutore() {
        return autore;
    }

    public void setAutore(Utente autore) {
        this.autore = autore;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
}
