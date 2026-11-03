package app;

import model.Peserta;

public class UjiValidasi {
    public static void main(String[] args) {
        try {
            // Mencoba membuat objek dengan input invalid (misal: nama kosong)
            Peserta p = new Peserta(1, "Awa Aimasugira", "081234567890", "2924021", "Informatika");
            System.out.println(p.getNama());
        } catch (IllegalArgumentException ex) {
            System.out.println("DITOLAK: " + ex.getMessage());
        }
    }
}