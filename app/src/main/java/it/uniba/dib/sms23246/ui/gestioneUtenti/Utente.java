package it.uniba.dib.sms23246.ui.gestioneUtenti;

public class Utente {
    private String userId;
    private String nome;
    private String cognome;

    public Utente(String userId, String nome, String cognome) {
        this.userId = userId;
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getUserId() {
        return userId;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }
}
