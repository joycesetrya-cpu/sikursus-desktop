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
public class Instruktur extends Orang {
     private String keahlian;
    public Instruktur(int id, String nama, String noHp,
                      String keahlian) {
        super(id, nama, noHp);
        this.keahlian = keahlian;
    }
    public String getKeahlian() {
        return keahlian;
    }
    public void setKeahlian(String keahlian) {
        this.keahlian = keahlian;
    }
    @Override
    public String getInfo() {
        return "[Instruktur] " + super.getInfo()
                + " | Keahlian: " + keahlian;
    }
}
