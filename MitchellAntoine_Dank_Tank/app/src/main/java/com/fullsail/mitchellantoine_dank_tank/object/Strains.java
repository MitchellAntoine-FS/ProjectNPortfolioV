package com.fullsail.mitchellantoine_dank_tank.object;

import java.io.Serializable;

public class Strains implements Serializable {

    private String name;
    private String description;
    private String effects;
    private String imageUrl;
    private String helps;
    private String type;

    @SuppressWarnings("unused")
    public Strains() {
        // Required Empty Constructor
    }

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
