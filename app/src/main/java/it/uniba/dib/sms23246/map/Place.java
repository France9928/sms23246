package it.uniba.dib.sms23246.map;

public class Place {
    private String name;
    private String rating;
    private String link;

    public Place(String name, String rating, String link) {
        this.name = name;
        this.rating = rating;
        this.link = formatLink(link);
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getLink() {
        return link;
    }

    private String formatLink(String rawLink) {
        // Aggiunge uno schema "http://" se mancante
        if (!rawLink.startsWith("http://") && !rawLink.startsWith("https://")) {
            return "http://" + rawLink;
        }
        return rawLink;
    }
}