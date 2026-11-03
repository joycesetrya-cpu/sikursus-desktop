
package model;

public class Peserta extends Orang {

    private String nim;
    private String jenisKelamin;
    private String prodi;
    private String noHp;
    private String email;

    public Peserta() {
        super();
    }

    // Constructor untuk kode lama: 5 parameter
    public Peserta(int id, String nim, String nama,
            String jenisKelamin, String prodi) {

        this(id, nim, nama, jenisKelamin,
                prodi, "", "");
    }

    // Constructor untuk FormPeserta lama: 6 parameter String
    public Peserta(String nim, String nama,
            String jenisKelamin, String prodi,
            String noHp, String email) {

        this(0, nim, nama, jenisKelamin,
                prodi, noHp, email);
    }

    // Constructor lengkap untuk CRUD
    public Peserta(int id, String nim, String nama,
            String jenisKelamin, String prodi,
            String noHp, String email) {

        super(id, nama);

        this.nim = nim;
        this.jenisKelamin = jenisKelamin;
        this.prodi = prodi;
        this.noHp = noHp;
        this.email = email;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getInfo() {
        return "PESERTA: " + super.getInfo()
                + " | NIM: " + getNim()
                + " | Prodi: " + getProdi()
                + " | JK: " + getJenisKelamin();
    }
}
