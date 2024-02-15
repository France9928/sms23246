package it.uniba.dib.sms23246.ui.shop;

public class Prodotto {
    private String nomeProdotto;
    private String categoriaProdotto;
    private double costo;
    private String data;

    public Prodotto() {
    }

    public Prodotto(String nomeProdotto, String categoriaProdotto, double costo, String data) {
        this.nomeProdotto = nomeProdotto;
        this.categoriaProdotto = categoriaProdotto;
        this.costo = costo;
        this.data = data;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public String getCategoriaProdotto() {
        return categoriaProdotto;
    }

    public void setCategoriaProdotto(String categoriaProdotto) {
        this.categoriaProdotto = categoriaProdotto;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "nomeProdotto='" + nomeProdotto + '\'' +
                ", categoriaProdotto='" + categoriaProdotto + '\'' +
                ", costo=" + costo +
                ", data='" + data + '\'' +
                '}';
    }
}
