package br.com.infoglobo.entity;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable {


    private String title;

    private String link;

    private List<Description> description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Description> getDescription() {
        return description;
    }

    public void setDescription(List<Description> description) {
        this.description = description;
    }
}
