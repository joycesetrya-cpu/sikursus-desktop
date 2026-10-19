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
        setNim(nim);
        setProdi(prodi);
    }
    public String getNim() {
        return nim;
    }
    public void setNim(String nim) {
        if (nim == null || nim.trim().isEmpty())
            throw new IllegalArgumentException("NIM wajib diisi");
        this.nim = nim.trim();
    }
    public String getProdi() {
        return prodi;
    }
    public void setProdi(String prodi) {
        if (prodi == null || prodi.trim().isEmpty())
            throw new IllegalArgumentException("Prodi wajib diisi");
        this.prodi = prodi.trim();
    }
    @Override
    public String getInfo() {
    return "PESERTA | " + super.getInfo()
            + " | NIM: " + getNim()
            + " | Prodi: " + getProdi();
    }
}
