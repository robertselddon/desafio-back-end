package br.com.infoglobo.types;

public enum CrawlerType {


    IMG("img"), TEXT("text"), LINKS("links");

    private final String value;


    CrawlerType(String value){
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
