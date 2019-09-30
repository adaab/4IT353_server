import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Vlakno implements Runnable {
    public static BufferedReader vstup;
    public static PrintWriter vystup;
    public static ServerSocket serverSocket;
    private static Socket spojeni;
    public Server server;
    public ClientList clients;

    public Vlakno(Socket socket, ClientList clients) {
        this.spojeni = socket;
        this.clients = clients;
        //run();
    }

    @Override
    public void run() {
        System.out.println("spouštím nové vlákno");
        try {
            vstup = new BufferedReader(new InputStreamReader((spojeni.getInputStream()), "UTF-8"));
            vystup = new PrintWriter(new OutputStreamWriter(spojeni.getOutputStream(), "UTF-8"), true);
        } catch (IOException e) {
            System.out.println(e);
        }
        server = new Server(vstup, vystup, this);
    }

    public int getMyIndexInClients() {
        return clients.getMyIndex(this);
    }

    public void removeFromClients(){
        clients.odeberKlienta(this);
    }

    public ClientList getClients(){
        return clients;
    }
}
