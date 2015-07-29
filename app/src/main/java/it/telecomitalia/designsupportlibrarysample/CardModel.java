package it.telecomitalia.designsupportlibrarysample;

/**
 * Created by octavian on 7/28/15.
 */
public class CardModel {

    private int identifier;
    private String title;

    public CardModel(int identifier, String title) {
        this.identifier=identifier;
        this.title =title;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
