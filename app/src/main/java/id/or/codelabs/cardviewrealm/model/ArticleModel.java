package id.or.codelabs.cardviewrealm.model;

/**
 * Created by FitriFebriana on 8/9/2016.
 */

public class ArticleModel {
    private int id;
    private String title;
    private String description;
 
 
    public ArticleModel(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
 
    public int getId() { 
        return id;
    } 

    public void setId(int id) {
        this.id = id;
    } 

    public String getTitle() {
        return title;
    } 

    public void setTitle(String title) {
        this.title = title;
    } 

    public String getDescription() {
        return description;
    } 

    public void setDescription(String description) {
        this.description = description;
    } 
} 