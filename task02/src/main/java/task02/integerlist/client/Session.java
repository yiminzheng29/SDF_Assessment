package task02.integerlist.client;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class Session {
    
    private Socket sock;
    private OutputStream os;
    private InputStream is;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public Session(Socket sock) {
        this.sock = sock;
    }

    private void initializeStreams(Socket sock) throws IOException {
        os = sock.getOutputStream();
        is = sock.getInputStream();
        oos = new ObjectOutputStream(os);
        ois = new ObjectInputStream(is);
    }

    private String readStr() throws IOException {
        return ois.readUTF();
    }

    private String readBoo() throws IOException {
        if (ois.readBoolean()==true) {
            return "True";
        }
        return "False";
    }

    private void writeStr(String line) throws IOException {
        oos.writeUTF(line);
        oos.flush();
    }

    private void writeFl(float value) throws IOException {
        oos.writeFloat(value);
        oos.flush();
    }

    private void close() throws IOException{
        os.close();
        is.close();
        
    }

    public void start() throws IOException {
        initializeStreams(sock);
        Console cons = System.console();
        boolean stop = false;


        while(!stop) {
            try {

                String req = readStr();
                System.out.printf("[Server] %s\n", req);
                // String[] terms = req.split(" ");
                // String reqID = terms[0];
                // String[] iList = terms[1].split(",");
                // LinkedList<Integer> intList = new LinkedList<Integer>();
                // for (int i =0; i<iList.length; i++) {
                //     intList.add(Integer.parseInt(iList[i]));
                // }

                String clientReqID = cons.readLine("Request ID: ");
                writeStr(clientReqID);
                String nric = cons.readLine("Name as per NRIC: ");
                writeStr(nric);
                String email = cons.readLine("Email: ");
                writeStr(email);

                String answer = cons.readLine("Float answer: ");
                writeFl(Float.parseFloat(answer));

            
                String response = readBoo();
                System.out.printf("[Server] Status: %s\n", response);
            
                
                if (response.equals("True")) {
                    System.out.println("SUCCESS");
                }
                if (response.equals("False")) {
                    String finalResp = readStr();
                    System.out.printf("[Server] Error message: %s\n", finalResp);
                    System.out.println("FAILED");
                }
                
                break;
            }catch (Exception ex) {
               
            }

        }
        close();
        System.out.println("Connection terminated");
        
    }
    
}
