package rmi;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class MahasiswaClient {
    public static void main(String[] args) {
        try {
            // 1. Terhubung dengan RMI Server di localhost port 1099
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            MahasiswaService service = (MahasiswaService) registry.lookup("MahasiswaService");
            
            System.out.println("--- KONEKSI BERHASIL ---");

            // 2. Memanggil method untuk menambahkan data
            System.out.println("\nMenambahkan data baru...");
            service.tambahMahasiswa("103", "Budi");
            
            // 3. Menampilkan seluruh data mahasiswa
            System.out.println("\nDaftar Semua Mahasiswa:");
            List<String> data = service.getSemuaMahasiswa();
            for (String mhs : data) {
                System.out.println("- " + mhs);
            }

            // 4. Mencari mahasiswa berdasarkan NIM
            System.out.println("\nMencari NIM 101:");
            String hasilCari = service.cariMahasiswa("101");
            System.out.println("Hasil: " + hasilCari);

        } catch (Exception e) {
            // Menampilkan pesan jika koneksi gagal sesuai syarat soal
            System.err.println("Koneksi gagal! Pastikan RMI Server sudah berjalan.");
            e.printStackTrace();
        }
    }
}