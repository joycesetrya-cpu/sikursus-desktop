package model;

public class Peserta extends Orang {
    private String nim;
    private String prodi;
    private String jenisKelamin; 
    private String email;        

    // =========================================================================
    // CONSTRUCTOR 1: Untuk UjiValidasi & DemoPolymorphism (Mendukung Kode Lama)
    // =========================================================================
    public Peserta(int id, String nama, String noHp, String nim, String prodi) {
        super(id, nama, noHp);
        setNim(nim);
        setProdi(prodi);
        this.jenisKelamin = "L"; 
        this.email = "";
    }

    // =========================================================================
    // CONSTRUCTOR 2: Untuk Form GUI & Simpan Database (Mendukung Kode Baru)
    // =========================================================================
    public Peserta(String nim, String nama, String jenisKelamin, String prodi, String noHp, String email) {
        // Karena di GUI (Langkah 8) ID di-generate otomatis oleh MySQL (Auto Increment), 
        // kita kirim ID default 0 ke kelas Orang.
        super(0, nama, noHp); 
        setNim(nim);
        setProdi(prodi);
        this.jenisKelamin = jenisKelamin;
        this.email = email;
    }

    // =========================================================================
    // VALIDASI & GETTER SETTER
    // =========================================================================
    public String getNim() {
        return nim;
    }

    public final void setNim(String nim) {
        if (nim == null || nim.trim().isEmpty()) {
            throw new IllegalArgumentException("NIM wajib diisi");
        }
        this.nim = nim.trim();
    }

    public String getProdi() {
        return prodi;
    }

    public final void setProdi(String prodi) {
        if (prodi == null || prodi.trim().isEmpty()) {
            throw new IllegalArgumentException("Prodi wajib diisi");
        }
        this.prodi = prodi.trim();
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getInfo() {
        return "PESERTA | " + super.getInfo()
                + " | NIM: " + getNim()
                + " | Prodi: " + getProdi()
                + " | JK: " + getJenisKelamin();
    }
}