package model;

public class Orang {
    // 1. Field domain dibuat private (Information Hiding)
    private int id;
    private String nama;
    private String noHp;

    // 2. Constructor memanggil setter agar aturan validasi tidak diduplikasi
    public Orang(int id, String nama, String noHp) {
        setId(id);
        setNama(nama);
        setNoHp(noHp);
    }

    // 3. Getter
    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getNoHp() { return noHp; }

    // 4. Setter dengan Validasi (Domain Rules)
    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID tidak boleh negatif");
        }
        this.id = id;
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama wajib diisi");
        }
        this.nama = nama.trim();
    }

    public void setNoHp(String noHp) {
        String nilai = (noHp == null) ? "" : noHp.trim();
        if (!nilai.isEmpty() && !nilai.matches("[0-9+-]{8,20}")) {
            throw new IllegalArgumentException("Format nomor HP tidak valid");
        }
        this.noHp = nilai;
    }

    // 5. Method Informasi Umum
    public String getInfo() {
        return getNama() + (getNoHp().isEmpty() ? "" : " | HP: " + getNoHp());
    }
}