package id.or.codelabs.cardviewrealm.helper;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by FitriFebriana on 8/8/2016.
 */

public class Article extends RealmObject {
    @PrimaryKey
    private int id;
    private String title;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}