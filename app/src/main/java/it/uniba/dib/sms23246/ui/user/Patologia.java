package it.uniba.dib.sms23246.ui.user;

public class Patologia {
    private int id;
    private String nome;
    private Livello livello;

    public Patologia(int id, String nome, Livello livello) {
        this.id = id;
        this.nome = nome;
        this.livello = livello;
    }

    public int getId(){return id;}

    public String getNome() {
        return nome;
    }

    public Livello getLivello() {
        return livello;
    }

    public enum Livello {
        BASSO,
        MEDIO,
        ALTO;

        public int getValoreNumerico() {
            // Restituisci il valore numerico appropriato per il livello corrente
            switch (this) {
                case BASSO:
                    return 0;
                case MEDIO:
                    return 1;
                case ALTO:
                    return 2;
                default:
                    return 0; // Valore di default Basso
            }
        }
    }
}
