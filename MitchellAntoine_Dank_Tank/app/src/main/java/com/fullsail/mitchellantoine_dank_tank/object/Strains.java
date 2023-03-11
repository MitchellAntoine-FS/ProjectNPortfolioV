package com.fullsail.mitchellantoine_dank_tank.object;

public class Strains {

    private final String name;
    private final String infoOf;
    private final String effectsOf;
    private final String imageUrl;
    private final String conditionOf;
    private final String typeOf;

    public Strains(String name, String infoOf, String effectsOf, String imageUrl, String conditionOf, String typeOf) {
        this.name = name;
        this.infoOf = infoOf;
        this.effectsOf = effectsOf;
        this.imageUrl = imageUrl;
        this.conditionOf = conditionOf;
        this.typeOf = typeOf;
    }

    public String getName() {
        return name;
    }

    public String getInfoOf() {
        return infoOf;
    }

    public String getEffectsOf() {
        return effectsOf;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getConditionOf() {
        return conditionOf;
    }

    public String getTypeOf() {
        return typeOf;
    }
}
