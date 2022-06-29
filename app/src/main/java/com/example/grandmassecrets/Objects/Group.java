package com.example.grandmassecrets.Objects;

import java.util.ArrayList;
import java.util.UUID;

public class Group {

    private String idGroup;
    private String name;
    private String description;
    private String imgGroup;
    private String groupCreator; //who create the group
    private ArrayList<String> recipesIds;
    private ArrayList<String> usersIds; //list of all the users that the group share with them


    public Group() {
    }

    public Group(String name, String description, String groupCreator) {
        this.idGroup = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.imgGroup = "url"; // TODO: 29/06/2022 change to url from fire base storage
        this.recipesIds = new ArrayList<>();
        this.groupCreator = groupCreator;
        this.usersIds = new ArrayList<>();
        this.usersIds.add(groupCreator);

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


    public String getGroupCreator() {
        return groupCreator;
    }

    public Group setGroupCreator(String groupCreator) {
        this.groupCreator = groupCreator;
        return this;
    }

    public ArrayList<String> getUsersIds() {
        return usersIds;
    }

    public Group setUsersIds(ArrayList<String> usersIds) {
        this.usersIds = usersIds;
        return this;
    }

    @Override
    public String toString() {
        return "Group{" +
                "idGroup='" + idGroup + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imgGroup='" + imgGroup + '\'' +
                ", recipesIds=" + recipesIds +
                ", groupCreator='" + groupCreator + '\'' +
                ", usersIds=" + usersIds +
                '}';
    }
}
