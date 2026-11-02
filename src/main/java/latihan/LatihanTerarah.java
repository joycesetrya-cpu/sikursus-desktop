package latihan;

public class LatihanTerarah {

    public static void main(String[] args) {

        // Variabel
        String kode = "JAVA-BSC";
        String nama = "Java Desktop Fundamental";
        double biaya = 2400000;
        double registrasi = 500000;
        boolean aktif = true;

        // Total sebelum diskon
        double totalSebelumDiskon = biaya + registrasi;

        // Menentukan diskon dan status
        double diskon;
        String status;

        if (totalSebelumDiskon >= 3500000) {
            diskon = 0.15;
            status = "MAHAL";
        } else if (totalSebelumDiskon >= 1500000) {
            diskon = 0.10;
            status = "STANDAR";
        } else {
            diskon = 0.05;
            status = "TERJANGKAU";
        }

        // Menghitung potongan dan total bayar
        double potongan = totalSebelumDiskon * diskon;
        double total = totalSebelumDiskon - potongan;

        // Output
        System.out.println("Kode                : " + kode);
        System.out.println("Kursus              : " + nama);
        System.out.println("Aktif               : " + aktif);
        System.out.printf("Biaya Kursus        : Rp%,.0f%n", biaya);
        System.out.printf("Biaya Registrasi    : Rp%,.0f%n", registrasi);
        System.out.printf("Total Sebelum Diskon: Rp%,.0f%n", totalSebelumDiskon);
        System.out.printf("Diskon              : %.0f%%%n", diskon * 100);
        System.out.printf("Potongan            : Rp%,.0f%n", potongan);
        System.out.printf("Total Bayar         : Rp%,.0f%n", total);
        System.out.println("Status              : " + status);
    }
}
