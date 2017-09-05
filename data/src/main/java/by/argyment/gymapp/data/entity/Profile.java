package by.argyment.gymapp.data.entity;

import com.google.gson.annotations.SerializedName;

import by.argyment.gymapp.data.extra.Strings;

/**
 * @author Olga Rudzko
 */

public class Profile implements DataModel {

    @SerializedName(Strings.USERNAME)
    private String username;

    @SerializedName(Strings.EMAIL)
    private String email;

    @SerializedName(Strings.PASSWORD)
    private String password;

    @SerializedName(Strings.ADMIN)
    private boolean isAdmin;

    @SerializedName(Strings.TRAINER)
    private boolean isTrainer;

    @SerializedName(Strings.STATUS)
    private int status;

    @SerializedName(Strings.STARS)
    private int stars;

    @SerializedName(Strings.SLON)
    private String slon;

    @SerializedName(Strings.USERPIC)
    private String userpic;

    @SerializedName(Strings.OBJECT_ID)
    private String objectId;

    @SerializedName(Strings.TIME_CHECKIN)
    private long timeCheckin;

    @SerializedName(Strings.TIME_STAR)
    private long timeStar;

    public long getTimeCheckin() {
        return timeCheckin;
    }

    public void setTimeCheckin(long timeCheckin) {
        this.timeCheckin = timeCheckin;
    }

    public long getTimeStar() {
        return timeStar;
    }

    public void setTimeStar(long timeStar) {
        this.timeStar = timeStar;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isTrainer() {
        return isTrainer;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setTrainer(boolean trainer) {
        isTrainer = trainer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getSlon() {
        return slon;
    }

    public void setSlon(String slon) {
        this.slon = slon;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
