package it.uniba.dib.sms23246.ui.gestioneUtenti;

public class Utente {
    private String userId;
    private String nome;
    private String cognome;
    private int eta;

    public Utente(String userId, String nome, String cognome, int eta) {
        this.userId = userId;
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
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

    public int getEta() {
        return eta;
    }
}
