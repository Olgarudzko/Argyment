package by.argyment.gymapp.data.entity;

import com.google.gson.annotations.SerializedName;

import by.argyment.gymapp.data.extra.Strings;

/**
 * @author Olga Rudzko
 */

public class VideoData implements DataModel {

    @SerializedName(Strings.LINK)
    private String url;
    @SerializedName(Strings.OBJECT_ID)
    private String objectId;
    @SerializedName(Strings.TITLE)
    private String title;
    @SerializedName(Strings.ADDEDAT)
    private Long addedAt;

    public Long getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Long addedAt) {
        this.addedAt = addedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
