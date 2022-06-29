package com.example.grandmassecrets.Objects;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String uid;
    private String img; //todo url to fire store
    private String firstName;
    private String lastName;
    private String phoneNumber;


    public User() {}

    public User(String uid, String firstName, String lastName, String phoneNumber) {
        this.uid = uid;
        this.img = "url";
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getUid() {
        return uid;
    }

// ------- Builder --------


    public String getImg() {
        return img;
    }

    public User setImg(String img) {
        this.img = img;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", img='" + img + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> mapRes = new HashMap<>();
        mapRes.put("uid", uid);
        mapRes.put("img", img);
        mapRes.put("firstName", firstName);
        mapRes.put("lastName", lastName);
        mapRes.put("phoneNumber", phoneNumber);
        return mapRes;
    }

}
