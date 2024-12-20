/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tubes_try;

/**
 *
 * @author nhqkb
 */
import java.util.ArrayList;
import java.util.Scanner;
public class AdminPerusahaan extends Pengguna {
    
    private ArrayList<Lowongan> daftarLowongan = new ArrayList<>();
    private int idLowonganCounter = 1; // Counter untuk ID lowongan
    Scanner scanner = new Scanner(System.in);
    public AdminPerusahaan(int idPengguna,String username,String password){
        super(idPengguna,username,password,"Admin");
    }
    
    public Lowongan addLowongan(IMenu menu) {
        System.out.println("\n=== Tambahkan Lowongan Baru ===");
        System.out.print("Nama Perusahaan: ");
        String namaPerusahaan = scanner.nextLine();
        System.out.print("Posisi: ");
        String posisi = scanner.nextLine();
        System.out.print("Deskripsi Pekerjaan: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Kualifikasi: ");
        String kualifikasi = scanner.nextLine();
        String status = "Dibuka"; // Default status saat lowongan baru dibuat

        Lowongan lowonganBaru = new Lowongan(idLowonganCounter++, namaPerusahaan, posisi, deskripsi, kualifikasi, status);
        daftarLowongan.add(lowonganBaru);
        menu.tambahLowongan(lowonganBaru); // Tambahkan ke daftar global

        System.out.println("Lowongan berhasil ditambahkan!");
        return lowonganBaru;
    }
    
    public void showAllLowongan() {
        if (daftarLowongan.isEmpty()) {
            System.out.println("Tidak ada lowongan yang tersedia.");
        } else {
            for (Lowongan lowongan : daftarLowongan) {
                lowongan.tampilkanLowongan();
                System.out.println("-------------------");
            }
        }
    }
    public void updateStatusLowongan(int idLowongan, String statusBaru) {
        for (Lowongan lowongan : daftarLowongan) {
            if (lowongan.getIdLowongan() == idLowongan) {
                lowongan.setStatus(statusBaru);
                System.out.println("Status lowongan ID " + idLowongan + " berhasil diupdate menjadi: " + statusBaru);
                return;
            }
        }
        System.out.println("Lowongan dengan ID " + idLowongan + " tidak ditemukan.");
    }
    public void addEvent(){
        
    }
    public void showNotification(){
        
    }
    public void showApply(){
        if (daftarLowongan.isEmpty()) {
            System.out.println("Anda belum memiliki lowongan yang aktif.");
            return;
        }

        for (Lowongan lowongan : daftarLowongan) {
            System.out.println("\n=== Lamaran untuk Lowongan: " + lowongan.getPosisi() + " ===");
            lowongan.tampilkanLamaran();
        }
    }
    public void processApply(){
        
    }
}
