package rmi;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaServer extends UnicastRemoteObject implements MahasiswaService {
    private List<String> dataMahasiswa;

    protected MahasiswaServer() throws RemoteException {
        super();
        dataMahasiswa = new ArrayList<>();
        // Data awal dummy
        dataMahasiswa.add("101-Raihan");
        dataMahasiswa.add("102-Agil");
    }

    @Override
    public List<String> getSemuaMahasiswa() throws RemoteException {
        return dataMahasiswa;
    }

    @Override
    public String cariMahasiswa(String nim) throws RemoteException {
        for (String mhs : dataMahasiswa) {
            if (mhs.startsWith(nim)) {
                return mhs;
            }
        }
        return "Mahasiswa tidak ditemukan";
    }

    @Override
    public void tambahMahasiswa(String nim, String nama) throws RemoteException {
        dataMahasiswa.add(nim + "-" + nama);
        System.out.println("Data ditambahkan: " + nama);
    }

    public static void main(String[] args) {
        try {
            MahasiswaServer server = new MahasiswaServer();
            // Membuat RMI Registry di port 1099 sesuai soal
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("MahasiswaService", server);
            System.out.println("RMI Server siap dan berjalan di port 1099...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}