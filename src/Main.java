import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    private ServerSocket serverSocket;
    private Socket spojeni;
    private static BufferedReader vstup;
    private static PrintWriter vystup;
    private static boolean bezim;
    private static String text;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server běží");
            Socket socket = serverSocket.accept();
            vstup = new BufferedReader(new InputStreamReader((socket.getInputStream()), "UTF-8"));
            vystup = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
            Main.zpracujPrikaz();
        } catch (UnknownHostException e) {
            System.out.println(e);
        } catch (IOException i) {
            System.out.println(i);
        }
        System.out.println("konec");
    }

    public static void zpracujPrikaz() {
        HashSet<String> zaznamy = new HashSet<>();
        HashMap<String, String> prikazy = new HashMap<>();
        prikazy.put("USER", "uživatel");
        prikazy.put("MESSAGE", "zpráva");
        prikazy.put("QUIT", "konec");
        bezim = true;
        while (bezim) {
            try {
                text = vstup.readLine();
                String textParse[] = text.split(" ", 2);
                String prikaz = textParse[0];
                //String zbytek = textParse[1];
                boolean existuje = false;
                for (String hledane : prikazy.keySet()) {
                    if (hledane.equals(prikaz)) {
                        existuje = true;
                        break;
                    }
                }
                if (existuje) {
                    System.out.println("přišlo " + text);
                    zaznamy.add(text);
                    if (prikaz.equals("QUIT")) {
                        vystup.println("OK, server končí");
                        bezim = false;
                        break;
                    } else {
                        vystup.println("OK");
                    }
                } else {
                    System.out.println("neplatný příkaz");
                    vystup.println("neplatný příkaz");
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}

