import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.HashSet;

public class Server {
    public boolean bezim;
    public String text;
    public BufferedReader vstup;
    public PrintWriter vystup;
    public Vlakno vlakno;


    public Server(BufferedReader vstup, PrintWriter vystup, Vlakno vlakno){
        this.vstup = vstup;
        this.vystup = vystup;
        this.vlakno = vlakno;
        zpracujPrikaz();
    }

    /*
    public void spojSe(){
        try {
            spojeni = serverSocket.accept();
            vstup = new BufferedReader(new InputStreamReader((spojeni.getInputStream()), "UTF-8"));
            vystup = new PrintWriter(new OutputStreamWriter(spojeni.getOutputStream(), "UTF-8"), true);
        } catch(IOException e){
            System.out.println(e);
        }
     */


    public void zpracujPrikaz() {
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
                /*
                if (text.equals("NEW CLIENT")) {
                    Thread vlakno = new Thread(new Vlakno());
                    vlakno.run();
                */

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
                        odesliOstatnim(text);
                    }
                } else {
                    System.out.println("neplatný příkaz");
                    vystup.println("neplatný příkaz");
                }

                } catch(IOException e){
                    System.out.println(e);
                }

        }
    }

    public void odesliOstatnim(String text){
        for(int i = 0; i < vlakno.getClients().getLength(); i++){
            if(i != vlakno.getMyIndexInClients()){
                vlakno.getClients().getVlakno(i).vystup.println("text");
            }
        }
    }

}
