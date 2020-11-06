package com.example.dietplan.dietplanfull.model;

/**
 * Created by natasya angelia on 4/27/2017.
 */
public class History {

    private String id;
    private String typeSnack;
    private String idEnergyDiary;
    private String idUser;
    private int energy;
    private String date;

    public History() {
    }

    public History(String id, String typeSnack, String idEnergyDiary, String idUser, int energy, String date) {
        this.id = id;
        this.typeSnack = typeSnack;
        this.idEnergyDiary = idEnergyDiary;
        this.idUser = idUser;
        this.energy = energy;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeSnack() {
        return typeSnack;
    }

    public void setTypeSnack(String typeSnack) {
        this.typeSnack = typeSnack;
    }

    public String getIdEnergyDiary() {
        return idEnergyDiary;
    }

    public void setIdEnergyDiary(String idEnergyDiary) {
        this.idEnergyDiary = idEnergyDiary;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
