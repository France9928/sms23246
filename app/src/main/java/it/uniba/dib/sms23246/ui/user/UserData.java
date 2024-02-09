package it.uniba.dib.sms23246.ui.user;

import androidx.annotation.NonNull;

public class UserData {
    private int id, eta, idPatologia;
    private String nome, cognome, username, luogoNascita, password;

    public UserData(int id, String nome, String cognome, int eta, String username, String password, String luogoNascita, int idPatologia) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.username = username;
        this.password = password;
        this.luogoNascita = luogoNascita;
        this.idPatologia = idPatologia;
    }
    public UserData(int id, String nome, String cognome, int eta, String luogoNascita, String email) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.luogoNascita = luogoNascita;
    }

    public UserData() {

    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public int getEta() {
        return eta;
    }

    public String getLuogoNascita(){
        return luogoNascita;
    }

    public int getIdPatologia() {return idPatologia;}

    public String getUsername() {return username;}

    public String getPassword() {return password;}

    @NonNull
    public String toString() {
        return "Name: " + nome + " " + cognome +
                "\nAge: " + eta +
                "\nBirthplace: " + luogoNascita;
        // Aggiungi altri campi se necessario
    }
}
