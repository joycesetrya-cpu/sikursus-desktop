
package model;

public class Orang {

    private int id;
    private String nama;

    public Orang() {
    }

    public Orang(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    // Constructor tambahan untuk Instruktur
    public Orang(int id, String nama, String informasi) {
        this.id = id;
        this.nama = nama;
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

    public String getInfo() {
        return "Nama: " + nama;
    }
}
