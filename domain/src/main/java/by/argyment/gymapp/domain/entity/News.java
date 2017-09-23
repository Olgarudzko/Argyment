package by.argyment.gymapp.domain.entity;

/**
 * @author Olga Rudzko
 */

public class News implements EntityModel {

    private String objectId;
    private String title;
    private String text;
    private String picture;
    private long time;

    public long getDate() {
        return time;
    }

    public void setDate(long date) {
        this.time = date;
    }
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
