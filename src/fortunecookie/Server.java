
package fortunecookie;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        // Exit program if number of arguments provided is not 2
        if (args.length != 2) {
            System.err.println(">>> Proper usage: port number (12345) + cookies.txt");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);

        String cookiefilePath = args[1];

        // Initialise Cookie
        Cookie cookieManager = new Cookie();

        // Read the cookie file
        cookieManager.readFile(cookiefilePath);

        // Open a port to accept connection
        ServerSocket server = new ServerSocket(port);
        
        System.out.printf(">>> Waiting for connection at %s\n", port);
        // Block until connection arrives
        Socket sock = server.accept();

        // Will start running from here once connected
        System.out.println(">>> New connection established");

        // Set up output
        OutputStream os = sock.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        // Set up input
        InputStream is = sock.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        while (true) {
            System.out.println("\n>>> Waiting for client's command\n");

            String clientCommand = dis.readUTF();

            System.out.println(clientCommand);

            if (clientCommand.equals("get-cookie")) {
                dos.writeUTF("Programming Cookie: " + cookieManager.getRandomCookie());
                dos.flush();
                System.out.println(">>> Sent a fortune cookie to client");
            }

            else if (clientCommand.equals("close")) {
                System.out.println(">>> Connection has ended");
                break;
            }

            else {
                dos.writeUTF(">>> Unknown command");
                dos.flush();
            }
        }

        dos.close();
        dis.close();
        server.close();
        
        
    }    
}