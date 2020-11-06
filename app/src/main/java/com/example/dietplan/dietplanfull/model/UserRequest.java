package com.example.dietplan.dietplanfull.model;

/**
 * Created by natasya angelia on 4/27/2017.
 */
public class UserRequest {

    private String password;
    private String username;
    private String email;
    private int idealCalori;
    private int idealSnackCalori;
    private int bmi;
    private int weight;
    private int height;
    private int point;
    private String birthday;
    private int activityLevel;

    public UserRequest() {
    }

    public UserRequest(String password, String username, String email, int idealCalori, int idealSnackCalori, int bmi, int weight, int height, int point, String birthday, int activityLevel) {
        this.password = password;
        this.username = username;
        this.email = email;
        this.idealCalori = idealCalori;
        this.idealSnackCalori = idealSnackCalori;
        this.bmi = bmi;
        this.weight = weight;
        this.height = height;
        this.point = point;
        this.birthday = birthday;
        this.activityLevel = activityLevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdealCalori() {
        return idealCalori;
    }

    public void setIdealCalori(int idealCalori) {
        this.idealCalori = idealCalori;
    }

    public int getIdealSnackCalori() {
        return idealSnackCalori;
    }

    public void setIdealSnackCalori(int idealSnackCalori) {
        this.idealSnackCalori = idealSnackCalori;
    }

    public int getBmi() {
        return bmi;
    }

    public void setBmi(int bmi) {
        this.bmi = bmi;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }
}
