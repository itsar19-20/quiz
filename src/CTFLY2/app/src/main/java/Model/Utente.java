package Model;

import com.google.gson.annotations.SerializedName;

public class Utente {
    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("dataiscrizione")
    private String dataiscrizione;

    @SerializedName("password")
    private String password;

    @SerializedName("token")
    private String token;

    @SerializedName("immagine")
    private String immagine;

    @SerializedName("punteggio")
    private int punteggio;

    @SerializedName("nazionalita")
    private String nazionalita;

    @SerializedName("ultimoaccesso")
    private String ultimoaccesso;


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getDataiscrizione() {
        return dataiscrizione;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public String getImmagine() {
        return immagine;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public String getUltimoaccesso() {
        return ultimoaccesso;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataiscrizione(String dataiscrizione) {
        this.dataiscrizione = dataiscrizione;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public void setUltimoaccesso(String ultimoaccesso) {
        this.ultimoaccesso = ultimoaccesso;
    }
}
