package Model;

public class UserPunteggio {
    private String username;
    private String punteggio;

    public UserPunteggio(String username,String punteggio){
        this.username=username;
        this.punteggio=punteggio;
    }
    public String getUsername(){

        return this.username;
    }
    public String getPunteggio(){
        return this.punteggio;
    }

}
