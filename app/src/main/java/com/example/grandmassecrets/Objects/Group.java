package com.example.grandmassecrets.Objects;

import java.util.ArrayList;
import java.util.UUID;

public class Group {

    private String idGroup;
    private String name;
    private String description;
    private String imgGroup;
    private ArrayList<String> recipesIds;


    public Group() { }

    public Group(String name, String description) {
        this.idGroup = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.imgGroup = "url"; // TODO: 29/06/2022 change to url from fire base storage
        this.recipesIds = new ArrayList<>();
    }

    public String getIdGroup() {
        return idGroup;
    }

//    public Group setIdGroup(String idGroup) {
//        this.idGroup = idGroup;
//        return this;
//    }

    public String getName() {
        return name;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Group setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImgGroup() {
        return imgGroup;
    }

    public Group setImgGroup(String imgGroup) {
        this.imgGroup = imgGroup;
        return this;
    }

    public ArrayList<String> getRecipesIds() {
        return recipesIds;
    }

//    public Group setRecipesIds(ArrayList<String> recipesIds) {
//        this.recipesIds = recipesIds;
//        return this;
//    }


    @Override
    public String toString() {
        return "Group{" +
                "idGroup='" + idGroup + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imgGroup='" + imgGroup + '\'' +
                ", recipesIds=" + recipesIds +
                '}';
    }
}
