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
public class Peserta extends Orang {
    private String nim;
    private String prodi;
    public Peserta(int id, String nama, String noHp,
                   String nim, String prodi) {
        super(id, nama, noHp);
        this.nim = nim;
        this.prodi = prodi;
    }
    public String getNim() {
        return nim;
    }
    public void setNim(String nim) {
        this.nim = nim;
    }
    public String getProdi() {
        return prodi;
    }
    public void setProdi(String prodi) {
        this.prodi = prodi;
    }
    @Override
    public String getInfo() {
        return "[Peserta] " + super.getInfo()
                + " | NIM: " + nim
                + " | Prodi: " + prodi;
    }
}
