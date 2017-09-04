package by.argyment.gymapp.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author Olga Rudzko
 */

public class Profile implements DataModel {

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("admin")
    private boolean isAdmin;

    @SerializedName("trainer")
    private boolean isTrainer;

    @SerializedName("status")
    private int status;

    @SerializedName("stars")
    private int stars;

    @SerializedName("slon")
    private String slon;

    @SerializedName("userpic")
    private String userpic;

    @SerializedName("objectId")
    private String objectId;

    @SerializedName("timeCheckin")
    private long timeCheckin;

    @SerializedName("timeStar")
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
