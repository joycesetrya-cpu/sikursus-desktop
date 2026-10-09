/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Asus
 */
public class Orang {
    private int id;
    private String nama;
    private String noHp;
    public Orang(int id, String nama, String noHp) {
        this.id = id;
        this.nama = nama;
        this.noHp = noHp;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNoHp() {
        return noHp;
    }
    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
    public String getInfo() {
        return "ID: " + id
                + " | Nama: " + nama
                + " | No. HP: " + noHp;
    }
}
