/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan;

/**
 *
 * @author USER
 */
public class LatihanBiayaKursus {  //class
  public static void main(String[] args){ //main method
      
      //variabel
      String kode = "JAVA-BSC";
String nama = "Java Desktop Fundamental";
double biaya = 500_000;
double diskon = 0.10;
boolean aktif = true;
double potongan = biaya * diskon;
double total = biaya - potongan;

 //deklarasi / output
System.out.println("Kode   : " + kode);
System.out.println("Kursus : " + nama);
System.out.println("Aktif  : " + aktif);
System.out.printf("Total  : Rp%,.0f%n", total);
  }  
}
