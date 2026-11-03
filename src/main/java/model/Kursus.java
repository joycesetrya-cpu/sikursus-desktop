package model;

public class Kursus {
    private String kode;
    private String nama;
    private String level;
    private double biaya;

    public Kursus() {
    }

    public Kursus(String kode, String nama, String level, double biaya) {
        setKode(kode);
        setNama(nama);
        setLevel(level);
        setBiaya(biaya);
    }

    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public String getLevel() { return level; }
    public double getBiaya() { return biaya; }

    public void setKode(String kode) { this.kode = kode; }
    public void setNama(String nama) { this.nama = nama; }
    public void setLevel(String level) { this.level = level; }

    public void setBiaya(double biaya) {
        if (biaya < 0) {
            throw new IllegalArgumentException("Biaya tidak boleh negatif");
        }
        this.biaya = biaya;
    }

    // Method yang dibutuhkan oleh DemoKursus.java
    public double hitungBiayaSetelahDiskon(double diskonPersen) {
        if (diskonPersen < 0 || diskonPersen > 100) {
            throw new IllegalArgumentException("Diskon harus di antara 0 - 100%");
        }
        return this.biaya - (this.biaya * (diskonPersen / 100.0));
    }
}