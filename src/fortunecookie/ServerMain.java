
package fortunecookie;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

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

        System.out.printf(">>> Waiting for connection at %s\n", port);
        
        // Open a port to accept connection
        ServerSocket server = new ServerSocket(port);

        Socket sock = server.accept();

        // Will start running from here once connected
        System.out.println(">>> New connection established");
       
        while (true) {

            ServerReceiver sr = new ServerReceiver(sock);

            System.out.println("\n>>> Waiting for client's command\n");

            // Receive client command from server receiver
            String clientCommand = sr.receive();

            if (clientCommand.equals(Constants.CLOSE)) {
                System.out.println(">>> Connection has ended\n");
                server.close();
                break;
            }

            ServerSender ss = new ServerSender(sock, cookieManager);

            ss.sendCookie();
        }

        
    
    }    
}