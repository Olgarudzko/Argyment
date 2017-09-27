package by.argyment.gymapp.extra;

/**
 * @author Olga Rudzko
 *
 * objects are created by trainers and contain of special bonuses for clients
 * according to business logic apllication can contain only 3 or less elephants without winner
 * user can have only one active bonus
 */

public class Elephant {
    private String objectId;
    private String slon;
    private String winner;
    private String trainer;

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getSlon() {
        return slon;
    }

    public void setSlon(String slon) {
        this.slon = slon;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
