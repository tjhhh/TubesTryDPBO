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
    private ArrayList<Notifikasi> daftarNotifikasi = new ArrayList<>();
    private ArrayList<Lowongan> daftarLowongan = new ArrayList<>();
    private ArrayList<Pengguna> users = new ArrayList<>();
    private int idLowonganCounter = 1; // Counter untuk ID lowongan
    private Pelamar pelamar;
    
    Scanner scanner = new Scanner(System.in);
    public AdminPerusahaan(int idPengguna,String username,String password, String role, String email){
        super(idPengguna,username,password,"Admin", email);
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

        Lowongan lowonganBaru = new Lowongan(idLowonganCounter++, namaPerusahaan, posisi, deskripsi, kualifikasi, status,this,pelamar);
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
    public void processApply() {
        if (daftarLowongan.isEmpty()) {
            System.out.println("Tidak ada lowongan yang tersedia.");
            return;
        }
    
        Scanner scanner = new Scanner(System.in);
    
        // Tampilkan semua lowongan yang memiliki lamaran
        for (Lowongan lowongan : daftarLowongan) {
            System.out.println("\n=== Lowongan: " + lowongan.getPosisi() + " ===");
            if (lowongan.getDaftarLamaran().isEmpty()) {
                System.out.println("Belum ada lamaran untuk lowongan ini.");
            } else {
                lowongan.tampilkanLamaran();
            }
        }
    
        // Input ID Lowongan
        System.out.print("Masukkan ID Lowongan yang ingin diproses: ");
        int idLowongan = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline
    
        // Cari lowongan berdasarkan ID
        Lowongan targetLowongan = null;
        for (Lowongan lowongan : daftarLowongan) {
            if (lowongan.getIdLowongan() == idLowongan) {
                targetLowongan = lowongan;
                break;
            }
        }
    
        if (targetLowongan == null) {
            System.out.println("Lowongan dengan ID tersebut tidak ditemukan.");
            return;
        }
    
        // Tampilkan lamaran untuk lowongan tersebut
        if (targetLowongan.getDaftarLamaran().isEmpty()) {
            System.out.println("Tidak ada lamaran untuk lowongan ini.");
            return;
        }
    
        // Tampilkan detail lamaran
        System.out.println("=== Lamaran untuk Lowongan: " + targetLowongan.getPosisi() + " ===");
        targetLowongan.tampilkanLamaran();

        // Pilih ID Lamaran yang ingin diproses
        System.out.print("Masukkan ID Lamaran yang ingin diproses: ");
        int idLamaran = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        // Cari lamaran berdasarkan ID
        Lamaran targetLamaran = null;
        for (Lamaran lamaran : targetLowongan.getDaftarLamaran()) {
            if (lamaran.getIdLamaran() == idLamaran) {
                targetLamaran = lamaran;
                break;
            }
        }

        if (targetLamaran == null) {
            System.out.println("ID Lamaran tidak ditemukan.");
            return;
        }

        // Tampilkan opsi untuk memproses lamaran
        System.out.println("1. Terima Lamaran");
        System.out.println("2. Tolak Lamaran");
        System.out.print("Pilih aksi: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        String statusBaru;
        if (pilihan == 1) {
            statusBaru = "Diterima";
        } else if (pilihan == 2) {
            statusBaru = "Ditolak";
        } else {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        // Perbarui status lamaran
        targetLamaran.setStatusLamaran(statusBaru);

        // Minta input waktu pengiriman dari admin
        System.out.print("Masukkan waktu pengiriman notifikasi (format: dd-MM-yyyy): ");
        String waktuPengiriman = scanner.nextLine();

        // Kirim notifikasi ke pelamar
         
         Pelamar pelamarr = targetLamaran.getPelamar(); // Pastikan ada getter di Lowongan
         if (pelamarr != null) {
             String pesanNotifikasi = "Admin ID " + this.getIdPengguna() + " telah memproses lamaran anda " + targetLowongan.getPosisi() + " ,status lamaran anda: " + statusBaru;
             Notifikasi notifikasi = new Notifikasi(pelamarr.getIdPengguna(), pesanNotifikasi, waktuPengiriman);
             pelamarr.tambahNotifikasi(notifikasi);
         }

        System.out.println("Lamaran dengan ID " + idLamaran + " telah " + statusBaru.toLowerCase() + ".");
    }

    @Override
    public void showNotification(){
        if (daftarNotifikasi.isEmpty()) {
            System.out.println("Tidak ada notifikasi untuk Anda.");
        } else {
            System.out.println("\n=== Notifikasi Anda ===");
            for (Notifikasi notifikasi : daftarNotifikasi) {
                System.out.println("ID Notifikasi: " + notifikasi.getIdNotifikasi());
                System.out.println("Pesan: " + notifikasi.getPesan());
                System.out.println("Waktu Pengiriman: " + notifikasi.getWaktuPengiriman());
                System.out.println("-------------------");
            }
        }
    }

    public void tambahNotifikasi(Notifikasi notifikasi) {
        daftarNotifikasi.add(notifikasi);
    }

    public void showInfoPengguna(){
        System.out.println("======Informasi Admin Perusahaan======");
        System.out.println("ID Admin\t:" + getIdPengguna());
        System.out.println("Username\t:" + getUsername());
        System.out.println("Role\t:" + getRole());
        System.out.println("Email\t:" +getEmail());
    }
}
