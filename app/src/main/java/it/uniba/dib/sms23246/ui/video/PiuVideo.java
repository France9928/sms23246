package it.uniba.dib.sms23246.ui.video;

public class PiuVideo{
    private String name;
    private String link;

    public PiuVideo(String name, String link) {
        super();
        this.name = name;
        this.link = formatLink(link);
    }

    public String getName() {
        return name;
    }


    public String getLink() {
        return link;
    }

    private String formatLink(String rawLink) {
        // Aggiunge uno schema "http://" se mancante e
        if (!rawLink.startsWith("http://") && !rawLink.startsWith("https://")) {
            return "http://" + rawLink;
        }
        return rawLink;
    }
}
