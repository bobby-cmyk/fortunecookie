package fortunecookie;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientMain {
    
    public static void main(String[] args) throws UnknownHostException, IOException {

        // Default port: 12345
        int port = Constants.PORT;

        // If port is provided in args
        if (args.length > 1) {
            port = Integer.parseInt(args[0]);
        }

        // Continuously receive input from user
        while (true) {

            // Try to connect to server on port
            Socket sock = new Socket("localhost", port );

            // Instantiate scanner to receive input from user
            Scanner scan = new Scanner(System.in);
            
            // Receive command to either get cookie or close server
            System.out.printf("\nCommand (%s/%s): ", Constants.GET_COOKIE, Constants.CLOSE);
            
            String command = scan.nextLine().trim().toLowerCase();

            // Check if proper commands are given (get-cookie or close)
            if (!command.equals(Constants.GET_COOKIE) && 
                !command.equals(Constants.CLOSE)) 
            {
                System.out.println("Command does not exist!");
                // Get user to provide a new command
                continue;
            }
            
            // Send user command to server
            ClientSender cs = new ClientSender(sock);
            cs.send(command);

            System.out.print(command);

            // If command sent is "close", end client
            if (command.equals(Constants.CLOSE)) {
                break;
            }

            System.out.println("Before receiver");
            // Receive respone from server
            ClientReceiver cr = new ClientReceiver(sock);
            cr.receive();

            System.out.println("After receiver");
            
            // Close scanner and socket
            sock.close();
            scan.close();
        }
        
    }
}
