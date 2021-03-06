package by.argyment.gymapp.domain.entity;

/**
 * @author Olga Rudzko
 *
 * objects are created by trainers and contain of special bonuses for clients
 */

public class Slon implements EntityModel{

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
