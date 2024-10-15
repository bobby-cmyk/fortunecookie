package fortunecookie;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        // Tries to connect to an appplication on local host listining to port 3000
        Socket sock = new Socket("localhost", 12345);

        System.out.println(">>> New connection established");

        // Set up input
        InputStream is = sock.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        // Set up output
        OutputStream os = sock.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        Scanner scan = new Scanner(System.in);

        while (true) {
            
            System.out.print("\nCommand (get-cookie/close): ");
            String command = scan.nextLine();
            
            if (command.equals("close")) {
                System.out.println(">>> Connection will be ended");
                dos.writeUTF(command);
                dos.flush();
                break;
            }

            dos.writeUTF(command);
            dos.flush();

            System.out.println("\n>>> Command sent to server\n");

            String serverMessage = dis.readUTF();
            
            System.out.println(serverMessage);
        }

        scan.close();
        dis.close();
        dos.close();
        sock.close();

    }
}
