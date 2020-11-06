package com.example.dietplan.dietplanfull.model;

/**
 * Created by natasya angelia on 4/27/2017.
 */
public class EnergyDiary {

    private String id;
    private String kategori;
    private String nama;
    private int kalori;

    public EnergyDiary() {
    }

    public EnergyDiary(String id, String kategori, String nama, int kalori) {
        this.id = id;
        this.kategori = kategori;
        this.nama = nama;
        this.kalori = kalori;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getKalori() {
        return kalori;
    }

    public void setKalori(int kalori) {
        this.kalori = kalori;
    }
}
