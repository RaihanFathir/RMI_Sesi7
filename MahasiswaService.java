package rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MahasiswaService extends Remote {
    List<String> getSemuaMahasiswa() throws RemoteException;
    String cariMahasiswa(String nim) throws RemoteException;
    void tambahMahasiswa(String nim, String nama) throws RemoteException;
}