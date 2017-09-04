package by.argyment.gymapp.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author Olga Rudzko
 */

public class Image implements DataModel {

    @SerializedName("link")
    private String link;

    @SerializedName("email")
    private String email;

    @SerializedName("objectId")
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
