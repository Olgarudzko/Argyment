package by.argyment.gymapp.data.entity;

import com.google.gson.annotations.SerializedName;

import by.argyment.gymapp.data.extra.Strings;

/**
 * @author Olga Rudzko
 */

public class SlonData implements DataModel{
    @SerializedName(Strings.OBJECT_ID)
    private String objectId;
    @SerializedName(Strings.SLON)
    private String slon;
    @SerializedName(Strings.WINNER)
    private String winner;
    @SerializedName(Strings.TRAINER)
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
