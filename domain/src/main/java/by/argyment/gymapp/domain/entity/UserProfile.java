package by.argyment.gymapp.domain.entity;

/**
 * @author Olga Rudzko
 */

public class UserProfile implements EntityModel {

    private String username;
    private boolean isAdmin;
    private boolean isTrainer;
    private int status;
    private int stars;
    private String userpic;
    private String slon;
    private String email;
    private String password;
    private String objectId;
    private long timeCheckin;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isTrainer() {
        return isTrainer;
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
