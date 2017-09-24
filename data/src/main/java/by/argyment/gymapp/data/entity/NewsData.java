package by.argyment.gymapp.data.entity;

import com.google.gson.annotations.SerializedName;

import by.argyment.gymapp.data.extra.Strings;

/**
 * @author Olga Rudzko
 */

public class NewsData implements DataModel {

    @SerializedName(Strings.OBJECT_ID)
    private String objectId;
    @SerializedName(Strings.TITLE)
    private String title;
    @SerializedName(Strings.CONTENTS)
    private String text;
    @SerializedName(Strings.PICTURE)
    private String picture;
    @SerializedName(Strings.ADDEDAT)
    private Long time;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
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
