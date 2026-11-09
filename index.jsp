<%@ page import="java.rmi.registry.LocateRegistry" %>
<%@ page import="java.rmi.registry.Registry" %>
<%@ page import="java.util.List" %>
<!-- Ganti "NamaPackage" di bawah ini jika kamu menggunakan package saat compile tadi. Jika tidak, abaikan. -->
<%@ page import="rmi.MahasiswaService, java.rmi.registry.*, java.util.List" %>
<%
    MahasiswaService service = null;
    String pesanError = "";
    List<String> daftarMhs = null;

    try {
        // Terhubung ke RMI Server
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        service = (MahasiswaService) registry.lookup("MahasiswaService");

        // Menangkap request penambahan data dari Form
        if(request.getParameter("tambah") != null) {
            String nim = request.getParameter("nim");
            String nama = request.getParameter("nama");
            if(nim != null && !nim.isEmpty() && nama != null && !nama.isEmpty()){
                service.tambahMahasiswa(nim, nama);
            }
        }
        
        // Mengambil semua data untuk ditampilkan di tabel
        daftarMhs = service.getSemuaMahasiswa();

    } catch (Exception e) {
        pesanError = "Koneksi ke RMI Server gagal! Pastikan server menyala.";
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Data Mahasiswa RMI</title>
</head>
<body>
    <h2>Sistem Monitoring Data Mahasiswa</h2>
    
    <% if(!pesanError.isEmpty()) { %>
        <p style="color:red;"><%= pesanError %></p>
    <% } %>

    <!-- Form Input Data -->
    <form method="POST">
        NIM: <input type="text" name="nim" required>
        Nama: <input type="text" name="nama" required>
        <button type="submit" name="tambah">Tambah Data</button>
    </form>
    <br>

    <!-- Tabel Data -->
    <table border="1">
        <tr><th>Daftar Mahasiswa (NIM - Nama)</th></tr>
        <% if(daftarMhs != null) {
            for(String mhs : daftarMhs) { %>
                <tr><td><%= mhs %></td></tr>
        <%  }
           } %>
    </table>
</body>
</html>