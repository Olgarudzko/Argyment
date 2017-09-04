package by.argyment.gymapp.domain.entity;

/**
 * @author Olga Rudzko
 */

public class UserImage implements EntityModel {

    private String link;
    private String email;
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
