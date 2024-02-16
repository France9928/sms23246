package it.uniba.dib.sms23246.ui.shop;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Prodotto implements Serializable {
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

    // Metodo per convertire l'oggetto Prodotto in una mappa (Map) per Firebase
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nomeProdotto", nomeProdotto);
        result.put("categoriaProdotto", categoriaProdotto);
        result.put("costoProdotto", costo);
        result.put("dataProdotto", data);
        return result;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(nomeProdotto);
        out.writeObject(categoriaProdotto);
        out.writeDouble(costo);
        out.writeObject(data);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        nomeProdotto = (String) in.readObject();
        categoriaProdotto = (String) in.readObject();
        costo = in.readDouble();
        data = (String) in.readObject();
    }

}
