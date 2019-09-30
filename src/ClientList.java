import java.util.ArrayList;

public class ClientList {
    public ArrayList<Vlakno> Clients;

    public ClientList(){
        Clients = new ArrayList<>();
    }

    public synchronized void pridejKlienta(Vlakno vlakno){
        Clients.add(vlakno);
    }

    public synchronized void odeberKlienta(Vlakno vlakno){
        Clients.remove(vlakno);
    }

    public ArrayList getClients(){
        return Clients;
    }

    public int getMyIndex(Vlakno vlakno){
        return Clients.indexOf(vlakno);
    }

    public int getLength(){
        return Clients.size();
    }

    public Vlakno getVlakno(int i){
        return Clients.get(i);
    }
}
