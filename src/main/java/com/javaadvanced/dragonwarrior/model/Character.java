package com.javaadvanced.dragonwarrior.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Représentation d'un personnage.")
public class Character {
    @ApiModelProperty(notes = "Identifiant unique du personnage", example = "1", required = true, position = 0)
    private int id;
    @ApiModelProperty(notes = "Nom du personnage.", example = "Toto", required = true, position = 1)
    private String name;
    @ApiModelProperty(notes = "Type du personnage.", example = "Wizard", required = true, position = 2)
    private String type;

    public Character(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
