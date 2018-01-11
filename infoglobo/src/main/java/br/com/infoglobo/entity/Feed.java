package br.com.infoglobo.entity;


import java.io.Serializable;
import java.util.List;


public class Feed implements Serializable{


    private List<Item> item;

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }
}
