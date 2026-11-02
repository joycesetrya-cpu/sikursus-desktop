/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import model.Kursus;
/**
 *
 * @author Asus
 */
public class DemoKursus {
    public static void main(String[] args) {
        Kursus k1 = new Kursus(
                "JAVA-BSC",
                "Java Desktop Fundamental",
                "BASIC",
                500000
        );
        double hasil = k1.hitungBiayaSetelahDiskon(10);
        System.out.println("Biaya setelah diskon: " + hasil);
    }
}
