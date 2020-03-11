package Model;

public class Friend {
    private String username;
    private String punteggio;
    public Friend (String username, String punteggio) {
        this.username=username;
        this.punteggio=punteggio;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPunteggio() {
        return this.punteggio;
    }
}
