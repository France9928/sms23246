package it.uniba.dib.sms23246.ui.user;

public class Patologia {
    private String nome;
    private int livello;

    public Patologia(String nome, int livello) {
        this.nome = nome;
        this.livello = livello;
    }

    public String getNome() {
        return nome;
    }

    public int getLivello() {
        return livello;
    }
}
