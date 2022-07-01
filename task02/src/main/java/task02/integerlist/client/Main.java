package task02.integerlist.client;

import java.io.IOException;
import java.net.Socket;

public class Main {
    
    public static void main(String[] args) throws IOException{
        
        String address = args[0];
        int port = Integer.parseInt(args[1]);

        System.out.printf("Connecting to %s on port %d \n", address, port);

        Socket sock = new Socket(address, port);

        System.out.println("Connected...");

        try {

            Session sess = new Session(sock);
            sess.start();
        } catch (Exception ex) {

        }


    }
}
