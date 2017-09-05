package by.argyment.gymapp.data.entity;

import com.google.gson.annotations.SerializedName;

import by.argyment.gymapp.data.extra.Strings;

/**
 * @author Olga Rudzko
 */

public class Image implements DataModel {

    @SerializedName(Strings.LINK)
    private String link;

    @SerializedName(Strings.EMAIL)
    private String email;

    @SerializedName(Strings.OBJECT_ID)
    private String objectId;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
