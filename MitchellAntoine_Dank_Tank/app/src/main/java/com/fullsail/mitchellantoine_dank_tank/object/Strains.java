package com.fullsail.mitchellantoine_dank_tank.object;

public class Strains {

    private final String name;
    private String description;
    private String effects;
    private final String imageUrl;
    private String helps;
    private String type;

    public Strains(String name, String description, String effects, String imageUrl, String helps, String type) {
        this.name = name;
        this.description = description;
        this.effects = effects;
        this.imageUrl = imageUrl;
        this.helps = helps;
        this.type = type;
    }

    public Strains(String name, String imageUrl) {
        this.name=name;
        this.imageUrl=imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEffects() {
        return effects;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getHelps() {
        return helps;
    }

    public String getType() {
        return type;
    }
}
