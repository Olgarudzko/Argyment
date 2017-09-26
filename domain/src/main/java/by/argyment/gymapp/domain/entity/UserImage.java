package by.argyment.gymapp.domain.entity;

import java.util.Date;

/**
 * @author Olga Rudzko
 */

public class UserImage implements EntityModel {

    private String link;
    private String email;
    private String objectId;
    private Long day;

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }
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
