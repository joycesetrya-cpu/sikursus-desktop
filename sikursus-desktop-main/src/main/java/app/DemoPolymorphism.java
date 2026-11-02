package app;

import model.Orang;
import model.Peserta;
import model.Instruktur;
import java.util.ArrayList;
import java.util.List;

public class DemoPolymorphism {

    // Method menerima parameter polimorfik (Polymorphic Argument)
    public static void tampilkanInfo(Orang orang) {
        System.out.println(orang.getInfo());
    }

    public static void main(String[] args) {
        // 1. Membuat Objek Concrete
        Peserta peserta = new Peserta(1, "Awa Aimasugira", "081234567801", "2924021", "Informatika");
        Instruktur instruktur = new Instruktur(2, "Annisa Miftahul Jannah", "081211110001", "Java Desktop");

        // 2. Menyimpan Objek Berbeda pada List dengan Tipe Parent (List<Orang>)
        List<Orang> daftarOrang = new ArrayList<>();
        daftarOrang.add(peserta);
        daftarOrang.add(instruktur);

        // 3. Loop Polimorfik (Dynamic Dispatch: Pemanggilan method sesuai tipe objek konkret)
        System.out.println("=== DISPLAY VIA LOOP LIST<ORANG> ===");
        for (Orang o : daftarOrang) {
            System.out.println(o.getInfo());
        }

        // 4. Demonstrasi Polymorphic Argument
        System.out.println("\n=== DISPLAY VIA POLYMORPHIC ARGUMENT ===");
        tampilkanInfo(peserta);
        tampilkanInfo(instruktur);

        // 5. Pengecekan Tipe Spesifik dengan instanceof & Casting Aman
        System.out.println("\n=== PENGECEKAN INSTANCEOF & DOWNCASTING ===");
        for (Orang o : daftarOrang) {
            if (o instanceof Peserta) {
                Peserta p = (Peserta) o;
                System.out.println("NIM Khusus Peserta: " + p.getNim());
            }
        }
    }
}