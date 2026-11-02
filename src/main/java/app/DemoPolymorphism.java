/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.ArrayList;
import java.util.List;
import model.Instruktur;
import model.Orang;
import model.Peserta;
/**
 *
 * @author Asus
 */
public class DemoPolymorphism {
    // Tahap 14: polymorphic argument
    public static void tampilkanInfo(Orang orang) {
        System.out.println(orang.getInfo());
    }

    // Latihan tahap 14: menerima List<Orang>, tanpa instanceof
    public static void cetakSemua(List<Orang> daftar) {
        for (Orang o : daftar) {
            System.out.println(o.getInfo());
        }
    }

    public static void main(String[] args) {
        Peserta peserta = new Peserta(1, "Ahmad Fauzan", "081234567801",
                "252001", "Informatika");
        Instruktur instruktur = new Instruktur(2, "Dina Pratama", "081211110001",
                "Java Desktop");

        // Tahap 12: simpan object berbeda dalam satu List<Orang>
        List<Orang> daftarOrang = new ArrayList<>();
        daftarOrang.add(peserta);
        daftarOrang.add(instruktur);
        System.out.println("Jumlah item: " + daftarOrang.size());

        // Tahap 13: loop tanpa if tipe
        System.out.println("--- loop getInfo ---");
        for (Orang o : daftarOrang) {
            System.out.println(o.getInfo());
        }

        // Tahap 14: polymorphic argument
        System.out.println("--- tampilkanInfo ---");
        tampilkanInfo(peserta);
        tampilkanInfo(instruktur);

        System.out.println("--- cetakSemua ---");
        cetakSemua(daftarOrang);

        // Tahap 15: instanceof hanya untuk data khusus subtype
        System.out.println("--- instanceof ---");
        for (Orang o : daftarOrang) {
            if (o instanceof Peserta) {
                Peserta p = (Peserta) o;
                System.out.println("NIM khusus peserta: " + p.getNim());
            }
        }

        // Tahap 16 (opsional): aktifkan baris di bawah untuk melihat bedanya.
        // daftarOrang.add(new Admin(3, "Rina Sari", "081200000003", "Operator"));

        System.out.println("--- Pendekatan A ---");
        for (Orang o : daftarOrang) {
            if (o instanceof Peserta) {
                Peserta p = (Peserta) o;
                System.out.println("PESERTA: " + p.getNama() + " / " + p.getNim());
            } else if (o instanceof Instruktur) {
                Instruktur i = (Instruktur) o;
                System.out.println("INSTRUKTUR: " + i.getNama() + " / " + i.getKeahlian());
            }
        }

        System.out.println("--- Pendekatan B ---");
        for (Orang o : daftarOrang) {
            System.out.println(o.getInfo());
        }
    }
}
